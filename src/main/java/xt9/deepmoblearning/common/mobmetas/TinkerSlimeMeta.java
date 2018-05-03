package xt9.deepmoblearning.common.mobs;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import slimeknights.tconstruct.world.entity.EntityBlueSlime;

/**
 * Created by xt9 on 2018-03-21.
 */
public class TinkerSlimeMeta extends MobMetaData {
    static String[] mobTrivia = {"The elusive blue slime. Seemingly a", "part of some sort of power hierarchy", "since there's a bunch of \"King slimes\" around."};

    TinkerSlimeMeta(String key, String name, String pluralName, int numberOfHearts, int interfaceScale, int interfaceOffsetX, int interfaceOffsetY, Item livingMatter, Item pristineMatter) {
        super(key, name, pluralName, numberOfHearts, interfaceScale, interfaceOffsetX, interfaceOffsetY, livingMatter, pristineMatter, mobTrivia);
    }

    @Override
    public EntityZombie getCombatEntity(World world) {
        EntityZombie entity = new EntityZombie(world);
        entity.setHeldItem(EnumHand.MAIN_HAND, new ItemStack(Items.DIAMOND_SWORD));
        return entity;
    }

    @Override
    public boolean entityLivingMatchesMob(EntityLivingBase entityLiving) {
        return entityLiving instanceof EntityBlueSlime;
    }

    public EntityBlueSlime getEntity(World world) {
        return new EntityBlueSlime(world);
    }
}