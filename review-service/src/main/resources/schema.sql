DROP TABLE IF EXISTS PRODUCT_REVIEWS;

CREATE TABLE PRODUCT_REVIEWS (
  product_id VARCHAR(250) PRIMARY KEY,
  average_review_score number NOT NULL,
  number_of_reviews number NOT NULL
);