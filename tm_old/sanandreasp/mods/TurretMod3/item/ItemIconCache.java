package sanandreasp.mods.TurretMod3.item;

import java.util.Map;

import com.google.common.collect.Maps;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.Item;
import net.minecraft.util.Icon;

public class ItemIconCache extends Item {
	
	@SideOnly(Side.CLIENT)
	private Icon[] itemIcons;
	
	public static Map<Integer, String> iconList = Maps.newHashMap();

	public ItemIconCache(int par1) {
		super(par1);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Icon getIconFromDamage(int par1) {
		return par1 < 0 || par1 >= iconList.size() ? this.itemIcons[0] : this.itemIcons[par1];
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister par1IconRegister) {
		this.itemIcons = new Icon[iconList.size()];
		for (int i = 0; i < iconList.size(); i++) {
			this.itemIcons[i] = par1IconRegister.registerIcon(iconList.get(i));
		}
	}
}
