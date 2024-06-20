package com.vechile.simulator.controller;

import com.vechile.simulator.model.Vehicle;
import com.vechile.simulator.model.VehiclesResponse;
import com.vechile.simulator.service.VehicleService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller for managing vehicle-related operations. This controller handles HTTP requests
 * related to vehicles, providing endpoints for retrieving, adding, updating, and deleting vehicle
 * information.
 */
@RestController
@RequestMapping("/")
public class VehicleController {

  @Autowired
  private VehicleService vehicleService;
  @Autowired
  private SimpMessagingTemplate messagingTemplate;

  /**
   * Retrieves all vehicles available in the system.
   *
   * @return A list of all vehicles currently available.
   */
  @GetMapping("/getAllVehicles")
  public List<Vehicle> getAllVehicles() {
    return vehicleService.getAllVehicles();
  }

  /**
   * Retrieves vehicles within a specified radius based on latitude and longitude coordinates.
   *
   * @param latitude   The latitude coordinate for the center point of the search area.
   * @param longitude  The longitude coordinate for the center point of the search area.
   * @param radiusInKm The radius in kilometers to search for vehicles from the center point.
   * @return A ResponseEntity containing a VehiclesResponse with vehicles found within the specified
   * radius.
   */
  @GetMapping("/vehicles")
  public ResponseEntity<VehiclesResponse> getVehiclesInRadius(
      @RequestParam("latitude") double latitude,
      @RequestParam("longitude") double longitude,
      @RequestParam("radius") double radiusInKm) {
    // Implement logic to query vehicles within the specified radius
    List<Vehicle> vehiclesInRadius = vehicleService.findVehiclesInRadius(latitude, longitude,
        radiusInKm);

    VehiclesResponse response = new VehiclesResponse();
    response.setVehicles(vehiclesInRadius);

    return ResponseEntity.ok().body(response);
  }

  /**
   * Updates the details of a specific vehicle.
   * @param id      The ID of the vehicle to update.
   * @param vehicle The updated details of the vehicle.
   * @return The updated Vehicle object after the update operation.
   */
  @PostMapping("/vehicle/{id}")
  public Vehicle updateVehicle(@PathVariable Long id, @RequestBody Vehicle vehicle) {
    Vehicle updatedVehicle = vehicleService.updateVehicle(id, vehicle);
    messagingTemplate.convertAndSend("/topic/vehicleLocation", updatedVehicle);
    return updatedVehicle;
  }

  /**
   * Creates a new vehicle based on the provided details.
   *
   * @param vehicle The details of the vehicle to create.
   * @return The newly created Vehicle object.
   */
  @PostMapping("/vehicles")
  public Vehicle createVehicle(@RequestBody Vehicle vehicle) {
    return vehicleService.createVehicle(vehicle);
  }
}
