// src/MapComponent.js
import React, { useState, useEffect } from "react";
import { MapContainer, TileLayer, Marker, Popup } from "react-leaflet";
import L from "leaflet";
import webSocketService from "../services/webSocketService";
import ListViewComponent from "./ListView";
import carIconUrl from "../images/car.png";

function MapComponent() {
  const [coordinates, setCoordinates] = useState({
    lat: 47.47581,
    lng: 19.05749,
  });
  const [selectedVehicle, setSelectedVehicle] = useState(null);
  const [vehicles, setVehicles] = useState([]);

  useEffect(() => {
    webSocketService.onVehicleLocationUpdate = (vehicle) => {
      setVehicles((prevVehicles) => {
        const updatedVehicles = prevVehicles.map((v) =>
          v.id === vehicle.id ? vehicle : v
        );
        if (!updatedVehicles.find((v) => v.id === vehicle.id)) {
          updatedVehicles.push(vehicle);
        }
        console.log(updatedVehicles);
        return updatedVehicles;
      });
    };
  }, []);

  const carMarkerIcon = L.icon({
    iconUrl: `${process.env.PUBLIC_URL}/car.png`,
    iconSize: [30, 30],
    iconAnchor: [15, 15],
  });

  return (
    <div>
      {selectedVehicle && (
        <ListViewComponent
          selectedVehicle={selectedVehicle}
        ></ListViewComponent>
      )}
      <MapContainer
        center={coordinates}
        zoom={13}
        style={{ height: "800px", width: "100%" }}
      >
        <TileLayer
          url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"
          attribution='&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
        />
        {vehicles.map((vehicle) => (
          <Marker
            key={vehicle.id}
            position={[vehicle.latitude, vehicle.longitude]}
            icon={carMarkerIcon}
          >
            <Popup>
              <p>Vehicle ID: {vehicle.id}</p>
              <p>
                Location: {vehicle.latitude}, {vehicle.longitude}
              </p>
              <button
                onClick={() => {
                  if (selectedVehicle) {
                    setSelectedVehicle(null);
                  } else {
                    setSelectedVehicle(vehicle);
                  }
                }}
                className="map-button"
              >
                {selectedVehicle ? "Close Notifications" : "Show Notifications"}
              </button>
            </Popup>
          </Marker>
        ))}
      </MapContainer>
    </div>
  );
}

export default MapComponent;
