package mrhid6.xorbo.tileEntity;

import java.util.ArrayList;

import mrhid6.xorbo.interfaces.IXorGridObj;
import net.minecraft.tileentity.TileEntity;

public class TECableBase extends TileEntity{
	
	public ArrayList controllers;
	public ArrayList cables;
	
	private boolean isLoaded = false;
	
	public TECableBase(){
		controllers = new ArrayList();
		cables = new ArrayList();
		
	}
	
	
	@Override
	public void updateEntity() {
		super.updateEntity();
		
		if(this.worldObj.isRemote)
			return;
		
		if(!isLoaded){
			cables.clear();
			controllers.clear();
			findController(xCoord,yCoord,zCoord);
			isLoaded = true;
		}
	}


	public static double getCableThickness() {
		return 4.0D / 16.0D;
	}
	
	public boolean canInteractWith(TileEntity te){
		
		if(te instanceof TECableBase)return true;
		if(te instanceof IXorGridObj)return true;
		
		return false;
	}
	
	public boolean isMachine(TileEntity te){
		
		if(te instanceof IXorGridObj)return true;
		
		return false;
	}
	
	public void findController(int x,int y,int z){
		for(int x1 = x-1;x1<x+1;x1++){
			for(int z1 = z-1;z1<z+1;z1++){
				for(int y1 = y-1;y1<y+1;y1++){
					TileEntity te = this.worldObj.getBlockTileEntity(x1, y1, z1);
					
					if(te instanceof TEZoroController && !controllers.contains(te)){
						
						TEZoroController controller = (TEZoroController) te;
						
						if(!controller.hasCable(this)){
							controller.addCable(this);
						}
						
						controllers.add(te);
					}
					
					if(te instanceof TECableBase && !cables.contains(te)){
						cables.add(te);
						
						findController(x1,y1,z1);
					}
				}
			}
		}
	}


}
