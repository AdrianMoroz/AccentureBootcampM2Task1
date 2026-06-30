package org.example.menu;

import org.example.model.*;
import org.example.shelter.Shelter;

import java.util.ArrayList;
import java.util.List;

import java.util.Scanner;

public class ConsoleMenu {
    private final Shelter<Animal> shelter;
    private final Scanner scanner = new Scanner(System.in);
    private final List<MenuOption> menuOptions = generateMenuOptions();

    public ConsoleMenu(Shelter<Animal> shelter) {
        this.shelter = shelter;
    }

    public void start() {

        while (true) {
            printMenu(menuOptions);
            int option = getInput();
            switch (option) {
                case 1:
                    addAnimal();
                    break;
                case 2:
                    listAllAnimals();
                    break;
                case 3:
                    findAnimalsBySpecies();
                    break;
                case 4:
                    listAvailableAnimals();
                    break;
                case 5:
                    markAnimalAsAdopted();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Please choose a valid option");
            }
        }


    }

    private void markAnimalAsAdopted() {
        System.out.print("Enter animal ID: ");
        String id = scanner.nextLine().trim();
        shelter.markAsAdopted(id);
        System.out.println("Animal marked as adopted");
    }

    private void listAvailableAnimals() {
        List<Animal> animals = shelter.findAvailableAnimals();

        if (animals.isEmpty()) {
            System.out.println("There are no available animals.");
            return;
        }

        animals.forEach(System.out::println);
    }
    private void findAnimalsBySpecies() {
        System.out.print("Enter species (Dog, Cat, Bird): ");
        String species = scanner.nextLine().trim();

        List<Animal> animals = shelter.findBySpecies(species);

        if (animals.isEmpty()) {
            System.out.println("No animals found for that species.");
            return;
        }

        animals.forEach(System.out::println);
    }

    private void listAllAnimals() {


        List<Animal> animals = shelter.getAllAnimals();

        if (animals.isEmpty()) {
            System.out.println("No animals found for that species.");
            return;
        }

        animals.forEach(System.out::println);
        shelter.getAllAnimals().forEach(System.out::println);
    }

    private void addAnimal() {
        System.out.print("Enter species (Dog, Cat, Bird): ");
        String species = scanner.nextLine().trim();

        System.out.print("Enter animal name: ");
        String name = scanner.nextLine().trim();

        System.out.print("Enter animal age: ");
        int age = Integer.parseInt(scanner.nextLine());

        Animal animal;

        switch (species.toLowerCase()) {
            case "Dog" -> animal = new Dog(new AnimalId(), name, age);
            case "Cat" -> animal = new Cat(new AnimalId(), name, age);
            case "Bird" -> animal = new Bird(new AnimalId(), name, age);
            default -> {
                System.out.println("Unknown animal species.");
                return;
            }
        }

        shelter.addAnimal(animal);

        System.out.println("Animal added successfully:");
        System.out.println(animal);
    }

    private int getInput() {
        System.out.print("Choose an option: ");

        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException exception) {
            return -1;
        }
    }

    private List<MenuOption> generateMenuOptions() {
        List<MenuOption> menuOptionList = new ArrayList<>();
        menuOptionList.add(new MenuOption(1, "Add animal"));
        menuOptionList.add(new MenuOption(2, "List all animals"));
        menuOptionList.add(new MenuOption(3, "Find animals by species"));
        menuOptionList.add(new MenuOption(4, "List available animals"));
        menuOptionList.add(new MenuOption(5, "Mark animal as adopted"));
        menuOptionList.add(new MenuOption(0, "Exit"));
        return menuOptionList;
    }

    private void printMenu(List<MenuOption> menuOptions) {
        for (MenuOption option : menuOptions) {
            System.out.println(option.number() + ". " + option.label());
        }
    }
}
