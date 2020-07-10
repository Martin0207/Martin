package com.martin.lib_base.retrofit.adapter

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonWriter
import java.lang.Exception
import java.lang.NumberFormatException
import java.lang.reflect.Type

class FloatTypeAdapter : JsonDeserializer<Float> {

    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): Float {
        return try {
            json!!.asFloat
        } catch (e: Exception) {
            0f
        }
    }

}
