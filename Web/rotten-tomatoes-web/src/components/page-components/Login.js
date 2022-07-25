import React, { useContext, useState } from "react";
import { Link } from "react-router-dom";
import { AuthContext } from "../../services/AuthContext";
import RottenTomatoesLogo from "../media/RottenTomatoesLogo";

const Login = () => {
  const { login } = useContext(AuthContext);
  const [email, setEmail] = useState(null);
  const [password, setPassword] = useState(null);

  const updateEmail = (e) => {
    setEmail(e.target.value);
  };

  const updatePassword = (e) => {
    setPassword(e.target.value);
  };

  const loginUser = (e) => {
    e.preventDefault();
    login(email, password);
  };

  return (
    <div className="container">
      <RottenTomatoesLogo />
      <div className="row justify-content-center pt-5 mt-5 mr-1">
        <form onSubmit={loginUser} className="col-md-4 form">
          <div className="form-group text-center form-input pt-3 mb-2">
            <h1 className="login-font fw-bold">Login</h1>
          </div>
          <div className="form-group mx-sm-4 pt-3 mb-2">
            <input
              type="text"
              className="form-control"
              onChange={updateEmail}
              placeholder="Email"
            ></input>
          </div>
          <div className="form-group mx-sm-4 mb-2">
            <input
              type="password"
              className="form-control"
              onChange={updatePassword}
              placeholder="Password"
            ></input>
          </div>
          <div className="form-group login-button">
            <input
              type="submit"
              className="btn btn-outline-danger"
              value="Login"
            ></input>
          </div>

          <div className="join-content">
            <Link to="/register">
              <span> Do you want to be a tomatoe? Join us! </span>
            </Link>
          </div>
        </form>
      </div>
    </div>
  );
};

export default Login;
