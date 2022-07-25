import React from "react";
import Swal from "sweetalert2";
import ContentService from "../../../services/ContentService";
import useForm from "../../login-register/useForm";

const AddReview = ({ content, setContent, setUser }) => {
  const { stars, text, handleChange } = useForm({
    stars: "0",
    text: "",
  });

  const handleSubmit = (e) => {
    e.preventDefault();
    if (text) {
      ContentService.addReview(content.id, text, stars)
        .then((res) => {
          setContent({
            ...content,
            reviews: [...content?.reviews, res.data],
          });
          setUser((prevUser) => ({
            ...prevUser,
            reviews: [...prevUser?.reviews, res.data],
          }));
          Swal.fire({
            icon: "success",
            title: "Your review has been added",
            showConfirmButton: false,
            timer: 1500,
          });
        })
        .catch((err) => {
          if (err.response.status === 400) {
            Swal.fire({
              icon: "error",
              title: "Error",
              text: "You already have a review for this movie",
            });
          }
        });
    }
  };

  return (
    <div className="add-review-container">
      <p>Add your review</p>
      <form onSubmit={handleSubmit} className="form-group">
        <div className="review-input-range">
          <p>Stars:</p>
          <input
            name="stars"
            type="range"
            min="0"
            max="5"
            style={{ marginBottom: "10px" }}
            value={stars}
            onChange={handleChange}
          ></input>
          <p>{stars}</p>
        </div>
        <label className="review-input-text">
          <input
            className="form-control"
            name="text"
            type="text"
            placeholder="Write your review. Be nice"
            value={text}
            onChange={handleChange}
          ></input>
        </label>
        <button type="submit">Add review</button>
      </form>
    </div>
  );
};

export default AddReview;
