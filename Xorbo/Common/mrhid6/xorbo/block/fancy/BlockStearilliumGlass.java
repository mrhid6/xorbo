package mrhid6.xorbo.block.fancy;

import mrhid6.xorbo.Config;
import mrhid6.xorbo.block.BlockTexturedBase;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

public class BlockStearilliumGlass extends BlockTexturedBase {

	public BlockStearilliumGlass( int id, String name ) {
		super(id, name, name, true);

		this.setResistance(4.0F);
		this.setHardness(5.0F);

	}
	
	@Override
	public boolean isOpaqueCube() {
		return false;
	}
	
	public Icon getBlockTextureForConnect(int side,World world,int x,int y,int z){
		
		boolean[] sides = new boolean[6];
		
		for (int i = 0; i < 6; i++) {

			int x1 = x + Config.SIDE_COORD_MOD[i][0];
			int y1 = y + Config.SIDE_COORD_MOD[i][1];
			int z1 = z + Config.SIDE_COORD_MOD[i][2];
			
			int block = world.getBlockId(x1, y1, z1);
			if(block == this.blockID){
				sides[i]=true;
			}
		}
		
		return this.blockIcon;
	}
}
