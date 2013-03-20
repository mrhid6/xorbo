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

public class TEZoroController extends TEMachineBase implements IXorGridObj{
	
	private int TickSinceUpdate = 0;

	public TEZoroController(){
		
		this.inventory = new ItemStack[0];
	}
	
	public void breakBlock() {
		if(myGrid!=null){
			myGrid.removeController(this);
		}
	}

	@Override
	public void updateEntity() {
		super.updateEntity();

		if(Utils.isClientWorld(worldObj))
			return;

		if((TickSinceUpdate % 60) == 0){
			
			//if(myGrid!=null)
			//System.out.println(myGrid.getEnergyStored());
			//sendUpdatePacket(Side.CLIENT);
		}
		
		TickSinceUpdate++;
	}

	public void init(){
		
		findGridOnlyCable();
		
		if(myGrid==null){
			myGrid = new GridPower(worldObj);
			myGrid.addController(this);
			
			gridindex = myGrid.gridIndex;
		}else if(!myGrid.getControllers().contains(this)){
			myGrid.addController(this);
		}
	}

	@Override
	public int func_94127_c(int i) {
		return 0;
	}

	@Override
	public int func_94128_d(int i) {
		return 0;
	}
	
	public static boolean setDescPacketId(int id){
		if (id == 0) {
			return false;
		}
		descPacketId = id;
		return true;
	}
	
	public void receiveGuiNetworkData(int i, int j)
	{
	}

	public void sendGuiNetworkData(Container container, ICrafting iCrafting)
	{
		if (((iCrafting instanceof EntityPlayer)) && 
				(Utils.isServerWorld(this.worldObj)))
			PacketUtils.sendToPlayer((EntityPlayer)iCrafting, getDescriptionPacket());
	}

	@Override
	public boolean func_94042_c() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean func_94041_b(int i, ItemStack itemstack) {
		// TODO Auto-generated method stub
		return false;
	}


}
