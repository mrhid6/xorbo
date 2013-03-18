package mrhid6.xorbo.tileentities;

import mrhid6.xorbo.PowerProviderXor;
import mrhid6.xorbo.Utils;
import mrhid6.xorbo.network.IPacketXorHandler;
import mrhid6.xorbo.network.PacketTile;
import mrhid6.xorbo.network.PacketUtils;
import mrhid6.xorbo.network.Payload;
import mrhid6.xorbo.tileentities.engine.TileEngineMain;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.network.packet.Packet;
import buildcraft.api.power.IPowerProvider;
import buildcraft.api.power.IPowerReceptor;


public class TETriniumCore extends TileEngineMain implements IPacketXorHandler{

	public static boolean setDescPacketId(int id)
	{
		if (id == 0) {
			return false;
		}
		descPacketId = id;
		return true;
	}

	public TETriniumCore(){

		this.inventory = new ItemStack[0];
		inputsides = new boolean[6];
		inputsideproviders = new IPowerProvider[6];

		this.myProvider = new PowerProviderXor();
		this.myProvider.configure(20, (int)getMaxEnergy());
		this.invName = "xor.tcore";
	}

	@Override
	public boolean canProcess() {

		return this.transmitpower;
	}

	@Override
	public boolean gainFuel() {
		return false;
	}

	@Override
	public Packet getDescriptionPacket() {
		Payload payload = new Payload(1, 0, 0, 1, 0);

		payload.boolPayload[0] = this.transmitpower;

		payload.floatPayload[0] = this.myProvider.getEnergyStored();

		PacketTile packet = new PacketTile(descPacketId,this.xCoord,this.yCoord,this.zCoord,payload);
		return packet.getPacket();
	}

	public float getMaxEnergy(){
		return 100000;
	}

	@Override
	public float getMaxPower() {
		return 0;
	}

	@Override
	public float getPower() {
		// TODO Auto-generated method stub
		return 0;
	}

	public int getSizeInventory()
	{
		return 0;
	}

	@Override
	public void handleTilePacket(PacketTile packet) {
		this.transmitpower = packet.payload.boolPayload[0];

		if (Utils.isClientWorld(this.worldObj)) {
			this.myProvider.setEnergyStored(packet.payload.floatPayload[0]);
		}

		this.worldObj.markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);
		this.worldObj.updateAllLightTypes(this.xCoord, this.yCoord, this.zCoord);

		if (Utils.isServerWorld(this.worldObj))
			PacketUtils.sendToPlayers(getDescriptionPacket(), this.worldObj, this.xCoord, this.yCoord, this.zCoord, 192);

		//System.out.println(this.transmitpower);

	}

	public int powerRequest()
	{
		if (this.myProvider.getEnergyStored() == this.myProvider.getMaxEnergyStored()) {
			return 0;
		}
		return (int)Math.ceil(Math.min(this.myProvider.getMaxEnergyReceived(), this.myProvider.getMaxEnergyStored() - this.myProvider.getEnergyStored()));
	}

	@Override
	public boolean Process() {
		// TODO Auto-generated method stub
		return false;
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

	@Override
	public boolean func_94042_c() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean func_94041_b(int i, ItemStack itemstack) {
		// TODO Auto-generated method stub
		return false;
	}



}
