package br.edu.facec.sorteio.pessoa.service

import br.edu.facec.sorteio.pessoa.entity.Pessoa
import br.edu.facec.sorteio.pessoa.repository.PessoaRepository
import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.util.*

internal class DefaultPessoaServiceTest{

    @Test
    internal fun deve_encontrar_a_pessoa_pelo_id() {

        val repository = MockPessoaRepository()
        val unit = DefaultPessoaService(repository)
        val resultado = unit.findById(UUID.randomUUID())

        assertThat(resultado).isNotNull()
    }
    @Test
    internal fun deve_dispara_excessao_quando_passar_uma_pessoa_pelo_id() {
        val repository = MockPessoaNulaRepository()
        val unit = DefaultPessoaService(repository)

        Assertions.assertThatThrownBy {
            unit.findById(UUID.randomUUID())
        }.isInstanceOf(IllegalStateException::class.java).hasMessage("Pessoa não encontrada.")
    }
}

class MockPessoaNulaRepository : PessoaRepository {
    override fun save(pessoa: Pessoa) {
        TODO("Not yet implemented")
    }

    override fun existsById(id: UUID): Boolean {
        TODO("Not yet implemented")
    }

    override fun findById(id: UUID): Pessoa? {
        return null
    }

    override fun findAll(): List<Pessoa> {
        TODO("Not yet implemented")
    }

    override fun deleteById(id: UUID) {
        TODO("Not yet implemented")
    }

}
class MockPessoaRepository : PessoaRepository {
    override fun save(pessoa: Pessoa) {
        TODO("Not yet implemented")
    }

    override fun existsById(id: UUID): Boolean {
        TODO("Not yet implemented")
    }

    override fun findById(id: UUID): Pessoa? {
        return Pessoa(UUID.randomUUID(), "Dale", "4499884321", "astolfin@gmail.com",null)

    }

    override fun findAll(): List<Pessoa> {
        TODO("Not yet implemented")
    }

    override fun deleteById(id: UUID) {
        TODO("Not yet implemented")
    }

}
