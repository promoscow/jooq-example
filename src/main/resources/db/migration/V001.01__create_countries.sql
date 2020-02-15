create table countries
(
    id              bigserial primary key,
    name            varchar(255),
    government_form varchar(255),
    population      int
);