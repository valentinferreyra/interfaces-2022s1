import React, { useContext } from "react";
import { Link } from "react-router-dom";
import { AuthContext } from "../../../services/AuthContext";

const ReviewSection = ({ contentReviews }) => {
  const { user } = useContext(AuthContext);

  return (
    <div>
      <h2 className="home-title">Reviews</h2>
      <div className="user-reviews">
        {contentReviews?.map((review) => (
          <div
            className={`review-container ${
              review.user.id === user?.id ? "user-review" : ""
            }`}
            key={review.id}
          >
            <figure>
              <Link to={`/user/${review.user.id}`}>
                <img src={review.user.image} alt="Rotten tomatoes user" />
              </Link>
            </figure>
            <div className="review-description">
              <h1>{review.user.name}</h1>
              <p>{review.texts}</p>
              <br />
              <p>
                Stars: <span>{review.stars}</span>
              </p>
            </div>
          </div>
        ))}
      </div>
      )
    </div>
  );
};

export default ReviewSection;
