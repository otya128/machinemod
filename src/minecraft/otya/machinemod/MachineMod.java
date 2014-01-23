package otya.machinemod;


import java.io.DataOutput;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Map;

import net.minecraft.block.Block;
import net.minecraft.block.BlockDispenser;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelCreeper;
import net.minecraft.client.renderer.entity.RenderCreeper;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.dispenser.DispenserBehaviors;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemSkull;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.src.BaseMod;
import net.minecraft.src.ModLoader;
import net.minecraft.util.ResourceLocation;

import org.bouncycastle.asn1.cms.MetaData;

import ibxm.Sample;
import cpw.mods.fml.common.*;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.InstanceFactory;
import cpw.mods.fml.common.Mod.Metadata;
import cpw.mods.fml.common.event.*;
import cpw.mods.fml.common.event.FMLInterModComms.IMCEvent;
//import cpw.mods.fml.common.Mod.*;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.common.registry.VillagerRegistry;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.gen.structure.MapGenStructureIO;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.EnumHelper;
import net.minecraftforge.common.MinecraftForge;
import net.minecraft.*;
import cpw.mods.fml.*;
import otya.machinemod.block.*;
import otya.machinemod.electric.gui.GuiHandler;
import otya.machinemod.electric.item.ItemReader;
import otya.machinemod.electric.machine.TestMachine;
import otya.machinemod.electric.machine.TestMachine2;
import otya.machinemod.electric.tileentity.BaseElectricTileEntity;
import otya.machinemod.electric.wire.WireBlock;
import otya.machinemod.item.*;
import otya.machinemod.renderer.*;
import otya.machinemod.tileentity.*;
//  Sample Mod �N���X.
//  
//  Mod�A�m�e�[�V���������邾���ŁAMod�N���X�Ƃ��ĔF�������B
//  �N���X���͎��R�Ɍ��߂邱�Ƃ��ł��A���̃N���X���p������K�v���Ȃ��B
//  �Ȃ��AMod�A�m�e�[�V�����̕K�{���ڂ� modid �݂̂ŁA���̑��͊O���t�@�C������ݒ肷�邱�Ƃ��ł��A�ȗ����邱�Ƃ��\�B
// 
//  �܂��ANetworkMod �͕s�v�ł���Ώȗ����Ă��悢�B
// 
@Mod(modid = "engine-mod-id", name = "Engine MOD",version="0.01")
@NetworkMod(clientSideRequired = true, serverSideRequired = false)
public class MachineMod  {

	//
	// �w�肵��ID��Mod�̃C���X�^���X.
     // 
     // �t�B�[���h���A�A�N�Z�X�w��q�͂Ȃ�ł��悢�B�s�v�ł���Ώȗ��\�B
     // ���g�̃C���X�^���X���C���W�F�N�V���������B
     //
    @Instance("engine-mod-id")
	public
    static MachineMod instance;
    /**
     * not uint
     * �P�ʂ̖��O
     */
    public static final String unit = "EU";
	public static int guiMachine2ID = 1000;
    //
     // �T�[�o�[�A�N���C�A���g�ňقȂ鏈�����s�킹��ꍇ�ɗp����v���L�V�[�N���X.
     // 
     // �t�B�[���h���A�͂Ȃ�ł��悢�B�s�v�ł���Ώȗ��\�B(public�ȊO�̃A�N�Z�X�w��q���g���邩�͕s��)
     // ���s���ɂ��킹�āA�ǂ��炩�̃C���X�^���X���C���W�F�N�V���������B
     //
    
    @SidedProxy(
        clientSide = "otya.machinemod.ClientProxy",
        serverSide = "otya.machinemod.CommonProxy")
    public static CommonProxy proxy;
    public static Block BaseMachineBlock;
    public static Block WindMillBlock;
    public static int WindMillID=4007;
    public static Item ironHammer;
    public static int ironHammerID=28900;
    public static EnumToolMaterial ironHammerEnum;
    public static String DomainName="otya:";
    public static int[] machineID =  new int[2];
    public static int[] electricID = new int[2];

	public static Block wireBlock;
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
    	GameRegistry.registerTileEntity(BaseMachineTileEntity.class, "otya.BaseMachineTileEntity");
    	GameRegistry.registerTileEntity(WindMillTileEntity.class, "otya.WindMillTileEntity");
    	
		ironHammerEnum = EnumHelper.addToolMaterial("IRON_HAMMER", 128, 128, 128.0F, 2.0F, 14);
		ironHammerEnum.customCraftingMaterial = new ItemStack(Block.blockIron).getItem();
    	BaseMachineBlock = new BaseMachineBlock(4006, Material.iron);
    	GameRegistry.registerBlock(BaseMachineBlock, "BaseMachineBlock");
    	ironHammer= new ItemIronHammer(ironHammerID, 1, ironHammerEnum, new Block[]{BaseMachineBlock});
		GameRegistry.registerItem(ironHammer, "ironHammer");
		LanguageRegistry.instance().addNameForObject(BaseMachineBlock, "ja_JP","�@�B�̌��̃u���b�N");
		//����
		WindMillBlock = new WindMillBlock(4007);
		GameRegistry.registerBlock(WindMillBlock, "WindMill");
		LanguageRegistry.instance().addNameForObject(WindMillBlock, "ja_JP","����");
		machineID[0] = 4006;
		machineID[1] = WindMillID;
		//Elec
    	GameRegistry.registerTileEntity(BaseElectricTileEntity.class, BaseElectricTileEntity.class.getName());
    	wireBlock=new WireBlock(4008);
    	GameRegistry.registerBlock(wireBlock, "Wire");
    	GameRegistry.registerBlock(new TestMachine(4009), "Test");
    	GameRegistry.registerBlock(new TestMachine2(4010), "hatudenki");
    	GameRegistry.registerItem(new ItemReader(28901), "EUReader");
    	
	}
 
    ////
     // ����������.
     // 
     // ���\�b�h���͂Ȃ�ł��悢�B�s�v�ł���Ώȗ��\�B
     // 
     // @param event
     //         FML�̏����������C�x���g
     ///
    @EventHandler
    public void init(FMLInitializationEvent event) {
		NetworkRegistry.instance().registerGuiHandler(this, new GuiHandler());
    	machineBlockRenderId = proxy.getNewRenderType();
    	proxy.registerRenderInformation();
    	proxy.registerRenderItemMachine(4009);
    	proxy.registerRenderItemMachine(4010);
    	proxy.registerRenderItemWire(4008);
    }
    // ���̑��̃C�x���g
    // -----------------------------------------------------
 
    // ---------------------------------
    // client's events
    // ---------------------------------
 
    // �������㏈���C�x���g
    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
    }
 
    // �������C�x���g�BPreInit�C�x���g�̑O�ɔ���
    @EventHandler
    public void fingerprintWarning(FMLFingerprintViolationEvent event) {
    }
 
    // IMC���b�Z�[�W�C�x���g�BInitEvent�̂��Ƃɔ���
    @EventHandler
    public void handleIMC(IMCEvent event) {
    }
 
    // ---------------------------------
    // Server's events
    // ---------------------------------
 
    @EventHandler
    public void handleServerAboutToStart(FMLServerAboutToStartEvent event) {
    }
 
    @EventHandler
    public void handleServerStarting(FMLServerStartingEvent event) {
    }

    @EventHandler
    public void handleServerStarted(FMLServerStartedEvent event) {
    	//net.minecraft.item.Item.
        //TickHandler tickHandler = new TickHandler();
        //TickRegistry.registerScheduledTickHandler(tickHandler, Side.SERVER);
    }
 
    @EventHandler
    public void handleServerStopping(FMLServerStoppingEvent event) {
    }
 
    @EventHandler
    public void handleServerStopped(FMLServerStoppedEvent event) {
    }
 
    // ���̑��A�m�e�[�V����
    // -----------------------------------------------------
 
    @Metadata
    protected static ModMetadata metadata;

	public static int machineBlockRenderId;
 
    @InstanceFactory
    public  MachineMod instance() {
        return new MachineMod();
    }
}