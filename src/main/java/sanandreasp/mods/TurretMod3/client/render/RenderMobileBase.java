package sanandreasp.mods.turretmod3.client.render;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.EntityLiving;

public class RenderMobileBase extends RenderLiving {

	public RenderMobileBase(ModelBase par1ModelBase, float par2) {
		super(par1ModelBase, par2);
	}
	
	@Override
	protected void rotateCorpse(EntityLiving par1EntityLiving, float par2, float par3, float par4) {
//		super.rotateCorpse(par1EntityLiving, par2, par3, par4);
	}
}
