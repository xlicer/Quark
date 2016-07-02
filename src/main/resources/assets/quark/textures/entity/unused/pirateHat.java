//package model;
//
//import net.minecraft.client.model.ModelBase;
//import net.minecraft.client.model.ModelRenderer;
//import net.minecraft.entity.Entity;
//
///**
// * ModelPirateQ - wiiv
// * Created using Tabula 4.1.1
// */
//public class pirateHat extends ModelBase {
//    public ModelRenderer field_78191_aChild;
//    public ModelRenderer field_78191_aChildChild;
//    public ModelRenderer field_78191_aChildChild_1;
//    public ModelRenderer field_78191_aChildChild_2;
//    public ModelRenderer field_78191_aChildChild_3;
//
//    public pirateHat() {
//        this.textureWidth = 64;
//        this.textureHeight = 64;
//        this.field_78191_aChildChild_2 = new ModelRenderer(this, 0, 30);
//        this.field_78191_aChildChild_2.setRotationPoint(0.0F, 0.0F, 0.0F);
//        this.field_78191_aChildChild_2.addBox(-5.0F, -4.0F, -3.0F, 1, 4, 8, 0.0F);
//        this.setRotateAngle(field_78191_aChildChild_2, -0.08726646259971647F, 0.0F, -0.08726646259971647F);
//        this.field_78191_aChild = new ModelRenderer(this, 0, 0);
//        this.field_78191_aChild.setRotationPoint(0.0F, -8.53F, 0.0F);
//        this.field_78191_aChild.addBox(-5.0F, 0.0F, -5.0F, 10, 1, 10, 0.0F);
//        this.setRotateAngle(field_78191_aChild, 0.05235987755982988F, 0.08726646259971647F, 0.0F);
//        this.field_78191_aChildChild = new ModelRenderer(this, 0, 11);
//        this.field_78191_aChildChild.setRotationPoint(0.0F, 0.0F, 0.0F);
//        this.field_78191_aChildChild.addBox(-3.5F, -4.0F, -3.5F, 7, 4, 8, 0.0F);
//        this.field_78191_aChildChild_3 = new ModelRenderer(this, 0, 23);
//        this.field_78191_aChildChild_3.setRotationPoint(0.0F, 0.0F, 0.0F);
//        this.field_78191_aChildChild_3.addBox(-5.0F, -6.4F, -5.0F, 10, 6, 1, 0.0F);
//        this.setRotateAngle(field_78191_aChildChild_3, 0.08726646259971647F, 0.0F, 0.0F);
//        this.field_78191_aChildChild_1 = new ModelRenderer(this, 0, 30);
//        this.field_78191_aChildChild_1.mirror = true;
//        this.field_78191_aChildChild_1.setRotationPoint(0.0F, 0.0F, 0.0F);
//        this.field_78191_aChildChild_1.addBox(4.0F, -4.0F, -3.0F, 1, 4, 8, 0.0F);
//        this.setRotateAngle(field_78191_aChildChild_1, -0.08726646259971647F, 0.0F, 0.08726646259971647F);
//        this.field_78191_aChild.addChild(this.field_78191_aChildChild_2);
//        this.field_78191_aChild.addChild(this.field_78191_aChildChild);
//        this.field_78191_aChild.addChild(this.field_78191_aChildChild_3);
//        this.field_78191_aChild.addChild(this.field_78191_aChildChild_1);
//    }
//
//    @Override
//    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
//        this.field_78191_aChild.render(f5);
//    }
//
//    /**
//     * This is a helper function from Tabula to set the rotation of model parts
//     */
//    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
//        modelRenderer.rotateAngleX = x;
//        modelRenderer.rotateAngleY = y;
//        modelRenderer.rotateAngleZ = z;
//    }
//}
