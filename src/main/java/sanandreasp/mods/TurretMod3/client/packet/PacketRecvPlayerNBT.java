package sanandreasp.mods.turretmod3.client.packet;

import java.io.DataInputStream;
import java.io.IOException;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import sanandreasp.mods.turretmod3.packet.PacketBase;
import sanandreasp.mods.turretmod3.registry.TM3ModRegistry;

public class PacketRecvPlayerNBT extends PacketBase {

	@Override
	public void handle(DataInputStream iStream, EntityPlayer player) {
		try {
			TM3ModRegistry.proxy.initTM3PlayerTag(player);
			NBTTagCompound nbt = ((NBTTagCompound)NBTBase.readNamedTag(iStream));
			TM3ModRegistry.proxy.setPlayerTM3Data(player, nbt);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
