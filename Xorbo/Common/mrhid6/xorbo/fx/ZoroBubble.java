package mrhid6.xorbo.fx;

import java.util.Random;

import net.minecraft.client.particle.EntityFX;
import net.minecraft.world.World;

public class ZoroBubble extends EntityFX{

	public ZoroBubble(World par1World, double x, double y, double z) {
		super(par1World, x, y, z, 0, 0,0);
		
		this.particleRed = 0.1F;
		this.particleGreen = 0.1F;
		this.particleBlue = 0.1F;
		this.particleMaxAge = (int)(16.0D / (Math.random() * 0.8D + 0.2D));
		this.setParticleTextureIndex(49);
		
		this.particleScale = this.rand.nextFloat() * 1.0F + 0.2F;
	}
	
	
	
    public void onUpdate()
    {
        this.prevPosX = this.posX;
        this.prevPosY = this.posY;
        this.prevPosZ = this.posZ;
        this.motionY -= 0.03D;
        this.moveEntity(this.motionX, this.motionY, this.motionZ);
        this.motionX *= 0.6990000128746033D;
        this.motionY *= 0.6990000128746033D;
        this.motionZ *= 0.6990000128746033D;

        if (this.particleMaxAge-- <= 0)
        {
            this.setDead();
        }
    }

}
