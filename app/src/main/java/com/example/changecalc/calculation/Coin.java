package com.example.changecalc.calculation;

/**硬貨クラス*/
public class Coin {
	
	//硬貨の単位
	private int coinUnit;
	
	//硬貨の枚数
	private int stockNum; 
	
	/**
	 * @param unit:硬貨の単位
	 * @param addNum:硬貨の枚数
	 */
	public Coin(int unit, int stockNum) {
		coinUnit = unit;
		this.stockNum = stockNum;
	}
	
	@Override
	public String toString() {
		return "[" + coinUnit + "円 ×" + stockNum + "] ";
	}
}
