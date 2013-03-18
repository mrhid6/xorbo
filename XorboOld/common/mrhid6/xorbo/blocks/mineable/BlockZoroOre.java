package mrhid6.xorbo.blocks.mineable;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import mrhid6.xorbo.CommonProxy;
import mrhid6.xorbo.Xorbo;
import net.minecraft.block.Block;
import net.minecraft.block.BlockOre;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;

public class BlockZoroOre extends OreMain
{

	public BlockZoroOre(int id, String textureName)
	{
		super(id,textureName);
		setHardness(5.0F);
		setStepSound(Block.soundStoneFootstep);
		setUnlocalizedName("zoroore");
		setCreativeTab(Xorbo.tabsXor);
	}

	@Override
	public int idDropped(int par1, Random random, int zero){
		return this.blockID;
	}
}
