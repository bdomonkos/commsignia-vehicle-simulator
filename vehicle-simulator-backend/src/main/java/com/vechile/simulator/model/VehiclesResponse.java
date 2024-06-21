package com.vechile.simulator.model;

import java.util.List;
import lombok.Data;

/**
 * Represents a response object containing a list of vehicles.
 */
@Data
public class VehiclesResponse {

  private List<Vehicle> vehicles;

}
