package com.example.sortmytrip;

import java.io.Serializable;
import java.util.List;

import com.example.sortmytrip.common.BoardingCard;
import com.example.sortmytrip.sorting.SortingStrategy;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v4.app.NavUtils;
import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;

public class SortingActivity extends Activity {
	
	protected List<BoardingCard> boardingCards;
	
	private AsyncTask<List<BoardingCard>, Void, List<BoardingCard>> sorter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sorting);
		// Show the Up button in the action bar.
		setupActionBar();
		if (sorter == null) {
			boardingCards = (List<BoardingCard>) getIntent().getSerializableExtra(MainActivity.BOARDINGCARDS);
			sorter = new AsyncTask<List<BoardingCard>, Void, List<BoardingCard>>() {@Override
				
				protected List<BoardingCard> doInBackground(
						List<BoardingCard>... params) {
					SortingStrategy strategy = new SortingStrategy();
					return strategy.sort(params[0]);
				}
				@Override
					protected void onCancelled() {
						// TODO Auto-generated method stub
						super.onCancelled();
						sorter = null;
					}
				
				@Override
					protected void onPostExecute(List<BoardingCard> result) {
						// TODO Auto-generated method stub
						super.onPostExecute(result);
						Intent intent = new Intent(SortingActivity.this, SortedTripActivity.class);
		                intent.putExtra(MainActivity.BOARDINGCARDS, (Serializable)boardingCards);
		                startActivity(intent);
						sorter = null;
					}
			};
          sorter.execute(boardingCards);
		}
				
		ProgressDialog.show(this, "Sorting ...", "Please wait!");
	}
	


	/**
	 * Set up the {@link android.app.ActionBar}, if the API is available.
	 */
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	private void setupActionBar() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			getActionBar().setDisplayHomeAsUpEnabled(true);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.sorting, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
