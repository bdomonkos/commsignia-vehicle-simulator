package com.vechile.simulator.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents a vehicle in the simulation system.
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Vehicle {

  /**
   * The unique identifier for the vehicle.
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  /**
   * The current latitude coordinate of the vehicle's position.
   */
  private double latitude;

  /**
   * The current longitude coordinate of the vehicle's position.
   */
  private double longitude;

  /**
   * The list of notifications associated with this vehicle.
   */
  @OneToMany(mappedBy = "vehicle", targetEntity = Notification.class, cascade = CascadeType.ALL,
      fetch = FetchType.EAGER)
  @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
  private final List<Notification> notifications = new ArrayList<>();


}
