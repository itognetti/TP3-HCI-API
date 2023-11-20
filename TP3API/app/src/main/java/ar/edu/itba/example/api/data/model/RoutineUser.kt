package ar.edu.itba.example.api.data.model

import ar.edu.itba.example.api.data.network.model.NetworkRoutineUser
import java.util.Date

data class RoutineUser(
    var id: Int?,
    var username: String?,
    var gender: String?,
    var avatarUrl: String?,
    var date: Date?,
    var lastActivity: Date?
) {
    fun asNetworkModel() : NetworkRoutineUser {
        return NetworkRoutineUser(
            id = id,
            username = username,
            gender = gender,
            avatarUrl = avatarUrl,
            date = date,
            lastActivity = lastActivity
        )
    }
}