import React, { useContext } from "react";
import { Link } from "react-router-dom";
import { AuthContext } from "../../../services/AuthContext";

const NavBarButtons = () => {
  const { user, logOut } = useContext(AuthContext);

  return user ? (
    <div className="navbar-home">
      <ol>
        <li className="nav-item nav-link">
          <Link className="nav-link" to={`/user/${user.id}`}>
            Profile
          </Link>
        </li>
        <li className="nav-item nav-link btn-logout">
          <button className="logout-button" onClick={logOut}>
            Logout
          </button>
        </li>
      </ol>
    </div>
  ) : (
    <>
      <div>
        <ol>
          <li className="nav-item nav-link">
            <Link className="nav-link" to="/login">
              Login
            </Link>
          </li>
          <li className="nav-item nav-link btn-logout">
            <Link className="nav-link" to="/register">
              Register
            </Link>
          </li>
        </ol>
      </div>
    </>
  );
};

export default NavBarButtons;
