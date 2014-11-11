package sanandreasp.mods.TurretMod3.client.registry;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelChest;
import net.minecraft.client.renderer.entity.RenderArrow;
import net.minecraft.client.renderer.entity.RenderFireball;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.MinecraftForgeClient;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import net.minecraftforge.common.MinecraftForge;
import sanandreasp.mods.TurretMod3.client.model.Model_MobileBase;
import sanandreasp.mods.TurretMod3.client.model.turret.Model_T1Arrow;
import sanandreasp.mods.TurretMod3.client.model.turret.Model_T1Shotgun;
import sanandreasp.mods.TurretMod3.client.model.turret.Model_T2Minigun;
import sanandreasp.mods.TurretMod3.client.model.turret.Model_T2Revolver;
import sanandreasp.mods.TurretMod3.client.model.turret.Model_T3Flamethrower;
import sanandreasp.mods.TurretMod3.client.model.turret.Model_T3Laser;
import sanandreasp.mods.TurretMod3.client.model.turret.Model_T4FLAK;
import sanandreasp.mods.TurretMod3.client.model.turret.Model_T4Sniper;
import sanandreasp.mods.TurretMod3.client.model.turret.Model_T5Artillery;
import sanandreasp.mods.TurretMod3.client.model.turret.Model_T5Railgun;
import sanandreasp.mods.TurretMod3.client.model.turret.Model_TSCollector;
import sanandreasp.mods.TurretMod3.client.model.turret.Model_TSForcefield;
import sanandreasp.mods.TurretMod3.client.model.turret.Model_TSHealer;
import sanandreasp.mods.TurretMod3.client.render.RenderDismantleStorage;
import sanandreasp.mods.TurretMod3.client.render.RenderMobileBase;
import sanandreasp.mods.TurretMod3.client.render.projectile.RenderBullet;
import sanandreasp.mods.TurretMod3.client.render.projectile.RenderFlame;
import sanandreasp.mods.TurretMod3.client.render.projectile.RenderPebble;
import sanandreasp.mods.TurretMod3.client.render.tileentity.ItemRenderLaptop;
import sanandreasp.mods.TurretMod3.client.render.tileentity.RenderLaptop;
import sanandreasp.mods.TurretMod3.client.render.turret.RenderTurretCollector;
import sanandreasp.mods.TurretMod3.client.render.turret.RenderTurretForcefield;
import sanandreasp.mods.TurretMod3.client.render.turret.RenderTurretHealer;
import sanandreasp.mods.TurretMod3.client.render.turret.RenderTurret_Base;
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
import sanandreasp.mods.TurretMod3.registry.CommonProxy;
import sanandreasp.mods.TurretMod3.registry.TM3ModRegistry;
import sanandreasp.mods.TurretMod3.tileentity.TileEntityLaptop;

public class ClientProxy extends CommonProxy {
    public final static ResourceLocation ITEM_ICONS = new ResourceLocation("/gui/items.png");
	@Override
	public void registerRenderInformation() {
		RenderingRegistry.registerEntityRenderingHandler(EntityTurret_T1Arrow.class, new RenderTurret_Base(new Model_T1Arrow(), 0.3F));
		RenderingRegistry.registerEntityRenderingHandler(EntityTurret_T1Shotgun.class, new RenderTurret_Base(new Model_T1Shotgun(), 0.3F));
		RenderingRegistry.registerEntityRenderingHandler(EntityTurret_T2Minigun.class, new RenderTurret_Base(new Model_T2Minigun(), 0.3F));
		RenderingRegistry.registerEntityRenderingHandler(EntityTurret_T2Revolver.class, new RenderTurret_Base(new Model_T2Revolver(), 0.3F));
		RenderingRegistry.registerEntityRenderingHandler(EntityTurret_T3Laser.class, new RenderTurret_Base(new Model_T3Laser(), 0.3F));
		RenderingRegistry.registerEntityRenderingHandler(EntityTurret_T3Flamethrower.class, new RenderTurret_Base(new Model_T3Flamethrower(), 0.3F));
		RenderingRegistry.registerEntityRenderingHandler(EntityTurret_T4Sniper.class, new RenderTurret_Base(new Model_T4Sniper(), 0.3F));
		RenderingRegistry.registerEntityRenderingHandler(EntityTurret_T4FLAK.class, new RenderTurret_Base(new Model_T4FLAK(), 0.3F));
		RenderingRegistry.registerEntityRenderingHandler(EntityTurret_T5Railgun.class, new RenderTurret_Base(new Model_T5Railgun(), 0.3F));
		RenderingRegistry.registerEntityRenderingHandler(EntityTurret_T5Artillery.class, new RenderTurret_Base(new Model_T5Artillery(), 0.3F));
		RenderingRegistry.registerEntityRenderingHandler(EntityTurret_TSSnowball.class, new RenderTurret_Base(new Model_T1Arrow(), 0.3F));
		RenderingRegistry.registerEntityRenderingHandler(EntityTurret_TSCollector.class, new RenderTurretCollector(new Model_TSCollector(), 0.3F));
		RenderingRegistry.registerEntityRenderingHandler(EntityTurret_TSHealer.class, new RenderTurretHealer(new Model_TSHealer(), 0.3F));
		RenderingRegistry.registerEntityRenderingHandler(EntityTurret_TSForcefield.class, new RenderTurretForcefield(new Model_TSForcefield(), 0.3F));
		
		RenderingRegistry.registerEntityRenderingHandler(TurretProj_Arrow.class, new RenderArrow());
		RenderingRegistry.registerEntityRenderingHandler(TurretProj_Pebble.class, new RenderPebble());
		RenderingRegistry.registerEntityRenderingHandler(TurretProj_Seed.class, new RenderPebble());
		RenderingRegistry.registerEntityRenderingHandler(TurretProj_Bullet.class, new RenderBullet(TM3ModRegistry.TEX_BULLET, 0.02625F));
		RenderingRegistry.registerEntityRenderingHandler(TurretProj_Laser.class, new RenderBullet(TM3ModRegistry.TEX_LASER, 0.06625F));
		RenderingRegistry.registerEntityRenderingHandler(TurretProj_Plasma.class, new RenderBullet(TM3ModRegistry.TEX_PLASMA, 0.06625F));
		RenderingRegistry.registerEntityRenderingHandler(TurretProj_Rocket.class, new RenderBullet(TM3ModRegistry.TEX_ROCKET, 0.09625F));
		RenderingRegistry.registerEntityRenderingHandler(TurretProj_Flame.class, new RenderFlame());
		RenderingRegistry.registerEntityRenderingHandler(TurretProj_Shard.class, new RenderBullet(TM3ModRegistry.TEX_SHARD, 0.06625F));
		RenderingRegistry.registerEntityRenderingHandler(TurretProj_Explosive.class, new RenderSnowball(Items.fire_charge));
		RenderingRegistry.registerEntityRenderingHandler(TurretProj_Snowball.class, new RenderSnowball(Items.snowball));
		
		RenderingRegistry.registerEntityRenderingHandler(EntityMobileBase.class, new RenderMobileBase(new Model_MobileBase(), 0.3F));
		RenderingRegistry.registerEntityRenderingHandler(EntityDismantleStorage.class, new RenderDismantleStorage(new ModelChest(), 0.3F));

        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityLaptop.class, new RenderLaptop());
        
        MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(TM3ModRegistry.laptop), new ItemRenderLaptop());
	}

    @Override
    public void registerHandlers() {
        MinecraftForge.EVENT_BUS.register(new SoundRegistry());
        TickRegistry.registerTickHandler(new TickHandlerClientRnd());
        KeyBindingRegistry.registerKeyBinding(new KeyBindHandler());
    }
}
