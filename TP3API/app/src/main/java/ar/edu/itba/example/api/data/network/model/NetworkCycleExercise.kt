package ar.edu.itba.example.api.data.network.model

import ar.edu.itba.example.api.data.model.CycleExercise
import com.google.gson.annotations.SerializedName

class NetworkCycleExercise(

    @SerializedName("exercise"    ) var exercise    : NetworkExercise,
    @SerializedName("order"       ) var order       : Int,
    @SerializedName("duration"    ) var duration    : Int?      = null,
    @SerializedName("repetitions" ) var repetitions : Int?      = null,
    @SerializedName("metadata"    ) var metadata    : String?   = null

) {
    fun asModel() : CycleExercise {
        return CycleExercise(
            exercise = exercise.asModel(),
            order = order,
            duration = duration,
            repetitions = repetitions,
            metadata = metadata
        )
    }
}