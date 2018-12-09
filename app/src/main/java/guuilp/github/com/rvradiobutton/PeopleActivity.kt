package guuilp.github.com.rvradiobutton

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_people.*

class PeopleActivity : AppCompatActivity() {

    private val people = listOf(
        Person("Person 1", "Brazil"),
        Person("Person 2", "USA"),
        Person("Person 3", "Canada"),
        Person("Person 4", "Russia"),
        Person("Person 5", "Germany")
    )

    private lateinit var viewModel: PeopleViewModel
    private lateinit var adapter: PeopleAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_people)
        initComponents()
        observeChanges()
    }

    private fun initComponents() {
        viewModel = ViewModelProviders.of(this).get(PeopleViewModel::class.java)
        adapter = PeopleAdapter(people, AdapterListener())
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

    private inner class AdapterListener : PeopleAdapter.Listener {
        override fun onItemClicked(person: Person) {
            viewModel.setSelectedPerson(person)
        }
    }
}
