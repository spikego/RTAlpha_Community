package cn.rtalpha.modules

import cn.rtalpha.Category
import cn.rtalpha.Module
import cn.rtalpha.value.BooleanSetting

object Sprint: Module("Sprint","sprint.",Category.MOVEMENT) {
    val test = BooleanSetting("test","sb.",false)
    init {
        this.enabled = true
    }


    override fun onTick() {
        if ((mc.player != null && mc.world != null )) {
            mc.player!!.isSprinting = true
        }

    }

}