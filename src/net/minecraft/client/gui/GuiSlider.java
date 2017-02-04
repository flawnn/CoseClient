package net.minecraft.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;

public class GuiSlider extends GuiButton
{
    private float field_175227_p = 1.0F;
    public boolean field_175228_o;
    private String displayString;
    private final float field_175225_r;
    private final float field_175224_s;
    private final GuiPageButtonList.GuiResponder responder;
    private GuiSlider.FormatHelper formatHelper;
    private static final String __OBFID = "CL_00001954";

	public GuiSlider(GuiPageButtonList.GuiResponder responder, int id, int x, int y, String displayString, float p_i45541_6_, float p_i45541_7_, float p_i45541_8_, GuiSlider.FormatHelper formatHelper)
    {
        super(id, x, y, 150, 20, "");
        this.displayString = displayString;
        this.field_175225_r = p_i45541_6_;
        this.field_175224_s = p_i45541_7_;
        this.field_175227_p = (p_i45541_8_ - p_i45541_6_) / (p_i45541_7_ - p_i45541_6_);
        this.formatHelper = formatHelper;
        this.responder = responder;
        this.displayString = this.func_175221_e();
    }

    public float func_175220_c()
    {
        return this.field_175225_r + (this.field_175224_s - this.field_175225_r) * this.field_175227_p;
    }

    public void func_175218_a(float p_175218_1_, boolean p_175218_2_)
    {
        this.field_175227_p = (p_175218_1_ - this.field_175225_r) / (this.field_175224_s - this.field_175225_r);
        this.displayString = this.func_175221_e();

        if (p_175218_2_)
        {
            this.responder.func_175320_a(this.id, this.func_175220_c());
        }
    }

    public float func_175217_d()
    {
        return this.field_175227_p;
    }

    private String func_175221_e()
    {
        return this.formatHelper == null ? I18n.format(this.displayString, new Object[0]) + ": " + this.func_175220_c() : this.formatHelper.func_175318_a(this.id, I18n.format(this.displayString, new Object[0]), this.func_175220_c());
    }

    /**
     * Returns 0 if the button is disabled, 1 if the mouse is NOT hovering over this button and 2 if it IS hovering over
     * this button.
     */
    protected int getHoverState(boolean mouseOver)
    {
        return 0;
    }

    /**
     * Fired when the mouse button is dragged. Equivalent of MouseListener.mouseDragged(MouseEvent e).
     */
    protected void mouseDragged(Minecraft mc, int mouseX, int mouseY)
    {
        if (this.visible)
        {
            if (this.field_175228_o)
            {
                this.field_175227_p = (float)(mouseX - (this.xPosition + 4)) / (float)(this.width - 8);

                if (this.field_175227_p < 0.0F)
                {
                    this.field_175227_p = 0.0F;
                }

                if (this.field_175227_p > 1.0F)
                {
                    this.field_175227_p = 1.0F;
                }

                this.displayString = this.func_175221_e();
                this.responder.func_175320_a(this.id, this.func_175220_c());
            }

            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
            this.drawTexturedModalRect(this.xPosition + (int)(this.field_175227_p * (float)(this.width - 8)), this.yPosition, 0, 66, 4, 20);
            this.drawTexturedModalRect(this.xPosition + (int)(this.field_175227_p * (float)(this.width - 8)) + 4, this.yPosition, 196, 66, 4, 20);
        }
    }

    public void func_175219_a(float p_175219_1_)
    {
        this.field_175227_p = p_175219_1_;
        this.displayString = this.func_175221_e();
        this.responder.func_175320_a(this.id, this.func_175220_c());
    }

    /**
     * Returns true if the mouse has been pressed on this control. Equivalent of MouseListener.mousePressed(MouseEvent
     * e).
     */
    public boolean mousePressed(Minecraft mc, int mouseX, int mouseY)
    {
        if (super.mousePressed(mc, mouseX, mouseY))
        {
            this.field_175227_p = (float)(mouseX - (this.xPosition + 4)) / (float)(this.width - 8);

            if (this.field_175227_p < 0.0F)
            {
                this.field_175227_p = 0.0F;
            }

            if (this.field_175227_p > 1.0F)
            {
                this.field_175227_p = 1.0F;
            }

            this.displayString = this.func_175221_e();
            this.responder.func_175320_a(this.id, this.func_175220_c());
            this.field_175228_o = true;
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * Fired when the mouse button is released. Equivalent of MouseListener.mouseReleased(MouseEvent e).
     */
    public void mouseReleased(int mouseX, int mouseY)
    {
        this.field_175228_o = false;
    }

    public interface FormatHelper
    {
        String func_175318_a(int p_175318_1_, String p_175318_2_, float p_175318_3_);
    }
}
