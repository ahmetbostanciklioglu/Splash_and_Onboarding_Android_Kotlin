package com.onboarding_android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.onboarding_android.ui.home.HomeScreen
import com.onboarding_android.ui.onboard.OnboardingUtils
import com.onboarding_android.ui.onboard.OnboardingScreen
import com.onboarding_android.ui.theme.OnboardingAndroidKotlinTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    private val onboardingUtils by lazy { OnboardingUtils(this)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        installSplashScreen()

        setContent {
            OnboardingAndroidKotlinTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    if (onboardingUtils.isOnboardingCompleted()) {
                        HomeScreen()
                    } else {
                        OnboardingScreen()
                    }
                }
            }
        }
    }


    @Composable
    fun OnboardingScreen() {
        val scope = rememberCoroutineScope()
        OnboardingScreen {
            onboardingUtils.setOnboardingCompleted()
            scope.launch {
                setContent {
                    HomeScreen()
                }
            }

        }

    }
}
