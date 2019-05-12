package spring.jsample.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import spring.jsample.model.App;

@Repository
public interface ApplicationDao extends JpaRepository<App, Long> {

	
}