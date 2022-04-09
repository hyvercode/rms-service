CREATE TABLE village (
      village_id   VARCHAR(36)  NOT NULL,
      postcode     INTEGER NOT NULL,
      village_name VARCHAR(60) NOT NULL,
      sub_district_id VARCHAR(36) NOT NULL,
       active BOOLEAN DEFAULT true,
      created_by VARCHAR(36) NULL,
      created_at TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
      updated_by VARCHAR(36) NULL,
      updated_at TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
      PRIMARY KEY (village_id)
 );