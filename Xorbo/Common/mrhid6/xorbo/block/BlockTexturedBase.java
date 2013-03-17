package mrhid6.xorbo.block;

import mrhid6.xorbo.BlockIds;
import mrhid6.xorbo.Config;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public abstract class BlockTexturedBase extends BlockContainer{

	private String name;
	protected String textureName;
	public Icon[] icons;

	public BlockTexturedBase(int id, String texture, String name, boolean craftable) {
		super(id, Material.ground);
		
		this.textureName = texture.toLowerCase();
		this.name = name;
		if(craftable){
			this.setCreativeTab(Config.creativeTabXor);
		}else{
			this.setCreativeTab(null);
		}
		this.setHardness(89.3F);
		this.setResistance(89.5F);
		this.setStepSound(soundMetalFootstep);
		this.setUnlocalizedName(name);
	}
	
	public String getName(){
		return this.name;
	}
	
	
	
	@Override
	public void func_94332_a(IconRegister iconRegister){
		field_94336_cN = iconRegister.func_94245_a("xorbo:"+textureName);
	}

	@Override
	public TileEntity createNewTileEntity(World world) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public abstract Icon getBlockTexture(IBlockAccess par1iBlockAccess, int x,int y, int z, int blockSide);
	
	


}
