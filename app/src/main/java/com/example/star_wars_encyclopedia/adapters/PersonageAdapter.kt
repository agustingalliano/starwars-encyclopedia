package com.example.star_wars_encyclopedia.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.star_wars_encyclopedia.R
import com.example.star_wars_encyclopedia.domain.model.Personage
import com.example.star_wars_encyclopedia.listeners.OnItemClickListener
import com.example.star_wars_encyclopedia.view.PeopleViewHolder

class PersonageAdapter() : RecyclerView.Adapter<PeopleViewHolder>() {

    private var personages :ArrayList<Personage> = ArrayList()
    lateinit var onItemClickListener: OnItemClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PeopleViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_personage, parent, false)
        return PeopleViewHolder(view)
    }

    override fun onBindViewHolder(holder: PeopleViewHolder, position: Int) {
        holder.render(personages[position])

        holder.itemView.setOnClickListener { v ->
            if (v != null) onItemClickListener.onClick(v, holder.bindingAdapterPosition)
        }
    }

    override fun getItemCount(): Int {
        return personages.size
    }
    
    fun addPeoples(personages: ArrayList<Personage>) : Unit {
        this.personages.addAll(personages)
    }

    fun setOnClickListener(onItemClickListener: OnItemClickListener) {
        this.onItemClickListener = onItemClickListener
    }

}