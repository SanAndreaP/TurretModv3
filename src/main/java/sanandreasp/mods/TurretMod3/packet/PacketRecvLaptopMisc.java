package sanandreasp.mods.turretmod3.packet;

import java.io.DataInputStream;
import java.io.IOException;

import sanandreasp.mods.turretmod3.tileentity.TileEntityLaptop;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;

public class PacketRecvLaptopMisc extends PacketBase {
	@Override
	public void handle(DataInputStream iStream, EntityPlayer player) {
		try {
	        TileEntity te = player.worldObj.getTileEntity(iStream.readInt(), iStream.readInt(), iStream.readInt());
	        if (te != null && te instanceof TileEntityLaptop) {
	        	((TileEntityLaptop)te).programItemsNameAndFreq(iStream.readUTF(), iStream.readShort());
	        	player.openContainer.detectAndSendChanges();
	        }
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
