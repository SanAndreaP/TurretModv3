package sanandreasp.mods.turretmod3.client.registry;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.util.IIcon;
import net.minecraftforge.client.event.TextureStitchEvent;

public class TextureRegistry {
    public static IIcon[] iconCache;

    @SubscribeEvent
    public void stitchTextureMap(TextureStitchEvent.Pre event){
        iconCache = new IIcon[3];
        iconCache[0] = event.map.registerIcon("TurretMod3:ach_piercing");
        iconCache[1] = event.map.registerIcon("TurretMod3:redFlame");
        iconCache[2] = event.map.registerIcon("TurretMod3:blueFlame");
    }
}
