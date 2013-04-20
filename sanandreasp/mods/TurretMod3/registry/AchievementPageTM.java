package sanandreasp.mods.TurretMod3.registry;

import sanandreasp.mods.managers.SAP_LanguageManager;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.src.ModLoader;
import net.minecraft.stats.Achievement;
import net.minecraftforge.common.AchievementPage;

public class AchievementPageTM extends AchievementPage {

	public static Achievement turretInfo;
	public static Achievement firstStrike;
	public static Achievement multiDeath;
	public static Achievement control;
	public static Achievement camKill;
	public static Achievement upgrade;
	public static Achievement piercing;
	
	public static int[] achievementIDs = new int[] {
		4000,
		4001,
		4002,
		4003,
		4004,
		4005,
		4006
	};
	
	public static String[] achieveNames = new String[] {
			"Turret Info",
			"First Strike",
			"Multi-Kill",
			"Control",
			"Cam Kill",
			"Upgrade",
			"Piercing"
		};
	
	public AchievementPageTM(String name, Achievement... par2Achievement) {
		super(name, par2Achievement);
	}

	public static void initAchievementPage() {
		turretInfo = new Achievement(achievementIDs[0], "turretmod3.tinfo", 0, 0, new ItemStack(TM3ModRegistry.tInfoBook), null).registerAchievement();
		firstStrike = new Achievement(achievementIDs[1], "turretmod3.firstStrike", -2, 0, new ItemStack(Item.arrow), turretInfo).registerAchievement();
		multiDeath = new Achievement(achievementIDs[2], "turretmod3.multiDeath", -2, -2, new ItemStack(TM3ModRegistry.turretItem, 1, 1), firstStrike).registerAchievement();
		control = new Achievement(achievementIDs[3], "turretmod3.control", 0, 2, new ItemStack(TM3ModRegistry.tcu), turretInfo).registerAchievement();
		camKill = new Achievement(achievementIDs[4], "turretmod3.camKill", -2, 2, new ItemStack(Item.saddle), control).registerAchievement();
		upgrade = new Achievement(achievementIDs[5], "turretmod3.upgrade", 2, 2, new ItemStack(Item.swordGold), control).registerAchievement();
		piercing = new Achievement(achievementIDs[6], "turretmod3.piercing", -4, 0, new ItemStack(TM3ModRegistry.iconCache, 1, 0), firstStrike).registerAchievement();
		
		AchievementPage.registerAchievementPage(new AchievementPage("Turret Mod 3", 
				turretInfo,
				firstStrike,
				multiDeath,
				control,
				camKill,
				upgrade,
				piercing
		));
	}
	
	public static void initTexts(SAP_LanguageManager languageManager) {
		languageManager.addLangPropS("achievement.turretmod3.tinfo", "Get the Infos!");
		languageManager.addLangPropS("achievement.turretmod3.tinfo.desc", "Read the Turret Info Book");
		languageManager.addLangPropS("achievement.turretmod3.firstStrike", "First Strike!");
		languageManager.addLangPropS("achievement.turretmod3.firstStrike.desc", "Cause your first death with your turret");
		languageManager.addLangPropS("achievement.turretmod3.multiDeath", "Multi-Kill!");
		languageManager.addLangPropS("achievement.turretmod3.multiDeath.desc", "Kill multiple targets with your Shotgun Turret");
		languageManager.addLangPropS("achievement.turretmod3.control", "Take The Control!");
		languageManager.addLangPropS("achievement.turretmod3.control.desc", "Use the TCU on a turret for the first time");
		languageManager.addLangPropS("achievement.turretmod3.camKill", "Cam-Kill!");
		languageManager.addLangPropS("achievement.turretmod3.camKill.desc", "Kill something while you riding your turret");
		languageManager.addLangPropS("achievement.turretmod3.upgrade", "Upgrade Your Stuff!");
		languageManager.addLangPropS("achievement.turretmod3.upgrade.desc", "Upgrade one of your turrets for the first time");
		languageManager.addLangPropS("achievement.turretmod3.piercing", "Piercing Blast!");
		languageManager.addLangPropS("achievement.turretmod3.piercing.desc", "Cause 10 Kills in 10 seconds with your T2 Minigun Turret");
	}
}
