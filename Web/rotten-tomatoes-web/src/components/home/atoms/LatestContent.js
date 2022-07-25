import React from "react";
import MovieCardSection from "./MovieCardSection";

export const LatestContent = ({ latestContent }) => {
  return (
    <MovieCardSection mainTitle="Latest Content" contents={latestContent} />
  );
};
