package sanandreasp.mods.turretmod3.packet;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.entity.player.EntityPlayer;
import sanandreasp.mods.turretmod3.client.packet.PacketRecvPlayerNBT;
import sanandreasp.mods.turretmod3.client.packet.PacketRecvSpawnParticle;
import sanandreasp.mods.turretmod3.client.packet.PacketRecvTargetListClt;
import sanandreasp.mods.turretmod3.client.packet.PacketRecvUpgrades;
import sanandreasp.mods.turretmod3.registry.TM3ModRegistry;

public class PacketHandlerCommon implements IMessageHandler<PacketBase, IMessage> {

    public void registerOn(SimpleNetworkWrapper wrapper){
        wrapper.registerMessage(this, PacketSendUpgrades.class, 0, Side.SERVER);
        wrapper.registerMessage(this, PacketRecvTargetListSrv.class, 1, Side.SERVER);
        wrapper.registerMessage(this, PacketRecvTurretSettings.class, 2, Side.SERVER);
        wrapper.registerMessage(this, PacketRecvTurretShootKey.class, 3, Side.SERVER);
        wrapper.registerMessage(this, PacketRecvLaptopTargets.class, 4, Side.SERVER);
        wrapper.registerMessage(this, PacketRecvLaptopGUICng.class, 5, Side.SERVER);
        wrapper.registerMessage(this, PacketRecvLaptopGeneralStg.class, 6, Side.SERVER);
        wrapper.registerMessage(this, PacketRecvLaptopUpgrades.class, 7, Side.SERVER);
        wrapper.registerMessage(this, PacketRecvLaptopMisc.class, 8, Side.SERVER);
        wrapper.registerMessage(this, PacketRecvUpgrades.class, 100, Side.CLIENT);
        wrapper.registerMessage(this, PacketRecvPlayerNBT.class, 101, Side.CLIENT);
        wrapper.registerMessage(this, PacketRecvTargetListClt.class, 102, Side.CLIENT);
        wrapper.registerMessage(this, PacketRecvSpawnParticle.class, 103, Side.CLIENT);
    }

    @Override
    public IMessage onMessage(PacketBase packet, MessageContext context) {
        EntityPlayer player = TM3ModRegistry.proxy.getPlayer(context);
        packet.handle(player);
        return null;
    }
}
