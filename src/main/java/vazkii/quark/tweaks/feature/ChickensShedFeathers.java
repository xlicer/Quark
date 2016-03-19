/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Quark Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Quark
 * 
 * Quark is Open Source and distributed under the
 * [ADD-LICENSE-HERE]
 * 
 * File Created @ [19/03/2016, 16:20:49 (GMT)]
 */
package vazkii.quark.tweaks.feature;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import vazkii.quark.base.module.Feature;

public class ChickensShedFeathers extends Feature {

	boolean chicksDropFeathers;
	boolean dropAtLeastOne;
	int dropFreq;
	
	@Override
	public void setupConfig() {
		chicksDropFeathers = loadPropBool("Chicks drop Feathers", "", true);
		dropAtLeastOne = loadPropBool("Force at least one feather on kill", "", true);
		dropFreq = loadPropInt("Drop frequency (lower means more)", "", 28000);
	}
	
	@SubscribeEvent
	public void onLivingUpdate(LivingUpdateEvent event) {
		if(event.entity.worldObj.isRemote || !(event.entity instanceof EntityChicken))
			return;
		
		EntityChicken chicken = (EntityChicken) event.entity;
		if((chicksDropFeathers || !chicken.isChild()) && chicken.worldObj.rand.nextInt(dropFreq) == 0)
			chicken.dropItem(Items.feather, 1);
	}
	
	@SubscribeEvent
	public void onLivingDrops(LivingDropsEvent event) {
		if(!dropAtLeastOne || event.entity.worldObj.isRemote || !(event.entity instanceof EntityChicken) || (!((EntityChicken)event.entity).isChild() && !chicksDropFeathers))
			return;
		
		EntityChicken chicken = (EntityChicken) event.entity;
		boolean hasFeather = false;

		for(EntityItem item : event.drops)
			if(item != null && item.getEntityItem().getItem().equals(Items.feather)) {
				hasFeather = true;
				break;
			}
		
		if(!hasFeather)
			event.drops.add(new EntityItem(event.entity.worldObj, chicken.posX, chicken.posY, chicken.posZ, new ItemStack(Items.feather, 1)));
	}
	
	@Override
	public boolean hasSubscriptions() {
		return true;
	}
	
}
