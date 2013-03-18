package mrhid6.xorbo.mob;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.world.World;

public class EntityTitan extends EntityMob{

	public EntityTitan(World par1World) {
		super(par1World);
		this.texture="/mrhid6/xorbo/textures/ModelTitan.png";
		this.moveSpeed = 0.25F;
		this.tasks.addTask(0, new EntityAISwimming(this));
		this.tasks.addTask(1, new EntityAIAttackOnCollide(this,EntityPlayer.class,this.moveSpeed,false));
		this.tasks.addTask(2, new EntityAIWatchClosest(this,EntityPlayer.class,6.0F));
		this.tasks.addTask(3, new EntityAIWander(this,this.moveSpeed));
		
		this.targetTasks.addTask(1, new EntityAIHurtByTarget(this,false));
		this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this,EntityPlayer.class,16.0F,0,true));
		
	}
	
	protected boolean isAIEnabled(){
		return true;
	}
	
	public int getMaxHealth() {
		return 30;
	}
	
	public int getAttackStrength(Entity par1Entity){
		return 4;
	}
	
	protected String getLivingSound(){
		return "mob.zombie.say";
	}
	
	protected String getHurtSound(){
		return "mob.zombie.hurt";
	}
	
	protected String getDeathSound(){
		return "mob.zombie.death";
	}
	
	protected void playerStepSound(int par1,int par2,int par3, int par4){
		this.worldObj.playSoundAtEntity(this, "mob.zombie.step", 0.15F, 1.0F);
	}
	
	protected int getDropItemId(){
		
		return Item.arrow.itemID;
	}
	protected void dropRareDrop(int par1){
		
		switch(this.rand.nextInt(2)){
		case 0:
			this.dropItem(Item.ingotIron.itemID, 1);
		case 1:
			this.dropItem(Item.ingotGold.itemID, this.rand.nextInt(5)+1);
		}
	}
	protected void dropFewItems(boolean par1,int par2){
		
		if(this.rand.nextInt(4)==0){
			this.dropItem(Block.blockDiamond.blockID, 5);
		}
	}
	
	
	
}
