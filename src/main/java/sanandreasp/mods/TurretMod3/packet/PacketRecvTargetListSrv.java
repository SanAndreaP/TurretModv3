package sanandreasp.mods.TurretMod3.packet;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;

import com.google.common.collect.Maps;

import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.common.network.Player;

import sanandreasp.mods.TurretMod3.client.gui.TCU.GuiTCUBase;
import sanandreasp.mods.TurretMod3.entity.turret.EntityTurret_Base;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.packet.Packet250CustomPayload;

public class PacketRecvTargetListSrv extends PacketBase {

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
			
			EntityTurret_Base turret = (EntityTurret_Base) player.worldObj.getEntityByID(eID);
			turret.targets = tgt;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static Packet250CustomPayload getPacket(EntityTurret_Base turret, int id) {
		NBTTagCompound nbt = new NBTTagCompound();				
		NBTTagList list = new NBTTagList("targetList");				
		for (Entry<String, Boolean> target : turret.targets.entrySet()) {
			NBTTagCompound var4 = new NBTTagCompound();
            var4.setString("tgName", target.getKey());
            var4.setBoolean("isEnabled", target.getValue());
            list.appendTag(var4);
		}
		nbt.setTag("targetsTag", list);
		
    	ByteArrayOutputStream b = new ByteArrayOutputStream();
		try {
			DataOutputStream o = new DataOutputStream(b);
	    	o.writeInt(id);
	    	o.writeInt(turret.entityId);
	    	NBTBase.writeNamedTag(nbt, o);
	    	
	    	return new Packet250CustomPayload(PacketHandlerCommon.getChannel(), b.toByteArray());
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return null;
	}
	
	public static void sendClient(EntityTurret_Base turret, EntityPlayer player) {
    	PacketDispatcher.sendPacketToPlayer(getPacket(turret, 0x102), player);
	}
	
	public static void sendServer(EntityTurret_Base turret) {
    	PacketDispatcher.sendPacketToServer(getPacket(turret, 0x001));
	}

}
