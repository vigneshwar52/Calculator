package com.example.calculator

import android.os.Bundle
import android.util.Log.d
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.calculator.databinding.ActivityMainBinding
import net.objecthunter.exp4j.ExpressionBuilder

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        //setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnOne.setOnClickListener {
            d("appending value","One")
            appending("1",true)
        }
        binding.btnTwo.setOnClickListener {
            appending("2",true)
        }
        binding.btnThree.setOnClickListener {
            appending("3",true)
        }
        binding.btnFour.setOnClickListener {
            appending("4",true)
        }
        binding.btnFive.setOnClickListener {
            appending("5",true)
        }
        binding.btnSix.setOnClickListener {
            appending("6",true)
        }
        binding.btnSeven.setOnClickListener {
            appending("7",true)
        }
        binding.btnEight.setOnClickListener {
            appending("8",true)
        }
        binding.btnNine.setOnClickListener {
            appending("9",true)
        }
        binding.btnZero.setOnClickListener {
            appending("0", true)
        }
        binding.btnDot.setOnClickListener{
            appending(".",true)
        }
        binding.btnAC.setOnClickListener{
            binding.tvinput.text = ""
            binding.tvresult.text = ""
        }

        //Operations
        binding.btnPlus.setOnClickListener{
            appending("+",false)
        }
        binding.btnMinus.setOnClickListener{
            appending("-",false)
        }
        binding.btnMultiply.setOnClickListener{
            appending("*",false)
        }
        binding.btnDivide.setOnClickListener{
            appending("/",false)
        }
        binding.btnMod.setOnClickListener{
            appending("%",false)
        }

        binding.btnDel.setOnClickListener{
            val exp = binding.tvinput.text.toString()
            if(exp.isNotEmpty()){
                binding.tvinput.text = exp.substring(0,exp.length-1)
            }else{
                binding.tvresult.text = ""
            }
        }

        binding.btnEqual.setOnClickListener{
            try {
                val expression = ExpressionBuilder(binding.tvinput.text.toString()).build()
                val result = expression.evaluate()
                val longResult = result.toLong()
                if(result == longResult.toDouble()){
                    binding.tvresult.text = longResult.toString()
                }else{
                    binding.tvresult.text = result.toString()
                }
            }catch(e:Exception) {
               d("Exception","Message : "+e.message)
            }
        }
    }
    fun appending(Val: String,canClear : Boolean) {
        if(canClear){
            d("appending",Val)
            binding.tvresult.text = ""
            binding.tvinput.append(Val)
        }else{
            binding.tvinput.append(binding.tvresult.text)
            binding.tvinput.append(Val)
            binding.tvresult.text = ""
        }
    }
}