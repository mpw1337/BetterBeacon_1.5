package de.mpw.betterbeacon.gui;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL40;

import de.mpw.betterbeacon.CommonProxy;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiBeacon;
import net.minecraft.potion.Potion;
import net.minecraft.util.StatCollector;

public class GuiBetterBeaconEffect extends GuiBetterBeaconButton {

	private int potionId;
	final GuiBetterBeacon beaconGui;
	private static final byte offset = 2;

	/**
	 * @param id
	 *            of the element, used in
	 *            {@link GuiBetterBeacon.actionPerformed}
	 * @param xPos
	 * @param yPos
	 * @param width
	 * @param height
	 * @param potionId
	 *            of the Potion effect to be shown
	 * @param gui
	 *            reference to {@link GuiBetterBeacon}
	 */
	public GuiBetterBeaconEffect(int id, int xPos, int yPos, int width, int height, int potionId, GuiBetterBeacon gui) {
		super(id, xPos, yPos, width, height, "/gui/inventory.png", 0 + Potion.potionTypes[potionId].getStatusIconIndex() % 8 * 18,
				198 + Potion.potionTypes[potionId].getStatusIconIndex() / 8 * 18);
		this.activated = false;
		this.beaconGui = gui;
		this.potionId = potionId;

	}
	public int getPotionId(){
		return potionId;
	}
}
