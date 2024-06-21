import React from "react";

const ListViewComponent = ({ selectedVehicle }) => {
  return (
    <div style={{ maxHeight: "400px", overflowY: "scroll" }}>
      <h2>Notifications for the selected vehicle</h2>
      <ul style={{ listStyleType: "none", padding: 0 }}>
        {selectedVehicle.notifications.map((notification) => (
          <li key={notification.id}>
            <strong>Notification ID:</strong> {notification.id}
            <br />
            <strong>Message:</strong> {notification.message}
          </li>
        ))}
      </ul>
    </div>
  );
};

export default ListViewComponent;
