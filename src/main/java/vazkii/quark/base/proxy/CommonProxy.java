/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Quark Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Quark
 * 
 * Quark is Open Source and distributed under the
 * [ADD-LICENSE-HERE]
 * 
 * File Created @ [18/03/2016, 21:49:33 (GMT)]
 */
package vazkii.quark.base.proxy;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import vazkii.quark.base.Quark;
import vazkii.quark.base.handler.GuiHandler;
import vazkii.quark.base.module.ModuleLoader;

public class CommonProxy {

	public void preInit(FMLPreInitializationEvent event) {
		ModuleLoader.preInit(event);
		
		NetworkRegistry.INSTANCE.registerGuiHandler(Quark.instance, new GuiHandler());
	}
	
	public void init(FMLInitializationEvent event) {
		ModuleLoader.init(event);
	}
	
	public void postInit(FMLPostInitializationEvent event) {
		ModuleLoader.postInit(event);
	}
	
}
