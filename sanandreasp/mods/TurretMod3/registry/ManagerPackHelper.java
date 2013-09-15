package sanandreasp.mods.TurretMod3.registry;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import sanandreasp.mods.managers.SAP_ConfigManagerII;
import sanandreasp.mods.managers.SAP_LanguageManager;
import sanandreasp.mods.managers.SAP_UpdateManager;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.Mod;

/**
 * A helper class for the Manager Pack to determine whether the Manager Pack is installed or not.
 * Also it provides methods to easily set and get new instances of the Manager Pack classes:
 * <ul>
 *   <li>SAP_ConfigManagerII</li>
 *   <li>SAP_LanguageManager</li>
 *   <li>SAP_UpdateManager</li>
 * </ul>
 * If the Manager Pack isn't found, the user will get a dialog where he's remembered to install the Manager Pack, with an URL to the downloads.
 * @author SanAndreas
 *
 */
public class ManagerPackHelper {
	public boolean loading = true;
	private Object cfgmanInst, updmanInst, langmanInst;
	
	public void checkManPack(String modname) {
		try {
			Class cls = Class.forName("sanandreasp.mods.managers.SAP_ManagerRegistry");
			if (cls == null) {
				loading = false;
				stopLoad(String.format("You forgot to install the Manager Pack!\nPlease download the latest version from http://goo.gl/WZwtZ\n- Minecraft will load without this mod: >> %s <<! -", modname), "No Manager Pack!");
			}
	    	Mod s = (Mod) cls.getAnnotation(Mod.class);
	        if (s != null) {
	        	int verErr = checkVersion(s.version(), "1.4.2");
	        	if (verErr > 1) {
	        		loading = false;
	        		stopLoad(String.format("Wrong version of the Manager Pack!\nPlease download the latest version from http://goo.gl/WZwtZ\n- Minecraft will load without this mod: >> %s <<! -", modname), "Manager Pack outdated!");
	        	} else if (verErr == 1) {
	        		FMLLog.warning("No up-to-date SAP Manager Pack!\nPlease get the latest version now at: http://goo.gl/WZwtZ\n- Expect bugs and crashes!");
	        	}
	        }
		} catch (ClassNotFoundException e) {
			stopLoad(String.format("You forgot to install the Manager Pack!\nPlease download the latest version from http://goo.gl/WZwtZ\n- Minecraft will load without this mod: >> %s <<! -", modname), "No Manager Pack!");
			loading = false;
		}
	}
	
	public void initMan(SAP_ConfigManagerII cfgman, SAP_LanguageManager langman, SAP_UpdateManager updman) {
		this.cfgmanInst = cfgman;
		this.langmanInst = langman;
		this.updmanInst = updman;
	}
	
	public SAP_ConfigManagerII getCfgMan() {
		return (SAP_ConfigManagerII) (this.cfgmanInst != null && this.cfgmanInst instanceof SAP_ConfigManagerII ? this.cfgmanInst : null);
	}
	
	public SAP_LanguageManager getLangMan() {
		return (SAP_LanguageManager) (this.langmanInst != null && this.langmanInst instanceof SAP_LanguageManager ? this.langmanInst : null);
	}
	
	public SAP_UpdateManager getUpdMan() {
		return (SAP_UpdateManager) (this.updmanInst != null && this.updmanInst instanceof SAP_UpdateManager ? this.updmanInst : null);
	}

	private void stopLoad(final String msg, final String title) {
		FMLLog.severe(title + " Please get the latest version now at: http://goo.gl/WZwtZ");
		if (FMLCommonHandler.instance().getSide().isClient()) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					JOptionPane pane = new JOptionPane(msg, JOptionPane.ERROR_MESSAGE);
					JDialog dlg = pane.createDialog(title);
					dlg.setModal(false);
					dlg.setVisible(true);
				}
			}).start();
		}
	}
	
	private int checkVersion(String currVer, String expectedVer) {
		String currSplit[] = currVer.split("\\.");
		String exptSplit[] = expectedVer.split("\\.");
		
		int majDiff = Integer.parseInt(currSplit[0]) - Integer.parseInt(exptSplit[0]);
		int minDiff = Integer.parseInt(currSplit[1]) - Integer.parseInt(exptSplit[1]);
		int revDiff = (currSplit.length < 3 ? 0 : Integer.parseInt(currSplit[2])) - (exptSplit.length < 3 ? 0 : Integer.parseInt(exptSplit[2]));
		
		if (majDiff < 0) {
			return 3;
		} else if (minDiff < 0 && majDiff == 0) {
			return 2;
		} else if (revDiff < 0 && minDiff == 0 && majDiff == 0) {
			return 1;
		}
		
		return 0;
	}
}
