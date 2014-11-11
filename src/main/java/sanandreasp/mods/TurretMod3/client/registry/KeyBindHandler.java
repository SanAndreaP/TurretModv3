package sanandreasp.mods.TurretMod3.client.registry;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.EnumSet;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent;
import org.lwjgl.input.Keyboard;

import sanandreasp.mods.TurretMod3.entity.turret.EntityTurret_Base;
import sanandreasp.mods.TurretMod3.packet.PacketHandlerCommon;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.packet.Packet250CustomPayload;
import cpw.mods.fml.client.registry.KeyBindingRegistry.KeyHandler;
import cpw.mods.fml.common.TickType;
import cpw.mods.fml.common.network.PacketDispatcher;

public class KeyBindHandler {
	
	public static KeyBinding turretShootKey = new KeyBinding("turretmod3.keys.turretShoot", Keyboard.KEY_F);

	@SubscribeEvent
	public void keyDown(InputEvent.KeyInputEvent event) {
        EntityPlayer player = Minecraft.getMinecraft().thePlayer;
        if (player != null && player.ridingEntity instanceof EntityTurret_Base && Minecraft.getMinecraft().currentScreen == null) {
            try {
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                DataOutputStream dos = new DataOutputStream(bos);

                dos.writeInt(0x003);

                PacketDispatcher.sendPacketToServer(new Packet250CustomPayload(PacketHandlerCommon.getChannel(), bos.toByteArray()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
	}
}
