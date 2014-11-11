package sanandreasp.mods.TurretMod3.entity.projectile;

import sanandreasp.mods.TurretMod3.registry.TM3ModRegistry;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class TurretProj_Pebble extends TurretProjectile {

	public TurretProj_Pebble(World par1World) {
		super(par1World);
	}

	public TurretProj_Pebble(World par1World, double par2, double par4, double par6) {
		super(par1World, par2, par4, par6);
	}
	
	@Override
	public String getHitSound() {
		return "ricochet.bullet";
	}
	
	@Override
	public float getGravityVal() {
		return 0.001F;
	}
	
	@Override
	public float getSpeedVal() {
		return 3F;
	}
	
	@Override
	public boolean isArrow() {
		return false;
	}
	
	@Override
	public float getCurveCorrector() {
		return 0.07F;
	}
	
	@Override
	public double getDamage() {
		return 1D;
	}
	
	@Override
	protected boolean shouldTargetOneType() {
		return false;
	}
	
	@Override
	public ItemStack getPickupItem() {
		return new ItemStack(TM3ModRegistry.ammoItems, 1, 2);
	}
}
