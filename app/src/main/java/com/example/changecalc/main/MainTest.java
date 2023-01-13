/**
 * 
 */
package com.example.changecalc.main;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import org.junit.jupiter.api.Test;

import message.Message;
import test.ArrayBilder;
import test.OutputSnatcher;

class MainTest {

	/**
	 * {@link main.Main#main(java.lang.String[])} のためのテスト・メソッド。
	 */

	//int型やString型の配列を生成するテスト用のクラス
	private ArrayBilder testArray = new ArrayBilder();

	//出力内容をコンソールから奪うテスト用のクラス
	private OutputSnatcher snatcher = OutputSnatcher.getInstance();

	@Test
	void testMain() {
		
		Main main = new Main();
		Message message = new Message();

		//コンソールからOutputSnatcherクラスへ出力先を変更
		snatcher.snatch();

		//1	正常な入力の中央値
		Main.main(testArray.get("670", "10"));
		assertThat(Message.get(Message.TEST_$500$100$50$10), is(snatcher.getOutput()));
		snatcher.clearOutput();

		//2	引数なし
		Main.main(testArray.getEmptyStr());
		assertThat((snatcher.getOutput()), is(Message.get(Message.ERRORMESSAGE_NYURYOKUNASHI)));
		snatcher.clearOutput();

		//3	引数1つ
		Main.main(testArray.get("100"));
		assertThat((snatcher.getOutput()), is(Message.get(Message.ERRORMESSAGE_NYURYOKUHUSEI)));
		snatcher.clearOutput();

		//4	1円単位の入力（args[0]）
		Main.main(testArray.get("1", "10"));
		assertThat((snatcher.getOutput()), is(Message.get(Message.ERRORMESSAGE_NYURYOKU$1)));
		snatcher.clearOutput();

		//5	1円単位の入力（args[1]）
		Main.main(testArray.get("10", "1"));
		assertThat((snatcher.getOutput()), is(Message.get(Message.ERRORMESSAGE_NYURYOKU$1)));
		snatcher.clearOutput();

		//6	負数の入力（args[0]）
		Main.main(testArray.get("-10", "10"));
		assertThat((snatcher.getOutput()), is(Message.get(Message.ERRORMESSAGE_NYURYOKUHUSEI)));
		snatcher.clearOutput();

		//7	負数の入力（args[1]）
		Main.main(testArray.get("10", "-10"));
		assertThat((snatcher.getOutput()), is(Message.get(Message.ERRORMESSAGE_NYURYOKUHUSEI)));
		snatcher.clearOutput();

		//8	数字以外の入力（args[0]）
		Main.main(testArray.get("a", "10"));
		assertThat((snatcher.getOutput()), is(Message.get(Message.ERRORMESSAGE_REQIRE_SEINOSEISU)));
		snatcher.clearOutput();

		//9	数字以外の入力（args[1]）
		Main.main(testArray.get("10", "a"));
		assertThat((snatcher.getOutput()), is(Message.get(Message.ERRORMESSAGE_REQIRE_SEINOSEISU)));
		snatcher.clearOutput();

		//10	整数以外の入力（args[0]）
		Main.main(testArray.get("10.1", "10"));
		assertThat((snatcher.getOutput()), is(Message.get(Message.ERRORMESSAGE_REQIRE_SEINOSEISU)));
		snatcher.clearOutput();

		//11	整数以外の入力（args[1]）
		Main.main(testArray.get("10", "10.1"));
		assertThat((snatcher.getOutput()), is(Message.get(Message.ERRORMESSAGE_REQIRE_SEINOSEISU)));
		snatcher.clearOutput();

		//12	お釣り無し
		Main.main(testArray.get("10", "10"));
		assertThat((snatcher.getOutput()), is(Message.get(Message.OTURI_NASHI)));
		snatcher.clearOutput();

		//13	投入金額不足
		Main.main(testArray.get("10", "20"));
		assertThat((snatcher.getOutput()), is(Message.get(Message.OTURI_HUSOKU)));
		snatcher.clearOutput();
		
		Main.main(null);
		assertThat((snatcher.getOutput()), is(Message.get(Message.ERRORMESSAGE_NULLPO)));
		snatcher.clearOutput();

		//コンソールへ出力先を戻す
		snatcher.release();
	}
}
