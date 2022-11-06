package edu.miu.lab5prob1

import android.app.AlertDialog
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.CheckBox
import android.widget.RadioButton
import androidx.annotation.RequiresApi
import kotlinx.android.synthetic.main.activity_main.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

class MainActivity : AppCompatActivity() {
    private var isQ1Correct = false
    private var isQ2Correct = false

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()

        rGrpQ1.setOnCheckedChangeListener{ group, checkId ->
                val clickedRbtn = group.findViewById<RadioButton>(checkId)
            isQ1Correct = clickedRbtn.isChecked && clickedRbtn.text.equals("val")
        }

        cb2B.setOnCheckedChangeListener { checkBtn, isChecked ->
            onCheckBoxClicked(checkBtn)
        }

        cb2A.setOnCheckedChangeListener { buttonView, isChecked ->
            onCheckBoxClicked(buttonView)
        }

        cb2C.setOnCheckedChangeListener { buttonView, isChecked ->
            onCheckBoxClicked(buttonView)
        }

        btnReset.setOnClickListener {
            rbtn1A.isChecked = false
            rbtn1B.isChecked = false
            rbtn1C.isChecked = false
            cb2A.isChecked = false
            cb2B.isChecked = false
            cb2C.isChecked = false
        }

        btnSubmit.setOnClickListener {
            val now = LocalDateTime.now()
            val currentDateTime: String = now.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM))
            var correctQcount = 0
            if(isQ1Correct) ++correctQcount
            if(isQ2Correct) ++correctQcount
            val percentage: Int = ((correctQcount / 2.0) * 100).toInt()

            val builder = AlertDialog.Builder(this@MainActivity)
            builder.setTitle("Submission Message")
            builder.setMessage("Congratualations! You have submitted on $currentDateTime. You achieved $percentage %")
            builder.setNeutralButton("OK") {dialogInterface, which ->
                dialogInterface.dismiss()
            }
            val dialog: AlertDialog = builder.create()
            dialog.show()
        }
    }

    fun onCheckBoxClicked(view: View){
        if(view is CheckBox){
            isQ2Correct = cb2B.isChecked && !cb2A.isChecked && !cb2C.isChecked
        }
    }

    fun init(){
        tvQuestion1.text = resources.getString(R.string.q1)
        tvQuestion2.text = resources.getString(R.string.q2)
        rbtn1A.text = resources.getStringArray(R.array.q1_answers)[0]
        rbtn1B.text = resources.getStringArray(R.array.q1_answers)[1]
        rbtn1C.text = resources.getStringArray(R.array.q1_answers)[2]
        cb2A.text = resources.getStringArray(R.array.q2_answers)[0]
        cb2B.text = resources.getStringArray(R.array.q2_answers)[1]
        cb2C.text = resources.getStringArray(R.array.q2_answers)[2]
    }
}