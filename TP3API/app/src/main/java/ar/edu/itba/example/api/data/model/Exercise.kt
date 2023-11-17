package ar.edu.itba.example.api.data.model

import ar.edu.itba.example.api.data.network.model.NetworkExercise
import java.util.Date

class Exercise(
    var id: Int,
    var name: String,
    var detail: String? = null,
    var type: String,
    var date: Date? = null,
    var order: Int
) {
    fun asNetworkModel() : NetworkExercise {
        return NetworkExercise(
            id = id,
            name = name,
            detail = detail,
            type = type,
            date = date,
            order = order
        )
    }
}
