package conta.repository;

import conta.model.Conta;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;

public class ContaRepositoryImpl implements ContaRepository {

    private Map<Integer, Conta> contas = new HashMap<>();
    private static int contador = 1;

    @Override
    public int gerarNumero() {
        return contador++;
    }

    @Override
    public void adicionar(Conta conta) {
        contas.put(conta.getNumero(), conta);
    }

    @Override
    public boolean existe(int numero) {
        return contas.containsKey(numero);
    }

    @Override
    public Conta buscar(int numero) {
        return contas.get(numero);
    }

    @Override
    public void remover(int numero) {
        contas.remove(numero);
    }

    @Override
    public void atualizar(Conta conta) {
        contas.put(conta.getNumero(), conta);
    }

    @Override
    public List<Conta> buscarTodas() {
        return new ArrayList<>(contas.values());
    }
}
