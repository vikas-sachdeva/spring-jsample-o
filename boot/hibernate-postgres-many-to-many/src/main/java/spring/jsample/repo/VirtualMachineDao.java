package spring.jsample.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import spring.jsample.model.Vm;

@Repository
public interface VirtualMachineDao extends JpaRepository<Vm, Long> {

	
}