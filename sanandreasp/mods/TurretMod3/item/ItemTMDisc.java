package sanandreasp.mods.TurretMod3.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.ItemRecord;

public class ItemTMDisc extends ItemRecord {
	private final String author;
	
	public ItemTMDisc(int par1, String par2Str, String par3Str) {
		super(par1, par2Str);
		this.author = par3Str;
	}

	@Override
    public String getRecordTitle()
    {
        return this.author + " - " + this.recordName;
    }

    @SideOnly(Side.CLIENT)
	@Override
    public void updateIcons(IconRegister par1IconRegister)
    {
        this.iconIndex = par1IconRegister.registerIcon("TurretMod3:record_" + this.recordName);
    }
}
