package sanandreasp.mods.TurretMod3.client.packet;

import java.io.DataInputStream;
import java.io.IOException;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityLavaFX;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import sanandreasp.mods.TurretMod3.client.particle.EntityBulletShotFX;
import sanandreasp.mods.TurretMod3.client.particle.EntityForcefieldFX;
import sanandreasp.mods.TurretMod3.packet.PacketBase;

public class PacketRecvSpawnParticle extends PacketBase {

	@Override
	public void handle(DataInputStream iStream, EntityPlayer player) {
		try {
			short ID = iStream.readShort();
			float posX = iStream.readFloat();
			float posY = iStream.readFloat();
			float posZ = iStream.readFloat();
			int eID = iStream.readInt();
			Entity entity = eID > -1 ? Minecraft.getMinecraft().theWorld.getEntityByID(eID) : null;
			switch(ID) {
				case 0:
					for (int i = 0; i < 5; i++) {
						if (entity != null && entity.riddenByEntity != null && entity.riddenByEntity != player || Minecraft.getMinecraft().gameSettings.thirdPersonView > 0 || entity != null && entity.riddenByEntity == null)
							Minecraft.getMinecraft().effectRenderer.addEffect(new EntityBulletShotFX(Minecraft.getMinecraft().theWorld, posX + this.rand.nextFloat()*0.2F - 0.1F, posY - 0.1F, posZ + this.rand.nextFloat()*0.2F - 0.1F, 1F, 1F, 1F));
					}
					break;
				case 1:
					for (int i = 0; i < 5; i++) {
						if (entity != null && entity.riddenByEntity != null && entity.riddenByEntity != player || Minecraft.getMinecraft().gameSettings.thirdPersonView > 0 || entity != null && entity.riddenByEntity == null)
							Minecraft.getMinecraft().effectRenderer.addEffect(new EntityBulletShotFX(Minecraft.getMinecraft().theWorld, posX + this.rand.nextFloat()*0.2F - 0.1F, posY - 0.1F, posZ + this.rand.nextFloat()*0.2F - 0.1F, 0.8F, 0.2F, 0.2F));
					}
					break;
				case 2:
					if (entity != null && entity.riddenByEntity != null && entity.riddenByEntity != player || Minecraft.getMinecraft().gameSettings.thirdPersonView > 0 || entity != null && entity.riddenByEntity == null)
						Minecraft.getMinecraft().effectRenderer.addEffect(new EntityLavaFX(Minecraft.getMinecraft().theWorld, posX + this.rand.nextFloat()*0.2F - 0.1F, posY - 0.1F, posZ + this.rand.nextFloat()*0.2F - 0.1F));
					break;
				case 3:
					for (int i = 0; i < 5; i++) {
						if (entity != null && entity.riddenByEntity != null && entity.riddenByEntity != player || Minecraft.getMinecraft().gameSettings.thirdPersonView > 0 || entity != null && entity.riddenByEntity == null)
							Minecraft.getMinecraft().effectRenderer.addEffect(new EntityBulletShotFX(Minecraft.getMinecraft().theWorld, posX + this.rand.nextFloat()*0.2F - 0.1F, posY - 0.1F, posZ + this.rand.nextFloat()*0.2F - 0.1F, 0.2F, 0.2F, 0.8F).setGlowing());
					}
					break;
				case 4:
					if (entity != null)
						Minecraft.getMinecraft().effectRenderer.addEffect(new EntityBulletShotFX(Minecraft.getMinecraft().theWorld, entity.posX, entity.posY, entity.posZ, 0.2F, 0.2F, 0.8F).setGlowing());
					break;
				case 5:
					if (entity != null)
						Minecraft.getMinecraft().effectRenderer.addEffect(new EntityBulletShotFX(Minecraft.getMinecraft().theWorld, entity.posX, entity.posY, entity.posZ, 0.2F, 0.2F, 0.2F));
					break;
				case 6:
					if (entity != null) {
						for (int i1 = 0; i1 < 20; i1++) {
							double d4 = this.rand.nextGaussian() * 0.02D;
							double d1 = this.rand.nextGaussian() * 0.02D;
							double d2 = this.rand.nextGaussian() * 0.02D;
							double d3 = 10D;
							Minecraft.getMinecraft().theWorld.spawnParticle(
											"explode",
											(entity.posX + (this.rand.nextFloat()
													* entity.width * 2.0F))
													- entity.width - d4 * d3,
											(entity.posY + (this.rand.nextFloat() * entity.height))
													- d1 * d3,
											(entity.posZ + (this.rand.nextFloat()
													* entity.width * 2.0F))
													- entity.width - d2 * d3, d4, d1, d2);
						}
					}
					break;
				case 7:
					for (int i = 0; i < 5; i++) {
						if (entity != null && entity.riddenByEntity != null && entity.riddenByEntity != player || Minecraft.getMinecraft().gameSettings.thirdPersonView > 0 || entity != null && entity.riddenByEntity == null)
							Minecraft.getMinecraft().effectRenderer.addEffect(new EntityBulletShotFX(Minecraft.getMinecraft().theWorld, posX + this.rand.nextFloat()*0.2F - 0.1F, posY - 0.1F, posZ + this.rand.nextFloat()*0.2F - 0.1F, 0.106F, 0.482F, 0.419F).setGlowing());
					}
					break;
				case 8:
					if (entity != null)
						Minecraft.getMinecraft().effectRenderer.addEffect(new EntityBulletShotFX(Minecraft.getMinecraft().theWorld, entity.posX, entity.posY, entity.posZ, 0.106F, 0.482F, 0.419F).setGlowing());
					break;
				case 9:
					for (int i1 = 0; i1 < 20; i1++) {
						double d4 = this.rand.nextGaussian() * 0.02D;
						double d1 = this.rand.nextGaussian() * 0.02D;
						double d2 = this.rand.nextGaussian() * 0.02D;
						double d3 = 10D;
						Minecraft.getMinecraft().theWorld.spawnParticle(
										"largeexplode",
										(posX + (this.rand.nextFloat())) - d4 * d3,
										(posY + (this.rand.nextFloat())) - d1 * d3,
										(posZ + (this.rand.nextFloat())) - d2 * d3,
										d4, d1, d2);
					}
					break;
				case 10:
					for (int i = 0; i < 20; i++) {
						if (entity != null && entity.riddenByEntity != null && entity.riddenByEntity != player || Minecraft.getMinecraft().gameSettings.thirdPersonView > 0 || entity != null && entity.riddenByEntity == null)
							Minecraft.getMinecraft().effectRenderer.addEffect(new EntityBulletShotFX(Minecraft.getMinecraft().theWorld, posX + this.rand.nextFloat()*0.2F - 0.1F, posY - 0.1F, posZ + this.rand.nextFloat()*0.2F - 0.1F, 0.8F, 0.8F, 0.8F));
					}
					break;
				case 11:
					for (int i1 = 0; i1 < 6; i1++) {
						double d4 = this.rand.nextGaussian() * 0.02D;
						double d1 = this.rand.nextGaussian() * 0.02D;
						double d2 = this.rand.nextGaussian() * 0.02D;
						double d3 = 10D;
						Minecraft.getMinecraft().effectRenderer.addEffect(new EntityForcefieldFX(Minecraft.getMinecraft().theWorld,
										(posX + (this.rand.nextFloat())) - d4 * d3,
										(posY + (this.rand.nextFloat())) - d1 * d3,
										(posZ + (this.rand.nextFloat())) - d2 * d3,
										1F, 0F, 0F));
					}
					break;
				case 12:
					for (int i1 = 0; i1 < 100; i1++) {
						double d4 = this.rand.nextGaussian() * 0.02D;
						double d1 = this.rand.nextGaussian() * 0.02D;
						double d2 = this.rand.nextGaussian() * 0.02D;
						double d3 = 10D;
						Minecraft.getMinecraft().effectRenderer.addEffect(new EntityForcefieldFX(Minecraft.getMinecraft().theWorld,
								(posX + (this.rand.nextFloat())) - d4 * d3,
								(posY + (this.rand.nextFloat())) - d1 * d3,
								(posZ + (this.rand.nextFloat())) - d2 * d3,
								1F, 0F, 0F));
					}
					break;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
