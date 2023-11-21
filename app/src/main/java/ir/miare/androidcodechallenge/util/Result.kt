package ir.miare.androidcodechallenge.util

sealed class Result<out R> {
    data class Success<T>(val data: T) : Result<T>()
    data class Error(val error: ErrorResult) : Result<Nothing>()
}

data class ErrorResult(
    val errorCode: Int = -1,
    val errorResponse: String = "",
    val errorCause: ErrorCause = ErrorCause.EXCEPTION,
)

enum class ErrorCause {
    INTERNET, API, EXCEPTION, VALIDATION
}