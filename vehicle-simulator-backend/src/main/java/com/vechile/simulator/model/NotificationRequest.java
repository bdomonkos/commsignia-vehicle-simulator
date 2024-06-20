package com.vechile.simulator.model;

import lombok.Data;

/**
 * Represents a request object containing vehicle ID and message for notifications.
 */
@Data
public class NotificationRequest {

  private long vehicle_id;
  private String message;

}
