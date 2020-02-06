package goro.igor.exampleexhibitionapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import goro.igor.model.Exhibit
import kotlinx.android.synthetic.main.exhibits_list_item.view.*

class ExhibitionAdapter : RecyclerView.Adapter<ExhibitionAdapter.ItemsHolder>() {

    // Why did you use 'var' instead of 'val'?
    private var exhibits: List<Exhibit> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemsHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.exhibits_list_item, parent, false)
        return ItemsHolder(itemView)
    }

    override fun onBindViewHolder(holder: ItemsHolder, position: Int) {
        recyclerViewSetUp(holder.horizontalRecycler, exhibits[position])
    }

    override fun getItemCount(): Int {
        return exhibits.size
    }

    private fun recyclerViewSetUp(horizontalRecycler: RecyclerView, exhibit: Exhibit) {
        horizontalRecycler.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            setHasFixedSize(true)

            val horizontalAdapter = HorizontalAdapter()
            horizontalAdapter.setExhibit(exhibit)
            adapter = horizontalAdapter
        }
    }

    // What happen if your list (exhibits) will be modified without calling any notify-method?
    fun setExhibits(exhibits: List<Exhibit>) {
        this.exhibits = exhibits
        notifyDataSetChanged()
    }

    // can this class be not inner?
    inner class ItemsHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var horizontalRecycler: RecyclerView = itemView.horizontalScroll
    }
}