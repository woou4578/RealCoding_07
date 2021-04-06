package org.cnu.realcoding.repository;


import org.cnu.realcoding.domain.Dog;
import org.cnu.realcoding.exception.DogsNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class Dogrepository {
    @Autowired
    private MongoTemplate mongoTemplate;

    public boolean checkDog(String name, String o_name, String number) {
        Dog dog = mongoTemplate.findOne(Query.query(Criteria.where("name").is(name)),Dog.class);
        if(dog == null ||!(dog.getOwnerPhoneNumber().equals(number) && dog.getOwnerName().equals(o_name))) {
            return true;
        }
        throw new DogsNotFoundException();
    }


    public void insertDog(Dog dog) {
        mongoTemplate.insert(dog);
    }
}
