package otya.machinemod;

import net.minecraftforge.client.MinecraftForgeClient;
import otya.machinemod.renderer.CustomItemRenderer;
import otya.machinemod.renderer.RenderMachineBlock;
import otya.machinemod.renderer.WindMillSpecialRenderer;
import otya.machinemod.tileentity.WindMillTileEntity;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy {
	@Override
	public void registerRenderInformation() 
    {
		for(int i:MachineMod.machineID)
		{
			MinecraftForgeClient.registerItemRenderer(i, new CustomItemRenderer());
		}
		RenderingRegistry.registerBlockHandler(new RenderMachineBlock());
		ClientRegistry.bindTileEntitySpecialRenderer(WindMillTileEntity.class, new WindMillSpecialRenderer());
    }
	@Override
    public void registerRenderItemMachine(int ID)
    {
		MinecraftForgeClient.registerItemRenderer(ID, new CustomItemRenderer());
    }
	@Override
    public void registerRenderItemWire(int ID)
    {
		MinecraftForgeClient.registerItemRenderer(ID, new CustomItemRenderer().new WireItemRenderer());
    }

	// ÉåÉìÉ_Å[IDÇÃéÊìæ
	@Override
	public int getNewRenderType() {
		return RenderingRegistry.getNextAvailableRenderId();
	}
	
}
