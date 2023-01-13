package com.example.changecalc.test;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**出力内容をコンソールから奪うテスト用のクラス*/
public final class OutputSnatcher {

	//シングルトン
	private static final OutputSnatcher INSTANCE = new OutputSnatcher();

	//元の出力(コンソール) 
	private PrintStream nativeOut = null;

	//変更後の出力(このクラスへの受け皿)
	private ByteArrayOutputStream snatchedOut = new ByteArrayOutputStream();//

	//奪取済みフラグ
	private boolean stealFlag = false;

	//デフォルトコンストラクタの禁止（他でインスタンス化させないため）
	private OutputSnatcher() {
	}

	//@return インスタンス（シングルトン）
	public static OutputSnatcher getInstance() {
		return INSTANCE;
	}

	/**
	 * System.setOut(●)で、出力先を奪い
	 * コンソールからこのクラスへ出力するように設定
	 */
	public void snatch() {
		if (!stealFlag) {//２重のスナップ禁止
			nativeOut = System.out;

			//1.PrintStreamで指定された引数の出力ストリームに出力先をリダイレクト
			//2.BufferedOutputStreamに受け皿(基)となる出力ストリームを入れ、そこにデータを書き込む
			System.setOut(new PrintStream(new BufferedOutputStream(snatchedOut)));
			
			stealFlag = true;
		}
	}

	//標準出力をクリア
	public void clearOutput() {
		snatchedOut.reset();
	}

	/**
	 * 標準出力の出力内容を取得
	 * @return 標準出力の出力内容
	 */
	public String getOutput() {
		System.out.flush();
		return snatchedOut.toString();
	}

	//出力先を元に戻す
	public void release() {
		if (stealFlag) {//２重で開放禁止
			clearOutput();
			System.setOut(nativeOut);
			stealFlag = false;
		}
	}
}
