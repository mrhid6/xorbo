package mrhid6.xorbo.tileEntity;

import mrhid6.xorbo.Utils;
import mrhid6.xorbo.network.PacketUtils;
import net.minecraft.tileentity.TileEntity;
import cpw.mods.fml.relauncher.Side;

public abstract class TileRoot extends TileEntity {

	public boolean transmitpower = false;

	public void sendUpdatePacket( Side side ) {
		if ((Utils.isServerWorld()) && (side == Side.CLIENT)) {
			PacketUtils.sendToPlayers(getDescriptionPacket(), worldObj, xCoord, yCoord, zCoord, 192);

			worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
			worldObj.updateAllLightTypes(xCoord, yCoord, zCoord);
		} else if ((Utils.isClientWorld()) && (side == Side.SERVER)) {
			PacketUtils.sendToServer(getDescriptionPacket());
		}
	}

	public boolean setTransmitInfo( boolean disable ) {
		transmitpower = disable;
		sendUpdatePacket(Side.SERVER);
		return true;
	}
}
