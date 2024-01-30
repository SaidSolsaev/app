package no.uio.ifi.in2000.smsolsae.oblig1.ui.palindrome

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import no.uio.ifi.in2000.smsolsae.oblig1.isPalindrome


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun PalindromeChecker(navController: NavController){
    var erP by remember { mutableStateOf("") }

    var word by remember { mutableStateOf("") }

    fun checkPalindrome(ord: String){
        if (ord.isNotBlank()){
            erP = if (isPalindrome(ord)){
                "$ord is a palindrome"
            } else{
                "$ord is not a palindrome"
            }
        }else{
            erP = "Field empty!"
        }
    }

    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(20.dp)
    ) {
        Column(
            modifier = Modifier.weight(10f),
            verticalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier
                    .background(Color.Red.copy(alpha = 0.2f))
                    .padding(18.dp)
                    .fillMaxWidth(),
            ){
                Text(
                    text = "Write a word",
                    modifier = Modifier.align(Alignment.Center),
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp
                )
            }

            val keyboardController = LocalSoftwareKeyboardController.current
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(IntrinsicSize.Min),
                verticalAlignment = Alignment.Bottom
            ){
                OutlinedTextField(
                    singleLine = true,
                    value = word,
                    onValueChange = {newText: String -> word = newText},
                    label = {Text("Write a word")},
                    modifier = Modifier
                        .weight(2f)
                        .fillMaxHeight(),

                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                    keyboardActions = KeyboardActions(
                        onDone = {
                            keyboardController?.hide()
                            checkPalindrome(word)
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
                    onClick = {checkPalindrome(word)}
                ){
                    Text(text = "Check!")
                }
            }
            Spacer(modifier = Modifier.height(40.dp))

            Box(
                modifier = Modifier
                    .background(Color.Red.copy(alpha = 0.3f), shape = RoundedCornerShape(10.dp))
                    .padding(40.dp)
                    .fillMaxWidth(),

                ){
                Text(
                    text = erP,
                    modifier = Modifier.align(Alignment.Center),
                    textAlign = TextAlign.Center,
                    fontSize = 22.sp
                )
            }
        }
        Button(
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            onClick = {println("clicked")}
        ){
            Text(text = "Unit Converter")
        }
    }

}