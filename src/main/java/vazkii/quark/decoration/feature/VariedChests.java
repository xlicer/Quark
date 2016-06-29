/**
 * This class was created by <SanAndreasP>. It's distributed as
 * part of the Quark Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Quark
 *
 * Quark is Open Source and distributed under the
 * [ADD-LICENSE-HERE]
 *
 * File Created @ [20/03/2016, 22:33:44 (GMT)]
 */
package vazkii.quark.decoration.feature;

import net.minecraft.block.BlockChest.Type;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import vazkii.quark.base.lib.LibMisc;
import vazkii.quark.base.module.Feature;
import vazkii.quark.decoration.block.BlockCustomChest;
import vazkii.quark.decoration.client.render.RenderTileCustomChest;
import vazkii.quark.decoration.tile.TileCustomChest;

import java.util.HashMap;
import java.util.Map;

public class VariedChests extends Feature {

	public static final Type CUSTOM_TYPE_QUARK = EnumHelper.addEnum(Type.class, "QUARK", new Class[0]);
    public static final Type CUSTOM_TYPE_QUARK_TRAP = EnumHelper.addEnum(Type.class, "QUARK_TRAP", new Class[0]);
	
    public static final ResourceLocation TRAP_RESOURCE = new ResourceLocation(LibMisc.PREFIX_MOD + "textures/models/chests/trap.png");
    public static final ResourceLocation TRAP_DOUBLE_RESOURCE = new ResourceLocation(LibMisc.PREFIX_MOD + "textures/models/chests/trap_double.png");
    
    public static BlockCustomChest custom_chest;
    public static BlockCustomChest custom_chest_trap;

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        custom_chest = new BlockCustomChest("custom_chest", CUSTOM_TYPE_QUARK);
        custom_chest_trap = new BlockCustomChest("custom_chest_trap", CUSTOM_TYPE_QUARK_TRAP);

        registerTile(TileCustomChest.class, "quark_chest");
        
        //TODO: add crafting recipe
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void preInitClient(FMLPreInitializationEvent event) {
        ClientRegistry.bindTileEntitySpecialRenderer(TileCustomChest.class, new RenderTileCustomChest());
    }

    public enum ChestType {
        NONE(""),
        SPRUCE("spruce"),
    	BIRCH("birch"),
    	JUNGLE("jungle"),
        ACACIA("acacia"),
        DARK_OAK("dark_oak");
    	
        public final String name;
        public final ResourceLocation nrmTex;
        public final ResourceLocation dblTex;

        public static final ChestType[] VALID_TYPES;
        public static final Map<String, ChestType> NAME_TO_TYPE;

        ChestType(String name) {
            this.name = name;
            this.nrmTex = new ResourceLocation(LibMisc.PREFIX_MOD + "textures/models/chests/" + name + ".png");
            this.dblTex = new ResourceLocation(LibMisc.PREFIX_MOD + "textures/models/chests/" + name + "_double.png");
        }

        public static ChestType getType(String type) {
            return NAME_TO_TYPE.containsKey(type) ? NAME_TO_TYPE.get(type) : NONE;
        }

        static {
            VALID_TYPES = new ChestType[] { SPRUCE, BIRCH, JUNGLE, ACACIA, DARK_OAK };
            NAME_TO_TYPE = new HashMap<>();
            for( ChestType type : VALID_TYPES )
                NAME_TO_TYPE.put(type.name, type);
        }
    }
}
