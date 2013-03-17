package mrhid6.xorbo.tileEntity;

import mrhid6.xorbo.Config;
import mrhid6.xorbo.GridPower;
import mrhid6.xorbo.interfaces.IGridInterface;
import mrhid6.xorbo.interfaces.IXorGridObj;
import net.minecraft.tileentity.TileEntity;

public class TEZoroFurnace extends TEPoweredBase implements IXorGridObj, IGridInterface{
	
	private int TickSinceUpdate = 0;
	
	public TEZoroFurnace(){
		
	}
	
	public void breakBlock() {
		myGrid.removeMachine(this);
	}
	
	
	@Override
	public void updateEntity() {
		super.updateEntity();
		
		if((TickSinceUpdate  % 10) == 0){
			
			if(getGrid()==null){
				findGrid();
				
				if(getGrid()!=null){
					System.out.println("found Grid zoroFurnace");
					myGrid.addMachine(this);
				}
			}
		}
	}

	@Override
	public void init() {
		
	}
}
