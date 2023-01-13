/**
 * 
 */
package com.example.changecalc.check;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import message.Message;
import test.ArrayBilder;
import test.OutputSnatcher;

class ArgumentCheckerTest {

	//int型やString型の配列を生成するテスト用のクラス
	private ArrayBilder testArray = new ArrayBilder();

	//出力内容をコンソールから奪うテスト用のクラス
	private OutputSnatcher snatcher = OutputSnatcher.getInstance();
	
	@Test
	void test() {

		//テスト用にインスタンス化
		ArgumentChecker argChecker = new ArgumentChecker();
		/**
		 * コンソールからOutputSnatcherクラスへ出力先を変更
		 * 以降、snatcher.getOutput()で出力内容を取得できる。
		 * 但し、snatcher.clearOutput()をしないと、前回の出力内容が残ったままになる。
		 */
		snatcher.snatch();

		//▼引数の要素数

		//1	inputNumがNull
		assertThrowsExactly(NullPointerException.class, () -> ArgumentChecker.checkArgument(null));

		//2	要素数：0
		assertEquals(false, ArgumentChecker.checkArgument(testArray.getEmptyStr()));
		assertThat((snatcher.getOutput()), is(Message.get(Message.ERRORMESSAGE_NYURYOKUNASHI)));
		snatcher.clearOutput();

		//3	要素数：2つ以外
		assertEquals(false, ArgumentChecker.checkArgument(testArray.get("100")));
		assertThat((snatcher.getOutput()), is(Message.get(Message.ERRORMESSAGE_NYURYOKUHUSEI)));
		snatcher.clearOutput();

		//▼引数(inputNum)の値

		//1	inputNum [0]の値：0以下
		assertEquals(false, ArgumentChecker.checkArgument(testArray.get("0", "10")));
		assertThat((snatcher.getOutput()), is(Message.get(Message.ERRORMESSAGE_NYURYOKUHUSEI)));
		snatcher.clearOutput();

		//2	inputNum [0]の値：1~9の入力
		assertEquals(false, ArgumentChecker.checkArgument(testArray.get("11", "10")));
		assertThat((snatcher.getOutput()), is(Message.get(Message.ERRORMESSAGE_NYURYOKU$1)));
		snatcher.clearOutput();

		//3	inputNum [1]の値：0以下
		assertEquals(false, ArgumentChecker.checkArgument(testArray.get("10", "0")));
		assertThat((snatcher.getOutput()), is(Message.get(Message.ERRORMESSAGE_NYURYOKUHUSEI)));
		snatcher.clearOutput();

		//4	inputNum [1]の値：1~9の入力
		assertEquals(false, ArgumentChecker.checkArgument(testArray.get("10", "11")));
		assertThat((snatcher.getOutput()), is(Message.get(Message.ERRORMESSAGE_NYURYOKU$1)));
		snatcher.clearOutput();

		//▼引数に問題無し	

		//1	要素数が2つ且つどちらの値も正の整数
		assertEquals(true, ArgumentChecker.checkArgument(testArray.get("100", "10")));

		//コンソールへ出力先を戻す
		snatcher.release();
	}
}
