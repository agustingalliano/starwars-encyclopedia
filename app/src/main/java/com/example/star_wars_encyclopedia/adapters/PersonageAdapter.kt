package com.example.star_wars_encyclopedia.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.example.star_wars_encyclopedia.R
import com.example.star_wars_encyclopedia.domain.model.Personage
import com.example.star_wars_encyclopedia.listeners.OnItemClickListener
import com.example.star_wars_encyclopedia.view.PeopleViewHolder
import java.util.*
import kotlin.collections.ArrayList

class PersonageAdapter() : RecyclerView.Adapter<PeopleViewHolder>(), Filterable {

    private var personages :ArrayList<Personage> = ArrayList()
    lateinit var onItemClickListener: OnItemClickListener
    var personageFilterList: ArrayList<Personage> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PeopleViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_personage, parent, false)
        return PeopleViewHolder(view)
    }

    override fun onBindViewHolder(holder: PeopleViewHolder, position: Int) {
        holder.itemView.setOnClickListener { v ->
            if (v != null) onItemClickListener.onClick(v, holder.bindingAdapterPosition)
        }

        holder.render(personageFilterList[position])
    }

    override fun getItemCount(): Int {
        return personageFilterList.size
    }
    
    fun addPeoples(personages: ArrayList<Personage>) : Unit {
        this.personages.addAll(personages)
        personageFilterList = this.personages
    }

    fun setOnClickListener(onItemClickListener: OnItemClickListener) {
        this.onItemClickListener = onItemClickListener
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {val charSearch = constraint.toString()
                if (charSearch.isEmpty()) {
                    personageFilterList = personages
                } else { val resultList = ArrayList<Personage>()
                    for (row in personages) {
                        if (row.name.lowercase(Locale.ROOT).contains(charSearch.lowercase(Locale.ROOT))) {
                            resultList.add(row)
                        }
                    }
                    personageFilterList = resultList
                }
                val filterResults = FilterResults()
                filterResults.values = personageFilterList
                return filterResults
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                personageFilterList = results?.values as ArrayList<Personage>
                notifyDataSetChanged()
            }

        }
    }

}