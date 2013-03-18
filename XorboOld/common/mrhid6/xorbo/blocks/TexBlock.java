package mrhid6.xorbo.blocks;

import java.util.Random;

import mrhid6.xorbo.Xorbo;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public abstract class TexBlock extends BlockContainer
{
	private String name;
	protected String textureName;
	public Icon[] icons;

	public TexBlock(int id, String texture, String name, boolean craftable) {
		super(id, Material.ground);
		
		this.textureName = texture.toLowerCase();
		this.name = name;
		if(craftable){
			this.setCreativeTab(Xorbo.tabsXor);
		}else{
			this.setCreativeTab(null);
		}
		this.setHardness(89.3F);
		this.setResistance(89.5F);
		this.setStepSound(soundMetalFootstep);
		this.setUnlocalizedName(name);
	}

    @Override
    public int idDropped(int par1, Random random, int zero)
    {
        return this.blockID;
    }

    @Override
	public void func_94332_a(IconRegister iconRegister){
		field_94336_cN = iconRegister.func_94245_a("xorbo:"+textureName);
	}
    
    @Override
	@SideOnly(Side.CLIENT)
	public abstract Icon getBlockTexture(IBlockAccess par1iBlockAccess, int x,int y, int z, int blockSide);
	
    
	@Override
	@SideOnly(Side.CLIENT)
	public abstract Icon getBlockTextureFromSideAndMetadata(int side, int meta);
}
