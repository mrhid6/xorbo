package mrhid6.xorbo;

import net.minecraft.world.World;

public class CommonProxy
{
    public static String ITEMS_PNG = "/mrhid6/xorbo/textures/items.png";
    public static String DUSTS_PNG = "/mrhid6/xorbo/textures/dusts.png";
    public static String BLOCK_PNG = "/mrhid6/xorbo/textures/block.png";
    public static String TILE_PNG = "/mrhid6/xorbo/textures/tile.png";
    public static String SFURNACE_PNG = "/mrhid6/xorbo/textures/SFurnace.png";

    // Client stuff
    public void registerRenderers(){}
    
    public void registerPacketInformation(){};
    
    public Object InitBeam(World world, double px, double py, double pz, double tx, double ty, double tz, int type, int color, boolean reverse, float endmod, Object input, int impact){return null;};
}