import java.util.List;
import java.util.Scanner;

import entities.Carro;
import entities.Moto;
import entities.Veiculo;
import services.VeiculoService;

public class CadVeiculo {
    private static Scanner scan;
    private static VeiculoService veiculoService;

    public static void main(String[] args) {
        scan = new Scanner(System.in);
        veiculoService = new VeiculoService();
        int opcao;
        do {
            System.out.print("\033[H\033[2J");
            System.out.println("Sistema de Gerenciamento de Frotas");
            System.out.println("MENU DE OPÇÕES:");
            System.out.println("Escolha uma das opções abaixo ");
            System.out.print(
                    "1 - Cadastrar novo Veículo \n2 - Listar todos os Veículos \n3 - Pesquisar veículo por placa \n4 - Remover Veículo \n0 - sair ");
            System.out.print("\nDigite a opção desejada:");

            do {
                if (scan.hasNextInt()) {
                    opcao = scan.nextInt();
                    if (opcao >= 0 && opcao <= 4)
                        break;
                }
                scan.nextLine(); // Limpar buffer do scan
                System.out.print("Digite um número dentro das opções acima: ");
            } while (true);

            scan.nextLine();
            switch (opcao) {
                case 1:
                    save();
                    break;
                case 2:
                    imprimirVeiculos();
                    break;
                case 3:
                    pesquisarVeiculoPorPlaca();
                    break;
                case 4:
                    removerVeiculo();
                    break;
                case 0:
                    System.out.println("Volte logo!");
                    break;
            }
        } while (opcao != 0);
    }

    public static void save() {
        Veiculo veiculoAdd;

        System.out.print("Qual o tipo de veículo:  1-Carro, 2-Moto: ");
        int tipoVeiculo = scan.nextInt();
        scan.nextLine();
        while (true) {
            if (tipoVeiculo != 1 && tipoVeiculo != 2) {
                System.out.println("tipo de veículo inválido!");
                break;
            }
        

        
            System.out.print("Digite a marca do veículo: ");
            String marca = scan.nextLine();
            System.out.print("Digite o modelo do veículo: ");
            String modelo = scan.nextLine();
            System.out.print("Digite o ano do veículo: ");
            int ano = scan.nextInt();
            scan.nextLine();
            if (marca == " ") {
                System.out.println("Marca inválida");
                break;
                
            }
            if (modelo == " ") {
                System.out.println("Modelo Inválido");
                break;
                
            }

            if (ano < 1900) {
                System.out.println("Ano inválido!");
                break;
            }
            System.out.print("Digite a placa do veículo: ");
            String placa = scan.nextLine();
            if (placa != " ") {
                System.out.println("Placa inválida ou não informada!");
                break;
            }

            if (tipoVeiculo == 1) {
                System.out.print("Digite o número de portas: ");
                int numeroPortas = scan.nextInt();
                scan.nextLine();
                veiculoAdd = new Carro(marca, modelo, ano, placa, numeroPortas);
            } else {
                System.out.print("A moto possui partida elétrica: 1-Sim, 2-Não:  ");
                int partidaEletrica = scan.nextInt();
                scan.nextLine();
                boolean partida = partidaEletrica == 1;
                veiculoAdd = new Moto(marca, modelo, ano, placa, partida);
            }

            try {
                veiculoService.save(veiculoAdd);
                System.out.println("Veículo cadastrado com sucesso!");
                scan.nextLine();
            } catch (Exception e) {
                System.out.println("NÃO FOI POSSÍVEL CADASTRAR O VEÍCULO!" + e.getMessage());
                System.out.println("Pressione ENTER para voltar a tela inicial.");
                scan.nextLine();
            }
        }
    }

    private static void imprimirVeiculos() {
        List<Veiculo> veiculos = veiculoService.getVeiculosDB();
        for (Veiculo veiculo : veiculos) {
            System.out.print(veiculo.getMarca() + " " + veiculo.getModelo() + " "
                    + veiculo.getAno());
            if (veiculo instanceof Carro) {
                Carro carro = (Carro) veiculo;
                System.out.println(" - Placa: " + carro.getPlaca() + " - Número de portas: " + carro.getNumeroPortas());
            } else if (veiculo instanceof Moto) {
                Moto moto = (Moto) veiculo;
                System.out.println(" - Placa: " + moto.getPlaca() + " - Partida elétrica: "
                        + (moto.getPartidaEletrica() ? "Sim" : "Não"));
            }
        }
        System.out.println("Pressione enter para continuar...");
        scan.nextLine();
    }

    private static void pesquisarVeiculoPorPlaca() {
        System.out.print("Digite a placa do veículo: ");
        String placa = scan.nextLine();

        Veiculo veiculo = veiculoService.getVeiculoByPlaca(placa);

        if (veiculo != null) {
            System.out.print(veiculo.getMarca() + " " + veiculo.getModelo() + " "
                    + veiculo.getAno());
            if (veiculo instanceof Carro) {
                Carro carro = (Carro) veiculo;
                System.out.println(" - Placa: " + carro.getPlaca() + " - Número de portas: " + carro.getNumeroPortas());
            } else if (veiculo instanceof Moto) {
                Moto moto = (Moto) veiculo;
                System.out.println(" - Placa: " + moto.getPlaca() + " - Partida elétrica: "
                        + (moto.getPartidaEletrica() ? "Sim" : "Não"));
            }
        } else {
            System.out.println("Não foi possível encontrar o veículo com placa informada!");
        }
        System.out.println("Pressione enter para continuar...");
        scan.nextLine();
    }

    public static void listarTodosVeiculos() {
        List<Veiculo> veiculos = veiculoService.getVeiculosDB();
        for (Veiculo veiculo : veiculos) {
            System.out.println(veiculo.getMarca() + " " + veiculo.getModelo() + " "
                    + veiculo.getAno());
            if (veiculo instanceof Carro) {
                Carro carro = (Carro) veiculo;
                System.out.print(" - Placa: " + carro.getPlaca() + " - Número de portas: " + carro.getNumeroPortas());
            } else if (veiculo instanceof Moto) {
                Moto moto = (Moto) veiculo;
                System.out.print(" - Placa: " + moto.getPlaca() + " - Partida elétrica: "
                        + (moto.getPartidaEletrica() ? "Sim" : "Não"));
            }
            System.out.println();
        }
    }

    public static void removerVeiculo() {
        System.out.println("Informe a placa do veículo que deseja remover: ");
        String placa = scan.nextLine();

        List<Veiculo> veiculos = veiculoService.getVeiculosDB();
        Veiculo veiculo = veiculoService.getVeiculoByPlaca(placa);

        if (veiculo != null) {
            veiculos.remove(veiculo);
            veiculoService.save(veiculos);
            System.out.println("Veículo removido com sucesso!");
        } else {
            System.out.println("Não foi possível encontrar o veículo com a placa informada.");
        }
        System.out.println("Pressione enter para continuar...");
        scan.nextLine();

    }
}