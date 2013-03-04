package mrhid6.xorbo;

import java.util.HashMap;

import cpw.mods.fml.client.registry.RenderingRegistry;

import mrhid6.xorbo.render.RenderBlockCable;
import net.minecraftforge.common.Configuration;

public class Config extends Configuration{


	public static final int[][] SIDE_COORD_MOD = { { 0, -1, 0 }, { 0, 1, 0 }, { 0, 0, -1 }, { 0, 0, 1 }, { -1, 0, 0 }, { 1, 0, 0 } };

	static HashMap<String, Integer> renderIds = new HashMap<String, Integer>();


	public static void init(){
		RenderingRegistry.registerBlockHandler(new RenderBlockCable());
		renderIds.put("cable", RenderBlockCable.renderId);
	}

	public static int getRenderId(String string) {
		return renderIds.get(string);
	}

	public static int[] getAdjacentCoordinatesForSide(int x, int y, int z, int side){
		return new int[] { x + SIDE_COORD_MOD[side][0], y + SIDE_COORD_MOD[side][1], z + SIDE_COORD_MOD[side][2] };
	}


}
