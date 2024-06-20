package com.vechile.simulator.model;

import java.util.List;

/**
 * Represents a response object containing a list of vehicles.
 */
public class VehiclesResponse {

  private List<Vehicle> vehicles;

  /**
   * Retrieves the list of vehicles.
   *
   * @return The list of vehicles.
   */
  public List<Vehicle> getVehicles() {
    return vehicles;
  }

  /**
   * Sets the list of vehicles.
   *
   * @param vehicles The list of vehicles to set.
   */
  public void setVehicles(List<Vehicle> vehicles) {
    this.vehicles = vehicles;
  }
}
