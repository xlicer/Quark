/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Quark Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Quark
 *
 * Quark is Open Source and distributed under the
 * [ADD-LICENSE-HERE]
 *
 * File Created @ [28/03/2016, 15:57:43 (GMT)]
 */
package vazkii.quark.management.feature;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.gui.inventory.GuiContainerCreative;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotCrafting;
import net.minecraft.world.GameRules;
import net.minecraft.world.GameRules.ValueType;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import vazkii.quark.base.handler.DropoffHandler;
import vazkii.quark.base.module.Feature;
import vazkii.quark.base.network.NetworkHandler;
import vazkii.quark.base.network.message.MessageDropoff;
import vazkii.quark.management.client.gui.GuiButtonChest;
import vazkii.quark.management.client.gui.GuiButtonChest.Action;

public class StoreToChests extends Feature {

	public static final String GAME_RULE = "quark_allowDropoff";
	
	public static boolean clientDisabled;
	public static boolean invert;

	@Override
	public void setupConfig() {
		invert = loadPropBool("Invert button", "", false);
	}

	@SubscribeEvent
	@SideOnly(Side.CLIENT)
	public void initGui(GuiScreenEvent.InitGuiEvent.Post event) {
		if(Minecraft.getMinecraft().theWorld == null)
			clientDisabled = false;
		
		if(event.getGui() instanceof GuiInventory || event.getGui() instanceof GuiContainerCreative) {
			if(clientDisabled)
				return;
			
			GuiContainer guiInv = (GuiContainer) event.getGui();
			GuiContainerCreative creativeInv = null;
			if(guiInv instanceof GuiContainerCreative)
				creativeInv = (GuiContainerCreative) guiInv;

			int guiLeft = (guiInv.width - 176) / 2;
			int guiTop = (guiInv.height - 166) / 2;

			Container container = guiInv.inventorySlots;
			for(Slot s : container.inventorySlots)
				if(creativeInv != null || s instanceof SlotCrafting) {
					if(creativeInv == null)
						event.getButtonList().add(new GuiButtonChest(guiInv, Action.DROPOFF, 13211, guiLeft + s.xDisplayPosition, guiTop + s.yDisplayPosition + 30));
					else {
						if(s.getSlotIndex() != 15)
							continue;

						event.getButtonList().add(new GuiButtonChest<GuiContainerCreative>(creativeInv, Action.DROPOFF, 13211, guiLeft + s.xDisplayPosition + 8, guiTop + s.yDisplayPosition - 20,
								(gui) -> gui.getSelectedTabIndex() == CreativeTabs.INVENTORY.getTabIndex()));
					}

					break;
				}
		}
	}

	@SubscribeEvent
	@SideOnly(Side.CLIENT)
	public void performAction(GuiScreenEvent.ActionPerformedEvent.Pre event) {
		if(event.getButton() instanceof GuiButtonChest && ((GuiButtonChest) event.getButton()).action == Action.DROPOFF) {
			boolean smart = GuiScreen.isShiftKeyDown() != StoreToChests.invert;
			NetworkHandler.INSTANCE.sendToServer(new MessageDropoff(smart, false));
			event.setCanceled(true);
		}
	}
	
	@SubscribeEvent
	public void worldLoad(WorldEvent.Load event) {
		GameRules rules = event.getWorld().getGameRules();
		if(!rules.hasRule(GAME_RULE))
			rules.addGameRule(GAME_RULE, "true", ValueType.BOOLEAN_VALUE);
	}
	
	@SubscribeEvent
	public void login(PlayerLoggedInEvent event) {
		if(!event.player.worldObj.getGameRules().getBoolean(GAME_RULE))
			DropoffHandler.disableClientDropoff(event.player);
	}

	@Override
	public boolean hasSubscriptions() {
		return isClient();
	}

}
