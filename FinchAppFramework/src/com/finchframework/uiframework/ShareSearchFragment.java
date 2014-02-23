package com.finchframework.uiframework;

import com.mediaentities.readerframework.R;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.widget.ShareActionProvider;

public abstract class ShareSearchFragment extends Fragment implements OnMenuItemClickListener {

	public ShareSearchFragment() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// Tell the system we have an options menu
		setHasOptionsMenu(true);
	}
	
	// TODO refactor menu inflation and actions to the specific abstract Fragment subclass
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		
		inflater.inflate(R.menu.fullscreen_menu, menu);
		inflater.inflate(R.menu.search_menu, menu);
		inflater.inflate(R.menu.share_menu, menu);
		inflater.inflate(R.menu.show_list_menu, menu);
		
	    MenuItem item = menu.findItem(R.id.menu_share);
		ShareActionProvider shareAction = 
				(ShareActionProvider) item.getActionProvider();
		shareAction.setShareIntent(new Intent(Intent.ACTION_SEND)
			.setType("text/plain")
			.putExtra(Intent.EXTRA_TEXT, "http://zigurd.com"));
}
	
	@Override
	public boolean onMenuItemClick(MenuItem item) {
		return true;
	}
	
	@Override
    public boolean onOptionsItemSelected(MenuItem item) {
		int itemId = item.getItemId();
        switch (itemId) {
            case R.id.menu_share:
        		ShareActionProvider shareAction = 
				(ShareActionProvider) item.getActionProvider();
		shareAction.setShareIntent(new Intent(Intent.ACTION_SEND)
			.setType("text/plain")
			.putExtra(Intent.EXTRA_TEXT, "http://zigurd.com"));
            	return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


}
