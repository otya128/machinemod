package otya.machinemod;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.network.IGuiHandler;
 
public class CommonProxy implements IGuiHandler
{
    // �N���C�A���g���݂̂̏���, Entity�̃����_�[�̓o�^�Ȃǂ̕`��֘A
    public void registerRenderInformation() 
    {
        // �T�[�o�[���ł͉������Ȃ�
    }
    public void registerRenderItemMachine(int ID)
    {
    	
    }
    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
        // TODO Auto-generated method stub
        return null;
    }
 
    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
        // TODO Auto-generated method stub
        return null;
    }

	public int getNewRenderType() {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		return -1;
	}
	public void registerRenderItemWire(int ID) {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		
	}


}