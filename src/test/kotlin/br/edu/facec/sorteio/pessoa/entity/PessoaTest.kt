package br.edu.facec.sorteio.pessoa.entity

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

internal class PessoaTest{
    @Test
    internal fun nome_da_pessoa_nao_deve_ser_vazio() {
        /*var deuExcecao: Boolean = false
        var mensagem: String? = ""
        try {
            val unit = Pessoa(UUID.randomUUID(), "", "4499884321", "astolfin@gmail.com")
        }catch(e : Exception){
            deuExcecao = true
            mensagem = e.message
            //fail("nome da pessoa nao deve ser vazio")
        }
        if(deuExcecao && mensagem == "Nome da pessoa deve ser informado."){

        }else{
            fail("nome da pessoa não deveria ser vazio")
        }
        */
        assertThatThrownBy{
            Pessoa(UUID.randomUUID(), "", "4499884321", "astolfin@gmail.com",null)
        }.isInstanceOf(IllegalArgumentException::class.java).hasMessage("Nome da pessoa deve ser informado.")
    }

    @Test
    internal fun deve_possuir_o_id_igual_ao_esperado() {
        val expectedId = UUID.randomUUID()
       val unit = Pessoa(id = expectedId, nome= "Astolfo", telefone = "447739872", email = "ast@gmail.com",null)

        assertThat(unit.hasId(expectedId)).isTrue()

    }

    @Test
    internal fun nao_deve_possuir_o_id_igual_ao_esperado() {
        val expectedId = UUID.randomUUID()
        val unit = Pessoa(id = UUID.randomUUID(), nome= "Astolfo", telefone = "447739872", email = "ast@gmail.com",null)

        assertThat(unit.hasId(expectedId)).isFalse()

    }

    @Test
    internal fun email_da_pessoa_nao_deve_ser_vazio() {
        assertThatThrownBy{
            Pessoa(UUID.randomUUID(), "Astolfo", "4499884321", "",null)
        }.isInstanceOf(IllegalArgumentException::class.java).hasMessage("E-mail da pessoa deve ser informado.")
    }
    @Test
    internal fun telefone_da_pessoa_nao_deve_ser_vazio() {
        assertThatThrownBy{
            Pessoa(UUID.randomUUID(), "Astolfo", "", "ast@gmail.com",null)
        }.isInstanceOf(IllegalArgumentException::class.java).hasMessage("Telefone da pessoa deve ser informado.")
    }

    @Test
    internal fun data_de_nasciento_normal() {
        var formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")
        var date : LocalDate = LocalDate.parse("31-12-2022", formatter)

        val unit = Pessoa(UUID.randomUUID(), "Astolfo", "4499884321", "ast@gmail.com",LocalDate.parse("30-06-2000",formatter))

        assertThat(unit.hasNascimentoLessThan(date)).isTrue()

    }
    @Test
    internal fun data_de_nasciento_acima() {
        var formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")
        var date : LocalDate = LocalDate.parse("31-12-2022", formatter)

        val unit = Pessoa(UUID.randomUUID(), "Astolfo", "4499884321", "ast@gmail.com",LocalDate.parse("30-06-2023",formatter))

        assertThat(unit.hasNascimentoLessThan(date)).isFalse()

    }
    @Test
    internal fun data_de_nasciento_null() {
        var formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")
        var date : LocalDate = LocalDate.parse("31-12-2022", formatter)

        val unit = Pessoa(UUID.randomUUID(), "Astolfo", "4499884321", "ast@gmail.com",null)

        assertThat(unit.hasNascimentoLessThan(date)).isFalse()

    }
    }


