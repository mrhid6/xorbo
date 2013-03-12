package mrhid6.xorbo;

import java.util.ArrayList;

import mrhid6.xorbo.tileEntity.TECableBase;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class GridPower {

	private ArrayList<TECableBase> cablesArray;
	private World worldObj;
	
	
	private float power = 0.0F;
	private float maxpower = 0.0F;
	
	public GridPower(World w){
		worldObj = w;
		cablesArray = new ArrayList<TECableBase>();
		
		
		maxpower = 500.0F;
		power = maxpower;
	}
	
	public void subtractPower(float quantity){
		
		this.power -= quantity;
		
		if(this.power < 0.0F){
			this.power = 0.0F;
		}
		
		System.out.println("subtracted:"+quantity+"power:"+power);
	}

	public boolean hasCable(TECableBase te) {
		for(TECableBase cable : cablesArray){

			if(cable == te)
				return true;
		}

		return false;
	}

	public void addCable(TECableBase te) {
		cablesArray.remove(te);
		cablesArray.add(te);
	}
	
	public void removeCable(TECableBase te) {
		cablesArray.remove(te);
	}

	public void loadGrid(int x,int y,int z){
		for(int i=0;i<6;i++){

			int x1 = x+Config.SIDE_COORD_MOD[i][0];
			int y1 = y+Config.SIDE_COORD_MOD[i][1];
			int z1 = z+Config.SIDE_COORD_MOD[i][2];

			TileEntity te = this.worldObj.getBlockTileEntity(x1, y1, z1);
			if(te !=null){
				if(te instanceof TECableBase && !hasCable((TECableBase)te)){
					addCable((TECableBase)te);
					loadGrid(x1,y1,z1);
				}
			}
		}
	}
	
	public void reloadGrid(int x,int y,int z){
		cablesArray.clear();
		
		loadGrid(x,y,z);
	}

	public ArrayList<TECableBase> getCableObjs(){
		return cablesArray;
	}

}
