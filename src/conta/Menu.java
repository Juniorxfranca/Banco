package conta;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

import conta.controller.ContaController;
import conta.model.Cliente;
import conta.model.Conta;
import conta.model.ContaCorrente;
import conta.model.ContaPoupanca;
import conta.repository.ContaRepository;
import conta.repository.ContaRepositoryImpl;

public class Menu {

	public static void main(String[] args) {
		ContaRepository contaRepository = new ContaRepositoryImpl();
		ContaController contaController = new ContaController(contaRepository);
		Scanner scanner = new Scanner(System.in);
		Random random = new Random();

		while (true) {
			System.out.println("___________________________________________________________________");
			System.out.println("                                                                   ");
			System.out.println("                          PURPLE BANK                              ");
			System.out.println("                                                                   ");
			System.out.println("___________________________________________________________________");
			System.out.println("                                                                   ");
			System.out.println("                   1- Abrir Conta                                  ");
			System.out.println("                   2- Listar todas as contas                       ");
			System.out.println("                   3- Buscar conta por número                      ");
			System.out.println("                   4- Atualizar dados da conta                     ");
			System.out.println("                   5- Excluir conta                                ");
			System.out.println("                   6- Saque                                        ");
			System.out.println("                   7- Depósito                                     ");
			System.out.println("                   8- Transferência entre contas                   ");
			System.out.println("                   9- Sair                                         ");
			System.out.println("___________________________________________________________________");

			int opcao = 0;
			try {
				System.out.print("Digite a opção desejada: ");
				opcao = scanner.nextInt();
			} catch (InputMismatchException e) {
				System.out.println("Opção inválida! Digite um número.");
				scanner.next(); // Limpa o buffer
				continue;
			}

			switch (opcao) {
				case 1:
					// Abrir Conta
					System.out.println("Escolha o tipo de conta:");
					System.out.println("1- Conta Corrente");
					System.out.println("2- Conta Poupança");
					int tipoConta = scanner.nextInt();
					scanner.nextLine(); // Limpa o buffer

					int numero = contaRepository.gerarNumero(); // Gerar número aleatório
					System.out.print("Agência: ");
					int agencia = scanner.nextInt();
					scanner.nextLine(); // Limpa o buffer

					System.out.print("Nome do Cliente: ");
					String nomeCliente = scanner.nextLine();
					System.out.print("CPF do Cliente: ");
					String cpfCliente = scanner.nextLine();

					Cliente cliente = new Cliente(1, nomeCliente, cpfCliente); // ID fixo para exemplo
					Conta conta = null;

					if (tipoConta == 1) {
						System.out.print("Saldo Inicial: ");
						float saldo = scanner.nextFloat();
						System.out.print("Limite: ");
						float limite = scanner.nextFloat();
						conta = new ContaCorrente(numero, agencia, cliente, saldo, limite);
					} else if (tipoConta == 2) {
						System.out.print("Saldo Inicial: ");
						float saldo = scanner.nextFloat();
						System.out.print("Aniversário: ");
						int aniversario = scanner.nextInt();
						conta = new ContaPoupanca(numero, agencia, cliente, saldo, aniversario);
					} else {
						System.out.println("Tipo de conta inválido!");
						break;
					}

					contaController.cadastrar(conta);
					System.out.println("Conta criada com sucesso!");
					break;

				case 2:
					// Listar todas as contas
					contaController.listarTodas();
					break;

				case 3:
					// Buscar conta por número
					System.out.print("Número da Conta: ");
					int numeroBuscar = scanner.nextInt();
					contaController.buscarPorNumero(numeroBuscar);
					break;

				case 4:
					// Atualizar dados da conta
					System.out.print("Número da Conta a ser atualizada: ");
					int numeroAtualizar = scanner.nextInt();
					scanner.nextLine(); // Limpa o buffer

					// Exemplo de atualização - implementar conforme necessário
					Conta contaAtualizada = new ContaPoupanca(numeroAtualizar, 1, new Cliente(1, "Nome", "CPF"), 1000, 12);
					contaController.atualizar(numeroAtualizar, contaAtualizada);
					System.out.println("Conta atualizada com sucesso!");
					break;

				case 5:
					// Excluir conta
					System.out.print("Número da Conta a ser excluída: ");
					int numeroExcluir = scanner.nextInt();
					contaController.excluir(numeroExcluir);
					System.out.println("Conta excluída com sucesso!");
					break;

				case 6:
					// Saque
					System.out.print("Número da Conta: ");
					int numeroSaque = scanner.nextInt();
					System.out.print("Valor do Saque: ");
					float valorSaque = scanner.nextFloat();
					if (contaController.sacar(numeroSaque, valorSaque)) {
						System.out.println("Saque realizado com sucesso!");
					} else {
						System.out.println("Falha no saque!");
					}
					break;

				case 7:
					// Depósito
					System.out.print("Número da Conta: ");
					int numeroDeposito = scanner.nextInt();
					System.out.print("Valor do Depósito: ");
					float valorDeposito = scanner.nextFloat();
					if (contaController.depositar(numeroDeposito, valorDeposito)) {
						System.out.println("Depósito realizado com sucesso!");
					} else {
						System.out.println("Falha no depósito!");
					}
					break;

				case 8:
					// Transferência
					System.out.print("Número da Conta Origem: ");
					int numeroOrigem = scanner.nextInt();
					System.out.print("Número da Conta Destino: ");
					int numeroDestino = scanner.nextInt();
					System.out.print("Valor da Transferência: ");
					float valorTransferencia = scanner.nextFloat();
					if (contaController.transferir(numeroOrigem, numeroDestino, valorTransferencia)) {
						System.out.println("Transferência realizada com sucesso!");
					} else {
						System.out.println("Falha na transferência!");
					}
					break;

				case 9:
					System.out.println("Saindo...");
					scanner.close();
					return;

				default:
					System.out.println("Opção inválida! Tente novamente.");
					break;
			}
		}
	}
}
