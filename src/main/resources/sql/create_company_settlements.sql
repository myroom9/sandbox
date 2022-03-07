CREATE TABLE `sandbox`.`company_settlements` (
                                                 `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '회사 매출액 정산 일련번호',
                                                 `channel_id` BIGINT UNSIGNED NOT NULL COMMENT '유튜브 채널 일련번호',
                                                 `settlement_amount` DECIMAL(20,2) NOT NULL DEFAULT 0.00 COMMENT '회사 정산금액',
                                                 `settlement_date` DATE NOT NULL COMMENT '회사 정산 일자',
                                                 `created_at` DATETIME NOT NULL COMMENT '생성시간',
                                                 `updated_at` DATETIME NULL COMMENT '수정시간',
                                                 `deleted_at` DATETIME NULL COMMENT '삭제시간',
                                                 PRIMARY KEY (`id`))
    ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_unicode_ci
COMMENT = '회사 매출액 정산 테이블';

ALTER TABLE `sandbox`.`company_settlements`
    ADD INDEX `channel_id_index` (`channel_id` ASC);

