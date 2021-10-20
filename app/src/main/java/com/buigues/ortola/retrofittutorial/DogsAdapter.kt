package com.buigues.ortola.retrofittutorial

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.buigues.ortola.retrofittutorial.databinding.DogItemBinding
import com.squareup.picasso.Picasso

class DogsAdapter(private val images: List<String>): RecyclerView.Adapter<DogsAdapter.DogsViewHolder>() {

    class DogsViewHolder(view: View): RecyclerView.ViewHolder(view) {

        private val binding = DogItemBinding.bind(view)

        fun bind(image: String)
        {
            Picasso.get().load(image).into(binding.ivDog)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return DogsViewHolder(inflater.inflate(R.layout.dog_item, parent, false))
    }

    override fun onBindViewHolder(holder: DogsViewHolder, position: Int) {
        val item = images[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return images.size
    }
}