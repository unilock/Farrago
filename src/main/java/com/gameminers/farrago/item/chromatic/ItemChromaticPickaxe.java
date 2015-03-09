package com.gameminers.farrago.item.chromatic;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

import com.gameminers.farrago.FarragoMod;

public class ItemChromaticPickaxe extends ItemPickaxe {
	public ItemChromaticPickaxe() {
		super(ToolMaterial.IRON);
		setCreativeTab(FarragoMod.creativeTab);
	}

	private IIcon bg;
	private IIcon fg;
	
	
	@Override
	public boolean requiresMultipleRenderPasses() {
		return true;
	}
	
	@Override
	public int getRenderPasses(int metadata) {
		return 2;
	}
	
	@Override
	public IIcon getIcon(ItemStack stack, int pass) {
		return pass == 0 ? bg : fg;
	}
	
	@Override
	public void registerIcons(IIconRegister register) {
		bg = register.registerIcon("farrago:chromatic_pickaxe_head");
		fg = register.registerIcon("farrago:chromatic_pickaxe_handle");
	}
	
	@Override
	public int getColorFromItemStack(ItemStack stack, int pass) {
		return Chromatics.getColorFromItemStack(stack, pass);
	}
	
	@Override
	public ItemChromaticPickaxe setUnlocalizedName(String p_77655_1_) {
		super.setUnlocalizedName(p_77655_1_);
		return this;
	}
	
	@Override
	public boolean getIsRepairable(ItemStack a, ItemStack b) {
        return b.getItem() == FarragoMod.INGOT && b.getItemDamage() == 0 ? true : super.getIsRepairable(a, b);
    }

}
