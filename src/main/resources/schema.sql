DROP TABLE IF EXISTS patterns;

CREATE TABLE patterns (
    id VARCHAR(255) PRIMARY KEY,
    name VARCHAR(255),
    type VARCHAR(255),
    use_cases VARCHAR(1000),
    description VARCHAR(1000),
    category VARCHAR(255),
    phase VARCHAR(255)
);
