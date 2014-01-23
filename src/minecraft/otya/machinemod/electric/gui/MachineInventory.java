package otya.machinemod.electric.gui;
import otya.machinemod.electric.tileentity.BaseElectricTileEntity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
public class MachineInventory implements ISidedInventory{
	 
		private static final int[] slots_top = new int[] {0};
		private static final int[] slots_bottom = new int[] {2};
		//private static final int[] slots_sides = new int[] {1};
	 
		public ItemStack[] sampleItemStacks = new ItemStack[2];
	 
		private BaseElectricTileEntity tileentity;
	 
		public MachineInventory(BaseElectricTileEntity tileentity) {
			this.tileentity = tileentity;
		}
	 
		// �X���b�g��
		@Override
		public int getSizeInventory() {
			return this.sampleItemStacks.length;
		}
	 
		// �C���x���g�����̔C�ӂ̃X���b�g�ɂ���A�C�e�����擾
		@Override
		public ItemStack getStackInSlot(int par1) {
			return this.sampleItemStacks[par1];
		}
	 
		@Override
		public ItemStack decrStackSize(int par1, int par2) {
			if (this.sampleItemStacks[par1] != null)
			{
				ItemStack itemstack;
	 
				if (this.sampleItemStacks[par1].stackSize <= par2)
				{
					itemstack = this.sampleItemStacks[par1];
					this.sampleItemStacks[par1] = null;
					return itemstack;
				}
				else
				{
					itemstack = this.sampleItemStacks[par1].splitStack(par2);
	 
					if (this.sampleItemStacks[par1].stackSize == 0)
					{
						this.sampleItemStacks[par1] = null;
					}
	 
					return itemstack;
				}
			}
			else
			{
				return null;
			}
		}
	 
		@Override
		public ItemStack getStackInSlotOnClosing(int par1) {
			if (this.sampleItemStacks[par1] != null)
			{
				ItemStack itemstack = this.sampleItemStacks[par1];
				this.sampleItemStacks[par1] = null;
				return itemstack;
			}
			else
			{
				return null;
			}
		}
	 
		// �C���x���g�����̃X���b�g�ɃA�C�e��������
		@Override
		public void setInventorySlotContents(int par1, ItemStack par2ItemStack) {
			this.sampleItemStacks[par1] = par2ItemStack;
	 
			if (par2ItemStack != null && par2ItemStack.stackSize > this.getInventoryStackLimit())
			{
				par2ItemStack.stackSize = this.getInventoryStackLimit();
			}
		}
	 
		// �C���x���g���̖��O
		@Override
		public String getInvName() {
			return "generator";
		}
	 
		// ������Ή����ǂ���
		@Override
		public boolean isInvNameLocalized() {
			return false;
		}
	 
		// �C���x���g�����̃X�^�b�N���E�l
		@Override
		public int getInventoryStackLimit() {
			return 64;
		}
	 
		@Override
		public void onInventoryChanged() {
			this.tileentity.onInventoryChanged();
		}
	 
		// par1EntityPlayer��TileEntity���g���邩�ǂ���
		@Override
		public boolean isUseableByPlayer(EntityPlayer par1EntityPlayer) {
			return this.tileentity.worldObj.getBlockTileEntity(this.tileentity.xCoord, this.tileentity.yCoord, this.tileentity.zCoord) != this.tileentity ? false : par1EntityPlayer.getDistanceSq((double) this.tileentity.xCoord + 0.5D, (double) this.tileentity.yCoord + 0.5D, (double) this.tileentity.zCoord + 0.5D) <= 64.0D;
		}
	 
		@Override
		public void openChest() {}
	 
		@Override
		public void closeChest() {}
	 
		@Override
		public boolean isItemValidForSlot(int par1, ItemStack par2ItemStack) {
			return par1 == 2 ? false : true;//(par1 == 1 ? this.tileentity.isItemFuel(par2ItemStack) : true);
		}
	 
		//�z�b�p�[�ɃA�C�e���̎󂯓n��������ۂ̗D��x
		@Override
		public int[] getAccessibleSlotsFromSide(int par1) {
			return par1 == 0 ? slots_bottom :slots_top;// (par1 == 1 ? slots_top : slots_sides);
		}
	 
		//�z�b�p�[����A�C�e���������邩�ǂ���
		@Override
		public boolean canInsertItem(int par1, ItemStack par2ItemStack, int par3) {
			return this.isItemValidForSlot(par1, par2ItemStack);
		}
	 
		//�אڂ���z�b�p�[�ɃA�C�e���𑗂�邩�ǂ���
		@Override
		public boolean canExtractItem(int par1, ItemStack par2ItemStack, int par3) {
			return par3 != 0 || par1 != 1 || par2ItemStack.itemID == Item.bucketEmpty.itemID;
		}
}
