DROP TABLE IF EXISTS price;

CREATE TABLE price (
  brand_id INT,
  start_date TIMESTAMP NOT NULL,
  end_date TIMESTAMP NOT NULL,
  price_list SMALLINT DEFAULT 1,
  product_id INT NOT NULL,
  priority TINYINT DEFAULT 0,
  price NUMERIC(100000, 2) NOT NULL,
  curr CHAR(3) NOT NULL,
  PRIMARY KEY(brand_id, product_id, price_list)
);

CREATE INDEX idx_brand_product_date
ON price(brand_id, product_id, start_date, end_date);
