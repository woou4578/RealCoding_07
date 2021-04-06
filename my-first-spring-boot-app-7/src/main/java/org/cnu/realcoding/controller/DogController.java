package org.cnu.realcoding.controller;

import org.cnu.realcoding.domain.Dog;
import org.cnu.realcoding.service.DogManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DogController {

    @Autowired
    private DogManagementService dogManagementService;

    @GetMapping("/dogs/name/{name}")
    public Dog getDogByName(@PathVariable("name") String name) {
        return dogManagementService.getDogByName(name);
    }
    @GetMapping("/dogs/ownerName/{ownerName}")
    public Dog getDogByOwnerName(@PathVariable ("ownerName") String ownerName) {
        return dogManagementService.getDogByOwnerName(ownerName);
    }
    @GetMapping("/dogs/ownerPhoneNumber/{ownerPhoneNumber}")
    public Dog getDogByOwnerPhoneNumber(@PathVariable ("ownerPhoneNumber") String ownerPhoneNumber) {
        return dogManagementService.getDogByOwnerPhoneNumber(ownerPhoneNumber);
    }
    @GetMapping("/dogs/{name}/{ownerName}/{ownerPhoneNumber}")
    public Dog getDogByAllInfo(@PathVariable String name, @PathVariable String ownerName,
                               @PathVariable String ownerPhoneNumber) {
        return dogManagementService.getDogByAllInfo(name, ownerName, ownerPhoneNumber);
    }

}
