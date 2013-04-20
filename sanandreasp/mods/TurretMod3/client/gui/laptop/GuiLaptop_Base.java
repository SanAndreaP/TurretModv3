package sanandreasp.mods.TurretMod3.client.gui.laptop;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.opengl.GL11;

import sanandreasp.mods.TurretMod3.client.gui.GuiItemTab;
import sanandreasp.mods.TurretMod3.client.gui.TCU.GuiTCUBase;
import sanandreasp.mods.TurretMod3.client.gui.TCU.GuiTCUInfo;
import sanandreasp.mods.TurretMod3.client.gui.TCU.GuiTCUSettings;
import sanandreasp.mods.TurretMod3.client.gui.TCU.GuiTCUTargets;
import sanandreasp.mods.TurretMod3.client.gui.TCU.GuiTCUUpgrades;
import sanandreasp.mods.TurretMod3.inventory.ContainerLaptop;
import sanandreasp.mods.TurretMod3.packet.PacketRecvLaptopGUICng;
import sanandreasp.mods.TurretMod3.registry.TM3ModRegistry;
import sanandreasp.mods.TurretMod3.tileentity.TileEntityLaptop;
import sanandreasp.mods.managers.SAP_LanguageManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityDispenser;
import net.minecraft.util.StatCollector;

public class GuiLaptop_Base extends GuiContainer {
	protected TileEntityLaptop laptop;
	protected int site = 1;
	protected static SAP_LanguageManager langman = TM3ModRegistry.manHelper.getLangMan();
    protected GuiButton programTurret;
    
    protected GuiButton tabGeneral;
    protected GuiButton tabTargets;
    protected GuiButton tabUpgrades;
    protected GuiButton tabMisc;
    
    private GuiButton selectedButton = null;
    
    protected List<GuiButton> buttonList2 = new ArrayList<GuiButton>();

	public GuiLaptop_Base(Container lapContainer, TileEntityLaptop par2TileEntityLaptop) {
		super(lapContainer);
		this.laptop = par2TileEntityLaptop;
		this.xSize = 256;
		this.ySize = 219;
		this.allowUserInput = true;
	}
	
	@Override
	public void initGui() {
		super.initGui();
		
		programTurret = new GuiItemTab(buttonList2.size(), this.guiLeft - 23, this.guiTop + this.ySize - 32, new ItemStack(TM3ModRegistry.tcu), langman.getTranslated("turretmod3.gui.laptop.btnprog"), false);
		buttonList2.add(programTurret);
        tabGeneral = new GuiItemTab(buttonList2.size(), this.guiLeft - 23, this.guiTop + 10, new ItemStack(Item.redstone), langman.getTranslated("turretmod3.gui.laptop.btngenrl"), false);
        buttonList2.add(tabGeneral);
        tabTargets = new GuiItemTab(buttonList2.size(), this.guiLeft - 23, this.guiTop + 36, new ItemStack(Item.swordDiamond), langman.getTranslated("turretmod3.gui.tcu.btntarg"), false);
        buttonList2.add(tabTargets);
        tabUpgrades = new GuiItemTab(buttonList2.size(), this.guiLeft - 23, this.guiTop + 62, new ItemStack(Item.saddle), langman.getTranslated("turretmod3.gui.tinfo.btnupgd"), false);
        buttonList2.add(tabUpgrades);
        tabMisc = new GuiItemTab(buttonList2.size(), this.guiLeft - 23, this.guiTop + 88, new ItemStack(Item.sign), langman.getTranslated("turretmod3.gui.tcu.misc"), false);
        buttonList2.add(tabMisc);
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int par1, int par2) {
		RenderHelper.disableStandardItemLighting();
        this.fontRenderer.drawString(StatCollector.translateToLocal("container.inventory"), 48, this.ySize - 92, 0x808080);
        RenderHelper.enableGUIStandardItemLighting();
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int i, int j) {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.renderEngine.bindTexture(TM3ModRegistry.TEX_GUILAP + "page_" + Integer.toString(site) + ".png");
        int k = (this.width - this.xSize) / 2;
        int l = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
	}
	
	@Override
	public void drawScreen(int par1, int par2, float par3) {
		super.drawScreen(par1, par2, par3);
		RenderHelper.disableStandardItemLighting();
        for (int k = 0; k < this.buttonList2.size(); ++k)
        {
            GuiButton guibutton = (GuiButton)this.buttonList2.get(k);
            guibutton.drawButton(this.mc, par1, par2);
        }
	}

	@Override
    protected void mouseClicked(int par1, int par2, int par3)
    {
		super.mouseClicked(par1, par2, par3);
        if (par3 == 0)
        {
            for (int l = 0; l < this.buttonList2.size(); ++l)
            {
                GuiButton guibutton = (GuiButton)this.buttonList2.get(l);

                if (guibutton.mousePressed(this.mc, par1, par2))
                {
                    this.selectedButton = guibutton;
                    this.mc.sndManager.playSoundFX("random.click", 1.0F, 1.0F);
                    this.actionPerformed(guibutton);
                }
            }
        }
    }
	
	@Override
    protected void mouseMovedOrUp(int par1, int par2, int par3)
    {
		super.mouseMovedOrUp(par1, par2, par3);
        if (this.selectedButton != null && par3 == 0)
        {
            this.selectedButton.mouseReleased(par1, par2);
            this.selectedButton = null;
        }
    }
	
	@Override
	public void setWorldAndResolution(Minecraft par1Minecraft, int par2, int par3) {
		buttonList2.clear();
		super.setWorldAndResolution(par1Minecraft, par2, par3);
	}
	
	@Override
	protected void actionPerformed(GuiButton par1GuiButton) {
		GuiLaptop_Base gui = null;
		if(par1GuiButton.id == tabGeneral.id) {
			PacketRecvLaptopGUICng.sendServer(3, this.laptop);
		} else if(par1GuiButton.id == tabTargets.id) {
			PacketRecvLaptopGUICng.sendServer(4, this.laptop);
		} else if(par1GuiButton.id == tabUpgrades.id) {
			PacketRecvLaptopGUICng.sendServer(5, this.laptop);
		} else if(par1GuiButton.id == tabMisc.id) {
			PacketRecvLaptopGUICng.sendServer(6, this.laptop);
		}
	}

}
