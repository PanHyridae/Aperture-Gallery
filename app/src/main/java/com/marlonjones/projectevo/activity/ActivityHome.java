package com.marlonjones.projectevo.activity;

import java.util.ArrayList;

import com.marlonjones.projectevo.R;
import com.marlonjones.projectevo.adapter.SlideMenuAdapter;
import com.marlonjones.projectevo.adapter.SlideMenuAdapter.SlideMenuAdapterInterface;
import com.marlonjones.projectevo.model.SlideData;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnMenuTabClickListener;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
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
	private SlideMenuAdapter mSlideMenuAdapter;
	private int currentPosition=0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(com.marlonjones.projectevo.R.layout.activity_home);
        toolbar = (Toolbar) findViewById(com.marlonjones.projectevo.R.id.tool_bar);
        setSupportActionBar(toolbar);

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
			Toast.makeText(getApplicationContext(), "Not Yet Developed",
					Toast.LENGTH_SHORT).show();
		}
		if (id==com.marlonjones.projectevo.R.id.action_about){
            Toast.makeText(getApplicationContext(), "Not Yet Developed",
                    Toast.LENGTH_SHORT).show();
        }
        if (id== R.id.action_information){
            Toast.makeText(getApplicationContext(), "Not Yet Developed",
                    Toast.LENGTH_SHORT).show();
        }
		return super.onOptionsItemSelected(item);
	}
}
