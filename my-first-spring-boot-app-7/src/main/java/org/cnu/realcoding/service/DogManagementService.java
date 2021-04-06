package org.cnu.realcoding.service;

import org.cnu.realcoding.domain.Dog;
import org.cnu.realcoding.exception.DogNotFoundException;
import org.cnu.realcoding.repository.DogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.cnu.realcoding.exception.DogsNotFoundException;


@Service
public class DogManagementService {

    @Autowired
    private DogRepository dogRepository;

    public void insertDog(Dog dog) {
        if(dogRepository.checkDog(dog.getName(),dog.getOwnerName(),dog.getOwnerPhoneNumber())) {
            dogRepository.insertDog(dog);
        }
    }
  
    public Dog getDogByName(String name) {
        Dog dog =  dogRepository.findDogByName(name);
        if (dog == null){
            throw new DogNotFoundException();
        }
        return dog;
    }

    public Dog getDogByOwnerName(String ownerName) {
        Dog dog = dogRepository.findDogByOwnerName(ownerName);
        if (dog == null){
            throw new DogNotFoundException();
        }
        return dog;
    }
    public Dog getDogByOwnerPhoneNumber(String ownerPhoneNumber){
        Dog dog = dogRepository.findDogByOwnerPhoneNumber(ownerPhoneNumber);
        if (dog == null){
            throw new DogNotFoundException();
        }
        return dog;
    }
    public Dog getDogByAllInfo(String name, String ownerName, String ownerPhoneNumber) {
        Dog dog = dogRepository.findDogByAllInfo(name, ownerName, ownerPhoneNumber);
        if (dog == null){
            throw new DogNotFoundException();
        }
        return dog;
    }

    public void updateDog(String name, String ownerName, String ownerPhoneNumber, String kind) {
        if(dogRepository.checkDog(name)) {
            dogRepository.updateDog(name, ownerName, ownerPhoneNumber, kind);
        }
    }

    public void updateKind(String name, String kind) {
        if(dogRepository.checkDog(name)) {
            dogRepository.updateKind(name, kind);
        }
    }

    public void addDogMedicalRecords(String name, String medicalRecords) {
        if(dogRepository.checkDog(name)) {
            dogRepository.addDogMedicalRecords(name, medicalRecords);
        }
    }
}

