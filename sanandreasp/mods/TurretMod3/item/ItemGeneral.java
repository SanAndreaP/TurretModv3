package sanandreasp.mods.TurretMod3.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemGeneral extends Item {
	
	private final String fileName;

	public ItemGeneral(int par1, String name) {
		super(par1);
		this.fileName = name;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void updateIcons(IconRegister par1IconRegister) {
		this.iconIndex = par1IconRegister.registerIcon(fileName);
	}
}
