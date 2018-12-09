package guuilp.github.com.rvradiobutton

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PeopleViewModel : ViewModel() {
    private val selectedPerson = MutableLiveData<Person>()

    fun setSelectedPerson(person: Person){
        selectedPerson.value = person
    }

    fun getSelectedPerson() = selectedPerson
}