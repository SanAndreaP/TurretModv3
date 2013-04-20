package sanandreasp.mods.TurretMod3.client.gui.turretInfo;

import org.lwjgl.opengl.GL11;

import sanandreasp.mods.TurretMod3.client.gui.GuiItemTab;
import sanandreasp.mods.TurretMod3.registry.TM3ModRegistry;
import sanandreasp.mods.TurretMod3.registry.TurretInfo.TurretInfo;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class GuiTInfoPG2 extends GuiTInfoBase {

	public GuiTInfoPG2(int pg) {
		this.allowUserInput = true;
		this.site = pg;
	}
	
	@Override
	public void initGui() {
		super.initGui();
        tabTurretValues.enabled = false;
	}
	
	@Override
	public void drawScreen(int par1, int par2, float par3) {
        this.drawDefaultBackground();
        
		this.mc.renderEngine.bindTexture(TM3ModRegistry.TEX_GUIINFO + "page_2.png");
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, xSize, ySize);
        
        String str = langman.getTranslated("turretmod3.gui.tinfo.titpg2");
        this.fontRenderer.drawString(str, this.guiLeft + (this.xSize - this.fontRenderer.getStringWidth(str))/2, this.guiTop + 6, 0x808080);
        
        str = this.turretInf.getTurretName();        
        this.fontRenderer.drawString(str, this.guiLeft + (this.xSize - this.fontRenderer.getStringWidth(str))/2, this.guiTop + 21, 0x00FF00);
        
        int icoX = this.guiLeft + 7;
        int icoY = this.guiTop + 131;
        
        String title = "", value = "", desc = "";
        
        if(par1 >= icoX && par1 < icoX + 16 && par2 >= icoY && par2 < icoY + 16) {
        	this.drawRect(icoX, icoY, icoX + 16, icoY + 16, 0x80FFFFFF);
        	title = langman.getTranslated("turretmod3.gui.tinfo.nameMaxAmmo");
        	value = ((Integer)this.turretInf.getMaxAmmo()).toString();
        	desc = langman.getTranslated("turretmod3.gui.tinfo.descMaxAmmo");
        } else if(par1 >= icoX + 18 && par1 < icoX + 34 && par2 >= icoY && par2 < icoY + 16) {
        	this.drawRect(icoX + 18, icoY, icoX + 34, icoY + 16, 0x80FFFFFF);
        	title = langman.getTranslated("turretmod3.gui.tinfo.nameMaxHealth");
        	value = ((Integer)this.turretInf.getMaxHealth()).toString() + " HP";
        	desc = langman.getTranslated("turretmod3.gui.tinfo.descMaxHealth");
        } else if(par1 >= icoX + 36 && par1 < icoX + 52 && par2 >= icoY && par2 < icoY + 16) {
        	this.drawRect(icoX + 36, icoY, icoX + 52, icoY + 16, 0x80FFFFFF);
        	title = langman.getTranslated("turretmod3.gui.tinfo.nameUpperRangeY");
        	value = new Integer((int)Math.floor(this.turretInf.getYRangeHigh())).toString() + " " + langman.getTranslated("turretmod3.gui.tinfo.blocks");
        	desc = langman.getTranslated("turretmod3.gui.tinfo.descUpperRangeY");
        } else if(par1 >= icoX + 54 && par1 < icoX + 70 && par2 >= icoY && par2 < icoY + 16) {
        	this.drawRect(icoX + 54, icoY, icoX + 70, icoY + 16, 0x80FFFFFF);
        	title = langman.getTranslated("turretmod3.gui.tinfo.nameLowerRangeY");
        	value = new Integer((int)Math.floor(this.turretInf.getYRangeLow())).toString() + " " + langman.getTranslated("turretmod3.gui.tinfo.blocks");
        	desc = langman.getTranslated("turretmod3.gui.tinfo.descLowerRangeY");
        } else if(par1 >= icoX + 72 && par1 < icoX + 88 && par2 >= icoY && par2 < icoY + 16) {
        	this.drawRect(icoX + 72, icoY, icoX + 88, icoY + 16, 0x80FFFFFF);
        	title = langman.getTranslated("turretmod3.gui.tinfo.nameRangeX");
        	value = new Integer((int)Math.floor(this.turretInf.getXRange())).toString() + " " + langman.getTranslated("turretmod3.gui.tinfo.blocks");
        	desc = langman.getTranslated("turretmod3.gui.tinfo.descRangeX");
        } else if(par1 >= icoX + 90 && par1 < icoX + 106 && par2 >= icoY && par2 < icoY + 16) {
        	this.drawRect(icoX + 90, icoY, icoX + 106, icoY + 16, 0x80FFFFFF);
        	title = langman.getTranslated("turretmod3.gui.tinfo.nameDamage");
        	value = this.turretInf.getDamage() + " HP";
        	desc = langman.getTranslated("turretmod3.gui.tinfo.descDamage");
        } else if(par1 >= icoX + 108 && par1 < icoX + 124 && par2 >= icoY && par2 < icoY + 16) {
        	this.drawRect(icoX + 108, icoY, icoX + 124, icoY + 16, 0x80FFFFFF);
        	title = langman.getTranslated("turretmod3.gui.tinfo.nameExp");
        	value = this.turretInf.getMaxXP() + " XP";
        	desc = langman.getTranslated("turretmod3.gui.tinfo.descExp");
        } else {
        	this.customFR.drawSplitString(langman.getTranslated("turretmod3.gui.tinfo.hoverPG2"), this.guiLeft + 11, this.guiTop + 153, 157, 0x808080);
        }
        
    	this.fontRenderer.drawString(title, this.guiLeft + 10, this.guiTop + 153, 0x006000);
    	this.fontRenderer.drawString(value, this.guiLeft + 15, this.guiTop + 162, 0x000000);
    	this.customFR.drawSplitString(desc, this.guiLeft + 11, this.guiTop + 171, 157, 0x606060);

		this.mc.renderEngine.bindTexture(TM3ModRegistry.TEX_GUIINFO + "page_2.png");
        GL11.glColor4f(1.0F, 0.3F, 0.3F, 1.0F);
        drawTexturedModalRect(this.guiLeft + 136, this.guiTop + 51, 176, 32, 16, 16);
        drawTexturedModalRect(this.guiLeft + 43, this.guiTop + 131, 176, 32, 16, 16);
        GL11.glColor4f(0.3F, 1.0F, 0.3F, 1.0F);
        drawTexturedModalRect(this.guiLeft + 136, this.guiTop + 86, 176, 32, 16, 16);
        drawTexturedModalRect(this.guiLeft + 61, this.guiTop + 131, 176, 32, 16, 16);
        GL11.glColor4f(1.0F, 1.0F, 0.3F, 1.0F);
        drawTexturedModalRect(this.guiLeft + 72, this.guiTop + 105, 176, 32, 16, 16);
        drawTexturedModalRect(this.guiLeft + 79, this.guiTop + 131, 176, 32, 16, 16);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        drawTexturedModalRect(this.guiLeft + 7, this.guiTop + 131, 176, 0, 16, 16);
        drawTexturedModalRect(this.guiLeft + 25, this.guiTop + 131, 176, 16, 16, 16);
        drawTexturedModalRect(this.guiLeft + 97, this.guiTop + 131, 176, 48, 16, 16);
        drawTexturedModalRect(this.guiLeft + 115, this.guiTop + 131, 176, 64, 16, 16);
        
		super.drawScreen(par1, par2, par3);
	}

}
