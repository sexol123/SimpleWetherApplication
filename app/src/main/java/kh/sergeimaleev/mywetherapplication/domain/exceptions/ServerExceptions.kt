package kh.sergeimaleev.mywetherapplication.domain.exceptions

import java.io.IOException

abstract class ServerException(message: String) : IOException(message)

class UnauthorizedException : ServerException(LOG_ERROR_MESSAGE) {
    companion object {
        private const val LOG_ERROR_MESSAGE = "Server Error: Wrong email or password"
    }
}

class UnauthenticatedException : ServerException(LOG_ERROR_MESSAGE) {
    companion object {
        private const val LOG_ERROR_MESSAGE = "Server Error: A valid access token is required"
    }
}

class UnknownServerException : ServerException(LOG_ERROR_MESSAGE) {
    companion object {
        private const val LOG_ERROR_MESSAGE = "Server Error: An unknown exception occurred"
    }
}

class UnprocessableEntityException(val error: String?) :
    ServerException(error ?: LOG_ERROR_MESSAGE) {
    companion object {
        private const val LOG_ERROR_MESSAGE = "Server Error: Unprocessable Entity"
    }
}

class NotFoundServerException(message: String? = "") :
    ServerException("$LOG_ERROR_MESSAGE $message") {
    companion object {
        private const val LOG_ERROR_MESSAGE = "Server Error: Not found"
    }
}

class ErrorServerException : ServerException(LOG_ERROR_MESSAGE) {
    companion object {
        private const val LOG_ERROR_MESSAGE = "Server Error: Unknown Error"
    }
}

class InvalidInputServerException : ServerException(LOG_ERROR_MESSAGE) {
    companion object {
        private const val LOG_ERROR_MESSAGE = "Server Error: Invalid Input"
    }
}