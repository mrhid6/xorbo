package mrhid6.xorbo;

import java.io.File;
import java.util.HashMap;

import mrhid6.xorbo.render.RenderBlockCable;
import net.minecraftforge.common.Configuration;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class Config extends Configuration{


	public static final int[][] SIDE_COORD_MOD = { { 0, -1, 0 }, { 0, 1, 0 }, { 0, 0, -1 }, { 0, 0, 1 }, { -1, 0, 0 }, { 1, 0, 0 } };

	static HashMap<String, Integer> renderIds = new HashMap<String, Integer>();

	public static CreativeTabXor creativeTabXor;
	
	private static Configuration config;


	public static void init(File configFile){
		creativeTabXor = new CreativeTabXor("Xorbo");
		
		RenderingRegistry.registerBlockHandler(new RenderBlockCable());
		renderIds.put("cable", RenderBlockCable.renderId);
		
		config = new Configuration(configFile);
        config.load();
        ItemIds.addItemID(config, "ZoroIngot", 5001);
        
        BlockIds.addBlockID(config, "zoroCable", 500);
        BlockIds.addBlockID(config, "zoroFurnace", 501);
        BlockIds.addBlockID(config, "zoroController", 502);
        BlockIds.addBlockID(config, "hazelspringLog", 503);
        BlockIds.addBlockID(config, "winterbirchLog", 504);
        BlockIds.addBlockID(config, "hazelspringLeaves", 505);
        BlockIds.addBlockID(config, "winterbirchLeaves", 506);
        
        BlockIds.addBlockID(config, "zoroStill", 507);
        BlockIds.addBlockID(config, "zoroFlowing", 508);
        
        
        ItemIds.addItemID(config, "zoroBucket", 5000);
        config.save();
	}
	
	

	public static int getRenderId(String string) {
		return renderIds.get(string);
	}

	public static int[] getAdjacentCoordinatesForSide(int x, int y, int z, int side){
		return new int[] { x + SIDE_COORD_MOD[side][0], y + SIDE_COORD_MOD[side][1], z + SIDE_COORD_MOD[side][2] };
	}


}
