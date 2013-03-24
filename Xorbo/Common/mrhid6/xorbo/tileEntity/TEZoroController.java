package mrhid6.xorbo.tileEntity;

import mrhid6.xorbo.GridManager;
import mrhid6.xorbo.GridPower;
import mrhid6.xorbo.Utils;
import mrhid6.xorbo.interfaces.IXorGridObj;
import mrhid6.xorbo.network.PacketUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class TEZoroController extends TEMachineBase implements IXorGridObj{

	private int tempEng;
	private boolean imMaster = false;

	public TEZoroController(){

		this.inventory = new ItemStack[0];
	}

	public void breakBlock() {
		if(getGrid()!=null){
			getGrid().removeController(xCoord,yCoord,zCoord);
		}
	}

	@Override
	public void updateEntity() {
		super.updateEntity();

		if(Utils.isClientWorld(worldObj))
			return;

		if((TickSinceUpdate % 5) == 0){

			if(getGrid()!=null){
				getGrid().addEnergy(50F);
				onInventoryChanged();
			}
		}

		TickSinceUpdate++;
	}

	public void init(){

		findGridOnlyCable();

		if(gridindex<0 || GridManager.getGrid(gridindex)==null){

			System.out.println("grid is null creating new one!");
			myGrid = new GridPower(worldObj);

			myGrid.addController(this);
			imMaster=true;

		}else if(getGrid()!=null && !getGrid().hasController(xCoord,yCoord,zCoord)){
			getGrid().addController(this);
			imMaster=false;
		}
	}

	public static boolean setDescPacketId(int id){
		if (id == 0) {
			return false;
		}
		descPacketId = id;
		return true;
	}

	public void receiveGuiNetworkData(int i, int j){

		if(i==0){
			if(getGrid()!=null && imMaster == true){
				
				getGrid().setEnergyStored(j);
			}else{
				System.out.println("grid null!");
			}
		}

		if(i == 1){
			this.gridindex = j;
		}
	}

	public void sendGuiNetworkData(Container container, ICrafting iCrafting){
		if(getGrid()!=null && tempEng!=((int)getGrid().getEnergyStored())){
			iCrafting.sendProgressBarUpdate(container, 0, (int)getGrid().getEnergyStored());
			tempEng = (int)getGrid().getEnergyStored();
		}
		iCrafting.sendProgressBarUpdate(container, 1, this.gridindex);
	}

	@Override
	public void readFromNBT(NBTTagCompound data)
	{
		super.readFromNBT(data);

		this.gridindex = data.getInteger("grid.index");
		if(getGrid()!=null && imMaster == true)
			getGrid().setEnergyStored(data.getFloat("grid.power"));

	}

	public void writeToNBT(NBTTagCompound data){
		super.writeToNBT(data);

		data.setInteger("grid.index",gridindex);

		if(getGrid()!=null && imMaster==true)
			data.setFloat("grid.power", getGrid().getEnergyStored());
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


}
