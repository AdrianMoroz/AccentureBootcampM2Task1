package org.example.shelter;

import org.example.model.AdoptionStatus;
import org.example.model.Animal;

import java.util.ArrayList;
import java.util.List;

public class Shelter <T extends Animal>{
    private final List<T> animals = new ArrayList<>();

    public void addAnimal(T animal){
        animals.add(animal);
    }

    public List<T> getAllAnimals(){

        return animals;
    }

    public List<T> findBySpecies(String species){
        return animals.stream()
                .filter(animal -> animal.getSpecies().equals(species))
                .toList();
    }

    public List<T> findAvailableAnimals(){
        return animals.stream()
                .filter(animal -> animal.getAdoptionStatus().equals(AdoptionStatus.AVAILABLE))
                .toList();
    }

    public void markAsAdopted(String id){
        animals.stream()
                .filter(animal -> animal.getId().toString().equals(id))
                .findFirst()
                .ifPresent(animal -> animal.markAsAdopted());
    }
}
