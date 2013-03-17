package mrhid6.xorbo.proxy;

import java.util.EnumSet;

import mrhid6.xorbo.render.RenderTEZoroController;
import mrhid6.xorbo.tileEntity.TEZoroController;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;

public class clientProxy extends commonProxy implements ITickHandler{

	
	public static final Minecraft mc = Minecraft.getMinecraft();
	private int playerCounter;

	
	
	public clientProxy() {
		TickRegistry.registerTickHandler(this, Side.CLIENT);
	}

	@Override
	public void registerRenderers() {
		
		
		//MinecraftForgeClient.preloadTexture(BLOCK_PNG);
		//MinecraftForgeClient.preloadTexture(BLOCK_TILES_PNG);
		//MinecraftForgeClient.preloadTexture(ITEMS_PNG);
		
		ClientRegistry.bindTileEntitySpecialRenderer(TEZoroController.class, new RenderTEZoroController());
		
	}

	@Override
	public void tickStart(EnumSet<TickType> type, Object... tickData) {
		
		 if ((mc.theWorld != null) && (mc.theWorld.playerEntities.size() > 0)){
			 this.playerCounter += 1;
			 
			 if(this.playerCounter>=mc.theWorld.playerEntities.size())this.playerCounter=0;
			 
			 EntityPlayer lplayer = (EntityPlayer)mc.theWorld.playerEntities.get(this.playerCounter);
			 if(true){
				  String oldCape = lplayer.cloakUrl;
				  
				  lplayer.cloakUrl = "http://profiles.projectminecraft.org/xorbo/modfiles/zoroCape.png";
				  if(oldCape != lplayer.cloakUrl)mc.renderEngine.obtainImageData(lplayer.cloakUrl, null);
			 }
		 }
	}

	@Override
	public void tickEnd(EnumSet<TickType> type, Object... tickData) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public EnumSet<TickType> ticks() {
		return EnumSet.of(TickType.CLIENT);
	}

	@Override
	public String getLabel() {
		return "Xorbo";
	}
}
