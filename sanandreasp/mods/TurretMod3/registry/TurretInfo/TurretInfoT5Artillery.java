package sanandreasp.mods.TurretMod3.registry.TurretInfo;

import java.util.Map;

import sanandreasp.mods.TurretMod3.item.ItemArtilleryShells;
import sanandreasp.mods.TurretMod3.registry.TM3ModRegistry;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class TurretInfoT5Artillery extends TurretInfo {

	public TurretInfoT5Artillery() {
		this.maxAmmo = 256;
		this.maxHealth = 100;
		this.damage = 15;
		this.maxEXP = 256;
		this.lowerRangeY = 20.5F;
		this.upperRangeY = 15.5F;
		this.rangeX = 50.5F;
		this.desc = "turretmod3.turret.desct5a";
		this.name = "turretmod3.turret.namet5a";
		this.crafting = new Object[] {
				"SDL", "EO ", "BOB",
				'S', new ItemStack(TM3ModRegistry.httm),
				'D', new ItemStack(Block.dispenser),
				'L', new ItemStack(Block.whiteStone),
				'E', new ItemStack(Item.emerald),
				'O', new ItemStack(Block.obsidian),
				'B', new ItemStack(Block.stone)
		};
		this.itemIcon = "TurretMod3:turret_10";
		
		for(int j = 0; j < 10; j++) {
			int i = this.registerNewAmmoType("turretmod3.turret.amtp"+ItemArtilleryShells.getRealNumber(j)+"t5a");
			 this.addAmmo(i, new ItemStack(TM3ModRegistry.artilleryBall, 1, j), 1);
		}
//		 this.addAmmo(i, new ItemStack(TM3ModRegistry.ammoItems, 1, 8), 8);
//		 this.addAmmo(i, new ItemStack(Item.eyeOfEnder), 2);
//		 this.addAmmo(i, new ItemStack(TM3ModRegistry.bulletPack), 8);
		
		this.healItems.put(new ItemStack(Block.stone), 1);
		this.healItems.put(new ItemStack(Block.obsidian), 20);
		this.healItems.put(new ItemStack(Block.whiteStone), 10);
	}
}
