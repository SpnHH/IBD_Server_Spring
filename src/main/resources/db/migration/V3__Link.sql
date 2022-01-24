CREATE TABLE link(
    id UUID NOT NULL PRIMARY KEY,
    person1 UUID NOT NULL ,
    person2 UUID NOT NULL,
    FOREIGN KEY (person1) REFERENCES person(id),
    FOREIGN KEY (person2) REFERENCES person(id)
)