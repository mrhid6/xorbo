package mrhid6.xorbo.armour;

import mrhid6.xorbo.Config;
import mrhid6.xorbo.items.ModItems;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraftforge.common.IArmorTextureProvider;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;


public class TriniumArmour extends ItemArmor implements IArmorTextureProvider{

	public TriniumArmour( int id, EnumArmorMaterial enumMaterial, int par3, int par4 ) {
		super(id, enumMaterial, par3, par4);
		setCreativeTab(Config.creativeTabXor);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void updateIcons( IconRegister iconRegister ) {

		switch(this.armorType){
		case 0:
			this.iconIndex = iconRegister.registerIcon("xorbo:triniumhelm");
			break;
		case 1:
			this.iconIndex = iconRegister.registerIcon("xorbo:triniumplate");
			break;
		case 2:
			this.iconIndex = iconRegister.registerIcon("xorbo:triniumlegs");
			break;
		case 3:
			this.iconIndex = iconRegister.registerIcon("xorbo:triniumboots");
			break;
		}
	}

	public String getArmorTextureFile(ItemStack stack)
	{

		if(stack.itemID == ModItems.triniumPlate.itemID || stack.itemID == ModItems.triniumHelm.itemID || stack.itemID == ModItems.triniumBoots.itemID){
			return "/mods/xorbo/textures/armor/trinium_1.png";
		}

		return "/mods/xorbo/textures/armor/trinium_2.png";

	}



}
