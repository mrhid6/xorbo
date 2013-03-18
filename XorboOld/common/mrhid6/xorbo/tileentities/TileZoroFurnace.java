package mrhid6.xorbo.tileentities;

import java.util.HashMap;
import java.util.Random;

import mrhid6.xorbo.PowerProviderXor;
import mrhid6.xorbo.Utils;
import mrhid6.xorbo.blocks.ModBlocks;
import mrhid6.xorbo.items.ModItems;
import mrhid6.xorbo.network.PacketUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.item.ItemStack;

public class TileZoroFurnace extends TileMachinePower
{
	protected static final HashMap recipes = new HashMap();

	public TileZoroFurnace()
	{
		this.inventory = new ItemStack[2];
		this.processInv = new ItemStack[1];
		
		this.invName = "xor.furnace";

		this.myProvider = new PowerProviderXor();
		this.myProvider.configure(25 * 2, (int)getMaxEnergy());
		
		recipes.put(ModItems.zoroIngot.itemID, new ItemStack(ModItems.refinedZoroIngot,1));
		recipes.put(ModBlocks.triniumOre.blockID, new ItemStack(ModItems.triniumIngot,1));
		
		recipes.put(ModItems.zoroDust.itemID, new ItemStack(ModItems.zoroIngot,1));
		recipes.put(ModItems.triniumDust.itemID, new ItemStack(ModItems.triniumIngot,1));
		
	}
	public float getMaxEnergy(){
		return 2400;
	}
	public static boolean setDescPacketId(int id){
		if (id == 0) {
			return false;
		}
		descPacketId = id;
		return true;
	}
	
	public void receiveGuiNetworkData(int i, int j)
	{
	}

	public void sendGuiNetworkData(Container container, ICrafting iCrafting)
	{
		if (((iCrafting instanceof EntityPlayer)) && 
				(Utils.isServerWorld(this.worldObj)))
			PacketUtils.sendToPlayer((EntityPlayer)iCrafting, getDescriptionPacket());
	}


	public boolean canStart()
	{
		if (this.inventory[0] == null) {
			return false;
		}
		int energy = 80;
		if (energy == 0) {
			energy = 160;
		}
		if (this.myProvider.getEnergyStored() < energy) {
			return false;
		}

		ItemStack output = getResultFor(this.inventory[0]);
		if (output == null) {
			return false;
		}
		if (this.inventory[1] == null) {
			return true;
		}
		if (!this.inventory[1].isItemEqual(output)) {
			return false;
		}
		int result = Integer.valueOf(this.inventory[1].stackSize) + Integer.valueOf(output.stackSize);
		return (result <= output.getMaxStackSize());
	}

	public boolean canFinish()
	{
		if (this.processCur < this.processEnd) {
			return false;
		}

		ItemStack output = getResultFor(this.processInv[0]);
		if (output == null) {
			this.processCur = 0.0F;
			this.isActive = false;
			return false;
		}
		if (this.inventory[1] == null) {
			return true;
		}
		if (!this.inventory[1].isItemEqual(output)) {
			return false;
		}
		int result = Integer.valueOf(this.inventory[1].stackSize) + Integer.valueOf(output.stackSize);
		return (result <= getInventoryStackLimit()) && (result <= output.getMaxStackSize());
	}

	protected void processStart()
	{
		this.processInv[0] = this.inventory[0].copy();
		
		this.processEnd = 80;
		if (this.processEnd == 0.0F) {
			this.processEnd = 160.0F;
		}

		this.inventory[0].stackSize -= 1;
		if (this.inventory[0].stackSize <= 0)
			this.inventory[0] = null;
	}

	protected void processFinish()
	{
		Random rand = new Random();
		if(rand.nextInt(10)<8){
			ItemStack output = getResultFor(this.processInv[0]);
			
			if(output==null)
				return;
			
			if (this.inventory[1] == null){
				this.inventory[1] = output;
			}else{
				this.inventory[1].stackSize += (Integer.valueOf(output.stackSize)>0)?1:Integer.valueOf(output.stackSize);
			}
		}
	}

	public ItemStack getResultFor(ItemStack itemstack)
	{
		ItemStack item = (ItemStack) recipes.get(itemstack.itemID);
		return (item==null)?null:item.copy();
	}

	public int getSizeInventory()
	{
		return 2;
	}
	
	
}
