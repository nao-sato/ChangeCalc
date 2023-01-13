/**
 *
 */
package com.example.changecalc.test;

/**int型、String型の配列を生成する（テスト用)*/
public class ArrayBilder {
	
	//int型のテスト配列
	private int[] intArray;
	
	//String型のテスト配列
	private String[] strArray;

	/** 
	 * 引数で指定した数に対応した配列を生成
	 * @param　1つ以上の引数
	 * @return　引数に対応した配列
	 */
	public String[] get(String... args) {
		strArray = new String[args.length];
		for (int i = 0; i < args.length; i++) {
			strArray[i] = args[i];
		}
		return strArray;
	}
	
	/** 
	 * 引数で指定した数に対応した配列を生成
	 * @param 1つ以上の引数
	 * @return 引数に対応した配列
	 */
	public int[] get(int... inputNum) {
		intArray = new int[inputNum.length];
		for (int i = 0; i < inputNum.length; i++) {
			intArray[i] = inputNum[i];
		}
		return intArray;
	}
	
	/**
	 * 空の配列を生成（コマンドライン引数の指定なし用）
	 * @return 空の配列
	 */
	public int[] getEmptyInt() {
		intArray = new int[0];
		return intArray;
	}
	
	/**
	 * 空の配列を生成（コマンドライン引数の指定なし用）
	 * @return 空の配列
	 */
	public String[] getEmptyStr() {
		strArray = new String[0];
		return strArray;
	}
}