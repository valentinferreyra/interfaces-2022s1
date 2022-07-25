import { useContext, useEffect, useState } from "react";
import { AuthContext } from "../../services/AuthContext";
import ContentService from "../../services/ContentService";
import { LatestContent } from "../home/atoms/LatestContent";
import { MovieCarousel } from "../home/atoms/MovieCarousel";
import NavBar from "../home/NavBar";

const Home = () => {
  const [latestContent, setLatestContent] = useState([]);
  const [contentTop, setContentTop] = useState([]);
  const { user } = useContext(AuthContext);

  useEffect(() => {
    ContentService.getLatestContent().then((res) => {
      setLatestContent(res.data);
    });
    ContentService.getContentTop().then((res) => {
      setContentTop(res.data);
    });
  }, []);

  return (
    <div className="home-page">
      <NavBar />
      {user && <h2 className="home-title">Welcome, {user.name}!</h2>}
      <MovieCarousel contents={contentTop} title="Top content of the week" />
      <LatestContent latestContent={latestContent} />
    </div>
  );
};

export default Home;
