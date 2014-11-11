package sanandreasp.mods.TurretMod3.item;

import java.util.Map;

import com.google.common.collect.Maps;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;

public class ItemIconCache extends Item {
	
	@SideOnly(Side.CLIENT)
	private IIcon[] itemIcons;
	
	public static Map<Integer, String> iconList = Maps.newHashMap();

	public ItemIconCache() {
		super();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIconFromDamage(int par1) {
		return par1 < 0 || par1 >= iconList.size() ? this.itemIcons[0] : this.itemIcons[par1];
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister par1IconRegister) {
		this.itemIcons = new IIcon[iconList.size()];
		for (int i = 0; i < iconList.size(); i++) {
			this.itemIcons[i] = par1IconRegister.registerIcon(iconList.get(i));
		}
	}
}
