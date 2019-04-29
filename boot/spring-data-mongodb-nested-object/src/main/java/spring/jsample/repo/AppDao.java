package spring.jsample.repo;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import spring.jsample.model.Application;
import spring.jsample.model.Sensor;

@Repository
public interface AppDao extends MongoRepository<Application, String> {

	Page<Application> findByNameIn(List<String> names, Pageable pageable);

	Page<Application> findByNameInAndRunning(List<String> names, boolean running, Pageable pageable);

	/*
	 * The $elemMatch operator matches documents that contain an array field with at
	 * least one element that matches all the specified query criteria.
	 */
	@Query("{ sensors : { $elemMatch : { type : '?0' } } }")
	List<Application> findBySensorType(Sensor.Type sensortype);

}