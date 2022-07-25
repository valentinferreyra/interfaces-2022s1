import React, { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import ContentService from "../../services/ContentService";
import { ReviewCarousel } from "../home/atoms/ReviewCarousel";
import NavBar from "../home/NavBar";

const UserPage = () => {
  const [user, setUser] = useState(null);
  const { userId } = useParams();
  const navigate = useNavigate();

  useEffect(() => {
    ContentService.getUserById(userId)
      .then((res) => {
        setUser(res.data);
      })
      .catch((error) => {
        navigate("/notFound");
      });
  }, [userId]);

  if (!user) {
    return null;
  }

  return (
    <div>
      <NavBar />
      <div className="profile-background d-flex justify-content-center">
        <h1>{user.name}</h1>
        <img src={user.image} alt="Rotten tomatoes user" />
      </div>
      <ReviewCarousel reviews={user.reviews} />
    </div>
  );
};

export default UserPage;
