package de.mpw.betterbeacon.gui;

import java.util.Iterator;

import org.lwjgl.opengl.GL11;

import de.mpw.betterbeacon.CommonProxy;
import de.mpw.betterbeacon.TileEntityBetterBeacon;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerBeacon;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;

public class GuiBetterBeacon extends GuiContainer {

	private TileEntityBetterBeacon beacon;

	public GuiBetterBeacon(InventoryPlayer inventory, TileEntityBetterBeacon tile_entity) {
		super(new ContainerBetterBeacon(tile_entity, inventory));
		//super(new ContainerBeacon(inventory, tile_entity));
		this.beacon = tile_entity;
        this.xSize = 230;
        this.ySize = 219;
	}

	@Override
	public void initGui() {
		super.initGui();
		this.buttonList.add(new GuiButton(1,this.guiLeft+155, this.guiTop+ 108,22,20, "OK"));
		this.buttonList.add(new GuiBetterBeaconButton(2, this.guiLeft+185, this.guiTop + 108, 22, 22, CommonProxy.BETTER_BEACON_GUI, 88, 219));
		
	}

	@Override
	public void updateScreen() {
		super.updateScreen();
		
	}

	@Override
	protected void actionPerformed(GuiButton par1GuiButton) {
		
		super.actionPerformed(par1GuiButton);
		
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
		//fontRenderer.drawString("Tutorial Gui", 6, 6, 0xffffff);
		// This draws the caption for the players inventory this is not needed
		// as the above but is sometimes nice
		//fontRenderer.drawString(StatCollector.translateToLocal("container.inventory"), 6, ySize - 96 + 2, 0xffffff);
		RenderHelper.disableStandardItemLighting();
        this.drawCenteredString(fontRenderer, "Level:" + beacon.getLevels(), 30, 13, 14737632);
        Iterator iterator = this.buttonList.iterator();

        while (iterator.hasNext())
        {
            GuiButton guibutton = (GuiButton)iterator.next();

            if (guibutton.func_82252_a())
            {
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
		this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
		itemRenderer.zLevel = 100.0F;
		itemRenderer.renderItemAndEffectIntoGUI(this.fontRenderer, this.mc.renderEngine, new ItemStack(Item.emerald), k + 42, l + 109);
		itemRenderer.renderItemAndEffectIntoGUI(this.fontRenderer, this.mc.renderEngine, new ItemStack(Item.diamond), k + 42 + 22, l + 109);
		itemRenderer.renderItemAndEffectIntoGUI(this.fontRenderer, this.mc.renderEngine, new ItemStack(Item.ingotGold), k + 42 + 44, l + 109);
		itemRenderer.renderItemAndEffectIntoGUI(this.fontRenderer, this.mc.renderEngine, new ItemStack(Item.ingotIron), k + 42 + 66, l + 109);
		itemRenderer.zLevel = 0.0F;

	}

}
