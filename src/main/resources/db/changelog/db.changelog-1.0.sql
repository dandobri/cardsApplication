--liquibase formatted sql

--changeset dandobr:1

CREATE TABLE users
(
    id       SERIAL PRIMARY KEY,
    email    VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    role     VARCHAR(20)  NOT NULL
);

CREATE TABLE cards
(
    id                    SERIAL PRIMARY KEY,
    encrypted_card_number TEXT           NOT NULL,
    last4_card_digits     VARCHAR(4)     NOT NULL,
    expiry_date           DATE           NOT NULL,
    status                VARCHAR(20)    NOT NULL,
    balance               NUMERIC(19, 4) NOT NULL,
    card_holder_id        BIGINT         NOT NULL,
    limit_money_to_spend  NUMERIC(19, 4) NOT NULL,
    CONSTRAINT fk_user FOREIGN KEY (card_holder_id) REFERENCES users(id)
);


CREATE TABLE transactions
(
    id          SERIAL   PRIMARY KEY,
    amount      NUMERIC(19, 4) NOT NULL,
    type        VARCHAR(20)    NOT NULL,
    time_stamp  TIMESTAMP      NOT NULL,
    description TEXT,
    card_id     BIGINT,
    CONSTRAINT fk_card FOREIGN KEY (card_id) REFERENCES cards(id)
);



