package sanandreasp.mods.turretmod3;

import net.minecraft.item.Item;
import sanandreasp.mods.turretmod3.registry.TM3ModRegistry;
import net.minecraft.creativetab.CreativeTabs;

public class CreativeTabTurrets extends CreativeTabs {

	public CreativeTabTurrets(String label) {
		super(label);
	}

    @Override
    public Item getTabIconItem() {
        return TM3ModRegistry.turretItem;
    }
}
