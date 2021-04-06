package org.cnu.realcoding.service;

import lombok.Getter;
import org.cnu.realcoding.domain.Dog;
import org.cnu.realcoding.exception.DogsNotFoundException;
import org.cnu.realcoding.repository.Dogrepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DogManagementService {
    @Autowired
    Dogrepository dogRepository;


    public void insertDog(Dog dog) {
        if(!dogRepository.checkDog(dog.getName(),dog.getOwnerName(),dog.getOwnerPhoneNumber())) {
            dogRepository.insertDog(dog);
        }
    }



}
