package kh.sergeimaleev.mywetherapplication.domain.exceptions

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlin.coroutines.AbstractCoroutineContextElement
import kotlin.coroutines.CoroutineContext

inline fun SimpleExceptionHandler(crossinline handler: (error: Throwable) -> Unit): CoroutineExceptionHandler {
    return object : AbstractCoroutineContextElement(CoroutineExceptionHandler),
        CoroutineExceptionHandler {
        override fun handleException(context: CoroutineContext, exception: Throwable) =
            handler.invoke(processError(exception))

        fun processError(exception: Throwable?): Throwable {
            return exception ?: Throwable("Unknown error")
        }
    }
}
