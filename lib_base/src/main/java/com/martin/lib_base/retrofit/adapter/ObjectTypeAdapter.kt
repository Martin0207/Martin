package com.martin.lib_base.retrofit.adapter

import com.google.gson.*
import java.lang.Exception
import java.lang.reflect.Type

class ObjectTypeAdapter : JsonDeserializer<JsonObject> {

    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): JsonObject {
        return try {
            json!!.asJsonObject
        } catch (e: Exception) {
            JsonObject()
        }
    }
}