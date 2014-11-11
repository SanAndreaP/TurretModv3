package sanandreasp.mods.turretmod3.registry.TurretUpgrades;

import net.minecraft.init.Blocks;
import sanandreasp.mods.turretmod3.entity.turret.EntityTurret_TSForcefield;
import net.minecraft.item.ItemStack;

public class TUpgShieldRngII extends TurretUpgrades {

	public TUpgShieldRngII() {
		this.upgName = "upgrades.nameShieldRngII";
		this.upgDesc = "upgrades.descShieldRngII";
		this.upgItem = new ItemStack(Blocks.gold_block);
		
		this.requiredUpg = TUpgShieldRngI.class;
		
		this.turrets.add(EntityTurret_TSForcefield.class);
	}
}
