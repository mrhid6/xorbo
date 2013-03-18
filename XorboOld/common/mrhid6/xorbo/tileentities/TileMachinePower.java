package mrhid6.xorbo.tileentities;

import cpw.mods.fml.relauncher.Side;
import mrhid6.xorbo.PowerProviderXor;
import mrhid6.xorbo.Utils;
import mrhid6.xorbo.network.IPacketXorHandler;
import mrhid6.xorbo.network.PacketTile;
import mrhid6.xorbo.network.PacketUtils;
import mrhid6.xorbo.network.Payload;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.packet.Packet;
import buildcraft.api.power.IPowerProvider;
import buildcraft.api.power.IPowerReceptor;
import buildcraft.api.power.PowerProvider;

public abstract class TileMachinePower extends TileEntityMachine implements IPacketXorHandler, IPowerReceptor
{
	private static int guiPacketId;
	public PowerProviderXor myProvider;
	protected static int descPacketId;

	public void doWork(){}

	public float getEnergy()
	{
		return this.myProvider.getEnergyStored();
	}
	
	@Override
	public Packet getDescriptionPacket() {
		Payload payload = new Payload(2, 0, 0, 3, 0);

	    payload.boolPayload[0] = this.isActive;
	    payload.boolPayload[1] = this.transmitpower;

		payload.floatPayload[0] = this.myProvider.getEnergyStored();
		payload.floatPayload[1] = this.processCur;
		payload.floatPayload[2] = this.processEnd;

		PacketTile packet = new PacketTile(descPacketId,this.xCoord,this.yCoord,this.zCoord,payload);
		return packet.getPacket();
	}
	
	@Override
	public void handleTilePacket(PacketTile packet) {

		this.isActive = packet.payload.boolPayload[0];
		this.transmitpower = packet.payload.boolPayload[0];

		this.myProvider.setEnergyStored(packet.payload.floatPayload[0]);
		this.processCur = packet.payload.floatPayload[1];
		this.processEnd = packet.payload.floatPayload[2];
		
		if (Utils.isClientWorld(this.worldObj)) {
			this.processCur = packet.payload.floatPayload[1];
			this.processEnd = packet.payload.floatPayload[2];
			this.myProvider.setEnergyStored(packet.payload.floatPayload[0]);
		}

		this.worldObj.markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);
		this.worldObj.updateAllLightTypes(this.xCoord, this.yCoord, this.zCoord);

		if (Utils.isServerWorld(this.worldObj))
			PacketUtils.sendToPlayers(getDescriptionPacket(), this.worldObj, this.xCoord, this.yCoord, this.zCoord, 192);

	}

	public abstract float getMaxEnergy();

	public float getPower()
	{
		if (!this.isActive)
		{
			return 0.0F;
		}

		int intPower = (int)(10.0F * this.myProvider.getEnergyStored() / 8 * getMaxEnergy() * 20);
		float power = intPower / 10.0F;

		if (power < 0.5F)
		{
			power = 0.5F;
		}

		if (power > 2)
		{
			power = 2;
		}

		return power;
	}

	public PowerProvider getPowerProvider()
	{
		return this.myProvider;
	}

	public int getScaledEnergyStored(int scale)
	{
		return Math.round(this.myProvider.getEnergyStored() * scale / this.myProvider.getMaxEnergyStored());
	}

	public int getScaledSpeed(int scale)
	{
		float power = this.myProvider.getEnergyStored() / 8 * this.myProvider.getMaxEnergyStored() / (10 * 2);

		if (power < 0.5F)
		{
			power = 0.5F;
		}

		if (power > 2)
		{
			power = 2;
		}

		return Math.round(power * scale / 2);
	}

	public int powerRequest()
	{
		if (this.myProvider.getEnergyStored() == this.myProvider.getMaxEnergyStored()) {
			return 0;
		}
		return (int)Math.ceil(Math.min(this.myProvider.getMaxEnergyReceived(), this.myProvider.getMaxEnergyStored() - this.myProvider.getEnergyStored()));
	}

	public void readFromNBT(NBTTagCompound nbttagcompound)
	{
		super.readFromNBT(nbttagcompound);
		this.myProvider.setEnergyStored(nbttagcompound.getFloat("energy.stored"));
	}

	public void setPowerProvider(IPowerProvider provider){}

	@Override
	public void updateEntity(){
		
		super.updateEntity();
		//System.out.println(getEnergy());
		
		if(this.worldObj.isRemote)
			return;
		
		boolean curActive = this.isActive;
		boolean needsInvUpdate = false;;

		if (this.isActive) {
			if (this.processCur < this.processEnd) {
				float energyToUse = getPower();
				float energy = this.myProvider.useEnergy(0.0F, energyToUse, true);
				this.processCur += energy;
			}
			if (canFinish()) {
				processFinish();
				this.myProvider.addEnergy(this.processCur % this.processEnd);
				this.myProvider.roundEnergyStored();
				this.processCur = 0.0F;
				this.processCur = 0.0F;

				if ((canStart())) {
					processStart();
					float energyToUse = getPower();
					float energy = this.myProvider.useEnergy(0.0F, energyToUse, true);
					this.processCur += energy;
				} else {
					this.processCur = 0.0F;
					this.isActive = false;
					this.wasActive = true;
				}
			}
		} else{
			if (canStart()) {
				processStart();
				float energyToUse = getPower();
				float energy = this.myProvider.useEnergy(0.0F, energyToUse, true);
				this.processCur += energy;
				this.isActive = true;
			}
		}
		if ((curActive != this.isActive) && (this.isActive == true)) {
			sendUpdatePacket(Side.CLIENT);
		} else if ((this.wasActive)) {
			this.wasActive = false;
			sendUpdatePacket(Side.CLIENT);
		}

	}

	public boolean hasEnergy(){
		return this.myProvider.getEnergyStored()>0;
	}

	public void writeToNBT(NBTTagCompound nbttagcompound)
	{
		super.writeToNBT(nbttagcompound);
		nbttagcompound.setFloat("energy.stored", this.myProvider.getEnergyStored());
	}
}
