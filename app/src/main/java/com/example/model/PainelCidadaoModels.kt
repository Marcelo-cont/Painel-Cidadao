package com.example.model

data class DiaAuditoria(
    val diaNumero: Int,
    val dataFormatada: String,
    val sobraKg: Double,
    val perdaFinanceiraReais: Double,
    val pratosEquivalentes: Double,
    val energiaKcal: Int,
    val observacao: String = ""
)

data class NovoRegistroPesagem(
    val id: String,
    val dataIdentificador: String,
    val sobraKg: Double,
    val refeicoesServidas: Int
) {
    val pratosDesperdicados: Double
        get() = sobraKg / 0.250

    val perdaFinanceiraReais: Double
        get() = pratosDesperdicados * 4.65

    val energiaKcal: Double
        get() = pratosDesperdicados * 350.0

    val sobraPorAlunoKg: Double
        get() = if (refeicoesServidas > 0) sobraKg / refeicoesServidas else 0.0
}

data class MetaOdsInfo(
    val numeroOds: Int,
    val tituloOds: String,
    val metaCodigo: String,
    val metaDescricao: String,
    val contribuicaoProjeto: String,
    val corHex: Long
)

enum class SecaoPainel(val id: String, val tituloMenu: String, val iconeNome: String) {
    INICIO("inicio", "Início", "home"),
    DIAGNOSTICO("diagnostico", "5 Dias", "bar_chart"),
    EVOLUCAO("evolucao", "Evolução", "trending_down"),
    CALCULADORA("calculadora", "Simulador", "calculate"),
    CUSTO_OPORTUNIDADE("oportunidade", "Equivalência", "devices"),
    KPIS("kpis", "Indicadores", "analytics"),
    ESCALA("escala", "100 Escolas", "domain"),
    ODS("ods", "ODS & Controle", "verified"),
    PROXIMOS_PASSOS("passos", "Próximos Passos", "checklist")
}

data class CenarioEscalaItem(
    val titulo: String,
    val valorMilhoes: Double,
    val valorFormatado: String,
    val descricao: String,
    val destaque: Boolean = false
)
