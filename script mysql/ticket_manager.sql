-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th6 29, 2024 lúc 05:52 PM
-- Phiên bản máy phục vụ: 10.4.25-MariaDB
-- Phiên bản PHP: 8.0.23

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `ticket_manager`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `calendar`
--

CREATE TABLE `calendar` (
  `id` varchar(25) NOT NULL,
  `time` bigint(20) NOT NULL,
  `idMovie` varchar(25) NOT NULL,
  `cancleAt` bigint(20) DEFAULT NULL,
  `room` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `calendar`
--

INSERT INTO `calendar` (`id`, `time`, `idMovie`, `cancleAt`, `room`) VALUES
('', 1719399660, '1', NULL, 1),
('1', 1719601592, '1', NULL, 2),
('2', 1719394201, 'MOVIE_1717866136_46521', 1, 2),
('CALENDAR_1719397686_030', 1719490620, 'MOVIE_1717866136_46521', NULL, 1),
('CALENDAR_1719604526_87', 1719604560, '1', 1719643756, 1),
('CALENDAR_1719604978_103', 1719608520, '1', 1719643735, 1),
('CALENDAR_1719604984_629', 1719608520, '1', NULL, 1),
('CALENDAR_1719605147_96', 1719608700, '1', 1719643855, 1),
('CALENDAR_1719605149_497', 1719608700, '1', NULL, 1),
('CALENDAR_1719605227_908', 1719608820, '1', NULL, 1),
('CALENDAR_1719605296_508', 1719608880, '1', NULL, 1),
('CALENDAR_1719605347_706', 1719608940, '1', NULL, 1),
('CALENDAR_1719640283_303', 1719647220, 'MOVIE_1718110284_28395', NULL, 1),
('CALENDAR_1719640326_796', 1719658920, 'MOVIE_1718110284_28395', NULL, 1),
('CALENDAR_1719640363_479', 1719660240, 'MOVIE_1718110284_28395', 1719643683, 1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `movie`
--

CREATE TABLE `movie` (
  `id` varchar(25) NOT NULL,
  `name` varchar(255) NOT NULL,
  `age` int(11) NOT NULL,
  `minPrice` int(11) NOT NULL,
  `hideAt` bigint(20) DEFAULT NULL,
  `createAt` bigint(20) NOT NULL,
  `time` int(11) NOT NULL,
  `image` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `movie`
--

INSERT INTO `movie` (`id`, `name`, `age`, `minPrice`, `hideAt`, `createAt`, `time`, `image`) VALUES
('1', 'cô dâu 8 tuổi', 16, 31000, NULL, 20, 90, NULL),
('MOVIE_1717866136_46521', 'chiến tranh 1', 1, 45000, NULL, 1717866136, 120, NULL),
('MOVIE_1717866136_55853', 'tình yêu 1', 16, 50000, NULL, 1717866136, 130, NULL),
('MOVIE_1717866136_60078', 'tình yêu 2', 16, 100000, NULL, 1717866136, 150, NULL),
('MOVIE_1718110145_27760', 'Ngày mai nắng lên', 2, 45000, 1, 1718110146, 120, NULL),
('MOVIE_1718110284_28395', 'Ngày mai nắng lên 2', 16, 50000, NULL, 1718110285, 120, NULL),
('MOVIE_1719672939_34', 'phim trường', 18, 50000, NULL, 1719672951, 120, NULL);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `seat`
--

CREATE TABLE `seat` (
  `id` bigint(20) NOT NULL,
  `idTicket` varchar(25) NOT NULL,
  `location` varchar(3) NOT NULL,
  `idCalendar` varchar(25) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `seat`
--

INSERT INTO `seat` (`id`, `idTicket`, `location`, `idCalendar`) VALUES
(1, '1', 'A3', '2'),
(2, '1', 'A4', '2'),
(3, '1', 'A5', '2'),
(4, 'TICKET_1718439182_576', 'A1', '2'),
(5, 'TICKET_1718439182_576', 'A2', '2'),
(6, 'TICKET_1718439357_648', 'A6', '2'),
(7, 'TICKET_1718439357_648', 'A7', '2'),
(8, 'TICKET_1718439728_886', 'A8', '2'),
(9, 'TICKET_1718439776_819', 'A10', '2'),
(10, 'TICKET_1718439776_819', 'A9', '2'),
(11, 'TICKET_1718440736_653', 'B4', '2'),
(12, 'TICKET_1718440736_653', 'B5', '2'),
(13, 'TICKET_1718440736_653', 'B6', '2'),
(14, 'TICKET_1718537782_700', 'B2', '2'),
(15, 'TICKET_1718537782_700', 'B3', '2'),
(16, 'TICKET_1718538045_283', 'B1', '2'),
(17, 'TICKET_1718538109_757', 'B7', '2'),
(18, 'TICKET_1718538515_876', 'H10', '2'),
(19, 'TICKET_1718538515_876', 'H9', '2'),
(20, 'TICKET_1718538721_113', 'B8', '2'),
(21, 'TICKET_1718538721_113', 'B9', '2'),
(22, 'TICKET_1718538948_273', 'B10', '2'),
(23, 'TICKET_1718538993_633', 'C1', '2'),
(24, 'TICKET_1718538993_633', 'C2', '2'),
(25, 'TICKET_1718539330_835', 'C3', '2'),
(26, 'TICKET_1718540040_528', 'C4', '2'),
(27, 'TICKET_1718540040_528', 'C5', '2'),
(28, 'TICKET_1718540253_585', 'C6', '2'),
(29, 'TICKET_1718540419_436', 'C7', '2'),
(30, 'TICKET_1718540419_436', 'C8', '2'),
(31, 'TICKET_1718788896_253', 'C10', '2'),
(32, 'TICKET_1718788896_253', 'C9', '2'),
(33, 'TICKET_1718960610_858', 'D1', '2'),
(34, 'TICKET_1718960610_858', 'D2', '2'),
(35, 'TICKET_1719505218_177', 'A1', '1'),
(36, 'TICKET_1719505218_177', 'A2', '1'),
(37, 'TICKET_1719505310_146', 'B1', '1'),
(38, 'TICKET_1719505310_146', 'B2', '1'),
(39, 'TICKET_1719505339_130', 'A3', '1'),
(40, 'TICKET_1719505339_130', 'B1', '1'),
(41, 'TICKET_1719505339_130', 'B2', '1'),
(42, 'TICKET_1719505339_130', 'B3', '1'),
(43, 'TICKET_1719505360_609', 'A3', '1'),
(44, 'TICKET_1719505360_609', 'A4', '1'),
(45, 'TICKET_1719505360_609', 'B1', '1'),
(46, 'TICKET_1719505360_609', 'B2', '1'),
(47, 'TICKET_1719505360_609', 'B3', '1'),
(48, 'TICKET_1719505360_609', 'B4', '1'),
(49, 'TICKET_1719505903_826', 'C1', '1'),
(50, 'TICKET_1719505903_826', 'D1', '1'),
(51, 'TICKET_1719506089_657', 'C2', '1'),
(52, 'TICKET_1719506089_657', 'D2', '1'),
(53, 'TICKET_1719560544_738', 'C3', '1'),
(54, 'TICKET_1719560544_738', 'D3', '1'),
(55, 'TICKET_1719560800_462', 'C4', '1'),
(56, 'TICKET_1719560800_462', 'C5', '1'),
(57, 'TICKET_1719560800_462', 'D4', '1'),
(58, 'TICKET_1719560949_892', 'A5', '1'),
(59, 'TICKET_1719560949_892', 'B5', '1'),
(60, 'TICKET_1719561088_538', 'B6', '1'),
(61, 'TICKET_1719561088_538', 'C6', '1'),
(62, 'TICKET_1719561202_190', 'A6', '1'),
(63, 'TICKET_1719561384_638', 'C7', '1'),
(64, 'TICKET_1719561464_543', 'B7', '1'),
(65, 'TICKET_1719561665_108', 'D5', '1'),
(66, 'TICKET_1719561665_108', 'D6', '1'),
(67, 'TICKET_1719561814_354', 'A7', '1'),
(68, 'TICKET_1719562060_855', 'A8', '1'),
(69, 'TICKET_1719562060_855', 'B8', '1'),
(70, 'TICKET_1719563399_786', 'D7', '1'),
(71, 'TICKET_1719563582_768', 'C8', '1');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `staff`
--

CREATE TABLE `staff` (
  `idnv` varchar(25) NOT NULL,
  `name` varchar(40) NOT NULL,
  `sdt` varchar(10) DEFAULT NULL,
  `email` varchar(50) NOT NULL,
  `sex` int(11) NOT NULL,
  `rank` enum('admin','staff') NOT NULL DEFAULT 'staff',
  `blockAt` bigint(20) DEFAULT NULL,
  `password` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `staff`
--

INSERT INTO `staff` (`idnv`, `name`, `sdt`, `email`, `sex`, `rank`, `blockAt`, `password`) VALUES
('123admin', 'admin1', '0815565487', 'chienboy03@gmail.com', 1, 'admin', NULL, 'xAqQiyJAjBg6vDs4EZ3Nb8QxcpJC/Sg1PLuFmoCIPkTehXlydt+haJgdkrZ2hxgM'),
('123staff', 'staff', NULL, 'staff@gmail.com', 0, 'staff', 1717695749, 'RU9DgfFK+uosq6NUPkuSBh+jo+ROvkzWVqMtZX17ErSYSP/lgHsOzEI7RcNyaGmB'),
('STAFF_1717668565_587', 'chien', '0333757429', 'abc@gmail.com', 1, 'staff', NULL, 'VE+Se067WVdJoCv+nQC1IOSz/fmc6FWUMyqtHdh1ddzLTMDXc2eQASHoIITiyngn'),
('STAFF_1717751387_626', 'chiến điên', '1234', 'phuongthanh18032003@gmail.com', 0, 'staff', 1719147344, 'VE+Se067WVdJoCv+nQC1IOSz/fmc6FWUMyqtHdh1ddzLTMDXc2eQASHoIITiyngn'),
('STAFF_1718804614_027', 'lê đăng chiến', '0333757429', 'chein@gmail.com', 1, 'staff', 1719226700, 'xd9taaNi4Aqb/l+63C+JzvPGA8yYGmx681U8AuaWhPf25aQ66ftw2le6hE90zpK+'),
('STAFF_1719030705_750', 'Lê đăng chiên', '1232', 'chein111@gmail.com', 1, 'staff', 1719332402, 'kswXoT1HNRzBBuBvGuc5KPIjYs/W/578V7naY2FAHsvcr4XTYg4I+q6hieTN8Q2m'),
('STAFF_1719031048_781', 'các', 'cacc', 'chcacscs@gmail.com', 1, 'staff', 1719239409, 'tlZmjGbiayhqNWXUY/wQ7p9YUr15MOABqCW6mIVK8EQIoBE0wi4h9KFoHPaZ/KUO'),
('STAFF_1719040131_181', 'chiến1', 'cscdc', 'cssdcs@gmail.com', 1, 'staff', 1719227484, '+jO9DAs3i3TYE1p3d3B5Q/bzHmtDgLwnAGI4l/rDB0Za50dDLi3Npaw2TFq5idIW'),
('STAFF_1719051119_42', 'dgf', 'gfdgf', 'chiiin@gmail.com', 1, 'staff', 1719239036, 'PnoKRxKR2Rf+KpodQVN+ldN93oITT4AVN7YGqEPkMlE2DDK2ZoyqfeteSlCPPacX'),
('STAFF_1719051490_917', 'hhhh', '123', 'acscsc@gmail.com', 0, 'staff', 1719238331, 'T2f8SvqE7P2UEDw8yIexF9jf/FREVPrETUqEG2+HlvaqkXnXKblVGqACCQsbXsIk'),
('STAFF_1719563197_31', 'chiến', '313', 'chienka0002@gmail.com', 1, 'staff', NULL, 'W9/pjdL5q2heFHGsvnsO9vUQMiRZGm4hVnOjWen6nZON1qklsT8sJZYXkSBs5Nmf');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `ticket`
--

CREATE TABLE `ticket` (
  `id` varchar(25) NOT NULL,
  `idCalendar` varchar(25) NOT NULL,
  `name` varchar(40) NOT NULL,
  `numPerson` int(11) NOT NULL,
  `numPopcorn` int(11) NOT NULL,
  `numWater` int(11) NOT NULL,
  `priceTicket` int(11) NOT NULL,
  `pricePopcorn` int(11) NOT NULL,
  `priceWater` int(11) NOT NULL,
  `email` varchar(50) NOT NULL,
  `createBy` varchar(25) NOT NULL,
  `createAt` bigint(20) NOT NULL,
  `checkinAt` bigint(20) DEFAULT NULL,
  `checkinBy` varchar(25) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `ticket`
--

INSERT INTO `ticket` (`id`, `idCalendar`, `name`, `numPerson`, `numPopcorn`, `numWater`, `priceTicket`, `pricePopcorn`, `priceWater`, `email`, `createBy`, `createAt`, `checkinAt`, `checkinBy`) VALUES
('1', '2', '1', 1, 1, 1, 1, 1, 1, 'hhh', '123staff', 4324, NULL, NULL),
('TICKET_1718439182_576', '2', 'lê đăng chiến', 2, 1, 1, 90000, 30000, 35000, 'ads@gmail.com', 'STAFF_1717668565_587', 0, NULL, NULL),
('TICKET_1718439357_648', '2', 'lê đăng chiến', 2, 1, 1, 90000, 30000, 35000, 'ads@gmail.com', 'STAFF_1717668565_587', 1718439359, NULL, NULL),
('TICKET_1718439728_886', '2', 'câcscs', 1, 1, 1, 45000, 30000, 35000, 'csacs@gmail.com', 'STAFF_1717668565_587', 1718439741, NULL, NULL),
('TICKET_1718439776_819', '2', 'câcsc', 2, 2, 2, 90000, 60000, 70000, 'fasfs@gmail.com', 'STAFF_1717668565_587', 1718439782, NULL, NULL),
('TICKET_1718440736_653', '2', 'chiến', 3, 1, 1, 141750, 30000, 35000, 'chien@gmail.com', 'STAFF_1717668565_587', 1718440749, NULL, NULL),
('TICKET_1718537782_700', '2', 'Trần Phương Thanh', 2, 2, 2, 94500, 60000, 70000, 'chienboy03@gmail.com', 'STAFF_1717668565_587', 1718537784, NULL, NULL),
('TICKET_1718538045_283', '2', 'chiến', 1, 1, 1, 47250, 30000, 35000, 'chienboy03@gmail.com', 'STAFF_1717668565_587', 1718538047, NULL, NULL),
('TICKET_1718538109_757', '2', 'Trần Phương Thanh', 1, 1, 1, 47250, 30000, 35000, 'chienboy03@gmail.com', 'STAFF_1717668565_587', 1718538111, NULL, NULL),
('TICKET_1718538515_876', '2', 'Lê Đăng Chiến', 2, 2, 2, 121500, 60000, 70000, 'Chienboy03@gmail.com', 'STAFF_1717668565_587', 1718538517, NULL, NULL),
('TICKET_1718538721_113', '2', 'Lê đăng chiến', 2, 1, 10, 94500, 30000, 350000, 'chienboy03@gmail.com', 'STAFF_1717668565_587', 1718538723, NULL, NULL),
('TICKET_1718538948_273', '2', 'lê đăng chiến', 1, 1, 1, 47250, 30000, 35000, 'chienboy03@gmail.com', 'STAFF_1717668565_587', 1718538950, NULL, NULL),
('TICKET_1718538993_633', '2', 'chiến', 2, 1, 1, 99000, 30000, 35000, 'chienboy03@gmail.com', 'STAFF_1717668565_587', 1718538995, NULL, NULL),
('TICKET_1718539330_835', '2', 'vdsvdsd', 1, 0, 0, 49500, 0, 0, 'chienboy03@gmail.com', 'STAFF_1717668565_587', 1718539333, NULL, NULL),
('TICKET_1718540040_528', '2', 'vsdvd', 2, 1, 1, 99000, 30000, 35000, 'chienboy03@gmail.com', 'STAFF_1717668565_587', 1718540041, NULL, NULL),
('TICKET_1718540253_585', '2', 'đâs', 1, 1, 1, 49500, 30000, 35000, 'chienboy03@gmail.com', 'STAFF_1717668565_587', 1718540255, 1718563537, NULL),
('TICKET_1718540419_436', '2', 'csac', 2, 1, 1, 99000, 30000, 35000, 'chienboy03@gmail.com', 'STAFF_1717668565_587', 1718540420, 1718563595, NULL),
('TICKET_1718788896_253', '2', 'chiến', 2, 1, 1, 99000, 30000, 35000, 'chienboy03@gmail.com', 'STAFF_1717668565_587', 1718788899, NULL, NULL),
('TICKET_1718960610_858', '2', 'chiến', 2, 0, 0, 103500, 0, 0, 'chienboy03@gmail.com', 'STAFF_1717668565_587', 1718960612, NULL, NULL),
('TICKET_1719504747_51', '1', 'chiến', 2, 1, 1, 62000, 30000, 35000, 'cavasvv@gmail.com', 'STAFF_1717668565_587', 1719504761, NULL, NULL),
('TICKET_1719504932_584', '1', 'vvvv', 2, 1, 1, 62000, 30000, 35000, 'vvvv@gmail.com', 'STAFF_1717668565_587', 1719504933, NULL, NULL),
('TICKET_1719505104_440', '1', 'vsvvasv', 2, 2, 2, 62000, 60000, 70000, 'vjvs@gmail.com', 'STAFF_1717668565_587', 1719505106, NULL, NULL),
('TICKET_1719505218_177', '1', 'cccc', 2, 2, 2, 62000, 60000, 70000, 'cccc@gmail.com', 'STAFF_1717668565_587', 1719505221, NULL, NULL),
('TICKET_1719505310_146', '1', 'vsvsd', 2, 2, 2, 62000, 60000, 70000, 'vsdvsdv@gmail.com', 'STAFF_1717668565_587', 1719505311, NULL, NULL),
('TICKET_1719505339_130', '1', 'vsvsd', 4, 2, 2, 124000, 60000, 70000, 'vsdvsdv@gmail.com', 'STAFF_1717668565_587', 1719505340, NULL, NULL),
('TICKET_1719505360_609', '1', 'vsvsd', 6, 2, 2, 186000, 60000, 70000, 'vsdvsdv@gmail.com', 'STAFF_1717668565_587', 1719505361, NULL, NULL),
('TICKET_1719505903_826', '1', 'chiến', 2, 1, 1, 62000, 30000, 35000, 'chienboy03@gmail.com', 'STAFF_1717668565_587', 1719505924, NULL, NULL),
('TICKET_1719506089_657', '1', 'thanh', 2, 10, 0, 62000, 300000, 0, '123@gmail.com', 'STAFF_1717668565_587', 1719506100, NULL, NULL),
('TICKET_1719560544_738', '1', 'chiến dz', 2, 1, 1, 62000, 30000, 35000, 'chienboy03@gmail.com', 'STAFF_1717668565_587', 1719560548, NULL, NULL),
('TICKET_1719560800_462', '1', 'chiến', 3, 1, 1, 93000, 30000, 35000, 'chienboy03@gmail.com', 'STAFF_1717668565_587', 1719560802, NULL, NULL),
('TICKET_1719560949_892', '1', 'ấccasc', 2, 0, 0, 62000, 0, 0, 'chienboy03@gmail.com', 'STAFF_1717668565_587', 1719560950, NULL, NULL),
('TICKET_1719561088_538', '1', 'cacsc', 2, 0, 0, 62000, 0, 0, 'chienboy03@gmail.com', 'STAFF_1717668565_587', 1719561089, NULL, NULL),
('TICKET_1719561202_190', '1', 'chienboy03@gmail.com', 1, 1, 1, 31000, 30000, 35000, 'chienboy03@gmail.com', 'STAFF_1717668565_587', 1719561203, NULL, NULL),
('TICKET_1719561384_638', '1', 'vvvv', 1, 1, 1, 31000, 30000, 35000, 'chienboy03@gmail.com', 'STAFF_1717668565_587', 1719561385, NULL, NULL),
('TICKET_1719561464_543', '1', 'b', 1, 0, 0, 31000, 0, 0, 'chienboy03@gmail.com', 'STAFF_1717668565_587', 1719561465, NULL, NULL),
('TICKET_1719561665_108', '1', 'chienboy03@gmail.com', 2, 0, 0, 62000, 0, 0, 'chienboy03@gmail.com', 'STAFF_1717668565_587', 1719561667, NULL, NULL),
('TICKET_1719561814_354', '1', 'chienboy03@gmail.com', 1, 0, 0, 31000, 0, 0, 'chienboy03@gmail.com', 'STAFF_1717668565_587', 1719561815, NULL, NULL),
('TICKET_1719562060_855', '1', 'bbb', 2, 0, 0, 62000, 0, 0, 'chienboy03@gmail.com', 'STAFF_1717668565_587', 1719562061, NULL, NULL),
('TICKET_1719563399_786', '1', 'cacsac', 1, 0, 0, 31000, 0, 0, 'chienboy03@gmail.com', 'STAFF_1717668565_587', 1719563400, NULL, NULL),
('TICKET_1719563582_768', '1', 'vdsdv', 1, 0, 0, 31000, 0, 0, 'chienboy03@gmail.com', 'STAFF_1717668565_587', 1719563583, NULL, NULL);

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `calendar`
--
ALTER TABLE `calendar`
  ADD PRIMARY KEY (`id`),
  ADD KEY `idMovie` (`idMovie`);

--
-- Chỉ mục cho bảng `movie`
--
ALTER TABLE `movie`
  ADD PRIMARY KEY (`id`),
  ADD KEY `idx_movie_name` (`name`);

--
-- Chỉ mục cho bảng `seat`
--
ALTER TABLE `seat`
  ADD PRIMARY KEY (`id`),
  ADD KEY `idTicket_idx` (`idTicket`),
  ADD KEY `idCalendar_idx` (`idCalendar`);

--
-- Chỉ mục cho bảng `staff`
--
ALTER TABLE `staff`
  ADD PRIMARY KEY (`idnv`),
  ADD UNIQUE KEY `email_UNIQUE` (`email`),
  ADD KEY `idx_staff_email` (`email`);

--
-- Chỉ mục cho bảng `ticket`
--
ALTER TABLE `ticket`
  ADD PRIMARY KEY (`id`),
  ADD KEY `idCalendar` (`idCalendar`),
  ADD KEY `createBy` (`createBy`),
  ADD KEY `checkinBy` (`checkinBy`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `seat`
--
ALTER TABLE `seat`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=72;

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `calendar`
--
ALTER TABLE `calendar`
  ADD CONSTRAINT `calendar_ibfk_1` FOREIGN KEY (`idMovie`) REFERENCES `movie` (`id`);

--
-- Các ràng buộc cho bảng `seat`
--
ALTER TABLE `seat`
  ADD CONSTRAINT `idCalendar` FOREIGN KEY (`idCalendar`) REFERENCES `calendar` (`id`),
  ADD CONSTRAINT `idTicket` FOREIGN KEY (`idTicket`) REFERENCES `ticket` (`id`);

--
-- Các ràng buộc cho bảng `ticket`
--
ALTER TABLE `ticket`
  ADD CONSTRAINT `checkinBy` FOREIGN KEY (`checkinBy`) REFERENCES `staff` (`idnv`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `ticket_ibfk_1` FOREIGN KEY (`idCalendar`) REFERENCES `calendar` (`id`),
  ADD CONSTRAINT `ticket_ibfk_2` FOREIGN KEY (`createBy`) REFERENCES `staff` (`idnv`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
