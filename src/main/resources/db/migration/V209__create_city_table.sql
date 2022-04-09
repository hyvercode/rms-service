CREATE TABLE city (
      city_id   VARCHAR(36)  NOT NULL,
      city_code VARCHAR(10) NOT NULL,
      city_name VARCHAR(60) NOT NULL,
      country_code VARCHAR(3) NULL,
       active BOOLEAN DEFAULT true,
      created_by VARCHAR(36) NULL,
      created_at TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
      updated_by VARCHAR(36) NULL,
      updated_at TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
      PRIMARY KEY (city_id)
 );