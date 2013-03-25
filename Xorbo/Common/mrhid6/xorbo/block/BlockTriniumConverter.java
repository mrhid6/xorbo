package mrhid6.xorbo.block;

import mrhid6.xorbo.Config;
import mrhid6.xorbo.render.BRTriniumConverter;
import mrhid6.xorbo.tileEntity.TETriniumConverter;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockTriniumConverter extends BlockMachine{

	public BlockTriniumConverter(int id, String texture, String name) {
		super(id, texture, name, true);
		
	}

	@Override
	public TileEntity createNewTileEntity(World world) {
		return new TETriniumConverter();
	}
	
	@Override
	public void registerIcons(IconRegister iconRegister){
		this.blockIcon = iconRegister.registerIcon("xorbo:TriniumConverterOff");
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public Icon getBlockTextureFromSideAndMetadata(int side, int meta) {
		return this.blockIcon;
	}
	
	public boolean isOpaqueCube() {
		return false;
	}

	public boolean renderAsNormalBlock() {
		return false;
	}
	
	public int getRenderType(){
		return BRTriniumConverter.renderID;
	}
	
}
