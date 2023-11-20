package ar.edu.itba.example.api.data.network

class DataSourceException(
    var code: Int,
    message: String,
    var details: List<String>?
) : Exception(message)