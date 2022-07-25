import { createContext, useEffect, useState } from "react";
import { AuthService } from "./AuthService";
import Swal from "sweetalert2";
import { useNavigate } from "react-router-dom";

export const AuthContext = createContext({});

const AuthProvider = ({ children }) => {
  const [user, setUser] = useState(null);
  const navigate = useNavigate();

  const login = (email, password) => {
    AuthService.login(email, password)
      .then((res) => {
        AuthService.authenticate(res.headers.authorization);
        setUser(res.data);
        navigate("/");
      })
      .catch((err) => {
        if (err.response.status === 404) {
          Swal.fire({
            icon: "error",
            title: "Error",
            text: "Email or password incorrect",
          });
        } else if (err.response.status === 400) {
          Swal.fire({
            icon: "error",
            title: "Error",
            text: "Mail invalid or missing information",
          });
        } else if (err.response.status === 500) {
          Swal.fire({
            icon: "error",
            title: "Error",
            text: "Error try again after",
          });
        }
      });
  };

  const register = (values) => {
    AuthService.register(values)
      .then((res) => {
        setUser(res.data);
        navigate("/login");
      })
      .catch((err) => {
        if (err.response.status === 400) {
          Swal.fire({
            icon: "error",
            title: "Error",
            text: "Email are in use",
          });
        } else if (err.response.status === 500) {
          Swal.fire({
            icon: "error",
            title: "Error",
            text: "Ups! Something went wrong. Please try again later",
          });
        }
        if (err.response.status === 500) {
          Swal.fire({
            icon: "error",
            title: "Error",
            text: "Error try again after",
          });
        }
        if (err.response.status === 404) {
          Swal.fire({
            icon: "error",
            title: "Error",
            text: "Error not found page",
          });
        }
      });
  };

  const logOut = () => {
    setUser(null);
    localStorage.clear();
  };

  useEffect(() => {
    AuthService.getUserLoged()
      .then((res) => {
        setUser(res.data);
      })
      .catch((err) => {});
  }, []);

  return (
    <AuthContext.Provider value={{ user, login, register, logOut, setUser }}>
      {children}
    </AuthContext.Provider>
  );
};

export default AuthProvider;
