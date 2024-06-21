package com.vechile.simulator.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents a notification associated with a vehicle.
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Notification {

  /**
   * The unique identifier for the notification.
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  /**
   * The vehicle associated with this notification.
   */
  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "vehicle_id")
  @JsonIgnore
  @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
  private final Vehicle vehicle;

  /**
   * The message content of the notification.
   */
  private final String message;

  /**
   * Constructs a notification with the given message and associated vehicle.
   *
   * @param message The message content of the notification.
   * @param vehicle The vehicle associated with this notification.
   */
  public Notification(String message, Vehicle vehicle) {
    this.message = message;
    this.vehicle = vehicle;
  }
}
