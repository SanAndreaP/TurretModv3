package sanandreasp.mods.TurretMod3.registry;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Map;

import sanandreasp.mods.TurretMod3.client.packet.PacketHandlerClient;
import sanandreasp.mods.TurretMod3.entity.turret.EntityTurret_TSHealer;
import sanandreasp.mods.TurretMod3.packet.PacketHandlerCommon;

import com.google.common.collect.Maps;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.common.network.Player;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.management.ServerConfigurationManager;

public class CommonProxy {
	
	public void registerRenderInformation() {
		;
	}
	
	public void initTM3PlayerTag(EntityPlayer player) {
		NBTTagCompound playerNBT = player.getEntityData();
		NBTTagCompound persNBT = new NBTTagCompound(EntityPlayer.PERSISTED_NBT_TAG);
		if(!playerNBT.hasKey(EntityPlayer.PERSISTED_NBT_TAG)) {
			playerNBT.setCompoundTag(EntityPlayer.PERSISTED_NBT_TAG, persNBT);
		} else {
			persNBT = player.getEntityData().getCompoundTag(EntityPlayer.PERSISTED_NBT_TAG);
		}
		
		if(!persNBT.hasKey("TurretMod3NBT")) {
			persNBT.setCompoundTag("TurretMod3NBT", new NBTTagCompound("TM3NBT"));
		}
		
		
		if(MinecraftServer.getServer() != null) {
		}
		
		if(!player.worldObj.isRemote) {
			boolean b = MinecraftServer.getServer().getConfigurationManager().getOps().contains(player.username.trim().toLowerCase());
			b = b || (MinecraftServer.getServer() != null 
						&& MinecraftServer.getServer().getServerOwner() != null 
						&& MinecraftServer.getServer().getServerOwner().equalsIgnoreCase(player.username) 
						&& MinecraftServer.getServer().isSinglePlayer()
					);
			this.getPlayerTM3Data(player).setBoolean("isOP", b);
			sendTM3NBTToClient(player);
		}
	}
	
	public NBTTagCompound getPlayerTM3Data(EntityPlayer player) {
		return player.getEntityData().getCompoundTag(EntityPlayer.PERSISTED_NBT_TAG).getCompoundTag("TurretMod3NBT");
	}
	
	public void setPlayerTM3Data(EntityPlayer player, NBTTagCompound nbt) {
		player.getEntityData().getCompoundTag(EntityPlayer.PERSISTED_NBT_TAG).setCompoundTag("TurretMod3NBT", nbt);
	}
	
	private void sendTM3NBTToClient(EntityPlayer player) {
		try {
			ByteArrayOutputStream b = new ByteArrayOutputStream();
			DataOutputStream o = new DataOutputStream(b);
			o.writeInt(0x101);
			NBTTagCompound nbt = getPlayerTM3Data(player);
	        NBTBase.writeNamedTag(nbt, o);
			Packet250CustomPayload packetTrans = new Packet250CustomPayload(PacketHandlerCommon.getChannel(), b.toByteArray());
			PacketDispatcher.sendPacketToPlayer(packetTrans, (Player) player);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void spawnParticle(int ID, double partX, double partY, double partZ, int dist, int dimID, Entity entity) {
		try {
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			DataOutputStream dos = new DataOutputStream(bos);
			
			dos.writeInt(0x103);
			dos.writeShort(ID);
			dos.writeFloat((float) partX);
			dos.writeFloat((float) partY);
			dos.writeFloat((float) partZ);
			dos.writeInt(entity != null ? entity.entityId : -1);
			
			PacketDispatcher.sendPacketToAllAround(partX, partY, partZ, dist, dimID, new Packet250CustomPayload(PacketHandlerCommon.getChannel(), bos.toByteArray()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
