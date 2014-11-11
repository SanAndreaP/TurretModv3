package sanandreasp.mods.turretmod3.packet;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.Map;

import com.google.common.collect.Maps;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.common.network.Player;

public class PacketHandlerCommon implements IPacketHandler {
	
	private Map<Integer, PacketBase> packetTypes = Maps.newHashMap();
	
	public PacketHandlerCommon() {
		this.packetTypes.put(0x000, new PacketSendUpgrades());
		this.packetTypes.put(0x001, new PacketRecvTargetListSrv());
		this.packetTypes.put(0x002, new PacketRecvTurretSettings());
		this.packetTypes.put(0x003, new PacketRecvTurretShootKey());
		this.packetTypes.put(0x004, new PacketRecvLaptopTargets());
		this.packetTypes.put(0x005, new PacketRecvLaptopGUICng());
		this.packetTypes.put(0x006, new PacketRecvLaptopGeneralStg());
		this.packetTypes.put(0x007, new PacketRecvLaptopUpgrades());
		this.packetTypes.put(0x008, new PacketRecvLaptopMisc());
	}

    @Override
    public void onPacketData(INetworkManager manager, Packet250CustomPayload packet, Player par3Player) {
        EntityPlayer player = (EntityPlayer)par3Player;
        try {
        	DataInputStream iStream = new DataInputStream(new ByteArrayInputStream(packet.data));
			this.packetTypes.get(iStream.readInt()).handle(iStream, player);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
	
	public static String getChannel() {
		return "TurretMod3";
	}
}
