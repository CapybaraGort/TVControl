package com.example.tvcontrol.ui.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.tvcontrol.R


@Composable
public fun CustomButton(text: String) {
    Button(
        onClick = {},
        shape = RoundedCornerShape(16.dp),
        contentPadding = PaddingValues(16.dp),
        modifier = Modifier
            .padding(horizontal = 48.dp)
            .width(420.dp),
        colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.tertiary)
    )
    {
        Text(
            text = text,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodyLarge,
            fontFamily = MaterialTheme.typography.displayLarge.fontFamily,
            fontWeight = FontWeight.Medium,
            fontSize = MaterialTheme.typography.titleMedium.fontSize,
            color = MaterialTheme.colorScheme.background
        )

    }
}

@Composable
fun NavAllIcons(title: String) {
    Row (
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Button(
            onClick = {},
            contentPadding = PaddingValues(2.dp),
            modifier = Modifier
                .width(40.dp)
                .height(40.dp),
            colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.background),

            ) {
            val imagePainter = rememberVectorPainter(image = ImageVector.vectorResource(id = R.drawable.ic_arrow_left))
            Image(
                painter = imagePainter,
                contentDescription = "Back",
                modifier = Modifier.size(40.dp, 40.dp)
            )
        }
        Text( modifier = Modifier.padding(top = 5.dp),
            text = title,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleLarge,
            fontFamily = MaterialTheme.typography.titleLarge.fontFamily,
            fontWeight = FontWeight.SemiBold,
            fontSize = MaterialTheme.typography.titleLarge.fontSize
        )
        Button(onClick = {},
            contentPadding = PaddingValues(2.dp),
            modifier = Modifier
                .width(40.dp)
                .height(40.dp),
            colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.background),
        ) {
            val imagePainter = rememberVectorPainter(image = ImageVector.vectorResource(id = R.drawable.ic_support))
            Image(
                painter = imagePainter,
                contentDescription = "Back",
                modifier = Modifier.size(40.dp, 40.dp)
            )
        }
    }
}

@Composable
fun NavSupportAndTitle(title: String) {
    Row (
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text( modifier = Modifier.padding(top = 5.dp),
            text = title,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleLarge,
            fontFamily = MaterialTheme.typography.titleLarge.fontFamily,
            fontWeight = FontWeight.SemiBold,
            fontSize = MaterialTheme.typography.titleLarge.fontSize
        )
        Button(onClick = {},
            contentPadding = PaddingValues(2.dp),
            modifier = Modifier
                .width(40.dp)
                .height(40.dp),
            colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.background),
        ) {
            val imagePainter = rememberVectorPainter(image = ImageVector.vectorResource(id = R.drawable.ic_support))
            Image(
                painter = imagePainter,
                contentDescription = "Back",
                modifier = Modifier.size(40.dp, 40.dp)
            )
        }
    }
}

@Composable
fun NavBackAndTitle(title: String) {
    Row (
        modifier = Modifier.fillMaxWidth()
    ) {
        Button(
            onClick = {},
            contentPadding = PaddingValues(2.dp),
            modifier = Modifier
                .width(40.dp)
                .height(40.dp),
            colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.background),

            ) {
            val imagePainter = rememberVectorPainter(image = ImageVector.vectorResource(id = R.drawable.ic_arrow_left))
            Image(
                painter = imagePainter,
                contentDescription = "Back",
                modifier = Modifier.size(40.dp, 40.dp)
            )
        }
        Text( modifier = Modifier.padding(start = 48.dp, top = 5.dp),
            text = title,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleLarge,
            fontFamily = MaterialTheme.typography.titleLarge.fontFamily,
            fontWeight = FontWeight.SemiBold,
            fontSize = MaterialTheme.typography.titleLarge.fontSize
        )

    }
}

@Composable
fun NavTitleOnly(title: String) {
    Row (
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Start
    ) {
        Text( modifier = Modifier.padding(top = 5.dp),
            text = title,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleLarge,
            fontFamily = MaterialTheme.typography.titleLarge.fontFamily,
            fontWeight = FontWeight.SemiBold,
            fontSize = MaterialTheme.typography.titleLarge.fontSize
        )
    }
}

@Composable
fun NavBack() {
    Row (
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Start
    ) {
        Button(
            onClick = {},
            contentPadding = PaddingValues(2.dp),
            modifier = Modifier
                .width(40.dp)
                .height(40.dp),
            colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.background),

            ) {
            val imagePainter = rememberVectorPainter(image = ImageVector.vectorResource(id = R.drawable.ic_arrow_left))
            Image(
                painter = imagePainter,
                contentDescription = "Back",
                modifier = Modifier.size(40.dp, 40.dp)
            )
        }
    }
}

@Composable
fun NumberedListItem(index: Int, text: String) {
    Row(modifier = Modifier.padding(bottom = 8.dp)) {
        Text(
            text = "${index + 1}. ",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.scrim
        )
        Text(
            text = text,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.scrim
        )
    }
}

@Composable
fun Recommendations() {
    Box(
        modifier = Modifier
            .background(
                color = MaterialTheme.colorScheme.surfaceVariant,
                shape = RoundedCornerShape(16.dp)
            )

    ) {
        Column(
            modifier = Modifier.padding(vertical = 32.dp, horizontal = 32.dp)
        ) {
            Text(
                text = "Убедитесь, что:",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold,
                fontFamily = MaterialTheme.typography.displayLarge.fontFamily,
                color = MaterialTheme.colorScheme.scrim,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            NumberedListItem(0, "Ваш смартфон и ТВ подключены к одной сети Wi-Fi")
            NumberedListItem(1, "Ваш ТВ включен")
            NumberedListItem(2, "На вашем смартфоне выключен VPN")
        }
    }
}