package mrhid6.xorbo.tileEntity;

import mrhid6.xorbo.Config;
import mrhid6.xorbo.interfaces.IConverterObj;
import mrhid6.xorbo.interfaces.ITriniumObj;
import net.minecraft.tileentity.TileEntity;

public class TETriniumCable extends TECableBase implements ITriniumObj{
	
	
	
	public TETriniumCable() {
		type = 1;
	}

	public boolean canInteractWith(TileEntity te){
		
		if(te instanceof ITriniumObj)return true;
		if(te instanceof IConverterObj)return true;

		return false;
	}
	
	@Override
	public void findGrid(){
		for(int i=0;i<6;i++){

			if(myGrid!=null)
				return;

			int x1 = xCoord+Config.SIDE_COORD_MOD[i][0];
			int y1 = yCoord+Config.SIDE_COORD_MOD[i][1];
			int z1 = zCoord+Config.SIDE_COORD_MOD[i][2];

			TileEntity te = this.worldObj.getBlockTileEntity(x1,y1,z1);

			if(te instanceof TETriniumCable){
				TETriniumCable cable = (TETriniumCable)te;

				if(cable.getGrid()!=null){
					myGrid=cable.getGrid();
					break;
				}
			}else if(te instanceof TETriniumConverter){
				TETriniumConverter controller = (TETriniumConverter)te;

				if(controller.getGrid()!=null){
					myGrid=controller.getGrid();
					break;
				}
			}
		}
	}
}
