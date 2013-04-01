package mrhid6.xorbo.tileEntity;

import mrhid6.xorbo.InventoryUtils;
import mrhid6.xorbo.Utils;
import mrhid6.xorbo.interfaces.IXorGridObj;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
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

		for (int i = 0; i < inventory.length; i++) {
			inventory[i] = new ItemStack(Block.dirt);
		}
		inventory[4] = null;
	}

	public void breakBlock() {
		if (getGrid() != null) {
			getGrid().removeMachine(this);
		}
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
		for (ItemStack a : ing) {
			if (a != null) {
				System.out.println(a.getItemName() + ":" + a.stackSize);
			}
		}

		if (!hasEnoughItems(ing)) {
			return;
		}

		for (ItemStack a : ing) {
			if (a != null) {
				System.out.println(a.getItemName() + ":" + a.stackSize);
			}
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

		int itemsLeft = 0;

		for (ItemStack a : items) {
			if (a != null) {
				ItemStack var1 = InventoryUtils.copyStack(a, a.stackSize);
				itemsLeft += var1.stackSize;
			}
		}

		System.out.println(itemsLeft);

		for (int i = 10; i < 19; i++) {
			if (inventory[i] != null) {
				ItemStack var1 = InventoryUtils.copyStack(inventory[i], inventory[i].stackSize);

				for (int ia = 0; ia < 9; ia++) {

					if (items[ia] != null) {
						ItemStack var2 = InventoryUtils.copyStack(items[ia], items[ia].stackSize);

						if (var1.itemID == var2.itemID) {
							int amount = 0;
							if (var1.stackSize >= var2.stackSize) {
								amount = var2.stackSize;
								System.out.println(var2.stackSize);
								itemsLeft -= amount;
								break;
							} else {
								amount = var1.stackSize;
							}
							itemsLeft -= amount;
						}
					}
				}
			}
		}

		System.out.println(itemsLeft);
		return (itemsLeft == 0) ? true : false;
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
		if ((TickSinceUpdate % 10) == 0) {
			if (getGrid() == null) {
				findGrid();

				if (getGrid() != null) {
					update = true;
					System.out.println("found Grid crafter" + (worldObj.isRemote));
					getGrid().addMachine(this);
				} else {
					System.out.println("im still null!" + (worldObj.isRemote));
				}
			}
		}

		if (update) {
			sendUpdatePacket(Side.CLIENT);
		}

		TickSinceUpdate++;
	}

}
