package sanandreasp.mods.TurretMod3.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.ItemRecord;

public class ItemTMDisc extends ItemRecord {
	private final String author;
	
	public ItemTMDisc(String par2Str, String par3Str) {
		super(par2Str);
		this.author = par3Str;
	}

	@Override
    public String getRecordTitle()
    {
        return this.author + " - " + this.recordName;
    }

    @SideOnly(Side.CLIENT)
	@Override
    public void registerIcons(IIconRegister par1IconRegister)
    {
        this.itemIcon = par1IconRegister.registerIcon("TurretMod3:record_" + this.recordName);
    }
}
