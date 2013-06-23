package de.mpw.betterbeacon.gui;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.util.Iterator;

import org.lwjgl.opengl.GL11;

import de.mpw.betterbeacon.BetterBeacon;
import de.mpw.betterbeacon.CommonProxy;
import de.mpw.betterbeacon.TileEntityBetterBeacon;
import de.mpw.betterbeacon.render.ModelBetterBeacon;
import de.mpw.betterbeacon.render.ModelBetterBeaconTechne;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerBeacon;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.potion.Potion;
import net.minecraft.util.StatCollector;

public class GuiBetterBeacon extends GuiContainer {

	private TileEntityBetterBeacon beacon;
	private static final short pay_pos_y = 145;
	private static final short pay_pos_x = 150;

	/**
	 * @param inventory
	 *            of the interacting player
	 * @param tile_entity
	 *            of the clicked beacon
	 */
	public GuiBetterBeacon(InventoryPlayer inventory, TileEntityBetterBeacon tile_entity) {
		super(new ContainerBetterBeacon(tile_entity, inventory));
		// super(new ContainerBeacon(inventory, tile_entity));
		this.beacon = tile_entity;
		this.xSize = 256;
		this.ySize = 256;
	}

	@Override
	public void initGui() {
		super.initGui();
		this.buttonList.add(new GuiButton(1, this.guiLeft + 155, this.guiTop + 108, 22, 20, "OK"));
		this.buttonList.add(new GuiBetterBeaconButton(2, this.guiLeft + 185, this.guiTop + 108, 22, 22, "/gui/beacon.png", 90, 220));
		this.buttonList.add(new GuiBetterBeaconEffect(3, this.guiLeft + 40, this.guiTop + 22, 18, 18, 1, this));
		byte vertical_spacing = 36;
		for (int i = 0; i < beacon.effectsList.length; i++) {
			Potion[] array_element = beacon.effectsList[i];
			for (int j = 0; j < array_element.length; j++) {
				Potion potion = array_element[j];
				this.buttonList.add(new GuiBetterBeaconEffect(3 + (i + 1) * j, this.guiLeft + 40 + (j * 25), this.guiTop + 22
						+ (i * vertical_spacing), 18, 18, potion.getId(), this));

			}

		}
	}

	@Override
	public void updateScreen() {
		super.updateScreen();
		int ef = beacon.getEffect();
		for (Object b : buttonList) {
			if (b.getClass().equals(GuiBetterBeaconEffect.class)) {
				GuiBetterBeaconEffect e = (GuiBetterBeaconEffect) b;
				if (ef == e.getPotionId()) {
					e.activated = true;
				}
			}
		}
		/*
		 * ((GuiBetterBeaconEffect)this.buttonList.get(2)).xPosition =
		 * this.guiLeft + 40;
		 * ((GuiBetterBeaconEffect)this.buttonList.get(2)).yPosition =
		 * this.guiTop + 22;
		 */
	}

	@Override
	protected void actionPerformed(GuiButton button) {

		// super.actionPerformed(button);
		if (button.id > 2 && button.getClass().equals(GuiBetterBeaconEffect.class)) {
			GuiBetterBeaconButton butbetter = (GuiBetterBeaconButton) button;
			if (!butbetter.activated) {
				for (Iterator iterator = this.buttonList.iterator(); iterator.hasNext();) {
					GuiButton guibutton = (GuiButton) iterator.next();
					if (guibutton.getClass().equals(GuiBetterBeaconEffect.class)) {
						GuiBetterBeaconEffect b = (GuiBetterBeaconEffect) guibutton;
						b.activated = false;
					}
					butbetter.activated = true;
				}
			} else {
				butbetter.activated = false;
			}
			this.beacon.setEffect(((GuiBetterBeaconEffect) button).getPotionId());
		}
		if (button.id == 1) {
			ByteArrayOutputStream bytearrayoutputstream = new ByteArrayOutputStream();
			DataOutputStream dataoutputstream = new DataOutputStream(bytearrayoutputstream);

			try {
				// Write Type
				dataoutputstream.writeInt(this.beacon.getEffect());
				// Write Strength
				dataoutputstream.writeInt(this.beacon.getEffectstrength());
				// Write the states of the affected Mob types
				dataoutputstream.writeBoolean(this.beacon.isPlayereffect());
				dataoutputstream.writeBoolean(this.beacon.isAnimaleffect());
				dataoutputstream.writeBoolean(this.beacon.isMobeffect());
				this.mc.getNetHandler().addToSendQueue(new Packet250CustomPayload(BetterBeacon.guiChannel, bytearrayoutputstream.toByteArray()));
			} catch (Exception exception) {
				exception.printStackTrace();
			}

			this.mc.displayGuiScreen((GuiScreen) null);
		}

	}

	// This method is required and all it does is draw the foreground
	// and overlays
	protected void drawGuiContainerForegroundLayer(int par1, int par2) {
		// Draws The title of the Gui like "Furnace" for the furnace,
		// It has 4 params
		// @param "Tutorial Gui" this is the Name in String format
		// @param int 6, this is the xCoord on the screen
		// @param int 6, this is the yCoord on the screen
		// @param 0xffffff, this is the color in hex, 0xffffff, is white,
		// 0x000000 is black BTW
		// fontRenderer.drawString("Tutorial Gui", 6, 6, 0xffffff);
		// This draws the caption for the players inventory this is not needed
		// as the above but is sometimes nice
		// fontRenderer.drawString(StatCollector.translateToLocal("container.inventory"),
		// 6, ySize - 96 + 2, 0xffffff);
		RenderHelper.disableStandardItemLighting();
		this.drawCenteredString(fontRenderer, "Level:" + beacon.getLevels(), 30, 13, 14737632);
		Iterator iterator = this.buttonList.iterator();

		while (iterator.hasNext()) {
			GuiButton guibutton = (GuiButton) iterator.next();

			if (guibutton.func_82252_a()) {
				guibutton.func_82251_b(par1 - this.guiLeft, par2 - this.guiTop);
				break;
			}
		}

		RenderHelper.enableGUIStandardItemLighting();
	}

	// This is another required method and has 3 params
	// @param float f, this is just something that is not required here,
	// something I shouldn't have to go indepth with
	// @param int i, int j, these are just like the later
	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int i, int j) {
		// This is the integer value (?Idk why though?) of the background you
		// want, basically replace "<path>" with what the path
		// To your file is
		// int picture = mc.renderEngine.getTexture("<path>");
		// This is reuquired and important not to be missed, this allows the
		// color spectrum to be alloted by open GL11
		GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
		// This is the method to bind the picture you defined above to the
		// background, this is required to have a background on
		// On the screen
		this.mc.renderEngine.bindTexture(CommonProxy.BETTER_BEACON_GUI);
		int k = (this.width - this.xSize) / 2;
		int l = (this.height - this.ySize) / 2;
		// Draws the main gui texture to the center of the screen
		this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
		itemRenderer.zLevel = 100.0F;
		byte spacing = 18;
		itemRenderer.renderItemAndEffectIntoGUI(this.fontRenderer, this.mc.renderEngine, new ItemStack(Item.emerald), k + pay_pos_x, l + pay_pos_y);
		itemRenderer.renderItemAndEffectIntoGUI(this.fontRenderer, this.mc.renderEngine, new ItemStack(Item.diamond), k + pay_pos_x + spacing, l
				+ pay_pos_y);
		itemRenderer.renderItemAndEffectIntoGUI(this.fontRenderer, this.mc.renderEngine, new ItemStack(Item.ingotGold), k + pay_pos_x + spacing * 2,
				l + pay_pos_y);
		itemRenderer.renderItemAndEffectIntoGUI(this.fontRenderer, this.mc.renderEngine, new ItemStack(Item.ingotIron), k + pay_pos_x + spacing * 3,
				l + pay_pos_y);
		itemRenderer.zLevel = 0.0F;

	}

}
