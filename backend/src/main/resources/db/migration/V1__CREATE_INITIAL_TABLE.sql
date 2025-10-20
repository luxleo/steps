-- =========================================================
-- USERS
-- =========================================================
CREATE TABLE member (
                              id            BIGINT AUTO_INCREMENT PRIMARY KEY,
                              email         VARCHAR(255) NOT NULL UNIQUE,
                              password_hash VARCHAR(255) NOT NULL,
                              name          VARCHAR(100) NOT NULL,
                              profile_image_url VARCHAR(500),
                              created_at    TIMESTAMP DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- =========================================================
-- PROJECTS
-- =========================================================
CREATE TABLE project (
                         id           BIGINT AUTO_INCREMENT PRIMARY KEY,
                         owner_id     BIGINT NOT NULL,
                         name         VARCHAR(200) NOT NULL,
                         description  TEXT,
                         created_at   TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                         CONSTRAINT fk_project_owner
                             FOREIGN KEY (owner_id) REFERENCES member(id)
                                 ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- =========================================================
-- PROJECT MEMBERSHIP
-- =========================================================
CREATE TABLE project_member (
                                id          BIGINT AUTO_INCREMENT PRIMARY KEY,
                                project_id  BIGINT NOT NULL,
                                user_id     BIGINT NOT NULL,
                                role VARCHAR(30), -- MEMBER, ADMIN, OWNER, VIEWER
                                joined_at   TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                CONSTRAINT fk_member_project
                                    FOREIGN KEY (project_id) REFERENCES project(id)
                                        ON DELETE CASCADE,
                                CONSTRAINT fk_member_user
                                    FOREIGN KEY (user_id) REFERENCES member(id)
                                        ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- =========================================================
-- SECTIONS
-- =========================================================
CREATE TABLE section (
                         id           BIGINT AUTO_INCREMENT PRIMARY KEY,
                         project_id   BIGINT NOT NULL,
                         name         VARCHAR(200) NOT NULL,
                         order_index  INT NOT NULL DEFAULT 0,
                         created_at   TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                         CONSTRAINT fk_section_project
                             FOREIGN KEY (project_id) REFERENCES project(id)
                                 ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- =========================================================
-- TASKS
-- =========================================================
CREATE TABLE task (
                      id            BIGINT AUTO_INCREMENT PRIMARY KEY,
                      project_id    BIGINT NOT NULL,
                      section_id    BIGINT NULL,
                      assignee_id   BIGINT NULL,
                      created_by    BIGINT NOT NULL,
                      title         VARCHAR(255) NOT NULL,
                      description   TEXT,
                      status VARCHAR(20) NOT NULL, -- TODO, DOING, DONE
                      priority TINYINT DEFAULT 0,
                      due_date DATE NULL,
                      created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                      CONSTRAINT fk_task_project
                          FOREIGN KEY (project_id) REFERENCES project(id)
                              ON DELETE CASCADE,
                      CONSTRAINT fk_task_section
                          FOREIGN KEY (section_id) REFERENCES section(id)
                              ON DELETE SET NULL,
                      CONSTRAINT fk_task_assignee
                          FOREIGN KEY (assignee_id) REFERENCES member(id)
                              ON DELETE SET NULL,
                      CONSTRAINT fk_task_created_by
                          FOREIGN KEY (created_by) REFERENCES member(id)
                              ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- =========================================================
-- COMMENTS (optional extension)
-- =========================================================
CREATE TABLE task_comment (
                              id          BIGINT AUTO_INCREMENT PRIMARY KEY,
                              task_id     BIGINT NOT NULL,
                              author_id   BIGINT NOT NULL,
                              content     TEXT NOT NULL,
                              created_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                              CONSTRAINT fk_comment_task
                                  FOREIGN KEY (task_id) REFERENCES task(id)
                                      ON DELETE CASCADE,
                              CONSTRAINT fk_comment_author
                                  FOREIGN KEY (author_id) REFERENCES member(id)
                                      ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- =========================================================
-- NOTIFICATIONS
-- =========================================================
CREATE TABLE notification (
                              id            BIGINT AUTO_INCREMENT PRIMARY KEY,
                              recipient_id  BIGINT NOT NULL,
                              type VARCHAR(30) NOT NULL, -- PROJECT_INVITE, TASK_ASSIGNED, TASK_COMPLETED, COMMENT_MENTION, SYSTEM
                              entity_type VARCHAR(30) NOT NULL, -- PROJECT, TASK, COMMENT, SYSTEM
                              entity_id     BIGINT NULL,
                              title         VARCHAR(200),
                              body          TEXT,
                              is_read       BOOLEAN DEFAULT FALSE,
                              created_at    TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                              delivered_at  TIMESTAMP NULL,
                              CONSTRAINT fk_notification_recipient
                                  FOREIGN KEY (recipient_id) REFERENCES member(id)
                                      ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- =========================================================
-- ACTIVITY LOG (optional)
-- =========================================================
CREATE TABLE activity_log (
                              id          BIGINT AUTO_INCREMENT PRIMARY KEY,
                              project_id  BIGINT NULL,
                              user_id     BIGINT NULL,
                              entity_type VARCHAR(30) NOT NULL, -- PROJECT, TASK, COMMENT, USER
                              entity_id   BIGINT NULL,
                              action      VARCHAR(100) NOT NULL,
                              created_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                              CONSTRAINT fk_activity_project
                                  FOREIGN KEY (project_id) REFERENCES project(id)
                                      ON DELETE CASCADE,
                              CONSTRAINT fk_activity_user
                                  FOREIGN KEY (user_id) REFERENCES member(id)
                                      ON DELETE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
