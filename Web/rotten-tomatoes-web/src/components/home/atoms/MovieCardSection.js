import React from "react";
import { MovieCard } from "./MovieCard";

const MovieCardSection = (props) => {
  return (
    <section>
      <h2 className="home-title">{props.mainTitle}</h2>
      <div className="d-flex p-2 flex-wrap gap-3">
        {props.contents?.map((content) => (
          <MovieCard {...content} key={content.id} />
        ))}
      </div>
    </section>
  );
};

export default MovieCardSection;
