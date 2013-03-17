package mrhid6.xorbo.tileEntity;

import mrhid6.xorbo.GridPower;
import mrhid6.xorbo.interfaces.IXorGridObj;

public class TEZoroController extends TEPoweredBase implements IXorGridObj{
	
	private int TickSinceUpdate = 0;

	public TEZoroController(){
		
	}
	
	public void breakBlock() {
		myGrid.removeController(this);
	}

	@Override
	public void updateEntity() {
		super.updateEntity();

		if(worldObj.isRemote)
			return;

		if((TickSinceUpdate % 10) == 0){
			//System.out.println("cables:"+myGrid.getCableObjs().size());
			
		}
		
		TickSinceUpdate++;
	}

	public void init(){
		
		findGridOnlyCable();
		
		if(myGrid==null){
			myGrid = new GridPower(worldObj);
			myGrid.addController(this);
		}
	}

}
