package mrhid6.xorbo.interfaces;

import mrhid6.xorbo.GridPower;

public abstract interface IGridInterface {
	
	
	public abstract GridPower getGrid();
	public abstract void findGrid();
}
