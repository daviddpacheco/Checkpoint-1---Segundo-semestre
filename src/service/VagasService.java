package service;

import exception.VagasInsuficientesException;
import model.Carro;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Objects;
import java.util.Scanner;

public class VagasService {
    Scanner scanner = new Scanner(System.in);

    public int adicionarVagas() {
        Scanner scanner = new Scanner(System.in);
        int qtdVagas = 0;
        boolean entradaValida = false;

        while (!entradaValida) {
            System.out.print("Por favor, insira um número inteiro: ");
            try {
                qtdVagas = scanner.nextInt();
                if (qtdVagas <= 0) {
                    System.out.println("O número de vagas não pode ser igual ou menor que zero.\n");
                } else {
                    entradaValida = true;
                    return qtdVagas;
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Digite um número inteiro.");
                scanner.next();
            }
        }

        return qtdVagas;
    }

    public void printarVagasDisponiveis(int qtdVagas, ArrayList<Carro> vagasOcupadas){
        if (vagasOcupadas.size() < 1){
            System.out.println("Todas as vagas estão livres.\n" +
                    "Vagas disponíveis: " + qtdVagas + "\n");
        } else if (vagasOcupadas.size() == qtdVagas) {
            System.out.println("Todas as vagas estão ocupadas.");
        } else {
            System.out.println("Vagas disponíveis: " + (vagasOcupadas.size() - qtdVagas));
        }
    }

    public void verCarrosEstacionados(ArrayList<Carro> vagasOcupadas){
        if (vagasOcupadas.size() <= 0) {
            System.out.println("Nenhuma vaga foi ocupada.\n");
        }

        for (Carro carro : vagasOcupadas){
            System.out.println("Placa: " + carro.getPlaca() + "\n" +
                    "Marca: " + carro.getMarca() + "\n" +
                    "Modelo: " + carro.getModelo() + "\n" +
                    "Cor: " + carro.getCor());
        }
    }

    public void inserirNovoCarro(ArrayList<Carro> vagasOcupadas, int qtdVagas) throws VagasInsuficientesException {
        if (vagasOcupadas.size() >= qtdVagas){
            throw new VagasInsuficientesException();
        } else {
            System.out.print("Insira algumas informações sobre o carro.\n\n" +
                    "Marca: ");
            String marca = scanner.nextLine().toUpperCase();

            System.out.print("Modelo: ");
            String modelo = scanner.nextLine().toUpperCase();

            System.out.print("Cor: ");
            String cor = scanner.nextLine().toUpperCase();

            System.out.print("Placa: ");
            String placa = scanner.nextLine().toUpperCase();
            if (placa.length() == 7) {
                Carro carroAdicionado = new Carro(placa, modelo, marca, cor);
                vagasOcupadas.add(carroAdicionado);
            }
            else {
                System.out.println("Placa inválida! Preencha novamente o formulário.\n");
            }

        }
    }

    public void retirarCarro(ArrayList<Carro> vagasOcupadas, int qtdVagas){
        if (vagasOcupadas.size() <= 0){
            System.out.println("Não há carros para serem retirados.");
        } else {
            System.out.print("Digite a placa do carro: ");
            String placa = scanner.nextLine().toUpperCase();

            for (Carro carros : vagasOcupadas){
                if (Objects.equals(carros.getPlaca(), placa)){
                    System.out.print("O carro " + carros.getModelo() + " " + carros.getMarca() + " de placa " + carros.getPlaca() + " foi removido.");
                    vagasOcupadas.remove(carros);
                    break;
                }
            }
        }
    }

    public void gerarRecibo(ArrayList<Carro> vagasOcupadas, int qtdVagas) throws IOException {
        if (vagasOcupadas.size() <= 0){
            System.out.println("Não há carros para receber recibo.");
        } else {
            System.out.print("Digite a placa do carro: ");
            String placa = scanner.nextLine();

            for (Carro carros : vagasOcupadas){
                if (Objects.equals(carros.getPlaca(), placa)){
                    System.out.println(carros.getMarca() + "/" + carros.getModelo() + "\n\n" +
                            "valor: R$35,00 (valor fixo)");
                }
            }
        }
    }
}