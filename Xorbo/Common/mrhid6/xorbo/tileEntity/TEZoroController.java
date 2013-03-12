package mrhid6.xorbo.tileEntity;

import mrhid6.xorbo.GridPower;
import mrhid6.xorbo.interfaces.IXorGridObj;

public class TEZoroController extends TEPoweredBase implements IXorGridObj{

	private boolean needUpdate = false;

	private GridPower myGrid;

	public TEZoroController(){
		
	}

	public void markForUpdate(){
		needUpdate = true;
	}

	@Override
	public void updateEntity() {
		super.updateEntity();

		if(worldObj.isRemote)
			return;

		if(needUpdate){
			System.out.println("cables:"+myGrid.getCableObjs().size());
			needUpdate=false;
		}
	}

	public void init(){
		
		if(myGrid==null){
			myGrid = new GridPower(worldObj);
		}
		
		myGrid.loadGrid(xCoord,yCoord,zCoord);
	}
	
	public void usePower(float amount){
		myGrid.subtractPower(1);
	}

	@Override
	public void updateGridObj() {

		if(worldObj.isRemote)
			return;

		myGrid.reloadGrid(xCoord,yCoord,zCoord);
		System.out.println("cables:"+myGrid.getCableObjs().size());

	}

	public GridPower getGrid(){
		return myGrid;
	}

}
