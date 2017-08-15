-- phpMyAdmin SQL Dump
-- version 4.6.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- 생성 시간: 17-08-15 05:36
-- 서버 버전: 5.7.14
-- PHP 버전: 5.6.25

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- 데이터베이스: `hospital`
--

-- --------------------------------------------------------

--
-- 테이블 구조 `bbs`
--

CREATE TABLE `bbs` (
  `seq` int(11) UNSIGNED NOT NULL,
  `category` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `title` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `content` longtext COLLATE utf8_unicode_ci NOT NULL,
  `status` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `count` int(11) UNSIGNED NOT NULL,
  `create_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `ancestor` int(11) UNSIGNED NOT NULL,
  `reply` varchar(10) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- 테이블의 덤프 데이터 `bbs`
--

INSERT INTO `bbs` (`seq`, `category`, `user_id`, `title`, `content`, `status`, `count`, `create_at`, `updated_at`, `ancestor`, `reply`) VALUES
(1, 'notice', 'moon', 'Hi', 'Bye', 'published', 0, '2017-08-13 18:37:19', '2017-08-13 18:37:19', 1, NULL),
(2, 'notice', 'moon', 'Hi', 'Bye', 'published', 0, '2017-08-13 18:38:19', '2017-08-13 18:38:19', 1, 'A'),
(3, 'notice', 'moon', 'Hi', 'Bye', 'published', 0, '2017-08-13 18:38:26', '2017-08-13 18:38:26', 1, 'AA'),
(4, 'notice', 'moon', 'Hi', 'Bye', 'published', 0, '2017-08-13 18:38:30', '2017-08-13 18:38:30', 1, 'B'),
(5, 'notice', 'moon', 'Hi', 'Bye', 'published', 0, '2017-08-13 18:38:35', '2017-08-13 18:38:35', 1, 'AAA'),
(6, 'notice', 'moon', 'Nice', 'To', 'published', 0, '2017-08-13 18:39:31', '2017-08-13 18:39:31', 6, NULL),
(7, 'notice', 'moon', 'Meet', 'You', 'published', 0, '2017-08-13 18:43:11', '2017-08-13 18:43:11', 7, NULL),
(8, 'notice', 'moon', 'Nice', 'To', 'published', 0, '2017-08-13 18:43:37', '2017-08-13 18:43:37', 6, 'A'),
(9, 'notice', 'moon', '?????', '?????', 'published', 0, '2017-08-13 19:07:41', '2017-08-13 19:07:41', 9, NULL),
(10, 'notice', 'moon', '안녕하세요', '반갑습니다', 'published', 0, '2017-08-13 19:08:41', '2017-08-13 19:08:41', 10, NULL),
(11, 'notice', 'moon', '휴일에는', '핏짜!', 'published', 0, '2017-08-14 04:53:30', '2017-08-14 04:53:30', 11, NULL);

--
-- 덤프된 테이블의 인덱스
--

--
-- 테이블의 인덱스 `bbs`
--
ALTER TABLE `bbs`
  ADD PRIMARY KEY (`seq`);

--
-- 덤프된 테이블의 AUTO_INCREMENT
--

--
-- 테이블의 AUTO_INCREMENT `bbs`
--
ALTER TABLE `bbs`
  MODIFY `seq` int(11) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
