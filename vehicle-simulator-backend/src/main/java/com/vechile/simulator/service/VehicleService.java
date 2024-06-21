package com.vechile.simulator.service;

import com.vechile.simulator.model.Vehicle;
import com.vechile.simulator.repository.VehicleRepository;
import jakarta.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * Service class for managing vehicles and their operations.
 */
@Service
public class VehicleService {

  @Autowired
  private VehicleRepository vehicleRepository;


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
   * @throws javax.persistence.EntityNotFoundException if no vehicle found with the given ID.
   */
  public Vehicle getVehicleById(Long id) {
    return vehicleRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Vehicle not found with id " + id));
  }

  /**
   * Finds vehicles within a specified radius from a given latitude and longitude.
   *
   * @param latitude   The latitude coordinate.
   * @param longitude  The longitude coordinate.
   * @param radiusInKm The radius in kilometers.
   * @return The list of vehicles within the specified radius.
   */
  public List<Vehicle> findVehiclesInRadius(double latitude, double longitude, double radiusInKm) {
    return vehicleRepository.findVehiclesInCircle(latitude, longitude, radiusInKm);
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
    Vehicle originalVehicle = vehicleRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Vehicle not found with id " + id));
    originalVehicle.setLatitude(vehicle.getLatitude());
    originalVehicle.setLongitude(vehicle.getLongitude());
    vehicleRepository.save(originalVehicle);
    return originalVehicle;
  }
}
