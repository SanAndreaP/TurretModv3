package sanandreasp.mods.TurretMod3.client.model.turret;

import net.minecraft.client.model.ModelBase;

public class ModelTurret_Base extends ModelBase {
	
	public boolean isGlowTexture = false;
	
	public ModelTurret_Base setGlowModel() {
		isGlowTexture = true;
		return this;
	}
	
	public void renderBase() {
		
	}

}
