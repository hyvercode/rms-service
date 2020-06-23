CREATE TABLE country (
      country_id   VARCHAR(36)  NOT NULL,
      country_code VARCHAR(3) NULL,
      country_name VARCHAR(30) NULL,
      active VARCHAR(1) NULL,
      created_by VARCHAR(36) NULL,
      created_at TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
      updated_by VARCHAR(36) NULL,
      updated_at TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
      PRIMARY KEY (country_id)
 );
