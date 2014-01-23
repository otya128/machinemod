package otya.machinemod.electric.gui;
import otya.machinemod.MachineMod;
import otya.machinemod.electric.tileentity.BaseElectricTileEntity;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
 

import org.lwjgl.opengl.GL11;
public class MachineGui extends GuiContainer{

	private BaseElectricTileEntity tileentity;
	private MachineInventory inventory;
 
	public MachineGui(EntityPlayer player, BaseElectricTileEntity par2TileEntity) {
		super(new MachineContainer(player, par2TileEntity));
		this.tileentity = par2TileEntity;
		this.inventory = par2TileEntity.inventory;
	}
 
	@Override
	protected void drawGuiContainerForegroundLayer(int par1, int par2)
	{
		//�C���x���g�����̕`��
		String s = this.inventory.isInvNameLocalized() ? this.inventory.getInvName() : I18n.getString(this.inventory.getInvName());
		this.fontRenderer.drawString(s, this.xSize / 2 - this.fontRenderer.getStringWidth(s) / 2, 6, 4210752);
		this.fontRenderer.drawString(I18n.getString("container.inventory"), 8, this.ySize - 96 + 2, 4210752);
	}
 
	@Override
	protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3)
	{
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
 
		//�e�N�X�`���̎w��
		//ResourceLocation�̑�������t�������ăh���C�����w�肷�邱�Ƃ��ł���
		//��:new ResourceLocation("sample", "textures/gui/container/furnace.png")
		this.mc.getTextureManager().bindTexture(new ResourceLocation(MachineMod.DomainName+"textures/gui/container/furnace.png"));
 
		//���܂Ǖ`�揈��
		int k = (this.width - this.xSize) / 2;
		int l = (this.height - this.ySize) / 2;
		this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
		int i1;
 
		/*if (this.tileentity.isBurning())
		{
			i1 = this.tileentity.getBurnTimeRemainingScaled(12);
			this.drawTexturedModalRect(k + 56, l + 36 + 12 - i1, 176, 12 - i1, 14, i1 + 2);
		}
 
		i1 = this.tileentity.getCookProgressScaled(24);
		this.drawTexturedModalRect(k + 79, l + 34, 176, 14, i1 + 1, 16);*/
	}
}
