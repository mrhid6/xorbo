package mrhid6.xorbo;

import mrhid6.xorbo.block.ModBlocks;
import mrhid6.xorbo.proxy.commonProxy;
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

@Mod(modid = "tutorial", name = "Tutorial Mod", version = "1.0.0")
@NetworkMod(clientSideRequired = true, serverSideRequired = false)

public class Xorbo {

	@Instance("tutorial")
	public static Xorbo instance;

	
	@SidedProxy(clientSide="mrhid6.xorbo.proxy.clientProxy", serverSide="mrhid6.xorbo.proxy.commonProxy")
	public static commonProxy proxy;
	
	@PreInit
	public void preInit(FMLPreInitializationEvent event) {
		
	}

	@Init
	public void load(FMLInitializationEvent event) {
		Config.init();
		proxy.registerRenderers();
		ModBlocks.init();
	}

	@PostInit
	public static void postInit(FMLPostInitializationEvent event) {

	}
}
