package mrhid6.xorbo.client;

import java.awt.Color;

import mrhid6.xorbo.CommonProxy;
import mrhid6.xorbo.blocks.custom.BRStearilliumGrinder;
import mrhid6.xorbo.blocks.custom.BRTriniumCore;
import mrhid6.xorbo.blocks.custom.BRTriniumSun;
import mrhid6.xorbo.fx.FXBeam;
import mrhid6.xorbo.mob.EntityTitan;
import mrhid6.xorbo.mob.render.ModelTitan;
import mrhid6.xorbo.mob.render.RenderTitan;
import mrhid6.xorbo.network.PacketHandler;
import mrhid6.xorbo.tileentities.TESRStearilliumGrinder;
import mrhid6.xorbo.tileentities.TESRTriniumCore;
import mrhid6.xorbo.tileentities.TEStearilliumGrinder;
import mrhid6.xorbo.tileentities.TETriniumChillerCore;
import mrhid6.xorbo.tileentities.TETriniumCore;
import mrhid6.xorbo.tileentities.TileZoroFurnace;
import mrhid6.xorbo.tileentities.engine.TileZoroGen;
import mrhid6.xorbo.triniumlaser.BRTriniumLaserBase;
import mrhid6.xorbo.triniumlaser.BRTriniumLaserTurret;
import mrhid6.xorbo.triniumlaser.TESRTriniumLaserBase;
import mrhid6.xorbo.triniumlaser.TESRTriniumLaserTurret;
import mrhid6.xorbo.triniumlaser.TETriniumLaserBase;
import mrhid6.xorbo.triniumlaser.TETriniumLaserTurret;
import net.minecraft.src.ModLoader;
import net.minecraft.world.World;
import net.minecraftforge.client.MinecraftForgeClient;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy
{
	@Override
	public void registerRenderers()
	{
		MinecraftForgeClient.preloadTexture(ITEMS_PNG);
		MinecraftForgeClient.preloadTexture(BLOCK_PNG);
		MinecraftForgeClient.preloadTexture(TILE_PNG);
		MinecraftForgeClient.preloadTexture(DUSTS_PNG);
		MinecraftForgeClient.preloadTexture(SFURNACE_PNG);

		RenderingRegistry.registerEntityRenderingHandler(EntityTitan.class, new RenderTitan(new ModelTitan(),0.3F));

		/*ClientRegistry.bindTileEntitySpecialRenderer(TETriniumSun.class, new TESRTriniumSun());
		RenderingRegistry.registerBlockHandler(new BRTriniumSun());

		ClientRegistry.bindTileEntitySpecialRenderer(TEStearilliumGrinder.class, new TESRStearilliumGrinder());
		RenderingRegistry.registerBlockHandler(new BRStearilliumGrinder());

		ClientRegistry.bindTileEntitySpecialRenderer(TETriniumCore.class, new TESRTriniumCore());
		RenderingRegistry.registerBlockHandler(new BRTriniumCore());

		ClientRegistry.bindTileEntitySpecialRenderer(TETriniumLaserBase.class, new TESRTriniumLaserBase());
		RenderingRegistry.registerBlockHandler(new BRTriniumLaserBase());

		ClientRegistry.bindTileEntitySpecialRenderer(TETriniumLaserTurret.class, new TESRTriniumLaserTurret());
		RenderingRegistry.registerBlockHandler(new BRTriniumLaserTurret());*/

	}

	public void registerPacketInformation(){
		TETriniumCore.setDescPacketId(PacketHandler.getAvailablePacketId());
		TileZoroGen.setDescPacketId(PacketHandler.getAvailablePacketId());
		TileZoroFurnace.setDescPacketId(PacketHandler.getAvailablePacketId());
		TETriniumLaserTurret.setDescPacketId(PacketHandler.getAvailablePacketId());
		TETriniumChillerCore.setDescPacketId(PacketHandler.getAvailablePacketId());
	}
	
	@Override
	public Object InitBeam(World world, double px, double py, double pz, double tx, double ty, double tz, int type, int color, boolean reverse, float endmod, Object input, int impact){
		FXBeam beam = null;
		Color c = new Color(color);
		if(input instanceof FXBeam) beam = (FXBeam)input;
		
		if(beam==null || beam.isDead){
			beam = new FXBeam(world, px, py, pz, tx, ty, tz, c.getRed() / 255.0F, c.getGreen() / 255.0F, c.getBlue() / 255.0F, 8);
			beam.setEndMod(endmod);
			beam.setReverse(reverse);
			ModLoader.getMinecraftInstance().effectRenderer.addEffect(beam);
		}else{
			beam.updateBeam(tx, ty, tz);
			beam.setEndMod(endmod);
			beam.impact = impact;
		}
		
		return beam;
		
	}
}
