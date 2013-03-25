package mrhid6.xorbo.tileEntity;

import mrhid6.xorbo.Config;
import mrhid6.xorbo.interfaces.IConverterObj;
import mrhid6.xorbo.interfaces.ITriniumObj;
import mrhid6.xorbo.interfaces.IXorGridObj;
import net.minecraft.tileentity.TileEntity;

public class TETriniumCable extends TECableBase implements ITriniumObj{



	public TETriniumCable() {
		type = 1;
	}

	public boolean canInteractRender(TileEntity te,int side){
		if(te instanceof TETriniumConverter){

			if(side == 2 || side == 3){
				return false;
			}

			return true;
		}

		return canInteractWith(te,side);
	}

	public boolean canInteractWith(TileEntity te, int side){

		if(te instanceof TETriniumConverter){			
			TETriniumConverter te1 = (TETriniumConverter)te;
			return te1.canConnectSide(side);
		}

		if(te instanceof ITriniumObj)return true;
		if(te instanceof IConverterObj)return true;

		return false;
	}

	@Override
	public void findGrid(){
		for(int i=0;i<6;i++){

			if(getGrid()!=null)
				return;

			int x1 = xCoord+Config.SIDE_COORD_MOD[i][0];
			int y1 = yCoord+Config.SIDE_COORD_MOD[i][1];
			int z1 = zCoord+Config.SIDE_COORD_MOD[i][2];

			TileEntity te = this.worldObj.getBlockTileEntity(x1,y1,z1);

			if(te instanceof TETriniumCable){
				TETriniumCable cable = (TETriniumCable)te;

				if(cable.getGrid()!=null){
					this.gridindex=cable.getGrid().gridIndex;
					break;
				}
			}else if(te instanceof TETriniumConverter){
				TETriniumConverter controller = (TETriniumConverter)te;

				if(controller.canConnectSide(i)){
				
					if(controller.getGrid()!=null){
						this.gridindex=controller.getGrid().gridIndex;
						break;
					}
				}
			}
		}
	}
}
