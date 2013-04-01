package mrhid6.xorbo.tileEntity;

import mrhid6.xorbo.Config;
import mrhid6.xorbo.GridManager;
import mrhid6.xorbo.GridPower;
import mrhid6.xorbo.Utils;
import mrhid6.xorbo.interfaces.IConverterObj;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import cpw.mods.fml.relauncher.Side;

public class TETriniumConverter extends TEMachineBase implements IConverterObj {

	public boolean[] connections = new boolean[6];

	public TETriniumConverter() {
		inventory = new ItemStack[0];
	}

	public void breakBlock() {
		if (getGrid() != null) {
			getGrid().removeConverter(this);
		}
	}

	public boolean canConnectSide( int side ) {

		if (side == 1 || side == 0) {
			return false;
		}

		return true;
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

	@Override
	public int[] getSizeInventorySide( int var1 ) {
		// TODO Auto-generated method stub
		return null;
	}

	public void gridCheck() {

		// System.out.println("updateCheck!");
		for (int i = 0; i < 6; i++) {

			int x1 = xCoord + Config.SIDE_COORD_MOD[i][0];
			int y1 = yCoord + Config.SIDE_COORD_MOD[i][1];
			int z1 = zCoord + Config.SIDE_COORD_MOD[i][2];

			GridPower gridCheck = GridManager.getGridAt(x1, y1, z1, worldObj, i);

			if (getGrid() != null && gridCheck != null) {

				if (gridCheck.gridIndex < getGrid().gridIndex) {
					getGrid().removeMachine(this);
					gridindex = gridCheck.gridIndex;

					System.out.println("teconverter grid Was Changed to" + gridCheck.gridIndex);
				}
			}
		}
	}

	@Override
	public void init() {

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

	@Override
	public boolean isUseableByPlayer( EntityPlayer var1 ) {
		return false;
	}

	public void updateConnections() {

		for (int i = 0; i < 6; i++) {

			int[] coords = Config.getAdjacentCoordinatesForSide(xCoord, yCoord, zCoord, i);
			TileEntity te = worldObj.getBlockTileEntity(coords[0], coords[1], coords[2]);

			connections[i] = (te instanceof TECableBase);
		}
	}

	@Override
	public void updateEntity() {
		super.updateEntity();

		if (Utils.isClientWorld()) {
			return;
		}

		if (getGrid() == null) {
			findGrid();

			if (getGrid() != null) {
				System.out.println("found Grid triniumconverter" + getGrid().gridIndex);
				getGrid().addConverter(this);
			} else {
				System.out.println("im still null!" + (worldObj.isRemote));
			}
		}

		updateConnections();
		gridCheck();
		sendUpdatePacket(Side.CLIENT);
	}

}
