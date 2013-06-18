package de.mpw.betterbeacon.gui;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiBeacon;
import net.minecraft.potion.Potion;

public class GuiBetterBeaconEffect extends GuiBetterBeaconButton {

	private int potionId;
	final GuiBetterBeacon beaconGui;

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
		this.activated = true;
		this.beaconGui = gui;
		this.potionId = potionId;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.mpw.betterbeacon.gui.GuiBetterBeaconButton#drawButton(net.minecraft
	 * .client.Minecraft, int, int)
	 */
	@Override
	public void drawButton(Minecraft minecraft, int mxPos, int myPos) {
		// Draws the button with the potion icon to the gui
		if (this.activated) {
			minecraft.renderEngine.bindTexture(buttonTexture);
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			this.mouseOverButton = mxPos >= this.xPosition && myPos >= this.yPosition && mxPos < this.xPosition + this.width
					&& myPos < this.yPosition + this.height;
			// short buttonYPos = 219;
			int k = 0;
			// this.drawTexturedModalRect(this.xPosition, this.yPosition, k,
			// buttonYPos, this.width, this.height);
			this.drawTexturedModalRect(this.xPosition, this.yPosition, u, v, this.width, this.height);

		}
	}

}
