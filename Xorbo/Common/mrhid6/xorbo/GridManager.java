package mrhid6.xorbo;

import java.util.ArrayList;

import mrhid6.xorbo.tileEntity.TECableBase;
import mrhid6.xorbo.tileEntity.TETriniumConverter;
import mrhid6.xorbo.tileEntity.TEZoroController;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class GridManager {
	
	
	public static ArrayList<GridPower> grids = new ArrayList<GridPower>();
	
	public static int addGridToManager(GridPower grid){
		
		int index = grids.size();
		grids.add(grid);
		
		return index;
	}

	public static GridPower getGrid(int gridindex) {
		
		if(gridindex==-1 || gridindex>=grids.size())
			return null;
		
		return grids.get(gridindex);
	}
	
	public static GridPower getGridAt(int x,int y, int z, World w){
		TileEntity te = w.getBlockTileEntity(x, y, z);
		
		if(te!=null){
			
			if(te instanceof TECableBase){
				TECableBase cable = (TECableBase)te;
				
				if(cable.getGrid()!=null){
					return cable.getGrid();
				}
			}
			
			if(te instanceof TEZoroController){
				TEZoroController controller = (TEZoroController)te;
				
				if(controller.getGrid()!=null){
					return controller.getGrid();
				}
			}
			if(te instanceof TETriniumConverter){
				TETriniumConverter converter = (TETriniumConverter)te;
				
				if(converter.getGrid()!=null){
					return converter.getGrid();
				}
			}
		}
		
		return null;
	}
	
	
}
