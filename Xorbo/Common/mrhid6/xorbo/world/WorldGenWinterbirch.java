package mrhid6.xorbo.world;

import java.util.Random;

import mrhid6.xorbo.block.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.world.World;

public class WorldGenWinterbirch {


	public void generate(World world, Random random, int x, int z, int count){


		for (; count > 0; count--){
			int y = world.getHeight() - 1;

			while ((world.getBlockId(x, y - 1, z) == 0) && (y > 0)){
				y--;
			}

			growTree(world,x,y,z,random);

			x += random.nextInt(15) - 7;
			z += random.nextInt(15) - 7;

		}
	}

	public boolean growTree(World world, int x, int y, int z, Random random){
		if ((world == null) || (ModBlocks.winterbirchLog == null)){
			System.out.println("[ERROR] Had a null that shouldn't have been. Winter Birch did not spawn! w=" + world + " r=" + ModBlocks.winterbirchLog);
			return false;
		}

		if(random.nextInt(20)>5)
			return false;


		int height = getGrowHeight(world,x,y,z);

		height -= random.nextInt(4)+1;

		if(height<2)
			return false;

		for(int i=0;i<height;i++){
			world.setBlockAndMetadataWithNotify(x, y+i, z,ModBlocks.winterbirchLog.blockID,0,0);
		}


		boolean outdent = false;
		int outdentCount = 0;
		int width=1;

		int treeTop = y+height+2;

		for(int y1=y+2;(y1<y+height+2);y1++){

			for(int x1=x-width;x1<=x+width;x1++){
				for(int z1=z-width;z1<=z+width;z1++){

					if(world.getBlockId(x1, y1, z1)==0){
						world.setBlockAndMetadataWithNotify(x1, y1, z1,ModBlocks.winterbirchLeaves.blockID,0,0);
					}
				}
			}

			if(y1<treeTop){
				if(outdentCount>=1 && y1<treeTop){
					width = 2;
					outdent = !outdent;
					outdentCount=0;
				}else{
					width = 1;
					outdentCount++;
				}
			}else if(y1>=treeTop){
				width = 1;
			}
		}


		return true;

	}

	public static int getGrowHeight(World world, int x, int y, int z){
		if (((world.getBlockId(x, y - 1, z) != Block.grass.blockID) && (world.getBlockId(x, y - 1, z) != Block.dirt.blockID)) || ((world.getBlockId(x, y, z) != 0)))
		{
			return 0;
		}

		int height = 1;

		for (; (world.getBlockId(x, y + 1, z) == 0) && (height < 8); y++)
		{
			height++;
		}

		return height;
	}
}
