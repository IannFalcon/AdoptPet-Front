package com.example.frontendadoptpet

import android.os.Bundle
import android.text.Editable
import android.text.InputFilter
import android.text.TextWatcher
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.frontendadoptpet.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.spnDocumento.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View?,
                position: Int,
                id: Long
            ) {
                validarNumeroDocumento()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
            }
        }

        binding.nroDocumento.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                validarNumeroDocumento()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })


        binding.btnIngresar.setOnClickListener {
            val password = binding.password.text.toString()

            if (isValidPassword(password)) {
                Toast.makeText(this, "Contraseña válida", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "La contraseña debe tener al menos 10 caracteres, contener un número, una letra mayúscula y una letra minúscula.", Toast.LENGTH_LONG).show()
            }
        }


    }

    private fun validarNumeroDocumento() {
        val tipoDocumento = binding.spnDocumento.selectedItem.toString()
        val numeroDocumento = binding.nroDocumento.text.toString()

        when (tipoDocumento) {
            "DNI" -> {
                binding.nroDocumento.filters = arrayOf(InputFilter.LengthFilter(8))
                binding.nroDocumento.inputType = android.text.InputType.TYPE_CLASS_NUMBER
                if (numeroDocumento.length != 8) {
                    binding.nroDocumento.error = "El DNI debe tener 8 dígitos"
                }
            }
            "CEX" -> {
                binding.nroDocumento.filters = arrayOf(InputFilter.LengthFilter(10))
                binding.nroDocumento.inputType = android.text.InputType.TYPE_CLASS_TEXT
                if (numeroDocumento.length != 10) {
                    binding.nroDocumento.error = "El CEX debe tener 10 caracteres alfanuméricos"
                }
            }
            "PAS" -> {
                binding.nroDocumento.filters = arrayOf(InputFilter.LengthFilter(12))
                binding.nroDocumento.inputType = android.text.InputType.TYPE_CLASS_NUMBER
                if (numeroDocumento.length != 12) {
                    binding.nroDocumento.error = "El PAS debe tener 12 dígitos"
                }
            }
            else -> {
                binding.nroDocumento.error = null
            }
        }
    }

    fun isValidPassword(password: String): Boolean {
        if (password.length < 10) return false

        if (!password.any { it.isDigit() }) return false

        if (!password.any { it.isUpperCase() }) return false

        if (!password.any { it.isLowerCase() }) return false

        return true
    }

}