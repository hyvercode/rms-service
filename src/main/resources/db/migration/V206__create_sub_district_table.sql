CREATE TABLE sub_district (
      sub_district_id   VARCHAR(36)  NOT NULL,
      sub_district_code VARCHAR(10) NOT NULL,
      sub_district_name VARCHAR(60) NOT NULL,
      district_id VARCHAR(36) NULL,
      active BOOLEAN DEFAULT true,
      created_by VARCHAR(36) NULL,
      created_at TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
      updated_by VARCHAR(36) NULL,
      updated_at TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
      PRIMARY KEY (sub_district_id)
 );