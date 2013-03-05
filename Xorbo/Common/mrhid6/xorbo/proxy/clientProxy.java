package mrhid6.xorbo.proxy;

import net.minecraftforge.client.MinecraftForgeClient;

public class clientProxy extends commonProxy{


	@Override
	public void registerRenderers() {
		MinecraftForgeClient.preloadTexture(BLOCK_PNG);
		MinecraftForgeClient.preloadTexture(ITEMS_PNG);
		
	}
}
