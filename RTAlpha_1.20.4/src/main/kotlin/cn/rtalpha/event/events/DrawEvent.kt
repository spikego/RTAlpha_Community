package cn.rtalpha.event.events

import cn.rtalpha.event.Event
import net.minecraft.client.gui.DrawContext

class DrawEvent(var context: DrawContext,var delta: Float): Event()