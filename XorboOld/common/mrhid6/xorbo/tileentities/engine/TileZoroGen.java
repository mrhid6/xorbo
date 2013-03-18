package mrhid6.xorbo.tileentities.engine;

import net.minecraft.item.ItemStack;
import buildcraft.api.power.IPowerProvider;
import mrhid6.xorbo.PowerProviderXor;
import mrhid6.xorbo.Utils;

public class TileZoroGen extends TileEngineMain{


	public TileZoroGen() {

		this.inventory = new ItemStack[1];
		
		inputsides = new boolean[6];
		inputsideproviders = new IPowerProvider[6];
		
		this.myProvider = new PowerProviderXor();
		this.myProvider.configure(200, (int) getMaxEnergy());
		
		this.invName = "xor.generator";
	}
	
	public static boolean setDescPacketId(int id)
	{
		if (id == 0) {
			return false;
		}
		descPacketId = id;
		return true;
	}

	@Override
	public boolean canProcess() {

		if(this.inventory[0]==null){
			return false;
		}

		if(Utils.getFuelFor(this.inventory[0])<=0)
			return false;

		return true;
	}

	@Override
	public boolean gainFuel() {
		if(this.inventory[0]==null){
			return false;
		}

		int value = Utils.getFuelFor(this.inventory[0]) / 6;
		if (value <= 0)
		{
			return false;
		}
		this.fuelMJ += value;


		if(this.inventory[0].getItem().hasContainerItem()){
			this.inventory[0] = this.inventory[0].getItem().getContainerItemStack(this.inventory[0]);
		}else{
			this.inventory[0].stackSize -=1;
		}

		if(this.inventory[0].stackSize==0){
			this.inventory[0]=null;
		}
		return true;
	}

	public float getMaxEnergy(){
		return 8000F;
	}

	public float getMaxPower(){
		return getPower();
	}

	@Override
	public float getPower() {
		return 0.7f;
	}

	@Override
	public int getSizeInventory() {
		return 1;
	}

	@Override
	public boolean Process() {

		boolean updateNeeded = false;

		if(this.needsFuel()){
			updateNeeded=this.gainFuel();
		}

		if (this.fuelMJ > 0.0F) {
			float energyToAdd = getPower();
			this.myProvider.addEnergy(energyToAdd);
			this.fuelMJ -= energyToAdd;
		}

		return updateNeeded;

	}


}
