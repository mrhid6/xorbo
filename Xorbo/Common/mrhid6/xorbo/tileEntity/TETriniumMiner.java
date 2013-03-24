package mrhid6.xorbo.tileEntity;

import java.util.List;

import mrhid6.xorbo.Config;
import mrhid6.xorbo.Utils;
import mrhid6.xorbo.interfaces.IXorGridObj;
import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class TETriniumMiner extends TEMachineBase implements IXorGridObj{

	private int depth;
	private boolean doneMineing = false;
	public int tempEng = 0;


	public TETriniumMiner() {
		this.inventory = new ItemStack[2];

		this.invName = "xor.furnace";
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
	public void updateEntity() {
		super.updateEntity();

		if(Utils.isClientWorld(worldObj))
			return;

		if(doneMineing==true){
			//System.out.println("im done mining");
			return;
		}

		if((TickSinceUpdate%4)==0){
			if(getGrid()==null){
				findGrid();

				if(getGrid()!=null){
					System.out.println("found Grid zoroMiner"+(worldObj.isRemote));
					getGrid().addMachine(this);
				}else{
					//System.out.println("im still null!"+(worldObj.isRemote));
				}
			}


			if(getGrid()!=null && getGrid().getEnergyStored()>=30){
				mineLevel();
			}
		}

		TickSinceUpdate++;
	}

	@Override
	public void readFromNBT(NBTTagCompound data)
	{
		super.readFromNBT(data);

		this.gridindex = data.getInteger("grid.index");
		this.depth = data.getInteger("depth");
		this.doneMineing = data.getBoolean("donemining");

	}

	public void writeToNBT(NBTTagCompound data){
		super.writeToNBT(data);

		data.setInteger("grid.index",gridindex);
		data.setInteger("depth",depth);

		data.setBoolean("donemining", doneMineing);
	}

	@Override
	public void init() {
		depth = this.yCoord-1;
	}

	public void mineLevel(){

		if(minedLevel()){
			depth--;
		}

		int[][] coord_mod = {
				{-2,-2},{-1,-2},{0,-2},{1,-2},{2,-2},
				{-2,-1},{-1,-1},{0,-1},{1,-1},{2,-1},
				{-2, 0},{-1, 0},{0, 0},{1, 0},{2, 0},
				{-2, 1},{-1, 1},{0, 1},{1, 1},{2, 1},
				{-2, 2},{-1, 2},{0, 2},{1, 2},{2, 2},
		};
		int[] ar1 = Config.spiralArray(5);



		for(int i=0;i<ar1.length;i++){

			int coordid = -1+ar1[i];
			int[] coords = coord_mod[coordid];
			//System.out.println(ar1[i]);
			//System.out.println("("+coords[0]+","+coords[1]+")");
			int x = coords[0]+this.xCoord;
			int z = coords[1]+this.zCoord;

			if(depth==0){
				doneMineing = true;
				return;
			}
			if(shouldMineBlock(worldObj.getBlockId(x, depth, z))){
				if(getGrid()!=null && getGrid().getEnergyStored()>=30){
					getGrid().subtractPower(30);
					mineBlock(x,depth,z,worldObj.getBlockId(x, depth, z));
				}
				
				return;
			}

		}
	}

	public boolean shouldMineBlock(int blockID){

		if(blockID==0)return false;
		if(blockID==Block.bedrock.blockID)return false;
		if(blockID==Block.waterStill.blockID)return false;
		if(blockID==Block.waterMoving.blockID)return false;
		if(blockID==Block.lavaStill.blockID)return false;
		if(blockID==Block.lavaMoving.blockID)return false;

		return true;
	}

	public boolean minedLevel(){
		int[][] coord_mod = {
				{-2,-2},{-1,-2},{0,-2},{1,-2},{2,-2},
				{-2,-1},{-1,-1},{0,-1},{1,-1},{2,-1},
				{-2, 0},{-1, 0},{0, 0},{1, 0},{2, 0},
				{-2, 1},{-1, 1},{0, 1},{1, 1},{2, 1},
				{-2, 2},{-1, 2},{0, 2},{1, 2},{2, 2},
		};
		int[] ar1 = Config.spiralArray(5);

		for(int i=0;i<ar1.length;i++){

			int coordid = -1+ar1[i];
			int[] coords = coord_mod[coordid];
			//System.out.println(ar1[i]);
			//System.out.println("("+coords[0]+","+coords[1]+")");
			int x = coords[0]+this.xCoord;
			int z = coords[1]+this.zCoord;

			if(shouldMineBlock(worldObj.getBlockId(x, depth, z))){
				return false;
			}

		}

		return true;
	}

	public void mineBlock(int i,int j,int k,int blockId){
		List<ItemStack> stacks = Utils.getItemStackFromBlock(worldObj, i, j, k);

		if (stacks != null) {
			for (ItemStack s : stacks) {
				if (s != null) {
					mineStack(s);
				}
			}
		}

		worldObj.playAuxSFXAtEntity(null, 2001, i, j, k, blockId + (worldObj.getBlockMetadata(i, j, k) << 12));
		worldObj.setBlock(i, j, k, 0);
	}

	private void mineStack(ItemStack stack) {

		if (stack.stackSize > 0) {
			float f = worldObj.rand.nextFloat() * 0.8F + 0.1F;
			float f1 = worldObj.rand.nextFloat() * 0.8F + 0.1F;
			float f2 = worldObj.rand.nextFloat() * 0.8F + 0.1F;

			EntityItem entityitem = new EntityItem(worldObj, xCoord + f, yCoord + f1 + 0.5F, zCoord + f2, stack);

			entityitem.lifespan = 5200;
			entityitem.delayBeforeCanPickup = 10;

			float f3 = 0.05F;
			entityitem.motionX = (float) worldObj.rand.nextGaussian() * f3;
			entityitem.motionY = (float) worldObj.rand.nextGaussian() * f3 + 0.5F;
			entityitem.motionZ = (float) worldObj.rand.nextGaussian() * f3;
			worldObj.spawnEntityInWorld(entityitem);
		}
	}

	public void sendGuiNetworkData(Container container, ICrafting iCrafting){
		if(getGrid()!=null && tempEng!=((int)getGrid().getEnergyStored())){
			iCrafting.sendProgressBarUpdate(container, 0, (int)getGrid().getEnergyStored());
			tempEng = (int)getGrid().getEnergyStored();
		}
		iCrafting.sendProgressBarUpdate(container, 1, this.gridindex);
	}

	public void receiveGuiNetworkData(int i, int j){

		if(i==0){
			if(getGrid()!=null){
				getGrid().setEnergyStored(j);
			}else{
				System.out.println("grid null!");
			}
		}

		if(i == 1){
			this.gridindex = j;
		}
	}	

}
