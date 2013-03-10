package mrhid6.xorbo.proxy;

import cpw.mods.fml.client.registry.ClientRegistry;
import mrhid6.xorbo.render.RenderTEZoroController;
import mrhid6.xorbo.tileEntity.TEZoroController;
import net.minecraftforge.client.MinecraftForgeClient;

public class clientProxy extends commonProxy{


	@Override
	public void registerRenderers() {
		
		
		MinecraftForgeClient.preloadTexture(BLOCK_PNG);
		MinecraftForgeClient.preloadTexture(BLOCK_TILES_PNG);
		MinecraftForgeClient.preloadTexture(ITEMS_PNG);
		
		ClientRegistry.bindTileEntitySpecialRenderer(TEZoroController.class, new RenderTEZoroController());
		
	}
}
