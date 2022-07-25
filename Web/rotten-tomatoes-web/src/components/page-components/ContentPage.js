import React, { useContext, useEffect, useState } from "react";
import { Link, useNavigate, useParams } from "react-router-dom";
import { AuthContext } from "../../services/AuthContext";
import ContentService from "../../services/ContentService";
import AddReview from "../home/atoms/AddReview";
import { MovieCarousel } from "../home/atoms/MovieCarousel";
import ReviewSection from "../home/atoms/ReviewSection";
import NavBar from "../home/NavBar";

const ContentPage = () => {
  const [content, setContent] = useState(null);
  const { contentId } = useParams();
  const { user, setUser } = useContext(AuthContext);
  const navigate = useNavigate();

  const userHasAReview = (user) => {
    return user?.reviews?.some((review) => review.movie.id === content.id);
  };

  useEffect(() => {
    ContentService.getContentById(contentId)
      .then((res) => {
        setContent(res.data);
      })
      .catch((error) => {
        navigate("/notFound");
      });
  }, [contentId, navigate]);

  if (content === null) {
    return;
  }

  return (
    <div>
      <NavBar />
      <div className="movie-background">
        <img src={content.poster} alt="Movie poster" className="movie-poster" />
        <div className="movie-content">
          <h1>{content.title}</h1>
          <p>{content.description}</p>
          <br />
          <div className="content-page-categories">
            <p className="text-danger">Categories:</p>
            {content.categories.map((category) => (
              <div className="content-page-category-text" key={category.id}>
                <Link
                  to={`/categories/${category.id}`}
                  className="text-decoration-none text-black"
                >
                  <p>{category.name}</p>
                </Link>
              </div>
            ))}
          </div>
          <br />
          <p>
            Actual score: <span>{content.score}</span>
          </p>
          {user && !userHasAReview(user) && (
            <AddReview
              content={content}
              setContent={setContent}
              setUser={setUser}
            />
          )}
        </div>
      </div>
      <ReviewSection contentReviews={content.reviews} />
      <MovieCarousel
        contents={content.relatedContent}
        title="Related content"
      />
    </div>
  );
};

export default ContentPage;
