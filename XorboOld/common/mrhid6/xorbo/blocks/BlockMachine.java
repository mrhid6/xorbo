package mrhid6.xorbo.blocks;

import java.util.Random;

import mrhid6.xorbo.Xorbo;
import mrhid6.xorbo.tileentities.TileEntityBlock;
import mrhid6.xorbo.tileentities.TileZoroFurnace;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public abstract class BlockMachine extends TexBlock
{

	public BlockMachine(int id, String texture, String name, boolean craftable) {
		super(id, texture, name, craftable);
	}

	@Override
	public void onBlockAdded(World par1World, int par2, int par3, int par4)
	{
		super.onBlockAdded(par1World, par2, par3, par4);
		this.setDefaultDirection(par1World, par2, par3, par4);
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int idk, float what, float these, float are)
	{
		TileEntity tileEntity = world.getBlockTileEntity(x, y, z);

		if (tileEntity == null || player.isSneaking()){
			return false;
		}

		//code to open gui explained later
		player.openGui(Xorbo.instance, 0, world, x, y, z);
		return true;
	}
	
	@Override
	public void onBlockPlacedBy(World par1World, int par2, int par3, int par4, EntityLiving par5EntityLiving, ItemStack par6ItemStack) {
		int var6 = MathHelper.floor_double((double)(par5EntityLiving.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;

		if (var6 == 0){
			par1World.setBlockMetadataWithNotify(par2, par3, par4, 2,0);
		}

		if (var6 == 1){
			par1World.setBlockMetadataWithNotify(par2, par3, par4, 5,0);
		}

		if (var6 == 2){
			par1World.setBlockMetadataWithNotify(par2, par3, par4, 3,0);
		}

		if (var6 == 3){
			par1World.setBlockMetadataWithNotify(par2, par3, par4, 4, 0);
		}
	}

	private void setDefaultDirection(World par1World, int par2, int par3, int par4)
	{
		if (!par1World.isRemote)
		{
			int var5 = par1World.getBlockId(par2, par3, par4 - 1);
			int var6 = par1World.getBlockId(par2, par3, par4 + 1);
			int var7 = par1World.getBlockId(par2 - 1, par3, par4);
			int var8 = par1World.getBlockId(par2 + 1, par3, par4);
			byte var9 = 3;

			if (Block.opaqueCubeLookup[var5] && !Block.opaqueCubeLookup[var6])
			{
				var9 = 3;
			}

			if (Block.opaqueCubeLookup[var6] && !Block.opaqueCubeLookup[var5])
			{
				var9 = 2;
			}

			if (Block.opaqueCubeLookup[var7] && !Block.opaqueCubeLookup[var8])
			{
				var9 = 5;
			}

			if (Block.opaqueCubeLookup[var8] && !Block.opaqueCubeLookup[var7])
			{
				var9 = 4;
			}

			par1World.setBlockMetadataWithNotify(par2, par3, par4, var9,0);
		}
	}

	@Override
	public void breakBlock(World world, int x, int y, int z, int par5, int par6)
	{
		dropItems(world, x, y, z);
		super.breakBlock(world, x, y, z, par5, par6);
	}

	private void dropItems(World world, int x, int y, int z)
	{
		Random rand = new Random();
		TileEntity tileEntity = world.getBlockTileEntity(x, y, z);

		if (!(tileEntity instanceof IInventory))
		{
			return;
		}

		IInventory inventory = (IInventory) tileEntity;

		for (int i = 0; i < inventory.getSizeInventory(); i++)
		{
			ItemStack item = inventory.getStackInSlot(i);

			if (item != null && item.stackSize > 0)
			{
				float rx = rand.nextFloat() * 0.8F + 0.1F;
				float ry = rand.nextFloat() * 0.8F + 0.1F;
				float rz = rand.nextFloat() * 0.8F + 0.1F;
				EntityItem entityItem = new EntityItem(world,
						x + rx, y + ry, z + rz,
						new ItemStack(item.itemID, item.stackSize, item.getItemDamage()));

				if (item.hasTagCompound())
				{
					//entityItem.func_92014_d().setTagCompound((NBTTagCompound) item.getTagCompound().copy());
				}

				float factor = 0.05F;
				entityItem.motionX = rand.nextGaussian() * factor;
				entityItem.motionY = rand.nextGaussian() * factor + 0.2F;
				entityItem.motionZ = rand.nextGaussian() * factor;
				world.spawnEntityInWorld(entityItem);
				item.stackSize = 0;
			}
		}
	}

	@Override
	public TileEntity createNewTileEntity(World var1)
	{
		return null;
	}
}