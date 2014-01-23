package otya.machinemod.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

public class ModelWindMill extends ModelBase{
	//variables init:
	public ModelRenderer box;
	public ModelWindMill()
	{
		//constructor:
		box = new ModelRenderer(this,0, 0);
		//box.addBox(2F, 10F, 2F, 2, 20, 2);
		box.addBox(2F, -40F, 5F, 2, 20, 2);
		//box.addBox(2F, 10F, 2F, 2, 20, 2);
		//box.addBox(2F, 10F, 2F, 2, 20, 2);
		//box.addBox(0F, 00F, -0F, -20, -20, -20);
	}
}
