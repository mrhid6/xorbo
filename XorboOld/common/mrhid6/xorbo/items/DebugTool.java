package mrhid6.xorbo.items;

import mrhid6.xorbo.CommonProxy;
import mrhid6.xorbo.Utils;
import mrhid6.xorbo.blocks.ModBlocks;
import mrhid6.xorbo.tileentities.TETriniumChillerCore;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;

public class DebugTool extends ItemTex{
	public DebugTool(int id)
	{
		super(id);
		this.canRepair = false;
	}

	public DebugTool(int id, int maxStackSize, String name)
	{
		super(id,maxStackSize,"xorbo:debug",name);
		this.canRepair = false;
	}

	public DebugTool(int id, int maxStackSize, CreativeTabs tab, String name)
	{
		super(id,maxStackSize,tab,"xorbo:debug",name);
		this.canRepair = false;
	}

	@Override
	public boolean onItemUseFirst(ItemStack stack, EntityPlayer player,
			World world, int x, int y, int z, int side, float hitX, float hitY,
			float hitZ) {
		
		int bi = world.getBlockId(x, y, z);
		int md = world.getBlockMetadata(x, y, z);
		boolean result = false;
		ForgeDirection direction = ForgeDirection.getOrientation(side);

		if( (bi==ModBlocks.stearilliumSmoothStone.blockID) || (bi == ModBlocks.triniumBrick.blockID) || (bi == Block.fenceIron.blockID)){
			//player.addChatMessage("" +world.isRemote);
			result = createArcaneFurnace(stack, player, world, x, y, z);
		}

		System.out.println(md);
		return result;
	}

	public boolean createArcaneFurnace(ItemStack itemstack, EntityPlayer player, World world, int x, int y, int z) {
		for (int xx = x - 2; xx <= x; xx++) {
			for (int yy = y - 2; yy <= y; yy++){
				for (int zz = z - 2; zz <= z; zz++){

					if (fitArcaneFurnace(world, xx, yy, zz)) {
						if(Utils.isServerWorld(world)){
							replaceArcaneFurnace(world, xx, yy, zz);
							return true;
						}
						return false;
					}
				}
			}
		}
		return false;
	}

	public boolean fitArcaneFurnace(World world, int x, int y, int z) {
		int bo = ModBlocks.stearilliumSmoothStone.blockID;
		int bn = ModBlocks.triniumBrick.blockID;
		int bf = Block.fenceIron.blockID;
		int bl = Block.ice.blockID;

		int[][][] blueprint = { { { bn, bo, bn }, { bo, bo, bo }, { bn, bo, bn } }, { { bo, bo, bo }, { bo, bl, bo }, { bo, bo, bo } }, { { bn, bo, bn }, { bo, bo, bo }, { bn, bo, bn } } };

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

	public static boolean replaceArcaneFurnace(World world, int x, int y, int z)
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
						world.setBlockAndMetadataWithNotify(x + xx, y + yy, z + zz, ModBlocks.triniumChiller.blockID, md,0);
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
