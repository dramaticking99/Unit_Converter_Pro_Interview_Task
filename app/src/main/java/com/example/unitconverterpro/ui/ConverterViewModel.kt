package com.example.unitconverterpro.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.unitconverterpro.NativeBridge
import com.example.unitconverterpro.ui.models.UnitCategory

class ConverterViewModel : ViewModel() {
    val inputText       = mutableStateOf("")
    val selectedCategory= mutableStateOf<UnitCategory?>(null)
    val fromUnit        = mutableStateOf<String?>(null)
    val toUnit          = mutableStateOf<String?>(null)
    val resultText      = mutableStateOf("")

    var lastInputValue by mutableStateOf<Double?>(null)
    var lastOutputValue by mutableStateOf<Double?>(null)

    // Called when user types in the text field
    fun onInputTextChanged(new: String) {
        inputText.value = new
    }

    // Called when user picks a category
    fun onCategorySelected(category: UnitCategory) {
        selectedCategory.value = category
        resultText.value = ""
        fromUnit.value = null
        toUnit.value   = null
    }

    // Called when user picks “from” unit
    fun onFromUnitSelected(unit: String) {
        fromUnit.value = unit
    }

    // Called when user picks “to” unit
    fun onToUnitSelected(unit: String) {
        toUnit.value = unit
    }

    // Called when “Convert” button is pressed
    fun performConversion() {
        val input = inputText.value.toDoubleOrNull()
        if (input == null) {
            resultText.value = "Please enter a valid number"
            return
        }
        if (fromUnit.value.isNullOrBlank() || toUnit.value.isNullOrBlank()) {
            resultText.value = "Select both From and To units"
            return
        }
        selectedCategory.value?.let { cat ->
            val converted = NativeBridge.nativeConvert(
                cat.ordinal,
                input,
                fromUnit.value!!,
                toUnit.value!!
            )
            resultText.value = "$input ${fromUnit.value} ＝ $converted ${toUnit.value}"

            lastInputValue  = input
            lastOutputValue = converted
        }
    }

    fun clearResult() {
        resultText.value = ""
        lastInputValue = null
        lastOutputValue = null
    }

}