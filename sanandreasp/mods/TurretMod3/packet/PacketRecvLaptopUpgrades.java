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
import sanandreasp.mods.TurretMod3.item.ItemTurret;
import sanandreasp.mods.TurretMod3.tileentity.TileEntityLaptop;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.tileentity.TileEntity;

public class PacketRecvLaptopUpgrades extends PacketBase {

	@Override
	public void handle(DataInputStream iStream, EntityPlayer player) {
		try {
	        TileEntity te = player.worldObj.getBlockTileEntity(iStream.readInt(), iStream.readInt(), iStream.readInt());
	        if(te != null && te instanceof TileEntityLaptop) {
	        	TileEntityLaptop lap = ((TileEntityLaptop)te);
	        	
	        	int turretSlot = iStream.readInt();
	        	int upgradeSlot = iStream.readInt();

				ItemStack turretItem = lap.getStackInSlot(turretSlot);
				ItemStack upgradeItem = lap.getStackInSlot(upgradeSlot);
				
				if(ItemTurret.isUpgradeValid(turretItem, upgradeItem, ItemTurret.getUpgItems(turretItem))) {
					ItemTurret.addUpgItem(turretItem, upgradeItem);
					lap.decrStackSize(upgradeSlot, 1);
					player.openContainer.detectAndSendChanges();
				}
	        }
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
