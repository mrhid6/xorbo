package mrhid6.xorbo.interfaces;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;


public abstract interface ICustomCollision
{
	public abstract void addCollidingBlockToList(World paramyc, int paramInt1, int paramInt2, int paramInt3, AxisAlignedBB paramaoe, List paramList, Entity paramlq);

	public abstract AxisAlignedBB getSelectedBoundingBoxFromPool(World paramyc, int paramInt1, int paramInt2, int paramInt3);
}