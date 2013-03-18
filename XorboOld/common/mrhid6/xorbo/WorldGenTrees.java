package mrhid6.xorbo;

import java.util.Random;

import mrhid6.xorbo.blocks.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.world.World;

public class WorldGenTrees
{
    public static boolean generate(World world, Random random, int x, int count, int z)
    {
        for (; count > 0; count--)
        {
            int y = Xorbo.getWorldHeight(world) - 1;

            while ((world.getBlockId(x, y - 1, z) == 0) && (y > 0))
            {
                y--;
            }

            if (!grow(world, x, y, z, random))
            {
                count -= 3;
            }

            x += random.nextInt(15) - 7;
            z += random.nextInt(15) - 7;
        }

        return true;
    }
    public static boolean grow(World world, int x, int y, int z, Random random)
    {
        if ((world == null) || (ModBlocks.heartThroLog == null))
        {
            System.out.println("[ERROR] Had a null that shouldn't have been. RubberTree did not spawn! w=" + world + " r=" + ModBlocks.heartThroLog);
            return false;
        }

        int treeholechance = 35;
        int h = getGrowHeight(world, x, y, z);

        if (h < 2)
        {
            return false;
        }

        int height = h / 2;
        h -= h / 2;
        height += random.nextInt(h + 1);

        for (int i = 0; i < height; i++)
        {
            world.setBlockAndMetadataWithNotify(x, y + i, z, ModBlocks.heartThroLog.blockID,0,0);

            if (random.nextInt(100) <= treeholechance)
            {
                treeholechance -= 10;
                world.setBlockAndMetadataWithNotify(x, y + i, z, random.nextInt(4) + 2,0,0);
            }
            else
            {
                world.setBlockMetadataWithNotify(x, y + i, z, 1,0);
            }

            if ((height < 4) || ((height < 7) && (i > 1)) || (i > 2))
            {
                for (int a = x - 2; a <= x + 2; a++)
                {
                    for (int b = z - 2; b <= z + 2; b++)
                    {
                        int c = i + 4 - height;

                        if (c < 1)
                        {
                            c = 1;
                        }

                        boolean gen = ((a > x - 2) && (a < x + 2) && (b > z - 2) && (b < z + 2)) || ((a > x - 2) && (a < x + 2) && (random.nextInt(c) == 0)) || ((b > z - 2) && (b < z + 2) && (random.nextInt(c) == 0));

                        if ((gen) && (world.getBlockId(a, y + i, b) == 0))
                        {
                            world.setBlockAndMetadataWithNotify(a, y + i, b, ModBlocks.heartThroLeaves.blockID,0,0);
                        }
                    }
                }
            }
        }

        for (int i = 0; i <= (height / 4) - 1; i++)
        {
            if (world.getBlockId(x, y + height + i, z) == 0)
            {
                world.setBlockAndMetadataWithNotify(x + 1, y + i + height, z, ModBlocks.heartThroLeaves.blockID,0,0);
                world.setBlockAndMetadataWithNotify(x - 1, y + i + height, z, ModBlocks.heartThroLeaves.blockID,0,0);
                world.setBlockAndMetadataWithNotify(x, y + i + height, z + 1, ModBlocks.heartThroLeaves.blockID,0,0);
                world.setBlockAndMetadataWithNotify(x, y + i + height, z - 1, ModBlocks.heartThroLeaves.blockID,0,0);
                world.setBlockAndMetadataWithNotify(x, y + i + height, z, ModBlocks.heartThroLeaves.blockID,0,0);
            }
        }

        return true;
    }

    public static int getGrowHeight(World world, int x, int y, int z)
    {
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
