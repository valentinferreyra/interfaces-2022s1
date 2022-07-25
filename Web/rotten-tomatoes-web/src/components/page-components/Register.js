import React, { useContext } from "react";
import { AuthContext } from "../../services/AuthContext";
import useForm from "../login-register/useForm";
import { Link } from "react-router-dom";
import RottenTomatoesLogo from "../media/RottenTomatoesLogo";

const Register = () => {
  const { register } = useContext(AuthContext);
  const { name, email, password, image, handleChange, formValues } = useForm({
    name: "",
    email: "",
    password: "",
    image: "",
  });

  const handleSubmit = (e) => {
    e.preventDefault();
    register(formValues);
  };

  return (
    <div className="register-container">
      <RottenTomatoesLogo />
      <div className="register-h1">
        <h1 className="login-font">Register</h1>
      </div>
      <form className="register-form" onSubmit={handleSubmit}>
        <div className="mb-3 register-mb-3">
          <label className="form-label register-label">Name</label>
          <input
            type="text"
            className="form-control register-input"
            value={name}
            name="name"
            required
            onChange={handleChange}
            placeholder="Enter Name"
          />
        </div>
        <div className="mb-3 register-mb-3">
          <label className="form-label register-label">Email address</label>
          <input
            type="email"
            className="form-control register-input"
            value={email}
            name="email"
            aria-describedby="emailHelp"
            required
            onChange={handleChange}
            placeholder="Enter Email"
          />
        </div>
        <div className="mb-3 register-mb-3">
          <label className="form-label register-label">Password</label>
          <input
            type="password"
            className="form-control register-input"
            value={password}
            name="password"
            required
            onChange={handleChange}
            placeholder="Enter password"
          />
        </div>
        <div className="mb-3 register-mb-3">
          <label className="form-label register-label">Image</label>
          <input
            type="text"
            className="form-control register-input"
            value={image}
            name="image"
            required
            onChange={handleChange}
            placeholder="Enter image"
          />
        </div>
        <div className="container-flex register-btn">
          <Link to="/login">
            <button className="btn btn-outline-primary" type="submit">
              Login
            </button>
          </Link>
          <button className="btn btn-outline-danger" type="submit">
            Register
          </button>
        </div>
      </form>
    </div>
  );
};

export default Register;
