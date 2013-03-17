package mrhid6.xorbo.tileEntity;

import mrhid6.xorbo.interfaces.IXorGridObj;

public class TEZoroFurnace extends TEPoweredBase implements IXorGridObj{
	
	public TEZoroFurnace(){
		
	}

	@Override
	public void updateGridObj() {
		
	}

	@Override
	public void init() {
		
		cables.clear();
		controllers.clear();
		findController(xCoord,yCoord,zCoord);
	}
}
