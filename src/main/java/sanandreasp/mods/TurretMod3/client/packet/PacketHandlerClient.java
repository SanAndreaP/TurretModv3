package sanandreasp.mods.turretmod3.client.packet;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.Map;

import com.google.common.collect.Maps;

import sanandreasp.mods.EnderStuffPlus.tileentity.TileEntityBiomeChanger;
import sanandreasp.mods.turretmod3.packet.PacketBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class PacketHandlerClient implements IPacketHandler {
	
	private Map<Integer, PacketBase> packetTypes = Maps.newHashMap();
	
	public PacketHandlerClient() {
		this.packetTypes.put(0x100, new PacketRecvUpgrades());
		this.packetTypes.put(0x101, new PacketRecvPlayerNBT());
		this.packetTypes.put(0x102, new PacketRecvTargetListClt());
		this.packetTypes.put(0x103, new PacketRecvSpawnParticle());
	}

	@Override
	public void onPacketData(INetworkManager manager, Packet250CustomPayload packet, Player player) {
        EntityPlayer player2 = (EntityPlayer)player;
        try {
        	DataInputStream iStream = new DataInputStream(new ByteArrayInputStream(packet.data));
			this.packetTypes.get(iStream.readInt()).handle(iStream, player2);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static String getChannel() {
		return "TurretMod3";
	}
}
