CREATE TABLE `sandbox`.`sales_managements` (
                                               `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '매출관리 일련번호',
                                               `channel_id` BIGINT UNSIGNED NOT NULL COMMENT '유튜브 채널 일련번호',
                                               `sales_date` DATE NOT NULL COMMENT '매출발생일',
                                               `sales_amount` DECIMAL(20,2) NOT NULL DEFAULT 0.00 COMMENT '매출액',
                                               `created_at` TIMESTAMP NOT NULL COMMENT '생성시간',
                                               `updated_at` TIMESTAMP NULL COMMENT '수정시간',
                                               `deleted_at` TIMESTAMP NULL COMMENT '삭제시간',
                                               PRIMARY KEY (`id`))
    ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_unicode_ci
COMMENT = '매출관리 테이블';

ALTER TABLE `sandbox`.`sales_managements`
    ADD INDEX `sales_date_index` (`sales_date` ASC),
ADD INDEX `sales_amount_index` (`sales_amount` ASC);