import axios from "axios";

axios.defaults.baseURL = "http://localhost:7070";
axios.defaults.headers.post["Content-Type"] = "application/json";

export const AuthService = {
  login: (email, password) => {
    return axios.post(`/login`, { email, password });
  },

  authenticate: (token) => {
    localStorage.setItem('token', token);
  },

  register: (values) => {
    return axios.post("/register", {
      email: values.email,
      password: values.password,
      image: values.image,
      name: values.name,
    });
  },
  getUserLoged: () =>{
    return  axios.get("/user", { headers : { Authorization : localStorage.getItem('token') } })
  }
};
