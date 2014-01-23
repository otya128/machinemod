package otya.machinemod.block;

import java.util.ArrayList;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import otya.machinemod.MachineMod;
import otya.machinemod.tileentity.BaseMachineTileEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.ForgeEventFactory;

public class BaseMachineBlock extends BlockContainer {

	public BaseMachineBlock(int par1, Material par2Material) {
		super(par1, par2Material);
		this.setUnlocalizedName("BaseMachineBlock");
		this.setHardness(1.0f);
		MinecraftForge.setBlockHarvestLevel(this, "IRON_HAMMER", 0);
		this.setCreativeTab(CreativeTabs.tabBlock);
		// this.on
		// TODO 自動生成されたコンストラクター・スタブ
	}
	public BaseMachineBlock(int par1) {
		super(par1, Material.iron);
		this.setUnlocalizedName("BaseMachineBlock");
		this.setHardness(1.0f);
		MinecraftForge.setBlockHarvestLevel(this, "IRON_HAMMER", 0);
		this.setCreativeTab(CreativeTabs.tabBlock);
		// this.on
		// TODO 自動生成されたコンストラクター・スタブ
	}

	@Override
	public boolean onBlockActivated(World par1World, int par2, int par3,
			int par4, EntityPlayer par5EntityPlayer, int par6, float par7,
			float par8, float par9) {

		// par1World.setBlockMetadataWithNotify(par2, par3, par4,
		// par5EntityPlayer.getCurrentItemOrArmor(0).itemID - 256, 0xffffff);
		boolean flag = par5EntityPlayer.capabilities.isCreativeMode;

		if (flag
				|| par5EntityPlayer.inventory
						.hasItem(MachineMod.ironHammer.itemID)) {

			if (par5EntityPlayer.getCurrentItemOrArmor(0) == null)
				return false;
			if (par5EntityPlayer.getCurrentItemOrArmor(0).itemID == MachineMod.ironHammer.itemID) {

				if (!(flag == true || (((BaseMachineTileEntity) par1World
						.getBlockTileEntity(par2, par3, par4)).getPlate(par6) == 0 && ((BaseMachineTileEntity) par1World
						.getBlockTileEntity(par2, par3, par4))
						.getPlateMetadata(par6) == 0)))
					par5EntityPlayer.getCurrentItemOrArmor(0).damageItem(1,
							par5EntityPlayer);
				((BaseMachineTileEntity) par1World.getBlockTileEntity(par2,
						par3, par4)).setPlate(par6, 0);
				((BaseMachineTileEntity) par1World.getBlockTileEntity(par2,
						par3, par4)).setPlateMetadata(par6, 0);
				par1World.markBlockForUpdate(par2, par3, par4);
				par1World.playSound(par2, par3, par4, "random.break", 1.0F,
						1.0F, false);
				return true;
			}
			if (Block.blocksList.length < par5EntityPlayer
					.getCurrentItemOrArmor(0).itemID)
				return false;
			if (Block.blocksList[par5EntityPlayer.getCurrentItemOrArmor(0).itemID] == null)
				return false;
			boolean hasIronHammer = false;

			if (((BaseMachineTileEntity) par1World.getBlockTileEntity(par2,
					par3, par4)).getPlate(par6) == par5EntityPlayer
					.getCurrentItemOrArmor(0).itemID
					&& ((BaseMachineTileEntity) par1World.getBlockTileEntity(
							par2, par3, par4)).getPlateMetadata(par6) == par5EntityPlayer
							.getCurrentItemOrArmor(0).getItemDamage()) {
				return true;
			}
			if (!flag)
				for (int k = 0; k < par5EntityPlayer.inventory.mainInventory.length / 4; ++k) {
					if (par5EntityPlayer.inventory.mainInventory[k] != null
							&& par5EntityPlayer.inventory.mainInventory[k].itemID == MachineMod.ironHammer.itemID) {
						hasIronHammer = true;
						par5EntityPlayer.inventory.mainInventory[k].damageItem(
								1, par5EntityPlayer);
						break;
					}
				}

			if (!flag && !hasIronHammer)
				return false;
			((BaseMachineTileEntity) par1World.getBlockTileEntity(par2, par3,
					par4)).setPlate(par6,
					par5EntityPlayer.getCurrentItemOrArmor(0).itemID);
			((BaseMachineTileEntity) par1World.getBlockTileEntity(par2, par3,
					par4)).setPlateMetadata(par6, par5EntityPlayer
					.getCurrentItemOrArmor(0).getItemDamage());// , 1);
			par1World.markBlockForUpdate(par2, par3, par4);

			par1World
					.playSound(par2, par3, par4, /* "random.anvil_land" */
							Block.blocksList[par5EntityPlayer
									.getCurrentItemOrArmor(0).itemID].stepSound
									.getPlaceSound(),
							Block.blocksList[par5EntityPlayer
									.getCurrentItemOrArmor(0).itemID].stepSound
									.getPitch(),
							Block.blocksList[par5EntityPlayer
									.getCurrentItemOrArmor(0).itemID].stepSound
									.getVolume(), false);
			// par1World.spawnParticle("iconcrack_" +
			// par5EntityPlayer.getCurrentItemOrArmor(0).itemID + "_" +
			// par5EntityPlayer.getCurrentItemOrArmor(0).getItemDamage(), par2,
			// par3, par4,par2, par3, par4);
			return true;
		}
		return false;
	}

	public int getRenderType() {
		return MachineMod.machineBlockRenderId;
	}

	public boolean isOpaqueCube() {
		return false;
	}

	public boolean renderAsNormalBlock() {
		return false;
	}

	/**
	 * Retrieves the block texture to use based on the display side. Args:
	 * iBlockAccess, x, y, z, side
	 */
	public Icon getBlockTexture(IBlockAccess par1IBlockAccess, int par2,
			int par3, int par4, int par5) {
if(par1IBlockAccess.getBlockTileEntity(par2, par3, par4)==null)return this.getIcon(par5, 0);
		return this.getIcon(par5, ((BaseMachineTileEntity) par1IBlockAccess
				.getBlockTileEntity(par2, par3, par4)).getPlate(par5),
				((BaseMachineTileEntity) par1IBlockAccess.getBlockTileEntity(
						par2, par3, par4)).getPlateMetadata(par5));// par1IBlockAccess.getBlockMetadata(par2,
																// par3, par4));
	}

	@SideOnly(Side.CLIENT)
	/**
	 * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
	 */
	public Icon getIcon(int par1, int par2) {
		if (par2 != 0) {
			if (Block.blocksList.length < par2)
				return this.blockIcon;
			return Block.blocksList[par2].getIcon(par1, 0);
		}// .carpet.getIcon(par1, 0);}
		return this.blockIcon;
	}

	// meta
	public Icon getIcon(int par1, int par2, int par3) {
		//System.out.println(par2);
		if (par2 != 0) {
			if (Block.blocksList.length < par2)
				return this.blockIcon;
			return Block.blocksList[par2].getIcon(par1, par3);
		}// .carpet.getIcon(par1, 0);}
		return this.blockIcon;
	}

	@Override
	public TileEntity createNewTileEntity(World world) {
		// TODO 自動生成されたメソッド・スタブ
		return new BaseMachineTileEntity();
	}
	@Override
	public void harvestBlock(World par1World, EntityPlayer par2EntityPlayer, int par3, int par4, int par5, int par6)
    {
		if(par2EntityPlayer.getCurrentItemOrArmor(0)!=null && par2EntityPlayer.getCurrentItemOrArmor(0).itemID==MachineMod.ironHammer.itemID){
			return ;
		}
		else
		{
			//super.onBlockHarvested(par1World, par2, par3, par4, par5, par6EntityPlayer);
			super.harvestBlock(par1World, par2EntityPlayer, par3, par4, par5, par6);
		}
	}
	/*public void onBlockHarvested(World par1World, int par2, int par3, int par4, int par5,EntityPlayer par6EntityPlayer)
    {
		
		if(par6EntityPlayer.getCurrentItemOrArmor(0).itemID==MachineMod.ironHammer.itemID){
			System.out.println("[DEBUG]");
			return ;
		}
		else
		{
			super.onBlockHarvested(par1World, par2, par3, par4, par5, par6EntityPlayer);
			//super.harvestBlock(par1World, par2EntityPlayer, par3, par4, par5, par6);
		}
		
		
    }*/

    public void breakBlock(World par1World, int par2, int par3, int par4, int par5, int par6)
    {
        super.breakBlock(par1World, par2, par3, par4, par5, par6);
        
    }

	public void onBlockPlacedBy(World par1World, int par2, int par3, int par4,
			EntityLivingBase par5EntityLivingBase, ItemStack par6ItemStack) {
		BaseMachineTileEntity bmte = (BaseMachineTileEntity) par1World
				.getBlockTileEntity(par2, par3, par4);
		if(par6ItemStack.stackTagCompound!=null)
		
			bmte.readFromNBTPlate(par6ItemStack.stackTagCompound);
		
	}

}
