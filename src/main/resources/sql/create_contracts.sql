CREATE TABLE `sandbox`.`contracts` (
                                       `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '계약정보 일련번호',
                                       `creator_id` BIGINT UNSIGNED NOT NULL COMMENT '크리에이터 일련번호',
    -- `creator_grade_id` BIGINT UNSIGNED NOT NULL COMMENT '크리에이터 등급 일련번호',
                                       `channel_id` BIGINT UNSIGNED NOT NULL COMMENT '유튜브 채널 일련번호',
                                       `creator_rate` TINYINT UNSIGNED NOT NULL DEFAULT 0 COMMENT '크리에이터 요율',
                                       `company_rate` TINYINT UNSIGNED NOT NULL DEFAULT 0 COMMENT '회사 요율',
                                       `status` VARCHAR(10) NOT NULL DEFAULT 'WAIT' COMMENT '계약상태',
                                       `created_at` TIMESTAMP NOT NULL COMMENT '생성시간',
                                       `updated_at` TIMESTAMP NULL COMMENT '수정시간',
                                       `deleted_at` TIMESTAMP NULL COMMENT '삭제시간',
                                       PRIMARY KEY (`id`))
    ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_unicode_ci
COMMENT = '계약정보 테이블';