package sanandreasp.mods.TurretMod3.registry.TurretUpgrades;

import sanandreasp.mods.TurretMod3.entity.turret.EntityTurret_Base;
import sanandreasp.mods.TurretMod3.entity.turret.EntityTurret_T1Arrow;
import sanandreasp.mods.TurretMod3.entity.turret.EntityTurret_T1Shotgun;
import sanandreasp.mods.TurretMod3.entity.turret.EntityTurret_T2Minigun;
import sanandreasp.mods.TurretMod3.entity.turret.EntityTurret_T2Revolver;
import sanandreasp.mods.TurretMod3.entity.turret.EntityTurret_T3Flamethrower;
import sanandreasp.mods.TurretMod3.entity.turret.EntityTurret_T3Laser;
import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class TUpgPurify extends TurretUpgrades {

	public TUpgPurify() {
		this.upgName = "turretmod3.upgrades.namePurify";
		this.upgDesc = "turretmod3.upgrades.descPurify";
		this.upgItem = new ItemStack(Block.blockLapis);
		
		this.turrets.add(EntityTurret_T3Flamethrower.class);
	}
}
