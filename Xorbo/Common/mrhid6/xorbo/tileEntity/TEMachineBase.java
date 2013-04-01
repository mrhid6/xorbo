package mrhid6.xorbo.tileEntity;

import mrhid6.xorbo.Utils;
import mrhid6.xorbo.interfaces.IPacketXorHandler;
import mrhid6.xorbo.network.PacketTile;
import mrhid6.xorbo.network.PacketUtils;
import mrhid6.xorbo.network.Payload;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.packet.Packet;

public abstract class TEMachineBase extends TEBlock implements ISidedInventory,
		IPacketXorHandler {

	protected static int descPacketId;

	public static boolean setDescPacketId( int id ) {
		if (id == 0) {
			return false;
		}
		descPacketId = id;
		return true;
	}

	byte facing;
	protected float processCur;
	protected float processEnd;
	public ItemStack[] processInv;

	public boolean wasActive;

	public TEMachineBase() {

	}

	@Override
	public void closeChest() {
	}

	@Override
	public Packet getDescriptionPacket() {
		Payload payload = new Payload(2, 0, 1, 2, 0);

		payload.boolPayload[0] = isActive;
		payload.boolPayload[1] = transmitpower;

		payload.intPayload[0] = gridindex;

		payload.floatPayload[0] = processCur;
		payload.floatPayload[1] = processEnd;

		PacketTile packet = new PacketTile(descPacketId, xCoord, yCoord,
				zCoord, payload);
		return packet.getPacket();
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	public int getScaledProgress( int scale ) {
		if (processEnd == 0.0F) {
			return 0;
		}
		return (int) (processCur * scale / processEnd);
	}

	@Override
	public void handleTilePacket( PacketTile packet ) {

		isActive = packet.payload.boolPayload[0];
		transmitpower = packet.payload.boolPayload[0];

		processCur = packet.payload.floatPayload[0];
		processEnd = packet.payload.floatPayload[1];

		gridindex = packet.payload.intPayload[0];

		if (Utils.isClientWorld()) {
			processCur = packet.payload.floatPayload[0];
			processEnd = packet.payload.floatPayload[1];
			gridindex = packet.payload.intPayload[0];
			// System.out.println(gridindex);
		}

		worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
		worldObj.updateAllLightTypes(xCoord, yCoord, zCoord);

		if (Utils.isServerWorld()) {
			PacketUtils.sendToPlayers(getDescriptionPacket(), worldObj, xCoord,
					yCoord, zCoord, 192);
		}

	}

	@Override
	public boolean isUseableByPlayer( EntityPlayer var1 ) {
		if (worldObj.getBlockTileEntity(xCoord, yCoord, zCoord) != this) {
			return false;
		}

		return var1.getDistanceSq(xCoord + 0.5D, yCoord + 0.5D, zCoord + 0.5D) <= 64;
	}

	@Override
	public void openChest() {
	}

	@Override
	public void readFromNBT( NBTTagCompound data ) {
		super.readFromNBT(data);
		processCur = data.getFloat("process.cur");
		processEnd = data.getFloat("process.end");

	}

	public void receiveGuiNetworkData( int i, int j ) {
	}

	public void sendGuiNetworkData( Container container, ICrafting iCrafting ) {
		if (((iCrafting instanceof EntityPlayer)) && (Utils.isServerWorld())) {
			PacketUtils.sendToPlayer((EntityPlayer) iCrafting,
					getDescriptionPacket());
		}
	}

	@Override
	public void writeToNBT( NBTTagCompound data ) {
		super.writeToNBT(data);

		data.setFloat("process.cur", processCur);
		data.setFloat("process.end", processEnd);
	}
}
