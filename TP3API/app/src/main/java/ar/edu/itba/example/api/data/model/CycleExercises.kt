package ar.edu.itba.example.api.data.model

import ar.edu.itba.example.api.data.network.model.NetworkCycleExercises

class CycleExercises(
    var content: List<CycleExercise>,
    val orderBy: String,
    var direction: String,
    var isLastPage: Boolean
) {
    fun asNetworkModel() : NetworkCycleExercises {
        return NetworkCycleExercises(
            content = content,
            orderBy = orderBy,
            direction = direction,
            isLastPage = isLastPage
        )
    }
}

data class CycleData(
    val cycleName: String,
    val cycleRepetitions: Int,
    val cycleExercises: List<CycleExercise>
)