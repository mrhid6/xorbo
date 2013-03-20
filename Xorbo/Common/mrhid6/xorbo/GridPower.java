package mrhid6.xorbo;

import java.util.ArrayList;

import mrhid6.xorbo.tileEntity.TECableBase;
import mrhid6.xorbo.tileEntity.TEPoweredBase;
import mrhid6.xorbo.tileEntity.TEZoroController;
import mrhid6.xorbo.tileEntity.TEZoroFurnace;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class GridPower {

	private ArrayList<TECableBase> cablesArray;
	private ArrayList<TEZoroController> controllerArray;
	private ArrayList<TEPoweredBase> machineArray;
	private float maxPower = 1000.0F;
	
	public int gridIndex;
	private float Power = 0.0F;
	private World worldObj;
	
	public GridPower(World w){
		worldObj = w;
		
		cablesArray = new ArrayList<TECableBase>();
		controllerArray = new ArrayList<TEZoroController>();
		machineArray = new ArrayList<TEPoweredBase>();
		
		Power = maxPower;
		
		gridIndex = GridManager.addGridToManager(this);
	}
	
	public void addCable(TECableBase te) {
		cablesArray.remove(te);
		cablesArray.add(te);

	}
	
	public void addController(TEZoroController te){
		controllerArray.remove(te);
		controllerArray.add(te);
		te.gridindex = this.gridIndex;
	}

	public void addMachine(TEPoweredBase te) {
		machineArray.remove(te);
		machineArray.add(te);
		
		te.gridindex = this.gridIndex;
	}
	
	public ArrayList<TECableBase> getCableObjs(){
		return cablesArray;
	}

	public float getMaxEnergy(){
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
		
		for(TEPoweredBase te1 : machineArray){
			te1.gridindex = -1;
		}
		
		machineArray.clear();
	}
	
	public void removeController(TEZoroController te) {
		controllerArray.remove(te);
		
		for(TECableBase te1 : cablesArray){
			te1.myGrid = null;
		}
		
		for(TEPoweredBase te1 : machineArray){
			te1.gridindex = -1;
		}
		
		machineArray.clear();
		cablesArray.clear();
	}

	public void removeMachine(TEPoweredBase te) {
		machineArray.remove(te);
		
		for(TEPoweredBase te1 : machineArray){
			te1.gridindex = -1;
		}
		
		machineArray.clear();
	}

	public void subtractPower(float quantity){
		
		this.Power -= quantity;
		
		if(this.Power < 0.0F){
			this.Power = 0.0F;
		}
		
		System.out.println("subtracted:"+quantity+"power:"+Power);
	}
	
	public boolean hasPower(){
		return (Power>0);
	}

	public float getEnergyStored() {
		return Power;
	}
	
	public void setEnergyStored(float power){
		this.Power = power;
		
		System.out.println(power);
	}

	public void addEnergy(float f) {
		this.Power+=f;
		
		if(Power> getMaxEnergy()){
			Power = getMaxEnergy();
		}
		
	}
	
	public ArrayList<TEZoroController> getControllers(){
		
		return controllerArray;
	}

	public boolean hasMachine(TEPoweredBase te) {
		for(TEPoweredBase te1 : machineArray){
			
			if(te.equals(te1))
				return true;
		}
		
		return false;
	}
	
	public ArrayList<TEPoweredBase> getMachines(){
		return this.machineArray;
	}
}
