/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Quark Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Quark
 * 
 * Quark is Open Source and distributed under the
 * [ADD-LICENSE-HERE]
 * 
 * File Created @ [19/06/2016, 04:32:59 (GMT)]
 */
package vazkii.quark.experimental.features;

import java.util.List;
import java.util.Map;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.client.renderer.entity.RenderLivingBase;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.relauncher.ReflectionHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import vazkii.quark.base.handler.ItemNBTHelper;
import vazkii.quark.base.lib.LibObfuscation;
import vazkii.quark.base.module.Feature;
import vazkii.quark.experimental.client.layer.LayerTabard;

public class Tabards extends Feature {

	@Override
	@SideOnly(Side.CLIENT)
	public void postInitClient(FMLPostInitializationEvent event) {
		Minecraft mc = Minecraft.getMinecraft();
		RenderManager manager = mc.getRenderManager();
		Map<String, RenderPlayer> renders = manager.getSkinMap();
		for(RenderPlayer render : renders.values())
			messWithRender(render);
	}
	
	@SideOnly(Side.CLIENT)
	private void messWithRender(RenderPlayer render) {
		List<LayerRenderer> list = ReflectionHelper.getPrivateValue(RenderLivingBase.class, render, LibObfuscation.LAYER_RENDERERS);
		list.add(new LayerTabard());
	}

}
