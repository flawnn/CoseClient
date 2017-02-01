package net.minecraft.client.model;

import me.cose.skins.SkinManager;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;

public class ModelPlayer extends ModelBiped
{
    public ModelRenderer field_178734_a;
    public ModelRenderer field_178732_b;
    public ModelRenderer field_178733_c;
    public ModelRenderer field_178731_d;
    public ModelRenderer field_178730_v;
    private ModelRenderer cape;
    private ModelRenderer headmau5;
    private boolean field_178735_y;
    private static final String __OBFID = "CL_00002626";

    public ModelPlayer(float unkown, boolean isSlim)
    {
        super(unkown, 0.0F, 64, 64);
        this.field_178735_y = isSlim;
        this.headmau5 = new ModelRenderer(this, 24, 0);
        this.headmau5.addBox(-3.0F, -6.0F, -1.0F, 6, 6, 1, unkown);
        this.cape = new ModelRenderer(this, 0, 0);
        this.cape.setTextureSize(64, 32);
        this.cape.addBox(-5.0F, 0.0F, -1.0F, 10, 16, 1, unkown);
        SkinManager.loadModels(this, unkown);

        if (isSlim)
        {
            this.bipedLeftArm = new ModelRenderer(this, 32, 48);
            this.bipedLeftArm.addBox(-1.0F, -2.0F, -2.0F, 3, 12, 4, unkown);
            this.bipedLeftArm.setRotationPoint(5.0F, 2.5F, 0.0F);
            this.bipedRightArm = new ModelRenderer(this, 40, 16);
            this.bipedRightArm.addBox(-2.0F, -2.0F, -2.0F, 3, 12, 4, unkown);
            this.bipedRightArm.setRotationPoint(-5.0F, 2.5F, 0.0F);
            this.field_178734_a = new ModelRenderer(this, 48, 48);
            this.field_178734_a.addBox(-1.0F, -2.0F, -2.0F, 3, 12, 4, unkown + 0.25F);
            this.field_178734_a.setRotationPoint(5.0F, 2.5F, 0.0F);
            this.field_178732_b = new ModelRenderer(this, 40, 32);
            this.field_178732_b.addBox(-2.0F, -2.0F, -2.0F, 3, 12, 4, unkown + 0.25F);
            this.field_178732_b.setRotationPoint(-5.0F, 2.5F, 10.0F);
        }
        else
        {
            this.bipedLeftArm = new ModelRenderer(this, 32, 48);
            this.bipedLeftArm.addBox(-1.0F, -2.0F, -2.0F, 4, 12, 4, unkown);
            this.bipedLeftArm.setRotationPoint(5.0F, 2.0F, 0.0F);
            this.field_178734_a = new ModelRenderer(this, 48, 48);
            this.field_178734_a.addBox(-1.0F, -2.0F, -2.0F, 4, 12, 4, unkown + 0.25F);
            this.field_178734_a.setRotationPoint(5.0F, 2.0F, 0.0F);
            this.field_178732_b = new ModelRenderer(this, 40, 32);
            this.field_178732_b.addBox(-3.0F, -2.0F, -2.0F, 4, 12, 4, unkown + 0.25F);
            this.field_178732_b.setRotationPoint(-5.0F, 2.0F, 10.0F);
        }

        this.bipedLeftLeg = new ModelRenderer(this, 16, 48);
        this.bipedLeftLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, unkown);
        this.bipedLeftLeg.setRotationPoint(1.9F, 12.0F, 0.0F);
        this.field_178733_c = new ModelRenderer(this, 0, 48);
        this.field_178733_c.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, unkown + 0.25F);
        this.field_178733_c.setRotationPoint(1.9F, 12.0F, 0.0F);
        this.field_178731_d = new ModelRenderer(this, 0, 32);
        this.field_178731_d.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, unkown + 0.25F);
        this.field_178731_d.setRotationPoint(-1.9F, 12.0F, 0.0F);
        this.field_178730_v = new ModelRenderer(this, 16, 32);
        this.field_178730_v.addBox(-4.0F, 0.0F, -2.0F, 8, 12, 4, unkown + 0.25F);
        this.field_178730_v.setRotationPoint(0.0F, 0.0F, 0.0F);
    }

    /**
     * Sets the models various rotation angles then renders the model.
     */
    public void render(Entity p_78088_1_, float p_78088_2_, float p_78088_3_, float p_78088_4_, float p_78088_5_, float p_78088_6_, float p_78088_7_)
    {
        super.render(p_78088_1_, p_78088_2_, p_78088_3_, p_78088_4_, p_78088_5_, p_78088_6_, p_78088_7_);
        GlStateManager.pushMatrix();

        if (this.isChild)
        {
            float var8 = 2.0F;
            GlStateManager.scale(1.0F / var8, 1.0F / var8, 1.0F / var8);
            GlStateManager.translate(0.0F, 24.0F * p_78088_7_, 0.0F);
            this.field_178733_c.render(p_78088_7_);
            this.field_178731_d.render(p_78088_7_);
            this.field_178734_a.render(p_78088_7_);
            this.field_178732_b.render(p_78088_7_);
            this.field_178730_v.render(p_78088_7_);
        }
        else
        {
            if (p_78088_1_.isSneaking())
            {
                GlStateManager.translate(0.0F, 0.2F, 0.0F);
            }

            this.field_178733_c.render(p_78088_7_);
            this.field_178731_d.render(p_78088_7_);
            this.field_178734_a.render(p_78088_7_);
            this.field_178732_b.render(p_78088_7_);
            this.field_178730_v.render(p_78088_7_);
        }

        GlStateManager.popMatrix();
    }

    public void func_178727_b(float p_178727_1_)
    {
    	copyAnglesFromFirstToSecond(this.bipedHead, this.headmau5);
        this.headmau5.rotationPointX = 0.0F;
        this.headmau5.rotationPointY = 0.0F;
        this.headmau5.render(p_178727_1_);
    }

    public void func_178728_c(float p_178728_1_)
    {
        this.cape.render(p_178728_1_);
    }

    /**
     * Sets the model's various rotation angles. For bipeds, par1 and par2 are used for animating the movement of arms
     * and legs, where par1 represents the time(so that arms and legs swing back and forth) and par2 represents how
     * "far" arms and legs can swing at most.
     */
    public void setRotationAngles(float p_78087_1_, float p_78087_2_, float p_78087_3_, float p_78087_4_, float p_78087_5_, float p_78087_6_, Entity p_78087_7_)
    {
        super.setRotationAngles(p_78087_1_, p_78087_2_, p_78087_3_, p_78087_4_, p_78087_5_, p_78087_6_, p_78087_7_);
        copyAnglesFromFirstToSecond(this.bipedLeftLeg, this.field_178733_c);
        copyAnglesFromFirstToSecond(this.bipedRightLeg, this.field_178731_d);
        copyAnglesFromFirstToSecond(this.bipedLeftArm, this.field_178734_a);
        copyAnglesFromFirstToSecond(this.bipedRightArm, this.field_178732_b);
        copyAnglesFromFirstToSecond(this.bipedBody, this.field_178730_v);
        
        if (p_78087_7_.isSneaking())
            this.cape.rotationPointY = 2.0F;
        else
            this.cape.rotationPointY = 0.0F;
    }
    
    public void updateCape(boolean sneaking)
    {
    	if(sneaking)
    		this.cape.offsetZ = 0.1F;
    	else
    		this.cape.offsetZ = 0.0F;
    }

    public void func_178725_a()
    {
        this.bipedRightArm.render(0.0625F);
        this.field_178732_b.render(0.0625F);
    }

    public void func_178726_b()
    {
        this.bipedLeftArm.render(0.0625F);
        this.field_178734_a.render(0.0625F);
    }

    public void func_178719_a(boolean show)
    {
        super.func_178719_a(show);
        this.field_178734_a.showModel = show;
        this.field_178732_b.showModel = show;
        this.field_178733_c.showModel = show;
        this.field_178731_d.showModel = show;
        this.field_178730_v.showModel = show;
        this.cape.showModel = show;
        this.headmau5.showModel = show;
        SkinManager.showModel(show);
    }

    public void postRenderHiddenArm(float p_178718_1_)
    {
        if (this.field_178735_y)
        {
            ++this.bipedRightArm.rotationPointX;
            this.bipedRightArm.postRender(p_178718_1_);
            --this.bipedRightArm.rotationPointX;
        }
        else
        {
            this.bipedRightArm.postRender(p_178718_1_);
        }
    }
}
