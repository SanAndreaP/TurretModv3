package sanandreasp.mods.TurretMod3.entity;

import java.util.ArrayList;
import java.util.List;

import sanandreasp.mods.TurretMod3.inventory.InventoryDismantleStorage;
import sanandreasp.mods.TurretMod3.registry.TM3ModRegistry;
import sanandreasp.mods.TurretMod3.registry.TurretInfo.TurretInfo;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityDismantleStorage extends EntityLiving {
	
	public InventoryDismantleStorage inventory;
	public Class tbClass = null;
	public Object tbRender = null;
	public boolean checkForDestroy = false;

	public EntityDismantleStorage(World par1World) {
		super(par1World);
		this.setSize(0.65F, 0.8F);
		this.dataWatcher.addObject(20, (int)0);
		this.inventory = new InventoryDismantleStorage("turretmod3.gui.dismtStorage", 27, this);
	}

	public EntityDismantleStorage(World par1World, int i) {
		this(par1World);
		this.dataWatcher.updateObject(20, i);
	}
	
	public void toggleCheckForDestroy() {
		this.checkForDestroy = !this.checkForDestroy;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public String getTexture() {
		return "/item/chest.png";
	}
	
	protected boolean isMovementBlocked() {
		return true;
	};
	
	@Override
	public void onUpdate() {
		super.onUpdate();
		if (this.tbClass == null) {
			this.tbClass = TurretInfo.getTurretClass(this.dataWatcher.getWatchableObjectInt(20));
		}
		
		if (this.checkForDestroy) {
			List<Boolean> b = new ArrayList<Boolean>();
			for (int i = 0; i < this.inventory.getSizeInventory(); i++) {
				ItemStack is = this.inventory.getStackInSlot(i);
				b.add(is != null && is.stackSize > 0);
			}
			if (!b.contains(true)) {
				this.attackEntityFrom(DamageSource.magic, Math.max(this.health, this.func_110138_aP()));
			}
		}
	}
	
	@Override
    protected void onDeathUpdate()
    {
        ++this.deathTime;

        if (this.deathTime > 5)
        {
            int var1;

            this.setDead();

            for (var1 = 0; var1 < 20 && this.worldObj.isRemote; ++var1)
            {
            	TurretInfo tInfo = TurretInfo.getTurretInfo(this.tbClass);
            	for (Object obj : tInfo.getCrafting()) {
            		if (obj != null && obj instanceof ItemStack) {
            			String s = "";
            			ItemStack is = ((ItemStack)obj);
            			if (is.getItem() instanceof ItemBlock) {
            				s = "tilecrack_"+is.itemID+"_"+is.getItemDamage();
            			} else {
                			s = "iconcrack_"+is.itemID;
            			}
            			this.worldObj.spawnParticle(s, this.posX + (double)(this.rand.nextFloat() * this.width * 2.0F) - (double)this.width, this.posY + (double)(this.rand.nextFloat() * this.height), this.posZ + (double)(this.rand.nextFloat() * this.width * 2.0F) - (double)this.width, 0D, 0D, 0D);
            		}
            	}
            	this.worldObj.spawnParticle("explode", this.posX + (double)(this.rand.nextFloat() * this.width * 2.0F) - (double)this.width, this.posY + (double)(this.rand.nextFloat() * this.height), this.posZ + (double)(this.rand.nextFloat() * this.width * 2.0F) - (double)this.width, 0D, 0D, 0D);
            }

            for (int i = 0; i < this.inventory.getSizeInventory(); i++) {
            	ItemStack is = this.inventory.getStackInSlot(i);
            	if (is != null) {
            		this.entityDropItem(is, 0.0F).setVelocity(0F, 0.2F, 0F);
            	}
            }
        }
    }
	
	@Override
	protected String getDeathSound() {
		return "dig.stone";
	}
	
	@Override
	public boolean interact(EntityPlayer par1EntityPlayer) {
		if (!worldObj.isRemote) par1EntityPlayer.openGui(TM3ModRegistry.instance, 2, this.worldObj, this.entityId, 0, 0);
		return true;
	}
	
	@Override
	public void moveEntity(double par1, double par3, double par5) {
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound par1) {
		
		this.dataWatcher.updateObject(20, par1.getInteger("turretCls"));
    	
        NBTTagList var1 = par1.getTagList("Inventory");

        for (int var2 = 0; var2 < var1.tagCount(); ++var2)
        {
            NBTTagCompound var3 = (NBTTagCompound)var1.tagAt(var2);
            int slot = var3.getInteger("slot");
            ItemStack is = ItemStack.loadItemStackFromNBT(var3);
            this.inventory.setInventorySlotContents(slot, is);
        }
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound par1) {
		
		par1.setInteger("turretCls", this.dataWatcher.getWatchableObjectInt(20));
    	
        NBTTagList var1 = new NBTTagList();

        for (int i = 0; i < this.inventory.getSizeInventory(); i++) {
        	NBTTagCompound var4 = new NBTTagCompound();
        	ItemStack is = this.inventory.getStackInSlot(i);
        	if (is != null) {
        		var4.setInteger("slot", i);
        		is.writeToNBT(var4);
        		var1.appendTag(var4);
        	}
        }

        par1.setTag("Inventory", var1);
	}

	@Override
	public int func_110138_aP() {
		return 1;
	}

}
