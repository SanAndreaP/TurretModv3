package sanandreasp.mods.TurretMod3.registry;

import java.util.EnumSet;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.common.IShearable;

import cpw.mods.fml.common.IScheduledTickHandler;
import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;

public class SchedTickHandlerWorld implements IScheduledTickHandler {

	
	public SchedTickHandlerWorld() { }

	@Override
	public void tickStart(EnumSet<TickType> type, Object... tickData) {
		for(Object obj : ((World)tickData[0]).playerEntities) {
			TM3ModRegistry.proxy.initTM3PlayerTag((EntityPlayer)obj);
		}
	}

	@Override
	public void tickEnd(EnumSet<TickType> type, Object... tickData) { }

	@Override
	public EnumSet<TickType> ticks() {
		return EnumSet.of(TickType.WORLD);
	}

	@Override
	public String getLabel() {
		return "TurretModWldTicks";
	}

	@Override
	public int nextTickSpacing() {
		return 5;
	}

}
