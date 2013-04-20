package sanandreasp.mods.TurretMod3.registry.TurretInfo;

import java.util.Map;

import sanandreasp.mods.TurretMod3.registry.TM3ModRegistry;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class TurretInfoTSHealer extends TurretInfo {

	public TurretInfoTSHealer() {
		this.maxAmmo = 256;
		this.maxHealth = 30;
		this.maxEXP = 256;
		this.damage = 0;
//		this.item = new ItemStack(TM3ModRegistry.turretItem, 1, 7);
		this.lowerRangeY = 5.5F;
		this.upperRangeY = 5.5F;
		this.rangeX = 16.5F;
		this.desc = "turretmod3.turret.desctsh";
		this.name = "turretmod3.turret.nametsh";
		this.crafting = new Object[] {
				" DG", " W ", "CGC",
				'D', new ItemStack(Block.dispenser),
				'G', new ItemStack(Block.blockGold),
				'W', new ItemStack(Block.planks, 0, OreDictionary.WILDCARD_VALUE),
				'C', new ItemStack(Block.cobblestone)
		};
		this.itemIcon = "TurretMod3:turret_14";
		
		int i = this.registerNewAmmoType("turretmod3.turret.amtp0tsh");
		 this.addAmmo(i, new ItemStack(Item.speckledMelon), 2);
		 this.addAmmo(i, new ItemStack(TM3ModRegistry.ammoItems, 1, 7), 18);
		i = this.registerNewAmmoType("turretmod3.turret.amtp1tsh");
		 this.addAmmo(i, new ItemStack(Item.goldenCarrot), 4);
		i = this.registerNewAmmoType("turretmod3.turret.amtp2tsh");
		 this.addAmmo(i, new ItemStack(Item.goldNugget), 1);
		 
		
		this.healItems.put(new ItemStack(Block.cobblestone), 5);
		this.healItems.put(new ItemStack(Block.blockGold), 15);
		this.healItems.put(new ItemStack(Item.ingotGold), 1);
	}
}
