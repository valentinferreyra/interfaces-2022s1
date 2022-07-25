import React from "react";
import { Link } from "react-router-dom";

export const ReviewCard = ({ user, texts, stars, id, movie }) => {
  return (
    <div className="card movie-card mx-2" style={{ width: "18rem" }}>
      <Link to={`/content/${movie.id}`}>
        <img className="card-img-top" src={movie.poster} alt="Movie poster" />
      </Link>
      <div className="card-body">
        <h5 className="card-title">{movie.title}</h5>
        <p className="card-text">{texts}</p>
        <p className="review-description">
          Stars: <span>{stars}</span>
        </p>
      </div>
    </div>
  );
};
