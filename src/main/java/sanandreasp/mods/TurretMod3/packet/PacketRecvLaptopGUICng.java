package sanandreasp.mods.turretmod3.packet;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.common.network.Player;

import sanandreasp.mods.turretmod3.registry.TM3ModRegistry;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.tileentity.TileEntity;

public class PacketRecvLaptopGUICng extends PacketBase {

	@Override
	public void handle(DataInputStream iStream, EntityPlayer player) {
		try {
			player.openGui(TM3ModRegistry.instance, iStream.readInt(), player.worldObj, iStream.readInt(), iStream.readInt(), iStream.readInt());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static Packet250CustomPayload getPacket(int guiID, TileEntity te, int id) {
    	ByteArrayOutputStream b = new ByteArrayOutputStream();
		try {
			DataOutputStream o = new DataOutputStream(b);
	    	o.writeInt(id);
	    	o.writeInt(guiID);
	    	o.writeInt(te.xCoord);
	    	o.writeInt(te.yCoord);
	    	o.writeInt(te.zCoord);
	    	
	    	return new Packet250CustomPayload(PacketHandlerCommon.getChannel(), b.toByteArray());
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return null;
	}
	
	public static void sendServer(int guiID, TileEntity te) {
    	PacketDispatcher.sendPacketToServer(getPacket(guiID, te, 0x005));
	}

}
