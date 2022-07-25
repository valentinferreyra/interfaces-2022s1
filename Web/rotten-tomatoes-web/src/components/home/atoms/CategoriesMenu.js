import React, { useState, useEffect } from "react";
import { Link } from "react-router-dom";
import ContentService from "../../../services/ContentService";

const CategoriesMenu = () => {
  const [categories, setCategories] = useState([]);

  const updateCategories = (categories) => {
    setCategories(categories);
  };

  useEffect(() => {
    ContentService.getAllCategories().then((response) => {
      updateCategories(response.data.result);
    });
  }, []);

  return (
    <li className="nav-item dropdown">
      <p
        className="nav-link dropdown-toggle m-0"
        id="navbarDropdown"
        role="button"
        data-bs-toggle="dropdown"
        aria-expanded="false"
      >
        Categories
      </p>
      <ul className="dropdown-menu" aria-labelledby="navbarDropdown">
        {categories.map((category) => {
          return (
            <li className="dropdown-item" key={category.id}>
              <Link
                to={`/categories/${category.id}`}
                className="text-decoration-none text-danger"
              >
                {category.name}
              </Link>
            </li>
          );
        })}
      </ul>
    </li>
  );
};

export default CategoriesMenu;
