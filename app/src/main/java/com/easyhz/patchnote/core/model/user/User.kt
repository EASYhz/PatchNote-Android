package com.easyhz.patchnote.core.model.user

data class User(
    val id: String = "",
    val name: String = "",
    val phone: String = "",
    val teamId: String,
) {
    companion object {
        val Empty = User(
            id = "",
            name = "",
            phone = "",
            teamId = "",
        )
    }
}
