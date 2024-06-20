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


  /**
   * Retrieves vehicles within a specified radius from a given latitude and longitude.
   *
   * @param latitude   The latitude coordinate.
   * @param longitude  The longitude coordinate.
   * @param radiusInKm The radius in kilometers.
   * @return The list of vehicles within the specified radius.
   */

  @Query(value = "SELECT * \n"
      + "FROM vehicle v \n"
      + "WHERE ST_Distance(\n"
      + "cast(ST_SetSRID(ST_MakePoint(v.latitude, v.longitude), 4326) as geography), \n"
      + "cast(ST_SetSRID(ST_MakePoint(:latitude, :longitude), 4326) as geography)\n"
      + ") < (:radius * 1000)", nativeQuery = true)
  List<Vehicle> findVehiclesInCircle(
      @Param("latitude") double latitude,
      @Param("longitude") double longitude,
      @Param("radius") double radiusInKm
  );
}
