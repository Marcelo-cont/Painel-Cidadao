package com.example

import com.example.viewmodel.PainelCidadaoViewModel
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class ExampleUnitTest {

    @Test
    fun `testar somas e medias dos 5 dias de auditoria`() {
        val vm = PainelCidadaoViewModel()

        // 1. Sobra total e média
        val somaKg = vm.dadosAuditoria5Dias.sumOf { it.sobraKg }
        assertEquals(61.60, somaKg, 0.001)

        val mediaKg = somaKg / vm.dadosAuditoria5Dias.size
        assertEquals(12.32, mediaKg, 0.001)

        // 2. Pratos equivalentes: 61.60 / 0.250 = 246.4
        val pratos = somaKg / 0.250
        assertEquals(246.4, pratos, 0.001)

        // 3. Perda financeira: 246.4 * 4.65 = R$ 1.145,76
        val perdaTotal = pratos * 4.65
        assertEquals(1145.76, perdaTotal, 0.01)

        // 4. Energia equivalente: 246.4 * 350 = 86.240 kcal
        val energiaTotal = pratos * 350
        assertEquals(86240.0, energiaTotal, 0.01)

        // 5. Queda do Dia 1 para o Dia 4
        val dia1 = vm.dadosAuditoria5Dias.first { it.diaNumero == 1 }.sobraKg
        val dia4 = vm.dadosAuditoria5Dias.first { it.diaNumero == 4 }.sobraKg
        val taxaQueda = ((dia1 - dia4) / dia1) * 100.0
        assertTrue("Queda deve ser de aproximadamente 50.8%", taxaQueda > 50.0 && taxaQueda < 51.5)
    }

    @Test
    fun `testar projecao anual da calculadora e equivalencia em equipamentos`() {
        val vm = PainelCidadaoViewModel()

        val sobraMedia = 12.32
        val diasLetivos = 200

        val pratosDia = sobraMedia / 0.250
        val prejuizoDia = pratosDia * 4.65 // R$ 229,152
        val projecaoAnual = prejuizoDia * diasLetivos // R$ 45.830,40

        assertEquals(45830.40, projecaoAnual, 0.01)

        val computadores = (projecaoAnual / 2500.0).toInt()
        assertEquals(18, computadores)

        val projetores = (projecaoAnual / 1000.0).toInt()
        assertEquals(45, projetores)
    }

    @Test
    fun `testar mini formulario de pesagem da sessao`() {
        val vm = PainelCidadaoViewModel()
        val qtdInicial = vm.novosRegistros.value.size

        vm.adicionarNovoRegistro("15/10 (Qua)", 10.0, 200)
        assertEquals(qtdInicial + 1, vm.novosRegistros.value.size)

        val ultimo = vm.novosRegistros.value.first()
        assertEquals(10.0, ultimo.sobraKg, 0.001)
        assertEquals(40.0, ultimo.pratosDesperdicados, 0.001) // 10 / 0.250 = 40
        assertEquals(186.0, ultimo.perdaFinanceiraReais, 0.01) // 40 * 4.65 = 186.0
        assertEquals(0.05, ultimo.sobraPorAlunoKg, 0.001) // 10 / 200 = 0.050 kg/aluno
    }
}
