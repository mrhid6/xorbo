package mrhid6.xorbo.tileEntity;

import mrhid6.xorbo.Config;
import mrhid6.xorbo.GridManager;
import mrhid6.xorbo.GridPower;
import mrhid6.xorbo.Utils;
import mrhid6.xorbo.interfaces.IGridInterface;
import net.minecraft.tileentity.TileEntity;

public abstract class TEPoweredBase extends TileRoot implements IGridInterface{
	
	public int TickSinceUpdate = 0;
	public boolean isLoaded = false;
	public GridPower myGrid;
	public int gridindex = -1;
	
	public void findGrid(){
		for(int i=0;i<6;i++){
			
			if(myGrid!=null)
				return;
			
			int x1 = xCoord+Config.SIDE_COORD_MOD[i][0];
			int y1 = yCoord+Config.SIDE_COORD_MOD[i][1];
			int z1 = zCoord+Config.SIDE_COORD_MOD[i][2];

			TileEntity te = this.worldObj.getBlockTileEntity(x1,y1,z1);
			
			if(te instanceof TECableBase){
				TECableBase cable = (TECableBase)te;
				
				if(cable.canInteractWith(this) && cable.getGrid()!=null){
					gridindex=cable.getGrid().gridIndex;
					break;
				}
			}else if(te instanceof TEZoroController){
				TEZoroController controller = (TEZoroController)te;
				
				if(controller.canInteractWith(this) && controller.getGrid()!=null){
					gridindex=controller.getGrid().gridIndex;
					break;
				}
			}
		}
	}
	
	public void findGridOnlyCable(){
		for(int i=0;i<6;i++){
			
			if(myGrid!=null)
				return;
			
			int x1 = xCoord+Config.SIDE_COORD_MOD[i][0];
			int y1 = yCoord+Config.SIDE_COORD_MOD[i][1];
			int z1 = zCoord+Config.SIDE_COORD_MOD[i][2];

			TileEntity te = this.worldObj.getBlockTileEntity(x1,y1,z1);
			
			if(te instanceof TECableBase){
				TECableBase cable = (TECableBase)te;
				
				if(cable.getGrid()!=null){
					gridindex=cable.getGrid().gridIndex;
					System.out.println("found Grid");
					break;
				}
			}
		}
	}
	
	@Override
	public GridPower getGrid() {
		GridPower grid = GridManager.getGrid(this.gridindex);
		
		return grid;

	}
	
	public abstract void init();
	
	public boolean isLoaded() {
		return isLoaded;
	}
	
	@Override
	public void updateEntity() {
		super.updateEntity();
		
		if(Utils.isClientWorld(worldObj))
			return;
		
		if(!isLoaded){
			init();
			isLoaded=true;
		}
	}
}
