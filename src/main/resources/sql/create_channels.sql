CREATE TABLE `sandbox`.`channels` (
                                      `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '유튜브 채널 일련번호',
    -- `creator_id` BIGINT UNSIGNED NOT NULL COMMENT '크리에이터 일련번호',
                                      `name` VARCHAR(100) NOT NULL COMMENT '유튜브 채널명',
                                      `subscriber_count` BIGINT UNSIGNED NOT NULL DEFAULT 0 COMMENT '유튜브 채널 구독자 수',
                                      `created_at` TIMESTAMP NOT NULL COMMENT '생성시간',
                                      `updated_at` TIMESTAMP NULL COMMENT '수정시간',
                                      `deleted_at` TIMESTAMP NULL COMMENT '삭제시간',
                                      PRIMARY KEY (`id`))
    ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_unicode_ci
COMMENT = '유튜브 채널 정보 테이블';


ALTER TABLE `sandbox`.`channels`
    ADD INDEX `name_idx` (`name` ASC);