package org.cnu.realcoding.service;

import lombok.Getter;
import org.cnu.realcoding.domain.Dog;
import org.cnu.realcoding.exception.DogsNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DogManagementService {

    @Getter
    private List<Dog> dogs = new ArrayList<>();

    public void insertDog(Dog dog) {
        dogs.add(dog);
    }

    public Dog getDogByName(String name) {
        for (Dog dog : dogs) {
            if (dog.getName().equals(name)) {
                return dog;
            }
        }
        throw new DogsNotFoundException();
    }

    public boolean CheckDogData(Dog NewDog) {
        for(Dog dog:dogs) {
            if(dog.getName().equals(NewDog.getName()) && dog.getOwnerName().equals(NewDog.getOwnerName()) &&
                    dog.getOwnerPhoneNumber().equals(NewDog.getOwnerPhoneNumber())) {
                throw new DogsNotFoundException();
            }
        }
        return true;
    }


}
