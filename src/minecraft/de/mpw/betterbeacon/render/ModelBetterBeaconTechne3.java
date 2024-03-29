// Date: 04.06.2013 16:58:07
// Template version 1.1
// Java generated by Techne
// Keep in mind that you still need to fill in some blanks
// - ZeuX






package de.mpw.betterbeacon.render;

import de.mpw.betterbeacon.TileEntityBetterBeacon;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;

public class ModelBetterBeaconTechne3 extends ModelBase
{
  //fields
    ModelRenderer Glass;
    ModelRenderer Base;
    ModelRenderer InnerBlock;
	private int textureWidth;
	private int textureHeight;
  
  public ModelBetterBeaconTechne3()
  {
    textureWidth = 104;
    textureHeight = 32;
    
      Glass = new ModelRenderer(this, 0, 0);
      Glass.addBox(0F, 0F, 0F, 16, 16, 16);
      Glass.setRotationPoint(-8F, 8F, -8F);
      Glass.setTextureSize(104, 32);
      Glass.mirror = true;
      setRotation(Glass, 0F, 0F, 0F);
      Base = new ModelRenderer(this, 48, 0);
      Base.addBox(0F, 0F, 0F, 14, 2, 14);
      Base.setRotationPoint(-7F, 22F, -7F);
      Base.setTextureSize(128, 32);
      Base.mirror = false;
      setRotation(Base, 0F, 0F, 0F);
      InnerBlock = new ModelRenderer(this, 64, 32);
      InnerBlock.addBox(-4F, -4F, -4F, 8, 8, 8);
      InnerBlock.setRotationPoint(0F, 16F, 0F);
      InnerBlock.setTextureSize(128, 32);
      InnerBlock.mirror = false;
      setRotation(InnerBlock, 0F, 1.0853982F, 0.7853982F);
  }
  
  public void render(TileEntityBetterBeacon tl, float f, float f1, float f2, float f3, float f4, float f5)
  {
   // super.render(null, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5);
    Glass.setTextureSize(104, 32);
    Glass.setTextureOffset(0, 0);
    Glass.render(f5);
    //Base.render(f5);
    InnerBlock.setTextureOffset(64, 16);
    //InnerBlock.render(f5);
    //InnerBlock.rotateAngleX =(float) tl.worldObj.getWorldTime();
  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z)
  {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }
  
  public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.setRotationAngles(f, f1, f2, f3, f4, f5, null);
  }

}
