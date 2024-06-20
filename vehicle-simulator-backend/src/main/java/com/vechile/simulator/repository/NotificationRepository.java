package com.vechile.simulator.repository;

import com.vechile.simulator.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * Repository interface for managing notifications in the database.
 */
@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {

}
