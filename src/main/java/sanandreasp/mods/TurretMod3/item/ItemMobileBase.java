package sanandreasp.mods.TurretMod3.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import sanandreasp.mods.TurretMod3.entity.EntityMobileBase;
import sanandreasp.mods.TurretMod3.entity.turret.EntityTurret_Base;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Facing;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class ItemMobileBase extends Item {

	public ItemMobileBase(int par1) {
		super(par1);
	}
	
	@Override
    public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10)
    {
        if (par3World.isRemote)
        {
            return true;
        }
        else
        {
            int var11 = par3World.getBlockId(par4, par5, par6);
            par4 += Facing.offsetsXForSide[par7];
            par5 += Facing.offsetsYForSide[par7];
            par6 += Facing.offsetsZForSide[par7];
            double var12 = 0.0D;

            if (par7 == 1 && Block.blocksList[var11] != null && Block.blocksList[var11].getRenderType() == 11)
            {
                var12 = 0.5D;
            }

            if (spawnBase(par3World, (double)par4 + 0.5D, (double)par5 + var12, (double)par6 + 0.5D) != null && !par2EntityPlayer.capabilities.isCreativeMode)
            {
                --par1ItemStack.stackSize;
            }

            return true;
        }
    }
    
    public static Entity spawnBase(World par0World, double par2, double par4, double par6)
    {
        Entity var8 = new EntityMobileBase(par0World);

        if (var8 != null && var8 instanceof EntityMobileBase)
        {
        	EntityMobileBase var10 = (EntityMobileBase)var8;
            var8.setLocationAndAngles(par2, par4, par6, MathHelper.wrapAngleTo180_float(par0World.rand.nextFloat() * 360.0F), 0.0F);
            var10.rotationYawHead = var10.rotationYaw;
            var10.renderYawOffset = var10.rotationYaw;
            var10.initCreature();
            par0World.spawnEntityInWorld(var8);
            var10.playLivingSound();
        }
        return var8;
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister par1IconRegister) {
    	this.itemIcon = par1IconRegister.registerIcon("TurretMod3:mobileBase");
    }

}
