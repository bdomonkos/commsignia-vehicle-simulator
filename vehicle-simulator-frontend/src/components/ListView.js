// ListViewComponent.js
import React from "react";

const ListViewComponent = ({ selectedVehicle }) => {
  return (
    <div style={{ maxHeight: "400px", overflowY: "scroll" }}>
      <h2>List View</h2>
      <ul>
        {selectedVehicle.notifications.map((notification) => (
          <li key={notification.id}>
            <strong>Notification ID:</strong> {notification.id}
            <br />
            <strong>message:</strong> {notification.message}
          </li>
        ))}
      </ul>
    </div>
  );
};

export default ListViewComponent;
