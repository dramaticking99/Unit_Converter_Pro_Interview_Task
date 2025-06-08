package com.example.unitconverterpro.ui

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.unitconverterpro.ui.components.*
import com.example.unitconverterpro.ui.models.UnitCategory
import com.example.unitconverterpro.ui.theme.UnitConverterProTheme
import com.example.unitconverterpro.ui.viewmodel.ConverterViewModel

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.ui.Alignment
import androidx.navigation.NavController

import androidx.compose.material3.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.runtime.rememberCoroutineScope
import kotlinx.coroutines.launch
import com.example.unitconverterpro.data.AppDatabase
import com.example.unitconverterpro.data.ConversionEntity
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import kotlinx.coroutines.Dispatchers

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConverterScreen(
    navController: NavController,
    viewModel: ConverterViewModel = viewModel()
) {
    val context = LocalContext.current
    val dao     = AppDatabase.getInstance(context).conversionDao()
    val scope   = rememberCoroutineScope()

    // subscribe to ViewModel state
    val inputText       by viewModel.inputText
    val selectedCategory by viewModel.selectedCategory
    val fromUnit        by viewModel.fromUnit
    val toUnit          by viewModel.toUnit
    val resultText      by viewModel.resultText

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Unit Converter Pro") },
                actions = {
                    IconButton(onClick = { navController.navigate("history") }) {
                        Icon(Icons.Default.Refresh, contentDescription = "History")
                    }
                }
            )
        }
    ) { innerPadding->
        Surface(
            modifier    = Modifier
                .padding(innerPadding)
                .padding(16.dp)
                .fillMaxSize(),
            color       = MaterialTheme.colorScheme.background,
            contentColor= MaterialTheme.colorScheme.onBackground
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                // Category dropdown
                UnitDropdown(
                    label = "Select Category",
                    options = UnitCategory.entries.map { it.displayName },
                    selectedOption = selectedCategory?.displayName,
                    onOptionSelected = { name ->
                        UnitCategory.entries
                            .first { it.displayName == name }
                            .also { viewModel.onCategorySelected(it) }
                    }
                )

                // From-unit dropdown
                UnitDropdown(
                    label = "From",
                    options = selectedCategory?.units ?: emptyList(),
                    selectedOption = fromUnit,
                    onOptionSelected = viewModel::onFromUnitSelected,
                    enabled = selectedCategory != null
                )

                // To-unit dropdown
                UnitDropdown(
                    label = "To",
                    options = selectedCategory?.units ?: emptyList(),
                    selectedOption = toUnit,
                    onOptionSelected = viewModel::onToUnitSelected,
                    enabled = selectedCategory != null
                )

                // Input field
                ValueInputField(
                    value = inputText,
                    onValueChange = viewModel::onInputTextChanged
                )

                AnimatedVisibility(
                    visible = resultText.isNotEmpty(),
                    enter   = slideInVertically(initialOffsetY = { -it }) + fadeIn(),
                    exit    = slideOutVertically(targetOffsetY = { -it }) + fadeOut()
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text      = resultText,
                            style     = MaterialTheme.typography.bodyLarge,
                            modifier  = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Center
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                    }
                }

                // Convert button
                ConvertButton(onClick = {
                    // 1) run all your checks + compute
                    viewModel.performConversion()

                    // 2) if VM captured valid numbers, save them
                    val input  = viewModel.lastInputValue  ?: return@ConvertButton
                    val output = viewModel.lastOutputValue ?: return@ConvertButton
                    val cat    = viewModel.selectedCategory.value ?: return@ConvertButton
                    val from   = viewModel.fromUnit.value  ?: return@ConvertButton
                    val to     = viewModel.toUnit.value    ?: return@ConvertButton

                    scope.launch(Dispatchers.IO) {
                        dao.insert(
                            ConversionEntity(
                                category    = cat.name,
                                fromUnit    = from,
                                toUnit      = to,
                                inputValue  = input,
                                resultValue = output
                            )
                        )
                    }
                })

                Spacer(Modifier.height(16.dp))
            }
        }
    }
}

@Preview(
    name            = "Light Mode",
    uiMode          = Configuration.UI_MODE_NIGHT_NO,
    showBackground  = true,
    widthDp         = 360,
    heightDp        = 640
)
@Preview(
    name            = "Dark Mode",
    uiMode          = Configuration.UI_MODE_NIGHT_YES,
    showBackground  = true,
    widthDp         = 360,
    heightDp        = 640
)
@Composable
fun ConverterDualPreview() {
    UnitConverterProTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color    = MaterialTheme.colorScheme.background
        ) {
//            ConverterScreen()
        }
    }
}
