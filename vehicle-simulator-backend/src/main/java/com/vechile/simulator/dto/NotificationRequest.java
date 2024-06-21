package com.vechile.simulator.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Represents a request object containing vehicle ID and message for notifications.
 */
@Data
public class NotificationRequest {

  @JsonProperty("vehicle_id")
  private long vehicleId;
  private String message;

}
