CREATE TABLE user_login (
      login_id   VARCHAR(36)  NOT NULL,
      username VARCHAR(30) NULL,
      password VARCHAR(255) NULL,
      name VARCHAR(60) NULL,
      email VARCHAR(40) NULL,
      phone_number VARCHAR(15) NULL,
      outlet_id VARCHAR(36) NOT NULL,
      employee_id VARCHAR(36) NOT NULL,
      avatar VARCHAR(1280) NULL,
      active BOOLEAN DEFAULT true,
      created_by VARCHAR(36) NULL,
      created_at TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
      updated_by VARCHAR(36) NULL,
      updated_at TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
      PRIMARY KEY (login_id)
 );
