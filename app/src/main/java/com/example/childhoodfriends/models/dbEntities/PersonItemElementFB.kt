package com.example.childhoodfriends.models.dbEntities

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class PersonItemElementFB (val name: String? = null, val city: String? = null,
                              val neighborhood: String? = null, val email: String? = null,
                              val school: String? = null, val description: String? = null,
                              val freq_places: String? = null, val birthday: String? = null) {
    fun convert() = PersonItem(name, city, neighborhood, email, school, description, freq_places, birthday)
}