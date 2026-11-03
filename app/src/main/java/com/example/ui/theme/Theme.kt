package com.example.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val PainelCidadaoColorScheme = lightColorScheme(
    primary = VerdePrimario,
    onPrimary = SuperficieClaro,
    primaryContainer = VerdeContainer,
    onPrimaryContainer = OnVerdeContainer,
    secondary = AzulSecundario,
    onSecondary = SuperficieClaro,
    secondaryContainer = AzulContainer,
    onSecondaryContainer = OnAzulContainer,
    tertiary = DouradoAlerta,
    background = FundoClaro,
    surface = SuperficieClaro,
    surfaceVariant = SuperficieCard,
    onBackground = TextoPrincipal,
    onSurface = TextoPrincipal,
    onSurfaceVariant = TextoSecundario,
    outline = BordaCard
)

@Composable
fun PainelCidadaoTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    // For school administration dashboard, a clean, welcoming high-contrast light theme is standard
    MaterialTheme(
        colorScheme = PainelCidadaoColorScheme,
        typography = Typography,
        content = content
    )
}
