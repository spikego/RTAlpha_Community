package cn.rtalpha

import cn.rtalpha.modules.*
import cn.rtalpha.value.AbstractSetting
import java.util.concurrent.CopyOnWriteArrayList

object ModuleManager {
    var modules = CopyOnWriteArrayList<Module>()


    init {
        addModule(Sprint)
        addModule(Watermark)
        addModule(ArrayList)
        addModule(Timelist)
        addModule(DateList)
        addModule(NoClickDelay)
        addModule(Crasher)
        addModule(ClickGui)
        addModule(NameList)
        addModule(FPSList)
        addModule(FullBright)
        addModule(NameTags)
        addModule(AutoClicker)
        addModule(FastPlace)
        addModule(ESP)
        addModule(AimAssist)
        addModule(Blink)
    }


    fun addModule(module: Module) {
        module::class.java.declaredFields.forEach {
            it.isAccessible = true
            val obj = it.get(module)
            if(obj is AbstractSetting<*>) {
                module.settings.add(obj)
            }
        }
        modules.add(module)
    }

    fun getModules(): List<Module> {
        return modules
    }





}