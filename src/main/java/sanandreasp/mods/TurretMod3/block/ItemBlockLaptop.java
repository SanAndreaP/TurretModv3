package sanandreasp.mods.TurretMod3.block;

import java.util.List;

import net.minecraft.item.Item;
import sanandreasp.mods.TurretMod3.registry.TM3ModRegistry;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemBlockWithMetadata;
import net.minecraft.item.ItemStack;

public class ItemBlockLaptop extends ItemBlockWithMetadata {

	public ItemBlockLaptop() {
		super(TM3ModRegistry.laptop);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(Item par1, CreativeTabs par2CreativeTabs, List par3List) {
		par3List.add(new ItemStack(this, 1, 0 << 3));
		par3List.add(new ItemStack(this, 1, 1 << 3));
	}
}
