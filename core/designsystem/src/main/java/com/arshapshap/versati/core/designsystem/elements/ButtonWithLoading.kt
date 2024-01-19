package com.arshapshap.versati.core.designsystem.elements

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.arshapshap.versati.core.designsystem.theme.ButtonHeight

@Composable
fun ButtonWithLoading(
    modifier: Modifier,
    onClick: () -> Unit,
    text: String,
    loading: Boolean
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .height(ButtonHeight),
        enabled = !loading
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold
        )
        if (loading) {
            CircularProgressIndicator(
                modifier = Modifier
                    .padding(start = 16.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ButtonWithLoadingPreview() {
    ButtonWithLoading(
        modifier = Modifier.fillMaxWidth(),
        onClick = { /*TODO*/ },
        text = "Button",
        loading = true
    )
}