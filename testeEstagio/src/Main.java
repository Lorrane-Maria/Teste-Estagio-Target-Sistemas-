import java.util.Scanner;
import java.io.IOException;
import java.io.FileReader;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            // Exibir o menu
            System.out.println("Escolha uma opcao:");
            System.out.println("1 - Calcular soma de 1 a 13");
            System.out.println("2 - Verificar se um numero pertence a sequencia de Fibonacci");
            System.out.println("3 - Analisar faturamento diario");
            System.out.println("4 - Calcular percentual de representacao por estado");
            System.out.println("5 - Inverter uma string");
            System.out.println("0 - Sair");

            System.out.print("Digite sua escolha: ");
            int escolha = scanner.nextInt();
            scanner.nextLine();  // Consumir a quebra de linha

            switch (escolha) {
                case 1:
                    calcularSoma();
                    break;
                case 2:
                    verificarFibonacci(scanner);
                    break;
                case 3:
                    analisarFaturamento();
                    break;
                case 4:
                    calcularPercentualPorEstado();
                    break;
                case 5:
                    inverterString(scanner);
                    break;
                case 0:
                    System.out.println("Saindo...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Opcao invalida.");
                    break;
            }
        }
    }

    public static void calcularSoma() {
        int INDICE = 13, SOMA = 0, K = 0;

        while (K < INDICE) {
            K = K + 1;
            SOMA = SOMA + K;
        }

        System.out.println("O valor da variavel SOMA é: " + SOMA);
    }

    public static void verificarFibonacci(Scanner scanner) {
        System.out.print("Digite um numero para verificar se pertence a sequencia de Fibonacci: ");
        int numero = scanner.nextInt();

        if (isFibonacci(numero)) {
            System.out.println("O numero " + numero + " pertence a sequencia de Fibonacci.");
        } else {
            System.out.println("O numero " + numero + " NaO pertence a sequencia de Fibonacci.");
        }
    }

    public static boolean isFibonacci(int n) {
        int a = 0, b = 1;
        while (a <= n) {
            if (a == n) {
                return true;
            }
            int c = a + b;
            a = b;
            b = c;
        }
        return false;
    }

    public static void analisarFaturamento() {
        String filePath = "src/dados.json"; // Caminho relativo para o arquivo JSON

        JSONParser parser = new JSONParser();

        try (FileReader reader = new FileReader(filePath)) {
            // Parse o arquivo JSON
            Object obj = parser.parse(reader);
            JSONArray jsonArray = (JSONArray) obj;

            // Processar o JSONArray
            double soma = 0;
            double menor = Double.MAX_VALUE;
            double maior = Double.MIN_VALUE;
            int diasComFaturamento = 0;

            for (Object o : jsonArray) {
                JSONObject jsonObject = (JSONObject) o;
                double valor = ((Number) jsonObject.get("valor")).doubleValue();

                if (valor > 0) {
                    soma += valor;
                    diasComFaturamento++;
                    if (valor < menor) {
                        menor = valor;
                    }
                    if (valor > maior) {
                        maior = valor;
                    }
                }
            }

            double media = soma / diasComFaturamento;

            System.out.println("Menor valor de faturamento: R$ " + menor);
            System.out.println("Maior valor de faturamento: R$ " + maior);
            System.out.println("Numero de dias com faturamento acima da média: R$" + media);

        } catch (IOException | ParseException e) {
            System.out.println("Erro ao processar o arquivo JSON: " + e.getMessage());
        }
    }

    public static void calcularPercentualPorEstado() {
        double sp = 67836.43;
        double rj = 36678.66;
        double mg = 29229.88;
        double es = 27165.48;
        double outros = 19849.53;

        double total = sp + rj + mg + es + outros;

        System.out.printf("SP: %.2f%%\n", (sp / total) * 100);
        System.out.printf("RJ: %.2f%%\n", (rj / total) * 100);
        System.out.printf("MG: %.2f%%\n", (mg / total) * 100);
        System.out.printf("ES: %.2f%%\n", (es / total) * 100);
        System.out.printf("Outros: %.2f%%\n", (outros / total) * 100);
    }

    public static void inverterString(Scanner scanner) {
        System.out.print("Digite uma string para inverter: ");
        String original = scanner.nextLine();

        String invertida = "";
        for (int i = original.length() - 1; i >= 0; i--) {
            invertida += original.charAt(i);
        }

        System.out.println("String invertida: " + invertida);
    }
}