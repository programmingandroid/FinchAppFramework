package com.finchframework.uiframework;

import com.mediaentities.readerframework.R;

import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnLayoutChangeListener;
import android.view.ViewGroup;


/**
 * @author zigurd
 * 
 *         Fragment for display of article content. This is the fragment that
 *         goes full-screen when the user wants to see only content.
 * 
 */
public class ArticleContentFragment extends ShareSearchFragment implements TabListener,
		TabbedActivity.SetData {

	// String for logging the class name
	private final String CLASSNAME = getClass().getSimpleName();

	// Turn logging on or off
	private final boolean L = true;


	private ViewPager pager;

	public void onAttach(Activity activity) {
		super.onAttach(activity);

		// Log that the fragment is associated with an Activity
		if (L)
			Log.i(CLASSNAME, "onAttach " + activity.getClass().getSimpleName());
	}

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// Log that we got the onCreate callback
		Log.i(CLASSNAME, "onCreate");
	}

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		// Inflate the layout for this fragment
		pager = (ViewPager)inflater.inflate(R.layout.article_content,
				container, false);
		setPageAdapter();
		pager.addOnLayoutChangeListener(new OnLayoutChangeListener () {

			@Override
			public void onLayoutChange(View v, int left, int top, int right,
					int bottom, int oldLeft, int oldTop, int oldRight,
					int oldBottom) {
				
				if (oldRight - oldLeft != right - left || oldBottom - oldTop != bottom - top) {
					// Reformat content
				}
			}});
		
		// Log that we processed onCreateView
		if (L)
			Log.i(CLASSNAME, "onCreateView");
		return pager;

	}

	public void setPageAdapter() {
		// Set adapter for paging between pages
	}

	public void onStart() {
		super.onStart();
		Log.i(CLASSNAME, "onStart");
	}

	public void onresume() {
		super.onResume();
		Log.i(CLASSNAME, "onResume");
	}

	public void onPause() {
		super.onPause();
		Log.i(CLASSNAME, "onPause");
	}

	public void onStop() {
		super.onStop();
		Log.i(CLASSNAME, "onStop");
	}

	public void onDestroyView() {
		super.onDestroyView();
		Log.i(CLASSNAME, "onDestroyView");
		pager.destroyDrawingCache();
	}

	public void onDestroy() {
		super.onDestroy();
		Log.i(CLASSNAME, "onDestroy");
	}

	public void onDetach() {
		super.onDetach();
		Log.i(CLASSNAME, "onDetach");
	}

	// ////////////////////////////////////////////////////////////////////////////
	// Minor lifecycle methods
	// ////////////////////////////////////////////////////////////////////////////

	public void onActivityCreated() {
		
		// Log that the containing activiy and its View hierarchy exist
		Log.i(CLASSNAME, "onActivityCreated");
	}

	// /////////////////////////////////////////////////////////////////////////////
	// Overrides of the implementations ComponentCallbacks methods in Fragment
	// /////////////////////////////////////////////////////////////////////////////

	@Override
	public void onConfigurationChanged(Configuration newConfiguration) {
		super.onConfigurationChanged(newConfiguration);

		// This won't happen unless we declare changes we handle in the manifest
		if (L)
			Log.i(CLASSNAME, "onConfigurationChanged");
	}

	@Override
	public void onLowMemory() {
		
		// No guarantee this is called before or after other callbacks
		if (L)
			Log.i(CLASSNAME, "onLowMemory");
	}
	

	// /////////////////////////////////////////////////////////////////////////////
	// Implementation of SetData
	// /////////////////////////////////////////////////////////////////////////////

	// Label for sending data to this fragment in the data Bundle
	private static String CONTENT_LABEL;

	@Override
	public void setData(Bundle data) {
		
		/* A placeholder implementation of the setData that looks for some 
		 * text in the bundle to display in a TextView
		 * 
		 * TODO: Make this load the page layout that demonstrates, flow, etc.
		 */
	}

	@Override
	public String getDataLabel() {
		
		/* Retrieve the label of the bundle we got our data from,
		 * initialize if needed
		 */
		if (null == CONTENT_LABEL) {
			CONTENT_LABEL = getString(R.string.content_bundle_label);
		}
		return CONTENT_LABEL;
	}

	
	// /////////////////////////////////////////////////////////////////////////////
	// Implementation of TabListener
	// /////////////////////////////////////////////////////////////////////////////

	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
		// Nothing to do

	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		
		// Add "show" to the transition for this Fragment
		ft.show(this);

	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		
		// Add "hide" to the transition for this Fragment
		ft.hide(this);

	}
}
