package otya.machinemod.renderer;

import org.lwjgl.opengl.GL11;

import otya.machinemod.MachineMod;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;

public class CustomItemRenderer implements IItemRenderer {

	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type) {
		// TODO 自動生成されたメソッド・スタブ
		return true;//type==ItemRenderType.INVENTORY||type==ItemRenderType.EQUIPPED_FIRST_PERSON;
	}

	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item,
			ItemRendererHelper helper) {
		// TODO 自動生成されたメソッド・スタブ
		return true;
	}

	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
		// TODO 自動生成されたメソッド・スタブ
		//System.out.println(type);
		if(item!=null&&item.stackTagCompound!=null)
		{
			RenderBlocks renderer = (RenderBlocks)data[0];
			Block block=Block.blocksList[item.itemID];//MachineMod.BaseMachineBlock;
			int[] plate = item.stackTagCompound.getIntArray("Plate");
			if(plate.length<6)plate=new int[6];
			int[] metadata = item.stackTagCompound.getIntArray("PlateMetadata");
			if(metadata.length<6)metadata=new int[6];
			//int metadata=0;
			//System.out.println("hoge");
			Tessellator tessellator = Tessellator.instance;
			//ココをいじるとブロックの大きさが変わる
			renderer.setRenderBounds(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
 
			//描画位置の調整。ココをいじると、中心にレンダー持ってきたり、遊べる
			if(type==ItemRenderType.INVENTORY)GL11.glTranslatef(-0.5F, -0.5F, -0.5F);//INVENTORYの場合中心
			//くコ:彡 、コピペ☆　RenderBlocksみてね
			tessellator.startDrawingQuads();
			tessellator.setNormal(0.0F, -1.0F, 0.0F);
			renderer.renderFaceYNeg(block, 0.0D, 0.0D, 0.0D, renderer.getBlockIconFromSideAndMetadata(Block.blocksList[plate[0]]==null||plate[0]==0 ? block:Block.blocksList[plate[0]], 0, metadata[0]));
			tessellator.draw();
			tessellator.startDrawingQuads();
			tessellator.setNormal(0.0F, 1.0F, 0.0F);
			renderer.renderFaceYPos(block, 0.0D, 0.0D, 0.0D, renderer.getBlockIconFromSideAndMetadata(Block.blocksList[plate[1]]==null||plate[1]==0 ? block:Block.blocksList[plate[1]], 1, metadata[1]));
			tessellator.draw();
			tessellator.startDrawingQuads();
			tessellator.setNormal(0.0F, 0.0F, -1.0F);
			renderer.renderFaceZNeg(block, 0.0D, 0.0D, 0.0D, renderer.getBlockIconFromSideAndMetadata(Block.blocksList[plate[2]]==null||plate[2]==0 ? block:Block.blocksList[plate[2]], 2, metadata[2]));
			tessellator.draw();
			tessellator.startDrawingQuads();
			tessellator.setNormal(0.0F, 0.0F, 1.0F);
			renderer.renderFaceZPos(block, 0.0D, 0.0D, 0.0D, renderer.getBlockIconFromSideAndMetadata(Block.blocksList[plate[3]]==null ||plate[3]==0 ? block:Block.blocksList[plate[3]], 3, metadata[3]));
			tessellator.draw();
			tessellator.startDrawingQuads();
			tessellator.setNormal(-1.0F, 0.0F, 0.0F);
			renderer.renderFaceXNeg(block, 0.0D, 0.0D, 0.0D, renderer.getBlockIconFromSideAndMetadata(Block.blocksList[plate[4]]==null ||plate[4]==0 ? block:Block.blocksList[plate[4]], 4, metadata[4]));
			tessellator.draw();
			tessellator.startDrawingQuads();
			tessellator.setNormal(1.0F, 0.0F, 0.0F);
			renderer.renderFaceXPos(block, 0.0D, 0.0D, 0.0D, renderer.getBlockIconFromSideAndMetadata(Block.blocksList[plate[5]]==null ||plate[5]==0 ? block:Block.blocksList[plate[5]], 5, metadata[5]));
			tessellator.draw();
			//描画位置の調整。遊んだ後はお片づけ
			//上のヤツ→GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
			GL11.glTranslatef(0.5F, 0.5F, 0.5F);
		}
		else
		{
			RenderBlocks renderer = (RenderBlocks)data[0];
			Block block=Block.blocksList[item.itemID];//MachineMod.BaseMachineBlock;
			int metadata=0;
			Tessellator tessellator = Tessellator.instance;
			//ココをいじるとブロックの大きさが変わる
			renderer.setRenderBounds(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
 
			//描画位置の調整。ココをいじると、中心にレンダー持ってきたり、遊べる
			if(type==ItemRenderType.INVENTORY)GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
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
	public class WireItemRenderer implements IItemRenderer {

		public WireItemRenderer()
		{
			
		}
		@Override
		public boolean handleRenderType(ItemStack item, ItemRenderType type) {
			// TODO 自動生成されたメソッド・スタブ
			return true;//type==ItemRenderType.INVENTORY||type==ItemRenderType.EQUIPPED_FIRST_PERSON;
		}

		@Override
		public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item,
				ItemRendererHelper helper) {
			// TODO 自動生成されたメソッド・スタブ
			return true;
		}

		@Override
		public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
			// TODO 自動生成されたメソッド・スタブ
			//System.out.println(type);
			if(item!=null&&item.stackTagCompound!=null)
			{
				RenderBlocks renderer = (RenderBlocks)data[0];
				Block block=Block.blocksList[item.itemID];//MachineMod.BaseMachineBlock;
				int[] plate = item.stackTagCompound.getIntArray("Plate");
				if(plate.length<6)plate=new int[6];
				int[] metadata = item.stackTagCompound.getIntArray("PlateMetadata");
				if(metadata.length<6)metadata=new int[6];
				//int metadata=0;
				//System.out.println("hoge");
				Tessellator tessellator = Tessellator.instance;
				//ココをいじるとブロックの大きさが変わる
				renderer.setRenderBounds(0.3D, 0.3D, 0.3D, 0.7D, 0.7D, 0.7D);
	 
				//描画位置の調整。ココをいじると、中心にレンダー持ってきたり、遊べる
				if(type==ItemRenderType.INVENTORY)GL11.glTranslatef(-0.5F, -0.5F, -0.5F);//INVENTORYの場合中心
				//くコ:彡 、コピペ☆　RenderBlocksみてね
				tessellator.startDrawingQuads();
				tessellator.setNormal(0.0F, -1.0F, 0.0F);
				renderer.renderFaceYNeg(block, 0.0D, 0.0D, 0.0D, renderer.getBlockIconFromSideAndMetadata(Block.blocksList[plate[0]]==null||plate[0]==0 ? block:Block.blocksList[plate[0]], 0, metadata[0]));
				tessellator.draw();
				tessellator.startDrawingQuads();
				tessellator.setNormal(0.0F, 1.0F, 0.0F);
				renderer.renderFaceYPos(block, 0.0D, 0.0D, 0.0D, renderer.getBlockIconFromSideAndMetadata(Block.blocksList[plate[1]]==null||plate[1]==0 ? block:Block.blocksList[plate[1]], 1, metadata[1]));
				tessellator.draw();
				tessellator.startDrawingQuads();
				tessellator.setNormal(0.0F, 0.0F, -1.0F);
				renderer.renderFaceZNeg(block, 0.0D, 0.0D, 0.0D, renderer.getBlockIconFromSideAndMetadata(Block.blocksList[plate[2]]==null||plate[2]==0 ? block:Block.blocksList[plate[2]], 2, metadata[2]));
				tessellator.draw();
				tessellator.startDrawingQuads();
				tessellator.setNormal(0.0F, 0.0F, 1.0F);
				renderer.renderFaceZPos(block, 0.0D, 0.0D, 0.0D, renderer.getBlockIconFromSideAndMetadata(Block.blocksList[plate[3]]==null ||plate[3]==0 ? block:Block.blocksList[plate[3]], 3, metadata[3]));
				tessellator.draw();
				tessellator.startDrawingQuads();
				tessellator.setNormal(-1.0F, 0.0F, 0.0F);
				renderer.renderFaceXNeg(block, 0.0D, 0.0D, 0.0D, renderer.getBlockIconFromSideAndMetadata(Block.blocksList[plate[4]]==null ||plate[4]==0 ? block:Block.blocksList[plate[4]], 4, metadata[4]));
				tessellator.draw();
				tessellator.startDrawingQuads();
				tessellator.setNormal(1.0F, 0.0F, 0.0F);
				renderer.renderFaceXPos(block, 0.0D, 0.0D, 0.0D, renderer.getBlockIconFromSideAndMetadata(Block.blocksList[plate[5]]==null ||plate[5]==0 ? block:Block.blocksList[plate[5]], 5, metadata[5]));
				tessellator.draw();
				//描画位置の調整。遊んだ後はお片づけ
				//上のヤツ→GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
				GL11.glTranslatef(0.5F, 0.5F, 0.5F);
			}
			else
			{
				RenderBlocks renderer = (RenderBlocks)data[0];
				Block block=Block.blocksList[item.itemID];//MachineMod.BaseMachineBlock;
				int metadata=0;
				Tessellator tessellator = Tessellator.instance;
				//ココをいじるとブロックの大きさが変わる
				renderer.setRenderBounds(0.3D, 0.3D, 0.3D, 0.7D, 0.7D, 0.7D);
	 
				//描画位置の調整。ココをいじると、中心にレンダー持ってきたり、遊べる
				if(type==ItemRenderType.INVENTORY)GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
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
	}
}
