package com.example.session9_todo.tasks

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.session9_todo.tasks.adapter.ListAdapter
import com.example.session9_todo.R
import com.example.session9_todo.database.MyDatabase
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.MaterialCalendarView

class ListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list,container,false)
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        onResume()
    }
    private lateinit var recyclerView: RecyclerView
    private val listAdapter = ListAdapter(null)

    private lateinit var calendarView: MaterialCalendarView
    private fun initView(){
        calendarView = requireView().findViewById(R.id.calendarView)
        recyclerView = requireView().findViewById(R.id.recyclerView_list)
        //to select today on calender in page
        calendarView.selectedDate = CalendarDay.today()
        recyclerView.adapter = listAdapter
    }


    override fun onResume() {
        super.onResume()
        // load database
        getTodoListFromDatabase()
    }

    fun getTodoListFromDatabase(){
        // get all todo
        val todoList = MyDatabase.getInstance(requireContext())
            .todoDau().getAllTodo()

        // load to list in adapter
        listAdapter.changeData(todoList.toMutableList())


    }

}