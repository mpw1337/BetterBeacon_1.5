package de.mpw.betterbeacon.gui;

import org.lwjgl.opengl.GL11;

import de.mpw.betterbeacon.CommonProxy;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;

public class GuiBetterBeaconButton extends GuiButton {

	private String buttonTexture;
	private int u;
	private int v;
	private boolean mouseOverButton;
	private boolean activated;

	public GuiBetterBeaconButton(int id, int xPos, int yPos, int width, int height, String textureString, int u, int v) {
		super(id, xPos, yPos, width, height, "");
		this.buttonTexture = textureString;
		this.u = u;
		this.v = v;
		this.activated = false;
	}

	@Override
	public void drawButton(Minecraft minecraft, int mxPos, int myPos) {
		if (this.drawButton){
			minecraft.renderEngine.bindTexture(CommonProxy.BETTER_BEACON_GUI);
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			this.mouseOverButton = mxPos >= this.xPosition && myPos >= this.yPosition && mxPos < this.xPosition + this.width && myPos < this.yPosition + this.height;
			short buttonYPos = 219;
			int k = 0;
			if(!this.enabled){
				k+=this.width * 2;
			}else if (this.activated){
				k += this.width * 1;
			}else if(this.mouseOverButton){
				k+= this.width * 3;
			}
			this.drawTexturedModalRect(this.xPosition, this.yPosition, k, buttonYPos, this.width, this.height);
			this.drawTexturedModalRect(this.xPosition, this.yPosition,u, v, this.width, this.height);
		}
	}

	@Override
	public boolean func_82252_a() {
		return this.mouseOverButton;
	}

}
