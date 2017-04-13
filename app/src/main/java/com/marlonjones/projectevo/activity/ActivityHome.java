package com.marlonjones.projectevo.activity;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.kishan.askpermission.AskPermission;
import com.kishan.askpermission.ErrorCallback;
import com.kishan.askpermission.PermissionCallback;
import com.kishan.askpermission.PermissionInterface;
import com.marlonjones.projectevo.R;
import com.marlonjones.projectevo.SettingsActivity;
import com.marlonjones.projectevo.adapter.SlideMenuAdapter;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnMenuTabClickListener;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class ActivityHome extends AppCompatActivity implements PermissionCallback, ErrorCallback {

	private Context mContext;
	private Toolbar toolbar;
	private FragmentManager fragmentManager = null;
    private FragmentTransaction fragmentTransaction = null;
	private Fragment currentFragment=null;
	private BottomBar mBottomBar;
	private SlideMenuAdapter mSlideMenuAdapter;
	private int currentPosition=0;
	private static final int GET_PERMISSIONS = 20;
    int currentapiVersion = android.os.Build.VERSION.SDK_INT;
	
	@Override
	//TODO - Check if permissions have been granted. If not, request
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(com.marlonjones.projectevo.R.layout.activity_home);
        toolbar = (Toolbar) findViewById(com.marlonjones.projectevo.R.id.tool_bar);
        toolbar.setTitle("Aperture Gallery");
        setSupportActionBar(toolbar);
		//Asks for permission. Works, sort of. Temporary Solution
        new AskPermission.Builder(this).setPermissions(Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE)
				.setCallback(this)
				.setErrorCallback(this)
				.request (GET_PERMISSIONS);

        //TODO: Consider changing UI to be better, and consider adding Drive/Dropbox support.
        mContext=ActivityHome.this;
		mBottomBar = BottomBar.attach(this, savedInstanceState);
        mBottomBar.noTabletGoodness();
		mBottomBar.setItems(R.menu.bottombar_menu);
		mBottomBar.setOnMenuTabClickListener(new OnMenuTabClickListener() {
			@Override
			public void onMenuTabSelected(@IdRes int menuItemId) {
				if (menuItemId == R.id.bottomBarItemOne) {
                    Fragment fragout1 = new GalleryFragment();
                    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                    ft.replace(R.id.fragment_container, fragout1);
                    ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                    ft.addToBackStack(null);
                    ft.commit();
				}
                if (menuItemId == R.id.bottomBarItemTwo) {
                    Fragment fragout2 = new CameraFragment();
                    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                    ft.replace(R.id.fragment_container, fragout2);
                    ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                    ft.addToBackStack(null);
                    ft.commit();
                }
                if (menuItemId == R.id.bottomBarItemThree) {
                    Fragment fragout3 = new VideoFragment();
                    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                    ft.replace(R.id.fragment_container, fragout3);
                    ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                    ft.addToBackStack(null);
                    ft.commit();
                }
			}
			@Override
			public void onMenuTabReSelected(@IdRes int menuItemId) {
				if (menuItemId == R.id.bottomBarItemOne) {
					// The user reselected item number one, scroll your content to top.
				}
			}
		});
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(com.marlonjones.projectevo.R.menu.main, menu);
		return super.onCreateOptionsMenu(menu);
	}
    /**Opens the Settings Activity, which holds the theme settings and other information**/
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == com.marlonjones.projectevo.R.id.action_settings) {
            Intent settingsIntent = new Intent(ActivityHome.this, SettingsActivity.class);
            startActivity(settingsIntent);
		}
        //TODO - Add in Google Cardboard SDK and activate this
		if (id ==R.id.cardboard_toggle){
			Toast.makeText(getApplicationContext(), "Coming Soon - Google Cardboard viewing mode",
					Toast.LENGTH_SHORT).show();
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onPermissionsGranted(int requestCode) {
	//Toast.makeText(this, "Permission Granted!", Toast.LENGTH_SHORT).show();
		//This will show every time. So this has been removed.
	}

	@Override
	public void onPermissionsDenied(int requestCode) {
		new MaterialDialog.Builder(this)
				.title(R.string.perm_title)
				.content(R.string.permission_needed)
				.positiveText(R.string.OK)
				.onPositive(new MaterialDialog.SingleButtonCallback() {
					@Override
					public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
						finish();
					}
				})
				.show();
	}

	@Override
	public void onShowRationalDialog(PermissionInterface permissionInterface, int requestCode) {
		//TODO - Add Material Dialog Explanation

	}

	@Override
	public void onShowSettings(PermissionInterface permissionInterface, int requestCode) {
		//TODO

	}
}
