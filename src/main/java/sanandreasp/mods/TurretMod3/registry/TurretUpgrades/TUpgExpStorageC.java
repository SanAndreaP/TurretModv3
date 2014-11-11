package sanandreasp.mods.TurretMod3.registry.TurretUpgrades;

import net.minecraft.init.Items;
import sanandreasp.mods.TurretMod3.entity.turret.EntityTurret_TSCollector;
import net.minecraft.item.ItemStack;

public class TUpgExpStorageC extends TurretUpgrades {

	public TUpgExpStorageC() {
		this.upgName = "upgrades.nameMoreXP";
		this.upgDesc = "upgrades.descMoreXP";
		this.upgItem = new ItemStack(Items.ender_eye);
		
		this.turrets.add(EntityTurret_TSCollector.class);
	}
}
