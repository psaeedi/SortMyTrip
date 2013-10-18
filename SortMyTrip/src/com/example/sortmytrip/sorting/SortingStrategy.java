/**
 * 
 */
package com.example.sortmytrip.sorting;

import java.util.ArrayList;
import java.util.List;

import com.example.sortmytrip.common.BoardingCard;

/**
 * @author psaeedi
 * 
 */
public class SortingStrategy {

	private BoardingCard boardingCardFirst;
	private List<BoardingCard> ordered;

	/**
	 * 
	 */
	public SortingStrategy() {
		// TODO Auto-generated constructor stub
	}

	public List<BoardingCard> sort(List<BoardingCard> unordered) {
		ordered = new ArrayList<BoardingCard>();
		unordered = new ArrayList<BoardingCard>(unordered);

		BoardingCard next = findFirst(unordered);

		while (next != null) {
			ordered.add(next);
			unordered.remove(next);
			next = followFirst(unordered, next);
		}

		return ordered;
	}

	public BoardingCard findFirst(List<BoardingCard> listOfBoardingCards) {
		BoardingCard first = null;
		for (int i = 0; i < listOfBoardingCards.size(); i++) {
			first = listOfBoardingCards.get(i);
			boolean isFirst = true;
			for (int j = 0; j < listOfBoardingCards.size(); j++) {
				BoardingCard second = listOfBoardingCards.get(j);
				if (i == j) {
					continue;
				}
				if (first.getFrom().equals(second.getTo())) {
					isFirst = false;
					break;
				}
			}
			if (isFirst) {
				return first;
			}
		}
		// listOfBoardingCards
		return first;
	}

	public BoardingCard followFirst(List<BoardingCard> listOfBoardingCards,
			BoardingCard first) {
		for (int i = 0; i < listOfBoardingCards.size(); i++) {
			BoardingCard current = listOfBoardingCards.get(i);
			if (first.getTo().equals(current.getFrom())) {
				return current;
			}
		}
		return null;
	}

}
