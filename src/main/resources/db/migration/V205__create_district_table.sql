CREATE TABLE district (
      district_id   VARCHAR(36)  NOT NULL,
      district_code VARCHAR(10) NOT NULL,
      district_name VARCHAR(60) NOT NULL,
      province_code VARCHAR(10) NULL,
      active VARCHAR(1) NOT NULL,
      created_by VARCHAR(36) NULL,
      created_at TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
      updated_by VARCHAR(36) NULL,
      updated_at TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
      PRIMARY KEY (district_id)
 );