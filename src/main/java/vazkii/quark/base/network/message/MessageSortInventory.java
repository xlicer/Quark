package vazkii.quark.base.network.message;

import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import vazkii.arl.network.NetworkMessage;
import vazkii.quark.base.handler.SortingHandler;

public class MessageSortInventory extends NetworkMessage {

	public MessageSortInventory() { }

	@Override
	public IMessage handleMessage(MessageContext context) {
		SortingHandler.sortInventory(context.getServerHandler().playerEntity);
		return null;
	}

}
