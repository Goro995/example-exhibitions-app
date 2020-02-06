package goro.igor.model

import android.content.Context

interface ExhibitsLoader {

    fun getExhibitList(context: Context): List<Exhibit> // getExhibitList():List<Exhibit>
}