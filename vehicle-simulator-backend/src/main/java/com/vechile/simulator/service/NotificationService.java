package com.vechile.simulator.service;

import com.vechile.simulator.model.Notification;
import com.vechile.simulator.model.Vehicle;
import com.vechile.simulator.repository.NotificationRepository;
import com.vechile.simulator.repository.VehicleRepository;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * Service class for managing notifications and their association with vehicles.
 */
@Service
public class NotificationService {

  @Autowired
  private NotificationRepository notificationRepository;

  @Autowired
  private VehicleRepository vehicleRepository;

  /**
   * Adds a notification to the specified vehicle.
   *
   * @param vehicleId The ID of the vehicle.
   * @param message   The message content of the notification.
   */
  @Transactional
  public void addNotificationToVehicle(Long vehicleId, String message) {
    // Retrieve the vehicle by ID
    Vehicle vehicle = vehicleRepository.findById(vehicleId)
        .orElseThrow(() -> new EntityNotFoundException("Vehicle not found with id: " + vehicleId));

    // Create a new notification
    Notification notification = new Notification(message, vehicle);

    // Add the notification to the vehicle's list of notifications
    vehicle.getNotifications().add(notification);

    // Save the vehicle (this will cascade to save the new notification)
    vehicleRepository.save(vehicle);
  }
}
