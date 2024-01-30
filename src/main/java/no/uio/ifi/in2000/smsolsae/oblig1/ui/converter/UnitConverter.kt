package no.uio.ifi.in2000.smsolsae.oblig1.ui.converter

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import no.uio.ifi.in2000.smsolsae.oblig1.converter
import no.uio.ifi.in2000.smsolsae.oblig1.ConverterUnits
import no.uio.ifi.in2000.smsolsae.oblig1.Screens


@OptIn( ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class)
@Composable
fun UnitConverter(navController: NavController){
    var result by remember { mutableStateOf("0") }
    var inp by remember { mutableStateOf("") }

    val options = ConverterUnits.entries.map {
        it -> it.name.lowercase().replaceFirstChar {
            if (it.isLowerCase()) it.titlecase() else it.toString()
        }
    }

    var selectedOptionText by remember { mutableStateOf(options[0]) }

    fun updateResult(input: String, optionText: String){
        if (input.isNotBlank()){
            result = converter(input.toInt(), ConverterUnits.valueOf(optionText.uppercase())).toString() + " l"
        } else{
            result = "Field empty!"
        }

    }

    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(25.dp)
    ){
        Column(
            modifier = Modifier.weight(10f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ){
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(IntrinsicSize.Min),
                verticalAlignment = Alignment.Bottom
            ){
                val keyboardController = LocalSoftwareKeyboardController.current
                OutlinedTextField(
                    singleLine = true,
                    value = inp,
                    onValueChange = {inp = it},
                    label = { Text("$selectedOptionText's") },
                    modifier = Modifier
                        .weight(2f)
                        .fillMaxHeight(),

                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done, keyboardType = KeyboardType.Number),
                    keyboardActions = KeyboardActions(
                        onDone = {
                            keyboardController?.hide()
                            updateResult(inp, selectedOptionText)
                        }
                    )
                )
                Button(
                    modifier = Modifier
                        .weight(1f)
                        .align(Alignment.Bottom)
                        .fillMaxHeight()
                        .padding(top = 7.dp, start = 7.dp),
                    shape = RoundedCornerShape(10.dp),
                    onClick = {
                        println(inp)
                        updateResult(inp, selectedOptionText)
                    }
                ) {
                    Text(text = "Check")
                }
            }
            
            Spacer(modifier = Modifier.height(10.dp))

            //DROPDOWN MENU//

            var expanded by remember { mutableStateOf(false) }

            ExposedDropdownMenuBox(
                expanded = expanded,
                onExpandedChange = { expanded = it },
            ) {
                TextField(
                    value = selectedOptionText,
                    onValueChange = {},
                    readOnly = true,
                    label = {Text("Choose desired unit")},
                    trailingIcon = {ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)},
                    colors = TextFieldDefaults.colors(unfocusedIndicatorColor = Color.Transparent, focusedIndicatorColor = Color.Red),
                    modifier = Modifier
                        .menuAnchor()
                        .fillMaxWidth()
                )

                ExposedDropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false},
                ) {
                   options.forEach{optionToSelect ->
                       DropdownMenuItem(
                           text = {Text(text = optionToSelect)},
                           onClick = {
                               selectedOptionText = optionToSelect
                               expanded = false
                               updateResult(inp, selectedOptionText)
                           },
                           contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding,
                       )
                   }
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            Box(
                modifier = Modifier
                    .background(Color.Red.copy(alpha = 0.2f))
                    .padding(24.dp)
            ){
                Column {


                    Text(
                        text = result,
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentWidth(Alignment.CenterHorizontally),
                        textAlign = TextAlign.Center
                    )
                }
            }

            Text(
                text = "*If the unit in liter is lower than 1 then it will just show 0 as stated in the task",
                fontSize = 11.sp,
                fontStyle = FontStyle.Italic,
                lineHeight = 12.sp
            )


        }

        Button(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            shape = RoundedCornerShape(10.dp),
            onClick = {navController.navigate(Screens.PalindromeScreen.route)}
        ){
            Text(
                text = "Palindrome Checker"
            )
        }
    }
}


