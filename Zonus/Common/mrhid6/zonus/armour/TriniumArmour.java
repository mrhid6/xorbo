package mrhid6.zonus.armour;

import mrhid6.zonus.Config;
import mrhid6.zonus.Zonus;
import mrhid6.zonus.items.ModItems;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.IArmorTextureProvider;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class TriniumArmour extends ItemArmor implements IArmorTextureProvider {

	public TriniumArmour( int id, EnumArmorMaterial enumMaterial, int par3, int par4 ) {
		super(id, enumMaterial, par3, par4);
		setCreativeTab(Config.creativeTabXor);
	}

	@Override
	public String getArmorTextureFile( ItemStack stack ) {

		if (stack.itemID == ModItems.triniumPlate.itemID || stack.itemID == ModItems.triniumHelm.itemID || stack.itemID == ModItems.triniumBoots.itemID) {
			return "/mods/zonus/textures/armor/trinium_1.png";
		}

		return "/mods/zonus/textures/armor/trinium_2.png";

	}

	@Override
	@SideOnly(Side.CLIENT)
	public void updateIcons( IconRegister iconRegister ) {

		switch (armorType) {
		case 0:
			iconIndex = iconRegister.registerIcon(Zonus.Modname + "triniumhelm");
			break;
		case 1:
			iconIndex = iconRegister.registerIcon(Zonus.Modname + "triniumplate");
			break;
		case 2:
			iconIndex = iconRegister.registerIcon(Zonus.Modname + "triniumlegs");
			break;
		case 3:
			iconIndex = iconRegister.registerIcon(Zonus.Modname + "triniumboots");
			break;
		}
	}

}
