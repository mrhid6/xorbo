package mrhid6.xorbo.tileEntity;

import mrhid6.xorbo.Config;
import mrhid6.xorbo.GridPower;
import mrhid6.xorbo.Utils;
import mrhid6.xorbo.interfaces.IGridInterface;
import mrhid6.xorbo.interfaces.ITriniumObj;
import mrhid6.xorbo.interfaces.IXorGridObj;
import net.minecraft.tileentity.TileEntity;

public class TECableBase extends TileEntity implements IGridInterface{

	public double tempPower = 0.0D;
	public double maxPower = 5.0D;

	public boolean hasController = false;

	public GridPower myGrid;
	public int type = 0;

	public TECableBase(){

	}

	@Override
	public void updateEntity() {
		super.updateEntity();

		if(Utils.isClientWorld(worldObj))
			return;
		if(getGrid()==null){
			findGrid();

			if(getGrid()!=null){
				System.out.println("found Grid");
				myGrid.addCable(this);
			}
		}
	}


	public double getCableThickness() {
		switch(type){
			case 0:return 4.0D / 16.0D;
			case 1:return 5.0D / 16.0D;
		}
		
		return 1.0D/16.0D;
	}

	public boolean canInteractWith(TileEntity te){
		
		
		if(te instanceof ITriniumObj)return false;
		if(te instanceof TECableBase)return true;
		if(te instanceof IXorGridObj)return true;

		return false;
	}
	public void breakBlock() {

		if(myGrid!=null){
			myGrid.removeCable(this);
		}
	}

	public void onNeighborBlockChange() {

		worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
	}

	public GridPower getGrid(){
		return myGrid;
	}

	public void findGrid(){
		for(int i=0;i<6;i++){

			if(myGrid!=null)
				return;

			int x1 = xCoord+Config.SIDE_COORD_MOD[i][0];
			int y1 = yCoord+Config.SIDE_COORD_MOD[i][1];
			int z1 = zCoord+Config.SIDE_COORD_MOD[i][2];

			TileEntity te = this.worldObj.getBlockTileEntity(x1,y1,z1);

			if(te instanceof TECableBase && !(te instanceof ITriniumObj)){
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
}
