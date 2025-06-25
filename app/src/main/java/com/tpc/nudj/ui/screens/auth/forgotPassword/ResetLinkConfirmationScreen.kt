package com.tpc.nudj.ui.screens.auth.forgotPassword

import android.content.Intent
import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.tpc.nudj.ui.theme.ClashDisplay
import com.tpc.nudj.ui.theme.NudjTheme
import com.tpc.nudj.ui.theme.Purple
import com.tpc.nudj.R
import com.tpc.nudj.ui.components.PrimaryButton
import com.tpc.nudj.ui.components.TertiaryButton
import com.tpc.nudj.ui.theme.LocalAppColors


@Composable
fun ResetLinkConfirmationScreen(
    viewModel: ForgetPasswordScreenModel = hiltViewModel()
) {

    ResetLinkConfirmationScreenLayout(
        onResendEmail = viewModel::resendEmail
    )
}

@Composable
fun ResetLinkConfirmationScreenLayout(
    onResendEmail: () -> Unit
) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    Scaffold {paddingValues->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(LocalAppColors.current.background),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Sent successfully!",
                color = if (isSystemInDarkTheme()) Color.White else Purple,
                style = MaterialTheme.typography.titleLarge.copy(
                    fontFamily = ClashDisplay
                ),
            )
            Spacer(modifier = Modifier.height(35.dp))
            Icon(
                painter = painterResource(R.drawable.hot_air_balloon__travel_transportation_hot_air_balloon),
                contentDescription = "Sent Successfully",
                tint = LocalAppColors.current.editText,
                modifier = Modifier
                    .aspectRatio(1.4f)
            )
            Spacer(modifier = Modifier.padding(40.dp))
            PrimaryButton(
                text = "Check Inbox", onClick = {
                    val intent = Intent(Intent.ACTION_MAIN).apply {
                        addCategory(Intent.CATEGORY_APP_EMAIL)
                    }
                    context.startActivity(intent)
                }, modifier = Modifier.align(
                    Alignment.CenterHorizontally
                ), isDarkModeEnabled = false
            )
            Spacer(modifier = Modifier.height(16.dp))
            TertiaryButton(
                text = "Resend Email?",
                onClick = onResendEmail,
                isDarkModeEnabled = isSystemInDarkTheme()
            )
        }
    }

}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun ResetLinkConfirmationScreenPreview() {
    NudjTheme {
        ResetLinkConfirmationScreenLayout(onResendEmail = {})
    }
}