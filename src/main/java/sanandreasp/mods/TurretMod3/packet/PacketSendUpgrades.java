package sanandreasp.mods.turretmod3.packet;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.common.network.Player;

import sanandreasp.mods.turretmod3.entity.turret.EntityTurret_Base;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.world.WorldServer;

public class PacketSendUpgrades extends PacketBase {

	@Override
	public void handle(DataInputStream iStream, EntityPlayer player) {
		try {
			int eID = iStream.readInt();
			EntityTurret_Base turret = (EntityTurret_Base) ((WorldServer)player.worldObj).getEntityByID(eID);
			if (turret == null || turret.upgrades == null) return;
	    	ByteArrayOutputStream b = new ByteArrayOutputStream();
	    	DataOutputStream o = new DataOutputStream(b);
	    	o.writeInt(0x100);
	    	
			NBTTagCompound nbt = new NBTTagCompound("tuPkg");
			
			nbt.setInteger("eID", eID);

	        NBTTagList var2 = new NBTTagList();

	        for (int upgradeID : turret.upgrades.keySet())
	        {
	            NBTTagCompound var4 = new NBTTagCompound();
	            var4.setInteger("UgID", upgradeID);
	            turret.upgrades.get(upgradeID).writeToNBT(var4);
	            var2.appendTag(var4);
	        }

	        nbt.setTag("TurretUpgrades", var2);
	        
	        NBTBase.writeNamedTag(nbt, o);
	        
			Packet250CustomPayload packetTrans = new Packet250CustomPayload(PacketHandlerCommon.getChannel(), b.toByteArray());
			PacketDispatcher.sendPacketToPlayer(packetTrans, player);
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void send(EntityTurret_Base etb) {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		DataOutputStream dos = new DataOutputStream(bos);
		try {
			dos.writeInt(0x000);
			dos.writeInt(etb.entityId);
		} catch (IOException e) {
			e.printStackTrace();
		}
		PacketDispatcher.sendPacketToServer(new Packet250CustomPayload(PacketHandlerCommon.getChannel(), bos.toByteArray()));
	}
}
