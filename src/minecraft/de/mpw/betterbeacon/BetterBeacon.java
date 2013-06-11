package de.mpw.betterbeacon;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.src.ModLoader;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PostInit;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import de.mpw.betterbeacon.gui.GuiHandler;

@Mod(modid = "BetterBeacon", name = "Better Beacon", version = "0.0.0")
@NetworkMod(clientSideRequired = true, serverSideRequired = false)
public class BetterBeacon {
	private final static int BlockBetterBeaconID = 510;
	public final static int BetterBeaconGuiId = 0;
	// GenericBlock
	public static Block betterBeacon = new BlockBetterBeacon(BlockBetterBeaconID);
	//BetterBeaconTileEntity
	public static TileEntityBetterBeacon tileEntityBetterBeacon = new TileEntityBetterBeacon();
	// The instance of your mod that Forge uses.
	@Instance("BetterBeacon")
	public static BetterBeacon instance = new BetterBeacon();
	public static BlockBetterBeacon blockbetterbeacon;
	private GuiHandler guiHandler = new GuiHandler();
	

	// Says where the client and server 'proxy' code is loaded.
	@SidedProxy(clientSide = "de.mpw.betterbeacon.client.ClientProxy", serverSide = "de.mpw.BetterBeacon.CommonProxy")
	public static CommonProxy proxy;

	@PreInit
	public void preInit(FMLPreInitializationEvent event) {
		// Stub Method
	}

	@Init
	public void load(FMLInitializationEvent event) {
		proxy.initializeRendering();
		NetworkRegistry.instance().registerGuiHandler(this, guiHandler);
		GameRegistry.registerBlock(betterBeacon,"betterBeacon");
		LanguageRegistry.addName(betterBeacon, "Better Beacon");
		MinecraftForge.setBlockHarvestLevel(betterBeacon, "pickaxe", 1);
		GameRegistry.registerTileEntity(de.mpw.betterbeacon.TileEntityBetterBeacon.class, "tileEntityBetterBeacon");
	}

	@PostInit
	public void postInit(FMLPostInitializationEvent event) {
		// Stub Method
	}
}