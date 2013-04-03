package mrhid6.xorbo;

import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.world.World;
import cpw.mods.fml.common.FMLCommonHandler;

public class Utils {

	public static final int[][] SIDE_COORD_MOD = { { 0, -1, 0 }, { 0, 1, 0 }, { 0, 0, -1 }, { 0, 0, 1 }, { -1, 0, 0 }, { 1, 0, 0 } };

	public static int[] getAdjacentCoordinatesForSide( int x, int y, int z, int side ) {
		return new int[] { x + SIDE_COORD_MOD[side][0], y + SIDE_COORD_MOD[side][1], z + SIDE_COORD_MOD[side][2] };
	}
	
	
	
	public static int getFuelFor( ItemStack item ) {

		if (item == null) {
			return 0;
		}

		int index = item.getItem().itemID;

		if (index == Item.bucketLava.itemID) {
			return 2000;
		}

		return TileEntityFurnace.getItemBurnTime(item);

	}

	public static List<ItemStack> getItemStackFromBlock( World world, int i, int j, int k ) {
		Block block = Block.blocksList[world.getBlockId(i, j, k)];

		if (block == null) {
			return null;
		}

		int meta = world.getBlockMetadata(i, j, k);

		return block.getBlockDropped(world, i, j, k, meta, 0);
	}

	public static TileEntity getTileEntity( World world, int x, int y, int z, int meta, int blockId ) {
		for (int yy = -2; yy <= 2; yy++) {
			for (int xx = -2; xx <= 2; xx++) {
				for (int zz = -2; zz <= 2; zz++) {

					int id = world.getBlockId(x + xx, y + yy, z + zz);
					int md = world.getBlockMetadata(x + xx, y + yy, z + zz);

					if (id == blockId && md == meta) {
						return world.getBlockTileEntity(x + xx, y + yy, z + zz);
					}

				}
			}
		}

		return null;
	}

	public static boolean isClientWorld() {
		return FMLCommonHandler.instance().getEffectiveSide().isClient();
	}

	public static boolean isServerWorld() {
		return !isClientWorld();
	}
}
