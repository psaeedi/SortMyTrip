/**
 * 
 */
package com.example.sortmytrip;

import java.util.Arrays;
import java.util.List;

import com.example.sortmytrip.common.BoardingCard;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * @author psaeedi
 * 
 */
public class BoardingCardAdapter extends BaseAdapter {

	private Context context;
	private List<BoardingCard> boardingCards;

	public BoardingCardAdapter(Context context, List<BoardingCard> boardingCards) {
		this.context = context;
		this.boardingCards = boardingCards;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.widget.Adapter#getCount()
	 */
	@Override
	public int getCount() {
		return boardingCards.size();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.widget.Adapter#getItem(int)
	 */
	@Override
	public Object getItem(int position) {
		return boardingCards.get(position);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.widget.Adapter#getItemId(int)
	 */
	@Override
	public long getItemId(int position) {
		return 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.widget.Adapter#getView(int, android.view.View,
	 * android.view.ViewGroup)
	 */
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		final BoardingCard item = (BoardingCard) getItem(position);

		View rowView;
		if (convertView == null) {
			LayoutInflater li = LayoutInflater.from(context);
			rowView = li.inflate(R.layout.bording_row, null);
		} else {
			// Reuse
			rowView = convertView;
		}

		// Fill the view
		try {
			TextView transport = (TextView) rowView
					.findViewById(R.id.transport);
			transport.setText(item.getTransport());
			TextView toFrom = (TextView) rowView.findViewById(R.id.to_from);
			toFrom.setText(item.getFrom() + " > " + item.getTo());
			TextView seat = (TextView) rowView.findViewById(R.id.seat_info);
			seat.setText("Seat: " + item.getSeatNum());
			TextView extra = (TextView) rowView.findViewById(R.id.extra_info);
			StringBuilder builder = new StringBuilder();
			for (String s : item.getExtra()) {
				builder.append(s + "\n");
			}

			extra.setText(builder.toString());

		} catch (RuntimeException ex) {
			Log.e("CocktailListAdapter", item + " " + ex.getMessage(), ex);
		}

		return rowView;
	}

}
