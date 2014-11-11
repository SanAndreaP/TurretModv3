package sanandreasp.mods.turretmod3.client.packet;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.Map;

import com.google.common.collect.Maps;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import sanandreasp.mods.turretmod3.client.gui.TCU.GuiTCUBase;
import sanandreasp.mods.turretmod3.entity.turret.EntityTurret_Base;
import sanandreasp.mods.turretmod3.packet.PacketBase;

public class PacketRecvTargetListClt extends PacketBase {

	@Override
	public void handle(DataInputStream iStream, EntityPlayer player) {
		try {
			int eID = iStream.readInt();
			NBTTagCompound nbt = (NBTTagCompound) NBTBase.readNamedTag(iStream);
			Map<String, Boolean> tgt = Maps.newHashMap();
	        NBTTagList var1 = nbt.getTagList("targetsTag");

	        for (int var2 = 0; var2 < var1.tagCount(); ++var2)
	        {
	            NBTTagCompound var3 = (NBTTagCompound)var1.tagAt(var2);
	            tgt.put(var3.getString("tgName"), var3.getBoolean("isEnabled"));
	        }
			
			GuiScreen gui = Minecraft.getMinecraft().currentScreen;
			if (gui != null && gui instanceof GuiTCUBase) {
				((GuiTCUBase)gui).turret = (EntityTurret_Base) Minecraft.getMinecraft().theWorld.getEntityByID(eID);
				((GuiTCUBase)gui).turret.targets = tgt;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
