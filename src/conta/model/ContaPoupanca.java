package conta.model;

public class ContaPoupanca extends Conta {
    private int aniversario;

    public ContaPoupanca(int numero, int agencia, Cliente cliente, float saldo, int aniversario) {
        super(numero, agencia, cliente, saldo);
        this.aniversario = aniversario;
    }

    public int getAniversario() {
        return aniversario;
    }

    public void setAniversario(int aniversario) {
        this.aniversario = aniversario;
    }

    @Override
    public boolean sacar(float valor) {
        if (valor <= getSaldo()) {
            setSaldo(getSaldo() - valor);
            return true;
        }
        return false;
    }

    @Override
    public void depositar(float valor) {
        setSaldo(getSaldo() + valor);
        // Aqui você pode adicionar a lógica para aplicar juros
    }
}
