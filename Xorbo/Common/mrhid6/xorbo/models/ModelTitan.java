package mrhid6.xorbo.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class ModelTitan extends ModelBase {

	ModelRenderer bur1l;
	ModelRenderer bur1r;
	ModelRenderer bur2l;
	ModelRenderer bur2r;
	ModelRenderer bur3l;
	ModelRenderer bur3r;
	ModelRenderer bur4l;
	ModelRenderer bur4r;
	ModelRenderer bur5l;
	ModelRenderer bur5r;
	ModelRenderer bur6l;
	ModelRenderer bur6r;
	ModelRenderer bur7l;
	ModelRenderer bur7r;
	ModelRenderer bur8l;
	ModelRenderer bur8r;
	ModelRenderer bur9l;
	ModelRenderer bur9r;
	// fields
	ModelRenderer cabin1;
	ModelRenderer cabin10;
	ModelRenderer cabin11;
	ModelRenderer cabin12;
	ModelRenderer cabin13;
	ModelRenderer cabin15;
	ModelRenderer cabin16;
	ModelRenderer cabin17;
	ModelRenderer cabin18;
	ModelRenderer cabin19;
	ModelRenderer cabin2;
	ModelRenderer cabin3;
	ModelRenderer cabin4;
	ModelRenderer cabin5;
	ModelRenderer cabin6;
	ModelRenderer cabin7;
	ModelRenderer cabin8;
	ModelRenderer cabin9;
	ModelRenderer lbedro;
	ModelRenderer lfoot1;
	ModelRenderer lfoot2;
	ModelRenderer lgolen;
	ModelRenderer lkist;
	ModelRenderer lnarm;
	ModelRenderer lplecho;
	ModelRenderer lrocket1;
	ModelRenderer lrocket2;
	ModelRenderer lvarm;
	ModelRenderer lygodica;
	ModelRenderer rbedro;
	ModelRenderer rfoot1;
	ModelRenderer rfoot2;
	ModelRenderer rgolen;
	ModelRenderer rkist;
	ModelRenderer rnarm;
	ModelRenderer rplecho;
	ModelRenderer rrocket1;
	ModelRenderer rrocket2;
	ModelRenderer rvarm;
	ModelRenderer rygodica;
	ModelRenderer stvol1l;
	ModelRenderer stvol1r;
	ModelRenderer stvol2l;
	ModelRenderer stvol2r;
	ModelRenderer stvol3l;
	ModelRenderer stvol3r;

	public ModelTitan() {
		textureWidth = 64;
		textureHeight = 64;

		cabin1 = new ModelRenderer(this, 0, 0);
		cabin1.addBox(0F, 0F, 0F, 16, 2, 15);
		cabin1.setRotationPoint(-8F, 2F, -11F);
		cabin1.setTextureSize(64, 64);
		cabin1.mirror = true;
		setRotation(cabin1, 0F, 0F, 0F);
		cabin19 = new ModelRenderer(this, 0, 0);
		cabin19.addBox(0F, 0F, 0F, 14, 6, 1);
		cabin19.setRotationPoint(-7F, -7F, -14F);
		cabin19.setTextureSize(64, 64);
		cabin19.mirror = true;
		setRotation(cabin19, 0F, 0F, 0F);
		cabin2 = new ModelRenderer(this, 0, 0);
		cabin2.addBox(0F, 0F, 0F, 1, 9, 16);
		cabin2.setRotationPoint(8F, -7F, -12F);
		cabin2.setTextureSize(64, 64);
		cabin2.mirror = true;
		setRotation(cabin2, 0F, 0F, 0F);
		cabin18 = new ModelRenderer(this, 0, 25);
		cabin18.addBox(0F, -10F, 0F, 10, 10, 1);
		cabin18.setRotationPoint(-5F, -17F, -13F);
		cabin18.setTextureSize(64, 64);
		cabin18.mirror = true;
		setRotation(cabin18, -0.7853982F, 0F, 0F);
		cabin3 = new ModelRenderer(this, 0, 0);
		cabin3.addBox(0F, 0F, 0F, 1, 9, 16);
		cabin3.setRotationPoint(-9F, -7F, -12F);
		cabin3.setTextureSize(64, 64);
		cabin3.mirror = true;
		setRotation(cabin3, 0F, 0F, 0F);
		cabin17 = new ModelRenderer(this, 0, 41);
		cabin17.addBox(0F, 0F, 0F, 10, 4, 1);
		cabin17.setRotationPoint(-5F, -23F, -6F);
		cabin17.setTextureSize(64, 64);
		cabin17.mirror = true;
		setRotation(cabin17, 1.570796F, 0F, 0F);
		cabin4 = new ModelRenderer(this, 0, 0);
		cabin4.addBox(0F, 0F, 0F, 16, 9, 2);
		cabin4.setRotationPoint(-8F, -6F, -13F);
		cabin4.setTextureSize(64, 64);
		cabin4.mirror = true;
		setRotation(cabin4, 0F, 0F, 0F);
		cabin16 = new ModelRenderer(this, 0, 25);
		cabin16.addBox(0F, -10F, 0F, 10, 10, 1);
		cabin16.setRotationPoint(-5F, -7F, -14F);
		cabin16.setTextureSize(64, 64);
		cabin16.mirror = true;
		setRotation(cabin16, -0.1047198F, 0F, 0F);
		cabin5 = new ModelRenderer(this, 0, 36);
		cabin5.addBox(0F, 0F, -4F, 14, 1, 4);
		cabin5.setRotationPoint(-7F, -7F, -9F);
		cabin5.setTextureSize(64, 64);
		cabin5.mirror = true;
		setRotation(cabin5, -0.2094395F, 0F, 0F);
		cabin15 = new ModelRenderer(this, 0, 0);
		cabin15.addBox(0F, 0F, 0F, 10, 4, 1);
		cabin15.setRotationPoint(-5F, -8F, -15F);
		cabin15.setTextureSize(64, 64);
		cabin15.mirror = true;
		setRotation(cabin15, 0F, 0F, 0F);
		cabin6 = new ModelRenderer(this, 0, 46);
		cabin6.addBox(-5F, -10F, 0F, 10, 6, 2);
		cabin6.setRotationPoint(0F, -7F, 4F);
		cabin6.setTextureSize(64, 64);
		cabin6.mirror = true;
		setRotation(cabin6, 0F, 0F, 0F);
		cabin7 = new ModelRenderer(this, 0, 0);
		cabin7.addBox(-8F, -16F, 0F, 16, 16, 2);
		cabin7.setRotationPoint(0F, 2F, 3F);
		cabin7.setTextureSize(64, 64);
		cabin7.mirror = true;
		setRotation(cabin7, -0.1047198F, 0F, 0F);
		cabin13 = new ModelRenderer(this, 0, 0);
		cabin13.addBox(4F, 2F, 2F, 10, 13, 4);
		cabin13.setRotationPoint(-9F, -23F, 8F);
		cabin13.setTextureSize(64, 64);
		cabin13.mirror = true;
		setRotation(cabin13, -0.0698132F, 0F, 0F);
		cabin8 = new ModelRenderer(this, 0, 0);
		cabin8.addBox(0F, 0F, 0F, 16, 7, 3);
		cabin8.setRotationPoint(-8F, -3F, 5F);
		cabin8.setTextureSize(64, 64);
		cabin8.mirror = true;
		setRotation(cabin8, -0.1396263F, 0F, 0F);
		cabin12 = new ModelRenderer(this, 0, 0);
		cabin12.addBox(0F, 0F, 0F, 22, 4, 4);
		cabin12.setRotationPoint(-11F, -24F, 5F);
		cabin12.setTextureSize(64, 64);
		cabin12.mirror = true;
		setRotation(cabin12, -0.0872665F, 0F, 0F);
		cabin9 = new ModelRenderer(this, 22, 25);
		cabin9.addBox(2F, -2F, -3F, 1, 2, 1);
		cabin9.setRotationPoint(-7F, -7F, -9F);
		cabin9.setTextureSize(64, 64);
		cabin9.mirror = true;
		setRotation(cabin9, -0.2094395F, 0F, 0F);
		cabin11 = new ModelRenderer(this, 0, 0);
		cabin11.addBox(0F, 0F, 0F, 18, 22, 3);
		cabin11.setRotationPoint(-9F, -23F, 7F);
		cabin11.setTextureSize(64, 64);
		cabin11.mirror = true;
		setRotation(cabin11, -0.0872665F, 0F, 0F);
		cabin10 = new ModelRenderer(this, 22, 25);
		cabin10.addBox(11F, -2F, -3F, 1, 2, 1);
		cabin10.setRotationPoint(-7F, -7F, -9F);
		cabin10.setTextureSize(64, 64);
		cabin10.mirror = true;
		setRotation(cabin10, -0.2094395F, 0F, 0F);
		lgolen = new ModelRenderer(this, 0, 0);
		lgolen.addBox(-2F, 0F, 0F, 4, 16, 3);
		lgolen.setRotationPoint(10F, 10F, 10F);
		lgolen.setTextureSize(64, 64);
		lgolen.mirror = true;
		setRotation(lgolen, -0.7679449F, 0F, 0F);
		lygodica = new ModelRenderer(this, 0, 0);
		lygodica.addBox(0F, 0F, 0F, 5, 14, 8);
		lygodica.setRotationPoint(8F, -3F, 2F);
		lygodica.setTextureSize(64, 64);
		lygodica.mirror = true;
		setRotation(lygodica, -0.3839724F, 0F, 0F);
		lbedro = new ModelRenderer(this, 0, 0);
		lbedro.addBox(0F, -10F, 0F, 4, 7, 4);
		lbedro.setRotationPoint(8F, 16F, 11F);
		lbedro.setTextureSize(64, 64);
		lbedro.mirror = true;
		setRotation(lbedro, 1.151917F, 0F, 0F);
		lfoot1 = new ModelRenderer(this, 0, 0);
		lfoot1.addBox(0F, 0F, 0F, 7, 3, 12);
		lfoot1.setRotationPoint(7F, 21F, -8F);
		lfoot1.setTextureSize(64, 64);
		lfoot1.mirror = true;
		setRotation(lfoot1, 0F, 0F, 0F);
		lfoot2 = new ModelRenderer(this, 0, 0);
		lfoot2.addBox(0F, 0F, 0F, 9, 2, 10);
		lfoot2.setRotationPoint(6F, 22F, -11F);
		lfoot2.setTextureSize(64, 64);
		lfoot2.mirror = true;
		setRotation(lfoot2, 0F, 0F, 0F);
		rygodica = new ModelRenderer(this, 0, 0);
		rygodica.addBox(0F, 0F, 0F, 5, 14, 8);
		rygodica.setRotationPoint(-13F, -3F, 2F);
		rygodica.setTextureSize(64, 64);
		rygodica.mirror = true;
		setRotation(rygodica, -0.3839724F, 0F, 0F);
		rgolen = new ModelRenderer(this, 0, 0);
		rgolen.addBox(-2F, 0F, 0F, 4, 16, 3);
		rgolen.setRotationPoint(-10F, 10F, 10F);
		rgolen.setTextureSize(64, 64);
		rgolen.mirror = true;
		setRotation(rgolen, -0.7679449F, 0F, 0F);
		rbedro = new ModelRenderer(this, 0, 0);
		rbedro.addBox(0F, -10F, 0F, 4, 7, 4);
		rbedro.setRotationPoint(-12F, 16F, 11F);
		rbedro.setTextureSize(64, 64);
		rbedro.mirror = true;
		setRotation(rbedro, 1.151917F, 0F, 0F);
		rfoot1 = new ModelRenderer(this, 0, 0);
		rfoot1.addBox(-3.5F, 0F, -12F, 7, 3, 12);
		rfoot1.setRotationPoint(-10.5F, 21F, 4F);
		rfoot1.setTextureSize(64, 64);
		rfoot1.mirror = true;
		setRotation(rfoot1, 0F, 0F, 0F);
		rfoot2 = new ModelRenderer(this, 0, 0);
		rfoot2.addBox(-4.5F, 1F, -15F, 9, 2, 10);
		rfoot2.setRotationPoint(-10.5F, 21F, 4F);
		rfoot2.setTextureSize(64, 64);
		rfoot2.mirror = true;
		setRotation(rfoot2, 0F, 0F, 0F);
		lplecho = new ModelRenderer(this, 0, 0);
		lplecho.addBox(0F, 0F, 0F, 9, 9, 10);
		lplecho.setRotationPoint(11F, -26F, 0F);
		lplecho.setTextureSize(64, 64);
		lplecho.mirror = true;
		setRotation(lplecho, 0.1396263F, 0F, 0F);
		lvarm = new ModelRenderer(this, 0, 0);
		lvarm.addBox(0F, 0F, 0F, 4, 12, 4);
		lvarm.setRotationPoint(13F, -21F, 3F);
		lvarm.setTextureSize(64, 64);
		lvarm.mirror = true;
		setRotation(lvarm, 0.2268928F, 0F, 0F);
		lnarm = new ModelRenderer(this, 0, 0);
		lnarm.addBox(0F, 0F, 0F, 4, 16, 3);
		lnarm.setRotationPoint(14F, -12F, 10F);
		lnarm.setTextureSize(64, 64);
		lnarm.mirror = true;
		setRotation(lnarm, -1.343904F, 0F, 0F);
		lkist = new ModelRenderer(this, 0, 0);
		lkist.addBox(0F, 0F, 0F, 7, 6, 10);
		lkist.setRotationPoint(13F, -9F, -7F);
		lkist.setTextureSize(64, 64);
		lkist.mirror = true;
		setRotation(lkist, 0F, 0F, 0F);
		bur1l = new ModelRenderer(this, 48, 25);
		bur1l.addBox(-2F, -2F, 0F, 4, 4, 2);
		bur1l.setRotationPoint(16.5F, -6F, -13F);
		bur1l.setTextureSize(64, 64);
		bur1l.mirror = true;
		setRotation(bur1l, 0F, 0F, 0F);
		bur2l = new ModelRenderer(this, 48, 25);
		bur2l.addBox(-2.5F, -2.5F, 0F, 5, 5, 2);
		bur2l.setRotationPoint(16.5F, -6F, -11F);
		bur2l.setTextureSize(64, 64);
		bur2l.mirror = true;
		setRotation(bur2l, 0F, 0F, 0F);
		bur3l = new ModelRenderer(this, 48, 25);
		bur3l.addBox(-1.5F, -1.5F, 0F, 3, 3, 2);
		bur3l.setRotationPoint(16.5F, -6F, -15F);
		bur3l.setTextureSize(64, 64);
		bur3l.mirror = true;
		setRotation(bur3l, 0F, 0F, 0F);
		bur4l = new ModelRenderer(this, 0, 0);
		bur4l.addBox(-1F, -1F, 0F, 2, 2, 2);
		bur4l.setRotationPoint(16.5F, -6F, -9F);
		bur4l.setTextureSize(64, 64);
		bur4l.mirror = true;
		setRotation(bur4l, 0F, 0F, 0F);
		bur5l = new ModelRenderer(this, 48, 25);
		bur5l.addBox(-1F, -1F, 0F, 2, 2, 2);
		bur5l.setRotationPoint(16.5F, -6F, -17F);
		bur5l.setTextureSize(64, 64);
		bur5l.mirror = true;
		setRotation(bur5l, 0F, 0F, 0.7853982F);
		bur6l = new ModelRenderer(this, 48, 25);
		bur6l.addBox(-2F, -2F, 0F, 4, 4, 2);
		bur6l.setRotationPoint(16.5F, -6F, -13F);
		bur6l.setTextureSize(64, 64);
		bur6l.mirror = true;
		setRotation(bur6l, 0F, 0F, 0.7853982F);
		bur7l = new ModelRenderer(this, 48, 25);
		bur7l.addBox(-1.5F, -1.5F, 0F, 3, 3, 2);
		bur7l.setRotationPoint(16.5F, -6F, -15F);
		bur7l.setTextureSize(64, 64);
		bur7l.mirror = true;
		setRotation(bur7l, 0F, 0F, 0.7853982F);
		bur8l = new ModelRenderer(this, 48, 25);
		bur8l.addBox(-2.5F, -2.5F, 0F, 5, 5, 2);
		bur8l.setRotationPoint(16.5F, -6F, -11F);
		bur8l.setTextureSize(64, 64);
		bur8l.mirror = true;
		setRotation(bur8l, 0F, 0F, 0.7853982F);
		bur9l = new ModelRenderer(this, 48, 25);
		bur9l.addBox(-1F, -1F, 0F, 2, 2, 2);
		bur9l.setRotationPoint(16.5F, -6F, -17F);
		bur9l.setTextureSize(64, 64);
		bur9l.mirror = true;
		setRotation(bur9l, 0F, 0F, 0F);
		stvol3l = new ModelRenderer(this, 0, 0);
		stvol3l.addBox(0F, 0F, 0F, 3, 2, 4);
		stvol3l.setRotationPoint(15F, -6F, -11F);
		stvol3l.setTextureSize(64, 64);
		stvol3l.mirror = true;
		setRotation(stvol3l, 0F, 0F, 0F);
		stvol2l = new ModelRenderer(this, 26, 25);
		stvol2l.addBox(0F, 0F, 0F, 2, 2, 9);
		stvol2l.setRotationPoint(17F, -8F, -16F);
		stvol2l.setTextureSize(64, 64);
		stvol2l.mirror = true;
		setRotation(stvol2l, 0F, 0F, 0F);
		stvol1l = new ModelRenderer(this, 26, 25);
		stvol1l.addBox(0F, 0F, 0F, 2, 2, 9);
		stvol1l.setRotationPoint(14F, -8F, -16F);
		stvol1l.setTextureSize(64, 64);
		stvol1l.mirror = true;
		setRotation(stvol1l, 0F, 0F, 0F);
		rvarm = new ModelRenderer(this, 0, 0);
		rvarm.addBox(0F, 0F, 0F, 4, 12, 4);
		rvarm.setRotationPoint(-17F, -21F, 3F);
		rvarm.setTextureSize(64, 64);
		rvarm.mirror = true;
		setRotation(rvarm, 0.2268928F, 0F, 0F);
		rplecho = new ModelRenderer(this, 0, 0);
		rplecho.addBox(0F, 0F, 0F, 9, 9, 10);
		rplecho.setRotationPoint(-20F, -26F, 0F);
		rplecho.setTextureSize(64, 64);
		rplecho.mirror = true;
		setRotation(rplecho, 0.1396263F, 0F, 0F);
		rnarm = new ModelRenderer(this, 0, 0);
		rnarm.addBox(0F, 0F, 0F, 4, 16, 3);
		rnarm.setRotationPoint(-18F, -12F, 10F);
		rnarm.setTextureSize(64, 64);
		rnarm.mirror = true;
		setRotation(rnarm, -1.343904F, 0F, 0F);
		rkist = new ModelRenderer(this, 0, 0);
		rkist.addBox(0F, 0F, 0F, 7, 6, 10);
		rkist.setRotationPoint(-20F, -9F, -7F);
		rkist.setTextureSize(64, 64);
		rkist.mirror = true;
		setRotation(rkist, 0F, 0F, 0F);
		bur9r = new ModelRenderer(this, 48, 25);
		bur9r.addBox(-1F, -1F, 0F, 2, 2, 2);
		bur9r.setRotationPoint(-16.5F, -6F, -17F);
		bur9r.setTextureSize(64, 64);
		bur9r.mirror = true;
		setRotation(bur9r, 0F, 0F, 0F);
		bur8r = new ModelRenderer(this, 48, 25);
		bur8r.addBox(-2.5F, -2.5F, 0F, 5, 5, 2);
		bur8r.setRotationPoint(-16.5F, -6F, -11F);
		bur8r.setTextureSize(64, 64);
		bur8r.mirror = true;
		setRotation(bur8r, 0F, 0F, 0.7853982F);
		bur7r = new ModelRenderer(this, 48, 25);
		bur7r.addBox(-1.5F, -1.5F, 0F, 3, 3, 2);
		bur7r.setRotationPoint(-16.5F, -6F, -15F);
		bur7r.setTextureSize(64, 64);
		bur7r.mirror = true;
		setRotation(bur7r, 0F, 0F, 0.7853982F);
		bur6r = new ModelRenderer(this, 48, 25);
		bur6r.addBox(-2F, -2F, 0F, 4, 4, 2);
		bur6r.setRotationPoint(-16.5F, -6F, -13F);
		bur6r.setTextureSize(64, 64);
		bur6r.mirror = true;
		setRotation(bur6r, 0F, 0F, 0.7853982F);
		bur5r = new ModelRenderer(this, 48, 25);
		bur5r.addBox(-1F, -1F, 0F, 2, 2, 2);
		bur5r.setRotationPoint(-16.5F, -6F, -17F);
		bur5r.setTextureSize(64, 64);
		bur5r.mirror = true;
		setRotation(bur5r, 0F, 0F, 0.7853982F);
		bur4r = new ModelRenderer(this, 0, 0);
		bur4r.addBox(-1F, -1F, 0F, 2, 2, 2);
		bur4r.setRotationPoint(-16.5F, -6F, -9F);
		bur4r.setTextureSize(64, 64);
		bur4r.mirror = true;
		setRotation(bur4r, 0F, 0F, 0F);
		bur3r = new ModelRenderer(this, 48, 25);
		bur3r.addBox(-1.5F, -1.5F, 0F, 3, 3, 2);
		bur3r.setRotationPoint(-16.5F, -6F, -15F);
		bur3r.setTextureSize(64, 64);
		bur3r.mirror = true;
		setRotation(bur3r, 0F, 0F, 0F);
		bur2r = new ModelRenderer(this, 48, 25);
		bur2r.addBox(-2.5F, -2.5F, 0F, 5, 5, 2);
		bur2r.setRotationPoint(-16.5F, -6F, -11F);
		bur2r.setTextureSize(64, 64);
		bur2r.mirror = true;
		setRotation(bur2r, 0F, 0F, 0F);
		bur1r = new ModelRenderer(this, 48, 25);
		bur1r.addBox(-2F, -2F, 0F, 4, 4, 2);
		bur1r.setRotationPoint(-16.5F, -6F, -13F);
		bur1r.setTextureSize(64, 64);
		bur1r.mirror = true;
		setRotation(bur1r, 0F, 0F, 0F);
		stvol1r = new ModelRenderer(this, 26, 25);
		stvol1r.addBox(0F, 0F, 0F, 2, 2, 9);
		stvol1r.setRotationPoint(-19F, -8F, -16F);
		stvol1r.setTextureSize(64, 64);
		stvol1r.mirror = true;
		setRotation(stvol1r, 0F, 0F, 0F);
		stvol2r = new ModelRenderer(this, 26, 25);
		stvol2r.addBox(0F, 0F, 0F, 2, 2, 9);
		stvol2r.setRotationPoint(-16F, -8F, -16F);
		stvol2r.setTextureSize(64, 64);
		stvol2r.mirror = true;
		setRotation(stvol2r, 0F, 0F, 0F);
		stvol3r = new ModelRenderer(this, 0, 0);
		stvol3r.addBox(0F, 0F, 0F, 3, 2, 4);
		stvol3r.setRotationPoint(-18F, -6F, -11F);
		stvol3r.setTextureSize(64, 64);
		stvol3r.mirror = true;
		setRotation(stvol3r, 0F, 0F, 0F);
		rrocket1 = new ModelRenderer(this, 46, 43);
		rrocket1.addBox(0F, 0F, 0F, 5, 17, 4);
		rrocket1.setRotationPoint(-6F, -22F, 11F);
		rrocket1.setTextureSize(64, 64);
		rrocket1.mirror = true;
		setRotation(rrocket1, 0F, 0F, 0F);
		lrocket1 = new ModelRenderer(this, 46, 43);
		lrocket1.addBox(0F, 0F, 0F, 5, 17, 4);
		lrocket1.setRotationPoint(1F, -22F, 11F);
		lrocket1.setTextureSize(64, 64);
		lrocket1.mirror = true;
		setRotation(lrocket1, 0F, 0F, 0F);
		lrocket2 = new ModelRenderer(this, 0, 0);
		lrocket2.addBox(0F, 0F, 0F, 3, 1, 2);
		lrocket2.setRotationPoint(2F, -23F, 12F);
		lrocket2.setTextureSize(64, 64);
		lrocket2.mirror = true;
		setRotation(lrocket2, 0F, 0F, 0F);
		rrocket2 = new ModelRenderer(this, 0, 0);
		rrocket2.addBox(0F, 0F, 0F, 3, 1, 2);
		rrocket2.setRotationPoint(-5F, -23F, 12F);
		rrocket2.setTextureSize(64, 64);
		rrocket2.mirror = true;
		setRotation(rrocket2, 0F, 0F, 0F);
	}

	@Override
	public void render( Entity par1Entity, float par2, float par3, float par4,
			float par5, float par6, float f5 ) {
		super.render(par1Entity, par2, par3, par4, par5, par6, f5);
		this.setRotationAngles(par2, par3, par4, par5, par6, f5, par1Entity);

		cabin1.render(f5);
		cabin19.render(f5);
		cabin2.render(f5);
		cabin18.render(f5);
		cabin3.render(f5);
		cabin17.render(f5);
		cabin4.render(f5);
		cabin16.render(f5);
		cabin5.render(f5);
		cabin15.render(f5);
		cabin6.render(f5);
		cabin7.render(f5);
		cabin13.render(f5);
		cabin8.render(f5);
		cabin12.render(f5);
		cabin9.render(f5);
		cabin11.render(f5);
		cabin10.render(f5);
		lgolen.render(f5);
		lygodica.render(f5);
		lbedro.render(f5);
		lfoot1.render(f5);
		lfoot2.render(f5);
		rygodica.render(f5);
		rgolen.render(f5);
		rbedro.render(f5);
		rfoot1.render(f5);
		rfoot2.render(f5);
		lplecho.render(f5);
		lvarm.render(f5);
		lnarm.render(f5);
		lkist.render(f5);
		bur1l.render(f5);
		bur2l.render(f5);
		bur3l.render(f5);
		bur4l.render(f5);
		bur5l.render(f5);
		bur6l.render(f5);
		bur7l.render(f5);
		bur8l.render(f5);
		bur9l.render(f5);
		stvol3l.render(f5);
		stvol2l.render(f5);
		stvol1l.render(f5);
		rvarm.render(f5);
		rplecho.render(f5);
		rnarm.render(f5);
		rkist.render(f5);
		bur9r.render(f5);
		bur8r.render(f5);
		bur7r.render(f5);
		bur6r.render(f5);
		bur5r.render(f5);
		bur4r.render(f5);
		bur3r.render(f5);
		bur2r.render(f5);
		bur1r.render(f5);
		stvol1r.render(f5);
		stvol2r.render(f5);
		stvol3r.render(f5);
		rrocket1.render(f5);
		lrocket1.render(f5);
		lrocket2.render(f5);
		rrocket2.render(f5);
	}

	private void setRotation( ModelRenderer model, float x, float y, float z ) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	@Override
	public void setRotationAngles( float par1, float par2, float par3,
			float par4, float par5, float par6, Entity par7Entity ) {
		// System.out.println("rotationangles");

		/*
		 * this.bipedRightLeg.rotateAngleX = MathHelper.cos(par1 * 0.6662F) *
		 * 1.4F * par2; this.bipedLeftLeg.rotateAngleX = MathHelper.cos(par1 *
		 * 0.6662F + (float)Math.PI) * 1.4F * par2;
		 * this.bipedRightLeg.rotateAngleY = 0.0F;
		 * this.bipedLeftLeg.rotateAngleY = 0.0F;
		 */

		float f1 = (float) (par7Entity.motionX + par7Entity.motionX
				* par7Entity.motionZ + par7Entity.motionZ);

		lygodica.rotateAngleX = MathHelper
				.cos(par1 * 0.6662F + (float) Math.PI) * 1.4F * par2 + f1;
		// lygodica.rotateAngleX = (float) ((par2-par7Entity.motionX)%0.5);
	}

}
