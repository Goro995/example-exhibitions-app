package goro.igor.fileexhibitsloader

import android.content.ContentValues.TAG
import android.content.Context
import android.util.Log
import com.google.gson.Gson
import goro.igor.model.Exhibit
import goro.igor.model.ExhibitsLoader
import org.json.JSONObject

// Why is it object instead of simple class?
// Why was the data-file put in the assets of this module instead of app-module?
object FileExhibitionLoader : ExhibitsLoader {

    // You could use reader as a source for deserialization
    // and for avoiding redundant operations you could use
    // additional data class for description necessary structure, for example:
    // data class ListWrapper(@SerializedName("list") val list: List<Exhibit>)
    //
    // and your method can be replaced with something like that:
    //  src.bufferedReader().use {
    //      gson.fromJson(it, ListWrapper::class.java).list
    //  }
    //
    // where "src" and "gson" are fields of the class which were initialized via constructor
    //
    override fun getExhibitList(context: Context): List<Exhibit> {
        try {
            val listJson = context.assets.open("list.json").bufferedReader().use {
                it.readText()
            }

            Gson().apply {
                val jsonObject = JSONObject(listJson)["list"].toString()

                // interesting statement!!!
                return fromJson(jsonObject, Array<Exhibit>::class.java).toList()
            }
        } catch (e: Exception) {
            Log.d(TAG, e.toString())
        }
        return listOf()
    }
}