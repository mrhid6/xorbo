package mrhid6.xorbo;

import java.util.ArrayList;

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
}
