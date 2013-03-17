package mrhid6.xorbo.tileEntity;

import mrhid6.xorbo.Config;
import mrhid6.xorbo.GridPower;
import mrhid6.xorbo.interfaces.IGridInterface;
import net.minecraft.tileentity.TileEntity;

public abstract class TEPoweredBase extends TileEntity implements IGridInterface{
	
	public GridPower myGrid;
	public boolean isLoaded = false;
	
	public boolean isLoaded() {
		return isLoaded;
	}

	
	@Override
	public void updateEntity() {
		super.updateEntity();

		if(worldObj.isRemote)
			return;

		if(!isLoaded){
			init();
			isLoaded=true;
		}
	}
	
	public abstract void init();
	
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
				
				if(cable.getGrid()!=null){
					myGrid=cable.getGrid();
					break;
				}
			}else if(te instanceof TEZoroController){
				TEZoroController controller = (TEZoroController)te;
				
				if(controller.getGrid()!=null){
					myGrid=controller.getGrid();
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
					myGrid=cable.getGrid();
					System.out.println("found Grid");
					break;
				}
			}
		}
	}
	
	@Override
	public GridPower getGrid() {
		return myGrid;
	}
}
