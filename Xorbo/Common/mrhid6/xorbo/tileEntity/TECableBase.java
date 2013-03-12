package mrhid6.xorbo.tileEntity;

import mrhid6.xorbo.interfaces.IXorGridObj;
import net.minecraft.tileentity.TileEntity;

public class TECableBase extends TEPoweredBase{
	
	public double tempPower = 0.0D;
	public double maxPower = 5.0D;
	
	public boolean hasController = false;

	public TECableBase(){

	}
	
	public void powerRequest(){
		
	}

	@Override
	public void updateEntity() {
		super.updateEntity();
	}


	public static double getCableThickness() {
		return 4.0D / 16.0D;
	}

	public boolean canInteractWith(TileEntity te){

		if(te instanceof TECableBase)return true;
		if(te instanceof IXorGridObj)return true;

		return false;
	}

	public boolean isMachine(TileEntity te){

		if(te instanceof IXorGridObj)return true;

		return false;
	}

	@Override
	public void init() {
		
		cables.clear();
		controllers.clear();
		findController(xCoord,yCoord,zCoord);
		addMeToGrid(this);
		updateControllers();
	}

	public void breakBlock() {
		removeMeFromGrid(this);
		updateControllers();
	}

	public void onNeighborBlockChange() {
		if(!hasController){
			findController(xCoord,yCoord,zCoord);
			addMeToGrid(this);
			updateControllers();
		}else{
			updateControllers();
		}
	}


}
