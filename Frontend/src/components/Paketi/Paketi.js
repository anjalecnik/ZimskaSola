import React, { useEffect, useState } from "react";
import api from "../../services/api";
import { ListItem, ListItemIcon, ListItemText } from "@mui/material";
import EastIcon from "@mui/icons-material/East";
import Sidebar from "../Sidebar";
import "../../assets/css/styles.css";

const Paketi = () => {
  const [paketi, setPaketi] = useState([]);

  useEffect(() => {
    const pridobiPakete = () => {
      const userId = sessionStorage.getItem("userId");
      api.get(`/paket`).then((response) => {
        setPaketi(response.data);
      });
    };
    pridobiPakete();
  }, []);

  function generate() {
    return paketi.map((paket, index) => (
      <ListItem key={index}>
        <ListItemIcon>
          <EastIcon />
        </ListItemIcon>
        <ListItemText primary={["Naziv: ", paket.naziv]} />
        <br />
        <ListItemText primary={["Cena: ", paket.cena]} />
      </ListItem>
    ));
  }

  return (
    <div className="container">
      <Sidebar />
      <div className="content">
        <div
          style={{ margin: "5px", display: "flex", alignItems: "flex-start" }}
        >
          <div>
            <h1>Seznam vseh paketov</h1>
            <div>{generate()}</div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Paketi;
