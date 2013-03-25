package mrhid6.xorbo.tileEntity;

import java.util.HashMap;

import mrhid6.xorbo.Utils;
import mrhid6.xorbo.interfaces.IXorGridObj;
import mrhid6.xorbo.network.PacketUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.relauncher.Side;

public class TEZoroFurnace extends TEMachineBase implements IXorGridObj{

	protected static final HashMap recipes = new HashMap();
	public static int guiPacketId;
	
	public int tempEng = 0;

	public TEZoroFurnace(){
		this.inventory = new ItemStack[2];

		this.invName = "xor.furnace";
	}

	public static boolean setGuiPacketId(int id)
	{
		if (id == 0) {
			return false;
		}
		guiPacketId = id;
		return true;
	}

	public void breakBlock() {

		if(getGrid()!=null){
			getGrid().removeMachine(this);
		}
	}

	public ItemStack getResultFor(ItemStack itemstack){
		ItemStack item = (ItemStack) recipes.get(itemstack.itemID);
		return (item==null)?null:item.copy();
	}

	public int getSizeInventory(){
		return 2;
	}

	@Override
	public void updateEntity() {
		super.updateEntity();
		
		if(Utils.isClientWorld())
			return;
		
		if((TickSinceUpdate  % 10) == 0){

			if(getGrid()==null){
				findGrid();

				if(getGrid()!=null){
					System.out.println("found Grid zoroFurnace"+(worldObj.isRemote));
					getGrid().addMachine(this);
					sendUpdatePacket(Side.CLIENT);
				}else{
					System.out.println("im still null!"+(worldObj.isRemote));
					sendUpdatePacket(Side.CLIENT);
				}
			}
		}
		
		TickSinceUpdate++;
	}

	@Override
	public void init() {
		
	}

	public void receiveGuiNetworkData(int i, int j){
	}

	public void sendGuiNetworkData(Container container, ICrafting iCrafting)
	{
		if (((iCrafting instanceof EntityPlayer)) && 
				(Utils.isServerWorld()))
			PacketUtils.sendToPlayer((EntityPlayer)iCrafting, getDescriptionPacket());
	}

	@Override
	public int[] getSizeInventorySide(int var1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean func_102007_a(int i, ItemStack itemstack, int j) {
		return false;
	}

	@Override
	public boolean func_102008_b(int i, ItemStack itemstack, int j) {
		return false;
	}

	@Override
	public boolean isInvNameLocalized() {
		return false;
	}

	@Override
	public boolean isStackValidForSlot(int i, ItemStack itemstack) {
		return false;
	}
}
