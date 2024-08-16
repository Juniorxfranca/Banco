package conta.service;

import conta.model.Conta;
import conta.repository.ContaRepository;

public class ServicoTransferenciasImpl implements ServicoTransferencias {

    private ContaRepository contaRepository;

    // Construtor
    public ServicoTransferenciasImpl(ContaRepository contaRepository) {
        this.contaRepository = contaRepository;
    }

    @Override
    public boolean transferir(int numeroOrigem, int numeroDestino, float valor) {
        // Verificar se as contas existem
        Conta contaOrigem = contaRepository.buscar(numeroOrigem);
        Conta contaDestino = contaRepository.buscar(numeroDestino);

        if (contaOrigem != null && contaDestino != null) {
            // Realizar o saque na conta origem
            boolean saqueRealizado = contaOrigem.sacar(valor);

            if (saqueRealizado) {
                // Realizar o depósito na conta destino
                contaDestino.depositar(valor);

                // Atualizar as contas no repositório
                contaRepository.atualizar(contaOrigem);
                contaRepository.atualizar(contaDestino);

                return true;
            }
        }

        return false;
    }
}
