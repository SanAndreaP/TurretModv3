package sanandreasp.mods.TurretMod3.packet;

import java.io.DataInputStream;

import sanandreasp.mods.TurretMod3.entity.turret.EntityTurret_Base;

import net.minecraft.entity.player.EntityPlayer;

public class PacketRecvTurretShootKey extends PacketBase {

	@Override
	public void handle(DataInputStream iStream, EntityPlayer player) {
		if(player.ridingEntity != null && player.ridingEntity instanceof EntityTurret_Base) {
			EntityTurret_Base turret = (EntityTurret_Base)player.ridingEntity;
			turret.tryToShoot(true);
		}
	}

}
