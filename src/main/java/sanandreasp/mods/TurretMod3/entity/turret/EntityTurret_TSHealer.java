package sanandreasp.mods.turretmod3.entity.turret;

import sanandreasp.mods.turretmod3.entity.IHealable;
import sanandreasp.mods.turretmod3.entity.projectile.TurretProjectile;
import sanandreasp.mods.turretmod3.registry.TM3ModRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.EntityLiving;
import net.minecraft.world.World;

public class EntityTurret_TSHealer extends EntityTurret_Base {
	
	private int soundTicks = 0;

	public EntityTurret_TSHealer(World par1World) {
		super(par1World);
		this.dataWatcher.addObject(19, (int)0);
		this.ignoreFrustumCheck = true;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public String getTexture() {
		return TM3ModRegistry.TEX_TURRETDIR + "tsHealer.png";
	}

	@Override
	public TurretProjectile getProjectile() {
		return null;
	}
	
	@Override
	public void shootProjectile(boolean isRidden) {
		this.currentTarget.heal(1);
		if (this.soundTicks == 0) {
	        this.worldObj.playSoundAtEntity(this, this.getShootSound(), 1.5F, 1.0F / (float)(this.getRNG().nextFloat() * 0.2F + 0.8F));
		}
		this.soundTicks++;
		if (this.soundTicks > 3)
			this.soundTicks = 0;
	}
	
	@Override
	public String getShootSound() {
		return "turretmod3.shoot.healBeam";
	}
	
	@Override
	public int getMaxShootTicks() {
		return 5;
	}
	
	@Override
	public void onUpdate() {
		super.onUpdate();
		
		if (!this.worldObj.isRemote && this.currentTarget != null) {
			this.dataWatcher.updateObject(19, this.currentTarget.entityId);
		}
		
		if (this.currentTarget == null || this.getAmmo() <= 0) {
			this.soundTicks = 0;
		}
	}
	
	public int getTargetEID() {
		return this.dataWatcher.getWatchableObjectInt(19);
	}
	
	@Override
	public String getGlowTexture() {
		return TM3ModRegistry.TEX_TURRETDIR + "tsHealerG.png";
	}
	
	@Override
	public boolean isTargetValid(EntityLiving entity) {
		float var1 = (float)wdtRange;
        boolean inList = !isEntityTargeted(entity) && entity instanceof IHealable;
        
        return !(entity.isDead || entity.func_110143_aJ() <= 0 || entity.func_110143_aJ() >= entity.func_110138_aP()
        		|| entity.getDistanceSqToEntity(this) > (double)(var1 * var1) 
        		|| !this.canEntityBeSeen(entity)
        		|| this.posY - entity.posY > this.hgtRangeD
        		|| entity.posY - this.posY > this.hgtRangeU) && inList;
	}
}
