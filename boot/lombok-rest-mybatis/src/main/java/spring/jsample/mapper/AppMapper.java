package spring.jsample.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

import spring.jsample.model.Application;

@Mapper
public interface AppMapper {

	@Select("SELECT * FROM application")
	List<Application> getApps();

	@Select("SELECT * FROM application WHERE id= #{id}")
	Application getAppById(@Param("id") int id);

	@Delete("DELETE FROM application WHERE id= #{id}")
	int deleteApp(@Param("id") int id);

	@Insert("INSERT INTO application (name, running) VALUES (#{name},#{running})")
	/**
	 * Below is required for returning the generated value of id column. Statement
	 * value is specific for each database. Below one will work on postgresSql
	 * database.
	 */
	@SelectKey(statement = "SELECT currval((pg_get_serial_sequence('application', 'id')))", keyProperty = "id", before = false, resultType = int.class)
	int insertApp(Application application);

	@Update("UPDATE application SET name = #{name} , running=#{running} WHERE id=#{id}")
	int updateApp(Application application);

}
