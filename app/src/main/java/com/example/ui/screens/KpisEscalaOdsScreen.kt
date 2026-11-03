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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.FoodBank
import androidx.compose.material.icons.filled.Gavel
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.material.icons.filled.Security
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.CenarioEscalaItem
import com.example.model.MetaOdsInfo
import com.example.model.NovoRegistroPesagem
import com.example.ui.components.InstitutionalDisclaimer
import com.example.ui.theme.AzulSecundario
import com.example.ui.theme.DouradoAlerta
import com.example.ui.theme.TextoPrincipal
import com.example.ui.theme.TextoSecundario
import com.example.ui.theme.VerdePrimario

@Composable
fun KpisEscalaOdsScreen(
    cenarios: List<CenarioEscalaItem>,
    metasOds: List<MetaOdsInfo>,
    registrosSessao: List<NovoRegistroPesagem>,
    onAdicionarRegistro: (String, Double, Int) -> Unit,
    onRemoverRegistro: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var inputData by remember { mutableStateOf("15/10 (Qua)") }
    var inputKgText by remember { mutableStateOf("9.10") }
    var inputRefeicoesText by remember { mutableStateOf("260") }

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .testTag("tela_kpis_escala_ods"),
        contentPadding = PaddingValues(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 96.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // 6. INDICADORES DE ACOMPANHAMENTO (KPIS)
        item {
            Column {
                Text(
                    text = "Indicadores de Acompanhamento (KPIs)",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextoPrincipal
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Fórmulas simples para orientar a tomada de decisão da gestão e nutricionistas",
                    fontSize = 13.sp,
                    color = TextoSecundario
                )
            }
        }

        // 3 Cards de Fórmulas Explicadas
        item {
            Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                KpiFormulaCard(
                    numero = "1",
                    nome = "Taxa de Redução da Sobra Limpa (%)",
                    formula = "((Sobra Anterior − Sobra Atual) ÷ Sobra Anterior) × 100",
                    explicacao = "Avalia se as ações educativas ou de ajuste de porção reduziram o desperdício entre pesagens.",
                    cor = VerdePrimario
                )

                KpiFormulaCard(
                    numero = "2",
                    nome = "Índice de Sobra Líquida por Aluno (kg/aluno)",
                    formula = "Sobra Total (kg) ÷ Refeições Servidas",
                    explicacao = "Calcula a média individual deixada por prato. Meta ideal: menos de 0,030 kg/aluno (30g).",
                    cor = AzulSecundario
                )

                KpiFormulaCard(
                    numero = "3",
                    nome = "Aderência ao Target Calórico da Faixa Etária",
                    formula = "Anos Iniciais: 350 kcal | Anos Finais: 470 kcal | Ensino Médio: 540 kcal",
                    explicacao = "Garante que o combate à sobra não diminua a nutrição mínima exigida por lei.",
                    cor = DouradoAlerta
                )
            }
        }

        // Mini-Formulário para a Escola Lançar Novos Dias de Pesagem
        item {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("card_novo_registro_sessao"),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                shape = RoundedCornerShape(20.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "REGISTRAR NOVO DIA DE PESAGEM",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            color = VerdePrimario,
                            letterSpacing = 1.sp
                        )
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(6.dp))
                                .background(Color(0xFFE8F5E9))
                                .padding(horizontal = 6.dp, vertical = 2.dp)
                        ) {
                            Text(
                                text = "Dados na Sessão",
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold,
                                color = VerdePrimario
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    OutlinedTextField(
                        value = inputData,
                        onValueChange = { inputData = it },
                        label = { Text("Identificação / Data (Ex: 15/10 Qua)") },
                        singleLine = true,
                        shape = RoundedCornerShape(10.dp),
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        OutlinedTextField(
                            value = inputKgText,
                            onValueChange = { inputKgText = it },
                            label = { Text("Sobra limpa (kg)") },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                            singleLine = true,
                            shape = RoundedCornerShape(10.dp),
                            modifier = Modifier.weight(1f)
                        )

                        OutlinedTextField(
                            value = inputRefeicoesText,
                            onValueChange = { inputRefeicoesText = it },
                            label = { Text("Refeições servidas") },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                            singleLine = true,
                            shape = RoundedCornerShape(10.dp),
                            modifier = Modifier.weight(1f)
                        )
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    Button(
                        onClick = {
                            val kg = inputKgText.replace(",", ".").toDoubleOrNull() ?: 0.0
                            val ref = inputRefeicoesText.toIntOrNull() ?: 0
                            if (kg > 0) {
                                onAdicionarRegistro(inputData, kg, ref)
                            }
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = VerdePrimario),
                        shape = RoundedCornerShape(10.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(44.dp)
                            .testTag("btn_adicionar_pesagem")
                    ) {
                        Icon(imageVector = Icons.Default.Add, contentDescription = null, modifier = Modifier.size(16.dp))
                        Spacer(modifier = Modifier.width(6.dp))
                        Text("Adicionar Pesagem ao Painel", fontWeight = FontWeight.Bold, fontSize = 13.sp)
                    }

                    Spacer(modifier = Modifier.height(14.dp))

                    // Registros Lançados na Sessão
                    Text(
                        text = "Registros Ativos (${registrosSessao.size})",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold,
                        color = TextoPrincipal
                    )

                    Spacer(modifier = Modifier.height(6.dp))

                    Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                        registrosSessao.forEach { reg ->
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clip(RoundedCornerShape(8.dp))
                                    .background(Color(0xFFF4F7F5))
                                    .padding(horizontal = 10.dp, vertical = 8.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Column {
                                    Text(
                                        text = "${reg.dataIdentificador} · ${reg.refeicoesServidas} ref.",
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 12.sp
                                    )
                                    Text(
                                        text = "~${String.format("%.1f", reg.pratosDesperdicados)} pratos | R$ ${String.format("%.2f", reg.perdaFinanceiraReais)} | ${String.format("%.3f", reg.sobraPorAlunoKg)} kg/aluno",
                                        fontSize = 11.sp,
                                        color = TextoSecundario
                                    )
                                }

                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Text(
                                        text = "${String.format("%.2f", reg.sobraKg)} kg",
                                        fontWeight = FontWeight.ExtraBold,
                                        fontSize = 13.sp,
                                        color = VerdePrimario
                                    )
                                    IconButton(
                                        onClick = { onRemoverRegistro(reg.id) },
                                        modifier = Modifier.size(32.dp)
                                    ) {
                                        Icon(
                                            imageVector = Icons.Default.Delete,
                                            contentDescription = "Remover",
                                            tint = Color(0xFFC62828),
                                            modifier = Modifier.size(16.dp)
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        // 7. ESCALANDO A IDEIA (100 ESCOLAS)
        item {
            Column {
                Text(
                    text = "Escalando a ideia: O que representaria em 100 escolas?",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextoPrincipal
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Simulação do impacto se a metodologia for estendida para a rede estadual",
                    fontSize = 12.sp,
                    color = TextoSecundario
                )
            }
        }

        item {
            Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                cenarios.forEach { cenario ->
                    CenarioCard(cenario = cenario)
                }
            }
        }

        // Aviso Obrigatório de Escala
        item {
            InstitutionalDisclaimer(
                texto = "“Simulações, não perdas comprovadas.” Estes valores são estimativas de modelagem de rede para justificar políticas públicas de monitoramento contínuo.",
                alerta = true
            )
        }

        // 8. ODS E CONTROLE SOCIAL
        item {
            Column {
                Text(
                    text = "ODS e Controle Social na Merenda",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextoPrincipal
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Como o projeto atende às metas globais da ONU e às leis do PNAE/FNDE",
                    fontSize = 12.sp,
                    color = TextoSecundario
                )
            }
        }

        // Tabela de ODS
        item {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                metasOds.forEach { meta ->
                    MetaOdsCard(meta = meta)
                }
            }
        }

        // PNAE / FNDE e CAE Cards
        item {
            Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFE8F5E9)),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Column(modifier = Modifier.padding(14.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(imageVector = Icons.Default.Restaurant, contentDescription = null, tint = VerdePrimario)
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "Diretriz PNAE / FNDE (Mínimo de 75% in natura)",
                                fontWeight = FontWeight.Bold,
                                fontSize = 14.sp,
                                color = Color(0xFF1B5E20)
                            )
                        }
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            text = "Por determinação legal, ao menos 75% dos recursos da alimentação escolar devem adquirir alimentos in natura ou minimamente processados. Evitar o desperdício assegura que esses alimentos frescos de alta qualidade sejam efetivamente consumidos.",
                            fontSize = 12.sp,
                            lineHeight = 16.sp,
                            color = Color(0xFF2E7D32)
                        )
                    }
                }

                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFE3F2FD)),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Column(modifier = Modifier.padding(14.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(imageVector = Icons.Default.Gavel, contentDescription = null, tint = AzulSecundario)
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "Papel do Conselho de Alimentação Escolar (CAE)",
                                fontWeight = FontWeight.Bold,
                                fontSize = 14.sp,
                                color = Color(0xFF0D47A1)
                            )
                        }
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            text = "O CAE é o órgão colegiado de fiscalização social permanente. O Painel Cidadão capacita conselheiros e a diretoria com relatórios técnicos e transparentes para aprimorar os cardápios em conjunto com a comunidade.",
                            fontSize = 12.sp,
                            lineHeight = 16.sp,
                            color = Color(0xFF1565C0)
                        )
                    }
                }
            }
        }

        // 9. PRÓXIMOS PASSOS
        item {
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = Color(0xFF0E3821)),
                shape = RoundedCornerShape(20.dp)
            ) {
                Column(modifier = Modifier.padding(18.dp)) {
                    Text(
                        text = "PRÓXIMOS PASSOS RECOMENDADOS",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFFA7F3D0),
                        letterSpacing = 1.sp
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    ProximoPassoItem(
                        numero = "1",
                        titulo = "Reunião Técnica com as Nutricionistas do PNAE/BH",
                        descricao = "Apresentar estes dados detalhados para calibrar a quantidade e os itens de menor aceitação no cardápio escolar."
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    ProximoPassoItem(
                        numero = "2",
                        titulo = "Ajuste de Porções por Faixa Etária",
                        descricao = "Personalizar porções para Anos Iniciais (350 kcal), Anos Finais (470 kcal) e Ensino Médio (540 kcal), com liberdade de repetição sem sobras."
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    ProximoPassoItem(
                        numero = "3",
                        titulo = "Replicação da Metodologia na Rede",
                        descricao = "Expandir o piloto de Atrito Zero para outras escolas estaduais da regional, multiplicando o ganho social."
                    )
                }
            }
        }

        // Rodapé Obrigatório Requerido
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 12.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "PUC Minas · ICEG · Ciências Contábeis · TEI 2026/2 · Fonte: TEI – Projeto Escola (2026)",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextoSecundario
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Escola Estadual Professor Morais · Belo Horizonte / MG",
                    fontSize = 10.sp,
                    color = TextoSecundario.copy(alpha = 0.7f)
                )
            }
        }
    }
}

@Composable
private fun KpiFormulaCard(
    numero: String,
    nome: String,
    formula: String,
    explicacao: String,
    cor: Color
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        shape = RoundedCornerShape(16.dp),
        border = CardDefaults.outlinedCardBorder().copy(
            brush = androidx.compose.ui.graphics.SolidColor(Color(0xFFE2EBE5))
        )
    ) {
        Column(modifier = Modifier.padding(14.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(24.dp)
                        .clip(CircleShape)
                        .background(cor.copy(alpha = 0.15f)),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = numero, fontSize = 12.sp, fontWeight = FontWeight.Bold, color = cor)
                }
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = nome, fontWeight = FontWeight.Bold, fontSize = 13.sp, color = TextoPrincipal)
            }

            Spacer(modifier = Modifier.height(6.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color(0xFFF4F7F5))
                    .padding(8.dp)
            ) {
                Text(
                    text = formula,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = cor
                )
            }

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = explicacao,
                fontSize = 11.sp,
                color = TextoSecundario,
                lineHeight = 15.sp
            )
        }
    }
}

@Composable
private fun CenarioCard(cenario: CenarioEscalaItem) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = if (cenario.destaque) Color(0xFFE8F5E9) else MaterialTheme.colorScheme.surface
        ),
        shape = RoundedCornerShape(16.dp),
        border = CardDefaults.outlinedCardBorder().copy(
            brush = androidx.compose.ui.graphics.SolidColor(
                if (cenario.destaque) VerdePrimario else Color(0xFFE2EBE5)
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
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = cenario.titulo.uppercase(),
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    color = if (cenario.destaque) VerdePrimario else TextoSecundario
                )
                Text(
                    text = cenario.descricao,
                    fontSize = 11.sp,
                    color = TextoSecundario,
                    lineHeight = 15.sp
                )
            }
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                text = cenario.valorFormatado,
                fontSize = 14.sp,
                fontWeight = FontWeight.Black,
                color = if (cenario.destaque) VerdePrimario else TextoPrincipal
            )
        }
    }
}

@Composable
private fun MetaOdsCard(meta: MetaOdsInfo) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        shape = RoundedCornerShape(14.dp),
        border = CardDefaults.outlinedCardBorder().copy(
            brush = androidx.compose.ui.graphics.SolidColor(Color(0xFFE2EBE5))
        )
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(10.dp)
                        .clip(CircleShape)
                        .background(Color(meta.corHex))
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = "ODS ${meta.numeroOds} · ${meta.metaCodigo}",
                    fontWeight = FontWeight.Bold,
                    fontSize = 12.sp,
                    color = TextoPrincipal
                )
            }

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = meta.metaDescricao,
                fontSize = 11.sp,
                color = TextoSecundario,
                lineHeight = 15.sp
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "No projeto: ${meta.contribuicaoProjeto}",
                fontSize = 11.sp,
                fontWeight = FontWeight.Medium,
                color = VerdePrimario,
                lineHeight = 15.sp
            )
        }
    }
}

@Composable
private fun ProximoPassoItem(
    numero: String,
    titulo: String,
    descricao: String
) {
    Row(verticalAlignment = Alignment.Top) {
        Box(
            modifier = Modifier
                .size(24.dp)
                .clip(CircleShape)
                .background(Color(0xFF10B981).copy(alpha = 0.25f)),
            contentAlignment = Alignment.Center
        ) {
            Text(text = numero, fontSize = 12.sp, fontWeight = FontWeight.Bold, color = Color.White)
        }
        Spacer(modifier = Modifier.width(10.dp))
        Column {
            Text(text = titulo, fontSize = 13.sp, fontWeight = FontWeight.Bold, color = Color.White)
            Text(text = descricao, fontSize = 11.sp, color = Color(0xFFD1FAE5), lineHeight = 15.sp)
        }
    }
}
