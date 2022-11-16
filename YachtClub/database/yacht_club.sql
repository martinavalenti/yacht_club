-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Creato il: Nov 16, 2022 alle 19:34
-- Versione del server: 10.4.25-MariaDB
-- Versione PHP: 7.4.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `yacht_club`
--

-- --------------------------------------------------------

--
-- Struttura della tabella `address`
--

CREATE TABLE `address` (
  `id` int(11) NOT NULL,
  `address` varchar(50) NOT NULL,
  `city` varchar(20) NOT NULL,
  `postal_code` varchar(5) NOT NULL,
  `country` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dump dei dati per la tabella `address`
--

INSERT INTO `address` (`id`, `address`, `city`, `postal_code`, `country`) VALUES
(24, 'via cavagnari 10', 'Parma', '43126', 'italia'),
(25, 'Via Frati 12', 'Felino', '43035', 'Italia');

-- --------------------------------------------------------

--
-- Struttura della tabella `admin`
--

CREATE TABLE `admin` (
  `username` varchar(30) NOT NULL,
  `password` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dump dei dati per la tabella `admin`
--

INSERT INTO `admin` (`username`, `password`) VALUES
('admin', 'admin');

-- --------------------------------------------------------

--
-- Struttura della tabella `boat`
--

CREATE TABLE `boat` (
  `id` int(11) NOT NULL,
  `name` varchar(30) NOT NULL,
  `length` int(11) NOT NULL,
  `user` varchar(30) NOT NULL,
  `annual_fee_expiry` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dump dei dati per la tabella `boat`
--

INSERT INTO `boat` (`id`, `name`, `length`, `user`, `annual_fee_expiry`) VALUES
(19, 'barca_1', 20, 'user', '2023-11-16'),
(20, 'barca_2', 10, 'user', '2022-12-05'),
(21, 'barca_3', 13, 'user', '2023-11-16'),
(22, 'stella di mare', 6, 'anna', '2023-11-16');

-- --------------------------------------------------------

--
-- Struttura della tabella `creditcard`
--

CREATE TABLE `creditcard` (
  `number` varchar(16) NOT NULL,
  `owner` varchar(50) NOT NULL,
  `cvc` varchar(3) NOT NULL,
  `expiration_date` varchar(5) NOT NULL,
  `user` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dump dei dati per la tabella `creditcard`
--

INSERT INTO `creditcard` (`number`, `owner`, `cvc`, `expiration_date`, `user`) VALUES
('4471494437803684', 'Mario Rossi', '445', '07/26', 'user'),
('5273456123528146', 'Mario Rossi', '258', '01/29', 'user');

-- --------------------------------------------------------

--
-- Struttura della tabella `notification`
--

CREATE TABLE `notification` (
  `id` int(11) NOT NULL,
  `content` varchar(60) NOT NULL,
  `user` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dump dei dati per la tabella `notification`
--

INSERT INTO `notification` (`id`, `content`, `user`) VALUES
(32, 'Attention! Your annual storage fee is about to expire', 'user'),
(33, 'Attention! Your annual fee is about to expire', 'user');

-- --------------------------------------------------------

--
-- Struttura della tabella `payment`
--

CREATE TABLE `payment` (
  `id` int(11) NOT NULL,
  `reason` varchar(50) NOT NULL,
  `type` varchar(40) NOT NULL,
  `amount` int(11) NOT NULL,
  `date` date NOT NULL,
  `user` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dump dei dati per la tabella `payment`
--

INSERT INTO `payment` (`id`, `reason`, `type`, `amount`, `date`, `user`) VALUES
(43, 'Boat Storage', 'Bank transfer', 800, '2022-11-16', 'user'),
(44, 'Boat Storage', 'Bank transfer', 400, '2022-11-16', 'user'),
(45, 'Boat Storage', 'Bank transfer', 520, '2022-11-16', 'user'),
(46, 'Race Entry Fee', 'Credit Card', 500, '2022-11-16', 'user'),
(47, 'Boat Storage', 'Bank transfer', 240, '2022-11-16', 'anna'),
(48, 'Race Entry Fee', 'Bank transfer', 170, '2022-11-16', 'anna'),
(49, 'Annual Fee', 'Credit Card', 1000, '2022-11-16', 'user');

-- --------------------------------------------------------

--
-- Struttura della tabella `race`
--

CREATE TABLE `race` (
  `id` int(11) NOT NULL,
  `name` varchar(50) NOT NULL,
  `date` date NOT NULL,
  `entry_fee` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dump dei dati per la tabella `race`
--

INSERT INTO `race` (`id`, `name`, `date`, `entry_fee`) VALUES
(9, 'race_1', '2023-02-10', 300),
(10, 'race_2', '2022-12-24', 500),
(11, 'race_3', '2023-01-05', 170),
(12, 'past_race', '2022-05-01', 300);

-- --------------------------------------------------------

--
-- Struttura della tabella `race_registration`
--

CREATE TABLE `race_registration` (
  `race_id` int(11) NOT NULL,
  `user_id` varchar(30) NOT NULL,
  `boat_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dump dei dati per la tabella `race_registration`
--

INSERT INTO `race_registration` (`race_id`, `user_id`, `boat_id`) VALUES
(10, 'user', 19),
(11, 'anna', 22);

-- --------------------------------------------------------

--
-- Struttura della tabella `staff`
--

CREATE TABLE `staff` (
  `username` varchar(30) NOT NULL,
  `password` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dump dei dati per la tabella `staff`
--

INSERT INTO `staff` (`username`, `password`) VALUES
('staff', 'staff');

-- --------------------------------------------------------

--
-- Struttura della tabella `user`
--

CREATE TABLE `user` (
  `username` varchar(30) NOT NULL,
  `password` varchar(30) NOT NULL,
  `full_name` varchar(50) NOT NULL,
  `tax_code` varchar(16) NOT NULL,
  `address_id` int(11) NOT NULL,
  `annual_fee` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dump dei dati per la tabella `user`
--

INSERT INTO `user` (`username`, `password`, `full_name`, `tax_code`, `address_id`, `annual_fee`) VALUES
('anna', 'password', 'Anna Bianchi', 'BNCNNA80E45G337Z', 25, '2023-11-16'),
('user', 'user', 'Mario Rossi', 'RSSMRA95A01G337O', 24, '2022-12-05');

--
-- Indici per le tabelle scaricate
--

--
-- Indici per le tabelle `address`
--
ALTER TABLE `address`
  ADD PRIMARY KEY (`id`);

--
-- Indici per le tabelle `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`username`);

--
-- Indici per le tabelle `boat`
--
ALTER TABLE `boat`
  ADD PRIMARY KEY (`id`),
  ADD KEY `boat_userFK` (`user`);

--
-- Indici per le tabelle `creditcard`
--
ALTER TABLE `creditcard`
  ADD PRIMARY KEY (`number`),
  ADD KEY `creditcard_userFK` (`user`);

--
-- Indici per le tabelle `notification`
--
ALTER TABLE `notification`
  ADD PRIMARY KEY (`id`),
  ADD KEY `notification_userFK` (`user`);

--
-- Indici per le tabelle `payment`
--
ALTER TABLE `payment`
  ADD PRIMARY KEY (`id`),
  ADD KEY `payment_userFK` (`user`);

--
-- Indici per le tabelle `race`
--
ALTER TABLE `race`
  ADD PRIMARY KEY (`id`);

--
-- Indici per le tabelle `race_registration`
--
ALTER TABLE `race_registration`
  ADD PRIMARY KEY (`race_id`,`user_id`,`boat_id`),
  ADD KEY `boatFK` (`boat_id`),
  ADD KEY `registration_userFK` (`user_id`);

--
-- Indici per le tabelle `staff`
--
ALTER TABLE `staff`
  ADD PRIMARY KEY (`username`);

--
-- Indici per le tabelle `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`username`),
  ADD KEY `adress_foreign_key` (`address_id`);

--
-- AUTO_INCREMENT per le tabelle scaricate
--

--
-- AUTO_INCREMENT per la tabella `address`
--
ALTER TABLE `address`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=26;

--
-- AUTO_INCREMENT per la tabella `boat`
--
ALTER TABLE `boat`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=23;

--
-- AUTO_INCREMENT per la tabella `notification`
--
ALTER TABLE `notification`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=34;

--
-- AUTO_INCREMENT per la tabella `payment`
--
ALTER TABLE `payment`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=50;

--
-- AUTO_INCREMENT per la tabella `race`
--
ALTER TABLE `race`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- Limiti per le tabelle scaricate
--

--
-- Limiti per la tabella `boat`
--
ALTER TABLE `boat`
  ADD CONSTRAINT `boat_userFK` FOREIGN KEY (`user`) REFERENCES `user` (`username`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Limiti per la tabella `creditcard`
--
ALTER TABLE `creditcard`
  ADD CONSTRAINT `creditcard_userFK` FOREIGN KEY (`user`) REFERENCES `user` (`username`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Limiti per la tabella `notification`
--
ALTER TABLE `notification`
  ADD CONSTRAINT `notification_userFK` FOREIGN KEY (`user`) REFERENCES `user` (`username`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Limiti per la tabella `payment`
--
ALTER TABLE `payment`
  ADD CONSTRAINT `payment_userFK` FOREIGN KEY (`user`) REFERENCES `user` (`username`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Limiti per la tabella `race_registration`
--
ALTER TABLE `race_registration`
  ADD CONSTRAINT `boatFK` FOREIGN KEY (`boat_id`) REFERENCES `boat` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `raceFK` FOREIGN KEY (`race_id`) REFERENCES `race` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `registration_userFK` FOREIGN KEY (`user_id`) REFERENCES `user` (`username`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Limiti per la tabella `user`
--
ALTER TABLE `user`
  ADD CONSTRAINT `adress_foreign_key` FOREIGN KEY (`address_id`) REFERENCES `address` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
