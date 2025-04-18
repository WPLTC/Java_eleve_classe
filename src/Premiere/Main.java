package Premiere;

import java.util.Scanner;
import java.time.LocalDate;

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

            System.out.println("Entrez les notes pour les matières suivantes:");
            double[] notes = new double[MATIERES.length];
            for (int j = 0; j < MATIERES.length; j++) {
                System.out.print(MATIERES[j] + ": ");
                notes[j] = scanner.nextDouble();
            }
            scanner.nextLine();

            eleves[i] = new Eleve(nom, adresse, dateNaissance, notes);
        }
    }

    private double calculerMoyenneClasse() {
        double somme = 0;
        for (Eleve eleve : eleves) {
            somme += eleve.getMoyenne();
        }
        return effectif > 0 ? somme / effectif : 0;
    }

    private void compterElevesParMoyenne() {
        double moyenneClasse = calculerMoyenneClasse();
        int auDessus = 0;
        int enDessous = 0;
        int egal = 0;

        for (Eleve eleve : eleves) {
            double moyenneEleve = eleve.getMoyenne();
            if (moyenneEleve > moyenneClasse) {
                auDessus++;
            } else if (moyenneEleve < moyenneClasse) {
                enDessous++;
            } else {
                egal++;
            }
        }

        System.out.println("Moyenne de la classe: " + moyenneClasse);
        System.out.println("Nombre d'Eleves au-dessus de la moyenne: " + auDessus);
        System.out.println("Nombre d'Eleves en dessous de la moyenne: " + enDessous);
        if (egal > 0) {
            System.out.println("Nombre d'Eleves avec la moyenne exacte: " + egal);
        }
    }

    public void afficherParMerite() {
        for (int i = 1; i < effectif; i++) {
            Eleve key = eleves[i];
            int j = i - 1;
            while (j >= 0 && eleves[j].getMoyenne() < key.getMoyenne()) {
                eleves[j + 1] = eleves[j];
                j--;
            }
            eleves[j + 1] = key;
        }

        System.out.println("\nEleves par ordre de mérite:");
        for (Eleve e : eleves) {
            e.afficher();
        }

        compterElevesParMoyenne();
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.afficherParMerite();
        main.scanner.close();
    }
}