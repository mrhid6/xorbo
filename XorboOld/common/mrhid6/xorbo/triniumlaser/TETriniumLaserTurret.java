package mrhid6.xorbo.triniumlaser;

import java.util.List;

import mrhid6.xorbo.Utils;
import mrhid6.xorbo.Xorbo;
import mrhid6.xorbo.network.IPacketXorHandler;
import mrhid6.xorbo.network.PacketTile;
import mrhid6.xorbo.network.PacketUtils;
import mrhid6.xorbo.network.Payload;
import mrhid6.xorbo.tileentities.TileEntityBlock;
import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.packet.Packet;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import cpw.mods.fml.relauncher.Side;

public class TETriniumLaserTurret extends TileEntityBlock implements IPacketXorHandler {
	private static int descPacketId;
	public float beamlength = 16.0F;
	public float yaw,pitch;

	public float maxyaw = 180,minyaw=-180;
	public float maxpitch = 35,minpitch=-90;

	private int deltaPitch = 1;

	boolean wasMoving = false;
	boolean isAtDefaultYaw = true;
	boolean isAtDefaultPitch = true;

	boolean rotatingToYaw = true;
	boolean rotatingToPitch = true;

	float prevPitch = 0F;
	float prevYaw = 0F;

	private int processCur = 0;
	private int processEnd = 1;

	private int digBlockCount=0;

	private Object beam1;

	private boolean jobDone = false;
	public float rotX = 0;
	public float rotZ = 0;
	public float vRadX = 0.0F;
	public float vRadZ = 0.0F;
	public float tRadX = 0.0F;
	public float tRadZ = 0.0F;
	public float mRadX = 0.0F;
	public float mRadZ = 0.0F;

	int paused = 100;
	int maxPause = 100;

	float speedX;

	int speedZ;

	private float tarZ = 90;

	private float tarX = 180;

	public TETriniumLaserTurret() {
		this.inventory = new ItemStack[0];
		this.invName = "trinium.Laser";
	}

	public boolean hasBase(){
		if(this.worldObj==null)
			return false;

		TileEntity tile = this.worldObj.getBlockTileEntity(xCoord, yCoord-1, zCoord);
		return (tile !=null && (tile instanceof TETriniumLaserBase));
	}

	public TETriniumLaserBase getBaseTile(){
		if(hasBase()){
			TileEntity tile = this.worldObj.getBlockTileEntity(xCoord, yCoord-1, zCoord);

			return (TETriniumLaserBase)tile;
		}
		return null;
	}

	public boolean isRedstonePowered(){
		if(!hasBase())
			return false;

		TETriniumLaserBase tile = (TETriniumLaserBase)this.worldObj.getBlockTileEntity(xCoord, yCoord-1, zCoord);

		return tile.isRedstonePowered();
	}

	public boolean canShowLaser(){
		return (hasBase() && isRedstonePowered() && !jobDone);
	}

	public boolean isMineable(int x, int y, int z){

		int blockId = this.worldObj.getBlockId(x,y,z);
		if(blockId!=0 && blockId!=7){
			TileEntity te = this.worldObj.getBlockTileEntity(x, y, z);

			if(te!=null){
				if(!(te instanceof TETriniumLaserTurret)){
					return false;
				}else if(!(te instanceof TETriniumLaserBase)){
					return false;
				}
			}else{
				return true;
			}
		}

		return false;
	}

	@Override
	public void updateEntity() {
		super.updateEntity();

		if(hasBase() && isRedstonePowered() && !jobDone){
			if(!worldObj.isRemote){
				
				this.vRadX *= 0.9F;
				this.vRadZ *= 0.9F;

				this.mRadX *= 0.9F;
				this.mRadZ *= 0.9F;
				
				if (this.rotX < this.tarX) {
					this.rotX += this.speedX;
					if (this.rotX < this.tarX) this.speedX += 1F; else this.speedX = ((int)(this.speedX / 3.0F));
				}
				else if (this.rotX > this.tarX) {
					this.rotX += this.speedX;
					if (this.rotX > this.tarX) this.speedX -= 1F; else this.speedX = ((int)(this.speedX / 3.0F)); 
				} else { this.speedX = 0; }


				if (this.rotZ < this.tarZ) {
					this.rotZ += this.speedZ;
					if (this.rotZ < this.tarZ) this.speedZ += 1F; else this.speedZ=((int)(this.speedZ / 3.0F));
				}
				else if (this.rotZ > this.tarZ) {
					this.rotZ += this.speedZ;
					if (this.rotZ > this.tarZ) this.speedZ -= 1F; else this.speedZ =((int)(this.speedZ / 3.0F)); 
				} else { this.speedZ = 0; }

				sendUpdatePacket(Side.CLIENT);
				//System.out.println("x:"+bx+"y:"+by+"z:"+bz);
			}else{

				float vx = this.rotX + 90 - this.vRadX;
				float vz = this.rotZ + 90 - this.vRadZ;

				float var3 = 1.0F;

				float dX = MathHelper.sin(vx / 180.0F * 3.141592653589793F) * MathHelper.cos(vz / 180.0F * 3.141592653589793F) * var3;
				float dZ = MathHelper.cos(vx / 180.0F * 3.141592653589793F) * MathHelper.cos(vz / 180.0F * 3.141592653589793F) * var3;
				float dY = MathHelper.sin(vz / 180.0F * 3.141592653589793F) * var3;

				Vec3 var13 = worldObj.getWorldVec3Pool().getVecFromPool(this.xCoord + 0.5D + dX, this.yCoord + 0.5D + dY, this.zCoord + 0.5D + dZ);
				Vec3 var14 = worldObj.getWorldVec3Pool().getVecFromPool(this.xCoord + 0.5D + dX * this.beamlength, this.yCoord + 0.5D + dY * this.beamlength, this.zCoord + 0.5D + dZ * this.beamlength);

				int impact = 0;
				double bx = var14.xCoord;
				double by = var14.yCoord;
				double bz = var14.zCoord;

				beam1 = Xorbo.proxy.InitBeam(worldObj,xCoord+0.5D,yCoord+0.5D,zCoord+0.5D,bx,by,bz,1,0x1c72ba,true,2.0F,this.beam1,impact);
			}
			rotate();
		}else{
			if(!isAtDefaultPitch || !isAtDefaultYaw){
				moveToDefaultYaw();
				moveToDefaultPitch();
			}else{
				wasMoving = false;
			}
		}
	}

	public void mineBlocks(){
		float vx = this.rotX + 90 - this.vRadX;
		float vz = this.rotZ + 90 - this.vRadZ;

		float var3 = -1.0F;

		float dX = MathHelper.sin(vx / 180.0F * 3.141592653589793F) * MathHelper.cos(vz / 180.0F * 3.141592653589793F) * var3;
		float dZ = MathHelper.cos(vx / 180.0F * 3.141592653589793F) * MathHelper.cos(vz / 180.0F * 3.141592653589793F) * var3;
		float dY = MathHelper.sin(vz / 180.0F * 3.141592653589793F) * var3;

		double bx = 0;
		double by = 0;
		double bz = 0;

		for(int i=0;i<=this.beamlength;i++){
			Vec3 var14 = worldObj.getWorldVec3Pool().getVecFromPool(this.xCoord + dX * i, this.yCoord + 0.5D + dY * -i, this.zCoord + dZ * i);

			bx = var14.xCoord;
			by = var14.yCoord;
			bz = var14.zCoord;
			if(isMineable((int)bx,(int)by,(int)bz)){
				int blockId = this.worldObj.getBlockId((int)bx,(int)by,(int)bz);
				Block block = Block.blocksList[blockId];

				float energy = block.getBlockHardness(worldObj, (int)bx,(int)by,(int)bz)*10;
				mineBlock( (int)bx,(int)by,(int)bz,blockId);
				if(getBaseTile()!=null && energy <= getBaseTile().getEnergy()){

					getBaseTile().myProvider.useEnergy(0.0F, energy, true);
				}

				break;
			}
		}
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
		worldObj.setBlockWithNotify(i, j, k, 0);
	}

	private void mineStack(ItemStack stack) {

		ItemStack added = addToBaseInventory(stack);
		stack.stackSize -= added.stackSize;

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

	private ItemStack addToBaseInventory(ItemStack stack) {
		ItemStack added = stack.copy();

		if(this.hasBase()){

			int injected = 0;

			int slot = -1;
			while ((slot = getPartialSlot(stack, slot + 1,getBaseTile().getSizeInventory())) >= 0 && injected < stack.stackSize) {
				injected += addToSlot(slot, stack, injected, true);
			}

			slot = 0;
			while ((slot = getEmptySlot(0,getBaseTile().getSizeInventory())) >= 0 && injected < stack.stackSize) {
				injected += addToSlot(slot, stack, injected, true);
			}
			getBaseTile().onInventoryChanged();

			added.stackSize=injected;
		}

		return added;
	}

	protected int addToSlot(int slot, ItemStack stack, int injected, boolean doAdd) {
		int available = stack.stackSize - injected;
		int max = Math.min(stack.getMaxStackSize(), getBaseTile().getInventoryStackLimit());

		ItemStack stackInSlot = getBaseTile().getStackInSlot(slot);
		if (stackInSlot == null) {
			int wanted = Math.min(available, max);
			if (doAdd) {
				stackInSlot = stack.copy();
				stackInSlot.stackSize = wanted;
				getBaseTile().setInventorySlotContents(slot, stackInSlot);
			}
			return wanted;
		}

		if (!stackInSlot.isItemEqual(stack) || !ItemStack.areItemStackTagsEqual(stackInSlot, stack))
			return 0;

		int wanted = max - stackInSlot.stackSize;
		if (wanted <= 0)
			return 0;

		if (wanted > available)
			wanted = available;

		if (doAdd) {
			stackInSlot.stackSize += wanted;
			getBaseTile().setInventorySlotContents(slot, stackInSlot);
		}
		return wanted;
	}

	protected int getPartialSlot(ItemStack stack, int startSlot, int endSlot) {

		for (int i = startSlot; i < endSlot; i++) {
			if (getBaseTile().getStackInSlot(i) == null) {
				continue;
			}

			if (!getBaseTile().getStackInSlot(i).isItemEqual(stack) || !ItemStack.areItemStackTagsEqual(getBaseTile().getStackInSlot(i), stack)) {
				continue;
			}

			if (getBaseTile().getStackInSlot(i).stackSize >= getBaseTile().getStackInSlot(i).getMaxStackSize()) {
				continue;
			}

			return i;
		}

		return -1;
	}

	protected int getEmptySlot(int startSlot, int endSlot) {
		for (int i = startSlot; i < endSlot; i++)
			if (getBaseTile().getStackInSlot(i) == null)
				return i;

		return -1;
	}

	public void CylinderDig(){
		if(canMove(1)){
			this.tarX+=1;
			doMovingEnergy(1);
		}

		mineBlocks();
		digBlockCount++;

		wasMoving = true;
		isAtDefaultYaw = false;
	}
	public boolean canMove(int movement){

		return (getBaseTile().getEnergy()>=movement);
	}
	public void doMovingEnergy(int movement){
		getBaseTile().myProvider.useEnergy(0.0F, movement, true);
	}

	public void DomeDig(){

		if(pitch<=minpitch)
			jobDone = true;

		if((digBlockCount % 1080) == 0 && digBlockCount>0){
			this.tarZ-=1F;
			isAtDefaultPitch = false;
		}

		CylinderDig();
		wasMoving = true;
		isAtDefaultYaw = false;
	}

	public void rotateYawTo(float newyaw){
		if(!rotatingToYaw)
			//return;

			this.prevYaw = yaw;

		if(newyaw<yaw)
			yaw-=1F;
		else
			yaw+=1F;

		if(yaw>maxyaw)
			yaw=minyaw;

		if(yaw<minyaw)
			yaw=maxyaw;

		if(yaw>=newyaw && yaw<=newyaw+2F){
			yaw=newyaw;
			rotatingToYaw = false;
		}else if(yaw>=newyaw-2F && yaw<=newyaw){
			yaw=newyaw;
			rotatingToYaw = false;
		}

		wasMoving = true;
		isAtDefaultYaw = false;

	}

	public void rotatePitchTo(float newpitch){
		if(!rotatingToPitch)
			//return;

			this.prevPitch = pitch;
		if(newpitch>maxpitch){
			newpitch = maxpitch;
		}
		if(newpitch<minpitch){
			newpitch = minpitch;
		}

		if(pitch >=minpitch && pitch<newpitch)
			pitch+=1F;

		if(pitch >=newpitch && pitch<maxpitch)
			pitch-=1F;


		if(pitch>=newpitch && pitch<=newpitch+2F){
			pitch=newpitch;
			rotatingToPitch = false;
		}else if(pitch>=newpitch-2F && pitch<=newpitch){
			pitch=newpitch;
			rotatingToPitch = false;
		}

		wasMoving = true;
		isAtDefaultPitch = false;

	}

	public void rotate(){
	}

	public void moveToDefaultYaw(){ 
		if(isAtDefaultYaw)
			return;

		if(yaw<0)
			yaw+=1F;
		else
			yaw-=1F;


		if(yaw<=3 && yaw>0)
			yaw+=0.1F;

		if(yaw>=-3 && yaw<0)
			yaw-=0.1F;

		if( (yaw%90)>=0 && (yaw%90)<1){
			isAtDefaultYaw=true;
		}
	}

	public void moveToDefaultPitch(){ 
		if(isAtDefaultPitch)
			return;
		if(pitch >=minpitch && pitch<0)
			pitch+=1F;

		if(pitch >=0 && pitch<maxpitch)
			pitch-=1F;

		if(pitch>=0 && pitch<=2F){
			pitch=0;
			isAtDefaultPitch=true;
		}else if(pitch>=-2F && pitch<=0){
			pitch=0;
			isAtDefaultPitch=true;
		}
	}

	public void readFromNBT(NBTTagCompound data)
	{
		super.readFromNBT(data);
		this.rotX = data.getFloat("rotX");
		this.rotZ = data.getFloat("rotZ");
		this.tarX = data.getFloat("tarX");
		this.tarZ = data.getFloat("tarZ");
		this.isAtDefaultYaw = data.getBoolean("yawdefault");
		this.isAtDefaultPitch = data.getBoolean("pitchdefault");
		this.jobDone = data.getBoolean("jobDone");
		this.digBlockCount = data.getInteger("digBlockCount");
	}

	public void writeToNBT(NBTTagCompound data)
	{
		super.writeToNBT(data);
		data.setFloat("rotX", this.rotX);
		data.setFloat("rotZ", this.rotZ);
		data.setFloat("tarX", this.tarX);
		data.setFloat("tarZ", this.tarZ);
		data.setBoolean("yawdefault", this.isAtDefaultYaw);
		data.setBoolean("pitchdefault", this.isAtDefaultPitch);
		data.setBoolean("jobDone", this.jobDone);
		data.setInteger("digBlockCount", this.digBlockCount);
	}


	@Override
	public void handleTilePacket(PacketTile packet) {

		this.isAtDefaultYaw = packet.payload.boolPayload[0];
		this.isAtDefaultPitch = packet.payload.boolPayload[1];
		this.jobDone = packet.payload.boolPayload[2];

		this.digBlockCount = packet.payload.intPayload[0];

		this.rotX = packet.payload.floatPayload[0];
		this.rotZ = packet.payload.floatPayload[1];
		this.vRadX = packet.payload.floatPayload[2];
		this.vRadZ = packet.payload.floatPayload[3];
		this.tarX = packet.payload.floatPayload[4];
		this.tarZ = packet.payload.floatPayload[5];

		this.worldObj.markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);
		this.worldObj.updateAllLightTypes(this.xCoord, this.yCoord, this.zCoord);

		if (Utils.isServerWorld(this.worldObj))
			PacketUtils.sendToPlayers(getDescriptionPacket(), this.worldObj, this.xCoord, this.yCoord, this.zCoord, 192);

	}

	@Override
	public Packet getDescriptionPacket() {
		Payload payload = new Payload(3, 0, 1, 6, 0);

		payload.boolPayload[0] = this.isAtDefaultYaw;
		payload.boolPayload[1] = this.isAtDefaultPitch;
		payload.boolPayload[2] = this.jobDone;

		payload.intPayload[0] = this.digBlockCount;

		payload.floatPayload[0] = this.rotX;
		payload.floatPayload[1] = this.rotZ;
		payload.floatPayload[2] = this.vRadX;
		payload.floatPayload[3] = this.vRadZ;
		payload.floatPayload[4] = this.tarX;
		payload.floatPayload[5] = this.tarZ;

		PacketTile packet = new PacketTile(descPacketId,this.xCoord,this.yCoord,this.zCoord,payload);
		return packet.getPacket();
	}

	public static boolean setDescPacketId(int id)
	{
		if (id == 0) {
			return false;
		}
		descPacketId = id;
		return true;
	}

	public int getInventoryStackLimit() {
		return 0;
	}

}
