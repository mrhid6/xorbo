package mrhid6.xorbo.entities;

import java.util.List;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class EntityTitan extends Entity {

	private double boatPitch;
	private int boatPosRotationIncrements;
	private double boatX;
	private double boatY;
	private double boatYaw;
	private double boatZ;
	private double field_70276_b;
	private boolean field_70279_a;
	@SideOnly(Side.CLIENT)
	private double velocityX;
	@SideOnly(Side.CLIENT)
	private double velocityY;
	@SideOnly(Side.CLIENT)
	private double velocityZ;

	public EntityTitan( World par1World ) {
		super(par1World);
		field_70279_a = true;
		field_70276_b = 0.07D;
		preventEntitySpawning = true;
		this.setSize(1.5F, 2F);
		yOffset = height / 1.3F;
	}

	public EntityTitan( World par1World, double par2, double par4, double par6 ) {
		this(par1World);
		this.setPosition(par2, par4 + yOffset, par6);
		motionX = 0.0D;
		motionY = 0.0D;
		motionZ = 0.0D;
		prevPosX = par2;
		prevPosY = par4;
		prevPosZ = par6;
	}

	/**
	 * Called when the entity is attacked.
	 */
	@Override
	public boolean attackEntityFrom( DamageSource par1DamageSource, int par2 ) {
		if (this.isEntityInvulnerable()) {
			return false;
		} else if (!worldObj.isRemote && !isDead) {
			this.setForwardDirection(-this.getForwardDirection());
			this.setTimeSinceHit(10);
			this.setDamageTaken(this.getDamageTaken() + par2 * 10);
			this.setBeenAttacked();
			boolean flag = par1DamageSource.getEntity() instanceof EntityPlayer
					&& ((EntityPlayer) par1DamageSource.getEntity()).capabilities.isCreativeMode;

			if (flag || this.getDamageTaken() > 40) {
				if (riddenByEntity != null) {
					riddenByEntity.mountEntity(this);
				}

				if (!flag) {
					this.dropItemWithOffset(Item.boat.itemID, 1, 0.0F);
				}

				this.setDead();
			}

			return true;
		} else {
			return true;
		}
	}

	/**
	 * Returns true if other Entities should be prevented from moving through
	 * this Entity.
	 */
	@Override
	public boolean canBeCollidedWith() {
		return !isDead;
	}

	/**
	 * Returns true if this entity should push and be pushed by other entities
	 * when colliding.
	 */
	@Override
	public boolean canBePushed() {
		return false;
	}

	@Override
	protected void entityInit() {
		dataWatcher.addObject(17, new Integer(0));
		dataWatcher.addObject(18, new Integer(1));
		dataWatcher.addObject(19, new Integer(0));
	}

	@SideOnly(Side.CLIENT)
	public void func_70270_d( boolean par1 ) {
		field_70279_a = par1;
	}

	/**
	 * returns the bounding box for this entity
	 */
	@Override
	public AxisAlignedBB getBoundingBox() {
		return boundingBox;
	}

	/**
	 * Returns a boundingBox used to collide the entity with other entities and
	 * blocks. This enables the entity to be pushable on contact, like boats or
	 * minecarts.
	 */
	@Override
	public AxisAlignedBB getCollisionBox( Entity par1Entity ) {
		return par1Entity.boundingBox;
	}

	/**
	 * Gets the damage taken from the last hit.
	 */
	public int getDamageTaken() {
		return dataWatcher.getWatchableObjectInt(19);
	}

	/**
	 * Gets the forward direction of the entity.
	 */
	public int getForwardDirection() {
		return dataWatcher.getWatchableObjectInt(18);
	}

	/**
	 * Returns the Y offset from the entity's position for any entity riding
	 * this one.
	 */
	@Override
	public double getMountedYOffset() {
		return height * 0.0D - 0.2D;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public float getShadowSize() {
		return 1.6F;
	}

	/**
	 * Gets the time since the last hit.
	 */
	public int getTimeSinceHit() {
		return dataWatcher.getWatchableObjectInt(17);
	}

	/**
	 * Called when a player interacts with a mob. e.g. gets milk from a cow,
	 * gets into the saddle on a pig.
	 */
	@Override
	public boolean interact( EntityPlayer par1EntityPlayer ) {
		if (riddenByEntity != null && riddenByEntity instanceof EntityPlayer
				&& riddenByEntity != par1EntityPlayer) {
			return true;
		} else {
			if (!worldObj.isRemote) {
				par1EntityPlayer.mountEntity(this);
			}

			return true;
		}
	}

	/**
	 * Called to update the entity's position/logic.
	 */
	@Override
	public void onUpdate() {
		super.onUpdate();

		if (this.getTimeSinceHit() > 0) {
			this.setTimeSinceHit(this.getTimeSinceHit() - 1);
		}

		if (this.getDamageTaken() > 0) {
			this.setDamageTaken(this.getDamageTaken() - 1);
		}

		prevPosX = posX;
		prevPosY = posY;
		prevPosZ = posZ;
		byte b0 = 5;
		double d0 = 0.0D;

		for (int i = 0; i < b0; ++i) {
			double d1 = boundingBox.minY
					+ (boundingBox.maxY - boundingBox.minY) * (i + 0) / b0
					- 0.125D;
			double d2 = boundingBox.minY
					+ (boundingBox.maxY - boundingBox.minY) * (i + 1) / b0
					- 0.125D;
			AxisAlignedBB axisalignedbb = AxisAlignedBB.getAABBPool().getAABB(
					boundingBox.minX, d1, boundingBox.minZ, boundingBox.maxX,
					d2, boundingBox.maxZ);

			if (worldObj.isAABBInMaterial(axisalignedbb, Material.water)) {
				d0 += 3.0D / b0;
			}
		}

		double d3 = Math.sqrt(motionX * motionX + motionZ * motionZ);
		double d4;
		double d5;

		double d10;
		double d11;

		if (worldObj.isRemote && field_70279_a) {
			if (boatPosRotationIncrements > 0) {
				d4 = posX + (boatX - posX) / boatPosRotationIncrements;
				d5 = posY + (boatY - posY) / boatPosRotationIncrements;
				d11 = posZ + (boatZ - posZ) / boatPosRotationIncrements;
				d10 = MathHelper.wrapAngleTo180_double(boatYaw - rotationYaw);
				rotationYaw = (float) (rotationYaw + d10
						/ boatPosRotationIncrements);
				rotationPitch = (float) (rotationPitch + (boatPitch - rotationPitch)
						/ boatPosRotationIncrements);
				--boatPosRotationIncrements;
				this.setPosition(d4, d5, d11);
				this.setRotation(rotationYaw, rotationPitch);
			} else {
				d4 = posX + motionX;
				d5 = posY + motionY;
				d11 = posZ + motionZ;
				this.setPosition(d4, d5, d11);

				if (onGround) {
					motionX *= 0.5D;
					motionY *= 0.5D;
					motionZ *= 0.5D;
				}

				motionX *= 0.9900000095367432D;
				motionY *= 0.949999988079071D;
				motionZ *= 0.9900000095367432D;

			}
		} else {

			if (d0 < 1.0D) {
				d4 = d0 * 2.0D - 1.0D;
				motionY += 0.3D * d4;
			} else {
				if (motionY < 0.0D) {
					motionY /= 2.0D;
				}

				motionY += 0.015D;
			}

			if (riddenByEntity != null) {
				motionX = riddenByEntity.motionX * 10;
				motionZ = riddenByEntity.motionZ * 10;
			}

			d4 = Math.sqrt(motionX * motionX + motionZ * motionZ);

			if (d4 > 0.35D) {
				d5 = 0.35D / d4;
				motionX *= d5;
				motionZ *= d5;
				d4 = 0.35D;
			}

			if (d4 > d3 && field_70276_b < 0.35D) {
				field_70276_b += (0.35D - field_70276_b) / 35.0D;

				if (field_70276_b > 0.35D) {
					field_70276_b = 0.35D;
				}
			} else {
				field_70276_b -= (field_70276_b - 0.07D) / 35.0D;

				if (field_70276_b < 0.07D) {
					field_70276_b = 0.07D;
				}
			}

			if (isCollidedHorizontally) {
				motionY = 0.2D;
			}

			this.moveEntity(motionX, motionY, motionZ);

			motionX *= 0.9900000095367432D;
			motionY *= 0.949999988079071D;
			motionZ *= 0.9900000095367432D;

			rotationPitch = 0.0F;
			d5 = rotationYaw;
			d11 = prevPosX - posX;
			d10 = prevPosZ - posZ;

			if (d11 * d11 + d10 * d10 > 0.001D) {
				d5 = ((float) (Math.atan2(d10, d11) * 180.0D / Math.PI));
			}

			double d12 = MathHelper.wrapAngleTo180_double(d5 - rotationYaw);

			if (d12 > 20.0D) {
				d12 = 20.0D;
			}

			if (d12 < -20.0D) {
				d12 = -20.0D;
			}

			rotationYaw = (float) (rotationYaw + d12);
			this.setRotation(rotationYaw, rotationPitch);

			if (!worldObj.isRemote) {
				List list = worldObj.getEntitiesWithinAABBExcludingEntity(this,
						boundingBox.expand(0.5, 0.0D, 0.5));
				int l;

				if (list != null && !list.isEmpty()) {
					for (l = 0; l < list.size(); ++l) {
						Entity entity = (Entity) list.get(l);

						if (entity != riddenByEntity && entity.canBePushed()
								&& entity instanceof EntityTitan) {
							// entity.applyEntityCollision(this);
						}
					}
				}

				if (riddenByEntity != null && riddenByEntity.isDead) {
					riddenByEntity = null;
				}
			}
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	/**
	 * Setups the entity to do the hurt animation. Only used by packets in multiplayer.
	 */
	public void performHurtAnimation() {
		this.setForwardDirection(-this.getForwardDirection());
		this.setTimeSinceHit(10);
		this.setDamageTaken(this.getDamageTaken() * 11);
	}

	/**
	 * (abstract) Protected helper method to read subclass entity data from NBT.
	 */
	@Override
	protected void readEntityFromNBT( NBTTagCompound par1NBTTagCompound ) {
	}

	/**
	 * Sets the damage taken from the last hit.
	 */
	public void setDamageTaken( int par1 ) {
		dataWatcher.updateObject(19, Integer.valueOf(par1));
	}

	/**
	 * Sets the forward direction of the entity.
	 */
	public void setForwardDirection( int par1 ) {
		dataWatcher.updateObject(18, Integer.valueOf(par1));
	}

	@SideOnly(Side.CLIENT)
	/**
	 * Sets the position and rotation. Only difference from the other one is no bounding on the rotation. Args: posX,
	 * posY, posZ, yaw, pitch
	 */
	@Override
	public void setPositionAndRotation2( double par1, double par3, double par5,
			float par7, float par8, int par9 ) {
		if (field_70279_a) {
			boatPosRotationIncrements = par9 + 5;
		} else {
			double d3 = par1 - posX;
			double d4 = par3 - posY;
			double d5 = par5 - posZ;
			double d6 = d3 * d3 + d4 * d4 + d5 * d5;

			if (d6 <= 1.0D) {
				return;
			}

			boatPosRotationIncrements = 3;
		}

		boatX = par1;
		boatY = par3;
		boatZ = par5;
		boatYaw = par7;
		boatPitch = par8;
		motionX = velocityX;
		motionY = velocityY;
		motionZ = velocityZ;
	}

	/**
	 * Sets the time to count down from since the last time entity was hit.
	 */
	public void setTimeSinceHit( int par1 ) {
		dataWatcher.updateObject(17, Integer.valueOf(par1));
	}

	@Override
	@SideOnly(Side.CLIENT)
	/**
	 * Sets the velocity to the args. Args: x, y, z
	 */
	public void setVelocity( double par1, double par3, double par5 ) {
		velocityX = motionX = par1;
		velocityY = motionY = par3;
		velocityZ = motionZ = par5;
	}

	@Override
	public void updateRiderPosition() {
		if (riddenByEntity != null) {
			double d0 = Math.cos(rotationYaw * Math.PI / 180.0D) * 0.4D;
			double d1 = Math.sin(rotationYaw * Math.PI / 180.0D) * 0.4D;
			riddenByEntity.setPosition(
					posX + d0,
					posY + this.getMountedYOffset()
							+ riddenByEntity.getYOffset(), posZ + d1);
		}
	}

	/**
	 * (abstract) Protected helper method to write subclass entity data to NBT.
	 */
	@Override
	protected void writeEntityToNBT( NBTTagCompound par1NBTTagCompound ) {
	}
}
