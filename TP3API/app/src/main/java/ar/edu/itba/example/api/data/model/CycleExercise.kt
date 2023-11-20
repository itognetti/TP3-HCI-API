package ar.edu.itba.example.api.data.model

data class CycleExercise (
    var exercise: Exercise,
    var order: Int? = null,
    var duration: Int? = null,
    var repetitions: Int? = null,
)