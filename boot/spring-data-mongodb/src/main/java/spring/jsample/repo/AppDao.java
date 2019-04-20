package spring.jsample.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import spring.jsample.model.Application;

@Repository
public interface AppDao extends MongoRepository<Application, String> {

}