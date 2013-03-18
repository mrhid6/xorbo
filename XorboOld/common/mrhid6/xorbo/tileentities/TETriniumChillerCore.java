package mrhid6.xorbo.tileentities;

import mrhid6.xorbo.PowerProviderXor;
import mrhid6.xorbo.Utils;
import mrhid6.xorbo.blocks.ModBlocks;
import mrhid6.xorbo.network.PacketTile;
import mrhid6.xorbo.network.PacketUtils;
import mrhid6.xorbo.network.Payload;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.packet.Packet;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.liquids.ILiquidTank;
import net.minecraftforge.liquids.ITankContainer;
import net.minecraftforge.liquids.LiquidStack;
import net.minecraftforge.liquids.LiquidTank;

public class TETriniumChillerCore extends TileMachinePower implements ITankContainer{

	public static boolean setDescPacketId(int id){
		if (id == 0) {
			return false;
		}
		descPacketId = id;
		return true;
	}

	public LiquidTank myTank = new LiquidTank(Block.waterStill.blockID, 0, 4000);

	public TETriniumChillerCore() {
		this.inventory = new ItemStack[1];
		this.processInv = new ItemStack[0];

		this.invName = "trin.chiller";

		this.myProvider = new PowerProviderXor();
		this.myProvider.configure(25 * 2, (int)getMaxEnergy());
	}

	@Override
	protected boolean canFinish() {
		if(this.processCur < this.processEnd){
			return false;
		}

		if(this.inventory[0]==null){
			return true;
		}

		ItemStack output = (new ItemStack(Block.ice)).copy();

		if(!this.inventory[0].isItemEqual(output)){
			return false;
		}

		int result = Integer.valueOf(this.inventory[0].stackSize) + Integer.valueOf(output.stackSize);
		return (result <= getInventoryStackLimit()) && (result <= output.getMaxStackSize());
	}

	@Override
	protected boolean canStart() {
		
		if(this.myProvider.getEnergyStored() < this.PowerNeeded()){
			return false;
		}

		if(this.myTank.getLiquid().amount < 1000){
			return false;
		}
		return true;
	}

	@Override
	public LiquidStack drain(ForgeDirection from, int maxDrain, boolean doDrain) {
		return null;
	}

	@Override
	public LiquidStack drain(int tankIndex, int maxDrain, boolean doDrain) {
		return null;
	}

	public int fill(ForgeDirection from, LiquidStack resource, boolean doFill)
	{
		return this.myTank.fill(resource, doFill);
	}

	public int fill(int tankIndex, LiquidStack resource, boolean doFill)
	{
		return 0;
	}

	@Override
	public Packet getDescriptionPacket() {
		Payload payload = new Payload(0, 0, 1, 0, 0);

		payload.intPayload[0] = this.myTank.getLiquid().amount;

		PacketTile packet = new PacketTile(descPacketId,this.xCoord,this.yCoord,this.zCoord,payload);
		return packet.getPacket();
	}

	@Override
	public float getMaxEnergy() {
		return 4000;
	}
	public int getSizeInventory()
	{
		return 1;
	}
	public ILiquidTank getTank(ForgeDirection direction, LiquidStack type)
	{
		if (direction == ForgeDirection.UNKNOWN) {
			System.out.println("nulltank1!");
			return null;
		}
		return this.myTank;
	}
	
	public ILiquidTank[] getTanks(ForgeDirection direction)
	{
		if (direction == ForgeDirection.UNKNOWN) {
			System.out.println("nulltank2!");
			return new ILiquidTank[0];
		}
		return new ILiquidTank[] { this.myTank };
	}
	
	@Override
	public void handleTilePacket(PacketTile packet) {

		this.myTank.getLiquid().amount = packet.payload.intPayload[0];

		this.worldObj.markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);
		this.worldObj.updateAllLightTypes(this.xCoord, this.yCoord, this.zCoord);

		if (Utils.isServerWorld(this.worldObj))
			PacketUtils.sendToPlayers(getDescriptionPacket(), this.worldObj, this.xCoord, this.yCoord, this.zCoord, 192);

	}

	public float PowerNeeded(){
		return 160F;
	}

	@Override
	protected void processFinish() {

		ItemStack output = (new ItemStack(Block.ice)).copy();

		if (this.inventory[0] == null)
			this.inventory[0] = output;
		else {
			this.inventory[0].stackSize += output.stackSize;
		}

	}

	@Override
	protected void processStart() {
		this.processEnd = this.PowerNeeded();

		myTank.getLiquid().amount -= 1000;

	}

	@Override
	public void readFromNBT(NBTTagCompound data) {
		super.readFromNBT(data);

		this.myTank.getLiquid().amount = data.getInteger("qty.water");
	}

	public void receiveGuiNetworkData(int i, int j)
	{
		
	}

	public void sendGuiNetworkData(Container container, ICrafting iCrafting)
	{
		super.sendGuiNetworkData(container, iCrafting);
	}

	@Override
	public void writeToNBT(NBTTagCompound data) {
		super.writeToNBT(data);
		data.setInteger("qty.water", this.myTank.getLiquid().amount);

	}


}
