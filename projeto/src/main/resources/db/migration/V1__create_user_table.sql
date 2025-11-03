CREATE TABLE user
(
    id    BIGINT AUTO_INCREMENT NOT NULL,
    email VARCHAR(100)          NOT NULL,
    name  VARCHAR(100)          NOT NULL,
    phone VARCHAR(20)           NOT NULL,
    CONSTRAINT `PRIMARY` PRIMARY KEY (id)
);

ALTER TABLE user
    ADD CONSTRAINT UKob8kqyqqgmefl0aco34akdtpe UNIQUE (email);