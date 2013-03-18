package mrhid6.xorbo.tileentities.engine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import mrhid6.xorbo.PowerProviderXor;
import mrhid6.xorbo.Utils;
import mrhid6.xorbo.network.IPacketXorHandler;
import mrhid6.xorbo.network.PacketTile;
import mrhid6.xorbo.network.PacketUtils;
import mrhid6.xorbo.network.Payload;
import mrhid6.xorbo.tileentities.TileEntityBlock;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.packet.Packet;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import buildcraft.api.power.IPowerProvider;
import buildcraft.api.power.IPowerReceptor;
import cpw.mods.fml.relauncher.Side;

public abstract class TileEngineMain extends TileEntityBlock implements IPacketXorHandler, IPowerReceptor {
	protected static int descPacketId;
	boolean canCycle;
	public boolean canseesun = false;
	float cycleProgress;
	byte facing;
	float fuelMJ;
	float heat;
	public IPowerProvider[] inputsideproviders;
	public boolean[] inputsides;
	public boolean isActive;
	public boolean isShutdown;
	public PowerProviderXor myProvider;
	public String name;

	public Random rand = new Random();

	boolean stageCycle;
	public int ticker;

	public boolean canCycle() {
		this.canCycle = false;
		for (boolean side : inputsides) {
			if (side != false)
				this.canCycle = true;
		}
		if (this.canCycle == false && this.myProvider != null) {
			this.canCycle = this.myProvider.getEnergyStored() < this.myProvider
					.getMaxEnergyStored();
		}
		return this.canCycle;
	}

	public abstract boolean canProcess();

	public void checkSides() {
		for (int i = 0; i < 6; i++) {

			int[] coords = Utils.getAdjacentCoordinatesForSide(this.xCoord,
					this.yCoord, this.zCoord, i);
			TileEntity tile = this.worldObj.getBlockTileEntity(coords[0],
					coords[1], coords[2]);

			IPowerProvider provider = null;

			if (tile != null && (tile instanceof IPowerReceptor)
					&& !(tile instanceof TileEngineMain)) {
				provider = ((IPowerReceptor) tile).getPowerProvider();
			}

			inputsides[i] = provider != null;

			inputsideproviders[i] = provider;
		}
	}

	@Override
	public void doWork() {
	}

	public abstract boolean gainFuel();

	@Override
	public Packet getDescriptionPacket() {
		Payload payload = new Payload(4, 0, 0, 3, 0);

		payload.boolPayload[0] = canCycle();
		payload.boolPayload[1] = this.isActive;
		payload.boolPayload[2] = this.transmitpower;
		payload.boolPayload[3] = this.canseesun;

		payload.floatPayload[0] = this.fuelMJ;
		payload.floatPayload[1] = this.myProvider.getEnergyStored();
		payload.floatPayload[2] = this.heat;

		PacketTile packet = new PacketTile(descPacketId,this.xCoord,this.yCoord,this.zCoord,payload);
		return packet.getPacket();
	}

	public float getEnergy() {
		return this.myProvider.getEnergyStored();
	}

	@Override
	public int getInventoryStackLimit() {
		// TODO Auto-generated method stub
		return 64;
	}

	public abstract float getMaxPower();

	public abstract float getPower();

	@Override
	public IPowerProvider getPowerProvider() {
		return this.myProvider;
	}

	public int getScaledEnergyStored(int scale) {
		return (int) (this.myProvider.getEnergyStored() * scale / this.myProvider
				.getMaxEnergyStored());
	}

	public int getScaledSpeed(int scale) {
		return (int) (getPower() * scale / getMaxPower());
	}

	@Override
	public void handleTilePacket(PacketTile packet) {

		this.canCycle = packet.payload.boolPayload[0];
		this.isActive = packet.payload.boolPayload[1];
		this.transmitpower = packet.payload.boolPayload[2];
		this.canseesun = packet.payload.boolPayload[3];

		this.fuelMJ = packet.payload.floatPayload[0];
		this.myProvider.setEnergyStored(packet.payload.floatPayload[1]);
		this.heat = packet.payload.floatPayload[2];

		if (Utils.isClientWorld(this.worldObj)) {
			this.myProvider.setEnergyStored(packet.payload.floatPayload[1]);
		}

		this.worldObj.markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);
		this.worldObj.updateAllLightTypes(this.xCoord, this.yCoord, this.zCoord);

		if (Utils.isServerWorld(this.worldObj))
			PacketUtils.sendToPlayers(getDescriptionPacket(), this.worldObj, this.xCoord, this.yCoord, this.zCoord, 192);

	}

	public boolean isSunVisible(World world, int x, int y, int z) {
		if (this.worldObj != null && this.worldObj.isDaytime()) {
			if (!world.isRaining() && !world.isThundering()) {
				if (world.canBlockSeeTheSky(x, y, z))
					return true;
				else
					return false;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public boolean needsFuel() {
		return this.fuelMJ <= 0;
	}

	@Override
	public int powerRequest() {
		return 0;
	}

	public abstract boolean Process();

	@Override
	public void readFromNBT(NBTTagCompound data) {
		super.readFromNBT(data);

		this.transmitpower = data.getBoolean("transmitpower");

		try {
			this.myProvider.setEnergyStored(data.getFloat("energy"));

			if (Float.isNaN(this.myProvider.getEnergyStored())) {
				this.myProvider.setEnergyStored(0.0F);
			}
		} catch (Throwable e) {
			this.myProvider.setEnergyStored(0.0F);
		}

	}

	@Override
	public void setPowerProvider(IPowerProvider provider) {
	}

	protected void transferEnergy(int i) {


		int[] coords = Utils.getAdjacentCoordinatesForSide(this.xCoord,
				this.yCoord, this.zCoord, i);
		TileEntity tile = this.worldObj.getBlockTileEntity(coords[0], coords[1], coords[2]);


		if (tile != null && (tile instanceof IPowerReceptor) && this.myProvider != null) {

			IPowerProvider tilePP = ((IPowerReceptor)tile).getPowerProvider();
			if(tilePP!=null){
				float powerRequested = ((IPowerReceptor)tile).powerRequest();
				if (powerRequested > 0.0F)
				{
					float adjustedEnergyRequest = Math.min(powerRequested, tilePP.getMaxEnergyStored() - tilePP.getEnergyStored());
					float energyMax = Math.min(adjustedEnergyRequest, 50);
					float energy = this.myProvider.useEnergy(0.0F, energyMax, true);
					tilePP.receiveEnergy(energy, ForgeDirection.values()[i].getOpposite());
				}
			}
		}
	}

	protected void transferEnergyAllSides() {
		List<IPowerProvider> intList = new ArrayList<IPowerProvider>(
				Arrays.asList(inputsideproviders));
		Collections.shuffle(intList, rand);

		intList.toArray(inputsideproviders);

		for (int i = 0; i < 6; i++) {
			transferEnergy(i);
		}
	}

	@Override
	public void updateEntity() {
		super.updateEntity();
		if(this.worldObj.isRemote)
			return;

		checkSides();

		if (this.ticker++ % 128 == 0) {
			updateSeeSun();
		}
		boolean curactive = this.isActive;
		if (canCycle()) {
			if (canProcess()==true) {
				this.transferEnergyAllSides();
				this.isActive = true;
				Process();
			} else {
				this.isActive = false;
			}

		} else {
			this.isActive = false;
		}

		sendUpdatePacket(Side.CLIENT);
	}

	public void updateSeeSun() {
		this.canseesun = isSunVisible(this.worldObj, this.xCoord,
				this.yCoord + 1, this.zCoord);
	}

	@Override
	public void writeToNBT(NBTTagCompound data) {
		super.writeToNBT(data);

		data.setBoolean("transmitpower", this.transmitpower);
		data.setFloat("energy", this.myProvider.getEnergyStored());

	}

}
