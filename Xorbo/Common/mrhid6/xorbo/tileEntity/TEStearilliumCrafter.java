package mrhid6.xorbo.tileEntity;

import java.util.HashMap;

import mrhid6.xorbo.Utils;
import mrhid6.xorbo.interfaces.IXorGridObj;
import net.minecraft.block.Block;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.item.ItemStack;

public class TEStearilliumCrafter extends TEMachineBase implements IXorGridObj{



	private int tempEng;
	protected static final HashMap recipes = new HashMap();

	public TEStearilliumCrafter() {
		this.inventory = new ItemStack[9];
		this.invName = "s.crafter";

		for(int i =0;i<inventory.length;i++){
			this.inventory[i]=new ItemStack(Block.dirt);
		}
		this.inventory[4]=null;
	}
	
	public int getSizeInventory(){
		return 9;
	}

	@Override
	public void updateEntity() {
		super.updateEntity();

		if(Utils.isClientWorld(worldObj))
			return;
		
		
		if((TickSinceUpdate  % 10) == 0){
			if(getGrid()==null){
				findGrid();

				if(getGrid()!=null){
					System.out.println("found Grid crafter"+(worldObj.isRemote));
					getGrid().addMachine(this);
				}else{
					System.out.println("im still null!"+(worldObj.isRemote));
				}
			}
			this.onInventoryChanged();
		}

		TickSinceUpdate++;
	}

	public void receiveGuiNetworkData(int i, int j){

		if(i==0){
			if(getGrid()!=null){
				//getGrid().setEnergyStored(j);
			}else{
				System.out.println("grid null!");
			}
		}

		if(i == 1){
			this.gridindex = j;
		}
	}

	public ItemStack getResultFor(ItemStack itemstack){
		ItemStack item = (ItemStack) recipes.get(itemstack.itemID);
		return (item==null)?null:item.copy();
	}

	public void sendGuiNetworkData(Container container, ICrafting iCrafting){
		if(getGrid()!=null && tempEng!=((int)getGrid().getEnergyStored())){
			iCrafting.sendProgressBarUpdate(container, 0, (int)getGrid().getEnergyStored());
			tempEng = (int)getGrid().getEnergyStored();
		}
		iCrafting.sendProgressBarUpdate(container, 1, this.gridindex);
	}

	@Override
	public int[] getSizeInventorySide(int var1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean func_102007_a(int i, ItemStack itemstack, int j) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean func_102008_b(int i, ItemStack itemstack, int j) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isInvNameLocalized() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isStackValidForSlot(int i, ItemStack itemstack) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void init() {
	}

	public void breakBlock() {
		if(getGrid()!=null){
			getGrid().removeMachine(this);
		}
	}

}
