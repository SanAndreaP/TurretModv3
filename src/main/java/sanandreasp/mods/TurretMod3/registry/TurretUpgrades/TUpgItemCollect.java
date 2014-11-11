package sanandreasp.mods.turretmod3.registry.TurretUpgrades;

import net.minecraft.init.Blocks;
import sanandreasp.mods.turretmod3.entity.turret.EntityTurret_TSCollector;
import net.minecraft.item.ItemStack;

public class TUpgItemCollect extends TurretUpgrades {

	public TUpgItemCollect() {
		this.upgName = "turretmod3.upgrades.nameICollect";
		this.upgDesc = "turretmod3.upgrades.descICollect";
		this.upgItem = new ItemStack(Blocks.chest);
		
		this.turrets.add(EntityTurret_TSCollector.class);
		
		this.requiredUpg = TUpgTurretCollect.class;
	}
}
