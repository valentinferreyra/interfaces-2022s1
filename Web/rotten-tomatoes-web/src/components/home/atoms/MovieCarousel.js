import { useState } from "react";
import ReactSimplyCarousel from "react-simply-carousel";
import { MovieCard } from "./MovieCard";

export const MovieCarousel = ({ contents, title }) => {
  const [activeSlideIndex, setActiveSlideIndex] = useState(0);

  if (contents.length === 0) {
    return;
  }

  return (
    <div>
      <h2 className="home-title">{title}</h2>
      <ReactSimplyCarousel
        activeSlideIndex={activeSlideIndex}
        onRequestChange={setActiveSlideIndex}
        itemsToShow={1}
        itemsToScroll={1}
        forwardBtnProps={{
          //here you can also pass className, or any other button element attributes
          style: {
            alignSelf: "center",
            background: "red",
            border: "none",
            borderRadius: "50%",
            color: "white",
            cursor: "pointer",
            fontSize: "20px",
            height: 30,
            lineHeight: 1,
            textAlign: "center",
            width: 30,
          },
          children: <span>{`>`}</span>,
        }}
        backwardBtnProps={{
          //here you can also pass className, or any other button element attributes
          style: {
            alignSelf: "center",
            background: "red",
            border: "none",
            borderRadius: "50%",
            color: "white",
            cursor: "pointer",
            fontSize: "20px",
            height: 30,
            lineHeight: 1,
            textAlign: "center",
            width: 30,
          },
          children: <span>{`<`}</span>,
        }}
        responsiveProps={[
          {
            itemsToShow: 4,
            itemsToScroll: 2,
            minWidth: 768,
          },
        ]}
        speed={400}
        easing="linear"
      >
        {contents?.map((content) => (
          <ul
            style={{ width: 300, height: 300, background: "none" }}
            key={content.id}
          >
            <li>
              <MovieCard {...content} />
            </li>
          </ul>
        ))}
      </ReactSimplyCarousel>
    </div>
  );
};
