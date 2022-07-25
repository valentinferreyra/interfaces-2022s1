import RottenTomatoesLogo from "../media/RottenTomatoesLogo";

const NotFoundPage = () => {
  return (
    <div className="container-flex not-found-page register-container">
      <header>
        <RottenTomatoesLogo />
      </header>

      <div className="not-found-text login-font">
        <p className="not-found">404</p>
        <p className="ops"> Ops!! Page Not Found</p>
      </div>
    </div>
  );
};

export default NotFoundPage;
