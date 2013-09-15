package sanandreasp.mods.TurretMod3.registry.TurretInfo;

import java.util.Map;

import sanandreasp.mods.TurretMod3.registry.TM3ModRegistry;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class TurretInfoT3Flamethrower extends TurretInfo {

	public TurretInfoT3Flamethrower() {
		this.maxAmmo = 512;
		this.maxHealth = 60;
		this.damage = 3;
		this.maxEXP = 256;
//		this.item = new ItemStack(TM3ModRegistry.turretItem, 1, 5);
		this.lowerRangeY = 5.5F;
		this.upperRangeY = 5.5F;
		this.rangeX = 8.5F;
		this.desc = "turretmod3.turret.desct3f";
		this.name = "turretmod3.turret.namet3f";
		this.crafting = new Object[] {
				"SDL", " O ", "BIB",
				'S', new ItemStack(Item.bucketEmpty),
				'D', new ItemStack(Block.dispenser),
				'L', new ItemStack(Item.enderPearl),
				'O', new ItemStack(Block.obsidian),
				'I', new ItemStack(Block.blockIron),
				'B', new ItemStack(Item.ingotIron)
		};
		this.itemIcon = "TurretMod3:turret_06";
		
		int i = this.registerNewAmmoType("turretmod3.turret.amtp0t3f");
		 this.addAmmo(i, new ItemStack(TM3ModRegistry.ammoItems, 1, 5), 4);
		 this.addAmmo(i, new ItemStack(TM3ModRegistry.ammoItems, 1, 6), 32);
		
		this.healItems.put(new ItemStack(Block.blockIron), 15);
		this.healItems.put(new ItemStack(Item.ingotIron), 1);
		this.healItems.put(new ItemStack(Block.obsidian), 30);
	}
}
