package sanandreasp.mods.TurretMod3;

import sanandreasp.mods.TurretMod3.registry.TM3ModRegistry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class CreativeTabTurrets extends CreativeTabs {

	public CreativeTabTurrets(String label) {
		super(label);
	}

	@Override
	public ItemStack getIconItemStack() {
		return new ItemStack(TM3ModRegistry.turretItem, 1, 0);
	}
}
