package de.mpw.betterbeacon.client;

import cpw.mods.fml.client.registry.ClientRegistry;
import net.minecraftforge.client.MinecraftForgeClient;
import de.mpw.betterbeacon.CommonProxy;
import de.mpw.betterbeacon.TileEntityBetterBeacon;
import de.mpw.betterbeacon.render.TileEntityBetterBeaconRenderer;

public class ClientProxy extends CommonProxy {
	@Override
	public void registerRenderers() {
		/*MinecraftForgeClient.preloadTexture(ITEMS_PNG);
		MinecraftForgeClient.preloadTexture(BLOCK_PNG);*/
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityBetterBeacon.class, new TileEntityBetterBeaconRenderer());
	}
}
