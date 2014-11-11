package sanandreasp.mods.turretmod3.registry.TurretUpgrades;

import net.minecraft.init.Items;
import sanandreasp.mods.turretmod3.entity.turret.EntityTurret_TSForcefield;
import net.minecraft.item.ItemStack;

public class TUpgShieldMobPush extends TurretUpgrades {

	public TUpgShieldMobPush() {
		this.upgName = "upgrades.nameShieldPush";
		this.upgDesc = "upgrades.descShieldPush";
		this.upgItem = new ItemStack(Items.ghast_tear);
		
		this.turrets.add(EntityTurret_TSForcefield.class);
	}
}
