package sanandreasp.mods.TurretMod3.entity.turret;

import java.util.AbstractMap.SimpleEntry;

import sanandreasp.mods.TurretMod3.entity.projectile.TurretProj_Arrow;
import sanandreasp.mods.TurretMod3.entity.projectile.TurretProj_Pebble;
import sanandreasp.mods.TurretMod3.entity.projectile.TurretProj_Seed;
import sanandreasp.mods.TurretMod3.entity.projectile.TurretProjectile;
import sanandreasp.mods.TurretMod3.registry.AchievementPageTM;
import sanandreasp.mods.TurretMod3.registry.TM3ModRegistry;
import sanandreasp.mods.TurretMod3.registry.TurretUpgrades.TUpgInfAmmo;
import sanandreasp.mods.TurretMod3.registry.TurretUpgrades.TUpgPiercing;
import sanandreasp.mods.TurretMod3.registry.TurretUpgrades.TurretUpgrades;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityTurret_T2Minigun extends EntityTurret_Base {
	
	public double barrelRot = 0D;
	private boolean canRot = false;
	private boolean isRight = false;
	private int killsWithin10sec = 0;
	
	public EntityTurret_T2Minigun(World par1World) {
		super(par1World);
		this.wdtRange = 24.5F;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public String getTexture() {
		return TM3ModRegistry.TEX_TURRETDIR + "t2Minigun.png";
	}
	
	@Override
	public TurretProjectile getProjectile() {
		TurretProj_Seed proj = new TurretProj_Seed(this.worldObj);
		proj.isPiercing = TurretUpgrades.hasUpgrade(TUpgPiercing.class, this.upgrades);
		return proj;
	}

	@Override
	public int getMaxHealth() {
		return 40;
	}
	
	@Override
	public int getMaxAmmo() {
		return 256;
	}
	
	@Override
	public void shootProjectile(boolean isRidden) {
		super.shootProjectile(isRidden);
		double rotYawX = Math.sin((this.rotationYawHead / 180) * Math.PI);
		double rotYawZ = Math.cos((this.rotationYawHead / 180) * Math.PI);
		double partX = this.posX - rotYawX * (Math.cos(this.rotationPitch / (180F / (float)Math.PI))) * 0.5D;
		double partY = this.posY + this.getEyeHeight() - Math.sin(this.rotationPitch / (180F / (float)Math.PI)) * 0.5D;
		double partZ = this.posZ + rotYawZ * (Math.cos(this.rotationPitch / (180F / (float)Math.PI))) * 0.5D;
		
		TM3ModRegistry.proxy.spawnParticle(0, partX - (this.isRight ? 0.5D : -0.5D)*rotYawZ, partY+0.1D, partZ - (this.isRight ? 0.5D : -0.5D)*rotYawX, 64, this.worldObj.getWorldInfo().getDimension(), this);
	}
	
	@Override
	public int getMaxShootTicks() {
		return 2;
	}
	
	@Override
	public void onUpdate() {
		super.onUpdate();
		
		if(this.getShootTicks() == 1) {
			barrelRot += 45D;
			if(MathHelper.floor_double(barrelRot) == 180)
				barrelRot = 0F;
			
			isRight = MathHelper.floor_double(barrelRot) % 90 != 0;
		}
		if(this.ticksExisted % 200 == 0) {
			if(this.killsWithin10sec >= 10){
				EntityPlayer player = this.worldObj.getPlayerEntityByName(this.getPlayerName());
				if(player != null) {
					player.triggerAchievement(AchievementPageTM.piercing);
				}
			}
			this.killsWithin10sec = 0;
		}
		
	}

	@Override
	public String getGlowTexture() {
		return TM3ModRegistry.TEX_TURRETDIR + "t2MinigunG.png";
	}
	
	@Override
	public String getShootSound() {
		return "turretmod3.shoot.minigun";
	}
	
	@Override
	public void onKillEntity(EntityLiving par1EntityLiving) {
		super.onKillEntity(par1EntityLiving);
		this.killsWithin10sec++;
	}
}
