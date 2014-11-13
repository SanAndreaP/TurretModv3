package sanandreasp.mods.turretmod3.client.registry;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import net.minecraft.client.model.ModelChest;
import net.minecraft.client.renderer.entity.RenderArrow;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.MinecraftForge;
import sanandreasp.mods.turretmod3.client.model.Model_MobileBase;
import sanandreasp.mods.turretmod3.client.model.turret.*;
import sanandreasp.mods.turretmod3.client.render.RenderDismantleStorage;
import sanandreasp.mods.turretmod3.client.render.RenderMobileBase;
import sanandreasp.mods.turretmod3.client.render.projectile.RenderBullet;
import sanandreasp.mods.turretmod3.client.render.projectile.RenderFlame;
import sanandreasp.mods.turretmod3.client.render.projectile.RenderPebble;
import sanandreasp.mods.turretmod3.client.render.tileentity.ItemRenderLaptop;
import sanandreasp.mods.turretmod3.client.render.tileentity.RenderLaptop;
import sanandreasp.mods.turretmod3.client.render.turret.RenderTurretCollector;
import sanandreasp.mods.turretmod3.client.render.turret.RenderTurretForcefield;
import sanandreasp.mods.turretmod3.client.render.turret.RenderTurretHealer;
import sanandreasp.mods.turretmod3.client.render.turret.RenderTurret_Base;
import sanandreasp.mods.turretmod3.entity.EntityDismantleStorage;
import sanandreasp.mods.turretmod3.entity.EntityMobileBase;
import sanandreasp.mods.turretmod3.entity.projectile.*;
import sanandreasp.mods.turretmod3.entity.turret.*;
import sanandreasp.mods.turretmod3.registry.CommonProxy;
import sanandreasp.mods.turretmod3.registry.TM3ModRegistry;
import sanandreasp.mods.turretmod3.tileentity.TileEntityLaptop;

public class ClientProxy extends CommonProxy {
    public final static ResourceLocation ITEM_ICONS = new ResourceLocation("/gui/items.png");
    public final static String TEX_BULLET		= "turretmod3:textures/entities/bullet.png";
    public final static String TEX_LASER		= "turretmod3:textures/entities/laser.png";
    public final static String TEX_PLASMA		= "turretmod3:textures/entities/plasma.png";
    public final static String TEX_ROCKET		= "turretmod3:textures/entities/rocket.png";
    public final static String TEX_SHARD		= "turretmod3:textures/entities/shard.png";
	@Override
	public void registerRenderInformation() {
		RenderingRegistry.registerEntityRenderingHandler(EntityTurret_T1Arrow.class, new RenderTurret_Base(new Model_T1Arrow()));
		RenderingRegistry.registerEntityRenderingHandler(EntityTurret_T1Shotgun.class, new RenderTurret_Base(new Model_T1Shotgun()));
		RenderingRegistry.registerEntityRenderingHandler(EntityTurret_T2Minigun.class, new RenderTurret_Base(new Model_T2Minigun()));
		RenderingRegistry.registerEntityRenderingHandler(EntityTurret_T2Revolver.class, new RenderTurret_Base(new Model_T2Revolver()));
		RenderingRegistry.registerEntityRenderingHandler(EntityTurret_T3Laser.class, new RenderTurret_Base(new Model_T3Laser()));
		RenderingRegistry.registerEntityRenderingHandler(EntityTurret_T3Flamethrower.class, new RenderTurret_Base(new Model_T3Flamethrower()));
		RenderingRegistry.registerEntityRenderingHandler(EntityTurret_T4Sniper.class, new RenderTurret_Base(new Model_T4Sniper()));
		RenderingRegistry.registerEntityRenderingHandler(EntityTurret_T4FLAK.class, new RenderTurret_Base(new Model_T4FLAK()));
		RenderingRegistry.registerEntityRenderingHandler(EntityTurret_T5Railgun.class, new RenderTurret_Base(new Model_T5Railgun()));
		RenderingRegistry.registerEntityRenderingHandler(EntityTurret_T5Artillery.class, new RenderTurret_Base(new Model_T5Artillery()));
		RenderingRegistry.registerEntityRenderingHandler(EntityTurret_TSSnowball.class, new RenderTurret_Base(new Model_T1Arrow()));
		RenderingRegistry.registerEntityRenderingHandler(EntityTurret_TSCollector.class, new RenderTurretCollector(new Model_TSCollector()));
		RenderingRegistry.registerEntityRenderingHandler(EntityTurret_TSHealer.class, new RenderTurretHealer(new Model_TSHealer()));
		RenderingRegistry.registerEntityRenderingHandler(EntityTurret_TSForcefield.class, new RenderTurretForcefield(new Model_TSForcefield()));
		
		RenderingRegistry.registerEntityRenderingHandler(TurretProj_Arrow.class, new RenderArrow());
		RenderingRegistry.registerEntityRenderingHandler(TurretProj_Pebble.class, new RenderPebble());
		RenderingRegistry.registerEntityRenderingHandler(TurretProj_Seed.class, new RenderPebble());
		RenderingRegistry.registerEntityRenderingHandler(TurretProj_Bullet.class, new RenderBullet(TEX_BULLET, 0.02625F));
		RenderingRegistry.registerEntityRenderingHandler(TurretProj_Laser.class, new RenderBullet(TEX_LASER, 0.06625F));
		RenderingRegistry.registerEntityRenderingHandler(TurretProj_Plasma.class, new RenderBullet(TEX_PLASMA, 0.06625F));
		RenderingRegistry.registerEntityRenderingHandler(TurretProj_Rocket.class, new RenderBullet(TEX_ROCKET, 0.09625F));
		RenderingRegistry.registerEntityRenderingHandler(TurretProj_Flame.class, new RenderFlame());
		RenderingRegistry.registerEntityRenderingHandler(TurretProj_Shard.class, new RenderBullet(TEX_SHARD, 0.06625F));
		RenderingRegistry.registerEntityRenderingHandler(TurretProj_Explosive.class, new RenderSnowball(Items.fire_charge));
		RenderingRegistry.registerEntityRenderingHandler(TurretProj_Snowball.class, new RenderSnowball(Items.snowball));
		
		RenderingRegistry.registerEntityRenderingHandler(EntityMobileBase.class, new RenderMobileBase(new Model_MobileBase(), 0.3F));
		RenderingRegistry.registerEntityRenderingHandler(EntityDismantleStorage.class, new RenderDismantleStorage(new ModelChest(), 0.3F));

        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityLaptop.class, new RenderLaptop());
        
        MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(TM3ModRegistry.laptop), new ItemRenderLaptop());
	}

    @Override
    public void registerHandlers() {
        MinecraftForge.EVENT_BUS.register(new TextureRegistry());
        FMLCommonHandler.instance().bus().register(new TickHandlerClientRnd());
    }

    @Override
    public EntityPlayer getPlayer(MessageContext context){
        return FMLClientHandler.instance().getClientPlayerEntity();
    }
}
