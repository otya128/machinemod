package otya.machinemod.electric.gui;
import otya.machinemod.electric.tileentity.BaseElectricTileEntity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotFurnace;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.tileentity.TileEntityFurnace;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
public class MachineContainer extends Container{
	private BaseElectricTileEntity tileentity;
	 
	private MachineInventory inventory;
 
	private int lastCookTime;
	private int lastBurnTime;
	private int lastItemBurnTime;
 
	public MachineContainer(EntityPlayer player, BaseElectricTileEntity par2TileEntity) {
		this.tileentity = par2TileEntity;
		this.inventory = par2TileEntity.inventory;
 
		// InventorySample�Œǉ�����C���x���g��
		this.addSlotToContainer(new Slot(this.inventory, 0, 56, 17));
		this.addSlotToContainer(new Slot(this.inventory, 1, 56, 53));
		//this.addSlotToContainer(new SlotFurnace(player, this.inventory, 2, 116, 35));
		int i;
 
		// 1 �` 3�i�ڂ̃C���x���g��
		for (i = 0; i < 3; ++i)
		{
			for (int j = 0; j < 9; ++j)
			{
				this.addSlotToContainer(new Slot(player.inventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
			}
		}
 
		// 4�i�ڂ̃C���x���g��
		for (i = 0; i < 9; ++i)
		{
			this.addSlotToContainer(new Slot(player.inventory, i, 8 + i * 18, 142));
		}
	}
 
	public void addCraftingToCrafters(ICrafting par1ICrafting)
	{
		super.addCraftingToCrafters(par1ICrafting);/*
		par1ICrafting.sendProgressBarUpdate(this, 0, this.tileentity.cookTime);
		par1ICrafting.sendProgressBarUpdate(this, 1, this.tileentity.burnTime);
		par1ICrafting.sendProgressBarUpdate(this, 2, this.tileentity.currentItemBurnTime);*/
	}
 
	// �X�V�𑗂�
	public void detectAndSendChanges()
	{
		super.detectAndSendChanges();
 /*
		for (int i = 0; i < this.crafters.size(); ++i)
		{
			ICrafting icrafting = (ICrafting)this.crafters.get(i);
 
			if (this.lastCookTime != this.tileentity.cookTime)
			{
				icrafting.sendProgressBarUpdate(this, 0, this.tileentity.cookTime);
			}
 
			if (this.lastBurnTime != this.tileentity.burnTime)
			{
				icrafting.sendProgressBarUpdate(this, 1, this.tileentity.burnTime);
			}
 
			if (this.lastItemBurnTime != this.tileentity.currentItemBurnTime)
			{
				icrafting.sendProgressBarUpdate(this, 2, this.tileentity.currentItemBurnTime);
			}
		}
 
		this.lastCookTime = this.tileentity.cookTime;
		this.lastBurnTime = this.tileentity.burnTime;
		this.lastItemBurnTime = this.tileentity.currentItemBurnTime;*/
	}
 
	// �X�V����
	@SideOnly(Side.CLIENT)
	public void updateProgressBar(int par1, int par2)
	{
		/*if (par1 == 0)
		{
			this.tileentity.cookTime = par2;
		}
 
		if (par1 == 1)
		{
			this.tileentity.burnTime = par2;
		}
 
		if (par1 == 2)
		{
			this.tileentity.currentItemBurnTime = par2;
		}*/
	}
 
	// InventorySample����isUseableByPlayer���\�b�h���Q��
	@Override
	public boolean canInteractWith(EntityPlayer par1EntityPlayer) {
		return this.inventory.isUseableByPlayer(par1EntityPlayer);
	}
 
	// Shift�N���b�N
	public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par2)
	{
		ItemStack itemstack = null;
		Slot slot = (Slot)this.inventorySlots.get(par2);
 
		if (slot != null && slot.getHasStack())
		{
			ItemStack itemstack1 = slot.getStack();
			itemstack = itemstack1.copy();
 
			//�X���b�g�ԍ���2�̎�
			if (par2 == 2)
			{
				//�A�C�e���̈ړ�(�X���b�g3�`39��)
				if (!this.mergeItemStack(itemstack1, 3, 39, true))
				{
					return null;
				}
 
				slot.onSlotChange(itemstack1, itemstack);
			}
			//�X���b�g�ԍ���0�A1�łȂ���
			else if (par2 != 1 && par2 != 0)
			{
				if (FurnaceRecipes.smelting().getSmeltingResult(itemstack1) != null)
				{
					//�A�C�e���̈ړ�(�X���b�g0�`1��)
					if (!this.mergeItemStack(itemstack1, 0, 1, false))
					{
						return null;
					}
				}
				else if (TileEntityFurnace.isItemFuel(itemstack1))
				{
					//�A�C�e���̈ړ�(�X���b�g1�`2��)
					if (!this.mergeItemStack(itemstack1, 1, 2, false))
					{
						return null;
					}
				}
				else if (par2 >= 3 && par2 < 30)
				{
					//�A�C�e���̈ړ�(�X���b�g30�`39��)
					if (!this.mergeItemStack(itemstack1, 30, 39, false))
					{
						return null;
					}
				}
				else if (par2 >= 30 && par2 < 39 && !this.mergeItemStack(itemstack1, 3, 30, false))
				{
					return null;
				}
			}
			//�A�C�e���̈ړ�(�X���b�g3�`39��)
			else if (!this.mergeItemStack(itemstack1, 3, 39, false))
			{
				return null;
			}
 
			if (itemstack1.stackSize == 0)
			{
				slot.putStack((ItemStack)null);
			}
			else
			{
				slot.onSlotChanged();
			}
 
			if (itemstack1.stackSize == itemstack.stackSize)
			{
				return null;
			}
 
			slot.onPickupFromSlot(par1EntityPlayer, itemstack1);
		}
 
		return itemstack;
	}
}
