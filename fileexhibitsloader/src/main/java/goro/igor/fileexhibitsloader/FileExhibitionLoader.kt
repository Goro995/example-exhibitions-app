package goro.igor.fileexhibitsloader

import android.content.ContentValues.TAG
import android.content.Context
import android.util.Log
import com.google.gson.Gson
import goro.igor.model.Exhibit
import goro.igor.model.ExhibitsLoader
import org.json.JSONObject

object FileExhibitionLoader : ExhibitsLoader {

    override fun getExhibitList(context: Context): List<Exhibit> {
        try {
            val listJson = context.assets.open("list.json").bufferedReader().use {
                it.readText()
            }

            Gson().apply {
                val jsonObject = JSONObject(listJson)["list"].toString()
                return fromJson(jsonObject, Array<Exhibit>::class.java).toList()
            }
        } catch (e: Exception) {
            Log.d(TAG, e.toString())
        }
        return listOf()
    }
}