package sanandreasp.mods.turretmod3.client.packet;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Map;

import com.google.common.collect.Maps;

import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.common.network.Player;

import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.packet.Packet250CustomPayload;
import sanandreasp.mods.turretmod3.entity.turret.EntityTurret_Base;
import sanandreasp.mods.turretmod3.packet.PacketBase;
import sanandreasp.mods.turretmod3.packet.PacketHandlerCommon;

public class PacketRecvUpgrades extends PacketBase {

	@Override
	public void handle(DataInputStream iStream, EntityPlayer player) {
		try {
			NBTTagCompound nbt = ((NBTTagCompound)NBTBase.readNamedTag(iStream));
			
			int entityID = nbt.getInteger("eID");
			
	        NBTTagList var2 = nbt.getTagList("TurretUpgrades");
	        
	        Map<Integer, ItemStack> upgrades = Maps.newHashMap();
	        
	        for (int var3 = 0; var3 < var2.tagCount(); ++var3)
	        {
	        	NBTTagCompound var4 = (NBTTagCompound)var2.tagAt(var3);
	        	int var5 = var4.getInteger("UgID");
	        	ItemStack var6 = ItemStack.loadItemStackFromNBT(var4);
	        	upgrades.put(var5, var6);
	        }
			
			WorldClient clientWorld = (WorldClient) ((EntityPlayer)player).worldObj;
			EntityTurret_Base turret = (EntityTurret_Base) clientWorld.getEntityByID(entityID);
			if (turret != null)
				turret.upgrades = upgrades;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void send(EntityTurret_Base turret) {
		ByteArrayOutputStream bos;
		DataOutputStream dos;
		try {
			bos = new ByteArrayOutputStream();
			dos = new DataOutputStream(bos);
			
			dos.writeInt(0x100);
			
			NBTTagCompound nbt = new NBTTagCompound("TurretUpgrades");
			
			nbt.setInteger("eID", turret.entityId);

	        NBTTagList var2 = new NBTTagList();

	        for (int upgradeID : turret.upgrades.keySet())
	        {
	            NBTTagCompound var4 = new NBTTagCompound();
	            var4.setInteger("UgID", upgradeID);
	            turret.upgrades.get(upgradeID).writeToNBT(var4);
	            var2.appendTag(var4);
	        }

	        nbt.setTag("TurretUpgrades", var2);
	        
	        NBTBase.writeNamedTag(nbt, dos);
	        
			Packet250CustomPayload packetTrans = new Packet250CustomPayload(PacketHandlerCommon.getChannel(), bos.toByteArray());
			PacketDispatcher.sendPacketToAllInDimension(packetTrans, turret.entityId);
		} catch(IOException e) {
			e.printStackTrace();
		}
	}

}
