package sanandreasp.mods.TurretMod3.registry.TurretUpgrades;

import sanandreasp.mods.TurretMod3.entity.turret.EntityTurret_Base;
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
import sanandreasp.mods.TurretMod3.entity.turret.EntityTurret_TSForcefield;
import sanandreasp.mods.TurretMod3.entity.turret.EntityTurret_TSHealer;
import sanandreasp.mods.TurretMod3.entity.turret.EntityTurret_TSSnowball;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class TUpgShieldRngII extends TurretUpgrades {

	public TUpgShieldRngII() {
		this.upgName = "turretmod3.upgrades.nameShieldRngII";
		this.upgDesc = "turretmod3.upgrades.descShieldRngII";
		this.upgItem = new ItemStack(Block.blockGold);
		
		this.requiredUpg = TUpgShieldRngI.class;
		
		this.turrets.add(EntityTurret_TSForcefield.class);
	}
}
