CREATE TABLE currency (
      currency_id   VARCHAR(3)  NOT NULL,
      currency_name VARCHAR(30) NOT NULL,
      currency_symbol VARCHAR(5) NULL,
       active BOOLEAN DEFAULT true,
      created_by VARCHAR(36) NULL,
      created_at TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
      updated_by VARCHAR(36) NULL,
      updated_at TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
      PRIMARY KEY (currency_id)
 );