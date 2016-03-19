/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Quark Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Quark
 * 
 * Quark is Open Source and distributed under the
 * [ADD-LICENSE-HERE]
 * 
 * File Created @ [18/03/2016, 21:51:45 (GMT)]
 */
package vazkii.quark.base.proxy;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import vazkii.quark.base.handler.ModelHandler;
import vazkii.quark.base.module.ModuleLoader;

public class ClientProxy extends CommonProxy {

	@Override
	public void preInit(FMLPreInitializationEvent event) {
		super.preInit(event);
		ModuleLoader.preInitClient(event);
		ModelHandler.preInit();
	}
	
	@Override
	public void init(FMLInitializationEvent event) {
		super.init(event);
		ModuleLoader.initClient(event);
		ModelHandler.init();
	}
	
	@Override
	public void postInit(FMLPostInitializationEvent event) {
		super.postInit(event);
		ModuleLoader.postInitClient(event);
	}
	
}
