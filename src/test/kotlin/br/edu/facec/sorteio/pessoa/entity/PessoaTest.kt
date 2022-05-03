package br.edu.facec.sorteio.pessoa.entity

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
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
            Pessoa(UUID.randomUUID(), "", "4499884321", "astolfin@gmail.com")
        }.isInstanceOf(IllegalArgumentException::class.java).hasMessage("Nome da pessoa deve ser informado.")
    }

    @Test
    internal fun deve_possuir_o_id_igual_ao_esperado() {
        val expectedId = UUID.randomUUID()
       val unit = Pessoa(id = expectedId, nome= "Astolfo", telefone = "447739872", email = "ast@gmail.com")

        assertThat(unit.hasId(expectedId)).isTrue()

    }

    @Test
    internal fun nao_deve_possuir_o_id_igual_ao_esperado() {
        val expectedId = UUID.randomUUID()
        val unit = Pessoa(id = UUID.randomUUID(), nome= "Astolfo", telefone = "447739872", email = "ast@gmail.com")

        assertThat(unit.hasId(expectedId)).isFalse()

    }
}

