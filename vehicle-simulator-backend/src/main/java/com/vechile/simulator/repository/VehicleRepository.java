package com.vechile.simulator.repository;

import com.vechile.simulator.model.Vehicle;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


/**
 * Repository interface for managing vehicles in the database.
 */
@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {


}
