package sanandreasp.mods.TurretMod3.client.gui.turretInfo;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import sanandreasp.mods.TurretMod3.registry.TM3ModRegistry;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.item.ItemStack;

public class GuiTabNav extends GuiButton {
	
	protected ItemStack renderedItem;
    
    protected boolean isUp;

	public GuiTabNav(int par1, int par2, int par3, boolean par6) {
		super(par1, par2, par3, "");
		this.width = 23;
		this.height = 13;
		this.isUp = par6;
	}

    @Override
    public void drawButton(Minecraft par1Minecraft, int par2, int par3)
    {
        if (this.visible)
        {
        	FontRenderer var4 = par1Minecraft.fontRenderer;
            GL11.glBindTexture(GL11.GL_TEXTURE_2D, par1Minecraft.renderEngine.getTexture(TM3ModRegistry.TEX_GUIBUTTONS));
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            this.field_82253_i = par2 >= this.xPosition && par3 >= this.yPosition && par2 < this.xPosition + this.width && par3 < this.yPosition + this.height;
            int var5 = this.getHoverState(this.field_82253_i);
            this.drawTexturedModalRect(this.xPosition, this.yPosition, 52+23*(isUp?0:1), var5*13, this.width, this.height);
            this.mouseDragged(par1Minecraft, par2, par3);
        }
    }

}
