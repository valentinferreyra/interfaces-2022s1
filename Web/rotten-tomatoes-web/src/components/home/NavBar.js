import React from "react";
import { Link } from "react-router-dom";
import rottenTomatoesLogo from "../../img/rotten-tomatoes-logo.png";
import CategoriesMenu from "./atoms/CategoriesMenu";
import NavBarButtons from "./atoms/NavBarButtons";

const NavBar = () => {
  return (
    <nav className="navbar-home align-items-center">
      <div className="navbar-icon-container">
        <Link to="/">
          <img
            src={rottenTomatoesLogo}
            className="img-fluid navbar-icon"
            alt="Rotten Tomatoes Logo"
          />
        </Link>
      </div>
      <ol className="align-items-center">
        <li>
          <Link to="/" className="text-decoration-none">
            Home
          </Link>
        </li>
        <CategoriesMenu />
        <NavBarButtons />
      </ol>
    </nav>
  );
};

export default NavBar;
