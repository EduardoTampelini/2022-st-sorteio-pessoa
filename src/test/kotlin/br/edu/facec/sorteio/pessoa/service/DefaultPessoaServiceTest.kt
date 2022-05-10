package br.edu.facec.sorteio.pessoa.service

import br.edu.facec.sorteio.pessoa.entity.Pessoa
import br.edu.facec.sorteio.pessoa.repository.PessoaRepository
import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import java.time.LocalDate
import java.time.format.DateTimeFormatter
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
    @Test
    internal fun data_nascimento_invalida() {

        val repository = MockPessoaRepository()
        val unit = DefaultPessoaService(repository)
        val pessoa = Pessoa(UUID.randomUUID(), "Dale", "4499884321", "astolfin@gmail.com", LocalDate.now())

        Assertions.assertThatThrownBy {
            unit.save(pessoa)
        }.isInstanceOf(IllegalArgumentException::class.java).hasMessage("A pessoa deve ter data de nascimento menor do que o data atual")
    }
    @Test
    internal fun data_nascimento_valida() {

        var formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")
        val repository = MockPessoaRepository()
        val unit = DefaultPessoaService(repository)
        val pessoa = Pessoa(UUID.randomUUID(), "Dale", "4499884321", "astolfin@gmail.com",LocalDate.parse("30-06-2000",formatter) )
        val resultado = unit.save(pessoa)
        assertThat(resultado).isNotNull()
    }
    @Test
    internal fun deletar_de_um_id_que_existe() {

        val repository = MockPessoaRepository()
        val unit = DefaultPessoaService(repository)
        val id = UUID.randomUUID()
        val resultado = unit.deleteById(id)

        assertThat(resultado).isNotNull()
    }
    @Test
    internal fun deletar_de_um_id_que_nao_existe() {

        val repository = MockPessoaNulaRepository()
        val unit = DefaultPessoaService(repository)
        val id = UUID.randomUUID()


        Assertions.assertThatThrownBy {
            unit.deleteById(UUID.randomUUID())
        }.isInstanceOf(IllegalStateException::class.java).hasMessage("A pessoa que você deseja remover não existe.")
    }
    @Test
    internal fun listar_a_lista_de_pessoas() {


        val repository = MockPessoaRepository()
        val unit = DefaultPessoaService(repository)
        val resultado = unit.findAll()

        assertThat(resultado).isNotNull()

}

class MockPessoaNulaRepository : PessoaRepository {
    override fun save(pessoa: Pessoa) {
        TODO("Not yet implemented")
    }

    override fun existsById(id: UUID): Boolean {
        return false
    }

    override fun findById(id: UUID): Pessoa? {
        return null
    }

    override fun findAll(): List<Pessoa> {
        TODO("Not yet implemented")
    }

    override fun deleteById(id: UUID) {
        Pessoa(UUID.randomUUID(), "Dale", "4499884321", "astolfin@gmail.com", null)
    }

}
class MockPessoaRepository : PessoaRepository {
    override fun save(pessoa: Pessoa) {

    }

    override fun existsById(id: UUID): Boolean {
        return true
    }

    override fun findById(id: UUID): Pessoa? {
        return Pessoa(UUID.randomUUID(), "Dale", "4499884321", "astolfin@gmail.com", LocalDate.now())

    }

    override fun findAll(): List<Pessoa> {
        val pessoa1 = Pessoa(UUID.randomUUID(), "Giorno", "4499884321", "Giorno@gmail.com", LocalDate.now())
        val pessoa2 = Pessoa(UUID.randomUUID(), "Jotaro", "4499884321", "Jotaro@gmail.com", LocalDate.now())
        val pessoa3 = Pessoa(UUID.randomUUID(), "Josuke", "4499884321", "Josuke@gmail.com", LocalDate.now())

        val pessoas: List<Pessoa> = listOf(pessoa1, pessoa2, pessoa3)
        return pessoas
    }

    override fun deleteById(id: UUID) {
        Pessoa(UUID.randomUUID(), "Dale", "4499884321", "astolfin@gmail.com", null)
    }

}}
