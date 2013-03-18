package mrhid6.xorbo.fx;

import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.EntityLiving;
import net.minecraft.src.ModLoader;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import org.lwjgl.opengl.GL11;

public class FXBeam extends EntityFX{

	private double tX = 0.0D;
	private double tY = 0.0D;
	private double tZ = 0.0D;
	private double ptX = 0.0D;
	private double ptY = 0.0D;
	private double ptZ = 0.0D;

	private float length = 0.0F;
	private float rotYaw = 0.0F;
	private float rotPitch = 0.0F;
	private float prevYaw = 0.0F;
	private float prevPitch = 0.0F;
	private float endMod = 1.0F;
	private float prevSize = 0.0F;
	public int impact;
	private boolean reverse;

	public FXBeam(World world, double px, double py, double pz, double tx, double ty, double tz,float red,float green, float blue, int age) {
		super(world, px, py, pz, 0.0D, 0.0D, 0.0D);

		this.particleRed = red;
		this.particleGreen = green;
		this.particleBlue = blue;

		setRotation(0.02F, 0.02F);

		this.noClip = true;

		this.motionX = 0.0D;
		this.motionY = 0.0D;
		this.motionZ = 0.0D;

		this.particleMaxAge = age;

		this.tX = tx;
		this.tY = ty;
		this.tZ = tz;
		this.prevYaw = this.rotYaw;
		this.prevPitch = this.rotPitch;

		EntityLiving renderentity = ModLoader.getMinecraftInstance().renderViewEntity;
		int visibleDistance = 50;
		if (!ModLoader.getMinecraftInstance().gameSettings.fancyGraphics) visibleDistance = 25;
		if (renderentity.getDistance(this.posX, this.posY, this.posZ) > visibleDistance) this.particleMaxAge = 0;

	}

	public void updateBeam(double x, double y, double z){
		this.tX = x;
		this.tY = y;
		this.tZ = z;

		//System.out.println(tZ);
		while (this.particleMaxAge - this.particleAge < 4) this.particleMaxAge += 1;
	}
	public void setReverse(boolean reverse)
	{
		this.reverse = reverse;
	}
	public void onUpdate(){
		this.prevPosX = this.posX;
		this.prevPosY = this.posY;
		this.prevPosZ = this.posZ;

		this.ptX = this.tX;
		this.ptY = this.tY;
		this.ptZ = this.tZ;

		this.prevYaw = this.rotYaw;
		this.prevPitch = this.rotPitch;

		float xd = (float)(this.posX - this.tX);
		float yd = (float)(this.posY - this.tY);
		float zd = (float)(this.posZ - this.tZ);
		this.length = MathHelper.sqrt_float(xd * xd + yd * yd + zd * zd);
		double var7 = MathHelper.sin(xd * xd + zd * zd);

		//System.out.println("xd:"+xd+"zd:"+zd);

		this.rotYaw = (float)(Math.atan2(xd, zd) * 180.0D / 3.141592653589793D);
		this.rotPitch = (float)(Math.atan2(yd, var7) * 180.0D / 3.141592653589793D);

		this.prevYaw = this.rotYaw;
		this.prevPitch = this.rotPitch;

		if (this.impact > 0) this.impact -= 1;

		if (this.particleAge++ >= this.particleMaxAge){
			setDead();
		}
	}

	public void setEndMod(float endMod){
		this.endMod = endMod;
	}

	@Override
	public void renderParticle(Tessellator tessellator, float f, float f1, float f2, float f3, float f4, float f5) {
		tessellator.draw();

		float size = 1.0F;
		GL11.glPushMatrix();
		{
			//System.out.println("beam drawn");
			float var9 = 1.0F;
			float slide = (float)this.worldObj.getWorldTime();
			float rot = (float)(this.worldObj.provider.getWorldTime() % (360 / 5) * 5) + 5 * f;

			float op = 0.4F;

			//ForgeHooksClient.bindTexture("/mrhid6/xorbo/textures/beam.png", 0);

			GL11.glTexParameterf(3553, 10242, 10497.0F);
			GL11.glTexParameterf(3553, 10243, 10497.0F);

			GL11.glDisable(2884);

			float var11 = slide + f;
			if (this.reverse) var11 *= -1.0F;
			float var12 = -var11 * 0.2F - MathHelper.floor_float(-var11 * 0.1F);

			GL11.glEnable(3042);
			GL11.glBlendFunc(770, 1);
			GL11.glDepthMask(false);

			float xx = (float)(this.prevPosX + (this.posX - this.prevPosX) * f - interpPosX);
			float yy = (float)(this.prevPosY + (this.posY - this.prevPosY) * f - interpPosY);
			float zz = (float)(this.prevPosZ + (this.posZ - this.prevPosZ) * f - interpPosZ);
			GL11.glTranslated(xx, yy, zz);

			float ry = (float)(this.prevYaw + (this.rotYaw - this.prevYaw) * f);
			float rp = (float)(this.prevPitch + (this.rotPitch - this.prevPitch) * f);
			GL11.glRotatef(90.0F, 1.0F, 0.0F, 0.0F);
			GL11.glRotatef(180.0F + ry, 0.0F, 0.0F, -1.0F);
			GL11.glRotatef(rp, 1.0F, 0.0F, 0.0F);

			double var44 = -0.15D * size;
			double var17 = 0.15D * size;
			double var44b = -0.15D * size * this.endMod;
			double var17b = 0.15D * size * this.endMod;

			GL11.glRotatef(rot, 0.0F, 1.0F, 0.0F);
			for (int t = 0; t < 3; t++)
			{
				double var29 = this.length * size * var9;
				double var31 = 0.0D;
				double var33 = 1.0D;
				double var35 = -1.0F + var12 + t / 3.0F;
				double var37 = this.length * size * var9 + var35;

				GL11.glRotatef(60.0F, 0.0F, 1.0F, 0.0F);
				tessellator.startDrawingQuads();
				tessellator.setBrightness(200);
				tessellator.setColorRGBA_F(this.particleRed, this.particleGreen, this.particleBlue, op);
				tessellator.addVertexWithUV(var44b, var29, 0.0D, var33, var37);
				tessellator.addVertexWithUV(var44, 0.0D, 0.0D, var33, var35);
				tessellator.addVertexWithUV(var17, 0.0D, 0.0D, var31, var35);
				tessellator.addVertexWithUV(var17b, var29, 0.0D, var31, var37);
				tessellator.draw();
			}

			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			GL11.glDepthMask(true);
			GL11.glDisable(3042);
			GL11.glEnable(2884);
		}
		GL11.glPopMatrix();
		
		GL11.glBindTexture(3553, ModLoader.getMinecraftInstance().renderEngine.getTexture("/particles.png"));
		
		tessellator.startDrawingQuads();
		this.prevSize = size;
	}



}
