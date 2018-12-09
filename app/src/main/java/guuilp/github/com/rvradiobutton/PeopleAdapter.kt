package guuilp.github.com.rvradiobutton

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.people_list_item.view.*

class PeopleAdapter(private val people: List<Person>, private val listener: Listener) :
    RecyclerView.Adapter<PeopleAdapter.ViewHolder>() {

    private var selectedPerson: Person? = null

    interface Listener {
        fun onItemClicked(person: Person)
    }

    fun updateSelectedPerson(person: Person) {
        this.selectedPerson = person
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(parent.inflate(R.layout.people_list_item))

    override fun getItemCount() = people.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(people[position], listener)

    override fun getItemId(position: Int) = people[position].id

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(person: Person, listener: Listener) = with(itemView) {
            name.text = person.name
            country.text = person.country
            radio.isChecked = person == selectedPerson
            setOnClickListener { listener.onItemClicked(person) }
            radio.setOnClickListener { listener.onItemClicked(person) }
        }
    }
}