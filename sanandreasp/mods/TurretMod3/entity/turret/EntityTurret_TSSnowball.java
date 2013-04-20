package sanandreasp.mods.TurretMod3.entity.turret;

import java.util.List;
import java.util.AbstractMap.SimpleEntry;

import sanandreasp.mods.TurretMod3.entity.projectile.TurretProj_Arrow;
import sanandreasp.mods.TurretMod3.entity.projectile.TurretProj_Snowball;
import sanandreasp.mods.TurretMod3.entity.projectile.TurretProjectile;
import sanandreasp.mods.TurretMod3.registry.TM3ModRegistry;
import sanandreasp.mods.TurretMod3.registry.TurretUpgrades.TUpgSlowdownII;
import sanandreasp.mods.TurretMod3.registry.TurretUpgrades.TUpgStopMove;
import sanandreasp.mods.TurretMod3.registry.TurretUpgrades.TurretUpgrades;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class EntityTurret_TSSnowball extends EntityTurret_Base {

	public EntityTurret_TSSnowball(World par1World) {
		super(par1World);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public String getTexture() {
		return TM3ModRegistry.TEX_TURRETDIR + "tsSnowball.png";
	}

	@Override
	public TurretProjectile getProjectile() {
		TurretProj_Snowball proj = new TurretProj_Snowball(this.worldObj);
		proj.amplifier = this.getSlowdownAmplifier();
		return proj;
	}

	@Override
	public int getMaxShootTicks() {
		return 20;
	}

	@Override
	public String getGlowTexture() {
		return TM3ModRegistry.TEX_TURRETDIR + "tsSnowballG.png";
	}
	
	private int getSlowdownAmplifier() {
		int amplifier = 0;
		if(TurretUpgrades.hasUpgrade(TUpgSlowdownII.class, this.upgrades))
			amplifier = 2;
		if(TurretUpgrades.hasUpgrade(TUpgStopMove.class, this.upgrades))
			amplifier = 6;
		
		return amplifier;
	}
	
	@Override
	public boolean isTargetValid(EntityLiving entity) {
		PotionEffect effect = entity.getActivePotionEffect(Potion.moveSlowdown);
		return super.isTargetValid(entity) && !(effect != null && effect.getDuration() > 20 && effect.getAmplifier() >= this.getSlowdownAmplifier());
	}
}
