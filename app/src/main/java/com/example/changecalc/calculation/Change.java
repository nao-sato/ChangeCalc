package com.example.changecalc.calculation;

import java.util.ArrayList;

/**計算されたお釣りを管理するクラス*/
public class Change {

	//硬貨（Coin()）を管理するリスト
	private ArrayList<Coin> changeArray = new ArrayList<>();

	//硬貨をインスタンス化し、配列に追加する。
	public void addCoin(int unitUnit, int stockNum) {
		changeArray.add(new Coin(unitUnit, stockNum));
	}

	//管理している硬貨を全て出力する。
	public void payout() {
		changeArray.forEach(System.out::print);
	}

	/**
	 * 管理しているお釣りをString型で返す。
	 * @return changeArrayの内容(String型)
	 */
	public String getChangeString() {
		StringBuilder strChange = new StringBuilder();
		for (Coin c : changeArray) {
			strChange.append(c.toString());
		}
		return strChange.toString();
	}

	/**
	 * 管理しているお釣りのリストを返す。
	 * @return 硬貨（Coin()）を管理するリスト
	 */
	public ArrayList<Coin> getChangeArray() {
		return changeArray;
	}

	//changeArrayの内容をリセットさせる
	public void clearArray() {
		changeArray.clear();
	}
}