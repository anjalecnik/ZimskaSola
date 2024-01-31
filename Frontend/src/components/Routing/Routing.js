import React from "react";
import { Routes } from "react-router-dom";
import { Route } from "react-router";
import PageNotFound from "../PageNotFound/PageNotFound";
import Paketi from "../Paketi/Paketi";
import ZakupiPaket from "../Paketi/ZakupiPaket";
import Authentiction from "../Authentication/Authentication";
import DodajNovPaket from "../Paketi/DodajNovPaket";

export default function Routing() {
  return (
    <Routes>
      <Route path="authentication" element={<Authentiction />} />

      <Route path="paketi" element={<Paketi />} />
      <Route path="paketi/zakupi" element={<ZakupiPaket />} />

      <Route path="paketi/dodaj" element={<DodajNovPaket />} />

      <Route path="*" element={<PageNotFound />} />
    </Routes>
  );
}
