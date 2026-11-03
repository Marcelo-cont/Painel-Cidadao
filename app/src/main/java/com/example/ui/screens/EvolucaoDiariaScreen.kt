package com.example.ui.screens

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.TrendingDown
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.DiaAuditoria
import com.example.ui.components.InstitutionalDisclaimer
import com.example.ui.theme.AzulSecundario
import com.example.ui.theme.DouradoAlerta
import com.example.ui.theme.TextoPrincipal
import com.example.ui.theme.TextoSecundario
import com.example.ui.theme.VerdePrimario

@Composable
fun EvolucaoDiariaScreen(
    dadosDias: List<DiaAuditoria>,
    modifier: Modifier = Modifier
) {
    var diaSelecionado by remember { mutableStateOf<DiaAuditoria?>(dadosDias.find { it.diaNumero == 4 }) }

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .testTag("tela_evolucao_diaria"),
        contentPadding = PaddingValues(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 96.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Título e Cabeçalho da Seção
        item {
            Column {
                Text(
                    text = "Evolução Diária da Sobra Limpa",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextoPrincipal
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Acompanhamento dia a dia da pesagem realizada na escola",
                    fontSize = 13.sp,
                    color = TextoSecundario
                )
            }
        }

        // Card do Gráfico de Barras Interativo
        item {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("card_grafico_barras"),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                shape = RoundedCornerShape(20.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Column(modifier = Modifier.padding(18.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "COMPARAÇÃO DIÁRIA (KG)",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            color = VerdePrimario,
                            letterSpacing = 1.sp
                        )

                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(8.dp))
                                .background(Color(0xFFE8F5E9))
                                .padding(horizontal = 8.dp, vertical = 3.dp)
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    imageVector = Icons.Default.TrendingDown,
                                    contentDescription = null,
                                    tint = VerdePrimario,
                                    modifier = Modifier.size(14.dp)
                                )
                                Spacer(modifier = Modifier.width(4.dp))
                                Text(
                                    text = "Queda de 50,8% (Dia 1 → Dia 4)",
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = VerdePrimario
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    // Barras Interativas
                    val maxKg = dadosDias.maxOfOrNull { it.sobraKg } ?: 16.9
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(180.dp),
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        verticalAlignment = Alignment.Bottom
                    ) {
                        dadosDias.forEach { dia ->
                            val isSelected = diaSelecionado?.diaNumero == dia.diaNumero
                            val alturaProporcional = (dia.sobraKg / maxKg).toFloat()

                            ColunaBarraDia(
                                dia = dia,
                                alturaProporcao = alturaProporcional,
                                isSelected = isSelected,
                                onClick = { diaSelecionado = dia },
                                modifier = Modifier.weight(1f)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // Detalhe do dia selecionado
                    diaSelecionado?.let { dia ->
                        Card(
                            colors = CardDefaults.cardColors(containerColor = Color(0xFFF3F7F4)),
                            shape = RoundedCornerShape(14.dp),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(14.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Column {
                                    Text(
                                        text = "Dia ${dia.diaNumero} · ${dia.dataFormatada}",
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 14.sp,
                                        color = TextoPrincipal
                                    )
                                    Text(
                                        text = "${dia.observacao}",
                                        fontSize = 11.sp,
                                        color = TextoSecundario
                                    )
                                }
                                Column(horizontalAlignment = Alignment.End) {
                                    Text(
                                        text = "${String.format("%.3f", dia.sobraKg)} kg",
                                        fontWeight = FontWeight.ExtraBold,
                                        fontSize = 16.sp,
                                        color = VerdePrimario
                                    )
                                    Text(
                                        text = "R$ ${String.format("%.2f", dia.perdaFinanceiraReais)}",
                                        fontSize = 12.sp,
                                        fontWeight = FontWeight.SemiBold,
                                        color = Color(0xFFC62828)
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }

        // Texto Obrigatório de Análise e Cautela Metodológica
        item {
            InstitutionalDisclaimer(
                texto = "“Do 1º ao 4º dia a sobra caiu cerca de 51%; no 5º dia houve aumento. A amostra é curta e não prova tendência permanente.”",
                alerta = true
            )
        }

        // Tabela/Lista dos 5 dias detalhados
        item {
            Text(
                text = "Tabela Detalhada por Dia de Auditoria",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = TextoPrincipal
            )
        }

        items(dadosDias, key = { it.diaNumero }) { dia ->
            DiaItemCard(
                dia = dia,
                isMelhorDia = dia.diaNumero == 4,
                isSelected = diaSelecionado?.diaNumero == dia.diaNumero,
                onClick = { diaSelecionado = dia }
            )
        }
    }
}

@Composable
private fun ColunaBarraDia(
    dia: DiaAuditoria,
    alturaProporcao: Float,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val animatedHeight = remember { Animatable(0f) }

    LaunchedEffect(alturaProporcao) {
        animatedHeight.animateTo(
            targetValue = alturaProporcao,
            animationSpec = tween(durationMillis = 750, easing = FastOutSlowInEasing)
        )
    }

    val corBarra = when {
        dia.diaNumero == 4 -> Color(0xFF2E7D32) // Verde destaque no dia de menor sobra
        dia.diaNumero == 1 -> Color(0xFFC62828) // Vermelho suave no dia de maior sobra
        isSelected -> AzulSecundario
        else -> Color(0xFF43A047)
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .clickable(onClick = onClick)
            .padding(horizontal = 4.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom
    ) {
        Text(
            text = "${String.format("%.1f", dia.sobraKg)}",
            fontSize = 11.sp,
            fontWeight = FontWeight.Bold,
            color = if (isSelected) VerdePrimario else TextoPrincipal
        )

        Spacer(modifier = Modifier.height(4.dp))

        Box(
            modifier = Modifier
                .width(if (isSelected) 34.dp else 28.dp)
                .height((130 * animatedHeight.value).dp.coerceAtLeast(12.dp))
                .clip(RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp))
                .background(corBarra)
        )

        Spacer(modifier = Modifier.height(6.dp))

        Text(
            text = "D${dia.diaNumero}",
            fontSize = 11.sp,
            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
            color = if (isSelected) VerdePrimario else TextoSecundario
        )
    }
}

@Composable
private fun DiaItemCard(
    dia: DiaAuditoria,
    isMelhorDia: Boolean,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .testTag("item_dia_${dia.diaNumero}"),
        colors = CardDefaults.cardColors(
            containerColor = if (isSelected) Color(0xFFE8F5E9) else MaterialTheme.colorScheme.surface
        ),
        shape = RoundedCornerShape(16.dp),
        border = CardDefaults.outlinedCardBorder().copy(
            brush = androidx.compose.ui.graphics.SolidColor(
                if (isSelected) VerdePrimario else Color(0xFFE0E0E0)
            )
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(36.dp)
                        .clip(CircleShape)
                        .background(if (isMelhorDia) Color(0xFFE8F5E9) else Color(0xFFF1F5F2)),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = if (isMelhorDia) "⭐" else "${dia.diaNumero}",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = if (isMelhorDia) VerdePrimario else TextoPrincipal
                    )
                }
                Spacer(modifier = Modifier.width(10.dp))
                Column {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = "Dia ${dia.diaNumero} (${dia.dataFormatada})",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            color = TextoPrincipal
                        )
                        if (isMelhorDia) {
                            Spacer(modifier = Modifier.width(6.dp))
                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(6.dp))
                                    .background(Color(0xFF2E7D32))
                                    .padding(horizontal = 6.dp, vertical = 1.dp)
                            ) {
                                Text(
                                    text = "Menor Sobra",
                                    fontSize = 9.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.White
                                )
                            }
                        }
                    }
                    Text(
                        text = "~${String.format("%.1f", dia.pratosEquivalentes)} pratos eq. · ~${dia.energiaKcal} kcal",
                        fontSize = 11.sp,
                        color = TextoSecundario
                    )
                }
            }

            Column(horizontalAlignment = Alignment.End) {
                Text(
                    text = "${String.format("%.3f", dia.sobraKg)} kg",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = TextoPrincipal
                )
                Text(
                    text = "R$ ${String.format("%.2f", dia.perdaFinanceiraReais)}",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0xFFC62828)
                )
            }
        }
    }
}
