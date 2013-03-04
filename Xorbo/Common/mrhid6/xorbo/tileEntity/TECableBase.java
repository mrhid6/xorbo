package mrhid6.xorbo.tileEntity;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class TECableBase extends TileEntity{


	public void renderCableAt(double Thickness, World world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer, int texture, boolean center, double pull){
		if (center){
			renderer.overrideBlockBounds(0.5D - Thickness, 0.5D - Thickness, 0.5D - Thickness, 0.5D + Thickness, 0.5D + Thickness, 0.5D + Thickness);

			renderer.renderStandardBlock(block, x, y, z);
		}

	}

	@Override
	public void updateEntity() {
		super.updateEntity();
		
	}

	public void draw(RenderBlocks renderer) {
		renderCableAt(0.03000000178813934D, this.worldObj, this.xCoord, this.yCoord, this.zCoord, getBlockType(), 0, renderer, 69, true, 0.0D);
		
	}
}
