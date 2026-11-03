package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.School
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.AzulSecundario
import com.example.ui.theme.DouradoAlerta
import com.example.ui.theme.Ods12Cor
import com.example.ui.theme.Ods2Cor
import com.example.ui.theme.TextoPrincipal
import com.example.ui.theme.TextoSecundario
import com.example.ui.theme.VerdePrimario

@Composable
fun OdsBadge(
    numero: Int,
    titulo: String,
    modifier: Modifier = Modifier
) {
    val corFundo = if (numero == 2) Color(Ods2Cor.value) else Color(Ods12Cor.value)

    Box(
        modifier = modifier
            .clip(RoundedCornerShape(20.dp))
            .background(corFundo)
            .padding(horizontal = 10.dp, vertical = 4.dp),
        contentAlignment = Alignment.Center
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = "ODS $numero",
                fontWeight = FontWeight.ExtraBold,
                fontSize = 11.sp,
                color = Color.White
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = titulo,
                fontWeight = FontWeight.Medium,
                fontSize = 11.sp,
                color = Color.White
            )
        }
    }
}

@Composable
fun BigMetricCard(
    titulo: String,
    valorDestaque: String,
    unidade: String,
    subtitulo: String,
    notaExplicativa: String,
    corDestaque: Color,
    icone: String,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .testTag("metric_card_${titulo.take(8).lowercase()}"),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        shape = RoundedCornerShape(20.dp),
        border = CardDefaults.outlinedCardBorder().copy(
            brush = androidx.compose.ui.graphics.SolidColor(Color(0xFFE2EBE5))
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier.padding(18.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = titulo.uppercase(),
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    color = corDestaque,
                    letterSpacing = 0.8.sp
                )
                Text(
                    text = icone,
                    fontSize = 20.sp
                )
            }

            Spacer(modifier = Modifier.height(6.dp))

            Row(verticalAlignment = Alignment.Bottom) {
                Text(
                    text = valorDestaque,
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Black,
                    color = TextoPrincipal
                )
                if (unidade.isNotEmpty()) {
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = unidade,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = TextoSecundario,
                        modifier = Modifier.padding(bottom = 4.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(4.dp))

            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .background(corDestaque.copy(alpha = 0.12f))
                    .padding(horizontal = 8.dp, vertical = 3.dp)
            ) {
                Text(
                    text = subtitulo,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = corDestaque
                )
            }

            if (notaExplicativa.isNotEmpty()) {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = notaExplicativa,
                    fontSize = 11.sp,
                    lineHeight = 15.sp,
                    color = TextoSecundario
                )
            }
        }
    }
}

@Composable
fun InstitutionalDisclaimer(
    texto: String,
    alerta: Boolean = false,
    modifier: Modifier = Modifier
) {
    val corBorda = if (alerta) DouradoAlerta.copy(alpha = 0.5f) else Color(0xFFC8E6C9)
    val corFundo = if (alerta) Color(0xFFFFF8E1) else Color(0xFFF1F8F4)
    val corTexto = if (alerta) Color(0xFF7F4500) else Color(0xFF1B5E20)

    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(14.dp))
            .background(corFundo)
            .border(1.dp, corBorda, RoundedCornerShape(14.dp))
            .padding(14.dp)
    ) {
        Row(
            verticalAlignment = Alignment.Top
        ) {
            Icon(
                imageVector = Icons.Default.Info,
                contentDescription = "Nota Informativa",
                tint = if (alerta) DouradoAlerta else VerdePrimario,
                modifier = Modifier
                    .size(18.dp)
                    .padding(top = 1.dp)
            )
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                text = texto,
                fontSize = 12.sp,
                lineHeight = 17.sp,
                fontWeight = FontWeight.Medium,
                color = corTexto
            )
        }
    }
}

@Composable
fun EquipeModalDialog(
    equipe: List<String>,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(36.dp)
                        .clip(CircleShape)
                        .background(AzulSecundario.copy(alpha = 0.12f)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.School,
                        contentDescription = "Universidade",
                        tint = AzulSecundario,
                        modifier = Modifier.size(20.dp)
                    )
                }
                Spacer(modifier = Modifier.width(10.dp))
                Column {
                    Text(
                        text = "Equipe do Projeto TEI",
                        fontSize = 17.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "Ciências Contábeis · PUC Minas",
                        fontSize = 12.sp,
                        color = TextoSecundario
                    )
                }
            }
        },
        text = {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Text(
                    text = "Projeto:",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextoPrincipal
                )
                Text(
                    text = "“Painel Cidadão: Governança Pública e Eficiência Nutricional no Combate ao Desperdício Escolar à luz dos ODS 2 e 12”",
                    fontSize = 12.sp,
                    lineHeight = 17.sp,
                    color = VerdePrimario,
                    fontWeight = FontWeight.SemiBold
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = "Acadêmicos Pesquisadores:",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextoPrincipal
                )

                Card(
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFF8FAF9)),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp),
                        verticalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        equipe.forEach { membro ->
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text(text = "• ", fontWeight = FontWeight.Bold, color = AzulSecundario)
                                Text(
                                    text = membro,
                                    fontSize = 12.sp,
                                    color = TextoPrincipal,
                                    fontWeight = FontWeight.Medium
                                )
                            }
                        }
                    }
                }

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(10.dp))
                        .background(Color(0xFFE8F5E9))
                        .padding(10.dp)
                ) {
                    Text(
                        text = "🛡️ Premissa Atrito Zero: Coleta e pesagens realizadas com balança própria da equipe, sem ônus de trabalho para a cantina ou professores.",
                        fontSize = 11.sp,
                        lineHeight = 15.sp,
                        color = Color(0xFF1B5E20)
                    )
                }
            }
        },
        confirmButton = {
            Button(
                onClick = onDismiss,
                colors = ButtonDefaults.buttonColors(containerColor = VerdePrimario),
                shape = RoundedCornerShape(10.dp)
            ) {
                Text("Fechar", fontWeight = FontWeight.Bold)
            }
        }
    )
}
