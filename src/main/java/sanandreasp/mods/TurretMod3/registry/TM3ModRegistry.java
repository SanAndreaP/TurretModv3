package sanandreasp.mods.turretmod3.registry;

import java.util.logging.Level;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlockWithMetadata;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraftforge.common.ChestGenHooks;
import net.minecraftforge.common.config.Configuration;
import sanandreasp.mods.turretmod3.CreativeTabTurrets;
import sanandreasp.mods.turretmod3.block.BlockLaptop;
import sanandreasp.mods.turretmod3.command.CommandTurretMod;
import sanandreasp.mods.turretmod3.entity.EntityDismantleStorage;
import sanandreasp.mods.turretmod3.entity.EntityMobileBase;
import sanandreasp.mods.turretmod3.entity.projectile.TurretProj_Arrow;
import sanandreasp.mods.turretmod3.entity.projectile.TurretProj_Bullet;
import sanandreasp.mods.turretmod3.entity.projectile.TurretProj_Explosive;
import sanandreasp.mods.turretmod3.entity.projectile.TurretProj_Flame;
import sanandreasp.mods.turretmod3.entity.projectile.TurretProj_Laser;
import sanandreasp.mods.turretmod3.entity.projectile.TurretProj_Pebble;
import sanandreasp.mods.turretmod3.entity.projectile.TurretProj_Plasma;
import sanandreasp.mods.turretmod3.entity.projectile.TurretProj_Rocket;
import sanandreasp.mods.turretmod3.entity.projectile.TurretProj_Seed;
import sanandreasp.mods.turretmod3.entity.projectile.TurretProj_Shard;
import sanandreasp.mods.turretmod3.entity.projectile.TurretProj_Snowball;
import sanandreasp.mods.turretmod3.entity.turret.EntityTurret_T1Arrow;
import sanandreasp.mods.turretmod3.entity.turret.EntityTurret_T1Shotgun;
import sanandreasp.mods.turretmod3.entity.turret.EntityTurret_T2Minigun;
import sanandreasp.mods.turretmod3.entity.turret.EntityTurret_T2Revolver;
import sanandreasp.mods.turretmod3.entity.turret.EntityTurret_T3Flamethrower;
import sanandreasp.mods.turretmod3.entity.turret.EntityTurret_T3Laser;
import sanandreasp.mods.turretmod3.entity.turret.EntityTurret_T4FLAK;
import sanandreasp.mods.turretmod3.entity.turret.EntityTurret_T4Sniper;
import sanandreasp.mods.turretmod3.entity.turret.EntityTurret_T5Artillery;
import sanandreasp.mods.turretmod3.entity.turret.EntityTurret_T5Railgun;
import sanandreasp.mods.turretmod3.entity.turret.EntityTurret_TSCollector;
import sanandreasp.mods.turretmod3.entity.turret.EntityTurret_TSForcefield;
import sanandreasp.mods.turretmod3.entity.turret.EntityTurret_TSHealer;
import sanandreasp.mods.turretmod3.entity.turret.EntityTurret_TSSnowball;
import sanandreasp.mods.turretmod3.item.ItemAmmunitions;
import sanandreasp.mods.turretmod3.item.ItemArtilleryShells;
import sanandreasp.mods.turretmod3.item.ItemFLAKRockets;
import sanandreasp.mods.turretmod3.item.ItemMobileBase;
import sanandreasp.mods.turretmod3.item.ItemTMDisc;
import sanandreasp.mods.turretmod3.item.ItemTurret;
import sanandreasp.mods.turretmod3.item.ItemTurretInfo;
import sanandreasp.mods.turretmod3.registry.TurretInfo.TurretInfo;
import sanandreasp.mods.turretmod3.registry.TurretInfo.TurretInfoT1Arrow;
import sanandreasp.mods.turretmod3.registry.TurretInfo.TurretInfoT1Shotgun;
import sanandreasp.mods.turretmod3.registry.TurretInfo.TurretInfoT2Minigun;
import sanandreasp.mods.turretmod3.registry.TurretInfo.TurretInfoT2Revolver;
import sanandreasp.mods.turretmod3.registry.TurretInfo.TurretInfoT3Flamethrower;
import sanandreasp.mods.turretmod3.registry.TurretInfo.TurretInfoT3Laser;
import sanandreasp.mods.turretmod3.registry.TurretInfo.TurretInfoT4FLAK;
import sanandreasp.mods.turretmod3.registry.TurretInfo.TurretInfoT4Sniper;
import sanandreasp.mods.turretmod3.registry.TurretInfo.TurretInfoT5Artillery;
import sanandreasp.mods.turretmod3.registry.TurretInfo.TurretInfoT5Railgun;
import sanandreasp.mods.turretmod3.registry.TurretInfo.TurretInfoTSCollector;
import sanandreasp.mods.turretmod3.registry.TurretInfo.TurretInfoTSForcefield;
import sanandreasp.mods.turretmod3.registry.TurretInfo.TurretInfoTSHealer;
import sanandreasp.mods.turretmod3.registry.TurretInfo.TurretInfoTSSnowball;
import sanandreasp.mods.turretmod3.registry.TurretUpgrades.TUpgChestGrabbing;
import sanandreasp.mods.turretmod3.registry.TurretUpgrades.TUpgControl;
import sanandreasp.mods.turretmod3.registry.TurretUpgrades.TUpgEconomy;
import sanandreasp.mods.turretmod3.registry.TurretUpgrades.TUpgEnderHitting;
import sanandreasp.mods.turretmod3.registry.TurretUpgrades.TUpgExpStorage;
import sanandreasp.mods.turretmod3.registry.TurretUpgrades.TUpgExpStorageC;
import sanandreasp.mods.turretmod3.registry.TurretUpgrades.TUpgExperience;
import sanandreasp.mods.turretmod3.registry.TurretUpgrades.TUpgFireImmunity;
import sanandreasp.mods.turretmod3.registry.TurretUpgrades.TUpgInfAmmo;
import sanandreasp.mods.turretmod3.registry.TurretUpgrades.TUpgItemCollect;
import sanandreasp.mods.turretmod3.registry.TurretUpgrades.TUpgPiercing;
import sanandreasp.mods.turretmod3.registry.TurretUpgrades.TUpgPrecision;
import sanandreasp.mods.turretmod3.registry.TurretUpgrades.TUpgPurify;
import sanandreasp.mods.turretmod3.registry.TurretUpgrades.TUpgRangeIncr;
import sanandreasp.mods.turretmod3.registry.TurretUpgrades.TUpgShieldMobPush;
import sanandreasp.mods.turretmod3.registry.TurretUpgrades.TUpgShieldPointsIncr;
import sanandreasp.mods.turretmod3.registry.TurretUpgrades.TUpgShieldRepairIncr;
import sanandreasp.mods.turretmod3.registry.TurretUpgrades.TUpgShieldRngI;
import sanandreasp.mods.turretmod3.registry.TurretUpgrades.TUpgShieldRngII;
import sanandreasp.mods.turretmod3.registry.TurretUpgrades.TUpgSlowdownII;
import sanandreasp.mods.turretmod3.registry.TurretUpgrades.TUpgStopMove;
import sanandreasp.mods.turretmod3.registry.TurretUpgrades.TUpgTurretCollect;
import sanandreasp.mods.turretmod3.registry.TurretUpgrades.TurretUpgrades;
import sanandreasp.mods.turretmod3.tileentity.TileEntityLaptop;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(modid=TM3ModRegistry.modID, name="Turret Mod 3", version="3.0.1")
public class TM3ModRegistry {
	
	/** modID field for the @Mod annotation and other references (for example the FML logging system) */
	public static final String modID = "TurretMod3";

	/** The @SidedProxy field for the Mod */
	@SidedProxy(clientSide="sanandreasp.mods.turretmod3.client.registry.ClientProxy", serverSide="sanandreasp.mods.turretmod3.registry.CommonProxy")
	public static CommonProxy proxy;
	
	/** The @Instance field for the Mod */
	@Instance(TM3ModRegistry.modID)
	public static TM3ModRegistry instance;
	
	// Texture fields
	public static final String TEX_TURRETDIR	= "turretmod3:textures/entities/turrets/";
	public static final String TEX_MOBILEBASE	= "turretmod3:textures/entities/mobileBase.png";
	public static final String TEX_GUITCUDIR	= "turretmod3:textures/guis/tcu_gui/";
	public static final String TEX_GUILAP		= "turretmod3:textures/guis/laptop/";
	public static final String TEX_GUIINFO		= "turretmod3:textures/guis/turretinfo_gui/";
	public static final ResourceLocation TEX_GUIBUTTONS	= new ResourceLocation("turretmod3:textures/guis/guis_buttons.png");
	public static final ResourceLocation TEX_TURRETCAM	= new ResourceLocation("turretmod3:textures/guis/turretCam.png");
	public static final ResourceLocation TEX_WHITELAP		= new ResourceLocation("turretmod3:textures/blocks/laptopWhite.png");
	public static final ResourceLocation TEX_BLACKLAP		= new ResourceLocation("turretmod3:textures/blocks/laptopBlack.png");
	public static final String TEX_LAPGLOW		= "turretmod3:textures/blocks/laptopGlow";
	
	// Block fields
	public static Block laptop;
	
	// Item fields
	public static Item turretItem;
	public static Item ammoItems;
	public static Item tcu;
	public static Item tInfoBook;
	public static Item mobileBase;
	public static Item httm;
	public static Item rocket;
	public static Item artilleryBall;
	public static Item turretRec1;
	
	// Creative tab field
	public static CreativeTabs tabTurret;
	
	public static float labelRenderRange = 8F;
	
	public static boolean canCollectorGetXP = true;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent evt) {
			
		// init Creative Tab
        tabTurret = new CreativeTabTurrets("turretTab");
			
		// create helper instance for the Config Manager (CFGMan)
        Configuration cfgman = new Configuration(evt.getSuggestedConfigurationFile());
		// load Config
        cfgman.load();
			
        AchievementPageTM.achievementIDs = cfgman.getInt(AchievementPageTM.achieveNames);

        labelRenderRange = cfgman.getFloat("Label Render Range", "Client side", labelRenderRange, 0, 500, "The range in blocks at which label is going to render");
        canCollectorGetXP = cfgman.getBoolean("Can Collector collect XP orbs", "Server side", canCollectorGetXP, "False to disable xp collection");
			
		// register Forge Events and Handlers
        proxy.registerHandlers();

		// register Ammo-Item types
        ItemAmmunitions.addAmmoItem(0, "arrowPack");
        ItemAmmunitions.addAmmoItem(1, "pebbles");
        ItemAmmunitions.addAmmoItem(2, "pebble");
        ItemAmmunitions.addAmmoItem(3, "bullets");
        ItemAmmunitions.addAmmoItem(4, "bulletPack");
        ItemAmmunitions.addAmmoItem(5, "tank");
        ItemAmmunitions.addAmmoItem(6, "tankPack");
        ItemAmmunitions.addAmmoItem(7, "gMelonPack");
        ItemAmmunitions.addAmmoItem(8, "enderPearlPack");
				
		// initialize items
        initItems();
			
		// register Achievement Page and initialize Achievements
        AchievementPageTM.initAchievementPage();
			
		// register optional Main Menu, if MainMenuAPI is installed
        registerMainMenu();
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

	}
	
	/** initializes the items **/
	public void initItems() {
		laptop			= new BlockLaptop(Material.circuits)
							.setLightOpacity(0)
							.setHardness(1F)
							.setBlockName("tm3.laptop")
                            .setBlockTextureName("turretmod3:laptop")
							.setCreativeTab(tabTurret);
		
		turretItem		= new ItemTurret()
							.setUnlocalizedName("tm3.turretItem")
							.setCreativeTab(tabTurret);
		ammoItems		= new ItemAmmunitions()
							.setUnlocalizedName("tm3.arrowPack")
							.setCreativeTab(tabTurret);
		tcu				= new Item().setTextureName("TurretMod3:tcu")
							.setUnlocalizedName("tm3.turretControlU")
							.setCreativeTab(tabTurret);
		tInfoBook		= new ItemTurretInfo()
							.setUnlocalizedName("tm3.tInfoBook")
							.setCreativeTab(tabTurret);
		mobileBase		= new ItemMobileBase().setTextureName("TurretMod3:mobileBase")
							.setUnlocalizedName("tm3.mobileBase")
							.setCreativeTab(tabTurret);
		httm			= new Item().setTextureName("TurretMod3:httm")
							.setUnlocalizedName("tm3.htTurretMod")
							.setCreativeTab(tabTurret);
		rocket			= new ItemFLAKRockets()
							.setUnlocalizedName("tm3.flakRockets")
							.setCreativeTab(tabTurret);
		artilleryBall	= new ItemArtilleryShells()
							.setUnlocalizedName("tm3.artilleryBalls")
							.setCreativeTab(tabTurret);
		turretRec1		= new ItemTMDisc("TidalForce", "Kubbi")
							.setUnlocalizedName("tm3.turretRec1")
							.setCreativeTab(tabTurret);
		
		this.registerItems(turretItem, ammoItems, tcu, tInfoBook, mobileBase, httm, rocket, artilleryBall, turretRec1);
		
		GameRegistry.registerBlock(laptop, ItemBlockWithMetadata.class, "tm3.laptopBLK");

		GameRegistry.registerTileEntity(TileEntityLaptop.class, "tm3.laptopTE");
	}

	@EventHandler
	public void init(FMLInitializationEvent evt) {
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
		NetworkRegistry.INSTANCE.registerGuiHandler(this, new GuiHandler());
		
	    // initialize crafting recipes
		CraftingRegistry.initCraftings();
	
	    // register rendering informations
		proxy.registerRenderInformation();
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent evt) {
		TurretTargetRegistry.initTargetReg();
	}
	
	@EventHandler
	public void startingServer(FMLServerStartingEvent evt) {
	    // register commands
		evt.registerServerCommand(new CommandTurretMod());
	}
	
	/** registers the Items **/
	private void registerItems(Item... items) {
		for (int i = 0; i < items.length; i++) GameRegistry.registerItem(items[i], "tm3.item_"+i);
	}
}