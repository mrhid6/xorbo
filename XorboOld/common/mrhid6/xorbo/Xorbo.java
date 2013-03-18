package mrhid6.xorbo;

import mrhid6.xorbo.blocks.ModBlocks;
import mrhid6.xorbo.configuration.BuildCraftPlugin;
import mrhid6.xorbo.configuration.ConfigurationHandler;
import mrhid6.xorbo.gui.GuiHandler;
import mrhid6.xorbo.items.ModItems;
import mrhid6.xorbo.mob.EntityTitan;
import mrhid6.xorbo.network.PacketHandler;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityEggInfo;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.Configuration;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

@Mod(modid = "Xorbo", name = "Xorbo", version = "1.0.0", dependencies = "after:mod_BuildCraftCore;after:mod_BuildCraftEnergy;after:mod_BuildCraftFactory;after:mod_BuildCraftSilicon;after:mod_BuildCraftTransport;")
@NetworkMod(clientSideRequired = true, serverSideRequired = false, channels={"Xorbo"}, packetHandler=PacketHandler.class)
public class Xorbo
{
    private Configuration config;
    private BuildCraftPlugin BP;

    public Xorbo()
    {
    }

    // The instance of your mod that Forge uses.
    @Instance("Xorbo")
    public static Xorbo instance;

    // Says where the client and server 'proxy' code is loaded.
    @SidedProxy(clientSide = "mrhid6.xorbo.client.ClientProxy", serverSide = "mrhid6.xorbo.CommonProxy")
    public static CommonProxy proxy;

    @PreInit
    public void preInit(FMLPreInitializationEvent event)
    {
        ConfigurationHandler.init(event.getSuggestedConfigurationFile());
    	BP = new BuildCraftPlugin();
        BP.checkStoneGear();
    }

    static int startEntityId = 300;
    @Init
    public void load(FMLInitializationEvent event)
    {
        proxy.registerRenderers();
        proxy.registerPacketInformation();
        
        LanguageRegistry.instance().addStringLocalization("itemGroup.Xorbo", "en_US", "Xorbo");
        ModBlocks.init();
        ModItems.init();
        ModBlocks.initSmelting();
        ModItems.initRecipes();
        NetworkRegistry.instance().registerGuiHandler(this, new GuiHandler());
        GameRegistry.registerWorldGenerator(new WorldGen());
        tabsXor.setIcon(ModBlocks.zoroFurnaceBlock);
        
        EntityRegistry.registerModEntity(EntityTitan.class, "Titan", 1, this, 80, 3, true);
        
        EntityRegistry.addSpawn(EntityTitan.class, 10, 2, 4, EnumCreatureType.creature, BiomeGenBase.plains,BiomeGenBase.forest);
        
        LanguageRegistry.instance().addStringLocalization("entity.Xorbo.Titan.name", "Titan");
        registerEntityEgg(EntityTitan.class, 0xff0000,0x0000ff);
    }

    public static CreativeTabXorbo tabsXor = new CreativeTabXorbo("Xorbo");

    public static int getWorldHeight(World world)
    {
        return world.getHeight();
    }
    
    public static int getUniqueEntitId(){
    	do{
    		startEntityId++;
    	}while(EntityList.getStringFromID(startEntityId)!=null);
    	
    	return startEntityId;
    }
    
    public static void registerEntityEgg(Class <? extends Entity> entity ,int primaryColor,int secondaryColor){
    	int id = getUniqueEntitId();
    	
    	EntityList.IDtoClassMapping.put(id,entity);
    	EntityList.entityEggs.put(id,new EntityEggInfo(id,primaryColor,secondaryColor));
    	
    }
}
