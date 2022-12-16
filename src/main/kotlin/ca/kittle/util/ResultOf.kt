package ca.kittle.util

sealed class ResultOf<out T> {

    data class Success<out R>(val value: R): ResultOf<R>()
    data class Failure(
        val message: String?,
        val throwable: Throwable?
    ): ResultOf<Nothing>()
}

//inline fun <reified T> ResultOf<T>.ifFailure(callback: (error: String?, throwable: Throwable?) -> Unit) {
//    if (this is ResultOf.Failure) {
//        callback(message, throwable)
//    }
//}
//
//inline fun <reified T> ResultOf<T>.ifSuccess(callback: (value: T) -> Unit) {
//    if (this is ResultOf.Success) {
//        callback(value)
//    }
//}
