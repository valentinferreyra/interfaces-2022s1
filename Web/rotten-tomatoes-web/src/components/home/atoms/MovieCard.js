import React from "react";
import { Link } from "react-router-dom";

export const MovieCard = ({ title, description, poster, id }) => {
  return (
    <div className="card movie-card" style={{ width: "18rem" }}>
      <Link to={`/content/${id}`}>
        <img className="card-img-top" src={poster} alt="Movie poster" />
      </Link>
      <div className="card-body">
        <h5 className="card-title">{title}</h5>
        <p className="card-text">{description}</p>
      </div>
    </div>
  );
};
