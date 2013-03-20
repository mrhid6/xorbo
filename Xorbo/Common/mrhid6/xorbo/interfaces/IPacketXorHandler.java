package mrhid6.xorbo.interfaces;

import mrhid6.xorbo.network.PacketTile;

public abstract interface IPacketXorHandler{
  public abstract void handleTilePacket(PacketTile paramPacketTile);
}
