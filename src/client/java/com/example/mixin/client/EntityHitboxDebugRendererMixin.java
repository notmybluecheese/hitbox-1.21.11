package com.example.mixin.client;

import net.minecraft.client.renderer.debug.EntityHitboxDebugRenderer;
import net.minecraft.gizmos.GizmoProperties;
import net.minecraft.gizmos.GizmoStyle;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(EntityHitboxDebugRenderer.class)
public class EntityHitboxDebugRendererMixin {

    // Vehicle riding-position indicator: 2nd cuboid call (ordinal 1) — thin yellow flat AABB at the passenger attachment point
    @Redirect(
        method = "showHitboxes",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/gizmos/Gizmos;cuboid(Lnet/minecraft/world/phys/AABB;Lnet/minecraft/gizmos/GizmoStyle;)Lnet/minecraft/gizmos/GizmoProperties;",
            ordinal = 1
        )
    )
    private GizmoProperties hideRidingPosition(AABB aabb, GizmoStyle style) {
        return null;
    }

    // Eye level indicator: 3rd cuboid call (ordinal 2) — thin flat AABB drawn at LivingEntity eye height
    @Redirect(
        method = "showHitboxes",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/gizmos/Gizmos;cuboid(Lnet/minecraft/world/phys/AABB;Lnet/minecraft/gizmos/GizmoStyle;)Lnet/minecraft/gizmos/GizmoProperties;",
            ordinal = 2
        )
    )
    private GizmoProperties hideEyeLevel(AABB aabb, GizmoStyle style) {
        return null;
    }

    @Redirect(
        method = "showHitboxes",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/gizmos/Gizmos;arrow(Lnet/minecraft/world/phys/Vec3;Lnet/minecraft/world/phys/Vec3;I)Lnet/minecraft/gizmos/GizmoProperties;"
        )
    )
    private GizmoProperties hideLookVector(Vec3 from, Vec3 to, int color) {
        return null;
    }
}
