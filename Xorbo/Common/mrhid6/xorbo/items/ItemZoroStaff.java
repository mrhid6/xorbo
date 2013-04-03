package mrhid6.xorbo.items;

import mrhid6.xorbo.Utils;
import mrhid6.xorbo.block.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;


public class ItemZoroStaff extends ItemTexturedBase{

	public ItemZoroStaff( int id, String name ) {
		super(id, 1, name);

		this.setMaxDamage(20);
	}

	@Override
	public boolean onItemUseFirst(ItemStack stack, EntityPlayer player,
			World world, int x, int y, int z, int side, float hitX, float hitY,
			float hitZ) {
		int bi = world.getBlockId(x, y, z);
		int md = world.getBlockMetadata(x, y, z);

		System.out.println(md);
		boolean result = false;
		ForgeDirection direction = ForgeDirection.getOrientation(side);

		if( (bi==ModBlocks.stearilliumStone.blockID) || (bi == ModBlocks.triniumBrick.blockID) || (bi == Block.fenceIron.blockID)){
			//player.addChatMessage("" +world.isRemote);
			result = createChiller(stack, player, world, x, y, z);
		}

		if((bi == ModBlocks.triniumBrick.blockID || bi == ModBlocks.stearilliumGlass.blockID) && result == false){
			result = createReactor(stack, player, world, x, y, z);
		}

		return result;
	}

	public boolean createReactor(ItemStack itemstack, EntityPlayer player, World world, int x, int y, int z){
		for (int xx = x - 3; xx <= x; xx++) {
			for (int yy = y - 3; yy <= y; yy++){
				for (int zz = z - 3; zz <= z; zz++){

					if(fitReactor(world, xx, yy, zz)){
						if(Utils.isServerWorld()){
							
							System.out.println("ok");
							//replaceReactor(world,xx,yy,zz);

							return true;
						}
					}
				}
			}
		}


		return false;
	}

	public boolean fitReactor(World world, int x, int y, int z) {
		int ss = ModBlocks.stearilliumStone.blockID;
		int tb = ModBlocks.triniumBrick.blockID;
		int zb = ModBlocks.zoroBrick.blockID;
		int gl = ModBlocks.stearilliumGlass.blockID;
		int ai = 0;

		int reactorcount = 0;

		int[][][] blueprint = { 
				{ { tb, tb, tb, tb }, { tb, tb, tb, tb }, { tb, tb, tb, tb }, { tb, tb, tb, tb }}, 
				{ { tb, gl, gl, tb }, { gl, ss, ss, gl }, { gl, ss, ss, gl }, { tb, gl, gl, tb }}, 
				{ { tb, gl, gl, tb }, { gl, ss, ss, gl }, { gl, ss, ss, gl }, { tb, gl, gl, tb }}, 
				{ { tb, tb, tb, tb }, { tb, tb, tb, tb }, { tb, tb, tb, tb }, { tb, tb, tb, tb }}
		};

		boolean fencefound = false;
		for (int yy = 0; yy < 4; yy++) {
			for (int xx = 0; xx < 4; xx++)
				for (int zz = 0; zz < 4; zz++) {
					int block = world.getBlockId(x + xx, y - yy + 3, z + zz);
					if (block != blueprint[yy][xx][zz]){
						return false;
					}
				}
		}
		return true;
	}

	public static boolean replaceReactor(World world, int x, int y, int z)
	{
		for (int yy = 0; yy < 7; yy++) {
			int step = 1;
			for (int zz = 0; zz < 5; zz++) {
				for (int xx = 0; xx < 5; xx++)
				{
					int md = step;

					int blockid = world.getBlockId(x + xx, y + yy, z + zz);

					if (blockid == ModBlocks.stearilliumStone.blockID) md = 50;
					if (blockid == ModBlocks.zoroBrick.blockID) md = 15;

					if(blockid!=0){
						//System.out.println(md);

						if(md>=16){
							blockid = 1;
						}
						world.setBlock(x + xx, y + yy, z + zz, blockid,md,2);
						world.addBlockEvent(x + xx, y + yy, z + zz, blockid, 1, 4);
						step++;
					}
				}
			}
		}

		world.markBlockRangeForRenderUpdate(x, y, z, x + 5, y + 7, z + 5);
		return true;
	}

	public boolean createChiller(ItemStack itemstack, EntityPlayer player, World world, int x, int y, int z) {
		for (int xx = x - 2; xx <= x; xx++) {
			for (int yy = y - 2; yy <= y; yy++){
				for (int zz = z - 2; zz <= z; zz++){

					if (fit(world, xx, yy, zz)) {
						if(Utils.isServerWorld()){
							if(itemstack.getItemDamageForDisplay()+5<=itemstack.getMaxDamage() && !player.capabilities.isCreativeMode){
								itemstack.setItemDamage(itemstack.getItemDamageForDisplay()+5);
								replace(world, xx, yy, zz);
								return true;
							}else if(player.capabilities.isCreativeMode){
								replace(world, xx, yy, zz);
								return true;
							}
						}
						return false;
					}
				}
			}
		}
		return false;
	}

	public boolean fit(World world, int x, int y, int z) {
		int bo = ModBlocks.stearilliumStone.blockID;
		int bn = ModBlocks.triniumBrick.blockID;
		int bf = Block.fenceIron.blockID;
		int bl = Block.ice.blockID;

		int[][][] blueprint = { 
				{ { bn, bo, bn }, { bo, bo, bo }, { bn, bo, bn } }, 
				{ { bo, bo, bo }, { bo, bl, bo }, { bo, bo, bo } }, 
				{ { bn, bo, bn }, { bo, bo, bo }, { bn, bo, bn } } 
		};

		boolean fencefound = false;
		for (int yy = 0; yy < 3; yy++) {
			for (int xx = 0; xx < 3; xx++)
				for (int zz = 0; zz < 3; zz++) {
					int block = world.getBlockId(x + xx, y - yy + 2, z + zz);
					if (block != blueprint[yy][xx][zz]){
						if ((yy == 1) && (!fencefound) && (block == bf) && (xx != zz) && ((xx == 1) || (zz == 1))){
							fencefound = true;
						}else{ 
							return false;
						}
					}
				}
		}
		return fencefound;
	}

	public static boolean replace(World world, int x, int y, int z)
	{
		for (int yy = 0; yy < 3; yy++) {
			int step = 1;
			for (int zz = 0; zz < 3; zz++) {
				for (int xx = 0; xx < 3; xx++)
				{
					int md = step;
					if ((world.getBlockId(x + xx, y + yy, z + zz) == Block.ice.blockID))
						md = 0;
					if (world.getBlockId(x + xx, y + yy, z + zz) == Block.fenceIron.blockID) md = 10;

					if(md == 5 && yy == 2)
						md=11;

					if (!world.isAirBlock(x + xx, y + yy, z + zz))
					{
						world.setBlock(x + xx, y + yy, z + zz, ModBlocks.triniumChiller.blockID,md,2);
						world.addBlockEvent(x + xx, y + yy, z + zz, ModBlocks.triniumChiller.blockID, 1, 4);
					}

					step++;
				}
			}
		}

		world.markBlockRangeForRenderUpdate(x, y, z, x + 2, y + 2, z + 2);
		return true;
	}

}
