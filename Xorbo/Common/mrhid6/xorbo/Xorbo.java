package mrhid6.xorbo;

import mrhid6.xorbo.block.ModBlocks;
import mrhid6.xorbo.items.ModItems;
import mrhid6.xorbo.network.PacketHandler;
import mrhid6.xorbo.proxy.commonProxy;
import mrhid6.xorbo.world.WorldGenBase;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PostInit;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(modid = "xorbo", name = "Xorbo", version = "1.0.0")
@NetworkMod(clientSideRequired = true, serverSideRequired = false, channels={"Xorbo"}, packetHandler=PacketHandler.class)

public class Xorbo {

	@Instance("xorbo")
	public static Xorbo instance;

	
	@SidedProxy(clientSide="mrhid6.xorbo.proxy.clientProxy", serverSide="mrhid6.xorbo.proxy.commonProxy")
	public static commonProxy proxy;
	
	@PreInit
	public void preInit(FMLPreInitializationEvent event) {
		Config.init(event.getSuggestedConfigurationFile());
	}

	@Init
	public void load(FMLInitializationEvent event) {
		proxy.registerRenderers();
		proxy.registerPacketInformation();
		ModBlocks.init();
		ModItems.init();
		NetworkRegistry.instance().registerGuiHandler(this, new GuiHandler());
		GameRegistry.registerWorldGenerator(new WorldGenBase());
	}

	@PostInit
	public static void postInit(FMLPostInitializationEvent event) {

	}
}
