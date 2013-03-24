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
	private float maxPower = 3000.0F;

	public int gridIndex;
	private float Power = 0.0F;
	private World worldObj;

	public GridPower(World w){
		worldObj = w;

		cablesArray = new ArrayList<TECableBase>();
		controllerArray = new ArrayList<TEZoroController>();
		machineArray = new ArrayList<TEPoweredBase>();

		Power = 0;

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
		System.out.println("addedcontroller!");
	}

	public void addMachine(TEPoweredBase te) {
		machineArray.remove(te);
		machineArray.add(te);

		te.gridindex = this.gridIndex;

		SyncMachines();
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

		for(TEPoweredBase te : machineArray){

			te.onInventoryChanged();
		}

		SyncMachines();

		//System.out.println("subtracted:"+quantity+"power:"+Power);
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

		for(TEPoweredBase te : machineArray){

			te.onInventoryChanged();
		}

		SyncMachines();

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
	
	public boolean hasController(int x,int y,int z){
		
		for(TEZoroController te : controllerArray){
			TileEntity te1 = worldObj.getBlockTileEntity(x, y, z);
			
			if(te1!=null && te1 instanceof TEZoroController){
				if(te.xCoord==x && te.yCoord==y && te.zCoord==z){
					return true;
				}
			}
		}
		
		return false;
	}
	
	public void removeController(int x,int y,int z){
		
		for(int i=0;i<controllerArray.size();i++){
			TEZoroController te = controllerArray.get(i);
			TileEntity te1 = worldObj.getBlockTileEntity(x, y, z);
			
			if(te1!=null && te1 instanceof TEZoroController){
				if(te.xCoord==x && te.yCoord==y && te.zCoord==z){
					controllerArray.remove(i);
				}
			}
		}
		
		for(TECableBase te1 : cablesArray){
			te1.myGrid = null;
		}

		for(TEPoweredBase te1 : machineArray){
			te1.gridindex = -1;
		}

		machineArray.clear();
		cablesArray.clear();
	}

	public ArrayList<TEPoweredBase> getMachines(){
		return this.machineArray;
	}

	public void SyncMachines(){
		for(TEPoweredBase te : machineArray){

			te.TickSinceUpdate=0;
		}
	}
}
