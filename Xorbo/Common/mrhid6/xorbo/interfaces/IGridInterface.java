package mrhid6.xorbo.interfaces;

import mrhid6.xorbo.GridPower;

public abstract interface IGridInterface {

	public abstract void findGrid();

	public abstract GridPower getGrid();
}
