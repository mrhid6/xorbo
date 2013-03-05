package mrhid6.xorbo.block;

import mrhid6.xorbo.Config;
import mrhid6.xorbo.proxy.commonProxy;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;

public abstract class BlockTexturedBase extends BlockContainer{

	private String name;

	public BlockTexturedBase(int id,int texture, String name) {

		super(id, texture, Material.ground);

		this.name = name;
		this.setCreativeTab(Config.creativeTabXor);
		this.setHardness(89.3F);
		this.setResistance(89.5F);
		this.setStepSound(soundMetalFootstep);
		this.setBlockName(name);
	}

	@Override
	public String getTextureFile() {
		return commonProxy.BLOCK_PNG;
	}
	public String getName(){
		return this.name;
	}


}
