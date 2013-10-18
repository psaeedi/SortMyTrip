/**
 * 
 */
package com.example.sortmytrip.common;

import java.io.Serializable;

/**
 * Model object representing boarding card.
 * 
 * @author psaeedi
 * 
 */
public class BoardingCard implements Serializable {

	private String from;
	private String to;
	private String transport;
	private String seatNum;
	private String[] extra;

	private BoardingCard() {
		// used by Gson for de-serilization
	}
	
	public BoardingCard(String from, String to, String transport,
			String seatNum, String[] extra) {
		this.from = from;
		this.to = to;
		this.transport = transport;
		this.seatNum = seatNum;
		this.extra = extra;
	}

	public String getFrom() {
		return from;
	}

	public String getTo() {
		return to;
	}

	public String getTransport() {
		return transport;
	}

	public String getSeatNum() {
		return seatNum;
	}

	public String[] getExtra() {
		return extra;
	}

}
