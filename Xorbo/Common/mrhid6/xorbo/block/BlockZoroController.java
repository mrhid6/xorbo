package mrhid6.xorbo.block;

import mrhid6.xorbo.tileEntity.TEZoroController;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockZoroController extends BlockTexturedBase{

	public int textureId;
	public BlockZoroController(int id, int textureid, String name,boolean craftable) {
		super(id, textureid, name, craftable,1);

		this.setResistance(6.0F);
		this.setHardness(6.0F);
		
		this.textureId=textureid;

	}

	@Override
	public TileEntity createNewTileEntity(World var1) {
		return new TEZoroController();
	}

	public int getBlockTextureFromSide(int side){
		if(side==1){
			return textureId+1;
		}else{
			return textureId;
		}
	}

}
