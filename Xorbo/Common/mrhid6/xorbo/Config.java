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


		BlockIds.addBlockID(config, "zoroCable", 500);
		BlockIds.addBlockID(config, "zoroFurnace", 501);
		BlockIds.addBlockID(config, "zoroController", 502);
		BlockIds.addBlockID(config, "triniumMiner", 503);
		BlockIds.addBlockID(config, "stearilliumCrafter", 504);
		BlockIds.addBlockID(config, "zoroGrass", 505);
		BlockIds.addBlockID(config, "hazelspringLog", 506);
		BlockIds.addBlockID(config, "winterbirchLog", 507);
		BlockIds.addBlockID(config, "hazelspringLeaves", 508);
		BlockIds.addBlockID(config, "winterbirchLeaves", 509);

		BlockIds.addBlockID(config, "zoroStill", 510);
		BlockIds.addBlockID(config, "zoroFlowing", 511);

		BlockIds.addBlockID(config, "zoroOre", 512);
		BlockIds.addBlockID(config, "stearilliumOre", 513);
		BlockIds.addBlockID(config, "triniumOre", 514);



		ItemIds.addItemID(config, "zoroBucket", 5000);
		ItemIds.addItemID(config, "ZoroIngot", 5001);
		ItemIds.addItemID(config, "stearilliumOre", 5002);
		ItemIds.addItemID(config, "debugTool", 5003);
		config.save();
	}



	public static int getRenderId(String string) {
		return renderIds.get(string);
	}

	public static int[] getAdjacentCoordinatesForSide(int x, int y, int z, int side){
		return new int[] { x + SIDE_COORD_MOD[side][0], y + SIDE_COORD_MOD[side][1], z + SIDE_COORD_MOD[side][2] };
	}


	public static int[] spiralArray(int dimension){

		int numberOfItem = dimension*dimension;
		int[] spiralArr = new int[numberOfItem];
		byte toggle = 0;
		int ds = dimension;
		int cnt = dimension - 1;
		int cntStart = dimension - 1;	
		for (int i = 1; i <= dimension; i++){
			spiralArr[numberOfItem-i] = i;
		} 

		for (int i = numberOfItem - dimension ; i > 0 ; i--){
			spiralArr[i - 1] = spiralArr[i] + ds;
			cnt = cnt - 1;
			if (cnt == 0){
				ds = (int)(ds * turnFactor(toggle, dimension));
				cntStart = cntStart - toggle;
				cnt = cntStart;
				toggle = (byte)(toggle^1);
			}
		}	
		return spiralArr;
	}

	private static float turnFactor(int arg, float n){
		if (arg == 0){	
			return -1/n;
		}else {
			return n; 
		}
	}


}
