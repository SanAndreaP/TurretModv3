package sanandreasp.mods.TurretMod3.registry.TurretUpgrades;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import sanandreasp.mods.TurretMod3.entity.turret.EntityTurret_T1Arrow;
import sanandreasp.mods.TurretMod3.entity.turret.EntityTurret_T1Shotgun;
import sanandreasp.mods.TurretMod3.entity.turret.EntityTurret_T2Minigun;
import sanandreasp.mods.TurretMod3.entity.turret.EntityTurret_T2Revolver;
import sanandreasp.mods.TurretMod3.entity.turret.EntityTurret_T3Flamethrower;
import sanandreasp.mods.TurretMod3.entity.turret.EntityTurret_T3Laser;
import sanandreasp.mods.TurretMod3.entity.turret.EntityTurret_T4FLAK;
import sanandreasp.mods.TurretMod3.entity.turret.EntityTurret_T4Sniper;
import sanandreasp.mods.TurretMod3.entity.turret.EntityTurret_T5Artillery;
import sanandreasp.mods.TurretMod3.entity.turret.EntityTurret_T5Railgun;

public class TUpgPrecision extends TurretUpgrades {

	public TUpgPrecision() {
		this.upgName = "turretmod3.upgrades.namePrec";
		this.upgDesc = "turretmod3.upgrades.descPrec";
		this.upgItem = new ItemStack(Item.magmaCream);
		
		this.turrets.add(EntityTurret_T5Artillery.class);
	}

}
