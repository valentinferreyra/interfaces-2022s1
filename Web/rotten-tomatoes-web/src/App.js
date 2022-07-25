import "./App.css";
import { Routes, Route } from "react-router-dom";

import Login from "./components/page-components/Login";
import Home from "./components/page-components/Home";
import Register from "./components/page-components/Register";
import CategoryPage from "./components/page-components/CategoryPage";
import ContentPage from "./components/page-components/ContentPage";
import UserPage from "./components/page-components/UserPage";
import NotFoundPage from "./components/page-components/NotFoundPage";

function App() {
  return (
    <Routes>
      <Route exact path="/login" element={<Login />} />
      <Route exact path="/register" element={<Register />} />
      <Route exact path="/user/:userId" element={<UserPage />} />
      <Route exact path="/content/:contentId" element={<ContentPage />} />
      <Route exact path="/categories/:categoryId" element={<CategoryPage />} />
      <Route exact path="/" element={<Home />} />
      <Route path="*" element={<NotFoundPage />} />
    </Routes>
  );
}

export default App;
