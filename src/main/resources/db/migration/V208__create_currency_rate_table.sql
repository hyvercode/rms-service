CREATE TABLE currency_rate (
      currency_rate_id   VARCHAR(36)  NOT NULL,
      date_rate TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
      currency_id VARCHAR(3) NOT NULL,
      currency_rate DECIMAL(15,3) DEFAULT 0,
      active VARCHAR(1) NOT NULL,
      created_by VARCHAR(36) NULL,
      created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
      updated_by VARCHAR(36) NULL,
      updated_at TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
      PRIMARY KEY (currency_rate_id)
);