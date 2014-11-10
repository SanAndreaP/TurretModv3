package sanandreasp.mods.TurretMod3.registry.TurretInfo;

import java.util.Map;

import sanandreasp.mods.TurretMod3.registry.TM3ModRegistry;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class TurretInfoTSSnowball extends TurretInfo {

	public TurretInfoTSSnowball() {
		this.maxAmmo = 256;
		this.maxHealth = 20;
		this.maxEXP = 256;
		this.damage = 0;
		this.lowerRangeY = 5.5F;
		this.upperRangeY = 5.5F;
		this.rangeX = 16.5F;
		this.desc = "turretmod3.turret.desctss";
		this.name = "turretmod3.turret.nametss";
		this.crafting = new Object[] {
				" DS", " W ", "CSC",
				'D', new ItemStack(Block.dispenser),
				'S', new ItemStack(Block.blockSnow),
				'W', new ItemStack(Block.planks, 0, OreDictionary.WILDCARD_VALUE),
				'C', new ItemStack(Block.cobblestone)
		};
		this.itemIcon = "TurretMod3:turret_11";
		
		int i = this.registerNewAmmoType("turretmod3.turret.amtp0tss");
		 this.addAmmo(i, new ItemStack(Item.snowball), 1);
		 this.addAmmo(i, new ItemStack(Block.snow, 1), 2);
		 this.addAmmo(i, new ItemStack(Block.blockSnow, 1), 4);
		
		this.healItems.put(new ItemStack(Block.cobblestoneMossy), 10);
		this.healItems.put(new ItemStack(Block.cobblestone), 5);
		this.healItems.put(new ItemStack(Block.wood, 1, OreDictionary.WILDCARD_VALUE), 2);
		this.healItems.put(new ItemStack(Block.planks, 1, OreDictionary.WILDCARD_VALUE), 1);
	}
}
