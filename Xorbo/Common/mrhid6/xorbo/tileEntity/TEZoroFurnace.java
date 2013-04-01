package mrhid6.xorbo.tileEntity;

import java.util.HashMap;
import mrhid6.xorbo.Utils;
import mrhid6.xorbo.interfaces.IXorGridObj;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.relauncher.Side;

public class TEZoroFurnace extends TEMachineBase implements IXorGridObj {

	public static int guiPacketId;
	protected static final HashMap recipes = new HashMap();

	public static boolean setGuiPacketId( int id ) {
		if (id == 0) {
			return false;
		}
		guiPacketId = id;
		return true;
	}

	public int tempEng = 0;

	public TEZoroFurnace() {
		inventory = new ItemStack[2];

		invName = "xor.furnace";
	}

	public void breakBlock() {

		if (getGrid() != null) {
			getGrid().removeMachine(this);
		}
	}

	@Override
	public boolean func_102007_a( int i, ItemStack itemstack, int j ) {
		return false;
	}

	@Override
	public boolean func_102008_b( int i, ItemStack itemstack, int j ) {
		return false;
	}

	public ItemStack getResultFor( ItemStack itemstack ) {
		ItemStack item = (ItemStack) recipes.get(itemstack.itemID);
		return (item == null) ? null : item.copy();
	}

	@Override
	public int getSizeInventory() {
		return 2;
	}

	@Override
	public int[] getSizeInventorySide( int var1 ) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void init() {

	}

	@Override
	public boolean isInvNameLocalized() {
		return false;
	}

	@Override
	public boolean isStackValidForSlot( int i, ItemStack itemstack ) {
		return false;
	}

	@Override
	public void updateEntity() {
		super.updateEntity();

		if (Utils.isClientWorld()) {
			return;
		}

		if ((TickSinceUpdate % 10) == 0) {

			if (getGrid() == null) {
				findGrid();

				if (getGrid() != null) {
					System.out.println("found Grid zoroFurnace" + (worldObj.isRemote));
					getGrid().addMachine(this);
					sendUpdatePacket(Side.CLIENT);
				} else {
					System.out.println("im still null!" + (worldObj.isRemote));
					sendUpdatePacket(Side.CLIENT);
				}
			}
		}

		TickSinceUpdate++;
	}
}
