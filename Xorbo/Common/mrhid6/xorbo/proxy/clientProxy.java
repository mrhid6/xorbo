package mrhid6.xorbo.proxy;

import java.util.EnumSet;
import mrhid6.xorbo.entities.EntityTitan;
import mrhid6.xorbo.models.ModelTitan;
import mrhid6.xorbo.network.PacketHandler;
import mrhid6.xorbo.render.RenderTEStearilliumCrafter;
import mrhid6.xorbo.render.RenderTETriniumConverter;
import mrhid6.xorbo.render.RenderTETriniumMiner;
import mrhid6.xorbo.render.RenderTEZoroChest;
import mrhid6.xorbo.render.RenderTEZoroController;
import mrhid6.xorbo.render.RenderTitan;
import mrhid6.xorbo.tileEntity.TEMachineBase;
import mrhid6.xorbo.tileEntity.TEStearilliumCrafter;
import mrhid6.xorbo.tileEntity.TETriniumConverter;
import mrhid6.xorbo.tileEntity.TETriniumMiner;
import mrhid6.xorbo.tileEntity.TEZoroChest;
import mrhid6.xorbo.tileEntity.TEZoroController;
import mrhid6.xorbo.tileEntity.TEZoroFurnace;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;

public class clientProxy extends commonProxy implements ITickHandler {

	public static final Minecraft mc = Minecraft.getMinecraft();
	public boolean allHaveCape = false;

	String[] capeUsers = { "mrhid6", "tommo1590", "danzo1997", "Wolfyart", "GreatCannonba11", "Blackout656" };
	private int playerCounter;

	public clientProxy() {
		TickRegistry.registerTickHandler(this, Side.CLIENT);
	}

	@Override
	public String getLabel() {
		return "Xorbo";
	}

	public boolean playerCanHaveCape( String username ) {

		for (String u : capeUsers) {

			if (u.equalsIgnoreCase(username)) {
				return true;
			}
		}

		return false;
	}

	@Override
	public void registerPacketInformation() {
		// TETriniumCore.setDescPacketId(PacketHandler.getAvailablePacketId());
		// TileZoroGen.setDescPacketId(PacketHandler.getAvailablePacketId());
		TEMachineBase.setDescPacketId(PacketHandler.getAvailablePacketId());
		TEZoroFurnace.setGuiPacketId(PacketHandler.getAvailablePacketId());
		TEZoroController.setDescPacketId(PacketHandler.getAvailablePacketId());
		// TETriniumLaserTurret.setDescPacketId(PacketHandler.getAvailablePacketId());
		// TETriniumChillerCore.setDescPacketId(PacketHandler.getAvailablePacketId());
	}

	@Override
	public void registerRenderers() {

		RenderingRegistry.registerEntityRenderingHandler(EntityTitan.class, new RenderTitan(new ModelTitan(), 0.3F));

		ClientRegistry.bindTileEntitySpecialRenderer(TEZoroController.class, new RenderTEZoroController());
		ClientRegistry.bindTileEntitySpecialRenderer(TEStearilliumCrafter.class, new RenderTEStearilliumCrafter());
		ClientRegistry.bindTileEntitySpecialRenderer(TETriniumMiner.class, new RenderTETriniumMiner());
		ClientRegistry.bindTileEntitySpecialRenderer(TETriniumConverter.class, new RenderTETriniumConverter());
		ClientRegistry.bindTileEntitySpecialRenderer(TEZoroChest.class, new RenderTEZoroChest());

	}

	@Override
	public void tickEnd( EnumSet<TickType> type, Object... tickData ) {
		// TODO Auto-generated method stub

	}

	@Override
	public EnumSet<TickType> ticks() {
		return EnumSet.of(TickType.CLIENT);
	}

	@Override
	public void tickStart( EnumSet<TickType> type, Object... tickData ) {

		if ((mc.theWorld != null) && (mc.theWorld.playerEntities.size() > 0)) {
			playerCounter += 1;

			if (playerCounter >= mc.theWorld.playerEntities.size()) {
				playerCounter = 0;
			}

			EntityPlayer lplayer = (EntityPlayer) mc.theWorld.playerEntities.get(playerCounter);
			if (allHaveCape || playerCanHaveCape(lplayer.username)) {
				String oldCape = lplayer.cloakUrl;

				lplayer.cloakUrl = "http://profiles.projectminecraft.org/xorbo/modfiles/zoroCape.png";
				if (oldCape != lplayer.cloakUrl) {
					mc.renderEngine.obtainImageData(lplayer.cloakUrl, null);
				}
			}
		}
	}
}
