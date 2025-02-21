/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package prueba;

import java.util.Scanner;

/**
 *
 * @author david
 */
public class Prueba {

    /**
     * @param args the command line arguments
     */
    public static String similitudDeCadenas(String peticion1, String peticion2) {
        String resultado = "";
        int inicio = 0;

        for (int i = 0; i < peticion2.length(); i++) {
            if (peticion2.charAt(i) == ' ' || i == peticion2.length() - 1) {
                String palabra2;
                if (i == peticion2.length() - 1) {
                    palabra2 = peticion2.substring(inicio, i + 1);
                } else {
                    palabra2 = peticion2.substring(inicio, i);
                }

                boolean encontrada = false;
                int inicioP1 = 0;
                for (int j = 0; j < peticion1.length(); j++) {
                    if (peticion1.charAt(j) == ' ' || j == peticion1.length() - 1) {
                        String palabra1;
                        if (j == peticion1.length() - 1) {
                            palabra1 = peticion1.substring(inicioP1, j + 1);
                        } else {
                            palabra1 = peticion1.substring(inicioP1, j);
                        }

                        if (palabra1.equals(palabra2)) {
                            encontrada = true;
                            break;
                        }

                        inicioP1 = j + 1;
                    }
                }

                if (encontrada) {
                    resultado += palabra2 + " ";
                } else {
                    for (int k = 0; k < palabra2.length(); k++) {
                        resultado += "*";
                    }
                    resultado += " ";
                }

                inicio = i + 1;
            }
        }

        return resultado;
    }

    public static boolean esRotacionExacta(String original, String alterada) {
        if (original.length() != alterada.length()) {
            return false;
        }

        for (int i = 0; i < original.length(); i++) {
            boolean coincide = true;
            for (int j = 0; j < original.length(); j++) {
                if (alterada.charAt(j) != original.charAt((i + j) % original.length())) {
                    coincide = false;
                    break;
                }
            }
            if (coincide) {
                return true;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        Scanner leer = new Scanner(System.in);
        Scanner scanner = new Scanner(System.in);
        int opciones = 0;
        do {
            System.out.print("-------------MENU-------------\n"
                    + "1) Orden de compras\n"
                    + "2) Similitud de peticiones\n"
                    + "3) Es una rotacion?\n"
                    + "4) Salir\n"
                    + "Ingrese una opcion: ");
            opciones = leer.nextInt();
            switch (opciones) {
                case 1 -> {
                    String input;
                    int maiz = 0, papa = 0, cafe = 0, trigo = 0, otros = 0;
                    int cantidad = 0;
                    boolean esValida;
                    do {
                        System.out.println("Ingrese un string para procesar su orden:");
                        input = scanner.nextLine();
                        esValida = true;
                        for (int i = 0; i < input.length(); i++) {
                            char c = input.charAt(i);
                            if (!((c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z') || (c >= '0' && c <= '9'))) {
                                esValida = false;
                                break;
                            }
                        }
                        if (!esValida) {
                            System.out.println("Orden invalida, Intente otra vez");
                        }
                    } while (!esValida);
                    for (int i = 0; i < input.length(); i++) {
                        char c = input.charAt(i);
                        if (Character.isDigit(c)) {
                            cantidad = 0;
                            cantidad = cantidad * 10 + (c - '0');
                        } else {
                            switch (c) {
                                case 'm' ->
                                    maiz += cantidad;
                                case 'p' ->
                                    papa += cantidad;
                                case 'c' ->
                                    cafe += cantidad;
                                case 't' ->
                                    trigo += cantidad;
                                default ->
                                    otros += cantidad;
                            }
                        }
                    }
                    System.out.println("Orden Final:");
                    System.out.println();
                    System.out.println("Maiz x" + maiz);
                    System.out.println("Papa x" + papa);
                    System.out.println("Cafe x" + cafe);
                    System.out.println("Trigo x" + trigo);
                    System.out.println("Otros x" + otros);
                }
                case 2 -> {
                    System.out.print("Ingrese la primera peticion: ");
                    String peticion1 = scanner.nextLine();
                    System.out.print("Ingrese la segunda peticion: ");
                    String peticion2 = scanner.nextLine();

                    String cadenaSimilitudes = similitudDeCadenas(peticion1, peticion2);
                    System.out.println("similitudes de la segunda peticion: " + cadenaSimilitudes);

                    int letrasTotales = 0, asteriscosTotales = 0;

                    for (int i = 0; i < cadenaSimilitudes.length(); i++) {
                        char caracter = cadenaSimilitudes.charAt(i);
                        if (caracter != ' ') {
                            if (caracter == '*') {
                                asteriscosTotales++;
                            } else {
                                letrasTotales++;
                            }
                        }
                    }

                    double similitud = (double) letrasTotales / (letrasTotales + asteriscosTotales);
                    System.out.println("La peticiones tienen un parecido de " + similitud);

                }
                case 3 -> {

                    System.out.print("Ingrese la cadena de prueba: ");
                    String original = scanner.nextLine();
                    System.out.print("Ingrese la cadena resultante del hechizo: ");
                    String alterada = scanner.nextLine();

                    boolean esRotacion = esRotacionExacta(original, alterada);

                    if (esRotacion) {
                        System.out.println("El hechizo ha funcionado! La cadena se ha rotado");
                    } else {
                        System.out.println("El hechizo fue un fracaso! La siguiente sera");
                    }

                }
                case 4 ->
                    System.out.println("Gracias por usar este programa.");
                default ->
                    System.out.println("Opcion no valida.");
            }
        } while (opciones != 4);
    }

}
