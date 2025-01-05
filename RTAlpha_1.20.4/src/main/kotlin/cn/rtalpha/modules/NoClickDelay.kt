package cn.rtalpha.modules

import cn.rtalpha.Category;
import cn.rtalpha.Module;

object NoClickDelay : Module("NoClickDelay", "no click delay", Category.COMBAT) {
    init {
        this.enabled = false
    }
}