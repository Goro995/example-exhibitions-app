package goro.igor.exampleexhibitionapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import goro.igor.model.Exhibit
import kotlinx.android.synthetic.main.exhibit_image_list_item.view.*

class HorizontalAdapter : RecyclerView.Adapter<HorizontalAdapter.ItemsHolder>() {
    // why did you use the modifier "lateinit" instead of passing data object via constructor?
    // You don't have any protection from the invocation of any notify-method which become a case of app-crash
    private lateinit var exhibit: Exhibit

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemsHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.exhibit_image_list_item, parent, false)
        return ItemsHolder(itemView)
    }

    override fun onBindViewHolder(holder: ItemsHolder, position: Int) {
        holder.apply { // why did you use "apply" instead of "also", "let" or "run"?
            titleText.text = exhibit.title

            Picasso.get()
                .load(exhibit.images[position])
                .placeholder(R.drawable.image_placeholder)
                .resize(500, 500)
                .centerCrop()
                .into(imageView)
        }
    }

    override fun getItemCount(): Int {
        return exhibit.images.size
    }

    fun setExhibit(exhibit: Exhibit) {
        this.exhibit = exhibit
        notifyDataSetChanged()
    }

    // Can it be a nested class?
    inner class ItemsHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var titleText: TextView = itemView.titleText
        var imageView: ImageView = itemView.imageView
    }
}