package conta.service;

public interface ServicoTransferencias {
    boolean transferir(int numeroOrigem, int numeroDestino, float valor);
}
