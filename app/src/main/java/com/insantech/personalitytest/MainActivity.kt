package com.insantech.personalitytest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.insantech.personalitytest.data.MbtiItems
import com.insantech.personalitytest.logic.describe
import com.insantech.personalitytest.logic.scoreMbti
import com.insantech.personalitytest.ui.screens.HomeScreen
import com.insantech.personalitytest.ui.screens.ResultScreen
import com.insantech.personalitytest.ui.screens.TestScreen
import com.insantech.personalitytest.ui.screens.AboutScreen
import com.insantech.personalitytest.ui.theme.AppTheme

class MainActivity : ComponentActivity() {
 override fun onCreate(savedInstanceState: Bundle?) {
  super.onCreate(savedInstanceState)
  setContent {
   AppTheme {
    val nav = rememberNavController()

    NavHost(navController = nav, startDestination = "home") {
     composable("home") {
      HomeScreen(
       onStart = { nav.navigate("test") },
       onAbout = { nav.navigate("about") }
      )
     }
     composable("about") {
      AboutScreen(onBack = { nav.popBackStack() })
     }
     composable("test") {
      val responses = remember { mutableStateMapOf<Int, Int>() }
      TestScreen(
       items = MbtiItems,
       onAnswer = { id, value -> responses[id] = value },
       onSubmit = {
        val type = scoreMbti(responses, MbtiItems)
        val desc = describe(type)
        nav.navigate("result/$type/$desc")
       }
      )
     }
     composable("result/{type}/{desc}") { backStackEntry ->
      val type = backStackEntry.arguments?.getString("type").orEmpty()
      val desc = backStackEntry.arguments?.getString("desc").orEmpty()
      ResultScreen(
       type = type,
       description = desc,
       onRestart = { nav.popBackStack(route = "home", inclusive = false) }
      )
     }
    }
   }
  }
 }
}