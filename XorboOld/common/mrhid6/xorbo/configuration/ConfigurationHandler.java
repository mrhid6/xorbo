package mrhid6.xorbo.configuration;

import java.io.File;

import mrhid6.xorbo.lib.BlockIds;
import mrhid6.xorbo.lib.ItemIds;
import net.minecraftforge.common.Configuration;

public class ConfigurationHandler
{
    private static Configuration config;

    public ConfigurationHandler()
    {
    }

    public static void init(File configFile)
    {
        config = new Configuration(configFile);
        config.load();
        ItemIds.addItemID(config, "ZoroIngot", 5001);
        ItemIds.addItemID(config, "RefinedZoroIngot", 5002);
        ItemIds.addItemID(config, "StearilliumOre", 5003);
        ItemIds.addItemID(config, "TriniumOre", 5004);
        ItemIds.addItemID(config, "StearilliumGear", 5005);
        ItemIds.addItemID(config, "TriniumGear", 5006);
        ItemIds.addItemID(config, "TriniumIngot", 5006);
        ItemIds.addItemID(config, "ZoroDust", 5007);
        ItemIds.addItemID(config, "TriniumDust", 5008);
        ItemIds.addItemID(config, "debugtool", 5009);
        
        BlockIds.addBlockID(config, "ZoroOre", 501);
        BlockIds.addBlockID(config, "ZoroBrick", 502);
        BlockIds.addBlockID(config, "TriniumBrick", 503);
        BlockIds.addBlockID(config, "StearilliumSmoothStone", 504);
        BlockIds.addBlockID(config, "ZoroFurnace", 505);
        BlockIds.addBlockID(config, "HeartThroLog", 506);
        BlockIds.addBlockID(config, "HeartThroLeaves", 507);
        BlockIds.addBlockID(config, "StearilliumOre", 508);
        BlockIds.addBlockID(config, "ZoroGen", 509);
        BlockIds.addBlockID(config, "TriniumOre", 510);
        BlockIds.addBlockID(config, "TriniumSun", 511);
        BlockIds.addBlockID(config, "StearilliumGrinder", 512);
        BlockIds.addBlockID(config, "TriniumCore", 513);
        BlockIds.addBlockID(config, "TriniumChiller", 514);
        BlockIds.addBlockID(config, "TriniumLaserBase", 515);
        BlockIds.addBlockID(config, "TriniumLaserTurret", 516);
        config.save();
    }
}
