package otya.machinemod;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.network.IGuiHandler;
 
public class CommonProxy implements IGuiHandler
{
    // クライアント側のみの処理, Entityのレンダーの登録などの描画関連
    public void registerRenderInformation() 
    {
        // サーバー側では何もしない
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
		// TODO 自動生成されたメソッド・スタブ
		return -1;
	}
	public void registerRenderItemWire(int ID) {
		// TODO 自動生成されたメソッド・スタブ
		
	}


}