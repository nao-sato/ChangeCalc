/**
 * 
 */
package com.example.changecalc.calculation;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import message.Message;
import test.ArrayBilder;
import test.OutputSnatcher;

class CalculatorTest {

	//int型やString型の配列を生成するテスト用のクラス
	private ArrayBilder testArray = new ArrayBilder();

	//出力内容をコンソールから奪うテスト用のクラス
	private OutputSnatcher snatcher = OutputSnatcher.getInstance();

	//硬貨の生成・お釣りの管理・払い出しを担うクラス
	private Change change = new Change();

	@Test
	void test() {

		//テスト用にインスタンス化
		Calculator calc = new Calculator();

		/**コンソールからOutputSnatcherクラスへ出力先を変更
		 * 以降、snatcher.getOutput()で出力内容を取得できる。
		 * 但し、snatcher.clearOutput()をしないと、前回の出力内容が残ったままになる。
		 */
		snatcher.snatch();

		//▼投入金額と購入金額の差分計算	

		//1	inputNumがNull
		assertThrows(NullPointerException.class,
				() -> Calculator.startCalc(null, change, testArray.get(50, 10)));

		//2	差分(diff)：0
		assertThrows(ArrayIndexOutOfBoundsException.class,
				() -> Calculator.startCalc(testArray.getEmptyInt(), change, testArray.get(50, 10)));

		//2	差分(diff)：0
		Calculator.startCalc(testArray.get(100, 100), change, testArray.get(50, 10));
		assertThat((snatcher.getOutput()), is(Message.get(Message.OTURI_NASHI)));
		snatcher.clearOutput();

		//3	差分(diff)：−1
		Calculator.startCalc(testArray.get(100, 101), change, testArray.get(50, 10));
		assertThat((snatcher.getOutput()), is(Message.get(Message.OTURI_HUSOKU)));
		snatcher.clearOutput();

		assertThrows(NullPointerException.class,
				() -> Calculator.startCalc(testArray.get(70, 10), null, testArray.get(50, 10)));

		//▼硬貨の振り分け処理	

		//1	avalableCoinがnull
		assertThrows(NullPointerException.class,
				() -> Calculator.startCalc(null, change, testArray.get(50, 10)));

		//2	正常な入力の中央値（diffをCoinの最小単位の値で割り切れる）
		Calculator.startCalc(testArray.get(70, 10), change, testArray.get(50, 10));
		assertThat(change.getChangeString(), is(Message.get(Message.TEST_$50$10)));
		change.clearArray();
		snatcher.clearOutput();

		//3	お釣りが0枚の硬貨は非表示になるか
		Calculator.startCalc(testArray.get(120, 10), change, testArray.get(100, 50, 10));
		assertThat(change.getChangeString(), is(Message.get(Message.TEST_$100$10)));
		change.clearArray();
		snatcher.clearOutput();

		//4	diffをCoinの最小単位の値で割り切れない
		Calculator.startCalc(testArray.get(20, 10), change, testArray.get(11));
		assertEquals(0, change.getChangeArray().size());
		assertThat((snatcher.getOutput()), is(""));
		snatcher.clearOutput();

		//コンソールへ出力先を戻す
		snatcher.release();
	}
}
