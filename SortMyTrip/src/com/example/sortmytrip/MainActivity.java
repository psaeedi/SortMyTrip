package com.example.sortmytrip;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.example.sortmytrip.common.BoardingCard;
import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import android.os.Bundle;
import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

public class MainActivity extends Activity {

	private final static String BOARDING_CARDS_JSON = "unsorted_trip.json";
	private List<BoardingCard> boardingCards = new ArrayList<BoardingCard>();
	public final static String BOARDINGCARDS = "boarding_cards";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Gson gson = new Gson();
		try {
			boardingCards.addAll(
					Arrays.asList(gson.fromJson(new InputStreamReader(getAssets()
							.open(BOARDING_CARDS_JSON)), BoardingCard[].class)));
		} catch (JsonSyntaxException e) {
			Log.e(MainActivity.class.getName(), "" + e.getMessage(), e);
			finish();
		} catch (JsonIOException e) {
			Log.e(MainActivity.class.getName(), "" + e.getMessage(), e);
			finish();
		} catch (IOException e) {
			Log.e(MainActivity.class.getName(), "" + e.getMessage(), e);
			finish();
		}
		
		ListView list = (ListView) findViewById(R.id.list_view_unsorted);
		list.setAdapter(new BoardingCardAdapter(this, boardingCards));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_sort: {
                Intent intent = new Intent(this, SortingActivity.class);
                intent.putExtra(BOARDINGCARDS, (Serializable)boardingCards);
                startActivity(intent);
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }
	

}
