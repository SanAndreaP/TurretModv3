package sanandreasp.mods.turretmod3.registry.TurretUpgrades;

import sanandreasp.mods.turretmod3.entity.turret.EntityTurret_TSCollector;
import sanandreasp.mods.turretmod3.registry.TM3ModRegistry;
import net.minecraft.item.ItemStack;

public class TUpgTurretCollect extends TurretUpgrades {

	public TUpgTurretCollect() {
		this.upgName = "upgrades.nameTCollect";
		this.upgDesc = "upgrades.descTCollect";
		this.upgItem = new ItemStack(TM3ModRegistry.httm);
		
		this.turrets.add(EntityTurret_TSCollector.class);
	}
}
