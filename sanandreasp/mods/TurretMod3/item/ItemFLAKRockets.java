package sanandreasp.mods.TurretMod3.item;

import java.util.List;

import sanandreasp.mods.TurretMod3.registry.TM3ModRegistry;
import sanandreasp.mods.managers.SAP_LanguageManager;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;

public class ItemFLAKRockets extends Item {
	
	@SideOnly(Side.CLIENT)
	private Icon[] itemIcons;

	public ItemFLAKRockets(int par1) {
		super(par1);
		setMaxDamage(0);
		setHasSubtypes(true);
	}
	
	@Override
	public String getUnlocalizedName(ItemStack par1ItemStack) {
		return this.getUnlocalizedName() + par1ItemStack.getItemDamage();
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public Icon getIconFromDamage(int par1) {
		return par1 < 0 || par1 >= 6 ? this.itemIcons[0] : this.itemIcons[par1];
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
		String rng = (TM3ModRegistry.manHelper.getLangMan()).getTranslated("turretmod3.item.rocketRng") + ": ";
		switch(par1ItemStack.getItemDamage()) {
			case 0:
			case 3:
				rng += "50 " + (TM3ModRegistry.manHelper.getLangMan()).getTranslated("turretmod3.gui.tinfo.blocks");
				break;
			case 1:
			case 4:
				rng += "75 " + (TM3ModRegistry.manHelper.getLangMan()).getTranslated("turretmod3.gui.tinfo.blocks");
				break;
			case 2:
			case 5:
				rng += "100 " + (TM3ModRegistry.manHelper.getLangMan()).getTranslated("turretmod3.gui.tinfo.blocks");
				break;
			default:
				rng += "N/A";
		}
		par3List.add(rng);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(int par1, CreativeTabs par2CreativeTabs, List par3List) {
		for(int i = 0; i < 6; i++) {
			par3List.add(new ItemStack(this, 1, i));
		}
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void updateIcons(IconRegister par1IconRegister) {
		this.itemIcons = new Icon[6];
		this.itemIcons[0] = par1IconRegister.registerIcon("TurretMod3:rktTB50");
		this.itemIcons[1] = par1IconRegister.registerIcon("TurretMod3:rktTB100");
		this.itemIcons[2] = par1IconRegister.registerIcon("TurretMod3:rktTB150");
		this.itemIcons[3] = par1IconRegister.registerIcon("TurretMod3:rktTG50");
		this.itemIcons[4] = par1IconRegister.registerIcon("TurretMod3:rktTG100");
		this.itemIcons[5] = par1IconRegister.registerIcon("TurretMod3:rktTG150");
	}

}
