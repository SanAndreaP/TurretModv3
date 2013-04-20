package sanandreasp.mods.TurretMod3.registry;

import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.logging.Level;

import javax.management.ReflectionException;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

import com.google.common.collect.Maps;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.crash.CrashReport;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.util.ReportedException;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraftforge.common.ChestGenHooks;
import net.minecraftforge.common.MinecraftForge;
import sanandreasp.mods.TurretMod3.CreativeTabTurrets;
import sanandreasp.mods.TurretMod3.block.BlockLaptop;
import sanandreasp.mods.TurretMod3.block.ItemBlockLaptop;
import sanandreasp.mods.TurretMod3.client.gui.mainMenu.MainMenuTM3;
import sanandreasp.mods.TurretMod3.client.packet.PacketHandlerClient;
import sanandreasp.mods.TurretMod3.client.registry.KeyBindHandler;
import sanandreasp.mods.TurretMod3.client.registry.SoundRegistry;
import sanandreasp.mods.TurretMod3.client.registry.TickHandlerClientRnd;
import sanandreasp.mods.TurretMod3.command.CommandTurretMod;
import sanandreasp.mods.TurretMod3.entity.EntityDismantleStorage;
import sanandreasp.mods.TurretMod3.entity.EntityMobileBase;
import sanandreasp.mods.TurretMod3.entity.projectile.TurretProj_Arrow;
import sanandreasp.mods.TurretMod3.entity.projectile.TurretProj_Bullet;
import sanandreasp.mods.TurretMod3.entity.projectile.TurretProj_Explosive;
import sanandreasp.mods.TurretMod3.entity.projectile.TurretProj_Flame;
import sanandreasp.mods.TurretMod3.entity.projectile.TurretProj_Laser;
import sanandreasp.mods.TurretMod3.entity.projectile.TurretProj_Pebble;
import sanandreasp.mods.TurretMod3.entity.projectile.TurretProj_Plasma;
import sanandreasp.mods.TurretMod3.entity.projectile.TurretProj_Rocket;
import sanandreasp.mods.TurretMod3.entity.projectile.TurretProj_Seed;
import sanandreasp.mods.TurretMod3.entity.projectile.TurretProj_Shard;
import sanandreasp.mods.TurretMod3.entity.projectile.TurretProj_Snowball;
import sanandreasp.mods.TurretMod3.entity.projectile.TurretProjectile;
import sanandreasp.mods.TurretMod3.entity.turret.EntityTurret_T1Arrow;
import sanandreasp.mods.TurretMod3.entity.turret.EntityTurret_T1Shotgun;
import sanandreasp.mods.TurretMod3.entity.turret.EntityTurret_T2Minigun;
import sanandreasp.mods.TurretMod3.entity.turret.EntityTurret_T2Revolver;
import sanandreasp.mods.TurretMod3.entity.turret.EntityTurret_T3Flamethrower;
import sanandreasp.mods.TurretMod3.entity.turret.EntityTurret_T3Laser;
import sanandreasp.mods.TurretMod3.entity.turret.EntityTurret_T4FLAK;
import sanandreasp.mods.TurretMod3.entity.turret.EntityTurret_T4Sniper;
import sanandreasp.mods.TurretMod3.entity.turret.EntityTurret_T5Artillery;
import sanandreasp.mods.TurretMod3.entity.turret.EntityTurret_T5Railgun;
import sanandreasp.mods.TurretMod3.entity.turret.EntityTurret_TSCollector;
import sanandreasp.mods.TurretMod3.entity.turret.EntityTurret_TSForcefield;
import sanandreasp.mods.TurretMod3.entity.turret.EntityTurret_TSHealer;
import sanandreasp.mods.TurretMod3.entity.turret.EntityTurret_TSSnowball;
import sanandreasp.mods.TurretMod3.item.ItemIconCache;
import sanandreasp.mods.TurretMod3.item.ItemAmmunitions;
import sanandreasp.mods.TurretMod3.item.ItemArtilleryShells;
import sanandreasp.mods.TurretMod3.item.ItemFLAKRockets;
import sanandreasp.mods.TurretMod3.item.ItemGeneral;
import sanandreasp.mods.TurretMod3.item.ItemMobileBase;
import sanandreasp.mods.TurretMod3.item.ItemTMDisc;
import sanandreasp.mods.TurretMod3.item.ItemTurret;
import sanandreasp.mods.TurretMod3.item.ItemTurretInfo;
import sanandreasp.mods.TurretMod3.packet.PacketHandlerCommon;
import sanandreasp.mods.TurretMod3.registry.TurretInfo.TurretInfo;
import sanandreasp.mods.TurretMod3.registry.TurretInfo.TurretInfoT1Arrow;
import sanandreasp.mods.TurretMod3.registry.TurretInfo.TurretInfoT1Shotgun;
import sanandreasp.mods.TurretMod3.registry.TurretInfo.TurretInfoT2Minigun;
import sanandreasp.mods.TurretMod3.registry.TurretInfo.TurretInfoT2Revolver;
import sanandreasp.mods.TurretMod3.registry.TurretInfo.TurretInfoT3Flamethrower;
import sanandreasp.mods.TurretMod3.registry.TurretInfo.TurretInfoT3Laser;
import sanandreasp.mods.TurretMod3.registry.TurretInfo.TurretInfoT4FLAK;
import sanandreasp.mods.TurretMod3.registry.TurretInfo.TurretInfoT4Sniper;
import sanandreasp.mods.TurretMod3.registry.TurretInfo.TurretInfoT5Artillery;
import sanandreasp.mods.TurretMod3.registry.TurretInfo.TurretInfoT5Railgun;
import sanandreasp.mods.TurretMod3.registry.TurretInfo.TurretInfoTSCollector;
import sanandreasp.mods.TurretMod3.registry.TurretInfo.TurretInfoTSForcefield;
import sanandreasp.mods.TurretMod3.registry.TurretInfo.TurretInfoTSHealer;
import sanandreasp.mods.TurretMod3.registry.TurretInfo.TurretInfoTSSnowball;
import sanandreasp.mods.TurretMod3.registry.TurretUpgrades.TUpgChestGrabbing;
import sanandreasp.mods.TurretMod3.registry.TurretUpgrades.TUpgControl;
import sanandreasp.mods.TurretMod3.registry.TurretUpgrades.TUpgEconomy;
import sanandreasp.mods.TurretMod3.registry.TurretUpgrades.TUpgEnderHitting;
import sanandreasp.mods.TurretMod3.registry.TurretUpgrades.TUpgExpStorage;
import sanandreasp.mods.TurretMod3.registry.TurretUpgrades.TUpgExpStorageC;
import sanandreasp.mods.TurretMod3.registry.TurretUpgrades.TUpgExperience;
import sanandreasp.mods.TurretMod3.registry.TurretUpgrades.TUpgFireImmunity;
import sanandreasp.mods.TurretMod3.registry.TurretUpgrades.TUpgInfAmmo;
import sanandreasp.mods.TurretMod3.registry.TurretUpgrades.TUpgItemCollect;
import sanandreasp.mods.TurretMod3.registry.TurretUpgrades.TUpgPiercing;
import sanandreasp.mods.TurretMod3.registry.TurretUpgrades.TUpgPrecision;
import sanandreasp.mods.TurretMod3.registry.TurretUpgrades.TUpgPurify;
import sanandreasp.mods.TurretMod3.registry.TurretUpgrades.TUpgRangeIncr;
import sanandreasp.mods.TurretMod3.registry.TurretUpgrades.TUpgShieldMobPush;
import sanandreasp.mods.TurretMod3.registry.TurretUpgrades.TUpgShieldPointsIncr;
import sanandreasp.mods.TurretMod3.registry.TurretUpgrades.TUpgShieldRepairIncr;
import sanandreasp.mods.TurretMod3.registry.TurretUpgrades.TUpgShieldRngI;
import sanandreasp.mods.TurretMod3.registry.TurretUpgrades.TUpgShieldRngII;
import sanandreasp.mods.TurretMod3.registry.TurretUpgrades.TUpgSlowdownII;
import sanandreasp.mods.TurretMod3.registry.TurretUpgrades.TUpgStopMove;
import sanandreasp.mods.TurretMod3.registry.TurretUpgrades.TUpgTurretCollect;
import sanandreasp.mods.TurretMod3.registry.TurretUpgrades.TurretUpgrades;
import sanandreasp.mods.TurretMod3.tileentity.TileEntityLaptop;
import sanandreasp.mods.managers.SAP_ConfigManagerII;
import sanandreasp.mods.managers.SAP_LanguageManager;
import sanandreasp.mods.managers.SAP_UpdateManager;
import sanandreasp.mods.managers.SAP_ConfigManagerII.DataTypes;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.KeyBindingRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.PostInit;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.Mod.ServerStarting;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.NetworkMod.SidedPacketHandler;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.FMLRelauncher;
import cpw.mods.fml.relauncher.Side;

@Mod(modid=TM3ModRegistry.modID, name="Turret Mod 3", version="3.0.1", dependencies="after:sanandreasp.mods.managers.SAP_ManagerRegistry")
@NetworkMod(clientSideRequired=true, serverSideRequired=false,
	clientPacketHandlerSpec = @SidedPacketHandler(channels={"TurretMod3"}, packetHandler=PacketHandlerClient.class),
	serverPacketHandlerSpec = @SidedPacketHandler(channels={"TurretMod3"}, packetHandler=PacketHandlerCommon.class))
public class TM3ModRegistry {
	
	/** modID field for the @Mod annotation and other references (for example the FML logging system) */
	public static final String modID = "TurretMod3";

	/** The @SidedProxy field for the Mod */
	@SidedProxy(clientSide="sanandreasp.mods.TurretMod3.client.registry.ClientProxy", serverSide="sanandreasp.mods.TurretMod3.registry.CommonProxy")
	public static CommonProxy proxy;
	
	/** The @Instance field for the Mod */
	@Instance(TM3ModRegistry.modID)
	public static TM3ModRegistry instance;
	
	/** The field for the ManagerPackHelper instance. */
	public static ManagerPackHelper manHelper = new ManagerPackHelper();
	
	// Texture fields
	public static final String TEX_TURRETDIR	= "/mods/TurretMod3/textures/entities/turrets/";
	public static final String TEX_MOBILEBASE	= "/mods/TurretMod3/textures/entities/mobileBase.png";
	public static final String TEX_SHIELD		= "/mods/TurretMod3/textures/entities/shield.png";
	public static final String TEX_GUITCUDIR	= "/mods/TurretMod3/textures/guis/tcu_gui/";
	public static final String TEX_GUILAP		= "/mods/TurretMod3/textures/guis/laptop/";
	public static final String TEX_BULLET		= "/mods/TurretMod3/textures/entities/bullet.png";
	public static final String TEX_LASER		= "/mods/TurretMod3/textures/entities/laser.png";
	public static final String TEX_PLASMA		= "/mods/TurretMod3/textures/entities/plasma.png";
	public static final String TEX_ROCKET		= "/mods/TurretMod3/textures/entities/rocket.png";
	public static final String TEX_SHARD		= "/mods/TurretMod3/textures/entities/shard.png";
	public static final String TEX_GUIINFO		= "/mods/TurretMod3/textures/guis/turretinfo_gui/";
	public static final String TEX_GUIBUTTONS	= "/mods/TurretMod3/textures/guis/guis_buttons.png";
	public static final String TEX_TURRETCAM	= "/mods/TurretMod3/textures/guis/turretCam.png";
	public static final String TEX_HEALBEAM		= "/mods/TurretMod3/textures/entities/healBeam.png";
	public static final String TEX_WHITELAP		= "/mods/TurretMod3/textures/blocks/laptopWhite.png";
	public static final String TEX_BLACKLAP		= "/mods/TurretMod3/textures/blocks/laptopBlack.png";
	public static final String TEX_LAPGLOW		= "/mods/TurretMod3/textures/blocks/laptopGlow";
	
	// Block fields
	public static Block laptop;
	
	// Item fields
	public static Item turretItem;
	public static Item ammoItems;
	public static Item tcu;
	public static Item tInfoBook;
	public static Item mobileBase;
	public static Item iconCache;
	public static Item httm;
	public static Item rocket;
	public static Item artilleryBall;
	public static Item turretRec1;
	
	// Creative tab field
	public static CreativeTabs tabTurret;
	
	public static float labelRenderRange = 8F;
	
	public static boolean canCollectorGetXP = true;
	
	public static HashMap<String, Integer> blockIDs = Maps.newHashMap();
	public static HashMap<String, Integer> itemIDs = Maps.newHashMap();
	static {
		blockIDs.put("Turret Laptop", 2000);
		
		itemIDs.put("Turret Item", 12000);
		itemIDs.put("Ammo Items", 12001);
		itemIDs.put("Turret Control Unit", 12002);
		itemIDs.put("Turret Info Book", 12003);
		itemIDs.put("Mobile Base", 12004);
		itemIDs.put("Icon-Cache Item", 12005);
		itemIDs.put("HT Turret Module", 12006);
		itemIDs.put("FLAK Rockets", 12007);
		itemIDs.put("Artillery Shell", 12008);
		itemIDs.put("Turret Record 1", 12009);
		itemIDs.put("Turret Record 2", 12010);
	}
	
	@PreInit
	public void preInit(FMLPreInitializationEvent evt) {
	// check if Manager Pack is installed
		manHelper.checkManPack(evt.getModMetadata().name);
	
	// If Manager Pack is installed, then...
		if(manHelper.loading) {
			
		// init managers
			String[] ver = evt.getModMetadata().version.split("\\.");
			manHelper.initMan(
					new SAP_ConfigManagerII("Turret Mod 3", "TurretMod3.txt", "/sanandreasp/"),
					new SAP_LanguageManager("/sanandreasp/TurretMod3", "1.0", "Turret Mod 3"),
					new SAP_UpdateManager("Turret Mod 3", Integer.parseInt(ver[0]), Integer.parseInt(ver[1]), Integer.parseInt(ver[2]), "https://dl.dropboxusercontent.com/u/56920617/TurretMod_latest.txt", "http://www.minecraftforum.net/topic/562836-")
			);
			
		// init Creative Tab
			tabTurret = new CreativeTabTurrets("turretTab");
			
		// create helper instance for the Config Manager (CFGMan)
			SAP_ConfigManagerII cfgman = manHelper.getCfgMan();
			
		// add Config Groups to CFGMan
			cfgman.addGroup("Other Settings (Doubles)", DataTypes.DOUBLE);
			cfgman.addGroup("Other Settings (Booleans)", DataTypes.BOOL);
			
		// set pre-defined config values and value names to CFGMan
			cfgman.addStaticItemIDs(itemIDs.keySet().toArray(new String[itemIDs.size()]), itemIDs.values().toArray(new Integer[itemIDs.size()]));
			cfgman.addStaticBlockIDs(blockIDs.keySet().toArray(new String[blockIDs.size()]), blockIDs.values().toArray(new Integer[blockIDs.size()]));
			cfgman.addAchievementIDs(AchievementPageTM.achieveNames, AchievementPageTM.achievementIDs);
			cfgman.addProperty("Label Render Range", "Other Settings (Doubles)", (double) labelRenderRange);
			cfgman.addProperty("can Collector collect XP orbs", "Other Settings (Booleans)", canCollectorGetXP);
			
		// load Config
			cfgman.loadConfig();
			
			for(String propName : itemIDs.keySet()) {
				itemIDs.put(propName, cfgman.getItemID(propName));
			}
			for(String propName : blockIDs.keySet()) {
				blockIDs.put(propName, cfgman.getBlockID(propName));
			}
			
			AchievementPageTM.achievementIDs = cfgman.getAchievementIDs(AchievementPageTM.achieveNames);
			
			double d = cfgman.getProperty("Label Render Range", "Other Settings (Doubles)");
			labelRenderRange = (float) d;			
			boolean b = cfgman.getProperty("can Collector collect XP orbs", "Other Settings (Booleans)");
			canCollectorGetXP = b;
			
		// register Forge Events and Handlers
			if(FMLCommonHandler.instance().getSide().isClient()) {
				MinecraftForge.EVENT_BUS.register(new SoundRegistry());
				TickRegistry.registerTickHandler(new TickHandlerClientRnd(), Side.CLIENT);
				KeyBindingRegistry.registerKeyBinding(new KeyBindHandler());
			}
			
			MinecraftForge.EVENT_BUS.register(new ServerEvents());
			TickRegistry.registerScheduledTickHandler(new SchedTickHandlerWorld(), Side.SERVER);
			
		// add Icon to the IconCache-Item
			ItemIconCache.iconList.put(0, "TurretMod3:ach_piercing");
			ItemIconCache.iconList.put(1, "TurretMod3:redFlame");
			ItemIconCache.iconList.put(2, "TurretMod3:blueFlame");

		// register Ammo-Item types
			ItemAmmunitions.addAmmoItem(0, "arrowPack", "Arrow Pack");
			ItemAmmunitions.addAmmoItem(1, "pebbles", "Pebbles");
			ItemAmmunitions.addAmmoItem(2, "pebble", "Pebble");
			ItemAmmunitions.addAmmoItem(3, "bullets", "Bullets");
			ItemAmmunitions.addAmmoItem(4, "bulletPack", "Bullet Pack");
			ItemAmmunitions.addAmmoItem(5, "tank", "FT Fuel");
			ItemAmmunitions.addAmmoItem(6, "tankPack", "FT Fuel Pack");
			ItemAmmunitions.addAmmoItem(7, "gMelonPack", "Glistering Melon Bundle");
			ItemAmmunitions.addAmmoItem(8, "enderPearlPack", "Ender Pearl Bundle");
				
		// initialize items
			initItems();
			
		// register Achievement Page and initialize Achievements
			AchievementPageTM.initAchievementPage();
			
		// register optional Main Menu, if MainMenuAPI is installed
			registerMainMenu();
	// ... else set the Enabled-State to false
		} else {
			Loader.instance().getIndexedModList().get(TM3ModRegistry.modID).setEnabledState(false);
		}
	}
	
	/** registers the dungeon loot **/
	private void registerDungeonLoot() {
		ChestGenHooks dungeon = ChestGenHooks.getInfo(ChestGenHooks.DUNGEON_CHEST);
		ChestGenHooks pyramid = ChestGenHooks.getInfo(ChestGenHooks.PYRAMID_DESERT_CHEST);
		ChestGenHooks jtemple = ChestGenHooks.getInfo(ChestGenHooks.PYRAMID_JUNGLE_CHEST);
		
		WeightedRandomChestContent disc1WRCC = new WeightedRandomChestContent(new ItemStack(turretRec1), 1, 1, 5);
		WeightedRandomChestContent httmWRCC = new WeightedRandomChestContent(new ItemStack(httm), 1, 1, 5);
		
		dungeon.addItem(disc1WRCC);
		pyramid.addItem(disc1WRCC);
		pyramid.addItem(httmWRCC);
		jtemple.addItem(disc1WRCC);
		jtemple.addItem(httmWRCC);
	}
	
	/** registers the TurretMod Main Menu */
	private void registerMainMenu() {
		try {
			if(FMLCommonHandler.instance().getSide().isClient())
				net.aetherteam.mainmenu_api.MainMenuAPI.registerMenu("TurretMod3Menu", MainMenuTM3.class);
		} catch (Exception e) {
			FMLLog.log(TM3ModRegistry.modID, Level.INFO, "kingbdogz' Main Menu API not found. Skipping Main Menu registration!");
		} catch (NoClassDefFoundError error) {
			FMLLog.log(TM3ModRegistry.modID, Level.INFO, "kingbdogz' Main Menu API not found. Skipping Main Menu registration!");
		}
	}
	
	
	
	/** initializes the items **/
	public void initItems() {
		laptop			= new BlockLaptop(blockIDs.get("Turret Laptop"), Material.circuits)
							.setLightOpacity(0)
							.setHardness(1F)
							.setUnlocalizedName("tm3.laptop")
							.setCreativeTab(tabTurret);
		
		turretItem		= new ItemTurret(itemIDs.get("Turret Item") - 256)
							.setUnlocalizedName("tm3.turretItem")
							.setCreativeTab(tabTurret);
		ammoItems		= (ItemAmmunitions) new ItemAmmunitions(itemIDs.get("Ammo Items") - 256)
							.setUnlocalizedName("tm3.arrowPack")
							.setCreativeTab(tabTurret);
		tcu				= new ItemGeneral(itemIDs.get("Turret Control Unit") - 256, "TurretMod3:tcu")
							.setUnlocalizedName("tm3.turretControlU")
							.setCreativeTab(tabTurret);
		tInfoBook		= new ItemTurretInfo(itemIDs.get("Turret Info Book") - 256)
							.setUnlocalizedName("tm3.tInfoBook")
							.setCreativeTab(tabTurret);
		mobileBase		= new ItemMobileBase(itemIDs.get("Mobile Base") - 256)
							.setUnlocalizedName("tm3.mobileBase")
							.setCreativeTab(tabTurret);
		iconCache		= new ItemIconCache(itemIDs.get("Icon-Cache Item") - 256)
							.setUnlocalizedName("tm3.achieves");
		httm			= new ItemGeneral(itemIDs.get("HT Turret Module") - 256, "TurretMod3:httm")
							.setUnlocalizedName("tm3.htTurretMod")
							.setCreativeTab(tabTurret);
		rocket			= new ItemFLAKRockets(itemIDs.get("FLAK Rockets") - 256)
							.setUnlocalizedName("tm3.flakRockets")
							.setCreativeTab(tabTurret);
		artilleryBall	= new ItemArtilleryShells(itemIDs.get("Artillery Shell") - 256)
							.setUnlocalizedName("tm3.artilleryBalls")
							.setCreativeTab(tabTurret);
		turretRec1		= new ItemTMDisc(itemIDs.get("Turret Record 1") - 256, "Tidal Force", "Kubbi")
							.setUnlocalizedName("tm3.turretRec1")
							.setCreativeTab(tabTurret);
		
		this.registerItems(turretItem, ammoItems, tcu, tInfoBook, mobileBase, iconCache, httm, rocket, artilleryBall, turretRec1);
//		this.registerBlocks();
		
		GameRegistry.registerBlock(laptop, ItemBlockLaptop.class, "tm3.laptopBLK");

		GameRegistry.registerTileEntity(TileEntityLaptop.class, "tm3.laptopTE");
	}

	@Init
	public void init(FMLInitializationEvent evt) {
		if(!manHelper.loading) return;
		
	// register Turret-Informations
		TurretInfo.addTurretInfo(EntityTurret_T1Arrow.class, new TurretInfoT1Arrow());
		TurretInfo.addTurretInfo(EntityTurret_T1Shotgun.class, new TurretInfoT1Shotgun());
		TurretInfo.addTurretInfo(EntityTurret_T2Minigun.class, new TurretInfoT2Minigun());
		TurretInfo.addTurretInfo(EntityTurret_T2Revolver.class, new TurretInfoT2Revolver());
		TurretInfo.addTurretInfo(EntityTurret_T3Laser.class, new TurretInfoT3Laser());
		TurretInfo.addTurretInfo(EntityTurret_T3Flamethrower.class, new TurretInfoT3Flamethrower());
		TurretInfo.addTurretInfo(EntityTurret_T4Sniper.class, new TurretInfoT4Sniper());
		TurretInfo.addTurretInfo(EntityTurret_T4FLAK.class, new TurretInfoT4FLAK());
		TurretInfo.addTurretInfo(EntityTurret_T5Railgun.class, new TurretInfoT5Railgun());
		TurretInfo.addTurretInfo(EntityTurret_T5Artillery.class, new TurretInfoT5Artillery());
		TurretInfo.addTurretInfo(EntityTurret_TSSnowball.class, new TurretInfoTSSnowball());
		TurretInfo.addTurretInfo(EntityTurret_TSCollector.class, new TurretInfoTSCollector());
		TurretInfo.addTurretInfo(EntityTurret_TSForcefield.class, new TurretInfoTSForcefield());
		TurretInfo.addTurretInfo(EntityTurret_TSHealer.class, new TurretInfoTSHealer());

	// register Turret-Upgrades
		TurretUpgrades.addUpgrade(new TUpgControl());
		TurretUpgrades.addUpgrade(new TUpgChestGrabbing());
		TurretUpgrades.addUpgrade(new TUpgInfAmmo());
		TurretUpgrades.addUpgrade(new TUpgExperience());
		TurretUpgrades.addUpgrade(new TUpgExpStorage());
		TurretUpgrades.addUpgrade(new TUpgFireImmunity());
		TurretUpgrades.addUpgrade(new TUpgPiercing());
		TurretUpgrades.addUpgrade(new TUpgEconomy());
		TurretUpgrades.addUpgrade(new TUpgPurify());
		TurretUpgrades.addUpgrade(new TUpgEnderHitting());
		TurretUpgrades.addUpgrade(new TUpgPrecision());
		TurretUpgrades.addUpgrade(new TUpgSlowdownII());
		TurretUpgrades.addUpgrade(new TUpgStopMove());
		TurretUpgrades.addUpgrade(new TUpgExpStorageC());
		TurretUpgrades.addUpgrade(new TUpgTurretCollect());
		TurretUpgrades.addUpgrade(new TUpgItemCollect());
		TurretUpgrades.addUpgrade(new TUpgShieldRngI());
		TurretUpgrades.addUpgrade(new TUpgShieldRngII());
		TurretUpgrades.addUpgrade(new TUpgShieldPointsIncr());
		TurretUpgrades.addUpgrade(new TUpgShieldRepairIncr());
		TurretUpgrades.addUpgrade(new TUpgShieldMobPush());
		TurretUpgrades.addUpgrade(new TUpgRangeIncr());
		
	// register mod entities
		int modEntityID = 0;
		
		EntityRegistry.registerModEntity(EntityTurret_T1Arrow.class, "Turret_T1A", modEntityID++, this, 128, 1, true);
		EntityRegistry.registerModEntity(EntityTurret_T1Shotgun.class, "Turret_T1S", modEntityID++, this, 128, 1, true);
		EntityRegistry.registerModEntity(EntityTurret_T2Minigun.class, "Turret_T2M", modEntityID++, this, 128, 1, true);
		EntityRegistry.registerModEntity(EntityTurret_T2Revolver.class, "Turret_T2R", modEntityID++, this, 128, 1, true);
		EntityRegistry.registerModEntity(EntityTurret_T3Laser.class, "Turret_T3L", modEntityID++, this, 128, 1, true);
		EntityRegistry.registerModEntity(EntityTurret_T3Flamethrower.class, "Turret_T3F", modEntityID++, this, 128, 1, true);
		EntityRegistry.registerModEntity(EntityTurret_T4Sniper.class, "Turret_T4S", modEntityID++, this, 128, 1, true);
		EntityRegistry.registerModEntity(EntityTurret_T4FLAK.class, "Turret_T4F", modEntityID++, this, 128, 1, true);
		EntityRegistry.registerModEntity(EntityTurret_T5Railgun.class, "Turret_T5R", modEntityID++, this, 128, 1, true);
		EntityRegistry.registerModEntity(EntityTurret_T5Artillery.class, "Turret_T5A", modEntityID++, this, 128, 1, true);
		EntityRegistry.registerModEntity(EntityTurret_TSSnowball.class, "Turret_T5S", modEntityID++, this, 128, 1, true);
		EntityRegistry.registerModEntity(EntityTurret_TSCollector.class, "Turret_TSC", modEntityID++, this, 128, 1, true);
		EntityRegistry.registerModEntity(EntityTurret_TSForcefield.class, "Turret_TSF", modEntityID++, this, 128, 1, true);
		EntityRegistry.registerModEntity(EntityTurret_TSHealer.class, "Turret_TSH", modEntityID++, this, 128, 1, true);
		
		EntityRegistry.registerModEntity(TurretProj_Arrow.class, "TurretProj_Arrow", modEntityID++, this, 128, 1, true);
		EntityRegistry.registerModEntity(TurretProj_Pebble.class, "TurretProj_Pebble", modEntityID++, this, 128, 1, true);
		EntityRegistry.registerModEntity(TurretProj_Seed.class, "TurretProj_Seed", modEntityID++, this, 128, 1, true);
		EntityRegistry.registerModEntity(TurretProj_Bullet.class, "TurretProj_Bullet", modEntityID++, this, 128, 1, true);
		EntityRegistry.registerModEntity(TurretProj_Laser.class, "TurretProj_Laser", modEntityID++, this, 128, 1, true);
		EntityRegistry.registerModEntity(TurretProj_Flame.class, "TurretProj_Flame", modEntityID++, this, 128, 1, true);
		EntityRegistry.registerModEntity(TurretProj_Plasma.class, "TurretProj_Plasma", modEntityID++, this, 128, 1, true);
		EntityRegistry.registerModEntity(TurretProj_Rocket.class, "TurretProj_Rocket", modEntityID++, this, 128, 1, true);
		EntityRegistry.registerModEntity(TurretProj_Shard.class, "TurretProj_Shard", modEntityID++, this, 128, 1, true);
		EntityRegistry.registerModEntity(TurretProj_Explosive.class, "TurretProj_Explose", modEntityID++, this, 128, 1, true);
		EntityRegistry.registerModEntity(TurretProj_Snowball.class, "TurretProj_Snowball", modEntityID++, this, 128, 1, true);
		
		EntityRegistry.registerModEntity(EntityMobileBase.class, "MobileBase", modEntityID++, this, 128, 5, true);
		EntityRegistry.registerModEntity(EntityDismantleStorage.class, "DismStorage", modEntityID++, this, 128, 5, false);
		
	// register Handlers
		NetworkRegistry.instance().registerGuiHandler(this, new GuiHandler());
		
	// initialize crafting recipes
		CraftingRegistry.initCraftings();
	
	// register rendering informations
		proxy.registerRenderInformation();
	}
	
	@PostInit
	public void postInit(FMLPostInitializationEvent evt) {
		if(!manHelper.loading) return;
		
	// create helper instance for the Language Manager (LangMan)
		SAP_LanguageManager langman = manHelper.getLangMan();
		
	// register Localizations to LangMan and define default Localizations
		AchievementPageTM.initTexts(langman);
		
		TurretTargetRegistry.initTargetReg();
		
		DefLangs.setupLang(langman);
		
		for(int i = 0; i < TurretInfo.getTurretCount(); i++) {
			TurretInfo tinf = TurretInfo.getTurretInfo(TurretInfo.getTurretClass(i));
			langman.addLangProp(tinf.getTurretItem().copy(), langman.getTranslatedEn(tinf.getTurretName()));
		}
		
		langman.addLangProp("entity.TurretMod3.Turret_T1A.name", langman.getTranslatedEn(TurretInfo.getTurretInfo(EntityTurret_T1Arrow.class).getTurretName()));
		langman.addLangProp("entity.TurretMod3.Turret_T1S.name", langman.getTranslatedEn(TurretInfo.getTurretInfo(EntityTurret_T1Shotgun.class).getTurretName()));
		langman.addLangProp("entity.TurretMod3.Turret_T2M.name", langman.getTranslatedEn(TurretInfo.getTurretInfo(EntityTurret_T2Minigun.class).getTurretName()));
		langman.addLangProp("entity.TurretMod3.Turret_T2R.name", langman.getTranslatedEn(TurretInfo.getTurretInfo(EntityTurret_T2Revolver.class).getTurretName()));
		langman.addLangProp("entity.TurretMod3.Turret_T3L.name", langman.getTranslatedEn(TurretInfo.getTurretInfo(EntityTurret_T3Laser.class).getTurretName()));
		langman.addLangProp("entity.TurretMod3.Turret_T3F.name", langman.getTranslatedEn(TurretInfo.getTurretInfo(EntityTurret_T3Flamethrower.class).getTurretName()));
		langman.addLangProp("entity.TurretMod3.Turret_T4S.name", langman.getTranslatedEn(TurretInfo.getTurretInfo(EntityTurret_T4Sniper.class).getTurretName()));
		langman.addLangProp("entity.TurretMod3.Turret_T4F.name", langman.getTranslatedEn(TurretInfo.getTurretInfo(EntityTurret_T4FLAK.class).getTurretName()));
		langman.addLangProp("entity.TurretMod3.Turret_T5R.name", langman.getTranslatedEn(TurretInfo.getTurretInfo(EntityTurret_T5Railgun.class).getTurretName()));
		langman.addLangProp("entity.TurretMod3.Turret_T5A.name", langman.getTranslatedEn(TurretInfo.getTurretInfo(EntityTurret_T5Artillery.class).getTurretName()));
		
		langman.addLangProp(laptop, "Turret Laptop");
		
		langman.addLangProp(new ItemStack(ammoItems, 1, -1), "Ammo Items");
		for(int id : ItemAmmunitions.dmgLangMapping.keySet())
			langman.addLangProp(new ItemStack(ammoItems, 1, id), ItemAmmunitions.dmgNameMapping.get(id));
		
		langman.addLangProp(mobileBase, "Mobile Base");
		langman.addLangProp(tcu, "Turret Control Unit");
		langman.addLangProp(tInfoBook, "Turret Info Book");
		langman.addLangProp(httm, "High Tech Turret Module");
		langman.addLangProp(turretRec1, "Turret Music Disc");
		langman.addLangProp("itemGroup.turretTab", "Turret Mod");
		
		String[] itemNames = new String[] {
			"TB50 Ballistic Rocket",
			"TB75 Ballistic Rocket",
			"TB100 Ballistic Rocket",
			"TG50 Guided Rocket",
			"TG75 Guided Rocket",
			"TG100 Guided Rocket"
		};
		for(int i = 0; i < 6; i++) {
			langman.addLangProp(new ItemStack(rocket, 1, i), itemNames[i]);
		}
		itemNames = new String[] {
				"Artillery Shell",
				"Big Artillery Shell",
				"Napalm Artillery Shell",
				"Big Napalm Artillery Shell",
				"Frag Artillery Shell",
		};
		for(int i = 0; i < 5; i++) {
			langman.addLangProp(new ItemStack(artilleryBall, 1, i), itemNames[i]);
			langman.addLangProp("turretmod3.turret.amtp"+i+"t5a", itemNames[i]);
		}
		
	// load Localizations from existing language files
		langman.loadLangs();
		
	// register Achievement-Icon localization
		LanguageRegistry.addName(iconCache, "Achievement-Icons");
	}
	
	@ServerStarting
	public void startingServer(FMLServerStartingEvent evt) {
		if(!manHelper.loading) return;
	
	// register commands
		evt.registerServerCommand(new CommandTurretMod());
	}
	
	/** registers the Items **/
	private void registerItems(Item... items) {
		for(int i = 0; i < items.length; i++) GameRegistry.registerItem(items[i], "tm3.item_"+i);
	}
	
	/** registers the Blocks **/
	private void registerBlocks(Block... blocks) {
		for(int i = 0; i < blocks.length; i++) GameRegistry.registerBlock(blocks[i], "tm3.block_"+i);
	}
}