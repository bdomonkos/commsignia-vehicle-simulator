package com.vechile.simulator.controller;

import com.vechile.simulator.dto.NotificationRequest;
import com.vechile.simulator.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller for handling notifications. This controller manages HTTP requests related to
 * notifications. It provides endpoints for retrieving and adding notifications.
 */
@RestController
@RequestMapping("/")
public class NotificationController {

  /**
   * The notification service used for sending notifications.
   */
  @Autowired
  private NotificationService notificationService;

  /**
   * Adds a notification to a vehicle.
   *
   * @param notificationRequest The request object containing vehicle ID and notification message.
   * @return ResponseEntity with a success message if the notification was added successfully.
   */
  @PostMapping("/notifications")
  public ResponseEntity<String> addNotificationToVehicle(
      @RequestBody NotificationRequest notificationRequest) {
    notificationService.addNotificationToVehicle(notificationRequest.getVehicleId(),
        notificationRequest.getMessage());
    return ResponseEntity.ok("Notification added successfully");
  }
}