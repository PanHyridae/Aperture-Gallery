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
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

public class ActivityHome extends AppCompatActivity implements SlideMenuAdapterInterface{

	private Context mContext;
	private Toolbar toolbar;
	private DrawerLayout Drawer;
	private ActionBarDrawerToggle mDrawerToggle;
	private FragmentManager fragmentManager = null;
    private FragmentTransaction fragmentTransaction = null;
	private Fragment currentFragment=null;
	private BottomBar mBottomBar;
	private ListView slidingList;
	private SlideMenuAdapter mSlideMenuAdapter;
	private int currentPosition=0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(com.marlonjones.projectevo.R.layout.activity_home);

		 mContext=ActivityHome.this;
		 initializeActionBar();
		 initialCalling();

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
	public void onBackPressed() {
		if(Drawer.isDrawerOpen(GravityCompat.START)){
			Drawer.closeDrawer(GravityCompat.START);
	    }else{
	        super.onBackPressed();
	    }
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
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	@Override
	public void slideRowClickEvent(int postion) {
		if (currentPosition== postion) {
			closeDrware();
			return;
		}
		currentPosition= postion;
		getFragment(postion);
		attachedFragment();
	}
	
	private void initializeActionBar() {
		toolbar = (Toolbar) findViewById(com.marlonjones.projectevo.R.id.tool_bar);
		toolbar.setTitle("");
		setSupportActionBar(toolbar);

		slidingList=(ListView)findViewById(com.marlonjones.projectevo.R.id.sliding_listView);
		mSlideMenuAdapter=new SlideMenuAdapter(mContext, getSlideList());
		mSlideMenuAdapter.setSlidemenuadapterinterface(this);
		slidingList.setAdapter(mSlideMenuAdapter);

		Drawer = (DrawerLayout) findViewById(com.marlonjones.projectevo.R.id.DrawerLayout);
		mDrawerToggle = new ActionBarDrawerToggle(this, Drawer, toolbar,
				com.marlonjones.projectevo.R.string.openDrawer, com.marlonjones.projectevo.R.string.closeDrawer) {

			@Override
			public void onDrawerOpened(View drawerView) {
				super.onDrawerOpened(drawerView);

			}

			@Override
			public void onDrawerClosed(View drawerView) {
				super.onDrawerClosed(drawerView);
			}

		};
		Drawer.setDrawerListener(mDrawerToggle);
		mDrawerToggle.syncState();
		

	}
	
	private void closeDrware(){
		if(Drawer.isDrawerOpen(GravityCompat.START)){
			Drawer.closeDrawer(GravityCompat.START);
	    }
	}
	
	private void initialCalling(){
		fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();

		getFragment(0);
		attachedFragment();
	}



	private void attachedFragment(){
		try {
			if (currentFragment != null) {
				if (fragmentTransaction.isEmpty()) {
					fragmentTransaction.add(com.marlonjones.projectevo.R.id.fragment_container, currentFragment,"" + currentFragment.toString());
					fragmentTransaction.commit();
					toolbar.setTitle(title[currentPosition]);
				}else {
					fragmentTransaction = fragmentManager.beginTransaction();
					fragmentTransaction.replace(com.marlonjones.projectevo.R.id.fragment_container, currentFragment,"" + currentFragment.toString());
					fragmentTransaction.commit();
					toolbar.setTitle(title[currentPosition]);
				}

			}
			closeDrware();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	private void getFragment(int postion){
		switch (postion) {
		case 0:
			currentFragment = new GalleryFragment();
			break;
		case 1:
			currentFragment = new CameraFragment();
			break;
		case 2:
			currentFragment = new VideoFragment();
			break;

		default:
			break;
		}
	}


	/**
	 * Change to Bottom Bars when possible.
	 */
	private String[] title={"All Images","Camera","Video"};
	private int[] titleLogo={com.marlonjones.projectevo.R.drawable.selector_allpic, com.marlonjones.projectevo.R.drawable.selector_camera, com.marlonjones.projectevo.R.drawable.selector_video};
	private ArrayList<SlideData> getSlideList(){
		ArrayList<SlideData> arrayList=new ArrayList<SlideData>();
		for (int i = 0; i < title.length; i++) {
			SlideData mSlideData=new SlideData();
			mSlideData.setIcon(titleLogo[i]);
			mSlideData.setName(title[i]);
			mSlideData.setState((i==0)?1:0);
			arrayList.add(mSlideData);
		}
		return arrayList;
	}
}
