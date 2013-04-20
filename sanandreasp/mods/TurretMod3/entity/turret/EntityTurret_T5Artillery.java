package sanandreasp.mods.TurretMod3.entity.turret;

import java.util.AbstractMap.SimpleEntry;

import sanandreasp.mods.TurretMod3.entity.projectile.TurretProj_Explosive;
import sanandreasp.mods.TurretMod3.entity.projectile.TurretProjectile;
import sanandreasp.mods.TurretMod3.registry.AchievementPageTM;
import sanandreasp.mods.TurretMod3.registry.TM3ModRegistry;
import sanandreasp.mods.TurretMod3.registry.TurretUpgrades.TUpgInfAmmo;
import sanandreasp.mods.TurretMod3.registry.TurretUpgrades.TUpgPrecision;
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

public class EntityTurret_T5Artillery extends EntityTurret_Base {
	
	public EntityTurret_T5Artillery(World par1World) {
		super(par1World);
		this.wdtRange = 50.5F;
		this.hgtRangeD = 20.5F;
		this.hgtRangeU = 15.5F;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public String getTexture() {
		return TM3ModRegistry.TEX_TURRETDIR + "t5Artillery.png";
	}
	
	@Override
	public int getMaxHealth() {
		return 100;
	}

	@Override
	public TurretProjectile getProjectile() {
		TurretProj_Explosive proj = new TurretProj_Explosive(this.worldObj);
		int i = this.getAmmoType();
		proj.isPrecise = TurretUpgrades.hasUpgrade(TUpgPrecision.class, this.upgrades);
		proj.explosionRadius = i==1||i==3||i==6||i==8 ? 4F : 2F;
		proj.isNapalm = i==2||i==3||i==7||i==8;
		proj.isGriefing = i>4;
		proj.isFragmentating = i==4||i==9;
		return proj;
	}
	
	@Override
	public void shootProjectile(boolean isRidden) {
		super.shootProjectile(isRidden);
		double rotYawX = Math.sin((this.rotationYawHead / 180F) * Math.PI);
		double rotYawZ = Math.cos((this.rotationYawHead / 180F) * Math.PI);
		double partX = this.posX - rotYawX * (Math.cos((this.rotationPitch - 90F) / (180F / (float)Math.PI))) * 0.5D;
		double partY = this.posY + this.getEyeHeight() - Math.sin((this.rotationPitch - 90F) / (180F / (float)Math.PI)) * 0.7D;
		double partZ = this.posZ + rotYawZ * (Math.cos((this.rotationPitch - 90F) / (180F / (float)Math.PI))) * 0.5D;
		
		TM3ModRegistry.proxy.spawnParticle(10, partX, partY, partZ, 64, this.worldObj.getWorldInfo().getDimension(), this);
	}
	
	@Override
	public int getMaxShootTicks() {
		return 150;
	}

	@Override
	public String getGlowTexture() {
		return TM3ModRegistry.TEX_TURRETDIR + "t5ArtilleryG.png";
	}
	
	@Override
	public boolean hasFireImmunity() {
		return true;
	}
	
	@Override
	public String getShootSound() {
		return "turretmod3.shoot.artillery";
	}
	
	@Override
	public float getShootSoundRng() {
		return 2F;
	}
}
