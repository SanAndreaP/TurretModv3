package sanandreasp.mods.turretmod3.client.packet;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.Map;

import com.google.common.collect.Maps;

import cpw.mods.fml.common.network.ByteBufUtils;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraftforge.common.util.Constants;
import sanandreasp.mods.turretmod3.client.gui.TCU.GuiTCUBase;
import sanandreasp.mods.turretmod3.entity.turret.EntityTurret_Base;
import sanandreasp.mods.turretmod3.packet.PacketBase;

public class PacketRecvTargetListClt extends PacketBase {
    private int eID;
    private NBTTagCompound nbt;
    public PacketRecvTargetListClt(){}
    public PacketRecvTargetListClt(int entity, NBTTagCompound tag){
        eID = entity;
        nbt = tag;
    }

	@Override
	public void handle(EntityPlayer player) {
        Map<String, Boolean> tgt = Maps.newHashMap();
        NBTTagList var1 = nbt.getTagList("targetsTag", Constants.NBT.TAG_COMPOUND);

        for (int var2 = 0; var2 < var1.tagCount(); ++var2)
        {
            NBTTagCompound var3 = var1.getCompoundTagAt(var2);
            tgt.put(var3.getString("tgName"), var3.getBoolean("isEnabled"));
        }

        GuiScreen gui = Minecraft.getMinecraft().currentScreen;
        if (gui != null && gui instanceof GuiTCUBase) {
            ((GuiTCUBase)gui).turret = (EntityTurret_Base) player.worldObj.getEntityByID(eID);
            ((GuiTCUBase)gui).turret.targets = tgt;
        }
	}

    @Override
    public void fromBytes(ByteBuf buf) {
        eID = buf.readInt();
        nbt = ByteBufUtils.readTag(buf);
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(eID);
        ByteBufUtils.writeTag(buf, nbt);
    }
}
