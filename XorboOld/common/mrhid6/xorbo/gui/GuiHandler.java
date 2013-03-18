package mrhid6.xorbo.gui;

import mrhid6.xorbo.tileentities.TEStearilliumGrinder;
import mrhid6.xorbo.tileentities.TETriniumChillerBase;
import mrhid6.xorbo.tileentities.TETriniumChillerCore;
import mrhid6.xorbo.tileentities.TETriniumCore;
import mrhid6.xorbo.tileentities.TileZoroFurnace;
import mrhid6.xorbo.tileentities.engine.TileZoroGen;
import mrhid6.xorbo.triniumlaser.ContainerTLaser;
import mrhid6.xorbo.triniumlaser.GuiTriniumLaser;
import mrhid6.xorbo.triniumlaser.TETriniumLaserBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler
{
	//returns an instance of the Container you made earlier
	@Override
	public Object getServerGuiElement(int id, EntityPlayer player, World world,
			int x, int y, int z)
	{
		TileEntity tileEntity = world.getBlockTileEntity(x, y, z);

		if (tileEntity instanceof TileZoroFurnace){
			return new ContainerZoroFurnace(player, (TileZoroFurnace) tileEntity);
		}else if (tileEntity instanceof TileZoroGen){
			return new ContainerZoroGen(player, (TileZoroGen) tileEntity);
		}else if (tileEntity instanceof TEStearilliumGrinder){
			return new ContainerStearilliumGrinder(player, (TEStearilliumGrinder) tileEntity);
		}else if (tileEntity instanceof TETriniumCore){
			return new ContainerTriniumCore(player, (TETriniumCore) tileEntity);
		}else if (tileEntity instanceof TETriniumLaserBase){
			return new ContainerTLaser(player, (TETriniumLaserBase) tileEntity);
		}
		
		if(tileEntity instanceof TETriniumChillerBase || tileEntity instanceof TETriniumChillerCore){
			TETriniumChillerCore core = null;
			if(tileEntity instanceof TETriniumChillerBase){
				core = ((TETriniumChillerBase) tileEntity).getCore();
			}else{
				core = (TETriniumChillerCore)tileEntity;
			}
			
			if(core!=null)
				return new ContainerTChiller(player, core);
		}

		return null;
	}

	//returns an instance of the Gui you made earlier
	@Override
	public Object getClientGuiElement(int id, EntityPlayer player, World world,
			int x, int y, int z)
	{
		TileEntity tileEntity = world.getBlockTileEntity(x, y, z);

		if (tileEntity instanceof TileZoroFurnace){
			return new GuiZoroFurnace(new ContainerZoroFurnace(player, (TileZoroFurnace) tileEntity));
		}else if (tileEntity instanceof TileZoroGen){
			return new GuiZoroGen(new ContainerZoroGen(player, (TileZoroGen) tileEntity));
		}else if (tileEntity instanceof TEStearilliumGrinder){
			return new GuiStearilliumGrinder(new ContainerStearilliumGrinder(player, (TEStearilliumGrinder) tileEntity));
		}else if (tileEntity instanceof TETriniumCore){
			return new GuiTriniumCore(new ContainerTriniumCore(player, (TETriniumCore) tileEntity));
		}else if (tileEntity instanceof TETriniumLaserBase){
			return new GuiTriniumLaser(new ContainerTLaser(player, (TETriniumLaserBase) tileEntity));
		}
		
		if(tileEntity instanceof TETriniumChillerBase || tileEntity instanceof TETriniumChillerCore){
			TETriniumChillerCore core = null;
			if(tileEntity instanceof TETriniumChillerBase){
				core = ((TETriniumChillerBase) tileEntity).getCore();
			}else{
				core = (TETriniumChillerCore)tileEntity;
			}
			
			if(core!=null)
				System.out.println(core.myTank.getLiquid().amount);
		}
		
		return null;
	}
}
