package mrhid6.xorbo.block;

import java.util.List;

import mrhid6.xorbo.Config;
import mrhid6.xorbo.tileEntity.TECableBase;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockCableBase extends BlockTexturedBase{

	public BlockCableBase(int id, String textureName, String name,boolean craftable) {
		super(id, textureName, name, craftable);
		
		this.setResistance(4.0F);
		this.setHardness(4.0F);
		this.setStepSound(soundClothFootstep);

	}

	public boolean isBlockNormalCube(){
		return false;
	}

	public boolean renderAsNormalBlock(){
		return false;
	}

	public int getRenderType(){
		return Config.getRenderId("zorocable");
	}

	public boolean isOpaqueCube(){
		return false;
	}
	

	@Override
	public void breakBlock(World world, int x, int y, int z, int par5, int par6) {
		
		TECableBase tile = (TECableBase)world.getBlockTileEntity(x, y, z);
		
		if(tile!=null){
			tile.breakBlock();
		}
		
		super.breakBlock(world, x, y, z, par5, par6);
	}
	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, int blockID) {
		super.onNeighborBlockChange(world, x, y, z, blockID);
		
		TECableBase tile = (TECableBase)world.getBlockTileEntity(x, y, z);
		tile.onNeighborBlockChange();
	}
	public void addCollisionBoxesToList(World world, int x, int y, int z, AxisAlignedBB axis, List list, Entity entity){
		setBlockBounds(0.25F, 0.25F, 0.25F, 0.75F, 0.75F, 0.75F);
		
		super.addCollisionBoxesToList(world, x, y, z, axis, list, entity);

		TECableBase theTile = (TECableBase)world.getBlockTileEntity(x, y, z);

		if (theTile.canInteractWith(world.getBlockTileEntity(x, y - 1, z),1)) {
			setBlockBounds(0.25F, 0.0F, 0.25F, 0.75F, 0.75F, 0.75F);
			super.addCollisionBoxesToList(world, x, y, z, axis, list, entity);
		}

		if (theTile.canInteractWith(world.getBlockTileEntity(x, y + 1, z),0)) {
			setBlockBounds(0.25F, 0.25F, 0.25F, 0.75F, 1.0F, 0.75F);
			super.addCollisionBoxesToList(world, x, y, z, axis, list, entity);
		}

		if (theTile.canInteractWith(world.getBlockTileEntity(x, y, z - 1),2)) {
			setBlockBounds(0.25F, 0.25F, 0.0F, 0.75F, 0.75F, 0.75F);
			super.addCollisionBoxesToList(world, x, y, z, axis, list, entity);
		}

		if (theTile.canInteractWith(world.getBlockTileEntity(x, y, z + 1),3)) {
			setBlockBounds(0.25F, 0.25F, 0.25F, 0.75F, 0.75F, 1.0F);
			super.addCollisionBoxesToList(world, x, y, z, axis, list, entity);
		}

		if (theTile.canInteractWith(world.getBlockTileEntity(x - 1, y, z),4)) {
			setBlockBounds(0.0F, 0.25F, 0.25F, 0.75F, 0.75F, 0.75F);
			super.addCollisionBoxesToList(world, x, y, z, axis, list, entity);
		}

		if (theTile.canInteractWith(world.getBlockTileEntity(x + 1, y, z),5)) {
			setBlockBounds(0.25F, 0.25F, 0.25F, 1.0F, 0.75F, 0.75F);
			super.addCollisionBoxesToList(world, x, y, z, axis, list, entity);
		}


		setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
	}

	public void setBlockBoundsBasedOnState(IBlockAccess world, int x, int y, int z){

		
		TECableBase cable = (TECableBase)world.getBlockTileEntity(x, y, z);
		
		double halfThickness = cable.getCableThickness()/2.0D;
		
		float minX = (float) (0.5F - halfThickness);
		float minY = (float) (0.5F - halfThickness);
		float minZ = (float) (0.5F - halfThickness);
		float maxX = (float) (0.5F + halfThickness);
		float maxY = (float) (0.5F + halfThickness);
		float maxZ = (float) (0.5F + halfThickness);

		if (cable.canInteractWith(world.getBlockTileEntity(x - 1, y, z),4)) minX = 0;
		if (cable.canInteractWith(world.getBlockTileEntity(x, y - 1, z),0)) minY = 0;
		if (cable.canInteractWith(world.getBlockTileEntity(x, y, z - 1),2)) minZ = 0;
		if (cable.canInteractWith(world.getBlockTileEntity(x + 1, y, z),5)) maxX = 1;
		if (cable.canInteractWith(world.getBlockTileEntity(x, y + 1, z),1)) maxY = 1;
		if (cable.canInteractWith(world.getBlockTileEntity(x, y, z + 1),3)) maxZ = 1;
		
		setBlockBounds(minX, minY, minZ, maxX, maxY, maxZ);
		
	}

	public AxisAlignedBB getSelectedBoundingBoxFromPool(World world, int x, int y, int z){

		TECableBase cable = (TECableBase)world.getBlockTileEntity(x, y, z);
		double halfThickness = cable.getCableThickness()/2.0D;

		double minX = x + 0.5D - halfThickness;
		double minY = y + 0.5D - halfThickness;
		double minZ = z + 0.5D - halfThickness;
		double maxX = x + 0.5D + halfThickness;
		double maxY = y + 0.5D + halfThickness;
		double maxZ = z + 0.5D + halfThickness;

		if (cable.canInteractWith(world.getBlockTileEntity(x - 1, y, z),4)) minX = x;
		if (cable.canInteractWith(world.getBlockTileEntity(x, y - 1, z),0)) minY = y;
		if (cable.canInteractWith(world.getBlockTileEntity(x, y, z - 1),2)) minZ = z;
		if (cable.canInteractWith(world.getBlockTileEntity(x + 1, y, z),5)) maxX = x + 1;
		if (cable.canInteractWith(world.getBlockTileEntity(x, y + 1, z),1)) maxY = y + 1;
		if (cable.canInteractWith(world.getBlockTileEntity(x, y, z + 1),3)) maxZ = z + 1;

		return AxisAlignedBB.getBoundingBox(minX, minY, minZ, maxX, maxY, maxZ);
	}
	@Override
	@SideOnly(Side.CLIENT)
	public Icon getBlockTexture(IBlockAccess par1iBlockAccess, int x, int y, int z, int blockSide) {
		return this.blockIcon;
	}

}
