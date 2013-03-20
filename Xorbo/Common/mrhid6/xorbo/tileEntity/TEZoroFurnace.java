package mrhid6.xorbo.tileEntity;

import java.util.HashMap;

import mrhid6.xorbo.GridManager;
import mrhid6.xorbo.GridPower;
import mrhid6.xorbo.Utils;
import mrhid6.xorbo.interfaces.IXorGridObj;
import mrhid6.xorbo.network.PacketTile;
import mrhid6.xorbo.network.PacketUtils;
import mrhid6.xorbo.network.Payload;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.network.packet.Packet;
import cpw.mods.fml.relauncher.Side;

public class TEZoroFurnace extends TEMachineBase implements IXorGridObj{

	private int TickSinceUpdate = 0;
	protected static final HashMap recipes = new HashMap();
	public static int guiPacketId;

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

	public Packet getGuiPacket()
	{
		Payload payload = new Payload(0, 0, 1, 3, 0);

		payload.intPayload[0] = this.gridindex;
		
		payload.floatPayload[0] = this.processCur;
		payload.floatPayload[1] = this.processEnd;
		if(getGrid()!=null)
			payload.floatPayload[2] = getGrid().getEnergyStored();
		

		PacketTile packet = new PacketTile(guiPacketId, this.xCoord, this.yCoord, this.zCoord, payload);
		return packet.getPacket();
	}

	public void handleTilePacket(PacketTile packet)
	{
		if (packet.getPacketId() == guiPacketId)
		{
			System.out.println("handling gui packet"+packet.getPacketId());

			this.processCur = packet.payload.floatPayload[0];
			this.processEnd = packet.payload.floatPayload[1];
			
			this.gridindex = packet.payload.intPayload[0];
			
			if(Utils.isServerWorld(worldObj)){
				GridPower grid = GridManager.getGrid(packet.payload.intPayload[0]);
				grid.setEnergyStored(packet.payload.floatPayload[2]);
			
			}
		} else {
			super.handleTilePacket(packet);
		}
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
		
		if(Utils.isClientWorld(worldObj))
			return;
		
		if((TickSinceUpdate  % 10) == 0){

			if(getGrid()==null){
				findGrid();

				if(getGrid()!=null){
					System.out.println("found Grid zoroFurnace"+(worldObj.isRemote));
					getGrid().addMachine(this);
				}else{
					System.out.println("im still null!"+(worldObj.isRemote));
				}
			}else{
				getGrid().addEnergy(10F);
				this.sendUpdatePacket(Side.CLIENT);
			}

			
		}

		TickSinceUpdate++;
	}

	@Override
	public void init() {
	}

	public void receiveGuiNetworkData(int i, int j){
	}

	public void sendGuiNetworkData(Container container, ICrafting iCrafting){
		if (((iCrafting instanceof EntityPlayer)) && 
				(Utils.isServerWorld(this.worldObj)))
			PacketUtils.sendToPlayer((EntityPlayer)iCrafting, getGuiPacket());
	}

	@Override
	public int func_94127_c(int i) {
		return 0;
	}

	@Override
	public int func_94128_d(int i) {
		return 0;
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
