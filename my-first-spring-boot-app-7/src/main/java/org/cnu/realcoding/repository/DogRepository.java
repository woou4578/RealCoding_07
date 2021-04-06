package org.cnu.realcoding.repository;

import org.cnu.realcoding.domain.Dog;
import org.cnu.realcoding.exception.DogsNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DogRepository {

    @Autowired
    private MongoTemplate mongoTemplate;

    public boolean checkDog(String name, String o_name, String number) {
        Dog dog = mongoTemplate.findOne(Query.query(Criteria.where("name").is(name)),Dog.class);
        if(dog == null ||!(dog.getOwnerPhoneNumber().equals(number) && dog.getOwnerName().equals(o_name))) {
            return true;
        }
        throw new DogsNotFoundException();
    }

    public boolean checkDog(String name) {
        if(mongoTemplate.exists(Query.query(Criteria.where("name").is(name)), Dog.class))
            return true;
        throw new DogsNotFoundException();
    }


    public void insertDog(Dog dog) {
        mongoTemplate.insert(dog);
    }

    public Dog findDogByName(String name) {
        return mongoTemplate.findOne(Query.query(Criteria.where("name").is(name)),
                Dog.class);
    }
    public Dog findDogByOwnerName(String ownerName){
        return mongoTemplate.findOne(Query.query(Criteria.where("ownerName").is(ownerName)),
                Dog.class);
    }

    public Dog findDogByOwnerPhoneNumber(String ownerPhoneNumber) {
        return mongoTemplate.findOne(Query.query(Criteria.where("ownerPhoneNumber").is(ownerPhoneNumber)),
                Dog.class);
    }

    public Dog findDogByAllInfo(String name, String ownerName, String ownerPhoneNumber) {
        Dog dog1 = mongoTemplate.findOne(Query.query(Criteria.where("name").is(name)),
                Dog.class);
        Dog dog2 = mongoTemplate.findOne(Query.query(Criteria.where("ownerName").is(ownerName)),
                Dog.class);
        Dog dog3 = mongoTemplate.findOne(Query.query(Criteria.where("ownerPhoneNumber").is(ownerPhoneNumber)),
                Dog.class);
        if(dog1.equals(dog2) && dog1.equals(dog3)) {
            return dog1;
        }else{
            return null;
        }
    }

    public void updateDog(String name, String ownerName, String ownerPhoneNumber, String kind) {
        Query query = new Query();
        Update update = new Update();

        query.addCriteria(Criteria.where("name").is(name));

        update.set("kind", kind);
        update.set("ownerName", ownerName);
        update.set("ownerPhoneNumber", ownerPhoneNumber);
        mongoTemplate.updateMulti(query, update, Dog.class);
    }

    public void updateKind(String name, String kind) {
        Query query = new Query();
        Update update = new Update();

        query.addCriteria(Criteria.where("name").is(name));

        update.set("kind", kind);
        mongoTemplate.updateMulti(query, update, Dog.class);
    }

    public void addDogMedicalRecords(String name, String medicalRecords) {
        Query query = new Query();
        Update update = new Update();

        query.addCriteria(Criteria.where("name").is(name));

        update.push("medicalRecords", medicalRecords);
        mongoTemplate.updateFirst(query, update, Dog.class);
    }
}