package mrhid6.xorbo;

import java.util.ArrayList;

import mrhid6.xorbo.tileEntity.TECableBase;
import mrhid6.xorbo.tileEntity.TEPoweredBase;
import mrhid6.xorbo.tileEntity.TEZoroController;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class GridPower {

	private ArrayList<TECableBase> cablesArray;
	private ArrayList<TEZoroController> controllerArray;
	private ArrayList<TEPoweredBase> machineArray;
	private float maxPower = 1000.0F;
	
	
	private float power = 0.0F;
	private World worldObj;
	
	public GridPower(World w){
		worldObj = w;
		
		cablesArray = new ArrayList<TECableBase>();
		controllerArray = new ArrayList<TEZoroController>();
		machineArray = new ArrayList<TEPoweredBase>();
		
		power = maxPower;
	}
	
	public void addCable(TECableBase te) {
		cablesArray.remove(te);
		cablesArray.add(te);
	}
	
	public void addController(TEZoroController te){
		controllerArray.remove(te);
		controllerArray.add(te);
	}

	public void addMachine(TEPoweredBase te) {
		machineArray.remove(te);
		machineArray.add(te);
	}
	
	public ArrayList<TECableBase> getCableObjs(){
		return cablesArray;
	}

	public float getMaxPower(){
		return controllerArray.size() * maxPower;
	}
	
	public boolean hasCable(TECableBase te) {
		for(TECableBase cable : cablesArray){

			if(cable == te)
				return true;
		}

		return false;
	}
	public void removeCable(TECableBase te) {
		cablesArray.remove(te);
		
		for(TECableBase te1 : cablesArray){
			te1.myGrid = null;
		}
		
		cablesArray.clear();
	}
	
	public void removeController(TEZoroController te) {
		controllerArray.remove(te);
		
		for(TEZoroController te1 : controllerArray){
			te1.myGrid = null;
		}
		
		for(TECableBase te1 : cablesArray){
			te1.myGrid = null;
		}
		
		cablesArray.clear();
		controllerArray.clear();
	}

	public void removeMachine(TEPoweredBase te) {
		machineArray.remove(te);
		
		for(TEPoweredBase te1 : machineArray){
			te1.myGrid = null;
		}
		
		machineArray.clear();
	}

	public void subtractPower(float quantity){
		
		this.power -= quantity;
		
		if(this.power < 0.0F){
			this.power = 0.0F;
		}
		
		System.out.println("subtracted:"+quantity+"power:"+power);
	}
	
	public boolean hasPower(){
		return (power>0);
	}

}
