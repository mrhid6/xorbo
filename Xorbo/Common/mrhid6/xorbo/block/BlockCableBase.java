package mrhid6.xorbo.block;

import java.util.List;

import mrhid6.xorbo.Config;
import mrhid6.xorbo.tileEntity.TECableBase;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockCableBase extends BlockTexturedBase{

	public BlockCableBase(int id, int textureid, String name,boolean craftable) {
		super(id, textureid, name, craftable);
		
		this.setResistance(4.0F);
		this.setHardness(4.0F);
		this.setStepSound(soundClothFootstep);

	}
	@Override
	public TileEntity createNewTileEntity(World world) {
		return new TECableBase();
	}

	public boolean isBlockNormalCube(){
		return false;
	}

	public boolean renderAsNormalBlock(){
		return false;
	}

	public int getRenderType(){
		return Config.getRenderId("cable");
	}

	public boolean isOpaqueCube(){
		return false;
	}

	public void addCollidingBlockToList(World world, int x, int y, int z, AxisAlignedBB axis, List list, Entity entity){
		setBlockBounds(0.25F, 0.25F, 0.25F, 0.75F, 0.75F, 0.75F);
		super.addCollidingBlockToList(world, x, y, z, axis, list, entity);

		TECableBase theTile = (TECableBase)world.getBlockTileEntity(x, y, z);

		if (theTile.canInteractWith(world.getBlockTileEntity(x, y - 1, z))) {
			setBlockBounds(0.25F, 0.0F, 0.25F, 0.75F, 0.75F, 0.75F);
			super.addCollidingBlockToList(world, x, y, z, axis, list, entity);
		}

		if (theTile.canInteractWith(world.getBlockTileEntity(x, y + 1, z))) {
			setBlockBounds(0.25F, 0.25F, 0.25F, 0.75F, 1.0F, 0.75F);
			super.addCollidingBlockToList(world, x, y, z, axis, list, entity);
		}

		if (theTile.canInteractWith(world.getBlockTileEntity(x, y, z - 1))) {
			setBlockBounds(0.25F, 0.25F, 0.0F, 0.75F, 0.75F, 0.75F);
			super.addCollidingBlockToList(world, x, y, z, axis, list, entity);
		}

		if (theTile.canInteractWith(world.getBlockTileEntity(x, y, z + 1))) {
			setBlockBounds(0.25F, 0.25F, 0.25F, 0.75F, 0.75F, 1.0F);
			super.addCollidingBlockToList(world, x, y, z, axis, list, entity);
		}

		if (theTile.canInteractWith(world.getBlockTileEntity(x - 1, y, z))) {
			setBlockBounds(0.0F, 0.25F, 0.25F, 0.75F, 0.75F, 0.75F);
			super.addCollidingBlockToList(world, x, y, z, axis, list, entity);
		}

		if (theTile.canInteractWith(world.getBlockTileEntity(x + 1, y, z))) {
			setBlockBounds(0.25F, 0.25F, 0.25F, 1.0F, 0.75F, 0.75F);
			super.addCollidingBlockToList(world, x, y, z, axis, list, entity);
		}


		setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
	}

	public void setBlockBoundsBasedOnState(IBlockAccess world, int x, int y, int z){

		
		TECableBase cable = (TECableBase)world.getBlockTileEntity(x, y, z);
		
		double halfThickness = TECableBase.getCableThickness()/2.0D;
		
		float minX = (float) (0.5F - halfThickness);
		float minY = (float) (0.5F - halfThickness);
		float minZ = (float) (0.5F - halfThickness);
		float maxX = (float) (0.5F + halfThickness);
		float maxY = (float) (0.5F + halfThickness);
		float maxZ = (float) (0.5F + halfThickness);

		if (cable.canInteractWith(world.getBlockTileEntity(x - 1, y, z))) minX = 0;
		if (cable.canInteractWith(world.getBlockTileEntity(x, y - 1, z))) minY = 0;
		if (cable.canInteractWith(world.getBlockTileEntity(x, y, z - 1))) minZ = 0;
		if (cable.canInteractWith(world.getBlockTileEntity(x + 1, y, z))) maxX = 1;
		if (cable.canInteractWith(world.getBlockTileEntity(x, y + 1, z))) maxY = 1;
		if (cable.canInteractWith(world.getBlockTileEntity(x, y, z + 1))) maxZ = 1;
		
		setBlockBounds(minX, minY, minZ, maxX, maxY, maxZ);
		
	}

	public AxisAlignedBB getSelectedBoundingBoxFromPool(World world, int x, int y, int z){

		TECableBase cable = (TECableBase)world.getBlockTileEntity(x, y, z);
		double halfThickness = TECableBase.getCableThickness()/2.0D;

		double minX = x + 0.5D - halfThickness;
		double minY = y + 0.5D - halfThickness;
		double minZ = z + 0.5D - halfThickness;
		double maxX = x + 0.5D + halfThickness;
		double maxY = y + 0.5D + halfThickness;
		double maxZ = z + 0.5D + halfThickness;

		if (cable.canInteractWith(world.getBlockTileEntity(x - 1, y, z))) minX = x;
		if (cable.canInteractWith(world.getBlockTileEntity(x, y - 1, z))) minY = y;
		if (cable.canInteractWith(world.getBlockTileEntity(x, y, z - 1))) minZ = z;
		if (cable.canInteractWith(world.getBlockTileEntity(x + 1, y, z))) maxX = x + 1;
		if (cable.canInteractWith(world.getBlockTileEntity(x, y + 1, z))) maxY = y + 1;
		if (cable.canInteractWith(world.getBlockTileEntity(x, y, z + 1))) maxZ = z + 1;

		return AxisAlignedBB.getBoundingBox(minX, minY, minZ, maxX, maxY, maxZ);
	}

}
