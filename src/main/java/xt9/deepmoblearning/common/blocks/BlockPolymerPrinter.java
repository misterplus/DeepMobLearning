package xt9.deepmoblearning.common.blocks;

import net.minecraft.block.BlockDirectional;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import xt9.deepmoblearning.common.tiles.TileEntitySimulationChamber;

/**
 * Created by xt9 on 2017-06-24.
 */
public class BlockPolymerPrinter extends BlockBase implements ITileEntityProvider {
        public BlockPolymerPrinter() {
            super("polymer_printer", Material.ROCK);
            setHardness(4f);
            setResistance(10.0f);
        }

        @Override
        public boolean isOpaqueCube(IBlockState state) {
            return false;
        }

        @Override
        public boolean isFullCube(IBlockState state) {
            return false;
        }

        @Override
        public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
            // TileEntitySimulationChamber tile = this.getTileEntity(world, pos);
            // CommonProxy.openTileEntityGui(world, player, tile, pos);
            return true;
        }

        @Override
        public void breakBlock(World world, BlockPos pos, IBlockState state) {
//            TileEntitySimulationChamber tile = getTileEntity(world, pos);
//            IItemHandler itemHandler = tile.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, EnumFacing.NORTH);
//            NonNullList<ItemStack> containedItems = ItemHandlerHelper.getItemStackHandlerList(itemHandler);
//
//            for (int i = 0; i < containedItems.size(); i++) {
//                if (!containedItems.get(i).isEmpty()) {
//                    EntityItem item = new EntityItem(world, pos.getX(), pos.getY(), pos.getZ(), containedItems.get(i));
//                    world.spawnEntity(item);
//                }
//            }
//            super.breakBlock(world, pos, state);
        }

        @Override
        public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase player, ItemStack stack) {
            int rotation = EnumFacing.getDirectionFromEntityLiving(pos, player).ordinal();
            world.setBlockState(pos, this.getStateFromMeta(rotation), 2);

            super.onBlockPlacedBy(world, pos, state, player, stack);
        }

        @Override
        public IBlockState getStateFromMeta(int meta){
            return this.getDefaultState().withProperty(BlockDirectional.FACING, EnumFacing.getFront(meta));
        }

        @Override
        public int getMetaFromState(IBlockState state){
            return state.getValue(BlockDirectional.FACING).getIndex();
        }

        @Override
        protected BlockStateContainer createBlockState(){
            return new BlockStateContainer(this, BlockDirectional.FACING);
        }

        @Override
        public IBlockState withRotation(IBlockState state, Rotation rot){
            return state.withProperty(BlockDirectional.FACING, rot.rotate(state.getValue(BlockDirectional.FACING)));
        }

        @Override
        public IBlockState withMirror(IBlockState state, Mirror mirror){
            return this.withRotation(state, mirror.toRotation(state.getValue(BlockDirectional.FACING)));
        }

        public Class<TileEntitySimulationChamber> getTileEntityClass() {
            return TileEntitySimulationChamber.class;
        }

        private TileEntitySimulationChamber getTileEntity(World world, BlockPos pos) {
            return (TileEntitySimulationChamber) world.getTileEntity(pos);
        }

        @Override
        public TileEntity createNewTileEntity(World world, int meta) {
            return new TileEntitySimulationChamber();
        }
}

