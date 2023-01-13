package com.example.changecalc.main;

import calculation.Calculator;
import calculation.Change;
import check.ArgumentChecker;
import message.Message;

/**お釣り計算システム*/
public class Main {

	/**
	 * コマンドライン引数が正常であればシステムを起動する。
	 * @param args　[0]:投入金額, [1]:購入金額
	 */
	public static void main(String[] args) {
		try {
			if (ArgumentChecker.checkArgument(args)) { //1.引数をチェック
				startSystem(ArgumentChecker.getInputNum()); //2.問題なければ、お釣り計算システムを起動
			}
		} catch (NumberFormatException e) { //数字以外の入力があった場合
			Message.show(Message.ERRORMESSAGE_REQIRE_SEINOSEISU);
		} catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
			Message.show(Message.ERRORMESSAGE_NULLPO);
		}
	}

	/**
	 * 必要なオブジェクトを初期化し、お釣り計算システムを実行する
	 * @param ArgumentCheckerでコマンドライン引数が正常なのを確認をしたうえで使用すること。
	 * */
	private static void startSystem(int[] inputNum) {

		Change change = new Change(); //3.お釣り管理クラスをインスタンス化

		int[] availableCoin = { 500, 100, 50, 10 }; //4.硬貨の種類を設定

		Calculator.startCalc(inputNum, change, availableCoin); //5.お釣り計算の実行

		change.payout(); //6.お釣りの払い出し
	}
}