package mrhid6.xorbo.tileEntity;

import java.util.ArrayList;

import mrhid6.xorbo.interfaces.IXorGridObj;
import net.minecraft.tileentity.TileEntity;

public class TEZoroController extends TileEntity implements IXorGridObj{

	private boolean isLoaded = false;
	private boolean needUpdate = false;

	private ArrayList<TECableBase> cablesArray;

	public TEZoroController(){


		cablesArray = new ArrayList<TECableBase>();
	}

	public void markForUpdate(){
		needUpdate = true;
	}

	@Override
	public void updateEntity() {
		super.updateEntity();

		if(!isLoaded || needUpdate){
			init();
			isLoaded=true;
			needUpdate=false;
		}
	}

	public void init(){
		loadGrid(xCoord,yCoord,zCoord);
	}

	public void loadGrid(int x,int y,int z){
		for(int x1 = x-1;x1<x+1;x1++){
			for(int z1 = z-1;z1<z+1;z1++){
				for(int y1 = y-1;y1<y+1;y1++){
					TileEntity te = this.worldObj.getBlockTileEntity(x1, y1, z1);
					if(te instanceof TECableBase && !hasCable((TECableBase)te)){
						
						addCable((TECableBase)te);
						loadGrid(x1,y1,z1);
					}
				}
			}
		}
	}

	public boolean hasCable(TECableBase te) {
		if(cablesArray.contains(te)) return true;
		
		return false;
	}

	public void addCable(TECableBase te) {
		cablesArray.remove(te);
		cablesArray.add(te);
		
		System.out.println("added cable to array :)");
	}


}
