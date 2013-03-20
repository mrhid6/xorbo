package mrhid6.xorbo.world;

import java.util.Random;

import mrhid6.xorbo.Utils;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import cpw.mods.fml.common.IWorldGenerator;

public class WorldGenBase implements IWorldGenerator{


	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider)
	{
		if(Utils.isClientWorld(world))
			return;

		switch (world.provider.dimensionId){
		//nether
		case -1:break;

		case 0:
			(new WorldGenWinterbirch()).generate(world, random, chunkX * 16 + random.nextInt(16), chunkZ * 16 + random.nextInt(16),3);
			break;

		case 1:
			//generateEnd(world, random, chunkX * 16, chunkZ * 16);
			break;
		}
	}
}
