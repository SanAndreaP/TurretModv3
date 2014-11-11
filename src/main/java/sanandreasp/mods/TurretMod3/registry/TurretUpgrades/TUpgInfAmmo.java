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
import sanandreasp.mods.turretmod3.entity.turret.EntityTurret_T5Artillery;
import sanandreasp.mods.turretmod3.entity.turret.EntityTurret_T5Railgun;
import sanandreasp.mods.turretmod3.entity.turret.EntityTurret_TSHealer;
import sanandreasp.mods.turretmod3.entity.turret.EntityTurret_TSSnowball;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.ItemStack;

public class TUpgInfAmmo extends TurretUpgrades {

	public TUpgInfAmmo() {
		this.upgName = "upgrades.nameInfbow";
		this.upgDesc = "upgrades.descInfbow";
		this.upgItem = new ItemStack(Items.bow);
		this.upgEnchantment = Enchantment.infinity;
		
		this.requiredUpg = TUpgEconomy.class;
		
		this.turrets.add(EntityTurret_T1Arrow.class);
		this.turrets.add(EntityTurret_T1Shotgun.class);
		this.turrets.add(EntityTurret_T2Minigun.class);
		this.turrets.add(EntityTurret_T2Revolver.class);
		this.turrets.add(EntityTurret_T3Laser.class);
		this.turrets.add(EntityTurret_T3Flamethrower.class);
		this.turrets.add(EntityTurret_T4Sniper.class);
		this.turrets.add(EntityTurret_T4FLAK.class);
		this.turrets.add(EntityTurret_T5Railgun.class);
		this.turrets.add(EntityTurret_T5Artillery.class);
		this.turrets.add(EntityTurret_TSSnowball.class);
		this.turrets.add(EntityTurret_TSHealer.class);
	}
}
