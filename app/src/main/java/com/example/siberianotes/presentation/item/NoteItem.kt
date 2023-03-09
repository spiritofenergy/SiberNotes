package com.example.siberianotes.presentation.item

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.siberianotes.domain.model.NoteModel
import com.example.siberianotes.presentation.theme.SiberNotesTheme
import java.time.LocalDate
import java.util.Random

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NoteItem(
    modifier: Modifier,
    note: NoteModel
) {
    val color = Color(
        Random().nextInt(256),
        Random().nextInt(256),
        Random().nextInt(256),
           // alpha = 30
     )
    Box(modifier = modifier){
        Box(modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .background(color)
            .padding(vertical = 16.dp, horizontal = 24.dp)
        ){
            Column() {
                Text(text = note.author, fontSize = 12.sp, fontWeight = FontWeight.Light)
                Text(text = "${note.date.dayOfMonth} ${note.date.month}", fontSize = 12.sp, fontWeight = FontWeight.Light)
                
            }
        }
    }

}
@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun NoteItemPreview(){
    SiberNotesTheme() {
        val note = NoteModel(
            id = 1,
        title = "Note 1",
        subtitle = "Subtitle for Note 1",
        date = LocalDate.now(),
        author = "Author"
        )
        NoteItem(
            note = note,
            modifier = Modifier
            .padding(
                vertical = 8.dp,
                horizontal = 16.dp))
    }
}