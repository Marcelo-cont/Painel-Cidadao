package com.example.ui

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Calculate
import androidx.compose.material.icons.filled.Group
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.Timeline
import androidx.compose.material.icons.filled.VerifiedUser
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.R
import com.example.model.SecaoPainel
import com.example.viewmodel.PainelCidadaoViewModel
import com.example.ui.components.EquipeModalDialog
import com.example.ui.screens.CalculadoraScreen
import com.example.ui.screens.EvolucaoDiariaScreen
import com.example.ui.screens.InicioDiagnosticoScreen
import com.example.ui.screens.KpisEscalaOdsScreen
import com.example.ui.screens.WebPresentationScreen
import com.example.ui.theme.AzulSecundario
import com.example.ui.theme.TextoPrincipal
import com.example.ui.theme.TextoSecundario
import com.example.ui.theme.VerdePrimario

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainAppContent(
    viewModel: PainelCidadaoViewModel = viewModel(),
    modifier: Modifier = Modifier
) {
    val secaoAtiva by viewModel.secaoAtiva.collectAsStateWithLifecycle()
    val mostrarEquipeModal by viewModel.mostrarModalEquipe.collectAsStateWithLifecycle()
    val sobraKgText by viewModel.calcSobraKg.collectAsStateWithLifecycle()
    val refeicoesText by viewModel.calcRefeicoes.collectAsStateWithLifecycle()
    val diasLetivosText by viewModel.calcDiasLetivos.collectAsStateWithLifecycle()
    val registrosSessao by viewModel.novosRegistros.collectAsStateWithLifecycle()

    var modoWebAtivo by remember { mutableStateOf(false) }

    // Intercepta botão voltar
    BackHandler(enabled = modoWebAtivo || secaoAtiva != SecaoPainel.INICIO) {
        if (modoWebAtivo) {
            modoWebAtivo = false
        } else {
            viewModel.selecionarSecao(SecaoPainel.INICIO)
        }
    }

    if (modoWebAtivo) {
        WebPresentationScreen(
            onVoltar = { modoWebAtivo = false }
        )
    } else {
        Scaffold(
            modifier = modifier.fillMaxSize(),
            topBar = {
                TopAppBar(
                    title = {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Box(
                                modifier = Modifier
                                    .size(34.dp)
                                    .clip(RoundedCornerShape(8.dp))
                                    .background(VerdePrimario.copy(alpha = 0.12f)),
                                contentAlignment = Alignment.Center
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.ic_launcher_merenda),
                                    contentDescription = "Logo",
                                    modifier = Modifier.size(26.dp)
                                )
                            }
                            Spacer(modifier = Modifier.width(10.dp))
                            Column {
                                Text(
                                    text = "Painel Cidadão",
                                    fontSize = 17.sp,
                                    fontWeight = FontWeight.ExtraBold,
                                    color = TextoPrincipal
                                )
                                Text(
                                    text = "E.E. Professor Morais · PUC Minas",
                                    fontSize = 10.sp,
                                    fontWeight = FontWeight.Medium,
                                    color = VerdePrimario
                                )
                            }
                        }
                    },
                    actions = {
                        // Botão para ver a Versão Web Responsive em tela cheia
                        IconButton(
                            onClick = { modoWebAtivo = true },
                            modifier = Modifier.testTag("btn_modo_web")
                        ) {
                            Icon(
                                imageVector = Icons.Default.Language,
                                contentDescription = "Modo Web",
                                tint = AzulSecundario
                            )
                        }

                        // Botão para ver a Equipe
                        IconButton(
                            onClick = { viewModel.abrirModalEquipe() },
                            modifier = Modifier.testTag("btn_equipe_topbar")
                        ) {
                            Icon(
                                imageVector = Icons.Default.Group,
                                contentDescription = "Equipe Acadêmica",
                                tint = VerdePrimario
                            )
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.surface
                    ),
                    modifier = Modifier.statusBarsPadding()
                )
            },
            bottomBar = {
                NavigationBar(
                    modifier = Modifier
                        .navigationBarsPadding()
                        .testTag("painel_bottom_navigation"),
                    containerColor = MaterialTheme.colorScheme.surface,
                    tonalElevation = 6.dp
                ) {
                    NavigationBarItem(
                        selected = secaoAtiva == SecaoPainel.INICIO,
                        onClick = { viewModel.selecionarSecao(SecaoPainel.INICIO) },
                        icon = { Icon(imageVector = Icons.Default.Home, contentDescription = "Início") },
                        label = { Text("Início", fontSize = 11.sp, fontWeight = FontWeight.SemiBold) },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = Color.White,
                            indicatorColor = VerdePrimario
                        ),
                        modifier = Modifier.testTag("nav_tab_inicio")
                    )

                    NavigationBarItem(
                        selected = secaoAtiva == SecaoPainel.EVOLUCAO,
                        onClick = { viewModel.selecionarSecao(SecaoPainel.EVOLUCAO) },
                        icon = { Icon(imageVector = Icons.Default.Timeline, contentDescription = "Evolução") },
                        label = { Text("Evolução", fontSize = 11.sp, fontWeight = FontWeight.SemiBold) },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = Color.White,
                            indicatorColor = VerdePrimario
                        ),
                        modifier = Modifier.testTag("nav_tab_evolucao")
                    )

                    NavigationBarItem(
                        selected = secaoAtiva == SecaoPainel.CALCULADORA,
                        onClick = { viewModel.selecionarSecao(SecaoPainel.CALCULADORA) },
                        icon = { Icon(imageVector = Icons.Default.Calculate, contentDescription = "Simulador") },
                        label = { Text("Simulador", fontSize = 11.sp, fontWeight = FontWeight.SemiBold) },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = Color.White,
                            indicatorColor = VerdePrimario
                        ),
                        modifier = Modifier.testTag("nav_tab_simulador")
                    )

                    NavigationBarItem(
                        selected = secaoAtiva == SecaoPainel.KPIS,
                        onClick = { viewModel.selecionarSecao(SecaoPainel.KPIS) },
                        icon = { Icon(imageVector = Icons.Default.VerifiedUser, contentDescription = "Gestão & ODS") },
                        label = { Text("Gestão", fontSize = 11.sp, fontWeight = FontWeight.SemiBold) },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = Color.White,
                            indicatorColor = VerdePrimario
                        ),
                        modifier = Modifier.testTag("nav_tab_gestao")
                    )
                }
            }
        ) { innerPadding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                when (secaoAtiva) {
                    SecaoPainel.INICIO, SecaoPainel.DIAGNOSTICO -> InicioDiagnosticoScreen(
                        onNavegarParaSimulador = { viewModel.selecionarSecao(SecaoPainel.CALCULADORA) },
                        onNavegarParaEvolucao = { viewModel.selecionarSecao(SecaoPainel.EVOLUCAO) },
                        onAbrirEquipe = { viewModel.abrirModalEquipe() }
                    )

                    SecaoPainel.EVOLUCAO -> EvolucaoDiariaScreen(
                        dadosDias = viewModel.dadosAuditoria5Dias
                    )

                    SecaoPainel.CALCULADORA, SecaoPainel.CUSTO_OPORTUNIDADE -> CalculadoraScreen(
                        sobraKgText = sobraKgText,
                        onSobraKgChange = { viewModel.setCalcSobraKg(it) },
                        refeicoesText = refeicoesText,
                        onRefeicoesChange = { viewModel.setCalcRefeicoes(it) },
                        diasLetivosText = diasLetivosText,
                        onDiasLetivosChange = { viewModel.setCalcDiasLetivos(it) },
                        onReset = { viewModel.resetarCalculadora() }
                    )

                    SecaoPainel.KPIS, SecaoPainel.ESCALA, SecaoPainel.ODS, SecaoPainel.PROXIMOS_PASSOS -> KpisEscalaOdsScreen(
                        cenarios = viewModel.cenarios100Escolas,
                        metasOds = viewModel.metasOds,
                        registrosSessao = registrosSessao,
                        onAdicionarRegistro = { data, kg, ref -> viewModel.adicionarNovoRegistro(data, kg, ref) },
                        onRemoverRegistro = { viewModel.removerRegistro(it) }
                    )
                }
            }
        }
    }

    // Modal da Equipe PUC Minas
    if (mostrarEquipeModal) {
        EquipeModalDialog(
            equipe = viewModel.equipeProjeto,
            onDismiss = { viewModel.fecharModalEquipe() }
        )
    }
}
