package otya.machinemod.renderer;

import org.lwjgl.opengl.GL11;

import otya.machinemod.MachineMod;
import otya.machinemod.electric.tileentity.BaseElectricTileEntity;
import otya.machinemod.model.ModelWindMill;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;

public class RenderMachineBlock implements ISimpleBlockRenderingHandler {

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelID,
			RenderBlocks renderer) {
		// TODO 自動生成されたメソッド・スタブ
		if (modelID == this.getRenderId())
		{
			Tessellator tessellator = Tessellator.instance;
			//ココをいじるとブロックの大きさが変わる
			renderer.setRenderBounds(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
 
			//描画位置の調整。ココをいじると、中心にレンダー持ってきたり、遊べる
			GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
			//くコ:彡 、コピペ☆　RenderBlocksみてね
			tessellator.startDrawingQuads();
			tessellator.setNormal(0.0F, -1.0F, 0.0F);
			renderer.renderFaceYNeg(block, 0.0D, 0.0D, 0.0D, renderer.getBlockIconFromSideAndMetadata(block, 0, metadata));
			tessellator.draw();
			tessellator.startDrawingQuads();
			tessellator.setNormal(0.0F, 1.0F, 0.0F);
			renderer.renderFaceYPos(block, 0.0D, 0.0D, 0.0D, renderer.getBlockIconFromSideAndMetadata(block, 1, metadata));
			tessellator.draw();
			tessellator.startDrawingQuads();
			tessellator.setNormal(0.0F, 0.0F, -1.0F);
			renderer.renderFaceZNeg(block, 0.0D, 0.0D, 0.0D, renderer.getBlockIconFromSideAndMetadata(block, 2, metadata));
			tessellator.draw();
			tessellator.startDrawingQuads();
			tessellator.setNormal(0.0F, 0.0F, 1.0F);
			renderer.renderFaceZPos(block, 0.0D, 0.0D, 0.0D, renderer.getBlockIconFromSideAndMetadata(block, 3, metadata));
			tessellator.draw();
			tessellator.startDrawingQuads();
			tessellator.setNormal(-1.0F, 0.0F, 0.0F);
			renderer.renderFaceXNeg(block, 0.0D, 0.0D, 0.0D, renderer.getBlockIconFromSideAndMetadata(block, 4, metadata));
			tessellator.draw();
			tessellator.startDrawingQuads();
			tessellator.setNormal(1.0F, 0.0F, 0.0F);
			renderer.renderFaceXPos(block, 0.0D, 0.0D, 0.0D, renderer.getBlockIconFromSideAndMetadata(block, 5, metadata));
			tessellator.draw();
			//描画位置の調整。遊んだ後はお片づけ
			//上のヤツ→GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
			GL11.glTranslatef(0.5F, 0.5F, 0.5F);
		}
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z,
			Block block, int modelId, RenderBlocks renderer) {
		// TODO 自動生成されたメソッド・スタブ
		if (modelId == this.getRenderId())
		{
			//renderer.setOverrideBlockTexture(renderer.getBlockIcon(Block.carpet));
			if(block.blockID==MachineMod.WindMillID)
			{
				//ModelWindMill mwm=new ModelWindMill();
				//mwm.box.render(0.5f);
				GL11.glPushMatrix();
				GL11.glRotatef(101f, 1f,1f, 1f);
				GL11.glScalef(2.0f, 2.0f, 2f);
				renderer.setRenderBounds(0.0D, 1.0D, 0.0D, 0.1D, 4.0D, 0.5D);
				renderer.uvRotateTop=3;renderer.renderStandardBlock(block, x, y, z);
				renderer.setRenderBounds(0.0D, 0.5D, 1.0D, 0.1D, 1.0D, 4.0D);
				renderer.uvRotateTop=3;renderer.renderStandardBlock(block, x, y, z);
				renderer.setRenderBounds(0.0D, -4D, 0.5D, 0.1D, 0D, 1.0D);
				renderer.uvRotateTop=3;renderer.renderStandardBlock(block, x, y, z);
				renderer.setRenderBounds(0.0D, 0.0D, -4.0D, 0.1D, 0.5D, -0.0D);
				renderer.uvRotateTop=3;
				renderer.renderStandardBlock(block, x, y, z);
				//GL11.glRotatef(-128f, 0f,1f, 0f);
				GL11.glPopMatrix();
				Tessellator tessellator = new Tessellator();
				//ココをいじるとブロックの大きさが変わる
				renderer.setRenderBounds(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
	 
				//描画位置の調整。ココをいじると、中心にレンダー持ってきたり、遊べる
				GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
				//くコ:彡 、コピペ☆　RenderBlocksみてね
				int metadata=0;
				tessellator.startDrawingQuads();
				tessellator.setNormal(0.0F, -1.0F, 0.0F);
				renderer.renderFaceYNeg(block, 0.0D, 0.0D, 0.0D, renderer.getBlockIconFromSideAndMetadata(block, 0, metadata));
				tessellator.draw();
				tessellator.startDrawingQuads();
				tessellator.setNormal(0.0F, 1.0F, 0.0F);
				renderer.renderFaceYPos(block, 0.0D, 0.0D, 0.0D, renderer.getBlockIconFromSideAndMetadata(block, 1, metadata));
				tessellator.draw();
				tessellator.startDrawingQuads();
				tessellator.setNormal(0.0F, 0.0F, -1.0F);
				renderer.renderFaceZNeg(block, 0.0D, 0.0D, 0.0D, renderer.getBlockIconFromSideAndMetadata(block, 2, metadata));
				tessellator.draw();
				tessellator.startDrawingQuads();
				tessellator.setNormal(0.0F, 0.0F, 1.0F);
				renderer.renderFaceZPos(block, 0.0D, 0.0D, 0.0D, renderer.getBlockIconFromSideAndMetadata(block, 3, metadata));
				tessellator.draw();
				tessellator.startDrawingQuads();
				tessellator.setNormal(-1.0F, 0.0F, 0.0F);
				renderer.renderFaceXNeg(block, 0.0D, 0.0D, 0.0D, renderer.getBlockIconFromSideAndMetadata(block, 4, metadata));
				tessellator.draw();
				tessellator.startDrawingQuads();
				tessellator.setNormal(1.0F, 0.0F, 0.0F);
				renderer.renderFaceXPos(block, 0.0D, 0.0D, 0.0D, renderer.getBlockIconFromSideAndMetadata(block, 5, metadata));
				tessellator.draw();
				//描画位置の調整。遊んだ後はお片づけ
				//上のヤツ→GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
				GL11.glTranslatef(0.5F, 0.5F, 0.5F);
				return true;
			}
			else
				if(block.blockID==MachineMod.wireBlock.blockID)
				{
					//double x1=0.3,y1=0.3,z1=0.3,x2=0.7,y2=0.7,z2=0.7;
					
					BaseElectricTileEntity bete=(BaseElectricTileEntity)world.getBlockTileEntity(x, y, z);
					int j=-1;
					for(TileEntity i:bete.TileEntity)
					{
						j++;
						if(i instanceof BaseElectricTileEntity)
						switch(j)
						{
						case 0:
							if(!BaseElectricTileEntity.isPlateStatic(world.getBlockTileEntity(x, y-1, z), j+1)){
							renderer.setRenderBounds(0.3,0,0.3,0.7,0.3,0.7);
							renderer.renderStandardBlock(block, x, y, z);}
							break;
						case 1:
							if(!BaseElectricTileEntity.isPlateStatic(world.getBlockTileEntity(x, y+1, z), j-1)){
							renderer.setRenderBounds(0.3,0.7,0.3,0.7,1,0.7);
							renderer.renderStandardBlock(block, x, y, z);}
							break;
						case 4:
							if(!BaseElectricTileEntity.isPlateStatic(world.getBlockTileEntity(x-1, y, z), j+1)){
							renderer.setRenderBounds(0,0.3,0.3,0.3,0.7,0.7);
							renderer.renderStandardBlock(block, x, y, z);}
							break;
						case 5:
							if(!BaseElectricTileEntity.isPlateStatic(world.getBlockTileEntity(x+1, y, z), j-1)){
							renderer.setRenderBounds(0.7,0.3,0.3,1,0.7,0.7);
							renderer.renderStandardBlock(block, x, y, z);}
							break;
						case 2:
							if(!BaseElectricTileEntity.isPlateStatic(world.getBlockTileEntity(x, y, z-1), j+1)){
							renderer.setRenderBounds(0.3,0.3,0,0.7,0.7,0.3);
							renderer.renderStandardBlock(block, x, y, z);}
							break;
						case 3:
							if(!BaseElectricTileEntity.isPlateStatic(world.getBlockTileEntity(x, y, z+1), j-1)){
							renderer.setRenderBounds(0.3,0.3,0.7,0.7,0.7,1);
							renderer.renderStandardBlock(block, x, y, z);}
							break;
						}
					}
					/*
					if(y1!=0.3)
					{
						renderer.setRenderBounds(0.3,0,0.3,0.7,0.3,0.7);
						renderer.renderStandardBlock(block, x, y, z);
					}
					if(y2!=0.7)
					{
						renderer.setRenderBounds(0.3,0.7,0.3,0.7,1,0.7);
						renderer.renderStandardBlock(block, x, y, z);
					}
					if(x1!=0.3)
					{
						renderer.setRenderBounds(0,0.3,0.3,0.3,0.7,0.7);
						renderer.renderStandardBlock(block, x, y, z);
					}
					if(x2!=0.7)
					{
						renderer.setRenderBounds(0.7,0.3,0.3,1,0.7,0.7);
						renderer.renderStandardBlock(block, x, y, z);
					}
					if(z1!=0.3)
					{
						renderer.setRenderBounds(0.3,0.3,0,0.7,0.7,0.3);
						renderer.renderStandardBlock(block, x, y, z);
					}
					if(z2!=0.7)
					{
						renderer.setRenderBounds(0.3,0.3,0.7,0.7,0.7,1);
						renderer.renderStandardBlock(block, x, y, z);
					}*/
					//if(y1==0.3&&y2==0.7&&x1==0.3&&x2==0.7&&z1==0.3&&z2==0.7)
					//{
					renderer.setRenderBounds(0.3,0.3,0.3,0.7,0.7,0.7);
					renderer.renderStandardBlock(block, x, y, z);
					//}
					return true;
				}
			//ココをいじればブロックの大きさが変わる。
			renderer.setRenderBounds(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
			renderer.renderStandardBlock(block, x, y, z);
			
			//renderer.clearOverrideBlockTexture();
			//上2つのコードを複数回実行することで、階段やドラゴンエッグのようなレンダーができる
			return true;
		}
		return false;
	}

	@Override
	public boolean shouldRender3DInInventory() {
		// TODO 自動生成されたメソッド・スタブ
		return true;
	}

	@Override
	public int getRenderId() {
		// TODO 自動生成されたメソッド・スタブ
		return MachineMod.machineBlockRenderId;
	}

}
