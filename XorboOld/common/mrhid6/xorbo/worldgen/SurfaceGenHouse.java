/*
*** MADE BY MITHION'S .SCHEMATIC TO JAVA CONVERTING TOOL v1.6 ***
*/

package mrhid6.xorbo.worldgen;
import java.util.Random;

import mrhid6.xorbo.Xorbo;
import net.minecraft.block.Block;
import net.minecraft.world.World;

public class SurfaceGenHouse
{
	protected int[] GetValidSpawnBlocks() {
		return new int[] {
			Block.grass.blockID,
		};
	}

	public boolean LocationIsValidSpawn(World world, int i, int j, int k){
		int distanceToAir = 0;
		int checkID = world.getBlockId(i, j, k);

		while (checkID != 0){
			distanceToAir++;
			checkID = world.getBlockId(i, j + distanceToAir, k);
		}

		if (distanceToAir > 1){
			return false;
		}
		j += distanceToAir - 1;

		int blockID = world.getBlockId(i, j, k);
		int blockIDAbove = world.getBlockId(i, j+1, k);
		int blockIDBelow = world.getBlockId(i, j-1, k);
		for (int x : GetValidSpawnBlocks()){
			if (blockIDAbove != 0){
				return false;
			}
			if (blockID == x){
				return true;
			}else if (blockID == Block.snow.blockID && blockIDBelow == x){
				return true;
			}
		}
		return false;
	}

	public SurfaceGenHouse() { }

	public boolean generate(World world, Random rand, int x, int z) {
		int y = Xorbo.getWorldHeight(world) - 1;

        while ((world.getBlockId(x, y - 1, z) == 0) && (y > 0))
        {
            y--;
        }
        
        if(rand.nextInt(100)>15 || world.getBlockId(x, y - 1, z) != Block.grass.blockID)
        	return true;
		world.setBlockAndMetadata(x + 1, y + 3, z + 1, Block.stairCompactPlanks.blockID, 2);
		world.setBlock(x + 1, y + 3, z + 2, Block.stairCompactPlanks.blockID);
		world.setBlock(x + 1, y + 3, z + 3, Block.stairCompactPlanks.blockID);
		world.setBlock(x + 1, y + 3, z + 4, Block.stairCompactPlanks.blockID);
		world.setBlock(x + 1, y + 3, z + 5, Block.stairCompactPlanks.blockID);
		world.setBlock(x + 1, y + 3, z + 6, Block.stairCompactPlanks.blockID);
		world.setBlock(x + 2, y, z + 2, Block.cobblestone.blockID);
		world.setBlock(x + 2, y, z + 3, Block.cobblestone.blockID);
		world.setBlock(x + 2, y, z + 4, Block.cobblestone.blockID);
		world.setBlock(x + 2, y, z + 5, Block.cobblestone.blockID);
		world.setBlock(x + 2, y, z + 6, Block.stairCompactCobblestone.blockID);
		world.setBlockAndMetadata(x + 2, y + 1, z + 2, Block.wood.blockID, 1);
		world.setBlockAndMetadata(x + 2, y + 1, z + 3, Block.planks.blockID, 1);
		world.setBlock(x + 2, y + 1, z + 4, Block.thinGlass.blockID);
		world.setBlockAndMetadata(x + 2, y + 1, z + 5, Block.wood.blockID, 1);
		world.setBlockAndMetadata(x + 2, y + 2, z + 2, Block.wood.blockID, 1);
		world.setBlockAndMetadata(x + 2, y + 2, z + 3, Block.planks.blockID, 1);
		world.setBlock(x + 2, y + 2, z + 4, Block.thinGlass.blockID);
		world.setBlockAndMetadata(x + 2, y + 2, z + 5, Block.wood.blockID, 1);
		world.setBlockAndMetadata(x + 2, y + 3, z + 1, Block.stairCompactPlanks.blockID, 2);
		world.setBlockAndMetadata(x + 2, y + 3, z + 2, Block.wood.blockID, 1);
		world.setBlock(x + 2, y + 3, z + 3, Block.planks.blockID);
		world.setBlock(x + 2, y + 3, z + 4, Block.planks.blockID);
		world.setBlockAndMetadata(x + 2, y + 3, z + 5, Block.wood.blockID, 1);
		world.setBlockAndMetadata(x + 2, y + 3, z + 6, Block.stairCompactPlanks.blockID, 3);
		world.setBlockAndMetadata(x + 2, y + 4, z + 2, Block.stairCompactPlanks.blockID, 2);
		world.setBlock(x + 2, y + 4, z + 3, Block.stairCompactPlanks.blockID);
		world.setBlock(x + 2, y + 4, z + 4, Block.stairCompactPlanks.blockID);
		world.setBlock(x + 2, y + 4, z + 5, Block.stairCompactPlanks.blockID);
		world.setBlock(x + 3, y, z + 2, Block.cobblestone.blockID);
		world.setBlockAndMetadata(x + 3, y, z + 3, Block.planks.blockID, 2);
		world.setBlockAndMetadata(x + 3, y, z + 4, Block.planks.blockID, 2);
		world.setBlock(x + 3, y, z + 5, Block.cobblestone.blockID);
		world.setBlockAndMetadata(x + 3, y, z + 6, Block.stairCompactCobblestone.blockID, 3);
		world.setBlockAndMetadata(x + 3, y + 1, z + 2, Block.thinGlass.blockID, 2);
		world.setBlockAndMetadata(x + 3, y + 1, z + 3, Block.chest.blockID, 3);
		world.setBlock(x + 3, y + 2, z + 2, Block.thinGlass.blockID);
		world.setBlockAndMetadata(x + 3, y + 3, z + 1, Block.stairCompactPlanks.blockID, 2);
		world.setBlock(x + 3, y + 3, z + 2, Block.planks.blockID);
		world.setBlock(x + 3, y + 3, z + 5, Block.planks.blockID);
		world.setBlockAndMetadata(x + 3, y + 3, z + 6, Block.stairCompactPlanks.blockID, 3);
		world.setBlockAndMetadata(x + 3, y + 4, z + 2, Block.stairCompactPlanks.blockID, 2);
		world.setBlockAndMetadata(x + 3, y + 4, z + 5, Block.stairCompactPlanks.blockID, 3);
		world.setBlock(x + 3, y + 5, z + 3, Block.stairCompactPlanks.blockID);
		world.setBlockAndMetadata(x + 3, y + 5, z + 4, Block.stairCompactPlanks.blockID, 3);
		world.setBlock(x + 4, y, z + 2, Block.cobblestone.blockID);
		world.setBlockAndMetadata(x + 4, y, z + 3, Block.planks.blockID, 2);
		world.setBlockAndMetadata(x + 4, y, z + 4, Block.planks.blockID, 2);
		world.setBlock(x + 4, y, z + 5, Block.cobblestone.blockID);
		world.setBlockAndMetadata(x + 4, y, z + 6, Block.stairCompactCobblestone.blockID, 1);
		world.setBlockAndMetadata(x + 4, y + 1, z + 2, Block.planks.blockID, 1);
		world.setBlockAndMetadata(x + 4, y + 1, z + 3, Block.bed.blockID, 10);
		world.setBlockAndMetadata(x + 4, y + 1, z + 4, Block.bed.blockID, 2);
		world.setBlockAndMetadata(x + 4, y + 1, z + 5, Block.planks.blockID, 1);
		world.setBlockAndMetadata(x + 4, y + 2, z + 2, Block.planks.blockID, 1);
		world.setBlockAndMetadata(x + 4, y + 2, z + 5, Block.planks.blockID, 1);
		world.setBlockAndMetadata(x + 4, y + 3, z + 1, Block.stairCompactPlanks.blockID, 2);
		world.setBlock(x + 4, y + 3, z + 2, Block.planks.blockID);
		world.setBlock(x + 4, y + 3, z + 5, Block.planks.blockID);
		world.setBlockAndMetadata(x + 4, y + 3, z + 6, Block.stairCompactPlanks.blockID, 3);
		world.setBlockAndMetadata(x + 4, y + 4, z + 2, Block.stairCompactPlanks.blockID, 2);
		world.setBlockAndMetadata(x + 4, y + 4, z + 5, Block.stairCompactPlanks.blockID, 3);
		world.setBlockAndMetadata(x + 4, y + 5, z + 3, Block.stairCompactPlanks.blockID, 2);
		world.setBlockAndMetadata(x + 4, y + 5, z + 4, Block.stairCompactPlanks.blockID, 1);
		world.setBlock(x + 5, y, z + 2, Block.cobblestone.blockID);
		world.setBlock(x + 5, y, z + 4, Block.cobblestone.blockID);
		world.setBlock(x + 5, y, z + 5, Block.cobblestone.blockID);
		world.setBlockAndMetadata(x + 5, y + 1, z + 2, Block.wood.blockID, 1);
		world.setBlockAndMetadata(x + 5, y + 1, z + 3, Block.planks.blockID, 1);
		world.setBlockAndMetadata(x + 5, y + 1, z + 4, Block.planks.blockID, 1);
		world.setBlockAndMetadata(x + 5, y + 1, z + 5, Block.wood.blockID, 1);
		world.setBlockAndMetadata(x + 5, y + 2, z + 2, Block.wood.blockID, 1);
		world.setBlockAndMetadata(x + 5, y + 2, z + 3, Block.planks.blockID, 1);
		world.setBlockAndMetadata(x + 5, y + 2, z + 4, Block.planks.blockID, 1);
		world.setBlockAndMetadata(x + 5, y + 2, z + 5, Block.wood.blockID, 1);
		world.setBlockAndMetadata(x + 5, y + 3, z + 1, Block.stairCompactPlanks.blockID, 2);
		world.setBlockAndMetadata(x + 5, y + 3, z + 2, Block.wood.blockID, 1);
		world.setBlock(x + 5, y + 3, z + 3, Block.planks.blockID);
		world.setBlock(x + 5, y + 3, z + 4, Block.planks.blockID);
		world.setBlockAndMetadata(x + 5, y + 3, z + 5, Block.wood.blockID, 1);
		world.setBlockAndMetadata(x + 5, y + 3, z + 6, Block.stairCompactPlanks.blockID, 3);
		world.setBlockAndMetadata(x + 5, y + 4, z + 2, Block.stairCompactPlanks.blockID, 1);
		world.setBlockAndMetadata(x + 5, y + 4, z + 3, Block.stairCompactPlanks.blockID, 1);
		world.setBlockAndMetadata(x + 5, y + 4, z + 4, Block.stairCompactPlanks.blockID, 1);
		world.setBlockAndMetadata(x + 5, y + 4, z + 5, Block.stairCompactPlanks.blockID, 1);
		world.setBlockAndMetadata(x + 6, y + 3, z + 1, Block.stairCompactPlanks.blockID, 1);
		world.setBlockAndMetadata(x + 6, y + 3, z + 2, Block.stairCompactPlanks.blockID, 1);
		world.setBlockAndMetadata(x + 6, y + 3, z + 3, Block.stairCompactPlanks.blockID, 1);
		world.setBlockAndMetadata(x + 6, y + 3, z + 4, Block.stairCompactPlanks.blockID, 1);
		world.setBlockAndMetadata(x + 6, y + 3, z + 5, Block.stairCompactPlanks.blockID, 1);
		world.setBlockAndMetadata(x + 6, y + 3, z + 6, Block.stairCompactPlanks.blockID, 1);
		world.setBlockAndMetadataWithNotify(x + 3, y + 1, z + 5, Block.doorWood.blockID, 3);
		world.setBlockAndMetadataWithNotify(x + 3, y + 2, z + 5, Block.doorWood.blockID, 8);
		world.setBlockAndMetadataWithNotify(x + 3, y + 3, z + 4, Block.torchWood.blockID, 4);

		return true;
	}
}