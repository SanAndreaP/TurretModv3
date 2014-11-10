package sanandreasp.mods.TurretMod3.packet;

import java.io.DataInputStream;
import java.io.IOException;

import cpw.mods.fml.common.FMLLog;

import sanandreasp.mods.TurretMod3.entity.EntityMobileBase;
import sanandreasp.mods.TurretMod3.entity.turret.EntityTurret_Base;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MathHelper;

public class PacketRecvTurretSettings extends PacketBase {

	@Override
	public void handle(DataInputStream iStream, EntityPlayer player) {
		try {
			EntityTurret_Base turret = (EntityTurret_Base) player.worldObj.getEntityByID(iStream.readInt());
			byte b = iStream.readByte();
			switch(b) {
				case 0x0:
					turret.dismantle();
					break;
				case 0x1:
					turret.setUniqueTargets(!turret.useUniqueTargets());
					break;
				case 0x2:
					player.addExperience(turret.getExperience());
					turret.remExperience();
					break;
				case 0x3:
					EntityMobileBase base = (EntityMobileBase) (turret.ridingEntity instanceof EntityMobileBase ? turret.ridingEntity : null);
					if (base != null) {
						turret.mountEntity(null);
						turret.posY -= 0.8D;
						lblPos:
						for (int x = -1; x <= 1; x++) {
							for (int z = -1; z <= 1; z++) {
								if (Math.abs(x) + Math.abs(z) != 0) {
									double pX = turret.posX + x;
									double pZ = turret.posZ + z;
									double pY = turret.posY;

									base.setPosition(pX, pY, pZ);
									if (!base.isEntityInsideOpaqueBlock())
										break lblPos;
								}
							}
						}
						if (base.isEntityInsideOpaqueBlock())
							base.setPosition(turret.posX, turret.posY, turret.posZ);
					}
					break;
				case 0x4:
					player.mountEntity(turret);
					break;
				case 0x5:
					try {
						turret.setFrequency(Integer.valueOf(iStream.readUTF()));
					} catch (NumberFormatException nfe) {
						;
					}
					break;
				case 0x6:
					turret.setActiveState(!turret.isActive());
					break;
			}
		} catch (IOException e) {
			e.printStackTrace();
		};
	}

}
