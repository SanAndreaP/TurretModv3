package sanandreasp.mods.TurretMod3.registry.TurretInfo;

import java.util.Map;

import sanandreasp.mods.TurretMod3.registry.TM3ModRegistry;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class TurretInfoT4FLAK extends TurretInfo {

	public TurretInfoT4FLAK() {
		this.maxAmmo = 256;
		this.maxHealth = 80;
		this.damage = 2;
		this.maxEXP = 256;
		this.lowerRangeY = 0.0F;
		this.upperRangeY = 50.5F;
		this.rangeX = 50.5F;
		this.desc = "turretmod3.turret.desct4f";
		this.name = "turretmod3.turret.namet4f";
		this.crafting = new Object[] {
				"SDL", " L ", "OOO",
				'S', new ItemStack(TM3ModRegistry.httm),
				'D', new ItemStack(Block.dispenser),
				'L', new ItemStack(Block.netherBrick),
				'O', new ItemStack(Block.obsidian)
		};
		this.itemIcon = "TurretMod3:turret_08";
		
		int i = this.registerNewAmmoType("turretmod3.turret.amtp0t4f");
		 this.addAmmo(i, new ItemStack(TM3ModRegistry.rocket, 1, 0), 1);
		i = this.registerNewAmmoType("turretmod3.turret.amtp1t4f");
		 this.addAmmo(i, new ItemStack(TM3ModRegistry.rocket, 1, 1), 1);
		i = this.registerNewAmmoType("turretmod3.turret.amtp2t4f");
		 this.addAmmo(i, new ItemStack(TM3ModRegistry.rocket, 1, 2), 1);
		i = this.registerNewAmmoType("turretmod3.turret.amtp3t4f");
		 this.addAmmo(i, new ItemStack(TM3ModRegistry.rocket, 1, 3), 1);
		i = this.registerNewAmmoType("turretmod3.turret.amtp4t4f");
		 this.addAmmo(i, new ItemStack(TM3ModRegistry.rocket, 1, 4), 1);
		i = this.registerNewAmmoType("turretmod3.turret.amtp5t4f");
		 this.addAmmo(i, new ItemStack(TM3ModRegistry.rocket, 1, 5), 1);
		
		this.healItems.put(new ItemStack(Block.obsidian), 40);
		this.healItems.put(new ItemStack(Block.netherBrick), 20);
	}
}
