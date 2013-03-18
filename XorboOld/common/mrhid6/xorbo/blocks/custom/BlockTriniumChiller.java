package mrhid6.xorbo.blocks.custom;

import java.util.Random;

import mrhid6.xorbo.CommonProxy;
import mrhid6.xorbo.blocks.BlockMachine;
import mrhid6.xorbo.blocks.ModBlocks;
import mrhid6.xorbo.tileentities.TETriniumChillerBase;
import mrhid6.xorbo.tileentities.TETriniumChillerCore;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLiving;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockTriniumChiller extends BlockMachine{

	private int texture;

	public BlockTriniumChiller(int id,int texture) {
		
		super(id, Material.ground, 2.0F, 5F, "triniumchiller", texture);
	}
	
	public void breakBlock(World par1World, int par2, int par3, int par4, int par5, int par6){
		if (par1World.getBlockMetadata(par2, par3, par4) == 0)
		{
			restoreBlocks(par1World, par2, par3, par4);
		}

		for (int yy = -1; yy <= 1; yy++) {
			for (int xx = -1; xx <= 1; xx++)
				for (int zz = -1; zz <= 1; zz++)
					par1World.notifyBlocksOfNeighborChange(par2 + xx, par3 + yy, par4 + zz, this.blockID);
		}
		super.breakBlock(par1World, par2, par3, par4, par5, par6);
	}

	public int calculateLevel(IBlockAccess world, int x, int y, int z) {
		int meta = world.getBlockMetadata(x, y, z);
		int metaA = world.getBlockMetadata(x, y + 1, z);
		if ((metaA == 10) || (metaA == 0)) metaA = meta;
		int metaB = world.getBlockMetadata(x, y - 1, z);
		if ((metaB == 10) || (metaB == 0)) metaB = meta;
		int blockA = world.getBlockId(x, y + 1, z);
		int blockB = world.getBlockId(x, y - 1, z);
		if ((meta == metaA) && (meta == metaB) && (this.blockID == blockA) && (this.blockID == blockB))
			return 16;
		if ((meta == metaA) && (this.blockID == blockA) && ((meta != metaB) || (this.blockID != blockB)))
			return 32;
		return 0;
	}

	@Override
	public TileEntity createNewTileEntity(World var1) {
		return null;
	}

	@Override
	public TileEntity createNewTileEntity(World world, int metadata) {
		if(metadata == 5)return new TETriniumChillerCore();
		//return super.createNewTileEntity(world, metadata);
		return new TETriniumChillerBase();
	}

	public int getBlockTexture(IBlockAccess world, int x, int y, int z, int side){

		int meta = world.getBlockMetadata(x, y, z);
		int level = calculateLevel(world,x,y,z);
		int add = 0;
		if(isFrontFacing(world, x, y, z, 10, side)) add = 3;
		switch (side) { 
		case 0:
		case 1:
			if ((side == 1) && (level == 32))
				switch (meta) {
				case 2:
					return 23;
				case 4:
					return 24;
				case 6:
					return 40;
				case 8:
					return 39;
				case 3:
				case 5:
				case 7: 
				}
			if (add != 3) {
				if (meta == 5 || meta == 11) return 7; return (meta - 1) % 3 + (meta - 1) / 3 * 16;
			}break;
		case 2:
			switch (meta) { 
			default:
				if (level != 16) return 7; return 6;
			case 1:
				return 2 + level + add;
			case 2:
				return 1 + level + add;
			case 3: 
				return 0 + level + add;
			case 10: 
				return 20;
			} 
		case 3:
			switch (meta) { 
			default:
				if (level != 16) return 7; return 6;
			case 7:
				return 0 + level + add;
			case 8:
				return 1 + level + add;
			case 9: 
				return 2 + level + add;
			case 10: 
				return 20;
			} 
		case 4:
			switch (meta) { 
			default:
				if (level != 16) return 7; return 6;
			case 1:
				return 0 + level + add;
			case 4:
				return 1 + level + add;
			case 7: 
				return 2 + level + add;
			case 10: 
				return 20;
			} 
		case 5:
			switch (meta) { 
			default:
				if (level != 16) return 7; return 6;
			case 3:
				return 2 + level + add;
			case 6:
				return 1 + level + add;
			case 9:
				return 0 + level + add;
			case 10: 
				return 20;
			} 
		}

		return add == 0 ? 7 : 6;
	}

	@Override
	public String getTextureFile()
	{
		return CommonProxy.SFURNACE_PNG;
	}
	
	@Override
	public int idDropped(int meta, Random par2Random, int par3) {

		if( ((meta % 2 == 0) || (meta == 5) || (meta == 11) || (par3==0)) && (meta!=10)){
			return ModBlocks.zoroBrick.blockID;
		}else if(meta == 10){
			return Block.fenceIron.blockID;
		}else{
			return ModBlocks.triniumBrick.blockID;
		}
	}

	public boolean isFrontFacing(IBlockAccess world, int x, int y, int z,int md, int side){
		if (((side > 3) && (world.getBlockId(x, y, z + 1) == this.blockID) && (world.getBlockMetadata(x, y, z + 1) == md)) || ((side > 3) && (world.getBlockId(x, y, z - 1) == this.blockID) && (world.getBlockMetadata(x, y, z - 1) == md)) || ((side > 1) && (side < 4) && (world.getBlockId(x + 1, y, z) == this.blockID) && (world.getBlockMetadata(x + 1, y, z) == md)) || ((side > 1) && (side < 4) && (world.getBlockId(x - 1, y, z) == this.blockID) && (world.getBlockMetadata(x - 1, y, z) == md)) || ((side > 1) && (world.getBlockId(x, y + 1, z) == this.blockID) && (world.getBlockMetadata(x, y + 1, z) == md)) || ((side > 1) && (world.getBlockId(x, y - 1, z) == this.blockID) && (world.getBlockMetadata(x, y - 1, z) == md)))
		{
			return true;
		}
		if (((side > 3) && (world.getBlockId(x, y + 1, z + 1) == this.blockID) && (world.getBlockMetadata(x, y + 1, z + 1) == md)) || ((side > 3) && (world.getBlockId(x, y + 1, z - 1) == this.blockID) && (world.getBlockMetadata(x, y + 1, z - 1) == md)) || ((side > 1) && (side < 4) && (world.getBlockId(x + 1, y + 1, z) == this.blockID) && (world.getBlockMetadata(x + 1, y + 1, z) == md)) || ((side > 1) && (side < 4) && (world.getBlockId(x - 1, y + 1, z) == this.blockID) && (world.getBlockMetadata(x - 1, y + 1, z) == md)))
		{
			return true;
		}
		if (((side > 3) && (world.getBlockId(x, y - 1, z + 1) == this.blockID) && (world.getBlockMetadata(x, y - 1, z + 1) == md)) || ((side > 3) && (world.getBlockId(x, y - 1, z - 1) == this.blockID) && (world.getBlockMetadata(x, y - 1, z - 1) == md)) || ((side > 1) && (side < 4) && (world.getBlockId(x + 1, y - 1, z) == this.blockID) && (world.getBlockMetadata(x + 1, y - 1, z) == md)) || ((side > 1) && (side < 4) && (world.getBlockId(x - 1, y - 1, z) == this.blockID) && (world.getBlockMetadata(x - 1, y - 1, z) == md)))
		{
			return true;
		}

		return false;
	}

	@Override
	public void onBlockAdded(World par1World, int par2, int par3, int par4)
	{
		
	}

	@Override
	public void onBlockPlacedBy(World par1World, int par2, int par3, int par4, EntityLiving par5EntityLiving)
	{
		
	}

	public void onNeighborBlockChange(World par1World, int par2, int par3, int par4, int par5){
		int meta = par1World.getBlockMetadata(par2, par3, par4);

		if(meta == 0){
			for (int yy = -1; yy <= 1; yy++) {
				for (int xx = -1; xx <= 1; xx++) {
					for (int zz = -1; (zz <= 1) && (
							((yy != 0)) || (zz != 0) || (xx != 0)); zz++)
					{
						int block = par1World.getBlockId(par2 + xx, par3 + yy, par4 + zz);
						if (block != this.blockID)
						{
							restoreBlocks(par1World, par2, par3, par4);
							par1World.setBlockWithNotify(par2, par3, par4, 0);
							par1World.notifyBlocksOfNeighborChange(par2, par3, par4, par1World.getBlockId(par2, par3, par4));
							par1World.markBlockForUpdate(par2, par3, par4);
							return;
						}
					}
				}
			}
		}

		super.onNeighborBlockChange(par1World, par2, par3, par4, par5);
	}

	private void restoreBlocks(World par1World, int par2, int par3, int par4)
	{
		for (int yy = -1; yy <= 1; yy++)
			for (int xx = -1; xx <= 1; xx++)
				for (int zz = -1; zz <= 1; zz++) {
					int block = par1World.getBlockId(par2 + xx, par3 + yy, par4 + zz);
					int md = par1World.getBlockMetadata(par2 + xx, par3 + yy, par4 + zz);
					if (block == this.blockID) {
						block = idDropped(md, new Random(), yy);
						par1World.setBlockWithNotify(par2 + xx, par3 + yy, par4 + zz, block);

						par1World.notifyBlocksOfNeighborChange(par2 + xx, par3 + yy, par4 + zz, par1World.getBlockId(par2 + xx, par3 + yy, par4 + zz));
						par1World.markBlockForUpdate(par2 + xx, par3 + yy, par4 + zz);
					}
				}
	}
}