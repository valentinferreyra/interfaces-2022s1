import { useState } from "react";
import ReactSimplyCarousel from "react-simply-carousel";
import { ReviewCard } from "./ReviewCard";

export const ReviewCarousel = ({ reviews }) => {
  const [activeSlideIndex, setActiveSlideIndex] = useState(0);

  if (reviews.length === 0) {
    return;
  }

  return (
    <div>
      <h2 className="home-title">Reviews</h2>
      <ReactSimplyCarousel
        activeSlideIndex={activeSlideIndex}
        onRequestChange={setActiveSlideIndex}
        itemsToShow={4}
        itemsToScroll={4}
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
            itemsToShow: 5,
            itemsToScroll: 4,
            minWidth: 768,
          },
        ]}
        speed={400}
        easing="linear"
      >
        {reviews?.map((review) => (
          <ReviewCard {...review} key={review.id} />
        ))}
      </ReactSimplyCarousel>
    </div>
  );
};
