package mrhid6.xorbo.gui;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

public class DummySlotOutput extends DummySlot {

	public DummySlotOutput( IInventory inv, int slot, int x, int y ) {
		super(inv, slot, x, y);
	}

	@Override
	public void slotClick( ItemStack stack, int button, boolean shift ) {
	}
}
