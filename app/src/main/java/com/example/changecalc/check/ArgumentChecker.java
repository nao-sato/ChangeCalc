package com.example.changecalc.check;

import message.Message;

/** 
 * 入力された引数に不備がないか確認するクラス
 * 不正な入力パターンが複数ある場合でも、エラーメッセージは1つ。
 */
public class ArgumentChecker {

	private static int[] inputNum = new int[2];

	/**
	 * コマンドライン引数の要素数とその値を確認する
	 * @return　不備なし → true | 不備あり → false
	 */
	public static boolean checkArgument(String[] arg) throws NullPointerException {
		return checkArray(arg) && checkValue(arg) ? true : false;
	}

	/**
	 * 要素数を確認する。
	 * @return　true（要素数が0、または2以外であればfalse）
	 */
	private static Boolean checkArray(String[] arg) {

		boolean flag = true;

		if (arg.length == 0) {
			Message.show(Message.ERRORMESSAGE_NYURYOKUNASHI);
			flag = false;
		} else if (arg.length != 2) {
			Message.show(Message.ERRORMESSAGE_NYURYOKUHUSEI);
			flag = false;
		}
		return flag;
	}

	/**
	 * 値を確認する。
	 * @return　true（負数または、1円単位の入力があればfalse）
	 */
	private static Boolean checkValue(String[] arg) throws NumberFormatException {

		boolean flag = true;

		inputNum[0] = Integer.parseInt(arg[0]);
		inputNum[1] = Integer.parseInt(arg[1]);

		for (int value : inputNum) {
			if (value <= 0) {
				Message.show(Message.ERRORMESSAGE_NYURYOKUHUSEI);
				flag = false;
				break;

			} else if (value % 10 != 0) {
				Message.show(Message.ERRORMESSAGE_NYURYOKU$1);
				flag = false;
				break;
			}
		}
		return flag;
	}

	/**@return　inputNum*/
	public static int[] getInputNum() {
		return inputNum;
	}
}