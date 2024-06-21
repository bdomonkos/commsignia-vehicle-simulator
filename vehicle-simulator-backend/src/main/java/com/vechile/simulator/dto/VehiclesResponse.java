package com.vechile.simulator.dto;

import com.vechile.simulator.model.Vehicle;
import java.util.List;
import lombok.Data;

/**
 * Represents a response object containing a list of vehicles.
 */
@Data
public class VehiclesResponse {

  private List<Vehicle> vehicles;

}
