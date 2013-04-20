package sanandreasp.mods.TurretMod3.entity.turret;

import java.util.AbstractMap.SimpleEntry;

import sanandreasp.mods.TurretMod3.entity.projectile.TurretProj_Arrow;
import sanandreasp.mods.TurretMod3.entity.projectile.TurretProj_Bullet;
import sanandreasp.mods.TurretMod3.entity.projectile.TurretProj_Pebble;
import sanandreasp.mods.TurretMod3.entity.projectile.TurretProjectile;
import sanandreasp.mods.TurretMod3.registry.AchievementPageTM;
import sanandreasp.mods.TurretMod3.registry.TM3ModRegistry;
import sanandreasp.mods.TurretMod3.registry.TurretUpgrades.TUpgInfAmmo;
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

public class EntityTurret_T2Revolver extends EntityTurret_Base {
	
	private boolean isRight = false;

	public EntityTurret_T2Revolver(World par1World) {
		super(par1World);
		this.dataWatcher.addObject(18, (int) 0); // right Offset
		this.dataWatcher.addObject(19, (int) 0); // left Offset
		this.wdtRange = 24.5F;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public String getTexture() {
		return TM3ModRegistry.TEX_TURRETDIR + "t2Revolver.png";
	}
	
	public int getRightBarrelOffset() {
		return this.dataWatcher.getWatchableObjectInt(18);
	}
	
	public int getLeftBarrelOffset() {
		return this.dataWatcher.getWatchableObjectInt(19);
	}
	
	@Override
	public int getMaxHealth() {
		return 40;
	}

	@Override
	public TurretProjectile getProjectile() {
		return new TurretProj_Bullet(this.worldObj);
	}
	
	@Override
	public void shootProjectile(boolean isRidden) {
		super.shootProjectile(isRidden);
		double rotYawX = Math.sin((this.rotationYawHead / 180) * Math.PI);
		double rotYawZ = Math.cos((this.rotationYawHead / 180) * Math.PI);
		double partX = this.posX - rotYawX * (Math.cos(this.rotationPitch / (180F / (float)Math.PI))) * 0.7D;
		double partY = this.posY + this.getEyeHeight() - Math.sin(this.rotationPitch / (180F / (float)Math.PI)) * 0.7D;
		double partZ = this.posZ + rotYawZ * (Math.cos(this.rotationPitch / (180F / (float)Math.PI))) * 0.7D;
		
		TM3ModRegistry.proxy.spawnParticle(0, partX - (this.isRight ? 0.1D : -0.1D)*rotYawZ, partY, partZ - (this.isRight ? 0.1D : -0.1D)*rotYawX, 64, this.worldObj.getWorldInfo().getDimension(), this);
	}
	
	@Override
	public void onUpdate() {
		super.onUpdate();
		
		if(!this.worldObj.isRemote) {
			this.dataWatcher.updateObject(18, Math.max(0, this.getRightBarrelOffset() - 1));
			this.dataWatcher.updateObject(19, Math.max(0, this.getLeftBarrelOffset() - 1));
		}
	}
	
	@Override
	public int getMaxShootTicks() {
		int maxShootTicks = 10;
		if(!this.worldObj.isRemote) {
			this.dataWatcher.updateObject(isRight ? 18 : 19, maxShootTicks);
			this.isRight = !this.isRight;
		}
		return maxShootTicks;
	}

	@Override
	public String getGlowTexture() {
		return TM3ModRegistry.TEX_TURRETDIR + "t2RevolverG.png";
	}
	
	@Override
	public String getShootSound() {
		return "turretmod3.shoot.pistol";
	}
}
