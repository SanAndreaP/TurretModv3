package sanandreasp.mods.TurretMod3.registry.TurretUpgrades;

import net.minecraft.init.Items;
import sanandreasp.mods.TurretMod3.entity.turret.EntityTurret_T2Minigun;
import net.minecraft.item.ItemStack;

public class TUpgPiercing extends TurretUpgrades {

	public TUpgPiercing() {
		this.upgName = "upgrades.nameGold";
		this.upgDesc = "upgrades.descGold";
		this.upgItem = new ItemStack(Items.gold_nugget);
		
		this.turrets.add(EntityTurret_T2Minigun.class);
	}

}
