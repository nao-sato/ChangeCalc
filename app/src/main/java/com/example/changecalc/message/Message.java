package com.example.changecalc.message;

/**出力メッセージを管理するクラス*/
public class Message {

	public static final int OTURI_NASHI = 0;
	public static final int OTURI_HUSOKU = 1;
	public static final int ERRORMESSAGE_NYURYOKUNASHI = 2;
	public static final int ERRORMESSAGE_NYURYOKUHUSEI = 3;
	public static final int ERRORMESSAGE_NYURYOKU$1 = 4;
	public static final int ERRORMESSAGE_REQIRE_SEINOSEISU = 5;
	public static final int ERRORMESSAGE_NULLPO = 6;
	public static final int TEST_$500$100$50$10 = 7;
	public static final int TEST_$50$10 = 8;
	public static final int TEST_$100$10 = 9;
	
	
	private static String[] messageList = {
			/*0*/ "お釣りはありません。", 
			/*1*/ "投入金額が足りません。",  
			/*2*/ "値を入力してください。", 
			/*3*/ "不正な入力!!",   
			/*4*/ "１円単位での入力はできません。", 
			/*5*/ "正の整数を入力してください。",
			/*6*/ "システム上のエラーが発生。",  
			/*7*/ "[500円 ×1] [100円 ×1] [50円 ×1] [10円 ×1] ",
			/*8*/ "[50円 ×1] [10円 ×1] ",
			/*9*/ "[100円 ×1] [10円 ×1] ",
	};

	/**
	 * 引数で指定された番号に対応したメッセージを出力する。 
	 * @param messageNum：メッセージリストの添字に対応したフィールド変数を選択
	 */
	public static void show(int messageNum) {
		System.out.print(messageList[messageNum]);
	}
	
	/** 
	 * 各メッセージの値をstringで返す。 
	 * @param messageNum：メッセージリストの添字に対応したフィールド変数を選択
	 * @return メッセージリストの値
	 */
	public static String get(int messageNum) {
		return messageList[messageNum];
	}
}