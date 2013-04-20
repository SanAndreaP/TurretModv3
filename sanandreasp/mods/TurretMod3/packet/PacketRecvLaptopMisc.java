package sanandreasp.mods.TurretMod3.packet;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.Map;

import com.google.common.collect.Maps;

import sanandreasp.mods.TurretMod3.item.ItemTurret;
import sanandreasp.mods.TurretMod3.tileentity.TileEntityLaptop;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;

public class PacketRecvLaptopMisc extends PacketBase {
	@Override
	public void handle(DataInputStream iStream, EntityPlayer player) {
		try {
	        TileEntity te = player.worldObj.getBlockTileEntity(iStream.readInt(), iStream.readInt(), iStream.readInt());
	        if(te != null && te instanceof TileEntityLaptop) {
	        	((TileEntityLaptop)te).programItemsNameAndFreq(iStream.readUTF(), iStream.readShort());
	        	player.openContainer.detectAndSendChanges();
	        }
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
