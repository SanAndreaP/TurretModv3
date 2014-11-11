package sanandreasp.mods.turretmod3.packet;

import java.io.DataInputStream;
import java.io.IOException;

import sanandreasp.mods.turretmod3.item.ItemTurret;
import sanandreasp.mods.turretmod3.tileentity.TileEntityLaptop;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

public class PacketRecvLaptopUpgrades extends PacketBase {

	@Override
	public void handle(DataInputStream iStream, EntityPlayer player) {
		try {
	        TileEntity te = player.worldObj.getTileEntity(iStream.readInt(), iStream.readInt(), iStream.readInt());
	        if (te != null && te instanceof TileEntityLaptop) {
	        	TileEntityLaptop lap = ((TileEntityLaptop)te);
	        	
	        	int turretSlot = iStream.readInt();
	        	int upgradeSlot = iStream.readInt();

				ItemStack turretItem = lap.getStackInSlot(turretSlot);
				ItemStack upgradeItem = lap.getStackInSlot(upgradeSlot);
				
				if (ItemTurret.isUpgradeValid(turretItem, upgradeItem, ItemTurret.getUpgItems(turretItem))) {
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
