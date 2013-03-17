package mrhid6.xorbo.block;

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
	public TileEntity createNewTileEntity(World var1) {
		return new TEZoroController();
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
	public void func_94332_a(IconRegister iconRegister)
    {
		icons[0] = iconRegister.func_94245_a("xorbo:"+textureName+"_top");
		icons[1] = iconRegister.func_94245_a("xorbo:"+textureName+"_side");
    }

	@Override
	@SideOnly(Side.CLIENT)
	public Icon getBlockTexture(IBlockAccess par1iBlockAccess, int x, int y, int z, int blockSide) {
		
		return getBlockTextureFromSideAndMetadata(blockSide,0);
	}
	
	


}
