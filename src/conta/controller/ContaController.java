package conta.controller;

import conta.model.Conta;
import conta.repository.ContaRepository;
import conta.service.ServicoTransferencias;
import conta.service.ServicoTransferenciasImpl;
import java.util.List;

public class ContaController {

    private ContaRepository contaRepository;
    private ServicoTransferencias servicoTransferencias;

    public ContaController(ContaRepository contaRepository) {
        this.contaRepository = contaRepository;
        this.servicoTransferencias = new ServicoTransferenciasImpl(contaRepository);
    }

    public void cadastrar(Conta conta) {
        contaRepository.adicionar(conta);
    }

    public void listarTodas() {
        List<Conta> todasAsContas = contaRepository.buscarTodas();
        for (Conta conta : todasAsContas) {
            System.out.println(conta);
        }
    }

    public void buscarPorNumero(int numero) {
        Conta conta = contaRepository.buscar(numero);
        if (conta != null) {
            System.out.println(conta);
        } else {
            System.out.println("Conta não encontrada!");
        }
    }

    public void atualizar(int numero, Conta contaAtualizada) {
        if (contaRepository.existe(numero)) {
            contaAtualizada.setNumero(numero);
            contaRepository.atualizar(contaAtualizada);
        } else {
            System.out.println("Conta não encontrada!");
        }
    }

    public void excluir(int numero) {
        if (contaRepository.existe(numero)) {
            contaRepository.remover(numero);
        } else {
            System.out.println("Conta não encontrada!");
        }
    }

    public boolean sacar(int numero, float valor) {
        Conta conta = contaRepository.buscar(numero);
        if (conta != null) {
            return conta.sacar(valor);
        }
        return false;
    }

    public boolean depositar(int numero, float valor) {
        Conta conta = contaRepository.buscar(numero);
        if (conta != null) {
            conta.depositar(valor);
            contaRepository.atualizar(conta);
            return true;
        }
        return false;
    }

    public boolean transferir(int numeroOrigem, int numeroDestino, float valor) {
        return servicoTransferencias.transferir(numeroOrigem, numeroDestino, valor);
    }
}
