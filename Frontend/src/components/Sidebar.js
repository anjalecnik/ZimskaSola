import React from "react";
import "../assets/css/sidebar.css";
import { Link } from "react-router-dom";
import LogOut from "../assets/img/log-out.png";
import Logo from "../assets/img/PngItem_3944726.png";

const Sidebar = () => {
  return (
    <div
      className="sidebar"
      style={{ display: "flex", flexDirection: "column", height: "100%" }}
    >
      <img
        src={Logo}
        style={{ width: "25%", marginLeft: "10%", marginTop: "5%" }}
        className="logo"
        alt="Logo"
      />
      <Link to="/paketi" className="sidebar-link">
        Paketi
      </Link>
      <Link to="/paketi/zakupi" className="sidebar-link">
        Zakupi paket
      </Link>
      <Link
        to="/paketi/dodaj"
        className="sidebar-link"
        style={{ marginTop: "15%" }}
      >
        Dodaj paket
      </Link>
      <div style={{ marginTop: "auto", marginBottom: "10px" }}>
        {sessionStorage.getItem("userId") ? (
          <img
            src={LogOut}
            style={{
              width: "15%",
              marginRight: "5%",
              float: "right",
              cursor: "pointer",
            }}
            className="logo"
            alt="Logo"
            onClick={() => {
              sessionStorage.removeItem("userId");
              window.location.reload();
            }}
          />
        ) : (
          <Link to="/authentication" className="sidebar-link">
            Prijavi se
          </Link>
        )}
      </div>
    </div>
  );
};

export default Sidebar;
