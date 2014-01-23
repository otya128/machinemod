package otya.machinemod.renderer;

import org.lwjgl.opengl.*;

import otya.machinemod.model.ModelWindMill;
import otya.machinemod.tileentity.WindMillTileEntity;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.util.ResourceLocation;

public class WindMillSpecialRenderer extends TileEntitySpecialRenderer {
	ResourceLocation tex=new ResourceLocation("");
	ModelWindMill mwm=new ModelWindMill();
	@Override
	public void renderTileEntityAt(TileEntity tileentity, double d0, double d1,
			double d2, float f) {
		WindMillTileEntity windmill=(WindMillTileEntity)tileentity;
		// TODO 自動生成されたメソッド・スタブ
		this.bindTexture(tex);
		GL11.glPushMatrix();
		//GL11.glTranslatef((float)d0, (float)d1, (float)d2);
		

        //GL11.glEnable(GL12.GL_RESCALE_NORMAL);
        //GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		//mwm.box.setRotationPoint((float)d0, (float)d1, (float)d2);
		mwm.box.offsetX=(float)d0+0.5f;mwm.box.offsetY=(float)d1+0.5f;mwm.box.offsetZ=(float)d2+0.5f;

        GL11.glEnable(GL12.GL_RESCALE_NORMAL);
       // GL11.glScalef(0.5F, 0.5F, 0.5F);
        //mwm.box.setRotationPoint(5, 5, 5);
        mwm.box.rotateAngleX=windmill.rotateX;//GL11.glRotatef(128f, 1f, 1f, 1f);
		mwm.box.render(0.0625F);
        GL11.glDisable(GL12.GL_RESCALE_NORMAL);
        //GL11.glDisable(GL12.GL_RESCALE_NORMAL);
		GL11.glPopMatrix();
	}

}
