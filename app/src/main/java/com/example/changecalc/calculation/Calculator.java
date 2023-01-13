package com.example.changecalc.calculation;

import message.Message;

/**投入金額と購入金額からお釣りを計算するクラス */
public class Calculator {

	/**
	 * お釣り計算を開始する。
	 * @param arg：投入金額　購入金額
	 * @param change：お釣り管理クラス
	 * @param coinUnits：利用可能な硬貨
	 */
	public static void startCalc(int[] inputNum, Change change, int... coinUnits) 
			throws NullPointerException, ArrayIndexOutOfBoundsException {
		setCoin(calcDifference(inputNum), change, coinUnits);
	}

	/**
	 * 投入金額と購入金額の差分を計算し、その値を返す。
	 * @return：diff：投入金額と購入金額の差分
	 */
	private static int calcDifference(int[] inputNum) throws ArrayIndexOutOfBoundsException{
		int diff = inputNum[0] - inputNum[1];
		if (diff == 0) {
			Message.show(Message.OTURI_NASHI);
		} else if (diff < 0) {
			Message.show(Message.OTURI_HUSOKU);
		}
		return diff;
	}

	//適切に硬貨を振り分ける。硬貨はChange()クラスで管理する。
	private static void setCoin(int diff, Change change, int... coinUnits) {
		for (int coin : coinUnits) {
			if (diff < coin) {
				continue;
			} else {
				change.addCoin(coin, diff / coin);
				diff %= coin;
			}
		}
	}
}