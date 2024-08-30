package com.example.session9_todo.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.session9_todo.tasks.AddBottomSheet
import com.example.session9_todo.tasks.ListFragment
import com.example.session9_todo.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeActivity : AppCompatActivity() {
    private lateinit var buttonNaviagtion : BottomNavigationView
    var listFragment: ListFragment?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        buttonNaviagtion = findViewById(R.id.navigation_bar)

        buttonNaviagtion.selectedItemId = R.id.navigation_list
        pushFragment(ListFragment())

        //to go on fragment clicked on
        buttonNaviagtion.setOnItemSelectedListener { item ->
            if (item.itemId == R.id.navigation_list){
                //push fragment
                pushFragment(ListFragment())
            } else if (item.itemId == R.id.navigation_add){
                showBottomSheet()
            }
        return@setOnItemSelectedListener true
        }


    }

    private fun pushFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container,fragment)
            .commit()
    }

    private fun showBottomSheet(){
        //create instance of AddBottom_Sheet() class
        val addBottomSheet = AddBottomSheet()
        // show bottom sheet
        addBottomSheet.show(supportFragmentManager,"")

        addBottomSheet.onTodoAddListener = object : AddBottomSheet.OnTodoAddListener{
            override fun onTodoAdd() {
                //Refresh db
                listFragment = supportFragmentManager.findFragmentById(R.id.fragment_container) as? ListFragment
                listFragment?.getTodoListFromDatabase()

            }

        }
    }

}