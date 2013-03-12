package mrhid6.xorbo.tileEntity;

import java.util.ArrayList;

import mrhid6.xorbo.Config;
import net.minecraft.tileentity.TileEntity;

public abstract class TEPoweredBase extends TileEntity{
	
	
	public boolean isLoaded = false;
	
	public ArrayList<TEZoroController> controllers;
	public ArrayList<TECableBase> cables;
	
	public TEPoweredBase(){
		controllers = new ArrayList<TEZoroController>();
		cables = new ArrayList<TECableBase>();
	}
	
	public boolean isLoaded() {
		return isLoaded;
	}
	
	public void updateControllers(){

		if(worldObj.isRemote)
			return;

		for(int i=0; i<controllers.size();i++){
			TEZoroController controller = controllers.get(i);
			
			if(worldObj.getBlockTileEntity(controller.xCoord, controller.yCoord, controller.zCoord)!=null)
				controller.updateGridObj();
			else
				controllers.remove(i);
		}
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
	
	public void findController(int x,int y,int z){
		for(int i=0;i<6;i++){

			int x1 = x+Config.SIDE_COORD_MOD[i][0];
			int y1 = y+Config.SIDE_COORD_MOD[i][1];
			int z1 = z+Config.SIDE_COORD_MOD[i][2];

			TileEntity te = this.worldObj.getBlockTileEntity(x1,y1,z1);
			
			if(te instanceof TEZoroController && !controllers.contains(te)){

				TEZoroController controller = (TEZoroController) te;
				if(controller.isLoaded()){
					System.out.println("found controller!");

					controllers.add((TEZoroController)te);
				}

			}

			if(te instanceof TECableBase && !cables.contains(te)){
				cables.add((TECableBase)te);

				findController(x1,y1,z1);
			}
		}
	}
	
	
	public void addMeToGrid(TEPoweredBase te){
		for(TEZoroController controller : controllers){
			if(te instanceof TECableBase){
				if(!controller.getGrid().hasCable((TECableBase)te)){
					controller.getGrid().addCable((TECableBase)te);
				}
			}
		}
	}
	
	public void removeMeFromGrid(TEPoweredBase te){
		for(TEZoroController controller : controllers){
			if(te instanceof TECableBase){
				if(controller.getGrid().hasCable((TECableBase)te)){
					controller.getGrid().removeCable((TECableBase)te);
				}
			}
		}
	}
	public abstract void init();
}
