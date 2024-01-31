import React from "react";
import api from "../../services/api";
import { TextField, Button } from "@mui/material";
import Sidebar from "../Sidebar";
import "../../assets/css/styles.css";

const DodajNovPaket = () => {
  const [naziv, setNaziv] = React.useState("");
  const [cena, setCena] = React.useState("");
  const [dolzinaVezaveVDneh, setDolzinaVezaveVDneh] = React.useState("");

  const dodajPaket = () => {
    if (naziv && cena && dolzinaVezaveVDneh) {
      api.post("/paket/dodaj-paket", {
        naziv: naziv,
        cena: cena,
        dolzinaVezaveVDneh: dolzinaVezaveVDneh,
      });
    }
  };

  return (
    <div className="container">
      <Sidebar />
      <div className="content">
        <h1>Dodaj nov paket</h1>
        <div>
          <TextField
            required
            id="outlined-required"
            value={naziv}
            onChange={(event) => setNaziv(event.target.value)}
            label="Naziv"
          />
          <TextField
            required
            id="outlined-required"
            value={cena}
            onChange={(event) => setCena(event.target.value)}
            label="Cena"
            sx={{ marginLeft: 1 }}
          />
          <TextField
            required
            id="outlined-required"
            value={dolzinaVezaveVDneh}
            onChange={(event) => setDolzinaVezaveVDneh(event.target.value)}
            label="Dolzina vezave v dneh"
            sx={{ marginLeft: 1 }}
          />
        </div>
        <div style={{ marginTop: "5px" }}>
          <Button variant="contained" color="success" onClick={dodajPaket}>
            DODAJ
          </Button>
        </div>
      </div>
    </div>
  );
};

export default DodajNovPaket;
