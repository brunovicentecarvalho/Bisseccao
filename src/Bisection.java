import java.util.*;

public class Bisection {

    private static Scanner input = new Scanner(System.in);
    private static List<Double> coeficientes = new ArrayList<>();

    private static double epsilon = 0.00000001; // Define a precisao a ser considerada pelo codigo
    public static void main(String[] args) {
        Bisection bisection = new Bisection();
        bisection.definePolinomio();
        bisection.calculaRaiz();
    }

    /**
     * Método a seguir forma o polinomio desejado da forma a pretender do estudante em questão
     */

    private void definePolinomio() {
        System.out.print("Qual o grau do polinomio ? ");
        int grau = input.nextInt();

        while (grau >= 0) {
            System.out.printf("Entre com o valor do coeficiente de grau %d :",grau);
            double value = input.nextDouble();
            coeficientes.add(value);
            grau--;
        }
    }

    /**
     * Método a seguir obtem o valor do polinomio para o valor de x passado para a função
     */

    private double function(double x) {
        double result = 0.0;
        for (int index = 0, order = coeficientes.size()-1; index < coeficientes.size(); order--, index++) {
            result += coeficientes.get(index) * (Math.pow(x, order));
        }
        return result;
    }

    /**
     * O método a seguir calcula o valor da raiz e se ela é realmente encontrada entre os dois intervalos definidos
     */

    private void calculaRaiz() {
        double a, b;
        do {
            System.out.print("Entrar com o primeiro valor do intervalo: ");
            a = input.nextDouble();
            System.out.print("Entrar com o segundo valor do intervalo: ");
            b = input.nextDouble();

            if (function(a) * function(b) >= 0) {
                System.out.println("Desculpe, a raiz não se encontra entre o intervalo, favor tentar novamente");
            }
        } while(function(a)*function(b) >= 0);
        double raiz = bisectionMethod(a, b);
        System.out.printf("A raiz encontrada é: %.6f.", raiz);
    }

    private double bisectionMethod(double comeco, double fim) {
        double meio = (comeco + fim)/2.0;;

        while (Math.abs(comeco - fim) > epsilon) {

            if ((function(comeco) * function(meio) == 0.0) || (function(fim) * function(meio) == 0.0)) {
                break;
            } else if (function(comeco) * function(meio) > 0.0) {
                comeco = meio;
            } else {
                fim = meio;
            }
            meio = (comeco + fim)/2.0;
        }
        return meio;
    }
}