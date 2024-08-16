package conta.repository;

import conta.model.Conta;
import java.util.List;

public interface ContaRepository {
    void adicionar(Conta conta);
    boolean existe(int numero);
    Conta buscar(int numero);
    void remover(int numero);
    int gerarNumero(); // Método para gerar números de contas
    void atualizar(Conta conta); // Método para atualizar uma conta existente
    List<Conta> buscarTodas(); // Método para listar todas as contas
}
