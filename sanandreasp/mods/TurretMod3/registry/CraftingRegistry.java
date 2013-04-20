package sanandreasp.mods.TurretMod3.registry;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import cpw.mods.fml.common.registry.GameRegistry;
import sanandreasp.mods.TurretMod3.registry.TurretInfo.TurretInfo;

public final class CraftingRegistry {
	public static void initCraftings() {
		// Turret Items
		for(int i = 0; i < TurretInfo.getTurretCount(); i++) {
			TurretInfo tinf = TurretInfo.getTurretInfo(TurretInfo.getTurretClass(i));
			GameRegistry.addRecipe(tinf.getTurretItem(), tinf.getCrafting());
		}
		
		// laptops
		for(int i = 0; i < 2; i++) {
			GameRegistry.addRecipe(new ItemStack(TM3ModRegistry.laptop, 1, i*8),
					"  #", "QRT", "###",
					'#', i == 0 ? new ItemStack(Block.blockNetherQuartz, 1, OreDictionary.WILDCARD_VALUE) : new ItemStack(Block.obsidian),
					'Q', new ItemStack(Item.field_94583_ca),
					'R', new ItemStack(Item.redstone),
					'T', new ItemStack(Block.torchRedstoneActive)
			);
		}
		
		// Arrow Pack
		GameRegistry.addRecipe(new ItemStack(TM3ModRegistry.ammoItems, 1, 0), 
				"###", "#+#", "###",
				'#', new ItemStack(Item.arrow),
				'+', new ItemStack(Item.leather)
		);
		
		// Ender Pearl Bundle
		GameRegistry.addRecipe(new ItemStack(TM3ModRegistry.ammoItems, 1, 8), 
				"###", "#+#", "###",
				'#', new ItemStack(Item.enderPearl),
				'+', new ItemStack(Item.silk)
		);
		
		// Glistering Melon Bundle
		GameRegistry.addRecipe(new ItemStack(TM3ModRegistry.ammoItems, 1, 7), 
				"###", "###", "###",
				'#', new ItemStack(Item.speckledMelon)
		);
		
		// Bullets
		GameRegistry.addShapelessRecipe(new ItemStack(TM3ModRegistry.ammoItems, 2, 3),
				new ItemStack(Item.ingotIron),
				new ItemStack(Item.gunpowder)
		);
		
		// Bullet Pack
		GameRegistry.addRecipe(new ItemStack(TM3ModRegistry.ammoItems, 1, 4), 
				"###", "#+#", "###",
				'#', new ItemStack(TM3ModRegistry.ammoItems, 1, 3),
				'+', new ItemStack(Item.goldNugget)
		);
		
		// Fuel Tank
		GameRegistry.addShapelessRecipe(new ItemStack(TM3ModRegistry.ammoItems, 1, 5),
				new ItemStack(Block.netherBrick),
				new ItemStack(Item.bucketLava)
		);
		
		// Fuel Tank Pack
		GameRegistry.addRecipe(new ItemStack(TM3ModRegistry.ammoItems, 1, 6), 
				"###", "#+#", "###",
				'#', new ItemStack(TM3ModRegistry.ammoItems, 1, 5),
				'+', new ItemStack(Item.silk)
		);
		
		// Rockets
		ItemStack[] is = new ItemStack[] {new ItemStack(Item.coal), new ItemStack(Item.ingotIron), new ItemStack(Item.ingotGold)};
		for(int i = 0; i < 3; i++) {
			GameRegistry.addRecipe(new ItemStack(TM3ModRegistry.rocket, 2, i),
				" T ", "IGI",
				'T', is[i],
				'I', new ItemStack(Item.ingotIron),
				'G', new ItemStack(Item.gunpowder)
			);
			GameRegistry.addRecipe(new ItemStack(TM3ModRegistry.rocket, 2, i+3),
					" T ", "IGI", " E ",
					'T', is[i],
					'I', new ItemStack(Item.ingotIron),
					'G', new ItemStack(Item.gunpowder),
					'E', new ItemStack(Item.enderPearl)
			);
		}
		
		// Artillery Shells
		  // Art. Shell
		GameRegistry.addRecipe(new ItemStack(TM3ModRegistry.artilleryBall, 1, 0),
				" + ", "+F+", " + ",
				'+', new ItemStack(Item.gunpowder),
				'F', new ItemStack(Item.fireballCharge)
		);
		  // Big Art. Shell
		GameRegistry.addRecipe(new ItemStack(TM3ModRegistry.artilleryBall, 1, 1),
				"+++", "+F+", "+++",
				'+', new ItemStack(Item.gunpowder),
				'F', new ItemStack(Item.fireballCharge)
		);
		GameRegistry.addRecipe(new ItemStack(TM3ModRegistry.artilleryBall, 1, 1),
				" + ", "+F+", " + ",
				'+', new ItemStack(Item.gunpowder),
				'F', new ItemStack(TM3ModRegistry.artilleryBall, 1, 0)
		);
		  // Napalm Art. Shell
		GameRegistry.addShapelessRecipe(new ItemStack(TM3ModRegistry.artilleryBall, 1, 2),
				new ItemStack(TM3ModRegistry.artilleryBall, 1, 0),
				new ItemStack(Item.blazePowder)
		);
		  // Big Napalm Art. Shell
		GameRegistry.addShapelessRecipe(new ItemStack(TM3ModRegistry.artilleryBall, 1, 3),
				new ItemStack(TM3ModRegistry.artilleryBall, 1, 1),
				new ItemStack(Item.blazePowder)
		);
		  // Frag Art. Shell
		GameRegistry.addShapelessRecipe(new ItemStack(TM3ModRegistry.artilleryBall, 1, 4),
				new ItemStack(TM3ModRegistry.artilleryBall, 1, 0),
				new ItemStack(Item.lightStoneDust)
		);
		  // Griefing Art. Shells
		for(int i = 0; i < 5; i++) {
			GameRegistry.addShapelessRecipe(new ItemStack(TM3ModRegistry.artilleryBall, 1, 5+i),
					new ItemStack(TM3ModRegistry.artilleryBall, 1, i),
					new ItemStack(Item.magmaCream)
			);
		}
		
		// Turret Info Book
		GameRegistry.addShapelessRecipe(new ItemStack(TM3ModRegistry.tInfoBook, 1),
				new ItemStack(Block.dispenser),
				new ItemStack(Item.book),
				new ItemStack(Item.dyePowder, 1, 0)
		);
		
		// TCU
		GameRegistry.addRecipe(new ItemStack(TM3ModRegistry.tcu, 1),
				" | ", "RBR", "III",
				'|', new ItemStack(Item.stick),
				'R', new ItemStack(Item.redstone),
				'B', new ItemStack(Item.ingotGold),
				'I', new ItemStack(Item.ingotIron)
		);
		
		// HT Turret Module
		GameRegistry.addRecipe(new ItemStack(TM3ModRegistry.httm, 1),
				"RDR", "GGG", "  E",
				'R', new ItemStack(Item.redstone),
				'D', new ItemStack(Item.diamond),
				'G', new ItemStack(Item.ingotGold),
				'E', new ItemStack(Item.enderPearl)
		);
		
		// Mobile Base
		GameRegistry.addRecipe(new ItemStack(TM3ModRegistry.mobileBase, 1),
				"IBI", "B B", "IBI",
				'I', new ItemStack(Item.stick),
				'B', new ItemStack(Block.stone)
		);
		
		// Pebble -> Pebbles
		Object[] objArr = new ItemStack[5];
		for(int i = 0; i < 5; i++) objArr[i] = new ItemStack(TM3ModRegistry.ammoItems, 1, 2);
		GameRegistry.addShapelessRecipe(new ItemStack(TM3ModRegistry.ammoItems, 1, 1), objArr);
		
		// Pebbles -> Gravel
		objArr = new ItemStack[8];
		for(int i = 0; i < 8; i++) objArr[i] = new ItemStack(TM3ModRegistry.ammoItems, 1, 1);
		GameRegistry.addShapelessRecipe(new ItemStack(Block.gravel, 1), objArr);
		
		// Arrow Pack -> Arrows
		GameRegistry.addShapelessRecipe(new ItemStack(Item.arrow, 8), new ItemStack(TM3ModRegistry.ammoItems, 1, 0));
		
		// Gravel -> Pebbles
		GameRegistry.addShapelessRecipe(new ItemStack(TM3ModRegistry.ammoItems, 8, 1), new ItemStack(Block.gravel));
		
		// Bullet Pack -> Bullets
		GameRegistry.addShapelessRecipe(new ItemStack(TM3ModRegistry.ammoItems, 8, 3), new ItemStack(TM3ModRegistry.ammoItems, 1, 4));
		
		// Tank Pack -> Fuel Tank
		GameRegistry.addShapelessRecipe(new ItemStack(TM3ModRegistry.ammoItems, 8, 5), new ItemStack(TM3ModRegistry.ammoItems, 1, 6));
		
		// Glister Melon Bundle -> Glister Melon
		GameRegistry.addShapelessRecipe(new ItemStack(Item.speckledMelon, 8), new ItemStack(TM3ModRegistry.ammoItems, 1, 7));
		
		// Ender Pearl Bundle -> Ender Pearls
		GameRegistry.addShapelessRecipe(new ItemStack(Item.enderPearl, 8), new ItemStack(TM3ModRegistry.ammoItems, 1, 8));
	}
}
