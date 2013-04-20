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

import sanandreasp.mods.TurretMod3.entity.turret.EntityTurret_Base;
import sanandreasp.mods.TurretMod3.tileentity.TileEntityLaptop;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.tileentity.TileEntity;

public class PacketRecvLaptopTargets extends PacketBase {

	@Override
	public void handle(DataInputStream iStream, EntityPlayer player) {
		try {
			NBTTagCompound nbt = (NBTTagCompound) NBTBase.readNamedTag(iStream);
			Map<String, Boolean> tgt = Maps.newHashMap();
	        NBTTagList var1 = nbt.getTagList("targetsTag");

	        for (int var2 = 0; var2 < var1.tagCount(); ++var2)
	        {
	            NBTTagCompound var3 = (NBTTagCompound)var1.tagAt(var2);
	            tgt.put(var3.getString("tgName"), var3.getBoolean("isEnabled"));
	        }
			
	        TileEntity te = player.worldObj.getBlockTileEntity(iStream.readInt(), iStream.readInt(), iStream.readInt());
	        if(te != null && te instanceof TileEntityLaptop) {
	        	((TileEntityLaptop)te).programItemsTargets(tgt);
	        	player.openContainer.detectAndSendChanges();
	        }
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static Packet250CustomPayload getPacket(Map<String, Boolean> list, TileEntity te, int id) {
		NBTTagCompound nbt = new NBTTagCompound();				
		NBTTagList nbtList = new NBTTagList("targetList");				
		for(Entry<String, Boolean> target : list.entrySet()) {
			NBTTagCompound var4 = new NBTTagCompound();
            var4.setString("tgName", target.getKey());
            var4.setBoolean("isEnabled", target.getValue());
            nbtList.appendTag(var4);
		}
		nbt.setTag("targetsTag", nbtList);
		
    	ByteArrayOutputStream b = new ByteArrayOutputStream();
		try {
			DataOutputStream o = new DataOutputStream(b);
	    	o.writeInt(id);
	    	NBTBase.writeNamedTag(nbt, o);
	    	o.writeInt(te.xCoord);
	    	o.writeInt(te.yCoord);
	    	o.writeInt(te.zCoord);
	    	
	    	return new Packet250CustomPayload(PacketHandlerCommon.getChannel(), b.toByteArray());
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return null;
	}
	
	public static void sendServer(Map<String, Boolean> list, TileEntity te) {
    	PacketDispatcher.sendPacketToServer(getPacket(list, te, 0x004));
	}

}
