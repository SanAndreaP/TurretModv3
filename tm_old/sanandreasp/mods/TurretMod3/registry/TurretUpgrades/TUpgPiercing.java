package sanandreasp.mods.TurretMod3.registry.TurretUpgrades;

import sanandreasp.mods.TurretMod3.entity.turret.EntityTurret_Base;
import sanandreasp.mods.TurretMod3.entity.turret.EntityTurret_T1Arrow;
import sanandreasp.mods.TurretMod3.entity.turret.EntityTurret_T1Shotgun;
import sanandreasp.mods.TurretMod3.entity.turret.EntityTurret_T2Minigun;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class TUpgPiercing extends TurretUpgrades {

	public TUpgPiercing() {
		this.upgName = "turretmod3.upgrades.nameGold";
		this.upgDesc = "turretmod3.upgrades.descGold";
		this.upgItem = new ItemStack(Item.goldNugget);
		
		this.turrets.add(EntityTurret_T2Minigun.class);
	}

}
