package com.marlonjones.projectevo.activity;

import com.afollestad.materialdialogs.MaterialDialog;
import com.marlonjones.gyrochecklib.GyroCheck;
import com.marlonjones.projectevo.R;
import com.marlonjones.projectevo.adapter.SlideMenuAdapter;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnMenuTabClickListener;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class ActivityHome extends AppCompatActivity{

	private Context mContext;
	private Toolbar toolbar;
	private FragmentManager fragmentManager = null;
    private FragmentTransaction fragmentTransaction = null;
	private Fragment currentFragment=null;
	private BottomBar mBottomBar;
    private static final int PERMISSION_REQUEST_CODE = 1;
	private SlideMenuAdapter mSlideMenuAdapter;
	private int currentPosition=0;
    final private int REQUEST_CODE_ASK_PERMISSIONS = 123;
    int currentapiVersion = android.os.Build.VERSION.SDK_INT;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(com.marlonjones.projectevo.R.layout.activity_home);
        toolbar = (Toolbar) findViewById(com.marlonjones.projectevo.R.id.tool_bar);
        toolbar.setTitle("Aperture Gallery");
        setSupportActionBar(toolbar);

        /**Permission Check Start**/

        /**Permission Check End**/

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
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == com.marlonjones.projectevo.R.id.action_settings) {
			Toast.makeText(getApplicationContext(), "Settings Coming Soon - Personalization, Security, and Backup",
					Toast.LENGTH_SHORT).show();
		}
		if (id==com.marlonjones.projectevo.R.id.action_about){
            new MaterialDialog.Builder(this)
				   .title(R.string.about)
				   .content(R.string.about_body)
				   .positiveText(R.string.OK)
				   .show();

        }
        if (id== R.id.action_information){
             new MaterialDialog.Builder(this)
					.title(R.string.alphatitle)
					.content(R.string.alpha_text)
					.positiveText(R.string.OK)
					.show();
        }
		if (id ==R.id.cardboard_toggle){
			Toast.makeText(getApplicationContext(), "Coming Soon - Google Cardboard viewing mode",
					Toast.LENGTH_SHORT).show();
		}
		return super.onOptionsItemSelected(item);
	}
}
