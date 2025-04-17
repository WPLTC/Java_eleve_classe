package Premiere;

import java.time.LocalDate;
import java.time.Period;

public class Eleve {
    private String nom;
    private String adresse;
    private LocalDate dateNaissance;
    private double[] notes; // Tableau pour stocker les notes des matières
    private double moyenne;
    private static final String[] MATIERES = {"Math", "Physique", "Java", "Python", "C"};

    public Eleve(String nom, String adresse, LocalDate dateNaissance, double[] notes) {
        this.nom = nom;
        this.adresse = adresse;
        this.dateNaissance = dateNaissance;
        this.notes = notes;
        this.moyenne = calculerMoyenne();
    }

    private double calculerMoyenne() {
        double somme = 0;
        for (double note : notes) {
            somme += note;
        }
        return somme / notes.length;
    }

    public void afficher() {
        System.out.println("Nom: " + nom);
        System.out.println("Adresse: " + adresse);
        System.out.println("Date de naissance: " + dateNaissance);
        System.out.println("Âge: " + calculerAge() + " ans");
        System.out.println("Notes:");
        for (int i = 0; i < MATIERES.length; i++) {
            System.out.println("  " + MATIERES[i] + ": " + notes[i]);
        }
        System.out.println("Moyenne: " + moyenne);
        
    }

    public int calculerAge() {
        return Period.between(dateNaissance, LocalDate.now()).getYears();
    }

    public double getMoyenne() {
        return moyenne;
    }

    public String getNom() {
        return nom;
    }
}