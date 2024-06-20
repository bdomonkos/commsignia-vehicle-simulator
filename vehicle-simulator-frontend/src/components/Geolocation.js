// src/components/Geolocation.js
import React, { useState } from "react";

const Geolocation = () => {
  const [location, setLocation] = useState({ lat: null, lng: null });
  const [error, setError] = useState(null);

  const getLocation = () => {
    if (navigator.geolocation) {
      navigator.geolocation.getCurrentPosition(
        (position) => {
          setLocation({
            lat: position.coords.latitude,
            lng: position.coords.longitude,
          });
          setError(null);
        },
        (err) => {
          setError(err.message);
        }
      );
    } else {
      setError("Geolocation is not supported by this browser.");
    }
  };

  return (
    <div>
      <h2>Geolocation</h2>
      <button onClick={getLocation}>Get Current Location</button>
      {error && <p>Error: {error}</p>}
      {location.lat && location.lng && (
        <div>
          <p>Latitude: {location.lat}</p>
          <p>Longitude: {location.lng}</p>
        </div>
      )}
    </div>
  );
};

export default Geolocation;
