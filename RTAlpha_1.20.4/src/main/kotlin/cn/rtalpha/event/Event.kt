package cn.rtalpha.event

open class Event {
    var cancelled = false
    open class CancellableEvent : Event() {

        /**
         * Let you know if the event is cancelled
         *
         * @return state of cancel
         */
        var isCancelled: Boolean = false
            private set

        /**
         * Allows you to cancel an event
         */
        fun cancelEvent() {
            isCancelled = true
        }

    }
}