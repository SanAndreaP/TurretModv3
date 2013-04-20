package sanandreasp.mods.TurretMod3.client.render.turret;

import org.lwjgl.opengl.GL11;

import sanandreasp.mods.TurretMod3.client.model.turret.ModelTurret_Base;
import sanandreasp.mods.TurretMod3.client.model.turret.Model_T1Arrow;
import sanandreasp.mods.TurretMod3.entity.turret.EntityTurret_Base;
import sanandreasp.mods.TurretMod3.registry.TM3ModRegistry;
import sanandreasp.mods.TurretMod3.registry.TurretUpgrades.TUpgExperience;
import sanandreasp.mods.TurretMod3.registry.TurretUpgrades.TUpgInfAmmo;
import sanandreasp.mods.TurretMod3.registry.TurretUpgrades.TurretUpgrades;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MathHelper;

public class RenderTurret_Base extends RenderLiving {
	public RenderTurret_Base(ModelBase par1ModelBase, float par2) {
		super(par1ModelBase, par2);
		
		try {
			Class baseModelC = par1ModelBase.getClass();
			ModelTurret_Base baseModelI = (ModelTurret_Base) baseModelC.newInstance();
			setRenderPassModel(baseModelI.setGlowModel());
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} 
	}
	
	public ModelBase getMainModel() {
		return this.mainModel;
	}
	
	@Override
	protected int shouldRenderPass(EntityLiving par1EntityLiving, int par2, float par3) {
		if(par1EntityLiving.hurtTime <= 0) {
			GL11.glScalef(1.001F, 1.001F, 1.001F);
			GL11.glTranslatef(0F, -0.001F, 0F);
		}
		if(!(par1EntityLiving instanceof EntityTurret_Base)) {
			return -1;
		}
		EntityTurret_Base turret = (EntityTurret_Base)par1EntityLiving;
		if (par2 != 0 || turret.getGlowTexture().length() <= 0 || turret.hurtTime > 0 || !turret.isActive()) {
			GL11.glDepthMask(true);
			return -1;
		} else {
			this.loadTexture(turret.getGlowTexture());
			float var4 = 1.0F;
			GL11.glEnable(GL11.GL_BLEND);
			GL11.glDisable(GL11.GL_ALPHA_TEST);
			GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_ONE);
			char var5 = 0x000F0;
			int var6 = var5 % 65536;
			int var7 = var5 / 65536;
			OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, var6 / 1.0F, var7 / 1.0F);
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			GL11.glColor4f(1.0F, 1.0F, 1.0F, var4);
			GL11.glDepthMask(false);
			return 1;
		}
	}

    protected void passSpecialRender(EntityLiving par1EntityLiving, double par2, double par4, double par6)
    {
        this.renderStats((EntityTurret_Base)par1EntityLiving, par2, par4, par6);
    }
    
    @Override
    public void doRenderLiving(EntityLiving par1EntityLiving, double par2, double par4, double par6, float par8, float par9) {
    	if(!(par1EntityLiving.riddenByEntity != null && par1EntityLiving.riddenByEntity == Minecraft.getMinecraft().thePlayer && Minecraft.getMinecraft().gameSettings.thirdPersonView == 0))
    		super.doRenderLiving(par1EntityLiving, par2, par4, par6, par8, par9);
    }
    
    protected void renderStats(EntityTurret_Base par1Turret, double par2, double par4, double par6)
    {
        if (Minecraft.isGuiEnabled() && TM3ModRegistry.proxy.getPlayerTM3Data(Minecraft.getMinecraft().thePlayer).getBoolean("renderLabels") && !par1Turret.isInGui())
        {
            float var8 = 1.0F;
            float var9 = 0.016666668F * var8;
            double var10 = par1Turret.getDistanceSqToEntity(this.renderManager.livingPlayer);
            float var12 = TM3ModRegistry.labelRenderRange;

            if (var10 < (double)(var12 * var12))
            {
                String turretName = par1Turret.getTurretName() + String.format(" (Freq: %s)", par1Turret.getFrequency());
                String playerName = par1Turret.getPlayerName();

                FontRenderer var14 = this.getFontRendererFromRenderManager();
                GL11.glPushMatrix();
                GL11.glTranslatef((float)par2 + 0.0F, (float)par4 + 2.8F, (float)par6);
                GL11.glNormal3f(0.0F, 1.0F, 0.0F);
                GL11.glRotatef(-this.renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(this.renderManager.playerViewX, 1.0F, 0.0F, 0.0F);
                GL11.glScalef(-var9, -var9, var9);
                GL11.glDisable(GL11.GL_LIGHTING);
                GL11.glTranslatef(0.0F, 0.25F / var9, 0.0F);
                GL11.glDepthMask(false);
                GL11.glEnable(GL11.GL_BLEND);
                GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
                Tessellator var15 = Tessellator.instance;
                GL11.glDisable(GL11.GL_TEXTURE_2D);
                var15.startDrawingQuads();
                
                // name render bkg
                int var16 = var14.getStringWidth(turretName) / 2;
                var15.setColorRGBA_F(0.0F, 0.0F, 0.0F, 0.25F);
                var15.addVertex((double)(-var16 - 1), -1.0D, 0.0D);
                var15.addVertex((double)(-var16 - 1), 8.0D, 0.0D);
                var15.addVertex((double)(var16 + 1), 8.0D, 0.0D);
                var15.addVertex((double)(var16 + 1), -1.0D, 0.0D);
                
                var16 = var14.getStringWidth(playerName) / 2;
                var15.addVertex((double)(-var16 - 1), 17.0D, 0.0D);
                var15.addVertex((double)(-var16 - 1), 26.0D, 0.0D);
                var15.addVertex((double)(var16 + 1), 26.0D, 0.0D);
                var15.addVertex((double)(var16 + 1), 17.0D, 0.0D);
                
                var15.draw();
                GL11.glEnable(GL11.GL_TEXTURE_2D);
                GL11.glDepthMask(true);
                var14.drawString(turretName, -var14.getStringWidth(turretName) / 2, 0, 0xFFFFFF);
                var14.drawString(playerName, -var14.getStringWidth(playerName) / 2, 18, 0xBBBBBB);
                GL11.glDisable(GL11.GL_TEXTURE_2D);
                
                double health = ((double)par1Turret.getSrvHealth() / (double)par1Turret.getMaxHealth()) * 50D - 25D;
                double ammo = ((double)par1Turret.getAmmo() / (double)par1Turret.getMaxAmmo()) * 50D - 25D;
                double exp = ((double)par1Turret.getExperience() / (double)par1Turret.getExpCap()) * 50D - 25D;
                boolean hasXP = TurretUpgrades.hasUpgrade(TUpgExperience.class, par1Turret.upgrades) && par1Turret.hasPlayerAccess(Minecraft.getMinecraft().thePlayer);
                
                if(TurretUpgrades.hasUpgrade(TUpgInfAmmo.class, par1Turret.upgrades) && par1Turret.getAmmo() > 0) {
                	ammo = 25D;
                }
                
                //bars bkg
                var15.startDrawingQuads();
                var15.setColorRGBA_F(0F, 0F, 0F, 1F);
                var15.addVertex(health, 9D, 0D);
                var15.addVertex(health, 11D, 0D);
                var15.addVertex(25D, 11D, 0D);
                var15.addVertex(25D, 9D, 0D);
                
                var15.addVertex(ammo, 11.5D, 0D);
                var15.addVertex(ammo, 13.5D, 0D);
                var15.addVertex(25D, 13.5D, 0D);
                var15.addVertex(25D, 11.5D, 0D);
                
                var15.addVertex(-25.5D, 8.5D, 0D);
                var15.addVertex(-25.5D, 9D, 0D);
                var15.addVertex(25.5D, 9D, 0D);
                var15.addVertex(25.5D, 8.5D, 0D);
                
                var15.addVertex(-25.5D, 8.5D, 0D);
                var15.addVertex(-25.5D, 11.5D, 0D);
                var15.addVertex(-25D, 11.5D, 0D);
                var15.addVertex(-25D, 8.5D, 0D);
                var15.addVertex(25D, 8.5D, 0D);
                var15.addVertex(25D, 11.5D, 0D);
                var15.addVertex(25.5D, 11.5D, 0D);
                var15.addVertex(25.5D, 8.5D, 0D);
                var15.addVertex(-25D, 11D, 0D);
                var15.addVertex(-25D, 11.5D, 0D);
                var15.addVertex(25D, 11.5D, 0D);
                var15.addVertex(25D, 11D, 0D);
                
                var15.addVertex(-25.5D, 11D, 0D);
                var15.addVertex(-25.5D, 14D, 0D);
                var15.addVertex(-25D, 14D, 0D);
                var15.addVertex(-25D, 11D, 0D);
                var15.addVertex(25D, 11D, 0D);
                var15.addVertex(25D, 14D, 0D);
                var15.addVertex(25.5D, 14D, 0D);
                var15.addVertex(25.5D, 11D, 0D);
                var15.addVertex(-25D, 13.5D, 0D);
                var15.addVertex(-25D, 14D, 0D);
                var15.addVertex(25D, 14D, 0D);
                var15.addVertex(25D, 13.5D, 0D);
                
                if(hasXP) {
                    var15.addVertex(exp, 14D, 0D);
                    var15.addVertex(exp, 16D, 0D);
                    var15.addVertex(25D, 16D, 0D);
                    var15.addVertex(25D, 14D, 0D);
                    
                    var15.addVertex(-25.5D, 13.5D, 0D);
                    var15.addVertex(-25.5D, 16.5D, 0D);
                    var15.addVertex(-25D, 16.5D, 0D);
                    var15.addVertex(-25D, 13.5D, 0D);
                    var15.addVertex(25D, 13.5D, 0D);
                    var15.addVertex(25D, 16.5D, 0D);
                    var15.addVertex(25.5D, 16.5D, 0D);
                    var15.addVertex(25.5D, 13.5D, 0D);
                    var15.addVertex(-25D, 16D, 0D);
                    var15.addVertex(-25D, 16.5D, 0D);
                    var15.addVertex(25D, 16.5D, 0D);
                    var15.addVertex(25D, 16D, 0D);
                }
                
                //health bar
                var15.setColorRGBA_F(1F, 0F, 0F, 1F);
                var15.addVertex(-25D, 9D, 0D);
                var15.addVertex(-25D, 11D, 0D);
                var15.addVertex(health, 11D, 0D);
                var15.addVertex(health, 9D, 0D);
                
                //ammo bar
                var15.setColorRGBA_F(0F, 0.5F, 1F, 1F);
                var15.addVertex(-25D, 11.5D, 0D);
                var15.addVertex(-25D, 13.5D, 0D);
                var15.addVertex(ammo, 13.5D, 0D);
                var15.addVertex(ammo, 11.5D, 0D);
                
                //exp bar
                if(hasXP) {
                    var15.setColorRGBA_F(0F, 1F, 0.5F, 1F);
                    var15.addVertex(-25D, 14D, 0D);
                    var15.addVertex(-25D, 16D, 0D);
                    var15.addVertex(exp, 16D, 0D);
                    var15.addVertex(exp, 14D, 0D);
                }
                
                var15.draw();                    
                GL11.glEnable(GL11.GL_TEXTURE_2D);
                GL11.glEnable(GL11.GL_LIGHTING);
                GL11.glDisable(GL11.GL_BLEND);
                GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
                GL11.glPopMatrix();
            }
        }
    }
}
