package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Calculate
import androidx.compose.material.icons.filled.Computer
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Videocam
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.components.InstitutionalDisclaimer
import com.example.ui.theme.AzulSecundario
import com.example.ui.theme.DouradoAlerta
import com.example.ui.theme.TextoPrincipal
import com.example.ui.theme.TextoSecundario
import com.example.ui.theme.VerdePrimario

@Composable
fun CalculadoraScreen(
    sobraKgText: String,
    onSobraKgChange: (String) -> Unit,
    refeicoesText: String,
    onRefeicoesChange: (String) -> Unit,
    diasLetivosText: String,
    onDiasLetivosChange: (String) -> Unit,
    onReset: () -> Unit,
    modifier: Modifier = Modifier
) {
    val sobraKg = sobraKgText.toDoubleOrNull() ?: 0.0
    val refeicoes = refeicoesText.toIntOrNull() ?: 0
    val diasLetivos = diasLetivosText.toIntOrNull() ?: 200

    val pratosDesperdicados = sobraKg / 0.250
    val prejuizoDiario = pratosDesperdicados * 4.65
    val energiaKcal = pratosDesperdicados * 350.0
    val sobraLiquidaPorAluno = if (refeicoes > 0) sobraKg / refeicoes else 0.0
    val projecaoAnual = prejuizoDiario * diasLetivos
    val computadoresEquivalentes = (projecaoAnual / 2500.0).toInt().coerceAtLeast(0)
    val projetoresEquivalentes = (projecaoAnual / 1000.0).toInt().coerceAtLeast(0)

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .testTag("tela_calculadora"),
        contentPadding = PaddingValues(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 96.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Cabeçalho da Ferramenta
        item {
            Column {
                Text(
                    text = "Simule você mesma",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextoPrincipal
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Ajuste os valores para visualizar o impacto orçamentário e nutricional em tempo real",
                    fontSize = 13.sp,
                    color = TextoSecundario
                )
            }
        }

        // Card dos Campos de Entrada
        item {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("card_inputs_calculadora"),
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
                            text = "PARÂMETROS DE ENTRADA",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            color = VerdePrimario,
                            letterSpacing = 1.sp
                        )

                        OutlinedButton(
                            onClick = onReset,
                            contentPadding = PaddingValues(horizontal = 10.dp, vertical = 2.dp),
                            shape = RoundedCornerShape(8.dp),
                            modifier = Modifier.testTag("btn_reset_calculadora")
                        ) {
                            Icon(imageVector = Icons.Default.Refresh, contentDescription = null, modifier = Modifier.size(14.dp))
                            Spacer(modifier = Modifier.width(4.dp))
                            Text("Restaurar Padrão", fontSize = 11.sp)
                        }
                    }

                    Spacer(modifier = Modifier.height(14.dp))

                    // Campo 1: Sobra em kg
                    OutlinedTextField(
                        value = sobraKgText,
                        onValueChange = onSobraKgChange,
                        label = { Text("Sobra diária da merenda (em kg)") },
                        supportingText = { Text("Padrão: 12,32 kg (média real aferida na escola)") },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                        singleLine = true,
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("input_sobra_kg")
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    // Campo 2: Refeições servidas
                    OutlinedTextField(
                        value = refeicoesText,
                        onValueChange = onRefeicoesChange,
                        label = { Text("Refeições servidas no dia (opcional)") },
                        supportingText = { Text("Utilizado para calcular o índice de sobra por aluno") },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        singleLine = true,
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("input_refeicoes")
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    // Campo 3: Dias letivos
                    OutlinedTextField(
                        value = diasLetivosText,
                        onValueChange = onDiasLetivosChange,
                        label = { Text("Dias letivos no ano escolar") },
                        supportingText = { Text("Padrão LDB: 200 dias de aula no ano") },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        singleLine = true,
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("input_dias_letivos")
                    )
                }
            }
        }

        // Título Resultados Diários
        item {
            Text(
                text = "Resultados Diários Estimados",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = TextoPrincipal
            )
        }

        // Grid 4 Resultados Diários
        item {
            Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    ResultadoMiniCard(
                        titulo = "Pratos Desperdiçados / dia",
                        valor = "${String.format("%.2f", pratosDesperdicados)}",
                        formula = "kg ÷ 0,250 kg",
                        cor = AzulSecundario,
                        modifier = Modifier.weight(1f)
                    )
                    ResultadoMiniCard(
                        titulo = "Prejuízo Orçamentário / dia",
                        valor = "R$ ${String.format("%.2f", prejuizoDiario)}",
                        formula = "pratos × R$ 4,65",
                        cor = Color(0xFFC62828),
                        modifier = Modifier.weight(1f)
                    )
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    ResultadoMiniCard(
                        titulo = "Energia Não Aproveitada",
                        valor = "${String.format("%,.0f", energiaKcal)} kcal",
                        formula = "pratos × 350 kcal",
                        cor = DouradoAlerta,
                        modifier = Modifier.weight(1f)
                    )
                    ResultadoMiniCard(
                        titulo = "Sobra Líquida / Aluno",
                        valor = "${String.format("%.3f", sobraLiquidaPorAluno)} kg",
                        formula = if (refeicoes > 0) "kg ÷ $refeicoes refeições" else "Informe as refeições",
                        cor = VerdePrimario,
                        modifier = Modifier.weight(1f)
                    )
                }
            }
        }

        // Destaque Projeção Anual para a Escola
        item {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("card_projecao_anual"),
                colors = CardDefaults.cardColors(containerColor = Color(0xFF0F3D1C)),
                shape = RoundedCornerShape(20.dp)
            ) {
                Column(modifier = Modifier.padding(20.dp)) {
                    Text(
                        text = "PROJEÇÃO ANUAL PARA A ESCOLA",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFFA7F3D0),
                        letterSpacing = 1.sp
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = "R$ ${String.format("%,.2f", projecaoAnual)}",
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Black,
                        color = Color.White
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = "Cenário-base: R$ ${String.format("%.2f", prejuizoDiario)}/dia × $diasLetivos dias letivos = R$ 45.830,40 no padrão",
                        fontSize = 12.sp,
                        color = Color(0xFFE2EBE5)
                    )
                }
            }
        }

        // Seção: O que o desperdício deixa de comprar (Equivalência visual)
        item {
            Column {
                Text(
                    text = "O que o desperdício deixa de comprar",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextoPrincipal
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Traduzindo a projeção anual da escola em bens duráveis para os alunos",
                    fontSize = 12.sp,
                    color = TextoSecundario
                )
            }
        }

        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                EquipamentoEquivalenteCard(
                    icone = "💻",
                    quantidade = "~$computadoresEquivalentes Computadores",
                    precoRef = "R$ 2.500 cada",
                    descricao = "Para renovar o laboratório de informática dos alunos.",
                    cor = AzulSecundario,
                    modifier = Modifier.weight(1f)
                )

                EquipamentoEquivalenteCard(
                    icone = "📽️",
                    quantidade = "~$projetoresEquivalentes Projetores",
                    precoRef = "R$ 1.000 cada",
                    descricao = "Recurso multimídia para quase todas as salas de aula.",
                    cor = VerdePrimario,
                    modifier = Modifier.weight(1f)
                )
            }
        }

        // Ressalva Obrigatória
        item {
            InstitutionalDisclaimer(
                texto = "“Estimativa baseada em preços de referência, não demonstra disponibilidade orçamentária.” Os recursos da merenda têm destinação legal estrita via PNAE/FNDE.",
                alerta = true
            )
        }
    }
}

@Composable
private fun ResultadoMiniCard(
    titulo: String,
    valor: String,
    formula: String,
    cor: Color,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        shape = RoundedCornerShape(16.dp),
        border = CardDefaults.outlinedCardBorder().copy(
            brush = androidx.compose.ui.graphics.SolidColor(Color(0xFFE2EBE5))
        )
    ) {
        Column(modifier = Modifier.padding(14.dp)) {
            Text(
                text = titulo,
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                color = TextoSecundario
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = valor,
                fontSize = 18.sp,
                fontWeight = FontWeight.Black,
                color = cor
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = formula,
                fontSize = 10.sp,
                color = TextoSecundario.copy(alpha = 0.8f)
            )
        }
    }
}

@Composable
private fun EquipamentoEquivalenteCard(
    icone: String,
    quantidade: String,
    precoRef: String,
    descricao: String,
    cor: Color,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        shape = RoundedCornerShape(18.dp),
        border = CardDefaults.outlinedCardBorder().copy(
            brush = androidx.compose.ui.graphics.SolidColor(Color(0xFFE2EBE5))
        )
    ) {
        Column(modifier = Modifier.padding(14.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = icone, fontSize = 24.sp)
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(6.dp))
                        .background(cor.copy(alpha = 0.12f))
                        .padding(horizontal = 6.dp, vertical = 2.dp)
                ) {
                    Text(
                        text = precoRef,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        color = cor
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = quantidade,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = TextoPrincipal
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = descricao,
                fontSize = 11.sp,
                lineHeight = 15.sp,
                color = TextoSecundario
            )
        }
    }
}
