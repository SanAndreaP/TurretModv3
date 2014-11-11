package sanandreasp.mods.TurretMod3;

import net.minecraft.item.Item;
import sanandreasp.mods.TurretMod3.registry.TM3ModRegistry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class CreativeTabTurrets extends CreativeTabs {

	public CreativeTabTurrets(String label) {
		super(label);
	}

    @Override
    public Item getTabIconItem() {
        return TM3ModRegistry.turretItem;
    }
}
