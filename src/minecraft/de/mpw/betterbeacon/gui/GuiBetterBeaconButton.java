package de.mpw.betterbeacon.gui;

import org.lwjgl.opengl.GL11;

import de.mpw.betterbeacon.CommonProxy;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;

/**
 * @author Markus
 * 
 */
public class GuiBetterBeaconButton extends GuiButton {

	protected String buttonTexture;
	protected int u;
	protected int v;
	protected boolean mouseOverButton;
	protected boolean activated;

	/**
	 * @param id
	 *            of the GUI element (used in onButtonPressed Event)
	 * @param xPos
	 * @param yPos
	 * @param width
	 * @param height
	 * @param textureString
	 *            to the png of the button
	 * @param u
	 *            x offset of the texture
	 * @param v
	 *            y offset of the texture
	 */
	public GuiBetterBeaconButton(int id, int xPos, int yPos, int width, int height, String textureString, int u, int v) {
		super(id, xPos, yPos, width, height, "");
		this.buttonTexture = textureString;
		this.u = u;
		this.v = v;
		this.activated = false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.minecraft.client.gui.GuiButton#drawButton(net.minecraft.client.Minecraft
	 * , int, int)
	 */
	@Override
	public void drawButton(Minecraft minecraft, int mxPos, int myPos) {
		if (this.drawButton) {
			minecraft.renderEngine.bindTexture("/gui/beacon.png");
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			this.mouseOverButton = mxPos >= this.xPosition && myPos >= this.yPosition && mxPos < this.xPosition + 22
					&& myPos < this.yPosition + 22;
			short buttonYPos = 219;
			int k = 0;
			if (!this.enabled) {
				k += 22 * 2;
			} else if (this.activated) {
				k += 22 * 1;
			} else if (this.mouseOverButton) {
				k += 22* 3;
			}
			this.drawTexturedModalRect(this.xPosition, this.yPosition, k, buttonYPos, 22, 22);
			if(!this.buttonTexture.equals("/gui/beacon.png")){
				minecraft.renderEngine.bindTexture(this.buttonTexture);
			}
			this.drawTexturedModalRect(this.xPosition, this.yPosition, u, v, this.width, this.height);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @return is the Mouse over the button
	 * 
	 * @see net.minecraft.client.gui.GuiButton#func_82252_a()
	 */
	@Override
	public boolean func_82252_a() {
		return this.mouseOverButton;
	}

}
