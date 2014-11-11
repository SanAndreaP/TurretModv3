package sanandreasp.mods.turretmod3.packet;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.List;

import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.common.network.Player;

import sanandreasp.mods.turretmod3.entity.turret.EntityTurret_Base;
import sanandreasp.mods.turretmod3.registry.TM3ModRegistry;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.packet.Packet250CustomPayload;

public class PacketRecvLaptopGeneralStg extends PacketBase {

	@Override
	public void handle(DataInputStream iStream, EntityPlayer player) {
		try {
			switch(iStream.readInt()) {
				case 0x00: {
						NBTTagCompound nbt = TM3ModRegistry.proxy.getPlayerTM3Data(player);
						nbt.setBoolean("renderLabels", !nbt.getBoolean("renderLabels"));
					}
					break;
				case 0x01: {
						NBTTagCompound nbt = TM3ModRegistry.proxy.getPlayerTM3Data(player);
						byte b = nbt.getByte("tcCrosshair");
						nbt.setByte("tcCrosshair", ++b > 4 ? 0 : b);
					}
					break;
				case 0x02: {
						int freq = iStream.readShort();
						boolean active = iStream.readBoolean();
						List<Entity> entities = player.worldObj.loadedEntityList;
						for (Entity e : entities) {
							if (e instanceof EntityTurret_Base) {
								EntityTurret_Base turret = (EntityTurret_Base) e;
								if (turret.getPlayerName().equalsIgnoreCase(player.username)) {
									if (freq == -1) {
										turret.setActiveState(active);
									} else if (freq >= 0) {
										if (turret.getFrequency() == freq) {
											turret.setActiveState(active);
										}
									}
								}
							}
						}
					}
					break;
				case 0x03: {
						int freq = iStream.readShort();
						List<Entity> entities = player.worldObj.loadedEntityList;
						for (Entity e : entities) {
							if (e instanceof EntityTurret_Base) {
								EntityTurret_Base turret = (EntityTurret_Base) e;
								if (turret.getPlayerName().equalsIgnoreCase(player.username)) {
									if (freq == -1) {
										turret.resetCurrentTarget();
									} else if (freq >= 0) {
										if (turret.getFrequency() == freq) {
											turret.resetCurrentTarget();
										}
									}
								}
							}
						}
					}
					break;
				case 0x04: {
						int freq = iStream.readShort();
						boolean unique = iStream.readBoolean();
						List<Entity> entities = player.worldObj.loadedEntityList;
						for (Entity e : entities) {
							if (e instanceof EntityTurret_Base) {
								EntityTurret_Base turret = (EntityTurret_Base) e;
								if (turret.getPlayerName().equalsIgnoreCase(player.username)) {
									if (freq == -1) {
										turret.setUniqueTargets(unique);
									} else if (freq >= 0) {
										if (turret.getFrequency() == freq) {
											turret.setUniqueTargets(unique);
										}
									}
								}
							}
						}
					}
					break;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
