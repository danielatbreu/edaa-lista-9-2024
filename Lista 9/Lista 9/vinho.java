import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class vinho {
    public static void main(String[] args) {
        // Nome do arquivo CSV
        String csvFile = "drinks.csv";

        try {
            // Cria um leitor de arquivo
            BufferedReader reader = new BufferedReader(new FileReader(csvFile));
            String line;

            // Lista para armazenar os países e seus consumos de vinho
            List<paisVinho> vinhoList = new ArrayList<>();

            // Pula o cabeçalho
            reader.readLine();

            // Lê o arquivo linha por linha
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String country = parts[0];
                double wineLiters = Double.parseDouble(parts[3]); // Índice 3 é o consumo de vinho
                vinhoList.add(new paisVinho(country, wineLiters));
            }

            // Fecha o leitor
            reader.close();

            // Ordena a lista pelo consumo de vinho
            vinhoList.sort(Comparator.comparingDouble(paisVinho::getWineLiters));

            // Imprime os países ordenados pelo consumo de vinho
            System.out.println("Consumo de vinho por país em 2010:");
            for (paisVinho vinho : vinhoList) {
                System.out.printf("%s: %.2f litros\n", vinho.getCountry(), vinho.getWineLiters());
            }

            // Encontra o país que mais consome vinho
            paisVinho maxWineCountry = vinhoList.get(vinhoList.size() - 1);
            System.out.println("\nPaís que mais consome vinho: " + maxWineCountry.getCountry());
            System.out.println("Quantidade: " + maxWineCountry.getWineLiters() + " litros");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

// Classe para representar o país e seu consumo de vinho
class paisVinho {
    private String country;
    private double wineLiters;

    public paisVinho(String country, double wineLiters) {
        this.country = country;
        this.wineLiters = wineLiters;
    }

    public String getCountry() {
        return country;
    }

    public double getWineLiters() {
        return wineLiters;

    }

}