package mrhid6.xorbo.network;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;

public class PacketHandler implements IPacketHandler{
	
	private static int packetIdCounter = 0;
	@Override
	public void onPacketData(INetworkManager manager, Packet250CustomPayload packet, Player player) {
		DataInputStream data = new DataInputStream(new ByteArrayInputStream(packet.data));
		try {
			int id = data.read();
			int type = data.read();
			if (type == 0) {
				PacketTile tilePacket = new PacketTile(id, (player instanceof EntityPlayerMP) ? (EntityPlayerMP)player : null);
				tilePacket.readData(data);

				World world = ((EntityPlayer)player).worldObj;
				if (!tilePacket.targetExists(world)) {
					return;
				}
				TileEntity tile = tilePacket.getTarget(world);
				if (!(tile instanceof IPacketXorHandler)) {

					return;
				}

				((IPacketXorHandler)tile).handleTilePacket(tilePacket);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static int getAvailablePacketId(){
		return ++packetIdCounter;
	}

}
