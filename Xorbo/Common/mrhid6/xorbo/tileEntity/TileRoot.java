package mrhid6.xorbo.tileEntity;

import mrhid6.xorbo.Utils;
import mrhid6.xorbo.network.PacketUtils;
import net.minecraft.tileentity.TileEntity;
import cpw.mods.fml.relauncher.Side;

public abstract class TileRoot extends TileEntity{

	public boolean transmitpower = false;
	
	public void sendUpdatePacket(Side side){
		if ((Utils.isServerWorld(this.worldObj)) && (side == Side.CLIENT)) {
			PacketUtils.sendToPlayers(getDescriptionPacket(), this.worldObj, this.xCoord, this.yCoord, this.zCoord, 192);

			this.worldObj.markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);
			this.worldObj.updateAllLightTypes(this.xCoord, this.yCoord, this.zCoord);
		}
		else if ((Utils.isClientWorld(this.worldObj)) && (side == Side.SERVER)) {
			PacketUtils.sendToServer(getDescriptionPacket());
		}
	}

	public boolean setTransmitInfo(boolean disable){
		transmitpower = disable;
		sendUpdatePacket(Side.SERVER);
		return true;
	}
}
