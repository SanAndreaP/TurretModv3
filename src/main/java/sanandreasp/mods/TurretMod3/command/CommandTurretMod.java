package sanandreasp.mods.turretmod3.command;

import sanandreasp.mods.turretmod3.registry.TM3ModRegistry;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;

public class CommandTurretMod extends CommandBase {
	@Override
	public String getCommandName() {
		return "turretmod";
	}

    @Override
    public String getCommandUsage(ICommandSender p_71518_1_) {
        return "/turretmod renderLabel <true/false>";
    }

    @Override
	public void processCommand(ICommandSender var1, String[] var2) {
		EntityPlayer player = getCommandSenderAsPlayer(var1);
		if (var2.length > 0) {
			if (var2[0].equals("renderLabel")) {
				if (var2.length > 1) {
                    if (var2[1].equals("true")) {
                        TM3ModRegistry.proxy.getPlayerTM3Data(player).setBoolean("renderLabels", true);
                        var1.sendChatToPlayer("turret labels will be rendered");
                    } else {
                        TM3ModRegistry.proxy.getPlayerTM3Data(player).setBoolean("renderLabels", false);
                        var1.sendChatToPlayer("turret labels won't be rendered");
                    }
                }
			}
		}
	}

    @Override
    public int getRequiredPermissionLevel()
    {
        return 4;
    }

    @Override
    public boolean canCommandSenderUseCommand(ICommandSender par1ICommandSender)
    {
    	if (par1ICommandSender instanceof EntityPlayer) {
    		return true;
    	}
        return par1ICommandSender.canCommandSenderUseCommand(this.getRequiredPermissionLevel(), this.getCommandName());
    }

}
