package sanandreasp.mods.TurretMod3.registry.TurretInfo;

import java.util.Map;

import sanandreasp.mods.TurretMod3.registry.TM3ModRegistry;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class TurretInfoT4Sniper extends TurretInfo {

	public TurretInfoT4Sniper() {
		this.maxAmmo = 256;
		this.maxHealth = 40;
		this.damage = 25;
		this.maxEXP = 256;
		this.lowerRangeY = 20.5F;
		this.upperRangeY = 5.5F;
		this.rangeX = 64.5F;
		this.desc = "turretmod3.turret.desct4s";
		this.name = "turretmod3.turret.namet4s";
		this.crafting = new Object[] {
				"SDL", " O ", "BBB",
				'S', new ItemStack(TM3ModRegistry.httm),
				'D', new ItemStack(Block.dispenser),
				'L', new ItemStack(Block.netherBrick),
				'O', new ItemStack(Block.obsidian),
				'B', new ItemStack(Block.netherrack)
		};
		this.itemIcon = "TurretMod3:turret_07";
		
		int i = this.registerNewAmmoType("turretmod3.turret.amtp0t4s");
		 this.addAmmo(i, new ItemStack(Item.dyePowder, 1, 4), 1);
		 this.addAmmo(i, new ItemStack(Block.blockLapis), 9);
//		 this.addAmmo(i, new ItemStack(TM3ModRegistry.bulletPack), 8);
		
		this.healItems.put(new ItemStack(Block.netherrack), 1);
		this.healItems.put(new ItemStack(Block.obsidian), 20);
		this.healItems.put(new ItemStack(Block.netherBrick), 10);
	}
}
