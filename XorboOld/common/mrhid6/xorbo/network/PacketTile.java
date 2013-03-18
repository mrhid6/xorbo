package mrhid6.xorbo.network;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class PacketTile extends PacketMain{

	public int xCoord;
	public int yCoord;
	public int zCoord;
	public EntityPlayerMP thePlayer;
	public Payload payload;

	public PacketTile(int id, EntityPlayerMP player){
		this.packetId = id;
		this.thePlayer = player;
		this.packetType = 0;
		this.payload = new Payload();
	}

	public boolean targetExists(World world)
	{
		return world.blockExists(this.xCoord, this.yCoord, this.zCoord);
	}
	
	  public TileEntity getTarget(World world)
	  {
	    return world.getBlockTileEntity(this.xCoord, this.yCoord, this.zCoord);
	  }

	public PacketTile(int id, int x, int y, int z, Payload payload)
	{
		this.packetId = id;
		this.packetType = 0;

		this.xCoord = x;
		this.yCoord = y;
		this.zCoord = z;

		this.payload = payload;
	}

	@Override
	public void readData(DataInputStream data)
			throws IOException {
		this.xCoord = data.readInt();
	    this.yCoord = data.readInt();
	    this.zCoord = data.readInt();

	    this.payload.readPayloadData(data);

	}

	@Override
	public void writeData(DataOutputStream data)
			throws IOException {
		data.writeInt(this.xCoord);
	    data.writeInt(this.yCoord);
	    data.writeInt(this.zCoord);

	    this.payload.writePayloadData(data);

	}

}
