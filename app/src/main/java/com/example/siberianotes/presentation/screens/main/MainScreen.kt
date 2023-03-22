package com.example.siberianotes.presentation.screens.main

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.siberianotes.domain.model.NoteModel
import com.example.siberianotes.presentation.item.ErrorItem
import com.example.siberianotes.presentation.item.LoadingItem
import com.example.siberianotes.presentation.item.NoteItem

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MainScreen(navController: NavHostController) {

   val viewModel = hiltViewModel<MainViewModel>()

   val state by viewModel.state.collectAsState()

   when {
      state.isLoading -> {
         Log.d("check", "Loading.......")
         LoadingItem()
      }
      state.data.isNotEmpty() ->{
         Log.d("check", "Data size: ${state.data.size}")
         MainScreenContent(state.data)
      }

      state.error != null ->{
         Log.d("check", "Error massage: ${state.error}")
         ErrorItem(state.error!!){
            viewModel.sendEvent(MainScreenEvent.LoadingData)
         }
      }
   }

}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MainScreenContent(data: List<NoteModel>) {
   Column(
      modifier = Modifier
         .fillMaxSize(),
   horizontalAlignment = Alignment.CenterHorizontally,
   verticalArrangement = Arrangement.Center
   ) {
      LazyColumn(
         modifier = Modifier
            .fillMaxWidth()){
         item {
            Text(text = "My Super Notes App",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(horizontal = 24.dp)
               .padding(top = 16.dp)
            )
         }
         items(data){note ->
            NoteItem(
               noteModel = note,
               modifier = Modifier.
               padding(
                  vertical = 8.dp,
                  horizontal = 16.dp))
         }
      }
   }
}



