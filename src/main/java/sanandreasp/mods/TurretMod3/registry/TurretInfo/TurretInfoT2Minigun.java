package sanandreasp.mods.TurretMod3.registry.TurretInfo;

import java.util.Map;

import sanandreasp.mods.TurretMod3.registry.TM3ModRegistry;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class TurretInfoT2Minigun extends TurretInfo {

	public TurretInfoT2Minigun() {
		this.maxAmmo = 256;
		this.maxHealth = 40;
		this.damage = 1;
		this.maxEXP = 256;
		this.lowerRangeY = 5.5F;
		this.upperRangeY = 5.5F;
		this.rangeX = 24.5F;
		this.desc = "turretmod3.turret.desct2m";
		this.name = "turretmod3.turret.namet2m";
		this.crafting = new Object[] {
				"GDL", " I ", "BBB",
				'G', new ItemStack(Item.ingotGold),
				'D', new ItemStack(Block.dispenser),
				'L', new ItemStack(Item.dyePowder, 1, 4),
				'I', new ItemStack(Block.blockIron),
				'B', new ItemStack(Block.stoneBrick)
		};
		this.itemIcon = "TurretMod3:turret_03";
		
		int i = this.registerNewAmmoType("turretmod3.turret.amtp0t2m");
		 this.addAmmo(i, new ItemStack(Item.seeds), 1);
		i = this.registerNewAmmoType("turretmod3.turret.amtp1t2m");
		 this.addAmmo(i, new ItemStack(Item.melonSeeds), 1);
		 this.addAmmo(i, new ItemStack(Item.melon), 1);
		 this.addAmmo(i, new ItemStack(Block.melon), 8);
		i = this.registerNewAmmoType("turretmod3.turret.amtp2t2m");
		 this.addAmmo(i, new ItemStack(Item.pumpkinSeeds), 1);
		 this.addAmmo(i, new ItemStack(Block.pumpkin), 4);
		
		this.healItems.put(new ItemStack(Block.blockIron), 20);
		this.healItems.put(new ItemStack(Item.ingotIron), 2);
		this.healItems.put(new ItemStack(Item.ingotGold), 8);
		this.healItems.put(new ItemStack(Item.dyePowder, 1, 4), 8);
		this.healItems.put(new ItemStack(Block.stoneBrick, 1, OreDictionary.WILDCARD_VALUE), 1);
	}
}
