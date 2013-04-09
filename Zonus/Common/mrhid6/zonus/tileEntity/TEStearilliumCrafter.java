package mrhid6.zonus.tileEntity;

import mrhid6.zonus.InventoryUtils;
import mrhid6.zonus.Utils;
import mrhid6.zonus.interfaces.IConverterObj;
import mrhid6.zonus.interfaces.ITriniumObj;
import mrhid6.zonus.interfaces.IXorGridObj;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.tileentity.TileEntity;
import cpw.mods.fml.relauncher.Side;

public class TEStearilliumCrafter extends TEMachineBase implements IXorGridObj {

	class LocalInventoryCrafting extends InventoryCrafting {

		public LocalInventoryCrafting() {
			super(new Container(){

				@SuppressWarnings("all")
				public boolean canInteractWith( EntityPlayer entityplayer ) {
					// TODO Auto-generated method stub
					return false;
				}

				@SuppressWarnings("all")
				public boolean isUsableByPlayer( EntityPlayer entityplayer ) {
					return false;
				}
			}, 3, 3);
			// TODO Auto-generated constructor stub
		}

	}

	private IRecipe currentRecipe = null;

	public TEStearilliumCrafter() {
		inventory = new ItemStack[19];
		invName = "s.crafter";

	}

	public void breakBlock() {
		if (getGrid() != null) {
			getGrid().removeMachine(this);
		}
	}

	@Override
	public boolean canConnectThrough() {
		return true;
	}

	public boolean canCraft( ItemStack stack ) {
		for (int i = 10; i < getSizeInventory(); i++) {
			if (inventory[i] == null) {
				return true;
			}

			if (inventory[i].itemID == stack.itemID) {
				ItemStack var2 = InventoryUtils.copyStack(inventory[i], inventory[i].stackSize);

				if ((var2.stackSize + 1) <= var2.getMaxStackSize()) {
					return true;
				}

			}
		}
		return false;
	}

	@Override
	public boolean canInteractWith( TileEntity te ) {
		if (te instanceof IConverterObj) {
			return false;
		}
		if (te instanceof ITriniumObj) {
			return false;
		}

		return true;
	}

	private int countMatchingStacks( IInventory inv, int fslot, int lslot, ItemStack filter ) {
		int c = 0;
		for (int k = fslot; k < lslot; k++) {
			ItemStack stack = inv.getStackInSlot(k);
			if (stack != null && InventoryUtils.canStack(filter, stack)) {
				c += stack.stackSize;
			}
		}
		return c;
	}

	public void craft() {

		ItemStack[] ing = new ItemStack[9];

		for (int i = 0; i < 9; i++) {
			if (inventory[i] != null) {
				ItemStack var1 = InventoryUtils.copyStack(inventory[i], 1);

				for (int ia = 0; ia < 9; ia++) {

					if (ing[ia] != null) {
						ItemStack var2 = InventoryUtils.copyStack(ing[ia], 1);

						if (var1.itemID == var2.itemID) {
							ing[ia].stackSize++;
							break;
						}
					} else {
						ing[ia] = InventoryUtils.copyStack(var1, 1);
						break;
					}
				}
			}
		}

		if (!hasEnoughItems(ing)) {
			return;
		}

		for (int i = 10; i < 19; i++) {
			if (inventory[i] != null) {
				ItemStack var1 = InventoryUtils.copyStack(inventory[i], inventory[i].stackSize);

				for (int ia = 0; ia < 9; ia++) {

					if (ing[ia] != null) {
						ItemStack var2 = InventoryUtils.copyStack(ing[ia], ing[ia].stackSize);

						if (var1.itemID == var2.itemID) {

							if (var1.stackSize >= var2.stackSize) {
								this.decrStackSize(i, var2.stackSize);
								ing[ia].stackSize = 0;
								onInventoryChanged();
								// sendUpdatePacket(Side.CLIENT);
							} else {
								this.decrStackSize(i, 1);
								ing[ia].stackSize -= var1.stackSize;
								onInventoryChanged();
							}

						}
					}
				}
			}
		}

		outputItem();

		for (ItemStack a : ing) {
			// if(a!=null)
			// System.out.println(a.getItemName()+":"+a.stackSize);
		}
	}

	public void decreaseRightinv( ItemStack[] items ) {

		for (int i = 10; i < getSizeInventory(); i++) {
			ItemStack var1 = inventory[i];

			if (var1 != null) {
				for (int i1 = 0; i1 < items.length; i1++) {

					if (items[i1] != null && var1.itemID == items[i1].itemID) {

						if (var1.stackSize == items[i1].stackSize) {
							inventory[i] = null;
							this.setInventorySlotContents(i, null);
							this.onInventoryChanged();
							this.sendUpdatePacket(Side.CLIENT);

						} else if (var1.stackSize > items[i1].stackSize) {
							inventory[i].stackSize -= items[i1].stackSize;

							this.onInventoryChanged();
							this.sendUpdatePacket(Side.CLIENT);
						}
					}
				}
			}
		}
	}

	public ItemStack findRecipe() {
		InventoryCrafting craftMatrix = new LocalInventoryCrafting();

		for (int i = 0; i < 9; ++i) {
			ItemStack stack = getStackInSlot(i);

			craftMatrix.setInventorySlotContents(i, stack);
		}

		if (currentRecipe == null || !currentRecipe.matches(craftMatrix, worldObj)) {
			currentRecipe = InventoryUtils.findMatchingRecipe(craftMatrix, worldObj);
		}

		if (currentRecipe != null) {
			return currentRecipe.getCraftingResult(craftMatrix);
		}
		return null;
	}

	public boolean foundController() {

		if (getGrid() != null) {
			return getGrid().hasMachine(this);
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

	@Override
	public int getSizeInventory() {
		return 19;
	}

	@Override
	public int[] getSizeInventorySide( int var1 ) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean hasEnoughItems( ItemStack[] items ) {

		boolean[] itemcount = new boolean[items.length];

		for (int i = 0; i < items.length; i++) {
			if (items[i] != null) {
				itemcount[i] = (countMatchingStacks(this, 10, 19, items[i]) >= items[i].stackSize);
				// System.out.println(items[i].getItemName() +
				// items[i].stackSize);
			}
		}

		/*
		 * int itemsLeft = 0;
		 * 
		 * for (ItemStack a : items) { if (a != null) { ItemStack var1 =
		 * InventoryUtils.copyStack(a, a.stackSize); itemsLeft +=
		 * var1.stackSize; } }
		 * 
		 * System.out.println(itemsLeft);
		 * 
		 * for (int i = 10; i < 19; i++) { if (inventory[i] != null) { ItemStack
		 * var1 = InventoryUtils.copyStack(inventory[i],
		 * inventory[i].stackSize);
		 * 
		 * for (int ia = 0; ia < 9; ia++) {
		 * 
		 * if (items[ia] != null) { ItemStack var2 =
		 * InventoryUtils.copyStack(items[ia], items[ia].stackSize);
		 * 
		 * if (var1.itemID == var2.itemID) { int amount = 0; if (var1.stackSize
		 * >= var2.stackSize) { amount = var2.stackSize;
		 * System.out.println(var2.stackSize); itemsLeft -= amount; break; }
		 * else { amount = var1.stackSize; } itemsLeft -= amount; } } } } }
		 */
		boolean res = true;
		for (int i = 0; i < items.length; i++) {

			if (itemcount[i] == false && items[i] != null) {
				res = false;
			}
		}

		return res;
	}

	@Override
	public void init() {
	}

	public void instataCraft() {

		ItemStack stack = findRecipe();
		if (stack != null) {
			if (canCraft(stack)) {

				craft();
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

	public void outputItem() {

		ItemStack stack = InventoryUtils.copyStack(findRecipe(), 1);

		for (int i = 10; i < getSizeInventory(); i++) {
			if (inventory[i] == null) {
				this.setInventorySlotContents(i, stack);
				return;
			}

			if (inventory[i].itemID == stack.itemID) {
				ItemStack var2 = InventoryUtils.copyStack(inventory[i], inventory[i].stackSize);

				if ((var2.stackSize + 1) <= var2.getMaxStackSize()) {
					stack.stackSize += var2.stackSize;
					this.setInventorySlotContents(i, stack);
					onInventoryChanged();
					sendUpdatePacket(Side.CLIENT);
					return;
				}

			}
		}
	}

	public void putActiveRecipeInOutput() {
		ItemStack stack = findRecipe();
		if (stack != null) {
			inventory[9] = InventoryUtils.copyStack(stack, 1);
		} else {
			inventory[9] = null;
		}
	}

	@Override
	public void setInventorySlotContents( int i, ItemStack stack ) {
		inventory[i] = stack;
	}

	@Override
	public void updateEntity() {
		super.updateEntity();

		if (Utils.isClientWorld()) {
			return;
		}

		boolean update = false;
		putActiveRecipeInOutput();
		if (TickSinceUpdate % 2 == 0) {
			if (!foundController()) {
				if (getGrid() != null) {
					getGrid().removeMachine(this);
				}
				gridindex = -1;
				sendUpdatePacket(Side.CLIENT);
			}

		}
		instataCraft();

		TickSinceUpdate++;
	}
}
