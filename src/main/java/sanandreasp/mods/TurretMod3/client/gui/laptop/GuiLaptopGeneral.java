package sanandreasp.mods.TurretMod3.client.gui.laptop;

import static sanandreasp.mods.TurretMod3.registry.TurretTargetRegistry.trTargets;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.minecraft.util.StatCollector;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

import com.google.common.collect.Maps;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.entity.EntityList;
import net.minecraft.inventory.Container;
import sanandreasp.mods.TurretMod3.client.gui.GuiTurretButton;
import sanandreasp.mods.TurretMod3.packet.PacketHandlerCommon;
import sanandreasp.mods.TurretMod3.packet.PacketRecvLaptopTargets;
import sanandreasp.mods.TurretMod3.packet.PacketRecvTargetListSrv;
import sanandreasp.mods.TurretMod3.registry.TM3ModRegistry;
import sanandreasp.mods.TurretMod3.registry.TurretTargetRegistry;
import sanandreasp.mods.TurretMod3.tileentity.TileEntityLaptop;

public class GuiLaptopGeneral extends GuiLaptop_Base {
	private GuiButton statLabel;
	private GuiButton chngTCCrosshair;
	private GuiButton activateTurret;
	private GuiButton deactivateTurret;
	private GuiButton resetTarget;
	private GuiButton uniqueTargetOn;
	private GuiButton uniqueTargetOff;
	private GuiTextField frequency;
	
	public GuiLaptopGeneral(Container lapContainer, TileEntityLaptop par2TileEntityLaptop) {
		super(lapContainer, par2TileEntityLaptop);
		this.site = 1;
	}

	@Override
	public void initGui() {
		super.initGui();
		this.tabGeneral.enabled = false;
		
		this.statLabel = new GuiTurretButton(buttonList2.size(), this.guiLeft + (this.xSize - 150) / 2, this.guiTop + 35, StatCollector.translateToLocal("gui.laptop.enableLabel").split("\\|")[0]);
		this.buttonList2.add(this.statLabel);
		this.chngTCCrosshair = new GuiTurretButton(buttonList2.size(), this.guiLeft + (this.xSize - 150) / 2, this.guiTop + 48, StatCollector.translateToLocal("gui.laptop.chngCrosshair"));
		this.buttonList2.add(this.chngTCCrosshair);
		this.activateTurret = new GuiTurretButton(buttonList2.size(), this.guiLeft + (this.xSize - 150) / 2, this.guiTop + 61, StatCollector.translateToLocal("gui.laptop.onTurret"));
		this.buttonList2.add(this.activateTurret);
		this.deactivateTurret = new GuiTurretButton(buttonList2.size(), this.guiLeft + (this.xSize - 150) / 2, this.guiTop + 74, StatCollector.translateToLocal("gui.laptop.offTurret"));
		this.buttonList2.add(this.deactivateTurret);
		this.resetTarget = new GuiTurretButton(buttonList2.size(), this.guiLeft + (this.xSize - 150) / 2, this.guiTop + 87, StatCollector.translateToLocal("gui.laptop.rstTargets"));
		this.buttonList2.add(this.resetTarget);
		this.uniqueTargetOn = new GuiTurretButton(buttonList2.size(), this.guiLeft + (this.xSize - 150) / 2, this.guiTop + 100, StatCollector.translateToLocal("gui.laptop.uniqueTgt").split("\\|")[0]);
		this.buttonList2.add(this.uniqueTargetOn);
		this.uniqueTargetOff = new GuiTurretButton(buttonList2.size(), this.guiLeft + (this.xSize - 150) / 2, this.guiTop + 113, StatCollector.translateToLocal("gui.laptop.uniqueTgt").split("\\|")[1]);
		this.buttonList2.add(this.uniqueTargetOff);
		
		this.frequency = new GuiTextField(this.fontRendererObj, this.guiLeft + 100, this.guiTop + 20, 40, 10);
		this.frequency.setText("-1");
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int par1, int par2) {
		super.drawGuiContainerForegroundLayer(par1, par2);
	}
	
	@Override
	public void drawScreen(int par1, int par2, float par3) {
		super.drawScreen(par1, par2, par3);
		
        this.fontRendererObj.drawString(StatCollector.translateToLocal("gui.laptop.titGeneral"), this.guiLeft + 6, this.guiTop + 6, 0x808080);
		
		boolean statEnabled = TM3ModRegistry.proxy.getPlayerTM3Data(this.mc.thePlayer).getBoolean("renderLabels");
		String split[] = StatCollector.translateToLocal("gui.laptop.enableLabel").split("\\|");
		this.statLabel.displayString = (!statEnabled ? split[1] : split[2]) + " " + split[0];
		
    	int icon = 0;
    	if (TM3ModRegistry.proxy.getPlayerTM3Data(this.mc.thePlayer).hasKey("tcCrosshair"))
    		icon = TM3ModRegistry.proxy.getPlayerTM3Data(this.mc.thePlayer).getByte("tcCrosshair");
    	int chX = this.guiLeft + (this.xSize - 150) / 2 + 159;
    	int chY = this.guiTop + 48;
        this.drawRect(chX, chY, chX + 11, chY + 11, 0xFF000000);
    	GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(TM3ModRegistry.TEX_TURRETCAM);
        this.drawTexturedModalRect(chX-2, chY-2, icon * 16, 40, 16, 16);
        
		String s = StatCollector.translateToLocal("gui.tcu.frequency");
		this.fontRendererObj.drawString(s, this.guiLeft + 40, this.guiTop + 22, 0x606060);
		
		this.frequency.drawTextBox();
	}
	
    @Override
	public void updateScreen()
    {
    	super.updateScreen();
    	this.frequency.updateCursorCounter();
    }
    
    @Override
	protected void mouseClicked(int par1, int par2, int par3)
    {
        super.mouseClicked(par1, par2, par3);
        this.frequency.mouseClicked(par1, par2, par3);
    }
	
    @Override
	protected void keyTyped(char par1, int par2)
    {
    	this.frequency.textboxKeyTyped(par1, par2);

    	if ((par2 == 28 || par2 == 1) && this.frequency.isFocused()) {
    		this.frequency.setFocused(false);
    	}
    	else if ((par2 == 1 || par2 == this.mc.gameSettings.keyBindInventory.getKeyCode()) && !this.frequency.isFocused()) {
    		this.mc.thePlayer.closeScreen();
    	}
    }
	
	@Override
	protected void actionPerformed(GuiButton par1GuiButton) {
		super.actionPerformed(par1GuiButton);
		try {
			if (par1GuiButton.id == this.programTurret.id) {
	        	this.inventorySlots.detectAndSendChanges();
			} else if (par1GuiButton.id == this.statLabel.id) {
		    	ByteArrayOutputStream b = new ByteArrayOutputStream();
				DataOutputStream o = new DataOutputStream(b);
		    	o.writeInt(0x006);
		    	o.writeInt(0x00);
		    	
		    	PacketDispatcher.sendPacketToServer(new Packet250CustomPayload(PacketHandlerCommon.getChannel(), b.toByteArray()));
			} else if (par1GuiButton.id == this.chngTCCrosshair.id) {
				ByteArrayOutputStream b = new ByteArrayOutputStream();
				DataOutputStream o = new DataOutputStream(b);
				o.writeInt(0x006);
				o.writeInt(0x01);
				
				PacketDispatcher.sendPacketToServer(new Packet250CustomPayload(PacketHandlerCommon.getChannel(), b.toByteArray()));
			} else if (par1GuiButton.id == this.activateTurret.id || par1GuiButton.id == this.deactivateTurret.id) {
				ByteArrayOutputStream b = new ByteArrayOutputStream();
				DataOutputStream o = new DataOutputStream(b);
				o.writeInt(0x006);
				o.writeInt(0x02);
				try {
					o.writeShort(Short.parseShort(this.frequency.getText()));
				} catch(NumberFormatException e) {
					o.writeShort(-2);
				}
				o.writeBoolean(par1GuiButton.id == this.activateTurret.id);
				
				PacketDispatcher.sendPacketToServer(new Packet250CustomPayload(PacketHandlerCommon.getChannel(), b.toByteArray()));
			} else if (par1GuiButton.id == this.resetTarget.id) {
				ByteArrayOutputStream b = new ByteArrayOutputStream();
				DataOutputStream o = new DataOutputStream(b);
				o.writeInt(0x006);
				o.writeInt(0x03);
				try {
					o.writeShort(Short.parseShort(this.frequency.getText()));
				} catch(NumberFormatException e) {
					o.writeShort(-2);
				}
				
				PacketDispatcher.sendPacketToServer(new Packet250CustomPayload(PacketHandlerCommon.getChannel(), b.toByteArray()));
			} else if (par1GuiButton.id == this.uniqueTargetOn.id || par1GuiButton.id == this.uniqueTargetOff.id) {
				ByteArrayOutputStream b = new ByteArrayOutputStream();
				DataOutputStream o = new DataOutputStream(b);
				o.writeInt(0x006);
				o.writeInt(0x04);
				try {
					o.writeShort(Short.parseShort(this.frequency.getText()));
				} catch(NumberFormatException e) {
					o.writeShort(-2);
				}
				o.writeBoolean(par1GuiButton.id == this.uniqueTargetOn.id);
				
				PacketDispatcher.sendPacketToServer(new Packet250CustomPayload(PacketHandlerCommon.getChannel(), b.toByteArray()));
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}
