package com.gameminers.farrago.proxy;

import java.io.File;
import java.util.Random;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;

import com.gameminers.farrago.FarragoMod;
import com.gameminers.farrago.Masses;
import com.gameminers.farrago.entity.EntityRifleProjectile;
import com.gameminers.farrago.enums.RifleMode;
import com.typesafe.config.ConfigException;
import com.typesafe.config.ConfigFactory;

import cpw.mods.fml.common.registry.GameData;

public class ServerProxy implements Proxy {

	@Override public void postInit() {
		for (Object o : GameData.getItemRegistry()) {
			if (o instanceof Item) { // should always be true, but just to be sure
				Item i = (Item) o;
				try {
					Masses.calculateMass(i, 0, 32767);
				} catch (StackOverflowError error) {
					continue;
				} catch (Exception e) {
					e.printStackTrace();
					System.err.println("Dazed and confused, but trying to continue");
					continue;
				}
			}
		}
		Masses.bake();
	}
	@Override public void init() {}
	@Override
	public void preInit() {
		File configFile = new File(Minecraft.getMinecraft().mcDataDir, "config/farrago.conf");
		FarragoMod.config = ConfigFactory.parseFile(configFile);
		try {
			FarragoMod.brand = FarragoMod.config.getString("modpack.brand");
		} catch (ConfigException.Null ex) {
			FarragoMod.brand = null;
		}
		FarragoMod.showBrand = FarragoMod.config.getBoolean("modpack.showBrand");
	}
	@Override public void spawnRifleParticle(RifleMode mode, EntityRifleProjectile proj) {}
	@Override public void stopSounds() {}
	@Override public void glowRandomDisplayTick(World world, int x, int y, int z,Random rand) {}
	@Override public void scope(EntityPlayer player) {}
	@Override public void tooltip(ItemTooltipEvent e) {}

}
