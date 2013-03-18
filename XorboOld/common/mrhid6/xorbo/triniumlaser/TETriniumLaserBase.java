package mrhid6.xorbo.triniumlaser;

import java.util.HashMap;

import mrhid6.xorbo.PowerProviderXor;
import mrhid6.xorbo.Utils;
import mrhid6.xorbo.network.PacketUtils;
import mrhid6.xorbo.tileentities.TileMachinePower;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class TETriniumLaserBase extends TileMachinePower
{
	protected static final HashMap recipes = new HashMap();
	private boolean isRedstonePowered;

	public TETriniumLaserBase()
	{
		this.inventory = new ItemStack[18];
		this.processInv = new ItemStack[0];
		
		this.invName = "xor.laser.base";

		this.myProvider = new PowerProviderXor();
		this.myProvider.configure(1, (int)getMaxEnergy());
		
	}
	public boolean hasTurret(){
		if(this.worldObj==null)
			return false;

		TileEntity tile = this.worldObj.getBlockTileEntity(xCoord, yCoord+1, zCoord);
		return (tile !=null && (tile instanceof TETriniumLaserTurret));
	}
	

	public TETriniumLaserTurret getTurretTile(){
		if(hasTurret()){
			TileEntity tile = this.worldObj.getBlockTileEntity(xCoord, yCoord+1, zCoord);

			return (TETriniumLaserTurret)tile;
		}
		return null;
	}
	
	@Override
	public void updateEntity() {
		super.updateEntity();
		
		checkRedstonePower();
	}
	
	public static boolean setDescPacketId(int id){
		if (id == 0) {
			return false;
		}
		descPacketId = id;
		return true;
	}
	
	public void writeToNBT(NBTTagCompound data){
		super.writeToNBT(data);
		
	}
	public void readFromNBT(NBTTagCompound data)
	{
		super.readFromNBT(data);
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
	
	public ItemStack getResultFor(ItemStack itemstack)
	{
		ItemStack item = (ItemStack) recipes.get(itemstack.itemID);
		return (item==null)?null:item.copy();
	}

	public int getSizeInventory()
	{
		return 18;
	}

	
	public void checkRedstonePower() {
		isRedstonePowered = worldObj.isBlockIndirectlyGettingPowered(xCoord, yCoord, zCoord);
	}
	
	public boolean isRedstonePowered(){
		return isRedstonePowered;
	}
	@Override
	public int getInventoryStackLimit() {
		return 64;
	}
	
	public float getMaxEnergy(){
		return 8000;
	}

	@Override
	protected boolean canStart() {
		return false;
	}


	@Override
	protected boolean canFinish() {
		return false;
	}


	@Override
	protected void processStart() {
		
	}


	@Override
	protected void processFinish() {
	}
	
}
