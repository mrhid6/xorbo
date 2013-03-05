package mrhid6.xorbo.tileEntity;

import net.minecraft.tileentity.TileEntity;

public class TECableBase extends TileEntity{

	protected byte connections = -1;
	protected byte connectionMask = 0;
	
	
	public static double getCableThickness() {
		return 4.0D / 16.0D;
	}
	
	public boolean canInteractWith(TileEntity te){
		return (te instanceof TECableBase)?true:false;
	}


}
