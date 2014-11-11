package sanandreasp.mods.TurretMod3.registry.TurretUpgrades;

import net.minecraft.init.Blocks;
import sanandreasp.mods.TurretMod3.entity.turret.EntityTurret_Base;
import sanandreasp.mods.TurretMod3.entity.turret.EntityTurret_T1Arrow;
import sanandreasp.mods.TurretMod3.entity.turret.EntityTurret_T1Shotgun;
import sanandreasp.mods.TurretMod3.entity.turret.EntityTurret_T2Minigun;
import sanandreasp.mods.TurretMod3.entity.turret.EntityTurret_T2Revolver;
import sanandreasp.mods.TurretMod3.entity.turret.EntityTurret_T3Laser;
import sanandreasp.mods.TurretMod3.entity.turret.EntityTurret_TSCollector;
import sanandreasp.mods.TurretMod3.entity.turret.EntityTurret_TSForcefield;
import sanandreasp.mods.TurretMod3.entity.turret.EntityTurret_TSHealer;
import sanandreasp.mods.TurretMod3.entity.turret.EntityTurret_TSSnowball;
import net.minecraft.item.ItemStack;

public class TUpgFireImmunity extends TurretUpgrades {

	public TUpgFireImmunity() {
		this.upgName = "upgrades.nameFireImmune";
		this.upgDesc = "upgrades.descFireImmune";
		this.upgItem = new ItemStack(Blocks.netherrack);
		
		this.turrets.add(EntityTurret_T1Arrow.class);
		this.turrets.add(EntityTurret_T1Shotgun.class);
		this.turrets.add(EntityTurret_T2Minigun.class);
		this.turrets.add(EntityTurret_T2Revolver.class);
		this.turrets.add(EntityTurret_T3Laser.class);
		this.turrets.add(EntityTurret_TSSnowball.class);
		this.turrets.add(EntityTurret_TSCollector.class);
		this.turrets.add(EntityTurret_TSForcefield.class);
		this.turrets.add(EntityTurret_TSHealer.class);
	}
}
