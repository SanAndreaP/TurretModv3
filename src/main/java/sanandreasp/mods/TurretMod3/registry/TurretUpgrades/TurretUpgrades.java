package sanandreasp.mods.TurretMod3.registry.TurretUpgrades;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import sanandreasp.mods.TurretMod3.entity.turret.EntityTurret_Base;

import com.google.common.collect.Maps;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.StatCollector;

import static sanandreasp.mods.managers.CommonUsedStuff.CUS;

public abstract class TurretUpgrades {
	protected int id;
	protected ItemStack upgItem;
	protected Enchantment upgEnchantment = null;
	protected String upgName;
	protected String upgDesc;
	protected List<Class> turrets = new ArrayList();
	protected static Map<Integer, TurretUpgrades> upgradeListINT = Maps.newHashMap();
	protected static Map<Class, TurretUpgrades> upgradeListCLT = Maps.newHashMap();
	protected Class<? extends TurretUpgrades> requiredUpg = null;
	
	public TurretUpgrades() { }
	
	public ItemStack getItem() {
		return this.upgItem.copy();
	}
	
	public Enchantment getEnchantment() {
		return this.upgEnchantment;
	}
	
	public boolean hasRequiredUpgrade(Map<Integer, ItemStack> upgMap) {
		if (this.requiredUpg == null)
			return true;
		return hasUpgrade(this.requiredUpg, upgMap);
	}
	
	public boolean hasRequiredUpgrade(List<ItemStack> upgMap) {
		if (this.requiredUpg == null)
			return true;
		return hasUpgrade(this.requiredUpg, upgMap);
	}
	
	public String getReqUpgradeName() {
		return this.requiredUpg != null ? TurretUpgrades.upgradeListCLT.get(this.requiredUpg).getName() : "";
	}
	
	public String getName() {
		return StatCollector.translateToLocal(this.upgName);
	}
	
	public String getDesc() {
		return StatCollector.translateToLocal(this.upgDesc);
	}
	
	public List<Class> getTurrets() {
		return new ArrayList(this.turrets);
	}
	
	public static boolean isUpgradeForTurret(TurretUpgrades upg, Class<? extends EntityTurret_Base> turretCls) {
		if (turretCls == null)
			return false;
		for (Class<? extends EntityTurret_Base> upgCls : upg.getTurrets()) {
			if (upgCls.isAssignableFrom(turretCls))
				return true;
		}
		return false;
	}
	
	public static void addUpgrade(TurretUpgrades upg) {
		upg.id = upgradeListINT.size();
		upgradeListINT.put(upg.id, upg);
		upgradeListCLT.put(upg.getClass(), upg);
	}
	
	public static int getUpgradeCount() {
		return upgradeListINT.size();
	}
	
	public static TurretUpgrades getUpgradeFromID(int id) {
		return upgradeListINT.get(id);
	}
	
	public static TurretUpgrades getUpgradeFromItem(ItemStack stack, Class<? extends EntityTurret_Base> turretCls) {
		if (stack == null) return null;
		for (int i = 0; i < upgradeListINT.size(); i++) {
			TurretUpgrades upg = getUpgradeFromID(i);
			if (CUS.areStacksEqualWithWCV(upg.getItem(), stack) && (turretCls == null || isUpgradeForTurret(upg, turretCls)))
				return getUpgradeFromID(i);
		}
		return null;
	}
	
	public int getUpgradeID() {
		return this.id;
	}
	
	public static boolean hasUpgrade(Class<? extends TurretUpgrades> tUpg, Map<Integer, ItemStack> upgMap) {
		if (upgMap == null || tUpg == null)
			return false;
		
		TurretUpgrades chkUpg = upgradeListCLT.get(tUpg);
		
		if (!upgMap.containsKey(chkUpg.getUpgradeID()) || (upgMap.containsKey(chkUpg.getUpgradeID()) && upgMap.get(chkUpg.getUpgradeID()) == null))
			return false;
		
		if (upgMap.containsKey(chkUpg.getUpgradeID()) && upgMap.get(chkUpg.getUpgradeID()) != null) {
			ItemStack is = upgMap.get(chkUpg.getUpgradeID());
			if (CUS.areStacksEqualWithWCV(chkUpg.getItem(), is)) {
				if (is.isItemEnchanted() && chkUpg.getEnchantment() != null) {
					NBTTagList ench = is.getEnchantmentTagList();
					for (int j = 0; j < ench.tagCount(); ++j) {
						NBTTagCompound var4 = (NBTTagCompound)ench.tagAt(j);
						if (var4.getShort("id") == chkUpg.getEnchantment().effectId) {
							return true;
						}
					}
				} else if (chkUpg.getEnchantment() == null) {
					return true;
				}
			}
		}
		
		return false;
	}
	
	public static boolean hasUpgrade(Class<? extends TurretUpgrades> tUpg, List<ItemStack> upgMap) {
		if (upgMap == null || tUpg == null)
			return false;
		
		TurretUpgrades chkUpg = upgradeListCLT.get(tUpg);
		
		for (ItemStack is : upgMap) {
			if (CUS.areStacksEqualWithWCV(chkUpg.getItem(), is)) {
				if (is.isItemEnchanted() && chkUpg.getEnchantment() != null) {
					NBTTagList ench = is.getEnchantmentTagList();
					for (int j = 0; j < ench.tagCount(); ++j) {
						NBTTagCompound var4 = (NBTTagCompound)ench.tagAt(j);
						if (var4.getShort("id") == chkUpg.getEnchantment().effectId) {
							return true;
						}
					}
				} else if (chkUpg.getEnchantment() == null) {
					return true;
				}
			}
		}
		return false;
	}
}
