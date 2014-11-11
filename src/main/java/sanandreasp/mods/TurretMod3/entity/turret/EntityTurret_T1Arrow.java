package sanandreasp.mods.turretmod3.entity.turret;

import sanandreasp.mods.turretmod3.entity.projectile.TurretProj_Arrow;
import sanandreasp.mods.turretmod3.entity.projectile.TurretProjectile;
import sanandreasp.mods.turretmod3.registry.TM3ModRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.world.World;

public class EntityTurret_T1Arrow extends EntityTurret_Base {

	public EntityTurret_T1Arrow(World par1World) {
		super(par1World);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public String getTexture() {
		return TM3ModRegistry.TEX_TURRETDIR + "t1Arrow.png";
	}

	@Override
	public TurretProjectile getProjectile() {
		return new TurretProj_Arrow(this.worldObj);
	}

	@Override
	public int getMaxShootTicks() {
		return 20;
	}

	@Override
	public String getGlowTexture() {
		return TM3ModRegistry.TEX_TURRETDIR + "t1ArrowG.png";
	}
}
