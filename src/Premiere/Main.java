package Premiere;

import java.util.Scanner;

public class Main {
    
    private Eleve[] eleves;
    private int effectif;
    private Scanner scanner;
    private static final String[] MATIERES = {"Math", "Physique", "Java", "Python", "C"};

    public Main() {
        scanner = new Scanner(System.in);
        initialiserEffectif();
    }

    private void initialiserEffectif() {
        System.out.print("Entrez l'effectif de la classe: ");
        effectif = scanner.nextInt();
        scanner.nextLine(); 
        eleves = new Eleve[effectif];
        enregistrerEleves();
    }

    private void enregistrerEleves() {
        for (int i = 0; i < effectif; i++) {
            System.out.println("\nEleve " + (i + 1) + ":");
            System.out.print("Nom: ");
            String nom = scanner.nextLine();
            System.out.print("Adresse: ");
            String adresse = scanner.nextLine();
            System.out.print("Date de naissance (YYYY-MM-DD): ");
            String dateStr = scanner.nextLine();
            LocalDate dateNaissance = LocalDate.parse(dateStr);

            System.out.println("Entrez les notes pour les matieres suivantes:");
            double[] notes = new double[MATIERES.length];
            for (int j = 0; j < MATIERES.length; j++) {
                System.out.print(MATIERES[j] + ": ");
                notes[j] = scanner.nextDouble();
            }
            scanner.nextLine(); 
            eleves[i] = new Eleve(nom, adresse, dateNaissance, notes);
        }
    }

    public void afficherParMerite() {
        //  moyenne décroissante de nos eleves
        for (int i = 1; i < effectif; i++) {
            Eleve key = eleves[i];
            int j = i - 1;
            while (j >= 0 && eleves[j].getMoyenne() < key.getMoyenne()) {
                eleves[j + 1] = eleves[j];
                j--;
            }
            eleves[j + 1] = key;
        }

        System.out.println("\nEleves par ordre de merite:");
        for (Eleve e : eleves) {
            e.afficher();
        }
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.afficherParMerite();
    }
}
