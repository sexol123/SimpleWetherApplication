package kh.sergeimaleev.mywetherapplication.data.rest.client

interface RestClient {
    fun <T> createRemoteDataSourceFrom(remoteDataSource: Class<T>): T
}