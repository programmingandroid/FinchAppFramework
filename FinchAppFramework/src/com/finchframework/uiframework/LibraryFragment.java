package com.finchframework.uiframework;

import com.mediaentities.readerframework.R;

import android.app.Activity;
import android.app.Fragment;
import android.app.LoaderManager;
import android.content.Loader;
import android.content.res.Configuration;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;



/**
 * @author zigurd
 * 
 * Present the library of publications or issues from which the user selects which ones to read
 *
 */
public class LibraryFragment extends Fragment implements
	LoaderManager.LoaderCallbacks<Cursor>, OnMenuItemClickListener {

	// Turn logging on or off
	private static final boolean L = true;
	
	// String for logging the class name
	private final String CLASSNAME = getClass().getSimpleName();
	
	// Tag my loader with this ID
	public static final int LOADER_ID = 42;
	
	// Labels for members saved as state
	private final String STATE_LABEL_NAME = "tablename";
	
	//The current table's class name
	private String tableName;


	public void onAttach(Activity activity) {
		super.onAttach(activity);
		
		// Notification that the fragment is associated with an Activity
		if (L)
			Log.i(CLASSNAME, "onAttach " + activity.getClass().getSimpleName());
	}

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// Tell the system we have an options menu
		//setHasOptionsMenu(true);
		
		// Don't show the list until it has some content
		// setListShown(false);
		
		// doLoaderCreation(savedInstanceState);
		
		// Notification that
		if (L)
			Log.i(CLASSNAME, "onCreate");
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		final ScrollView listLayout = (ScrollView) inflater.inflate(
				R.layout.list_frag_list, container, false);
		if (L)
			Log.i(CLASSNAME, "onCreateView");
		
		return listLayout;
	}
	
	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		
		outState.putString(STATE_LABEL_NAME, tableName);
	}

	public void onStart() {
		super.onStart();
		if (L)
			Log.i(CLASSNAME, "onStart");
	}

	public void onresume() {
		super.onResume();
		if (L)
			Log.i(CLASSNAME, "onResume");
	}

	public void onPause() {
		super.onPause();
		if (L)
			Log.i(CLASSNAME, "onPause");
	}

	public void onStop() {
		super.onStop();
		if (L)
			Log.i(CLASSNAME, "onStop");
	}

	public void onDestroyView() {
		super.onDestroyView();
		if (L)
			Log.i(CLASSNAME, "onDestroyView");
	}

	public void onDestroy() {
		super.onDestroy();
		if (L)
			Log.i(CLASSNAME, "onDestroy");
	}

	public void onDetach() {
		super.onDetach();
		if (L)
			Log.i(CLASSNAME, "onDetach");
	}

	// ////////////////////////////////////////////////////////////////////////////
	// Minor lifecycle methods
	// ////////////////////////////////////////////////////////////////////////////

	public void onActivityCreated() {
		// Notification that the containing activiy and its View hierarchy exist
		if (L)
			Log.i(CLASSNAME, "onActivityCreated");
	}

	// /////////////////////////////////////////////////////////////////////////////
	// Overrides of the implementations of ComponentCallbacks methods in Fragment
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
	// ListFragment click handling
	// /////////////////////////////////////////////////////////////////////////////

//	public void onListItemClick(ListView l, View v, int position, long id) {
//		Cursor c = ((CursorAdapter) getListView().getAdapter()).getCursor();
//	    String item = buildItemInfo(c, position);
//	    String tableInfo = buildDatabaseInfo(c);
//	    Bundle data = ((MainActivity) getActivity()).buildDataBundle(item, tableInfo);
//	    ((TabbedActivity) getActivity()).loadTabFragments(data);
//	}

	// ////////////////////////////////////////////////////////////////////////////
	// Implementation of LoaderCallbacks
	// ////////////////////////////////////////////////////////////////////////////

	// Create the loader, passing in the query
	public Loader<Cursor> onCreateLoader(int id, Bundle args) {
//		return new CursorLoader(this.getActivity(), uriForTable(tableName),
//	            null, null, null, null);
		return null;
	}
	
	// Get results
	public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
//		((SimpleCursorAdapter) getListAdapter()).swapCursor(cursor);
	}
	
	// Reset
	public void onLoaderReset(Loader<Cursor> loader) {
//		((SimpleCursorAdapter) getListAdapter()).swapCursor(null);
	    
	}
	
	// ////////////////////////////////////////////////////////////////////////////
	// App-specific code
	// ////////////////////////////////////////////////////////////////////////////
	
	private final static String NL = System.getProperty("line.separator");
	
	
	/**
	 * Called from onCreate. restore state if available
	 * 
	 * @param savedInstanceState
	 */
	private void doLoaderCreation(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);

	}
	
	/**
	 * Extracts, labels, and formats all the information in
	 * all the columns in a row.
	 * 
	 * @param c The cursor
	 * @param position The position in the cursor
	 * @return The formatted data from the row
	 */
	private String buildItemInfo(Cursor c, int position) {
		
		int i;
		int columns = c.getColumnCount();
		String info = "";
		
		c.moveToPosition(position);
		String names[] = c.getColumnNames();

		for (i = 0; i < columns; i++) {
			info += names[i] + ": ";
			try {
				info += c.getString(i);
			} catch (Exception e) {
				// Fail silently
			}
			info += NL;
		}
		
		return info;
	}	
	
	private String buildDatabaseInfo (Cursor c) {
		String info = "";
		
		info += getString(R.string.column_count_label) + c.getColumnCount() + NL;
		info += getString(R.string.row_count_label) + c.getCount() + NL;
				
		return info;
		
	}
	
	///////////////////////////////////////////////////////////////////////////////
	// Methods for transferring data between Fragments
	///////////////////////////////////////////////////////////////////////////////
	
	/**
	 * Build a Bundle that holds the database and item information
	 * 
	 * @param item Information about the selected row
	 * @param dbInfo Information about the database
	 * @return the Bundle containing the above information
	 */
	public Bundle buildDataBundle(String item, String dbInfo) {
		Bundle data = new Bundle();
		
		data.putString(getDataLabel(R.id.item_frag), item);
		data.putString(getDataLabel(R.id.detail_frag), dbInfo);
		return data;
		
	}
	
	public String getDataLabel(int id) {
		Fragment frag = getFragmentManager().findFragmentById(id);
		String label = ((TabbedActivity.SetData)frag).getDataLabel();
		return label;
	}

	
	// /////////////////////////////////////////////////////////////////////////////
	// Menu handling code, including implementation of onMenuItemClickListener
	// /////////////////////////////////////////////////////////////////////////////

	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		// buildTableMenu(menu);
//		super.onCreateOptionsMenu(menu, inflater);
	}
	

	@Override
	public boolean onMenuItemClick(MenuItem item) {
//		openNewTableByName((String) item.getTitle());
		return true;
	}
	
	public void showLibrary(boolean show) {
		android.app.FragmentTransaction ft = getFragmentManager().beginTransaction();
        if (isHidden() && true == show) {
            ft.show(this);
        } else if (false == show) {
            ft.hide(this);
        }
        ft.commit();
	}

}
