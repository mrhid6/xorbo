package mrhid6.xorbo;

import java.util.ArrayList;
import mrhid6.xorbo.network.PacketGrid;
import mrhid6.xorbo.network.Payload;
import mrhid6.xorbo.tileEntity.TECableBase;
import mrhid6.xorbo.tileEntity.TEPoweredBase;
import mrhid6.xorbo.tileEntity.TETriniumConverter;
import mrhid6.xorbo.tileEntity.TEZoroChest;
import mrhid6.xorbo.tileEntity.TEZoroController;
import net.minecraft.network.packet.Packet;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;

public class GridPower {

	private ArrayList<TECableBase> cablesArray;
	private ArrayList<TETriniumConverter> converterArray;
	public int gridIndex;
	private ArrayList<TEPoweredBase> machineArray;
	public TEZoroController masterController;
	private float maxPower = 30000.0F;

	private float Power = 0.0F;
	private ArrayList<TEZoroChest> storageArray;

	public GridPower() {

		cablesArray = new ArrayList<TECableBase>();
		machineArray = new ArrayList<TEPoweredBase>();
		converterArray = new ArrayList<TETriniumConverter>();
		storageArray = new ArrayList<TEZoroChest>();

		Power = 0;

		gridIndex = GridManager.addGridToManager(this);
	}

	public void addCable( TECableBase te ) {
		cablesArray.remove(te);
		cablesArray.add(te);
	}

	public void addConverter( TETriniumConverter te ) {
		converterArray.remove(te);
		converterArray.add(te);
	}

	public void addEnergy( float f ) {
		Power += f;

		if (Power > getMaxEnergy()) {
			Power = getMaxEnergy();
		}

		for (TEPoweredBase te : machineArray) {

			te.sendUpdatePacket(Side.CLIENT);
		}

		SyncMachines();

	}

	public void addMachine( TEPoweredBase te ) {
		machineArray.remove(te);
		machineArray.add(te);

		te.gridindex = gridIndex;
	}

	public void addStorage( TEZoroChest te ) {
		storageArray.remove(te);
		storageArray.add(te);

		te.gridindex = gridIndex;
	}

	public void cleanup() {
		for (TEPoweredBase te1 : machineArray) {
			te1.gridindex = -1;
			te1.sendUpdatePacket(Side.CLIENT);
		}

		for (TETriniumConverter te1 : converterArray) {
			te1.gridindex = -1;
			te1.sendUpdatePacket(Side.CLIENT);
		}

		for (TECableBase te1 : cablesArray) {
			te1.gridindex = -1;
			te1.sendUpdatePacket(Side.CLIENT);
		}

		for (TEZoroChest te1 : storageArray) {
			te1.gridindex = -1;
			te1.sendUpdatePacket(Side.CLIENT);
		}

		converterArray.clear();
		machineArray.clear();
		cablesArray.clear();
		storageArray.clear();
	}

	public ArrayList<TECableBase> getCableObjs() {
		return cablesArray;
	}

	public Packet getDescriptionPacket() {
		Payload payload = new Payload(0, 0, 1, 1, 0);

		// System.out.println(gridindex);

		payload.intPayload[0] = gridIndex;

		payload.floatPayload[0] = getEnergyStored();

		PacketGrid packet = new PacketGrid(gridIndex, payload);
		return packet.getPacket();
	}

	public float getEnergyStored() {
		return Power;
	}

	public ArrayList<TEPoweredBase> getMachines() {
		return machineArray;
	}

	public float getMaxEnergy() {
		return maxPower;
	}

	public int getScaledEnergyStored( int scale ) {
		return Math.round(getEnergyStored() * scale / getMaxEnergy());

	}

	public boolean gridConfigured() {
		if (masterController != null) {
			return true;
		}
		return false;
	}

	public void handleTilePacket( PacketGrid packet ) {
		gridIndex = packet.payload.intPayload[0];
		setEnergyStored(packet.payload.floatPayload[0]);

		if (Utils.isClientWorld()) {
			gridIndex = packet.payload.intPayload[0];
			setEnergyStored(packet.payload.floatPayload[0]);
		}
	}

	public boolean hasCable( TECableBase te ) {
		for (TECableBase cable : cablesArray) {

			if (cable == te) {
				return true;
			}
		}
		return false;
	}

	public boolean hasController( World w, int x, int y, int z ) {

		TileEntity te1 = w.getBlockTileEntity(x, y, z);

		if (masterController != null && te1 != null && te1 instanceof TEZoroController) {
			if (masterController.xCoord == x && masterController.yCoord == y && masterController.zCoord == z) {
				return true;
			}
		}
		return false;
	}

	public boolean hasMachine( TEPoweredBase te ) {
		for (TEPoweredBase te1 : machineArray) {
			if (te.equals(te1)) {
				return true;
			}
		}
		return false;
	}

	public boolean hasPower() {
		return (Power > 0);
	}

	public void removeCable( TECableBase te ) {
		cablesArray.remove(te);
		cleanup();
	}

	public void removeController( World w, int x, int y, int z ) {
		masterController = null;
		cleanup();
	}

	public void removeConverter( TETriniumConverter te ) {
		converterArray.remove(te);
		cleanup();
	}

	public void removeMachine( TEPoweredBase te ) {
		machineArray.remove(te);

		for (TEPoweredBase te1 : machineArray) {
			te1.gridindex = -1;
		}

		machineArray.clear();
	}

	public void removeStorage( TEPoweredBase te ) {
		storageArray.remove(te);

		for (TEZoroChest te1 : storageArray) {
			te1.gridindex = -1;
		}

		storageArray.clear();
	}

	public void setController( TEZoroController te ) {
		masterController = te;
		te.gridindex = gridIndex;
	}

	public void setEnergyStored( float power ) {
		Power = power;

		if (Power > this.getMaxEnergy()) {
			Power = this.getMaxEnergy();
		}
	}

	public void subtractPower( float quantity ) {

		Power -= quantity;

		if (Power < 0.0F) {
			Power = 0.0F;
		}
	}

	public void SyncMachines() {
		for (TEPoweredBase te : machineArray) {

			te.TickSinceUpdate = 0;
		}
	}
}
