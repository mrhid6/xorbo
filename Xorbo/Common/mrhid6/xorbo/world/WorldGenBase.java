package mrhid6.xorbo.world;

import java.util.Random;

import mrhid6.xorbo.Utils;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
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
			genTrees1(world,random,chunkX,chunkZ);
			genTrees2(world,random,chunkX,chunkZ);
			break;

		case 1:
			//generateEnd(world, random, chunkX * 16, chunkZ * 16);
			break;
		}
	}
	
	public void genTrees1(World world,Random random,int chunkX, int chunkZ){
		BiomeGenBase biomegenbase = world.getWorldChunkManager().getBiomeGenAt(chunkX * 16 + 16, chunkZ * 16 + 16);
		int trees = 0;

		if (biomegenbase.biomeName.toLowerCase().equals("taiga")){
			trees += random.nextInt(5) + 3;
		}
		
		if (biomegenbase.biomeName.toLowerCase().equals("ice plains")){
			trees += random.nextInt(2) + 1;
		}
		
		if (biomegenbase.biomeName.toLowerCase().equals("ice mountains")){
			trees += random.nextInt(3) + 1;
		}

		if (random.nextInt(100) + 1 <= trees * 2){
			(new WorldGenWinterbirch()).generate(world, random, chunkX * 16 + random.nextInt(16), chunkZ * 16 + random.nextInt(16), trees);
		}
	}
	
	public void genTrees2(World world,Random random,int chunkX, int chunkZ){
		BiomeGenBase biomegenbase = world.getWorldChunkManager().getBiomeGenAt(chunkX * 16 + 16, chunkZ * 16 + 16);
		int trees = 0;
		
		if (biomegenbase.biomeName.toLowerCase().equals("forest")){
			trees += random.nextInt(3) + 1;
		}
		
		if (biomegenbase.biomeName.toLowerCase().equals("junglehills")){
			trees += random.nextInt(3) + 1;
		}
		
		if (biomegenbase.biomeName.toLowerCase().equals("jungle")){
			trees += random.nextInt(5) + 3;
		}
		
		if (random.nextInt(100) + 1 <= trees * 2){
			(new WorldGenHazelspring()).generate(world, random, chunkX * 16 + random.nextInt(16), chunkZ * 16 + random.nextInt(16), trees);
		}
	}
}
