package sanandreasp.mods.turretmod3.registry.TurretUpgrades;

import net.minecraft.init.Blocks;
import sanandreasp.mods.turretmod3.entity.turret.EntityTurret_T3Flamethrower;
import net.minecraft.item.ItemStack;

public class TUpgPurify extends TurretUpgrades {

	public TUpgPurify() {
		this.upgName = "upgrades.namePurify";
		this.upgDesc = "upgrades.descPurify";
		this.upgItem = new ItemStack(Blocks.lapis_block);
		
		this.turrets.add(EntityTurret_T3Flamethrower.class);
	}
}
