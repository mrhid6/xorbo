package mrhid6.xorbo;

import java.util.HashMap;
import net.minecraftforge.common.Configuration;

public class BlockIds {

	private static HashMap IDs = new HashMap();

	public static void addBlockID( Configuration config, String name,
			int defaultID ) {
		IDs.put(name,
				Integer.valueOf(config.getBlock(name, defaultID).getInt()));
	}

	public static int getID( String name ) {
		int res = ((Integer) IDs.get(name)).intValue();
		return res;
	}
}
