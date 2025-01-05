package cn.rtalpha.modules

import cn.rtalpha.Category
import cn.rtalpha.Module
import cn.rtalpha.value.BooleanSetting
import cn.rtalpha.value.NumberSetting
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.util.math.Box
import net.minecraft.util.math.Vec3d
import org.lwjgl.glfw.GLFW
import kotlin.math.atan2
import kotlin.math.sqrt

object AimAssist : Module("AimAssist", "Helps you aim at players", Category.COMBAT) {

    private val range = NumberSetting("Range", "Range to target players", 3.0, 6.0, 6.0, 1.0)
    private val speed = NumberSetting("Speed", "Aim speed", 1.0, 1000.0, 1000.0,3.0 )
    private val fov = NumberSetting("FOV", "Field of view to target", 30.0, 180.0, 180.0, 1.0)
    private val verticalSpeed = NumberSetting("Vertical Speed", "Vertical aim speed", 1.0, 10.0, 3.0, 1.0)
    private val predict = BooleanSetting("Predict", "Predict player movement", true)
    private val visibleOnly = BooleanSetting("Visible Only", "Only target visible players", false)
    private val clickAim = BooleanSetting("Click Aim", "Enable aim assist only when clicking", false)

    init {
        this.enabled = false
        this.key = GLFW.GLFW_KEY_R

        settings.add(range)
        settings.add(speed)
        settings.add(fov)
        settings.add(verticalSpeed)
        settings.add(predict)
        settings.add(visibleOnly)
        settings.add(clickAim) // 添加 clickAim 设置
    }

    override fun onTick() {
        if (!enabled || mc.player == null || mc.world == null) return

        // 如果启用了点击瞄准，并且当前没有点击，则不进行自动瞄准
        if (clickAim.value && !mc.options.attackKey.isPressed) return

        // Get closest valid target
        val target = getClosestTarget() ?: return

        // Calculate aim angles
        val angles = calculateAngles(target)

        // Apply aim assist
        applyAimAssist(angles.first, angles.second)
    }

    private fun getClosestTarget(): PlayerEntity? {
        var closest: PlayerEntity? = null
        var closestDistance = Double.MAX_VALUE

        for (player in mc.world!!.players) {
            // Skip if it's the client player or a spectator/creative player
            if (player == mc.player ||
                player.isSpectator ||
                player.isCreative ||
                !player.isAlive) continue

            // Check if player is in range
            val distance = mc.player!!.distanceTo(player)
            if (distance > range.value) continue

            // Check if player is within FOV
            //if (!isInFOV(player, fov.value)) continue

            // Check visibility if enabled
            if (visibleOnly.value && !mc.player!!.canSee(player)) continue

            // Update closest target
            if (distance < closestDistance) {
                closest = player
                closestDistance = distance.toDouble()
            }
        }

        return closest
    }

    private fun calculateAngles(target: PlayerEntity): Pair<Float, Float> {
        var targetPos = target.pos

        // Predict movement if enabled
        if (predict.value) {
            targetPos = targetPos.add(
                target.velocity.x,
                target.velocity.y,
                target.velocity.z
            )
        }

        // Calculate difference vectors
        val diffX = targetPos.x - mc.player!!.x
        val diffY = targetPos.y + target.standingEyeHeight - (mc.player!!.y + mc.player!!.standingEyeHeight)
        val diffZ = targetPos.z - mc.player!!.z

        // Calculate distances
        val dist = sqrt(diffX * diffX + diffZ * diffZ)

        // Calculate angles
        val yaw = (atan2(diffZ, diffX) * 180f / Math.PI).toFloat() - 90f
        val pitch = -(atan2(diffY, dist) * 180f / Math.PI).toFloat()

        return Pair(yaw, pitch)
    }

    private fun applyAimAssist(targetYaw: Float, targetPitch: Float) {
        // Get current angles
        var currentYaw = mc.player!!.yaw
        var currentPitch = mc.player!!.pitch

        // Normalize angles
        currentYaw = normalizeAngle(currentYaw)
        val normalizedTargetYaw = normalizeAngle(targetYaw)

        // Calculate angle differences
        var yawDiff = normalizedTargetYaw - currentYaw
        val pitchDiff = targetPitch - currentPitch

        // Normalize yaw difference
        if (yawDiff > 180) yawDiff -= 360f
        if (yawDiff < -180) yawDiff += 360f

        // Apply smooth aim movement
        mc.player!!.yaw += (yawDiff * (speed.value / 100f)).toFloat()
        mc.player!!.pitch += (pitchDiff * (verticalSpeed.value / 100f)).toFloat()

        // Clamp pitch
        mc.player!!.pitch = mc.player!!.pitch.coerceIn(-90f, 90f)
    }

    private fun isInFOV(target: PlayerEntity, fov: Double): Boolean {
        val yawDiff = normalizeAngle(getYawToEntity(target) - mc.player!!.yaw)
        return yawDiff.coerceIn((-fov/2).toFloat(), (fov/2).toFloat()) == yawDiff
    }

    private fun getYawToEntity(entity: PlayerEntity): Float {
        val diffX = entity.x - mc.player!!.x
        val diffZ = entity.z - mc.player!!.z
        return (atan2(diffZ, diffX) * 180f / Math.PI).toFloat() - 90f
    }

    private fun normalizeAngle(angle: Float): Float {
        var normalized = angle % 360f
        if (normalized < 0) normalized += 360f
        return normalized
    }
}
