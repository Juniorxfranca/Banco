package conta.model;

public class ContaCorrente extends Conta {
    private float limite;

    public ContaCorrente(int numero, int agencia, Cliente cliente, float saldo, float limite) {
        super(numero, agencia, cliente, saldo);
        this.limite = limite;
    }

    public float getLimite() {
        return limite;
    }

    public void setLimite(float limite) {
        this.limite = limite;
    }

    @Override
    public boolean sacar(float valor) {
        if (valor <= getSaldo() + limite) {
            setSaldo(getSaldo() - valor);
            return true;
        }
        return false;
    }

    @Override
    public void depositar(float valor) {
        setSaldo(getSaldo() + valor);
    }
}
