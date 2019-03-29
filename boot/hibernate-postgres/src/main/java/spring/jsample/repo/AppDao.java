package spring.jsample.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import spring.jsample.model.Application;

@Repository
public interface AppDao extends JpaRepository<Application, Long> {

	
}