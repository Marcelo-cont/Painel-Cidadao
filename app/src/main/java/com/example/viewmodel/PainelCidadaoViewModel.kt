package com.example.viewmodel

import androidx.lifecycle.ViewModel
import com.example.model.CenarioEscalaItem
import com.example.model.DiaAuditoria
import com.example.model.MetaOdsInfo
import com.example.model.NovoRegistroPesagem
import com.example.model.SecaoPainel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.util.UUID

class PainelCidadaoViewModel : ViewModel() {

    // Seção ativa para navegação
    private val _secaoAtiva = MutableStateFlow(SecaoPainel.INICIO)
    val secaoAtiva: StateFlow<SecaoPainel> = _secaoAtiva.asStateFlow()

    // Dialog de créditos da equipe universitária
    private val _mostrarModalEquipe = MutableStateFlow(false)
    val mostrarModalEquipe: StateFlow<Boolean> = _mostrarModalEquipe.asStateFlow()

    // Dados oficiais auditados de 5 dias (5 a 9 de outubro de 2026)
    val dadosAuditoria5Dias: List<DiaAuditoria> = listOf(
        DiaAuditoria(
            diaNumero = 1,
            dataFormatada = "05/10 (Seg)",
            sobraKg = 16.90,
            perdaFinanceiraReais = 314.34,
            pratosEquivalentes = 67.6,
            energiaKcal = 23660,
            observacao = "Primeiro dia de pesagem direta com balança própria."
        ),
        DiaAuditoria(
            diaNumero = 2,
            dataFormatada = "06/10 (Ter)",
            sobraKg = 13.585,
            perdaFinanceiraReais = 252.68,
            pratosEquivalentes = 54.34,
            energiaKcal = 19019,
            observacao = "Queda de 19,6% em relação ao dia anterior."
        ),
        DiaAuditoria(
            diaNumero = 3,
            dataFormatada = "07/10 (Qua)",
            sobraKg = 12.29,
            perdaFinanceiraReais = 228.59,
            pratosEquivalentes = 49.16,
            energiaKcal = 17206,
            observacao = "Estabilização gradual da sobra limpa."
        ),
        DiaAuditoria(
            diaNumero = 4,
            dataFormatada = "08/10 (Qui)",
            sobraKg = 8.315,
            perdaFinanceiraReais = 154.66,
            pratosEquivalentes = 33.26,
            energiaKcal = 11641,
            observacao = "Menor sobra do período: redução de ~51% comparada ao 1º dia."
        ),
        DiaAuditoria(
            diaNumero = 5,
            dataFormatada = "09/10 (Sex)",
            sobraKg = 10.51,
            perdaFinanceiraReais = 195.49,
            pratosEquivalentes = 42.04,
            energiaKcal = 14714,
            observacao = "Aumento pontual na sexta-feira. Amostra curta requer cautela."
        )
    )

    // Totais e médias consolidadas do diagnóstico
    val totalSobraKg: Double = 61.60
    val mediaSobraKgDia: Double = 12.32
    val totalPratosEquivalentes: Double = 246.4
    val totalPerdaFinanceiraReais: Double = 1145.76
    val totalEnergiaKcal: Int = 86240

    // Equipe do projeto TEI PUC Minas
    val equipeProjeto: List<String> = listOf(
        "Erick Marques Queiroz",
        "Geovana Cristina Ferreira de Jesus",
        "Lucas Alves Nery",
        "Lucas Henrique Félix de Barros",
        "Marcelo Carvalho Couto de Souza",
        "Sarah Gabriela Vieira Madeira"
    )

    // Calculadora Interativa - "Simule você mesma"
    private val _calcSobraKg = MutableStateFlow("12.32")
    val calcSobraKg: StateFlow<String> = _calcSobraKg.asStateFlow()

    private val _calcRefeicoes = MutableStateFlow("250")
    val calcRefeicoes: StateFlow<String> = _calcRefeicoes.asStateFlow()

    private val _calcDiasLetivos = MutableStateFlow("200")
    val calcDiasLetivos: StateFlow<String> = _calcDiasLetivos.asStateFlow()

    fun setCalcSobraKg(valor: String) {
        _calcSobraKg.value = valor.replace(",", ".")
    }

    fun setCalcRefeicoes(valor: String) {
        _calcRefeicoes.value = valor
    }

    fun setCalcDiasLetivos(valor: String) {
        _calcDiasLetivos.value = valor
    }

    fun resetarCalculadora() {
        _calcSobraKg.value = "12.32"
        _calcRefeicoes.value = "250"
        _calcDiasLetivos.value = "200"
    }

    // Mini-Formulário de Novos Dias de Pesagem da Escola (Sessão)
    private val _novosRegistros = MutableStateFlow<List<NovoRegistroPesagem>>(
        listOf(
            NovoRegistroPesagem("base_1", "13/10 (Seg)", 9.20, 240),
            NovoRegistroPesagem("base_2", "14/10 (Ter)", 8.80, 255)
        )
    )
    val novosRegistros: StateFlow<List<NovoRegistroPesagem>> = _novosRegistros.asStateFlow()

    fun adicionarNovoRegistro(dataId: String, sobraKg: Double, refeicoes: Int) {
        val registro = NovoRegistroPesagem(
            id = UUID.randomUUID().toString(),
            dataIdentificador = dataId.ifBlank { "Novo Dia" },
            sobraKg = sobraKg,
            refeicoesServidas = refeicoes
        )
        _novosRegistros.value = listOf(registro) + _novosRegistros.value
    }

    fun removerRegistro(id: String) {
        _novosRegistros.value = _novosRegistros.value.filterNot { it.id == id }
    }

    // Metas ODS e Controle Social
    val metasOds: List<MetaOdsInfo> = listOf(
        MetaOdsInfo(
            numeroOds = 12,
            tituloOds = "Consumo e Produção Responsáveis",
            metaCodigo = "Meta 12.3",
            metaDescricao = "Até 2030, reduzir pela metade o desperdício de alimentos per capita mundial, nos níveis de varejo e do consumidor, e reduzir as perdas de alimentos ao longo das cadeias de produção e abastecimento.",
            contribuicaoProjeto = "Mede a sobra limpa em ambiente escolar real, identificando gargalos antes da destinação a resíduos e fomentando consciência alimentar comunitária.",
            corHex = 0xFFBF8B2E
        ),
        MetaOdsInfo(
            numeroOds = 2,
            tituloOds = "Fome Zero e Agricultura Sustentável",
            metaCodigo = "Meta 2.1",
            metaDescricao = "Até 2030, acabar com a fome e garantir o acesso de todas as pessoas, em particular os pobres e pessoas em situações vulneráveis, incluindo crianças, a alimentos seguros, nutritivos e suficientes durante todo o ano.",
            contribuicaoProjeto = "Converte sobras em refeições equivalentes para demonstrar o potencial nutricional que deixa de alimentar alunos da rede pública.",
            corHex = 0xFFDDA63A
        ),
        MetaOdsInfo(
            numeroOds = 12,
            tituloOds = "Consumo e Produção Responsáveis",
            metaCodigo = "Meta 12.7",
            metaDescricao = "Promover práticas de compras públicas sustentáveis, de acordo com as políticas e prioridades nacionais.",
            contribuicaoProjeto = "Oferece parâmetros para alinhar os pedidos de compra de insumos do PNAE ao consumo real, evitando sobrecompras.",
            corHex = 0xFFBF8B2E
        ),
        MetaOdsInfo(
            numeroOds = 16,
            tituloOds = "Paz, Justiça e Instituições Eficazes",
            metaCodigo = "Meta 16.6",
            metaDescricao = "Desenvolver instituições eficazes, responsáveis e transparentes em todos os níveis.",
            contribuicaoProjeto = "Subsidia o Conselho de Alimentação Escolar (CAE) e a gestão com contabilidade pública participativa, transparente e sem atrito para as cantineiras.",
            corHex = 0xFF00689D
        )
    )

    // Cenários em Escala (100 Escolas)
    val cenarios100Escolas: List<CenarioEscalaItem> = listOf(
        CenarioEscalaItem(
            titulo = "Cenário Baixo",
            valorMilhoes = 3.50,
            valorFormatado = "R$ 3,50 milhões / ano",
            descricao = "Considera redução média de desperdício com ajustes operacionais imediatos.",
            destaque = false
        ),
        CenarioEscalaItem(
            titulo = "Cenário Base",
            valorMilhoes = 4.58,
            valorFormatado = "R$ 4,58 milhões / ano",
            descricao = "Extrapolação direta da média da E.E. Professor Morais (R$ 45.830,40 × 100 escolas).",
            destaque = true
        ),
        CenarioEscalaItem(
            titulo = "Cenário Alto",
            valorMilhoes = 6.29,
            valorFormatado = "R$ 6,29 milhões / ano",
            descricao = "Cenário em que escolas maiores ou com maior rotatividade apresentam sobras superiores.",
            destaque = false
        )
    )

    fun selecionarSecao(secao: SecaoPainel) {
        _secaoAtiva.value = secao
    }

    fun abrirModalEquipe() {
        _mostrarModalEquipe.value = true
    }

    fun fecharModalEquipe() {
        _mostrarModalEquipe.value = false
    }
}
