package mrhid6.xorbo;

import java.util.Random;

import mrhid6.xorbo.blocks.ModBlocks;
import mrhid6.xorbo.worldgen.SurfaceGenFactory;
import mrhid6.xorbo.worldgen.SurfaceGenHouse;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import cpw.mods.fml.common.IWorldGenerator;

public class WorldGen implements IWorldGenerator
{
	private WorldGenTrees treegen;

	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider)
	{

		if(Utils.isClientWorld(world))
			return;

		switch (world.provider.dimensionId)
		{
		case -1:
			genStearilliumOre(world, random, chunkX * 16, chunkZ * 16);
			break;

		case 0:
			genZoroOre(world, random, chunkX * 16, chunkZ * 16);
			genTriniumOre(world, random, chunkX * 16, chunkZ * 16);
			genSurfaceHouse1(world, random, chunkX * 16, chunkZ * 16);
			genSurfaceFactory1(world, random, chunkX * 16, chunkZ * 16);
			break;

		case 1:
			//generateEnd(world, random, chunkX * 16, chunkZ * 16);
			break;
		}

		BiomeGenBase biomegenbase = world.getWorldChunkManager().getBiomeGenAt(chunkX * 16 + 16, chunkZ * 16 + 16);
		int trees = 0;

		if (biomegenbase.biomeName.toLowerCase().contains("taiga")){
			trees += random.nextInt(5) + 3;
		}

		if ((biomegenbase.biomeName.toLowerCase().contains("forest"))){
			trees += random.nextInt(6) + 3;
		}

		if (biomegenbase.biomeName.toLowerCase().contains("swamp")){
			trees += random.nextInt(3) + 3;
		}

		if (biomegenbase.biomeName.toLowerCase().contains("plains")){
			trees += random.nextInt(2) + 1;
		}

		if (random.nextInt(100) + 1 <= trees * 2){
			WorldGenTrees.generate(world, random, chunkX * 16 + random.nextInt(16), trees, chunkZ * 16 + random.nextInt(16));
		}
	}

	private void genZoroOre(World world, Random rand, int chunkX, int chunkZ)
	{
		for (int k = 0; k < 10; k++)
		{
			int firstBlockXCoord = chunkX + rand.nextInt(16);
			int firstBlockYCoord = rand.nextInt(64);
			int firstBlockZCoord = chunkZ + rand.nextInt(16);
			(new WorldGenMinable(ModBlocks.zoroOre.blockID, 6)).generate(world, rand, firstBlockXCoord, firstBlockYCoord, firstBlockZCoord);
		}
	}

	private void genSurfaceHouse1(World world, Random rand, int chunkX, int chunkZ){

		BiomeGenBase biomegenbase = world.getWorldChunkManager().getBiomeGenAt(chunkX + 16, chunkZ + 16);
		if(rand.nextInt(100)<30 && biomegenbase.biomeName.toLowerCase().equals("plains")){
			int firstBlockXCoord = chunkX + rand.nextInt(16);
			int firstBlockZCoord = chunkZ + rand.nextInt(16);
			(new SurfaceGenHouse()).generate(world, rand, firstBlockXCoord, firstBlockZCoord);
		}
	}

	private void genSurfaceFactory1(World world, Random rand, int chunkX, int chunkZ){
		BiomeGenBase biomegenbase = world.getWorldChunkManager().getBiomeGenAt(chunkX + 16, chunkZ + 16);
		
		if(biomegenbase.biomeName.toLowerCase().contains("jungle"))
			return;
		
		if(rand.nextInt(100)<20){
			int firstBlockXCoord = chunkX + rand.nextInt(16);
			int firstBlockYCoord = rand.nextInt(150);
			int firstBlockZCoord = chunkZ + rand.nextInt(16);
			(new SurfaceGenFactory()).generate(world, rand, firstBlockXCoord, firstBlockYCoord, firstBlockZCoord);
		}
	}

	private void genTriniumOre(World world, Random rand, int chunkX, int chunkZ)
	{
		for (int k = 0; k < 10; k++)
		{
			int firstBlockXCoord = chunkX + rand.nextInt(16);
			int firstBlockYCoord = rand.nextInt(64);
			int firstBlockZCoord = chunkZ + rand.nextInt(16);
			(new WorldGenMinable(ModBlocks.triniumOre.blockID, 4)).generate(world, rand, firstBlockXCoord, firstBlockYCoord, firstBlockZCoord);
		}
	}

	private void genStearilliumOre(World world, Random rand, int chunkX, int chunkZ)
	{
		for (int k = 0; k < 35; k++)
		{
			int firstBlockXCoord = chunkX + rand.nextInt(16);
			int firstBlockYCoord = rand.nextInt(256);
			int firstBlockZCoord = chunkZ + rand.nextInt(16);

			(new GenerateNether(ModBlocks.stearilliumOre.blockID, 12)).generate(world, rand, firstBlockXCoord, firstBlockYCoord, firstBlockZCoord);
		}
	}
}
