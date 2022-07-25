import React from "react";
import { Link } from "react-router-dom";
import rottenTomatoesLogo from "../../img/rotten-tomatoes-logo-v2.png";

const RottenTomatoesLogo = () => {
  return (
    <Link to="/">
      <img
        className="register-logo"
        src={rottenTomatoesLogo}
        alt="Rotten tomatoes Logo"
      />
    </Link>
  );
};

export default RottenTomatoesLogo;
