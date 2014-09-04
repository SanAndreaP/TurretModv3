package sanandreasp.mods.TurretMod3.client.gui.laptop;

import static sanandreasp.mods.TurretMod3.registry.TurretTargetRegistry.trTargets;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.bouncycastle.util.test.NumberParsing;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

import com.google.common.collect.Maps;

import cpw.mods.fml.common.network.PacketDispatcher;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.entity.EntityList;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.network.packet.Packet250CustomPayload;
import sanandreasp.mods.TurretMod3.client.gui.GuiTurretButton;
import sanandreasp.mods.TurretMod3.item.ItemTurret;
import sanandreasp.mods.TurretMod3.packet.PacketHandlerCommon;
import sanandreasp.mods.TurretMod3.packet.PacketRecvLaptopTargets;
import sanandreasp.mods.TurretMod3.packet.PacketRecvTargetListSrv;
import sanandreasp.mods.TurretMod3.registry.TM3ModRegistry;
import sanandreasp.mods.TurretMod3.registry.TurretTargetRegistry;
import sanandreasp.mods.TurretMod3.tileentity.TileEntityLaptop;

public class GuiLaptopMisc extends GuiLaptop_Base {
	private GuiTextField frequency;
	private GuiTextField customName;
	
	public GuiLaptopMisc(Container lapContainer, TileEntityLaptop par2TileEntityLaptop) {
		super(lapContainer, par2TileEntityLaptop);
		this.site = 1;
	}

	@Override
	public void initGui() {
		super.initGui();
		this.tabMisc.enabled = false;
		
		this.frequency = new GuiTextField(this.fontRenderer, this.guiLeft + (this.xSize-150)/2, this.guiTop + 40, 150, 10);
		this.frequency.setText("0");
		this.customName = new GuiTextField(this.fontRenderer, this.guiLeft + (this.xSize-150)/2, this.guiTop + 70, 150, 10);
		this.customName.setText("");
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int par1, int par2) {
		super.drawGuiContainerForegroundLayer(par1, par2);
	}
	
	@Override
	public void drawScreen(int par1, int par2, float par3) {
		super.drawScreen(par1, par2, par3);
		
        this.fontRenderer.drawString(langman.getTranslated("turretmod3.gui.laptop.titMisc"), this.guiLeft + 6, this.guiTop + 6, 0x808080);
		
		String s = langman.getTranslated("turretmod3.gui.tcu.frequency");
		this.fontRenderer.drawString(s, this.guiLeft + 40, this.guiTop + 30, 0x606060);
		s = langman.getTranslated("turretmod3.gui.laptop.customName");
		this.fontRenderer.drawString(s, this.guiLeft + 40, this.guiTop + 60, 0x606060);
		
		this.frequency.drawTextBox();
		this.customName.drawTextBox();
	}
	
    @Override
	public void updateScreen()
    {
    	super.updateScreen();
    	this.frequency.updateCursorCounter();
    	this.customName.updateCursorCounter();
    }
    
    @Override
	protected void mouseClicked(int par1, int par2, int par3)
    {
        super.mouseClicked(par1, par2, par3);
        this.frequency.mouseClicked(par1, par2, par3);
        this.customName.mouseClicked(par1, par2, par3);
    }
	
    @Override
	protected void keyTyped(char par1, int par2)
    {
    	this.frequency.textboxKeyTyped(par1, par2);
    	this.customName.textboxKeyTyped(par1, par2);

    	if ((par2 == 28 || par2 == 1) && (this.frequency.isFocused() || this.customName.isFocused())) {
    		this.frequency.setFocused(false);
    		this.customName.setFocused(false);
    	}
    	else if ((par2 == 1 || par2 == this.mc.gameSettings.keyBindInventory.keyCode) && !this.frequency.isFocused() && !this.customName.isFocused()) {
    		this.mc.thePlayer.closeScreen();
    	}
    }
	
	@Override
	protected void actionPerformed(GuiButton par1GuiButton) {
		super.actionPerformed(par1GuiButton);
		if (par1GuiButton.id == this.programTurret.id) {
			ByteArrayOutputStream b = new ByteArrayOutputStream();
			try {
				DataOutputStream o = new DataOutputStream(b);
				o.writeInt(0x008);
				o.writeInt(this.laptop.xCoord);
				o.writeInt(this.laptop.yCoord);
				o.writeInt(this.laptop.zCoord);
				o.writeUTF(this.customName.getText());
				o.writeShort(Short.valueOf(this.frequency.getText()));
				
				PacketDispatcher.sendPacketToServer(new Packet250CustomPayload(PacketHandlerCommon.getChannel(), b.toByteArray()));
			} catch (IOException ex) {
				ex.printStackTrace();
			} catch (NumberFormatException ex) {
				;
			}
        	this.inventorySlots.detectAndSendChanges();
		}
	}
}
