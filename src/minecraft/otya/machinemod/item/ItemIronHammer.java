package otya.machinemod.item;

import otya.machinemod.MachineMod;
import otya.machinemod.electric.tileentity.BaseElectricTileEntity;
import otya.machinemod.tileentity.BaseMachineTileEntity;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.stats.StatList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;

public class ItemIronHammer extends ItemPickaxe {

	public ItemIronHammer(int par1, float par2,
			EnumToolMaterial par3EnumToolMaterial, Block[] par4ArrayOfBlock) {
		super(par1, par3EnumToolMaterial);// super(par1, par2,
											// par3EnumToolMaterial,
											// par4ArrayOfBlock);
		// TODO 自動生成されたコンストラクター・スタブ
		this.setUnlocalizedName("IronHammer"); /* システム名の登録 */
		this.setTextureName(MachineMod.DomainName + "HammerIron"); /* テクスチャの指定 */
		this.setCreativeTab(CreativeTabs.tabTools);
	}


	public boolean onBlockDestroyed(ItemStack par1ItemStack, World par2World,
			int par3, int par4, int par5, int par6,
			EntityLivingBase par7EntityLivingBase) {
		super.onBlockDestroyed(par1ItemStack, par2World, par3, par4, par5,
				par6, par7EntityLivingBase);
		if (par7EntityLivingBase instanceof EntityPlayer) {
			BaseMachineTileEntity tile = (BaseMachineTileEntity) par2World
					.getBlockTileEntity(par4, par5, par6);
			if (tile == null)
				return true;
			String names="";
			for (int i : tile.plate) {
				if (i != 0) {
					names+=Block.blocksList[i].getLocalizedName();
				}
			}
			if(names!="")
			{
				((EntityPlayer) par7EntityLivingBase).addExhaustion(0.025F);
				if (!par2World.isRemote) {
					if (!par2World.isRemote
							&& par2World.getGameRules()
									.getGameRuleBooleanValue("doTileDrops")) {
						
						float f = 0.7F;
						double d0 = (double) (par2World.rand.nextFloat() * f)
								+ (double) (1.0F - f) * 0.5D;
						double d1 = (double) (par2World.rand.nextFloat() * f)
								+ (double) (1.0F - f) * 0.5D;
						double d2 = (double) (par2World.rand.nextFloat() * f)
								+ (double) (1.0F - f) * 0.5D;
						ItemStack item = new ItemStack(
								Block.blocksList[par3]);
						item.setItemName(Block.blocksList[par3]
								.getLocalizedName()
								+ EnumChatFormatting.GREEN + "Plate:"+names);
						NBTTagCompound nbt=item.stackTagCompound;//new NBTTagCompound();
						tile.writeToNBTPlate(nbt);
						EntityItem entityitem = new EntityItem(par2World,
								(double) par4 + d0, (double) par5 + d1,
								(double) par6 + d2, item);
						entityitem.delayBeforeCanPickup = 10;
						par2World.spawnEntityInWorld(entityitem);

					}
				}
				return false;
			}
			((EntityPlayer) par7EntityLivingBase).addExhaustion(0.025F);
			if (!par2World.isRemote) {
				if (!par2World.isRemote
						&& par2World.getGameRules().getGameRuleBooleanValue(
								"doTileDrops")) {
					float f = 0.7F;
					double d0 = (double) (par2World.rand.nextFloat() * f)
							+ (double) (1.0F - f) * 0.5D;
					double d1 = (double) (par2World.rand.nextFloat() * f)
							+ (double) (1.0F - f) * 0.5D;
					double d2 = (double) (par2World.rand.nextFloat() * f)
							+ (double) (1.0F - f) * 0.5D;
					ItemStack item = new ItemStack(Block.blocksList[par3]);
					EntityItem entityitem = new EntityItem(par2World,
							(double) par4 + d0, (double) par5 + d1,
							(double) par6 + d2, item);
					entityitem.delayBeforeCanPickup = 10;
					par2World.spawnEntityInWorld(entityitem);

				}
			}
		}
		return true;
	}

}
