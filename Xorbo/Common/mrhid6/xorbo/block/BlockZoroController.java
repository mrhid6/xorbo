package mrhid6.xorbo.block;

import mrhid6.xorbo.tileEntity.TECableBase;
import mrhid6.xorbo.tileEntity.TEZoroController;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockZoroController extends BlockTexturedBase{
	
	public Icon[] icons;
	
	public BlockZoroController(int id, String textureName, String name,boolean craftable) {
		super(id, textureName, name, craftable);

		this.setResistance(6.0F);
		this.setHardness(6.0F);
		icons = new Icon[2];
	}
	
	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, int blockID) {
		super.onNeighborBlockChange(world, x, y, z, blockID);
		
		TEZoroController tile = (TEZoroController)world.getBlockTileEntity(x, y, z);
		tile.onNeighborBlockChange();
	}

	@Override
	public TileEntity createNewTileEntity(World var1) {
		return new TEZoroController();
	}
	
	@Override
	public void breakBlock(World world, int x, int y, int z, int par5, int par6) {
		
		TEZoroController tile = (TEZoroController)world.getBlockTileEntity(x, y, z);
		
		if(tile!=null){
			tile.breakBlock();
		}
		
		super.breakBlock(world, x, y, z, par5, par6);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Icon getBlockTextureFromSideAndMetadata(int side, int meta) {
		
		if(side==1){
			return icons[0];
		}
		
		return icons[1];
	}
	
	@Override
	public void registerIcons(IconRegister iconRegister)
    {
		icons[0] = iconRegister.registerIcon("xorbo:"+textureName+"_top");
		icons[1] = iconRegister.registerIcon("xorbo:"+textureName+"_side");
    }

	@Override
	@SideOnly(Side.CLIENT)
	public Icon getBlockTexture(IBlockAccess par1iBlockAccess, int x, int y, int z, int blockSide) {
		
		return getBlockTextureFromSideAndMetadata(blockSide,0);
	}
	
	


}
