package de.mpw.betterbeacon.client;

import cpw.mods.fml.client.registry.ClientRegistry;
import net.minecraftforge.client.MinecraftForgeClient;
import de.mpw.betterbeacon.BetterBeacon;
import de.mpw.betterbeacon.CommonProxy;
import de.mpw.betterbeacon.TileEntityBetterBeacon;
import de.mpw.betterbeacon.render.ItemBetterBeaconRenderer;
import de.mpw.betterbeacon.render.TileEntityBetterBeaconRenderer;

public class ClientProxy extends CommonProxy {
	@Override
	public void initializeRendering(){		
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityBetterBeacon.class, new TileEntityBetterBeaconRenderer());
		MinecraftForgeClient.registerItemRenderer(BetterBeacon.betterBeacon.blockID, new ItemBetterBeaconRenderer());
	}
	
}
