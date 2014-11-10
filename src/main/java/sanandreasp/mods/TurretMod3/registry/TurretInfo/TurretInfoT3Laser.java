package sanandreasp.mods.TurretMod3.registry.TurretInfo;

import java.util.Map;

import sanandreasp.mods.TurretMod3.registry.TM3ModRegistry;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class TurretInfoT3Laser extends TurretInfo {

	public TurretInfoT3Laser() {
		this.maxAmmo = 256;
		this.maxHealth = 60;
		this.damage = 6;
		this.maxEXP = 256;
//		this.item = new ItemStack(TM3ModRegistry.turretItem, 1, 4);
		this.lowerRangeY = 5.5F;
		this.upperRangeY = 5.5F;
		this.rangeX = 32.5F;
		this.desc = "turretmod3.turret.desct3l";
		this.name = "turretmod3.turret.namet3l";
		this.crafting = new Object[] {
				"SDL", " O ", "BIB",
				'S', new ItemStack(Item.diamond),
				'D', new ItemStack(Block.dispenser),
				'L', new ItemStack(Item.enderPearl),
				'O', new ItemStack(Block.obsidian),
				'I', new ItemStack(Block.blockIron),
				'B', new ItemStack(Block.stone)
		};
		this.itemIcon = "TurretMod3:turret_05";
		
		int i = this.registerNewAmmoType("turretmod3.turret.amtp0t3l");
		 this.addAmmo(i, new ItemStack(Item.redstone), 1);
		 this.addAmmo(i, new ItemStack(Block.blockRedstone), 9);
		
		this.healItems.put(new ItemStack(Block.blockIron), 15);
		this.healItems.put(new ItemStack(Item.ingotIron), 1);
		this.healItems.put(new ItemStack(Block.obsidian), 30);
		this.healItems.put(new ItemStack(Block.stone, 1), 1);
	}
}
