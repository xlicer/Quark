/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Quark Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Quark
 * 
 * Quark is Open Source and distributed under the
 * [ADD-LICENSE-HERE]
 * 
 * File Created @ [02/07/2016, 20:02:38 (GMT)]
 */
package vazkii.quark.world.entity;

import net.minecraft.client.resources.I18n;
import net.minecraft.entity.Entity;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.monster.ZombieType;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTippedArrow;
import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.PotionType;
import net.minecraft.potion.PotionUtils;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import vazkii.quark.world.feature.DepthMobs;

public class EntityDweller extends EntityZombie {

	public EntityDweller(World worldIn) {
		super(worldIn);
	}
	
	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SPAWN_REINFORCEMENTS_CHANCE).setBaseValue(0);
		getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(3);
	}
	
	@Override
	public String getName() {
        if(hasCustomName()) 
        	return getCustomNameTag();

		return I18n.format("entity.Quark.dweller.name");
	}
	
	@Override
	protected void setEquipmentBasedOnDifficulty(DifficultyInstance difficulty) {
		// NO-OP
	}
	
	@Override
	public ZombieType func_189777_di() {
		return ZombieType.NORMAL;
	}
	
	@Override
	public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, IEntityLivingData livingdata) {
		addPotionEffect(new PotionEffect(MobEffects.INVISIBILITY, Integer.MAX_VALUE));
		return super.onInitialSpawn(difficulty, livingdata);
	}
	
	@Override
	public boolean attackEntityFrom(DamageSource source, float amount) {
		removePotionEffect(MobEffects.INVISIBILITY);
		return super.attackEntityFrom(source, amount);
	}
	
	@Override
	public boolean attackEntityAsMob(Entity entityIn) {
		removePotionEffect(MobEffects.INVISIBILITY);
		return super.attackEntityAsMob(entityIn);
	}
	
	@Override
	public boolean getCanSpawnHere() {
		return super.getCanSpawnHere() && posY < DepthMobs.upperBound;
	}
	
}
