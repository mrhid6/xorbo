package mrhid6.xorbo.network;

public abstract interface IPacketXorHandler{
  public abstract void handleTilePacket(PacketTile paramPacketTile);
}
