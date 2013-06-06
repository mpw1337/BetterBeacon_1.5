package de.mpw.betterbeacon.render;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import de.mpw.betterbeacon.CommonProxy;
import de.mpw.betterbeacon.TileEntityBetterBeacon;
import net.minecraft.client.model.ModelBase;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;
@SideOnly(Side.CLIENT)
public class ModelBetterBeacon extends ModelBase {
	private IModelCustom modelBetterBeacon;
	public ModelBetterBeacon(){
		modelBetterBeacon = AdvancedModelLoader.loadModel(CommonProxy.BETTER_BEACON_MODEL);
	}
	
	protected void render(){
		modelBetterBeacon.renderAll();
	}
	
	public void render(TileEntityBetterBeacon betterBeacon, double x,double y, double z){
		//Push a blank matrix onto the stack
		GL11.glPushMatrix();
		//Move to correct Position, obj origin is in the center of object
		GL11.glTranslatef((float)x+0.5f, (float)y+0.5f,(float) z+0.5f);
	}
}
