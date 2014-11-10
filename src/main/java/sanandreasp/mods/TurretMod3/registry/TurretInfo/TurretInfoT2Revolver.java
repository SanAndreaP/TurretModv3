package sanandreasp.mods.TurretMod3.registry.TurretInfo;

import java.util.Map;

import sanandreasp.mods.TurretMod3.registry.TM3ModRegistry;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class TurretInfoT2Revolver extends TurretInfo {

	public TurretInfoT2Revolver() {
		this.maxAmmo = 256;
		this.maxHealth = 40;
		this.damage = 4;
		this.maxEXP = 256;
//		this.item = new ItemStack(TM3ModRegistry.turretItem, 1, 3);
		this.lowerRangeY = 5.5F;
		this.upperRangeY = 5.5F;
		this.rangeX = 24.5F;
		this.desc = "turretmod3.turret.desct2r";
		this.name = "turretmod3.turret.namet2r";
		this.crafting = new Object[] {
				"SDL", " I ", "BBB",
				'S', new ItemStack(Block.blockIron),
				'D', new ItemStack(Block.dispenser),
				'L', new ItemStack(Item.dyePowder, 1, 4),
				'I', new ItemStack(Block.stoneBrick),
				'B', new ItemStack(Block.stone)
		};
		this.itemIcon = "TurretMod3:turret_04";
		
		int i = this.registerNewAmmoType("turretmod3.turret.amtp0t2r");
		 this.addAmmo(i, new ItemStack(TM3ModRegistry.ammoItems, 1, 3), 1);
		 this.addAmmo(i, new ItemStack(TM3ModRegistry.ammoItems, 1, 4), 8);
		
		this.healItems.put(new ItemStack(Block.blockIron), 20);
		this.healItems.put(new ItemStack(Item.ingotIron), 2);
		this.healItems.put(new ItemStack(Item.dyePowder, 1, 4), 8);
		this.healItems.put(new ItemStack(Block.stoneBrick, 1, OreDictionary.WILDCARD_VALUE), 2);
		this.healItems.put(new ItemStack(Block.stone, 1), 1);
	}
}
