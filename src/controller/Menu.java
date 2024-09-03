package controller;

import exception.VagasInsuficientesException;
import model.Carro;
import service.VagasService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
    Scanner scanner = new Scanner(System.in);
    VagasService vagasService = new VagasService();
    private int qtdVagas;

    public void iniciar() throws VagasInsuficientesException, IOException {
        System.out.print("!Bem vindo ao sistema de estacionamento!\n" +
                "Digite quantas vagas estão disponíeveis: ");

        boolean continuarCodigo = false;
        while (!continuarCodigo) {
            qtdVagas = vagasService.adicionarVagas();
            continuarCodigo = true;
        }

        ArrayList<Carro> vagasOcupadas = new ArrayList<>(qtdVagas);

        continuarCodigo = false;
        while (!continuarCodigo) {
            System.out.print("Escolha uma das opções\n\n" +
                    "1 - Ver vagas disponíveis\n" +
                    "2 - Ver carros estacionados\n" +
                    "3 - Inserir novo carro\n" +
                    "4 - Retirar carro\n" +
                    "5 - Gerar recibo\n" +
                    "6 - Sair\n\n" +
                    "Digite aqui o número da sua opção: ");

            String opcaoEscolhida = scanner.nextLine();
            switch (opcaoEscolhida) {
                case "1":
                    vagasService.printarVagasDisponiveis(qtdVagas, vagasOcupadas);
                    break;

                case "2":
                    vagasService.verCarrosEstacionados(vagasOcupadas);
                    break;

                case "3":
                    vagasService.inserirNovoCarro(vagasOcupadas, qtdVagas);
                    break;

                case "4":
                    vagasService.retirarCarro(vagasOcupadas, qtdVagas);
                    break;

                case "5":
                    vagasService.gerarRecibo(vagasOcupadas, qtdVagas);
                    break;

                case "6":
                    System.out.println("Saindo do sistema...");
                    continuarCodigo = true;
                    break;

                default:
                    System.out.println("Opção inválida!\n");
                    break;
            }
        }
    }
}
