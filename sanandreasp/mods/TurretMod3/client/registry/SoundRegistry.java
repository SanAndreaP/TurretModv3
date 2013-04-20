package sanandreasp.mods.TurretMod3.client.registry;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Enumeration;
import java.util.logging.Level;

import sanandreasp.mods.TurretMod3.registry.TM3ModRegistry;

import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.SoundManager;
import net.minecraft.src.ModLoader;
import net.minecraftforge.client.event.sound.SoundLoadEvent;
import net.minecraftforge.event.ForgeSubscribe;

public class SoundRegistry {
	
	@ForgeSubscribe
	@SideOnly(Side.CLIENT)
    public void onSound(SoundLoadEvent event)
    {
        try
        {
        	addSound(event, "idle/", "turretidle", 4);
        	addSound(event, "idle/", "turretexp", 2);
        	addSound(event, "collect/", "chest", 1);
        	addSound(event, "collect/", "ia_get", 1);
        	addSound(event, "hit/", "turretDeath", 1);
        	addSound(event, "hit/", "turrethit", 1);
        	addSound(event, "shoot/", "shotgun", 1);
        	addSound(event, "shoot/", "minigun", 1);
        	addSound(event, "shoot/", "pistol", 1);
        	addSound(event, "shoot/", "laser", 1);
        	addSound(event, "shoot/", "flamethrower", 1);
        	addSound(event, "shoot/", "sniper", 1);
        	addSound(event, "shoot/", "healBeam", 1);
        	addSound(event, "shoot/", "flak", 1);
        	addSound(event, "shoot/", "artillery", 1);
        	addSound(event, "ricochet/", "bullet", 5);
        	addSound(event, "ricochet/", "splash1", 1);
        	addSound(event, "misc/", "shieldActive", 1);
        	
        	registerStreaming(event.manager, "Tidal Force");
        }
        catch (Exception e)
        {
            System.err.println("Failed to register one or more sounds.");
            e.printStackTrace();
        }
    }
	
	private void addSound(SoundLoadEvent event, String path, String fileName, int qty) throws Exception {
		if(qty < 2) {
			event.manager.soundPoolSounds.addSound("turretmod3/"+path+fileName+".ogg", TM3ModRegistry.class.getResource("/sanandreasp/mods/TurretMod3/sounds/"+path+fileName+".ogg"));
		} else {
			for(int i = 1; i <= qty; i++) {
				event.manager.soundPoolSounds.addSound("turretmod3/"+path+fileName+String.valueOf(i)+".ogg", TM3ModRegistry.class.getResource("/sanandreasp/mods/TurretMod3/sounds/"+path+fileName+String.valueOf(i)+".ogg"));
			}
		}
    }
	
	private void addSound(SoundLoadEvent event, String path, String fileName, String name) throws Exception {
		event.manager.soundPoolSounds.addSound(name+".ogg", TM3ModRegistry.class.getResource("/sanandreasp/mods/TurretMod3/"+path+fileName+".ogg"));
	}
    
    private void registerStreaming(SoundManager manager, String name)
    {
    	File soundFile = new File(Minecraft.getMinecraftDir(), "/resources/streaming/"+name+".ogg");
    	
    	if (soundFile.canRead() && soundFile.isFile()) {
    		Minecraft.getMinecraft().installResource("streaming/"+name+".ogg", soundFile);
			FMLLog.log("TurretMod3", Level.INFO, "Sound Stream registered: %s", name);
		} else {
	    	InputStream is = TM3ModRegistry.class.getClassLoader().getResourceAsStream("sanandreasp/mods/TurretMod3/streaming/"+name+".ogg");
	    	try {
	    		if(is != null) {
					FileOutputStream fos = new FileOutputStream(soundFile);
					byte buffer[] = new byte[102400];
					int len;
					while((len = is.read(buffer)) != -1) {
						fos.write(buffer, 0, len);
					}
					fos.close();
	    		}
			} catch (FileNotFoundException e) {
				FMLLog.log("TurretMod3", Level.WARNING, e, "Sound Stream failed to register: %s", name);
			} catch (IOException e) {
				FMLLog.log("TurretMod3", Level.WARNING, e, "Sound Stream failed to register: %s", name);
			}
	    	
	    	soundFile = new File(Minecraft.getMinecraftDir(), "/resources/streaming/"+name+".ogg");
	    	
	    	if (soundFile.canRead() && soundFile.isFile()) {
	    		Minecraft.getMinecraft().installResource("streaming/"+name+".ogg", soundFile);
				FMLLog.log("TurretMod3", Level.INFO, "Sound Stream registered: %s", name);
			} else {
				FMLLog.log("TurretMod3", Level.WARNING, "Sound Stream failed to register: %s", name);
			}
		}
    }
}
