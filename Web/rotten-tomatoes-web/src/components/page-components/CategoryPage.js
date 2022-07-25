import React, { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import ContentService from "../../services/ContentService";
import MovieCardSection from "../home/atoms/MovieCardSection";
import NavBar from "../home/NavBar";

const CategoryPage = () => {
  const [content, setContent] = useState([]);
  const [categoryName, setCategoryName] = useState("");
  const { categoryId } = useParams();
  const navigate = useNavigate();

  useEffect(() => {
    ContentService.getCategoryById(categoryId)
      .then((res) => {
        setContent(res.data.result);
      })
      .catch((error) => {
        navigate("/notFound");
      });
    ContentService.getAllCategories()
      .then((res) => {
        const category = res.data.result.find(
          (category) => category.id === categoryId
        );
        setCategoryName(category.name);
      })
      .catch((error) => {
        navigate("/notFound");
      });
  }, [categoryId]);

  return (
    <div>
      <NavBar />
      <MovieCardSection
        mainTitle={`All movies for category ${categoryName}`}
        contents={content}
      />
    </div>
  );
};

export default CategoryPage;
