package mrhid6.xorbo.world;

import java.util.Random;
import mrhid6.xorbo.Utils;
import mrhid6.xorbo.block.ModBlocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import cpw.mods.fml.common.IWorldGenerator;

public class WorldGenBase implements IWorldGenerator {

	@Override
	public void generate( Random random, int chunkX, int chunkZ, World world,
			IChunkProvider chunkGenerator, IChunkProvider chunkProvider ) {
		if (Utils.isClientWorld()) {
			return;
		}

		switch (world.provider.dimensionId) {
		// nether
		case -1:
			genStearilliumOre(world, random, chunkX * 16, chunkZ * 16);
			break;

		case 0:
			genTrees1(world, random, chunkX, chunkZ);
			genTrees2(world, random, chunkX, chunkZ);
			genPonds(world, random, chunkX, chunkZ);
			genZoroOre(world, random, chunkX * 16, chunkZ * 16);
			genTriniumOre(world, random, chunkX * 16, chunkZ * 16);
			break;

		case 1:
			// generateEnd(world, random, chunkX * 16, chunkZ * 16);
			break;
		}
	}

	private void genPonds( World world, Random random, int chunkX, int chunkZ ) {
		BiomeGenBase biomegenbase = world.getWorldChunkManager().getBiomeGenAt(
				chunkX * 16 + 16, chunkZ * 16 + 16);
		int count = 0;

		if (biomegenbase.biomeName.toLowerCase().equals("swampland")) {
			count += random.nextInt(5) + 5;
		}

		if (random.nextInt(100) + 1 <= count * 2) {

			(new WorldGenZoroPond()).generate(world, random, chunkX * 16
					+ random.nextInt(16), chunkZ * 16 + random.nextInt(16));
		}
	}

	private void genStearilliumOre( World world, Random rand, int chunkX,
			int chunkZ ) {
		for (int k = 0; k < 35; k++) {
			int firstBlockXCoord = chunkX + rand.nextInt(16);
			int firstBlockYCoord = rand.nextInt(256);
			int firstBlockZCoord = chunkZ + rand.nextInt(16);

			(new WorldGenNetherOre(ModBlocks.stearilliumOre.blockID, 12))
					.generate(world, rand, firstBlockXCoord, firstBlockYCoord,
							firstBlockZCoord);
		}
	}

	public void genTrees1( World world, Random random, int chunkX, int chunkZ ) {
		BiomeGenBase biomegenbase = world.getWorldChunkManager().getBiomeGenAt(
				chunkX * 16 + 16, chunkZ * 16 + 16);
		int trees = 0;

		if (biomegenbase.biomeName.toLowerCase().equals("taiga")) {
			trees += random.nextInt(5) + 3;
		}

		if (biomegenbase.biomeName.toLowerCase().equals("ice plains")) {
			trees += random.nextInt(2) + 1;
		}

		if (biomegenbase.biomeName.toLowerCase().equals("ice mountains")) {
			trees += random.nextInt(3) + 1;
		}

		if (random.nextInt(100) + 1 <= trees * 2) {
			(new WorldGenWinterbirch()).generate(world, random, chunkX * 16
					+ random.nextInt(16), chunkZ * 16 + random.nextInt(16),
					trees);
		}
	}

	public void genTrees2( World world, Random random, int chunkX, int chunkZ ) {
		BiomeGenBase biomegenbase = world.getWorldChunkManager().getBiomeGenAt(
				chunkX * 16 + 16, chunkZ * 16 + 16);
		int trees = 0;

		if (biomegenbase.biomeName.toLowerCase().equals("forest")) {
			trees += random.nextInt(3) + 1;
		}

		if (biomegenbase.biomeName.toLowerCase().equals("junglehills")) {
			trees += random.nextInt(5) + 1;
		}

		if (biomegenbase.biomeName.toLowerCase().equals("jungle")) {
			trees += random.nextInt(9) + 3;
		}

		if (random.nextInt(100) + 1 <= trees * 2) {
			(new WorldGenHazelspring()).generate(world, random, chunkX * 16
					+ random.nextInt(16), chunkZ * 16 + random.nextInt(16),
					trees);
		}
	}

	private void genTriniumOre( World world, Random rand, int chunkX, int chunkZ ) {
		for (int k = 0; k < 10; k++) {
			int firstBlockXCoord = chunkX + rand.nextInt(16);
			int firstBlockYCoord = rand.nextInt(64);
			int firstBlockZCoord = chunkZ + rand.nextInt(16);
			(new WorldGenMinable(ModBlocks.triniumOre.blockID, 4)).generate(
					world, rand, firstBlockXCoord, firstBlockYCoord,
					firstBlockZCoord);
		}
	}

	private void genZoroOre( World world, Random rand, int chunkX, int chunkZ ) {
		for (int k = 0; k < 10; k++) {
			int firstBlockXCoord = chunkX + rand.nextInt(16);
			int firstBlockYCoord = rand.nextInt(64);
			int firstBlockZCoord = chunkZ + rand.nextInt(16);
			(new WorldGenMinable(ModBlocks.zoroOre.blockID, 6)).generate(world,
					rand, firstBlockXCoord, firstBlockYCoord, firstBlockZCoord);
		}
	}
}
