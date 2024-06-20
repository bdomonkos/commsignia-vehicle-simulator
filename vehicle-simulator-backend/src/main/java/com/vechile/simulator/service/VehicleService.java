package com.vechile.simulator.service;

import com.vechile.simulator.model.Vehicle;
import com.vechile.simulator.repository.VehicleRepository;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityNotFoundException;
import org.locationtech.jts.geom.GeometryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;


/**
 * Service class for managing vehicles and their operations.
 */
@Service
public class VehicleService {

  @Autowired
  private VehicleRepository vehicleRepository;

  private final GeometryFactory geometryFactory = new GeometryFactory();

  /**
   * Retrieves all vehicles.
   *
   * @return The list of all vehicles.
   */
  public List<Vehicle> getAllVehicles() {
    return vehicleRepository.findAll();
  }

  /**
   * Retrieves a vehicle by its ID.
   *
   * @param id The ID of the vehicle.
   * @return The vehicle with the specified ID.
   * @throws EntityNotFoundException if no vehicle found with the given ID.
   */
  public Vehicle getVehicleById(Long id) {
    return vehicleRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Vehicle not found with id " + id));
  }


  /**
   * Creates a new vehicle.
   *
   * @param vehicle The vehicle object to be created.
   * @return The created vehicle.
   */
  public Vehicle createVehicle(Vehicle vehicle) {
    vehicle.setNotifications(new ArrayList<>());
    return vehicleRepository.save(vehicle);
  }

  /**
   * Updates an existing vehicle's coordinates.
   *
   * @param id      The ID of the vehicle to update.
   * @param vehicle The updated vehicle object.
   * @return The updated vehicle.
   */
  public Vehicle updateVehicle(Long id, Vehicle vehicle) {
    Vehicle v = vehicleRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Vehicle not found with id " + id));
    v.setLatitude(vehicle.getLatitude());
    v.setLongitude(vehicle.getLongitude());
    return vehicleRepository.save(v);
  }
}
