package de.mpw.betterbeacon.gui;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL40;

import de.mpw.betterbeacon.CommonProxy;

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
		this.activated = false;
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
	/*@Override
	public void drawButton(Minecraft minecraft, int mxPos, int myPos) {
		// Draws the button with the potion icon to the gui
		if (this.drawButton) {

			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			this.mouseOverButton = mxPos >= this.xPosition && myPos >= this.yPosition && mxPos < this.xPosition + this.width
					&& myPos < this.yPosition + this.height;
			minecraft.renderEngine.bindTexture("/gui/gui.png");
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			this.field_82253_i = mxPos >= this.xPosition && myPos >= this.yPosition && mxPos < this.xPosition + this.width
					&& myPos < this.yPosition + this.height;
			int k = this.getHoverState(this.field_82253_i);
			this.drawTexturedModalRect(this.xPosition - 1, this.yPosition - 1, 0, 46 + k * 20, (this.width + 4) / 2, this.height);
			this.drawTexturedModalRect(this.xPosition + 1 + (this.width) / 2, this.yPosition - 1, 200 - (this.width + 2) / 2, 46 + k * 20,
					(this.width + 2) / 2, this.height);
			this.mouseDragged(minecraft, mxPos, myPos);
			int l = 14737632;
			this.enabled = false;
			if (!this.enabled) {
				l = -6250336;
			} else if (this.field_82253_i) {
				l = 16777120;
			}
			// this.drawTexturedModalRect(this.xPosition, this.yPosition, k,
			// buttonYPos, this.width, this.height);

			minecraft.renderEngine.bindTexture(buttonTexture);
			this.drawTexturedModalRect(this.xPosition, this.yPosition, u, v, this.width, this.height);

		}
	}*/

}
