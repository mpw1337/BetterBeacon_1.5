package de.mpw.betterbeacon.render;

import org.lwjgl.opengl.GL11;

import de.mpw.betterbeacon.BetterBeacon;
import de.mpw.betterbeacon.CommonProxy;
import de.mpw.betterbeacon.TileEntityBetterBeacon;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class TileEntityBetterBeaconRenderer extends TileEntitySpecialRenderer {

	// private ModelBetterBeaconTechne model = new ModelBetterBeaconTechne();
	private ModelBetterBeaconTechne model = new ModelBetterBeaconTechne();

	// This method is called when minecraft renders a tile entity
	public void renderTileEntityAt(TileEntity tileEntity, double x, double y, double z, float tick) {
		GL11.glPushMatrix();
		// This will move our renderer so that it will be on proper place in the
		// world
		GL11.glTranslatef((float) x, (float) y, (float) z);
		TileEntityBetterBeacon tileEntityBetterBeacon = (TileEntityBetterBeacon) tileEntity;
		/*
		 * Note that true tile entity coordinates (tileEntity.xCoord, etc) do
		 * not match to render coordinates (d, etc) that are calculated as [true
		 * coordinates] - [player coordinates (camera coordinates)]
		 */
		renderBetterBeaconBlock(tileEntityBetterBeacon, tileEntity.worldObj, tileEntity.xCoord, tileEntity.yCoord, tileEntity.zCoord,
				BetterBeacon.betterBeacon);
		GL11.glPopMatrix();
	}

	// And this method actually renders your tile entity
	public void renderBetterBeaconBlock(TileEntityBetterBeacon tl, World world, int i, int j, int k, Block block) {
		Tessellator tessellator = Tessellator.instance;
		// This will make your block brightness dependent from surroundings
		// lighting.
		float f = block.getBlockBrightness(world, i, j, k);
		int l = world.getLightBrightnessForSkyBlocks(i, j, k, 0);
		int l1 = l % 65536;
		int l2 = l / 65536;
		tessellator.setColorOpaque_F(f, f, f);
		OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, (float) l1, (float) l2);

		/*
		 * This will rotate your model corresponding to player direction that
		 * was when you placed the block. If you want this to work, add these
		 * lines to onBlockPlacedBy method in your block class. int dir =
		 * MathHelper.floor_double((double)((player.rotationYaw * 4F) / 360F) +
		 * 0.5D) & 3; world.setBlockMetadataWithNotify(x, y, z, dir, 0);
		 */

		int dir = world.getBlockMetadata(i, j, k);

		GL11.glPushMatrix();
		GL11.glTranslatef(0.5F, -0.5F, 0.5F);
		// This line actually rotates the renderer.
		GL11.glRotatef(dir * (-90F), 0F, 1F, 0F);
		GL11.glTranslatef(-0.5F, 0, -0.5F);
		bindTextureByName(CommonProxy.BETTER_BEACON_TEXTURE);
		// ModelBetterBeaconTechne temp = new ModelBetterBeaconTechne();
		// temp.render(tl, 0.0F, 0.0F, -0.0F, 0.0F, 0.0F, 0.0625F);
		this.model.render(tl, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);

		GL11.glPopMatrix();
	}
}
