package sanandreasp.mods.turretmod3.packet;

import java.io.DataInputStream;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;

public abstract class PacketBase implements IMessage{

    public abstract void handle(EntityPlayer player);

}
