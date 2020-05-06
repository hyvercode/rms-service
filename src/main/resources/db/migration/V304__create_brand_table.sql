CREATE TABLE brand (
   brand_id VARCHAR(36) NOT NULL,
   brand_name VARCHAR(60) NOT NULL,
   outlet_id VARCHAR(36) NOT NULL,
   remark VARCHAR(120) NULL,
   active VARCHAR(1) NOT NULL,
   created_by VARCHAR(36) NULL,
   created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
   updated_by VARCHAR(36) NULL,
   updated_at TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
   PRIMARY KEY (brand_id)
);