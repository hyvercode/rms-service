CREATE TABLE bank (
      bank_id   VARCHAR(5)  NOT NULL,
      country_code VARCHAR(3) NOT NULL,
      bank_name VARCHAR(30) NOT NULL,
      bank_image_link VARCHAR(2048) NULL,
      active VARCHAR(1) NOT NULL,
      created_by VARCHAR(36) NULL,
      created_at TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
      updated_by VARCHAR(36) NULL,
      updated_at TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
      PRIMARY KEY (bank_id)
 );