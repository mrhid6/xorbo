package mrhid6.xorbo.tileentities;

import mrhid6.xorbo.Utils;
import mrhid6.xorbo.blocks.ModBlocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import buildcraft.api.power.IPowerProvider;
import buildcraft.api.power.IPowerReceptor;

public class TETriniumChillerBase extends TileEntity implements IPowerReceptor{
	
	private TETriniumChillerCore core = null;
	
	public TETriniumChillerCore getCore(){
		if(core == null)
			core = (TETriniumChillerCore)Utils.getTileEntity(worldObj, xCoord, yCoord, zCoord, 5, ModBlocks.triniumChiller.blockID);
		
		return core;
	}
	
	@Override
	public void readFromNBT(NBTTagCompound par1nbtTagCompound) {
		super.readFromNBT(par1nbtTagCompound);
	}

	@Override
	public void writeToNBT(NBTTagCompound data) {
		super.writeToNBT(data);

	}

	@Override
	public void setPowerProvider(IPowerProvider provider) {}

	@Override
	public IPowerProvider getPowerProvider() {
		return getCore().myProvider;
	}

	@Override
	public void doWork() {}

	@Override
	public int powerRequest() {
		return getCore().powerRequest();
	}


}
