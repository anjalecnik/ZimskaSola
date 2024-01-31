import React, { useEffect, useState } from "react";
import api from "../../services/api";
import { ListItem, ListItemIcon, ListItemText } from "@mui/material";
import { Autocomplete, TextField, Button } from "@mui/material";
import EastIcon from "@mui/icons-material/East";
import Sidebar from "../Sidebar";
import "../../assets/css/styles.css";

const ZakupiPaket = () => {
  const [paketi, setPaketi] = useState([]);
  const [zakupljeniPaketi, setZakupljeniPaketi] = useState([]);

  const [izbraniPaket, setIzbraniPaket] = useState([]);
  const [zacetekVezave, setZacetekVezave] = useState([]);

  useEffect(() => {
    const pridobiPakete = () => {
      api.get("/paket").then((response) => {
        setPaketi(response.data);
      });
    };
    const pridobiZakupljenePakete = () => {
      const userId = sessionStorage.getItem("userId");
      api
        .get(`/narocilo/pridobi-po-id?strankaId=${userId}`)
        .then((response) => {
          setZakupljeniPaketi(response.data);
        });
    };

    pridobiZakupljenePakete();
    pridobiPakete();
  }, []);

  const zakupiPaket = () => {
    api
      .post("/narocilo/dodaj-narocilo", {
        id_paketa: izbraniPaket,
        id_stranke: sessionStorage.getItem("userId"),
        zacetekVezave: zacetekVezave,
      })
      .then((response) => {
        alert(`Cena: ${response.data.paket.cena}`);
      });
  };

  const spremembaIzbranegaPaketa = (_event, value) => {
    setIzbraniPaket(value ? value.id : null);
  };

  function generate() {
    return zakupljeniPaketi.map((paket, index) => (
      <ListItem key={index}>
        <ListItemIcon>
          <EastIcon />
        </ListItemIcon>
        <ListItemText primary={["Naziv: ", paket.paket.naziv]} />
        <br />
        <ListItemText primary={["Cena: ", paket.paket.cena]} />
        <br />
        <ListItemText
          primary={[
            "Zakupljeno do: ",
            new Date(paket.konecVezave).toLocaleString(),
          ]}
        />
      </ListItem>
    ));
  }

  return (
    <div className="container">
      <Sidebar />
      <div className="content">
        <h1>Zakupi paket</h1>
        <div>
          <Autocomplete
            id="seznam-paketov"
            options={paketi}
            getOptionLabel={(option) => option.naziv}
            filterSelectedOptions
            onChange={spremembaIzbranegaPaketa}
            renderInput={(params) => (
              <TextField
                {...params}
                label="Izberi paket"
                placeholder="Paketi"
              />
            )}
            style={{ marginTop: "15px" }}
          />
          <br />
          <TextField
            id="zacetek"
            label="Začetek vezave"
            helperText="Vpišite datum (in uro) začetka vezave npr. 2022-03-10T12:15:50"
            onChange={(event) => setZacetekVezave(event.target.value)}
          />
        </div>
        <div style={{ marginTop: "5px" }}>
          <Button variant="contained" color="success" onClick={zakupiPaket}>
            KUPI
          </Button>
        </div>
      </div>

      <div className="content">
        <h1>Zakupljeni paket</h1>
        <div>{generate()}</div>
      </div>
    </div>
  );
};

export default ZakupiPaket;
