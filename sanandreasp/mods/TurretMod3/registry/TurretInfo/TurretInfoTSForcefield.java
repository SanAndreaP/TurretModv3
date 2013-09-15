package sanandreasp.mods.TurretMod3.registry.TurretInfo;

import java.util.Map;

import sanandreasp.mods.TurretMod3.registry.TM3ModRegistry;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class TurretInfoTSForcefield extends TurretInfo {

	public TurretInfoTSForcefield() {
		this.maxAmmo = 0;
		this.maxHealth = 40;
		this.maxEXP = 0;
		this.damage = 0;
		this.lowerRangeY = 0F;
		this.upperRangeY = 16.5F;
		this.rangeX = 16.5F;
		this.desc = "turretmod3.turret.desctsf";
		this.name = "turretmod3.turret.nametsf";
		this.crafting = new Object[] {
				"P P", "OEO", "WWW",
				'P', new ItemStack(Item.eyeOfEnder),
				'E', new ItemStack(Block.blockRedstone),
				'O', new ItemStack(Block.blockNetherQuartz, 1, OreDictionary.WILDCARD_VALUE),
				'W', new ItemStack(Block.obsidian)
		};
		this.itemIcon = "TurretMod3:turret_13";
		
		this.healItems.put(new ItemStack(Item.redstone), 1);
		this.healItems.put(new ItemStack(Block.blockRedstone), 9);
		this.healItems.put(new ItemStack(Block.obsidian), 20);
		this.healItems.put(new ItemStack(Item.netherQuartz), 2);
		this.healItems.put(new ItemStack(Block.blockNetherQuartz), 18);
	}
}
