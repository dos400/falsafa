package uz.hamroev.faylasuf.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.hamroev.faylasuf.databinding.ItemNavBinding
import uz.hamroev.faylasuf.models.Nav

class NavAdapter(
    var context: Context,
    var list: ArrayList<Nav>,
    var onNavClickListener: OnNavClickListener
) : RecyclerView.Adapter<NavAdapter.VhNav>() {


    inner class VhNav(var itemNavBinding: ItemNavBinding) :
        RecyclerView.ViewHolder(itemNavBinding.root) {


        fun onBind(nav: Nav, position: Int) {
            itemNavBinding.navNameTextView.text = nav.navName
            itemNavBinding.navIconImageView.setImageResource(nav.navIcon)
            itemNavBinding.main.setOnClickListener {
                onNavClickListener.onCLick(nav, position)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VhNav {
        return VhNav(ItemNavBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: VhNav, position: Int) {
        return holder.onBind(list[position], position)
    }

    interface OnNavClickListener {
        fun onCLick(nav: Nav, position: Int)
    }


}