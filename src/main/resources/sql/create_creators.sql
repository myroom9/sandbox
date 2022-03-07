CREATE TABLE `sandbox`.`creators` (
                                      `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '크리에이터 일련번호',
    -- `channel_id` BIGINT UNSIGNED NOT NULL COMMENT '유튜브 채널 일련번호',
                                      `name` VARCHAR(50) NOT NULL COMMENT '크리에이터 이름',
                                      `birth` VARCHAR(8) NOT NULL COMMENT '크리에이터 생년월일',
                                      `sex` TINYINT NOT NULL COMMENT '크리에이터 성별 (0: 남성, 1: 여성, 2: 기타)',
                                      `created_at` TIMESTAMP NOT NULL COMMENT '등록시간',
                                      `updated_at` TIMESTAMP NULL COMMENT '수정시간',
                                      `deleted_at` TIMESTAMP NULL COMMENT '삭제시간',
                                      PRIMARY KEY (`id`))
    ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_unicode_ci
COMMENT = '유튜브 크리에이터 정보 테이블';

ALTER TABLE `sandbox`.`creators`
    ADD INDEX `name_index` (`name` ASC);