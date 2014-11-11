package sanandreasp.mods.turretmod3.registry.TurretUpgrades;

import net.minecraft.init.Items;
import sanandreasp.mods.turretmod3.entity.turret.EntityTurret_T1Arrow;
import sanandreasp.mods.turretmod3.entity.turret.EntityTurret_T1Shotgun;
import sanandreasp.mods.turretmod3.entity.turret.EntityTurret_T2Minigun;
import sanandreasp.mods.turretmod3.entity.turret.EntityTurret_T2Revolver;
import sanandreasp.mods.turretmod3.entity.turret.EntityTurret_T3Flamethrower;
import sanandreasp.mods.turretmod3.entity.turret.EntityTurret_T3Laser;
import sanandreasp.mods.turretmod3.entity.turret.EntityTurret_T4FLAK;
import sanandreasp.mods.turretmod3.entity.turret.EntityTurret_T4Sniper;
import sanandreasp.mods.turretmod3.entity.turret.EntityTurret_T5Railgun;
import sanandreasp.mods.turretmod3.entity.turret.EntityTurret_TSSnowball;
import net.minecraft.item.ItemStack;

public class TUpgEnderHitting extends TurretUpgrades {

	public TUpgEnderHitting() {
		this.upgName = "upgrades.nameEnderHit";
		this.upgDesc = "upgrades.descEnderHit";
		this.upgItem = new ItemStack(Items.water_bucket);
		
		this.turrets.add(EntityTurret_T1Arrow.class);
		this.turrets.add(EntityTurret_T1Shotgun.class);
		this.turrets.add(EntityTurret_T2Minigun.class);
		this.turrets.add(EntityTurret_T2Revolver.class);
		this.turrets.add(EntityTurret_T3Laser.class);
		this.turrets.add(EntityTurret_T3Flamethrower.class);
		this.turrets.add(EntityTurret_T4Sniper.class);
		this.turrets.add(EntityTurret_T4FLAK.class);
		this.turrets.add(EntityTurret_T5Railgun.class);
		this.turrets.add(EntityTurret_TSSnowball.class);
	}
}
