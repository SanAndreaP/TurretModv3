package sanandreasp.mods.turretmod3.entity.turret;

import sanandreasp.mods.turretmod3.entity.projectile.TurretProj_Plasma;
import sanandreasp.mods.turretmod3.entity.projectile.TurretProjectile;
import sanandreasp.mods.turretmod3.registry.TM3ModRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.world.World;

public class EntityTurret_T4Sniper extends EntityTurret_Base {
	
	public EntityTurret_T4Sniper(World par1World) {
		super(par1World);
		this.wdtRange = 64.5F;
		this.hgtRangeD = 20.5F;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public String getTexture() {
		return TM3ModRegistry.TEX_TURRETDIR + "t4Sniper.png";
	}
	
	@Override
	public int func_110138_aP() {
		return 40;
	}

	@Override
	public TurretProjectile getProjectile() {
		return new TurretProj_Plasma(this.worldObj);
	}
	
	@Override
	public void shootProjectile(boolean isRidden) {
		super.shootProjectile(isRidden);
		double rotYawX = Math.sin((this.rotationYawHead / 180) * Math.PI);
		double rotYawZ = Math.cos((this.rotationYawHead / 180) * Math.PI);
		double partX = this.posX - rotYawX * (Math.cos(this.rotationPitch / (180F / (float)Math.PI))) * 1.0D;
		double partY = this.posY + this.getEyeHeight() - Math.sin(this.rotationPitch / (180F / (float)Math.PI)) * 1.0D;
		double partZ = this.posZ + rotYawZ * (Math.cos(this.rotationPitch / (180F / (float)Math.PI))) * 1.0D;
		
		TM3ModRegistry.proxy.spawnParticle(3, partX, partY, partZ, 64, this.worldObj.provider.dimensionId, this);
	}
	
	@Override
	public int getMaxShootTicks() {
		return 100;
	}

	@Override
	public String getGlowTexture() {
		return TM3ModRegistry.TEX_TURRETDIR + "t4SniperG.png";
	}
	
	@Override
	public boolean hasFireImmunity() {
		return true;
	}
	
	@Override
	public String getShootSound() {
		return "turretmod3.shoot.sniper";
	}
}
