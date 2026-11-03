package com.example.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Calculate
import androidx.compose.material.icons.filled.Group
import androidx.compose.material.icons.filled.Timeline
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.ui.components.BigMetricCard
import com.example.ui.components.InstitutionalDisclaimer
import com.example.ui.components.OdsBadge
import com.example.ui.theme.AzulSecundario
import com.example.ui.theme.DouradoAlerta
import com.example.ui.theme.TextoPrincipal
import com.example.ui.theme.TextoSecundario
import com.example.ui.theme.VerdePrimario

@Composable
fun InicioDiagnosticoScreen(
    onNavegarParaSimulador: () -> Unit,
    onNavegarParaEvolucao: () -> Unit,
    onAbrirEquipe: () -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .testTag("tela_inicio_diagnostico"),
        contentPadding = PaddingValues(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 96.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Hero Card com Banner Institucional da Escola
        item {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("hero_banner_card"),
                shape = RoundedCornerShape(24.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 3.dp)
            ) {
                Column {
                    // Imagem Ilustrativa Acolhedora do Refeitório
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(180.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.banner_escola_morais),
                            contentDescription = "Refeitório da Escola Estadual Professor Morais",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxSize()
                        )

                        // Gradiente de transição suave
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(
                                    Brush.verticalGradient(
                                        colors = listOf(
                                            Color.Transparent,
                                            Color(0x880E3821),
                                            Color(0xDD0E3821)
                                        )
                                    )
                                )
                        )

                        Column(
                            modifier = Modifier
                                .align(Alignment.BottomStart)
                                .padding(14.dp)
                        ) {
                            Text(
                                text = "ESCOLA ESTADUAL PROFESSOR MORAIS",
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFFA7F3D0),
                                letterSpacing = 1.sp
                            )
                            Text(
                                text = "Painel Cidadão: Merenda Escolar",
                                fontSize = 20.sp,
                                fontWeight = FontWeight.ExtraBold,
                                color = Color.White
                            )
                        }
                    }

                    // Conteúdo Textual e Mensagem Central
                    Column(modifier = Modifier.padding(18.dp)) {
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            OdsBadge(numero = 2, titulo = "Fome Zero")
                            OdsBadge(numero = 12, titulo = "Consumo Resp.")
                        }

                        Spacer(modifier = Modifier.height(14.dp))

                        // Pergunta-Chave Obrigatória
                        Text(
                            text = "“Quanto da merenda deixa de ser aproveitada e o que isso representa para a escola?”",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = VerdePrimario,
                            lineHeight = 24.sp
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = "Trabalho Extensionista Interdisciplinar (TEI) de Ciências Contábeis da PUC Minas: “Governança Pública e Eficiência Nutricional no Combate ao Desperdício Escolar à luz dos ODS 2 e 12”.",
                            fontSize = 13.sp,
                            color = TextoSecundario,
                            lineHeight = 18.sp
                        )

                        Spacer(modifier = Modifier.height(14.dp))

                        // Destaque Premissa Atrito Zero
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(12.dp))
                                .background(Color(0xFFE8F5E9))
                                .padding(12.dp)
                        ) {
                            Row(verticalAlignment = Alignment.Top) {
                                Text(text = "🛡️", fontSize = 16.sp)
                                Spacer(modifier = Modifier.width(8.dp))
                                Column {
                                    Text(
                                        text = "Premissa de Atrito Zero",
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 12.sp,
                                        color = Color(0xFF1B5E20)
                                    )
                                    Text(
                                        text = "A coleta foi realizada integralmente pela equipe universitária da PUC Minas, utilizando balanças próprias, sem demandar trabalho adicional para a direção, professores ou cantineiras da escola.",
                                        fontSize = 11.sp,
                                        lineHeight = 15.sp,
                                        color = Color(0xFF2E7D32)
                                    )
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(12.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            OutlinedButton(
                                onClick = onAbrirEquipe,
                                shape = RoundedCornerShape(10.dp),
                                modifier = Modifier.testTag("btn_ver_equipe")
                            ) {
                                Icon(imageVector = Icons.Default.Group, contentDescription = null, modifier = Modifier.size(16.dp))
                                Spacer(modifier = Modifier.width(6.dp))
                                Text("Ver Equipe PUC Minas", fontSize = 12.sp, fontWeight = FontWeight.SemiBold)
                            }

                            Button(
                                onClick = onNavegarParaSimulador,
                                colors = ButtonDefaults.buttonColors(containerColor = VerdePrimario),
                                shape = RoundedCornerShape(10.dp),
                                modifier = Modifier.testTag("btn_ir_simulador")
                            ) {
                                Icon(imageVector = Icons.Default.Calculate, contentDescription = null, modifier = Modifier.size(16.dp))
                                Spacer(modifier = Modifier.width(6.dp))
                                Text("Simulador", fontSize = 12.sp, fontWeight = FontWeight.Bold)
                            }
                        }
                    }
                }
            }
        }

        // Título da Seção de Diagnóstico
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = "Diagnóstico de 5 Dias",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = TextoPrincipal
                    )
                    Text(
                        text = "Período presencial: 5 a 9 de outubro de 2026",
                        fontSize = 12.sp,
                        color = TextoSecundario
                    )
                }

                OutlinedButton(
                    onClick = onNavegarParaEvolucao,
                    shape = RoundedCornerShape(8.dp),
                    contentPadding = PaddingValues(horizontal = 10.dp, vertical = 4.dp)
                ) {
                    Icon(imageVector = Icons.Default.Timeline, contentDescription = null, modifier = Modifier.size(14.dp))
                    Spacer(modifier = Modifier.width(4.dp))
                    Text("Ver Gráfico", fontSize = 11.sp, fontWeight = FontWeight.SemiBold)
                }
            }
        }

        // Cartão 1: Sobra Alimentar
        item {
            BigMetricCard(
                titulo = "Sobra alimentar aferida",
                valorDestaque = "61,60",
                unidade = "kg no período",
                subtitulo = "Média de 12,32 kg por dia",
                notaExplicativa = "Sobra limpa medida diretamente no balcão e refeitório após o horário da merenda.",
                corDestaque = VerdePrimario,
                icone = "⚖️"
            )
        }

        // Cartão 2: Pratos Equivalentes (com a ressalva requerida)
        item {
            BigMetricCard(
                titulo = "Pratos equivalentes",
                valorDestaque = "246,4",
                unidade = "pratos estimados",
                subtitulo = "Cálculo: sobra ÷ 0,250 kg por prato",
                notaExplicativa = "⚠️ Estimativa teórica com base no peso médio de 250g; não são refeições efetivamente servidas e deixadas nas mesas.",
                corDestaque = AzulSecundario,
                icone = "🍽️"
            )
        }

        // Cartão 3: Perda Financeira Estimada
        item {
            BigMetricCard(
                titulo = "Perda financeira estimada",
                valorDestaque = "R$ 1.145,76",
                unidade = "nos 5 dias",
                subtitulo = "246,4 pratos × R$ 4,65 custo unitário",
                notaExplicativa = "Custo de referência unitário do prato servido no âmbito das diretrizes do PNAE/FNDE.",
                corDestaque = Color(0xFFC62828),
                icone = "💰"
            )
        }

        // Cartão 4: Energia Equivalente
        item {
            BigMetricCard(
                titulo = "Energia alimentar equivalente",
                valorDestaque = "~86.240",
                unidade = "kcal não aproveitadas",
                subtitulo = "Pratos × 350 kcal (piso conservador)",
                notaExplicativa = "Considera o piso de referência calórica para estudantes dos Anos Iniciais do Ensino Fundamental.",
                corDestaque = DouradoAlerta,
                icone = "⚡"
            )
        }

        // Ressalva Metodológica
        item {
            InstitutionalDisclaimer(
                texto = "Nota metodológica para a direção: Os valores representam o piloto de 5 dias úteis. São estimativas gerenciais de contabilidade pública para apoiar o planejamento nutricional, não demonstrando desvio ou irregularidade.",
                alerta = false
            )
        }
    }
}
