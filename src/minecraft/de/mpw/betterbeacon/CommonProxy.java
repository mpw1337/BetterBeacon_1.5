package de.mpw.betterbeacon;

/**
 * @author Markus this class stores references/path to resources
 */
public class CommonProxy {
	public static String ITEMS_PNG = "/tutorial/generic/items.png";
	public static String BLOCK_PNG = "/tutorial/generic/block.png";
	public static String BETTER_BEACON_GUI = "/de/mpw/betterbeacon/gui/beacon.png";
	public static String BETTER_BEACON_MODEL = "de/mpw/betterbeacon/render/betterBeacon.obj";
	public static String BETTER_BEACON_TEXTURE = "/de/mpw/betterbeacon/render/BetterBeacon.png";

	// Client stuff
	public void initializeRendering() {
		// Nothing here as the server doesn't render graphics!
	}
}
