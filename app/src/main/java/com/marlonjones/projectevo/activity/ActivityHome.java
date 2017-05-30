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
	private static final int GET_PERMISSIONS = 20;
	
	@Override
	//TODO - Check if permissions have been granted. If not, request
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(com.marlonjones.projectevo.R.layout.activity_home);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.tool_bar);
        toolbar.setTitle("Albums"); //This may be changed once the app's name is changed
        setSupportActionBar(toolbar);
		//Message at start of app!
		new MaterialDialog.Builder(this)
				.title(R.string.alpha_title)
				.content(R.string.alpha_text)
				.positiveText(R.string.OK)
				.show();
		//Asks for permission. Works, sort of. Temporary Solution
        new AskPermission.Builder(this).setPermissions(Manifest.permission.CAMERA,
                Manifest.permission.READ_EXTERNAL_STORAGE)
				.setCallback(this)
				.setErrorCallback(this)
				.request (GET_PERMISSIONS);

        //TODO: Consider changing UI to be better, and consider adding Drive/Dropbox support.
        //Fragments are named after explosives seen in COD: MW3
        mContext=ActivityHome.this;
        BottomBar BotLane = BottomBar.attach(this, savedInstanceState);
        BotLane.noTabletGoodness(); //This is due to not being deved for tablets. No need to.
		BotLane.setItems(R.menu.bottombar_menu);
		BotLane.setOnMenuTabClickListener(new OnMenuTabClickListener() {
            //Consider replacing with Google Bottom Bar from Android Design Library
			@Override
			public void onMenuTabSelected(@IdRes int menuItemId) {
				if (menuItemId == R.id.bottomBarItemOne) {
                    toolbar.setTitle("Albums");
                    Fragment semtexFrag = new GalleryFragment();
                    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                    ft.replace(R.id.fragment_container, semtexFrag);
                    ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                    ft.addToBackStack(null);
                    ft.commit();
				}
                if (menuItemId == R.id.bottomBarItemTwo) {
                    toolbar.setTitle("Camera");
                    Fragment claymoreFrag = new CameraFragment();
                    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                    ft.replace(R.id.fragment_container, claymoreFrag);
                    ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                    ft.addToBackStack(null);
                    ft.commit();
                }
                if (menuItemId == R.id.bottomBarItemThree) {
                    toolbar.setTitle("Videos");
                    Fragment moabFrag = new VideoFragment();
                    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                    ft.replace(R.id.fragment_container, moabFrag);
                    ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                    ft.addToBackStack(null);
                    ft.commit();
                }
			}
			@Override
			public void onMenuTabReSelected(@IdRes int menuItemId) {
				if (menuItemId == R.id.bottomBarItemOne) {
					// The user reselected item number one, scroll your content to top.
                    //TODO - Add in "Scroll to top" function for better UX.
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
		if (id == R.id.cam_toggle){
			Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
			startActivity(intent);
            //Temporary Concept - Change to open entire app instead. This will only show the
            //Selector of the photos and not the entire app
		}
		//TODO - Add Cardboard mode back in at some time
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onPermissionsGranted(int requestCode) {
	//Toast.makeText(this, "Permission Granted!", Toast.LENGTH_SHORT).show(); -- Bad UX Behavior
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
		//TODO - Add Material Dialog Explanation on why permissions are needed in detail.

	}

	@Override
	public void onShowSettings(PermissionInterface permissionInterface, int requestCode) {
		//TODO -  Add this in. If not, then remove it. This actually might not be needed :/

	}
}
