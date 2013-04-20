package sanandreasp.mods.TurretMod3.item;

import java.util.List;
import java.util.Map;

import com.google.common.collect.Maps;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;

public class ItemAmmunitions extends Item {
	
	public static Map<Integer, String> dmgLangMapping = Maps.newHashMap();
	public static Map<Integer, String> dmgNameMapping = Maps.newHashMap();
	private static Map<Integer, Icon> dmgIconMapping = Maps.newHashMap();

	public ItemAmmunitions(int par1) {
		super(par1);
		this.setMaxDamage(0);
		this.setHasSubtypes(true);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Icon getIconFromDamage(int par1) {
		return par1 < 0 || par1 >= dmgIconMapping.size() ? super.getIconFromDamage(par1) : dmgIconMapping.get(par1);
	}
	
	@Override
	public String getUnlocalizedName(ItemStack par1ItemStack) {
		int dmg = par1ItemStack.getItemDamage();
		if(dmg >= dmgLangMapping.size() || dmg < 0)
			return super.getUnlocalizedName(par1ItemStack);
		else
			return "tm3." + dmgLangMapping.get(dmg);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(int par1, CreativeTabs par2CreativeTabs, List par3List) {
		for(int id : dmgLangMapping.keySet())
			par3List.add(new ItemStack(this.itemID, 1, id));
	}
	
	public static void addAmmoItem(int dmg, String langStr, String name) {
		dmgLangMapping.put(dmg, langStr);
		dmgNameMapping.put(dmg, name);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void updateIcons(IconRegister par1IconRegister) {
		for(int i = 0; i < dmgLangMapping.size(); i++) {
			dmgIconMapping.put(i, par1IconRegister.registerIcon("TurretMod3:"+dmgLangMapping.get(i)));
		}
	}
}
