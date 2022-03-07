CREATE TABLE `sandbox`.`creator_settlements` (
                                                 `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '정산 일련번호',
                                                 `channel_id` BIGINT UNSIGNED NOT NULL COMMENT '유튜브 채널 일련번호',
                                                 `creator_id` BIGINT UNSIGNED NOT NULL COMMENT '크리에이터 일련번호',
                                                 `settlement_amount` DECIMAL(20,2) NOT NULL DEFAULT 0.00 COMMENT '크리에이터 정산금',
    -- `company_settlement_amount` BIGINT UNSIGNED NOT NULL DEFAULT 0 COMMENT '회사 정산금',
                                                 `settlement_date` DATE NOT NULL COMMENT '정산일',
                                                 `created_at` TIMESTAMP NOT NULL COMMENT '생성시간',
                                                 `updated_at` TIMESTAMP NULL COMMENT '수정시간',
                                                 `deleted_at` TIMESTAMP NULL COMMENT '삭제시간',
                                                 PRIMARY KEY (`id`))
    ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_unicode_ci
COMMENT = '정산 테이블';

ALTER TABLE `sandbox`.`creator_settlements`
    ADD INDEX `settlement_date_index` (`settlement_date` ASC);