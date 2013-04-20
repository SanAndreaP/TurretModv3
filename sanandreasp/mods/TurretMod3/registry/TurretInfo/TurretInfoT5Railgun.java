package sanandreasp.mods.TurretMod3.registry.TurretInfo;

import java.util.Map;

import sanandreasp.mods.TurretMod3.registry.TM3ModRegistry;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class TurretInfoT5Railgun extends TurretInfo {

	public TurretInfoT5Railgun() {
		this.maxAmmo = 256;
		this.maxHealth = 100;
		this.damage = 15;
		this.maxEXP = 256;
		this.lowerRangeY = 20.5F;
		this.upperRangeY = 5.5F;
		this.rangeX = 50.5F;
		this.desc = "turretmod3.turret.desct5r";
		this.name = "turretmod3.turret.namet5r";
		this.crafting = new Object[] {
				"SDL", " O ", "BLB",
				'S', new ItemStack(TM3ModRegistry.httm),
				'D', new ItemStack(Block.dispenser),
				'L', new ItemStack(Block.whiteStone),
				'O', new ItemStack(Block.obsidian),
				'B', new ItemStack(Block.stone)
		};
		this.itemIcon = "TurretMod3:turret_09";
		
		int i = this.registerNewAmmoType("turretmod3.turret.amtp0t5r");
		 this.addAmmo(i, new ItemStack(Item.enderPearl), 1);
		 this.addAmmo(i, new ItemStack(TM3ModRegistry.ammoItems, 1, 8), 8);
		 this.addAmmo(i, new ItemStack(Item.eyeOfEnder), 2);
//		 this.addAmmo(i, new ItemStack(TM3ModRegistry.bulletPack), 8);
		
		this.healItems.put(new ItemStack(Block.stone), 1);
		this.healItems.put(new ItemStack(Block.obsidian), 20);
		this.healItems.put(new ItemStack(Block.whiteStone), 10);
	}
}
