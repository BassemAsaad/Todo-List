package com.example.session9_todo.tasks

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.DatePicker
import android.widget.TextView
import com.example.session9_todo.R
import com.example.session9_todo.database.MyDatabase
import com.example.session9_todo.database.model.Todo
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.textfield.TextInputLayout
import java.util.Calendar

class AddBottomSheet :BottomSheetDialogFragment() {
    // created outside showDatePicker function to create default date
    private val calendar = Calendar.getInstance()

    private lateinit var titleLayout : TextInputLayout
    private lateinit var detailsLayout : TextInputLayout
    lateinit var chooseDate : TextView
    private lateinit var addButton : Button
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater
            .inflate(R.layout.fragment_add,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

         init()

    }


    private fun init() {
        callBack()

                chooseDate.setText(
            " "+calendar.get(Calendar.DAY_OF_MONTH)
                    + " / "+calendar.get((Calendar.MONTH+1))
                    +" / " +calendar.get(Calendar.YEAR)
        )
        // create DatePicker in chooseDate field
        chooseDate.setOnClickListener{
            //showDatePicker()
            showDatePicker()
        }

        addButton.setOnClickListener{
            //1 validate
            // if validate is true
            if(validateForm()){
                // create variables to use in link with database
                val title = titleLayout.editText?.text.toString()
                val details = detailsLayout.editText?.text.toString()
                //2 insert into database
                insertIntoDatabase(title,details)

            }
        }

    }
    private fun callBack(){
        // link with id
        titleLayout = requireView().findViewById(R.id.title_layout)
        detailsLayout = requireView().findViewById(R.id.details_layout)
        chooseDate = requireView().findViewById(R.id.choose_date)
        addButton = requireView().findViewById(R.id.add_button)
    }

    private fun showDatePicker() {
        // create instance of DatePickerDialog()
        val datePicker = DatePickerDialog(
            //1 use this class
            requireContext(),
            //2 object of DatePickerDialog.OnDateSetListener
            object : DatePickerDialog.OnDateSetListener{
                // override fun onDateSet with (year month day)
                override fun onDateSet(p0: DatePicker?, year: Int, month: Int, day: Int) {
                    // calendar now has year month
                    calendar.set(Calendar.DAY_OF_MONTH,day)
                    calendar.set(Calendar.MONTH,month)
                    calendar.set(Calendar.YEAR,year)
                    chooseDate.text = " "+ day +" / "+ (month+1)  +" / "+ year
                }
            }
            //3
            ,calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH)
        )//finish

        // to show datePicker on field when clicked
        datePicker.show()
    }

    private fun validateForm():Boolean{
        var isValidate = true
        // if empty
        if ( titleLayout.editText?.text.toString().isBlank()){
            titleLayout.error = " Please enter the title"
            isValidate = false

        } else{
            titleLayout.error = null
        }
        if (detailsLayout.editText?.text.toString().isBlank()){
            detailsLayout.error= " Please enter the details "
            isValidate = false

        } else{
            detailsLayout.error = null
        }
        if (chooseDate.text.toString() == "Choose Date") {
            chooseDate.error = " Please choose the date "
            isValidate = false
        }else{
            chooseDate.error = null
        }

        return isValidate
    }

    private fun insertIntoDatabase(title: String, details: String) {
        val todo = Todo(
            name = title,
            details = details,
            date = calendar.time
        )
        MyDatabase.getInstance(requireContext().applicationContext).todoDau().addTodo(todo)

        onTodoAddListener?.onTodoAdd()
        dismiss()
    }

    interface OnTodoAddListener{
        fun onTodoAdd()
    }
    var onTodoAddListener: OnTodoAddListener?=null

}