package mrhid6.xorbo;

import mrhid6.xorbo.block.ModBlocks;
import mrhid6.xorbo.entities.EntityTitan;
import mrhid6.xorbo.items.ModItems;
import mrhid6.xorbo.network.PacketHandler;
import mrhid6.xorbo.proxy.commonProxy;
import mrhid6.xorbo.world.WorldGenBase;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityEggInfo;
import net.minecraft.entity.EntityList;
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
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

@Mod(modid = "xorbo", name = "Xorbo", version = "1.0.0")
@NetworkMod(clientSideRequired = true, serverSideRequired = false, channels = { "Xorbo" }, packetHandler = PacketHandler.class)
public class Xorbo {

	@Instance("xorbo")
	public static Xorbo instance;

	@SidedProxy(clientSide = "mrhid6.xorbo.proxy.clientProxy", serverSide = "mrhid6.xorbo.proxy.commonProxy")
	public static commonProxy proxy;

	static int startEntityId = 300;

	public static int getUniqueEntitId() {
		do {
			startEntityId++;
		} while (EntityList.getStringFromID(startEntityId) != null);

		return startEntityId;
	}

	@PostInit
	public static void postInit( FMLPostInitializationEvent event ) {

	}

	public static void registerEntityEgg( Class<? extends Entity> entity,
			int primaryColor, int secondaryColor ) {
		int id = getUniqueEntitId();

		EntityList.IDtoClassMapping.put(id, entity);
		EntityList.entityEggs.put(id, new EntityEggInfo(id, primaryColor,
				secondaryColor));

	}

	@Init
	public void load( FMLInitializationEvent event ) {
		proxy.registerRenderers();
		proxy.registerPacketInformation();
		ModItems.init();
		ModBlocks.init();
		NetworkRegistry.instance().registerGuiHandler(this, new GuiHandler());
		GameRegistry.registerWorldGenerator(new WorldGenBase());

		EntityRegistry.registerModEntity(EntityTitan.class, "Titan", 1, this,
				80, 3, true);

		// EntityRegistry.addSpawn(EntityTitan.class, 10, 2, 4,
		// EnumCreatureType.creature, BiomeGenBase.plains,BiomeGenBase.forest);

		LanguageRegistry.instance().addStringLocalization(
				"entity.Xorbo.Titan.name", "Titan");
		registerEntityEgg(EntityTitan.class, 0xff0000, 0x0000ff);
	}

	@PreInit
	public void preInit( FMLPreInitializationEvent event ) {
		Config.init(event.getSuggestedConfigurationFile());
	}
}
