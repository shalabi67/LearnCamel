DROP TABLE IF EXISTS Category;
CREATE TABLE Category(
	id int NOT NULL,
	name varchar(50) NOT NULL,
 CONSTRAINT PK_Category PRIMARY KEY
(
	id ASC
)
);

DROP TABLE IF EXISTS Orders;
CREATE TABLE Orders(
	id int NOT NULL,
	description varchar(50) NOT NULL,
	amount double default(0.0),
	processed bit default(0),
 CONSTRAINT PK_Orders PRIMARY KEY
(
	id ASC
)
);
