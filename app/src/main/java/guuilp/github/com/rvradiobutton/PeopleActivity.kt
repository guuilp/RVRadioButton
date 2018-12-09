package guuilp.github.com.rvradiobutton

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_main.*

class PeopleActivity : AppCompatActivity() {

    private val listItems = listOf(
        Person("Person 1", "Brazil"),
        Person("Person 2", "USA"),
        Person("Person 3", "Canada"),
        Person("Person 4", "Russia"),
        Person("Person 5", "Germany")
    )

    private lateinit var viewModel: PeopleViewModel
    private lateinit var adapter: Adapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initComponents()
        observeChanges()
    }

    private fun initComponents() {
        viewModel = ViewModelProviders.of(this).get(PeopleViewModel::class.java)
        adapter = Adapter(listItems, AdapterListener())
        list.adapter = adapter
        button.setOnClickListener {
            viewModel.getSelectedPerson().value?.let { person ->
                Toast.makeText(this, person.name, Toast.LENGTH_LONG).show()
            } ?: Toast.makeText(this, "You need to select someone", Toast.LENGTH_LONG).show()
        }
    }

    private fun observeChanges() {
        viewModel.getSelectedPerson().observe(this, Observer {
            adapter.updateSelectedPerson(it)
        })
    }

    private inner class AdapterListener : Adapter.Listener {
        override fun onItemClicked(person: Person) {
            viewModel.setSelectedPerson(person)
        }
    }
}
