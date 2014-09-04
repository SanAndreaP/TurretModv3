package sanandreasp.mods.TurretMod3.registry.TurretInfo;

import java.util.Map;

import sanandreasp.mods.TurretMod3.registry.TM3ModRegistry;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class TurretInfoTSCollector extends TurretInfo {

	public TurretInfoTSCollector() {
		this.maxAmmo = 0;
		this.maxHealth = 60;
		this.maxEXP = 1024;
		this.damage = 0;
		this.lowerRangeY = 16.5F;
		this.upperRangeY = 16.5F;
		this.rangeX = 16.5F;
		this.desc = "turretmod3.turret.desctsc";
		this.name = "turretmod3.turret.nametsc";
		this.crafting = new Object[] {
				"P P", "OEO", "WWW",
				'P', new ItemStack(Item.enderPearl),
				'E', new ItemStack(Block.blockEmerald),
				'O', new ItemStack(Block.obsidian),
				'W', new ItemStack(Block.whiteStone)
		};
		this.itemIcon = "TurretMod3:turret_12";
		
		this.healItems.put(new ItemStack(Block.whiteStone), 30);
		this.healItems.put(new ItemStack(Block.obsidian), 20);
		this.healItems.put(new ItemStack(Item.emerald), 2);
		this.healItems.put(new ItemStack(Block.blockEmerald), 18);
	}
}
