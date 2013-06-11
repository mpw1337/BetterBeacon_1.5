package de.mpw.betterbeacon.render;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.client.FMLClientHandler;
import de.mpw.betterbeacon.BetterBeacon;
import de.mpw.betterbeacon.CommonProxy;
import de.mpw.betterbeacon.TileEntityBetterBeacon;
import net.minecraft.client.renderer.tileentity.TileEntityBeaconRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;

public class ItemBetterBeaconRenderer implements IItemRenderer {
	private ModelBetterBeaconTechne model;
	private TileEntityBeaconRenderer render = new TileEntityBeaconRenderer();

	public ItemBetterBeaconRenderer() {
		this.model = new ModelBetterBeaconTechne();
	}

	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type) {
		return true;
	}

	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item,
			ItemRendererHelper helper) {
		return true;
	}

	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
		switch(type)
		{
			case ENTITY:{
				renderTutBox(0f, 0f, -0.1F, 1f);
				return;
			}
			
			case EQUIPPED:{
				//renderTutBox(0.5F, -0.5F, 0.5F, 0.8f);
				renderTutBox(0f, 1f, 1f, 0.5f);
				return;
			}
				
			case INVENTORY:{
				renderTutBox(0F, -1F, 0F, 1f);
				return;
			}
			
			default:return;
		}
	}

	private void renderTutBox(float x, float y, float z, float scale) {
		GL11.glPushMatrix();

		// Disable Lighting Calculations
		GL11.glDisable(GL11.GL_LIGHTING);
		GL11.glTranslatef(x,  y,  z);

		GL11.glScalef(scale, scale, scale);
		GL11.glRotatef(180f, 0f, 1f, 0f);
		
		FMLClientHandler.instance().getClient().renderEngine.bindTexture(CommonProxy.BETTER_BEACON_TEXTURE);
		
		model.render(x,y,z);
		
		// Re-enable Lighting Calculations
		GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glPopMatrix();
		
	}
}
