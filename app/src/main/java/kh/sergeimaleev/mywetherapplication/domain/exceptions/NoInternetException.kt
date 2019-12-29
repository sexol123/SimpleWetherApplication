package kh.sergeimaleev.mywetherapplication.domain.exceptions

import java.io.IOException

class NoInternetException : IOException(ERROR_MESSAGE) {
    companion object {
        private const val ERROR_MESSAGE = "No internet connection"
    }
}