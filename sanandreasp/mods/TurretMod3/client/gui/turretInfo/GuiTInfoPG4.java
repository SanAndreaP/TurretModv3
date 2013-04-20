package sanandreasp.mods.TurretMod3.client.gui.turretInfo;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import com.google.common.collect.Maps;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

import sanandreasp.mods.TurretMod3.registry.TM3ModRegistry;
import sanandreasp.mods.TurretMod3.registry.TurretUpgrades.TurretUpgrades;

public class GuiTInfoPG4 extends GuiTInfoBase {
    protected static RenderItem itemRenderer = new RenderItem();
    protected FontRenderer customFR;

	private Map<Integer, TurretUpgrades> upgrades = Maps.newHashMap();
	
	private int entryPos = 0;
	private boolean isScrolling = false;
	private float currScrollPos = 0F;
	
	public GuiTInfoPG4(int pg) {
		this.allowUserInput = true;
		this.site = pg;
	}
	
	@Override
	public void initGui() {
		super.initGui();
        this.upgrades.clear();
		for(int i = 0; i < TurretUpgrades.getUpgradeCount(); i++) {
			WeakReference<TurretUpgrades> upg = new WeakReference<TurretUpgrades>(TurretUpgrades.getUpgradeFromID(i));
			for(Class cls : upg.get().getTurrets()) {
				if(cls.isAssignableFrom(this.turretCls)) {
					this.upgrades.put(this.upgrades.size(), upg.get());
					break;
				}
			}
		}
		
        this.tabTurretUpgrades.enabled = false;
        
        this.customFR = new FontRenderer(this.mc.gameSettings, "/font/default.png", this.mc.renderEngine, true);
        
        this.entryPos = 0;
        this.currScrollPos = 0;
        this.isScrolling = false;
	}
	
	@Override
	public void drawScreen(int par1, int par2, float par3) {
        this.drawDefaultBackground();

		this.mc.renderEngine.bindTexture(TM3ModRegistry.TEX_GUIINFO + "page_4.png");
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, xSize, ySize);
        
        String str = langman.getTranslated("turretmod3.gui.tinfo.titpg4");
        this.fontRenderer.drawString(str, this.guiLeft + (this.xSize - this.fontRenderer.getStringWidth(str))/2, this.guiTop + 6, 0x808080);
        
        str = this.turretInf.getTurretName();
        this.fontRenderer.drawString(str, this.guiLeft + (this.xSize - this.fontRenderer.getStringWidth(str))/2, this.guiTop + 21, 0x00FF00);

		this.mc.renderEngine.bindTexture(TM3ModRegistry.TEX_GUIINFO + "page_4.png");
        int scrollX = 163;
        int scrollY = 49 + (int)(69F * currScrollPos);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        drawTexturedModalRect(scrollX + this.guiLeft, scrollY + this.guiTop, 176,  this.upgrades.size() > 4 ? 0 : 6, 6, 6);
        
        int hover = -1;
        int hovY = 0;

        for(int i = this.entryPos; i < 4 + entryPos && i < this.upgrades.size(); i++) {
        	if(this.upgrades.get(i) != null) {
	        	int icnX = this.guiLeft + 8;
	        	int icnY = this.guiTop + 50 + (i-entryPos)*19;
	        	RenderHelper.enableGUIStandardItemLighting();
	        	GL11.glEnable(GL12.GL_RESCALE_NORMAL);
	        	ItemStack renderedItem = this.upgrades.get(i).getItem().copy();
	        	if(renderedItem.getItemDamage() == OreDictionary.WILDCARD_VALUE) renderedItem.setItemDamage(0);
	        	if(this.upgrades.get(i).getEnchantment() != null) renderedItem.addEnchantment(this.upgrades.get(i).getEnchantment(), 1);
	        	this.drawItemStack(renderedItem, icnX, icnY);
	        	RenderHelper.disableStandardItemLighting();
	        	this.fontRenderer.drawString(this.upgrades.get(i).getName(), this.guiLeft + 26, this.guiTop + 54 + (i-entryPos)*19, 0xFFFFFF);
	        	
	        	if(par1 >= this.guiLeft + 7 && par1 < this.guiLeft + 159 && par2 >= this.guiTop + 49 + (i-entryPos)*19 && par2 < this.guiTop + 67 + (i-entryPos)*19) {
	        		this.drawRect(this.guiLeft + 7, this.guiTop + 49 + (i-entryPos)*19, this.guiLeft + 159, this.guiTop + 67 + (i-entryPos)*19, 0x3000A0FF);
	        		this.customFR.drawSplitString(this.upgrades.get(i).getDesc(), this.guiLeft + 11, this.guiTop + 131, 154, 0x000000);
	        		hover = i;
	        		hovY = this.guiTop + 49 + (i-entryPos)*19;
	        	}
        	}
        }
        
        if(hover < 0) {
    		this.customFR.drawSplitString(langman.getTranslated("turretmod3.gui.tinfo.hoverPG4"), this.guiLeft + 11, this.guiTop + 131, 154, 0x808080);
        }
        
        boolean var4 = Mouse.isButtonDown(0);
        
        int scrollMinX = this.guiLeft + 163;
        int scrollMaxX = scrollMinX + 6;
        int scrollMinY = this.guiTop + 49;
        int scrollMaxY = scrollMinY + 75;
        
        if(!this.isScrolling && var4 && par1 > scrollMinX && par1 < scrollMaxX && par2 > scrollMinY && par2 < scrollMaxY && this.upgrades.size() > 4) {
        	this.isScrolling = true;
        } else if(!var4) {
        	this.isScrolling = false;
        }
        
        if(this.isScrolling) {
        	int sY = (int) (69F / (float)(this.upgrades.size() - 4));
	        for(int y = 0; y < this.upgrades.size() - 3; y++) {
	        	if(par2 > sY * y + this.guiTop + 49 || par1 < sY * y + this.guiTop + 49) {
	        		this.entryPos = y;
	        	}
	        }
	        this.currScrollPos = ((float)(par2 - scrollMinY - 4) / 69F);
        }
        
        if(this.currScrollPos < 0.0F)
        	this.currScrollPos = 0.0F;
        if(this.currScrollPos > 1.0F)
        	this.currScrollPos = 1.0F;
        
		super.drawScreen(par1, par2, par3);
		
		if(hover > -1) {
    		drawUpgradeTooltip(this.upgrades.get(hover).getItem().getDisplayName(),
    				this.upgrades.get(hover).getEnchantment() != null ? this.upgrades.get(hover).getEnchantment().getTranslatedName(1) : "",
    				this.upgrades.get(hover).getReqUpgradeName(), par1, par2);
		}
	}

    private void drawItemStack(ItemStack par1ItemStack, int par2, int par3)
    {
        GL11.glTranslatef(0.0F, 0.0F, 32.0F);
        this.zLevel = 200.0F;
        itemRenderer.zLevel = 200.0F;
        itemRenderer.renderItemAndEffectIntoGUI(this.fontRenderer, this.mc.renderEngine, par1ItemStack, par2, par3);
        itemRenderer.renderItemOverlayIntoGUI(this.fontRenderer, this.mc.renderEngine, par1ItemStack, par2, par3);
        this.zLevel = 0.0F;
        itemRenderer.zLevel = 0.0F;
    }
    
    
	
	public void handleMouseInput()
    {
        super.handleMouseInput();
        int var1 = Mouse.getEventDWheel();
        
        if (var1 != 0 && this.upgrades.size() > 4)
        {
            if (var1 < 0)
            {
                this.entryPos = Math.min(this.entryPos + 1, this.upgrades.size() - 4);
    	        this.currScrollPos = (float)this.entryPos / ((float)(this.upgrades.size() - 4));
            }

            if (var1 > 0)
            {
            	this.entryPos = Math.max(this.entryPos - 1, 0);
    	        this.currScrollPos = (float)this.entryPos / ((float)(this.upgrades.size() - 4));
            }
        }
    }
	
	protected void drawUpgradeTooltip(String itemName, String ench, String required, int par2, int par3)
    {
        GL11.glDisable(GL11.GL_DEPTH_TEST);
        List<String> var4 = new ArrayList<String>();
        
    	String s[] = this.langman.getTranslated("turretmod3.gui.tinfo.upgToolTip").split("\\|");
    	
        var4.add(s[1] + ": " + itemName);
        if(ench.length() > 0) var4.add("  " + ench);
        if(required.length() > 0) var4.add(s[2] + ": " + required);

        if (!var4.isEmpty())
        {
            int var5 = 0;
            int var6;
            int var7;

            for (var6 = 0; var6 < var4.size(); ++var6)
            {
                var7 = this.fontRenderer.getStringWidth(var4.get(var6));

                if (var7 > var5)
                {
                    var5 = var7;
                }
            }

            var6 = par2 + 12;
            var7 = par3 - 12;
            int var9 = 8;

            if (var4.size() > 1)
            {
                var9 += (var4.size() - 1) * 10;
            }

            if (this.guiTop + var7 + var9 + 6 > this.height)
            {
                var7 = this.height - var9 - this.guiTop - 6;
            }

            this.zLevel = 300.0F;
            itemRenderer.zLevel = 300.0F;
            int var10 = -267386864;
            this.drawGradientRect(var6 - 3, var7 - 4, var6 + var5 + 3, var7 - 3, var10, var10);
            this.drawGradientRect(var6 - 3, var7 + var9 + 3, var6 + var5 + 3, var7 + var9 + 4, var10, var10);
            this.drawGradientRect(var6 - 3, var7 - 3, var6 + var5 + 3, var7 + var9 + 3, var10, var10);
            this.drawGradientRect(var6 - 4, var7 - 3, var6 - 3, var7 + var9 + 3, var10, var10);
            this.drawGradientRect(var6 + var5 + 3, var7 - 3, var6 + var5 + 4, var7 + var9 + 3, var10, var10);
            int var11 = 1347420415;
            int var12 = (var11 & 16711422) >> 1 | var11 & -16777216;
            this.drawGradientRect(var6 - 3, var7 - 3 + 1, var6 - 3 + 1, var7 + var9 + 3 - 1, var11, var12);
            this.drawGradientRect(var6 + var5 + 2, var7 - 3 + 1, var6 + var5 + 3, var7 + var9 + 3 - 1, var11, var12);
            this.drawGradientRect(var6 - 3, var7 - 3, var6 + var5 + 3, var7 - 3 + 1, var11, var11);
            this.drawGradientRect(var6 - 3, var7 + var9 + 2, var6 + var5 + 3, var7 + var9 + 3, var12, var12);

            this.fontRenderer.drawStringWithShadow(var4.get(0), var6, var7, 0xFFFF33);
            if(ench.length() > 0) this.fontRenderer.drawStringWithShadow(var4.get(1), var6, var7+=10, 0xAAAA33);
            if(required.length() > 0) {
            	if(ench.length() > 0)
            		this.fontRenderer.drawStringWithShadow(var4.get(2), var6, var7+=10, 0x33FFFF);
            	else
            		this.fontRenderer.drawStringWithShadow(var4.get(1), var6, var7+=10, 0x33FFFF);
            }

            this.zLevel = 0.0F;
            itemRenderer.zLevel = 0.0F;
        }
    }

}
