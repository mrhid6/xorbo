package mrhid6.xorbo.tileEntity;

import mrhid6.xorbo.Config;
import mrhid6.xorbo.GridManager;
import mrhid6.xorbo.GridPower;
import mrhid6.xorbo.Utils;
import mrhid6.xorbo.block.ModBlocks;
import mrhid6.xorbo.interfaces.ITriniumObj;
import mrhid6.xorbo.interfaces.IXorGridObj;
import mrhid6.xorbo.network.PacketUtils;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import cpw.mods.fml.relauncher.Side;

public class TEZoroController extends TEMachineBase implements IXorGridObj {

	public static boolean setDescPacketId( int id ) {
		if (id == 0) {
			return false;
		}
		descPacketId = id;
		return true;
	}

	public boolean breakingblock = false;

	public TEZoroController() {

		inventory = new ItemStack[0];
	}

	public void breakBlock() {
		if (getGrid() != null) {
			getGrid().removeController(worldObj, xCoord, yCoord, zCoord);
		}
	}

	public void breakController() {

		if (breakingblock == true) {
			return;
		}

		breakingblock = true;

		worldObj.setBlock(xCoord, yCoord, zCoord, 0);
		EntityItem entityitem = new EntityItem(worldObj, xCoord, yCoord, zCoord, new ItemStack(ModBlocks.zoroController, 1));

		entityitem.lifespan = 5200;
		entityitem.delayBeforeCanPickup = 10;

		entityitem.motionX = 0;
		entityitem.motionY = 0;
		entityitem.motionZ = 0;
		worldObj.spawnEntityInWorld(entityitem);
	}

	public int cableCount() {
		int count = 0;
		for (int i = 0; i < 6; i++) {

			int x1 = xCoord + Config.SIDE_COORD_MOD[i][0];
			int y1 = yCoord + Config.SIDE_COORD_MOD[i][1];
			int z1 = zCoord + Config.SIDE_COORD_MOD[i][2];

			TileEntity te = worldObj.getBlockTileEntity(x1, y1, z1);

			if (te instanceof TECableBase) {
				count++;
			}
		}
		return count;
	}

	public boolean canBeAdjacent() {
		for (int i = 0; i < 6; i++) {

			int x1 = xCoord + Config.SIDE_COORD_MOD[i][0];
			int y1 = yCoord + Config.SIDE_COORD_MOD[i][1];
			int z1 = zCoord + Config.SIDE_COORD_MOD[i][2];

			TileEntity te = worldObj.getBlockTileEntity(x1, y1, z1);

			if (te instanceof TECableBase) {
				TECableBase cable = (TECableBase) te;

				if (cable.canInteractWith(this, i)) {
					return false;
				}
			}
			if (te instanceof TEPoweredBase) {
				return false;
			}
			if (te instanceof TETriniumConverter) {
				return false;
			}

		}

		return true;
	}

	public boolean canInteractWith( TileEntity te ) {

		if (te instanceof ITriniumObj) {
			return false;
		}
		if (te instanceof TECableBase) {
			return true;
		}
		if (te instanceof IXorGridObj) {
			return true;
		}

		return false;
	}

	@Override
	public boolean func_102007_a( int i, ItemStack itemstack, int j ) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean func_102008_b( int i, ItemStack itemstack, int j ) {
		// TODO Auto-generated method stub
		return false;
	}

	/*
	 * public void sendGuiNetworkData(Container container, ICrafting iCrafting){
	 * if(getGrid()!=null && tempEng!=((int)getGrid().getEnergyStored())){
	 * iCrafting.sendProgressBarUpdate(container, 0,
	 * (int)getGrid().getEnergyStored()); tempEng =
	 * (int)getGrid().getEnergyStored(); }
	 * iCrafting.sendProgressBarUpdate(container, 1, this.gridindex); }
	 */

	@Override
	public int[] getSizeInventorySide( int var1 ) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean gridCheck() {
		for (int i = 0; i < 6; i++) {

			int x1 = xCoord + Config.SIDE_COORD_MOD[i][0];
			int y1 = yCoord + Config.SIDE_COORD_MOD[i][1];
			int z1 = zCoord + Config.SIDE_COORD_MOD[i][2];

			GridPower gridCheck = GridManager.getGridAt(x1, y1, z1, worldObj, i);

			if (myGrid != null && gridCheck != null) {

				if (gridCheck.gridIndex < myGrid.gridIndex && !(gridCheck.gridIndex == -1)) {
					if (!canBeAdjacent()) {
						myGrid.removeController(worldObj, xCoord, yCoord, zCoord);
						breakController();
						return true;
					}
				}
			}
		}
		return false;
	}

	@Override
	public void init() {

		findGridOnlyCable();

		if (gridindex < 0 || GridManager.getGrid(gridindex) == null) {

			System.out.println("grid is null creating new one!");
			myGrid = new GridPower();

			myGrid.setController(this);

		} else if (getGrid() != null && !getGrid().hasController(worldObj, xCoord, yCoord, zCoord)) {

			if (!canBeAdjacent()) {
				breakController();
			}
		}
	}

	@Override
	public boolean isInvNameLocalized() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isStackValidForSlot( int i, ItemStack itemstack ) {
		// TODO Auto-generated method stub
		return false;
	}

	public void onNeighborBlockChange() {

		// updateGrid();
	}

	@Override
	public void readFromNBT( NBTTagCompound data ) {
		super.readFromNBT(data);

		gridindex = data.getInteger("grid.index");
		if (getGrid() != null) {
			getGrid().setEnergyStored(data.getFloat("grid.power"));
		}

	}

	@Override
	public void receiveGuiNetworkData( int i, int j ) {

	}

	@Override
	public void sendGuiNetworkData( Container container, ICrafting iCrafting ) {
		if (((iCrafting instanceof EntityPlayer)) && (Utils.isServerWorld())) {
			PacketUtils.sendToPlayer((EntityPlayer) iCrafting, getDescriptionPacket());
		}
	}

	@Override
	public void updateEntity() {
		super.updateEntity();

		if (Utils.isClientWorld()) {
			return;
		}

		boolean update = false;
		boolean updateGridCheck = false;

		if ((TickSinceUpdate % 5) == 0) {

			if (getGrid() != null) {
				getGrid().addEnergy(50F);
				update = true;
			}
			updateGridCheck = gridCheck();
		}

		if (update || updateGridCheck) {
			GridManager.sendUpdatePacket(Side.CLIENT, worldObj, xCoord, yCoord, zCoord, gridindex);
			sendUpdatePacket(Side.CLIENT);
		}

		TickSinceUpdate++;
	}

	@Override
	public void writeToNBT( NBTTagCompound data ) {
		super.writeToNBT(data);

		data.setInteger("grid.index", gridindex);

		if (getGrid() != null) {
			data.setFloat("grid.power", getGrid().getEnergyStored());
		}
	}

}
