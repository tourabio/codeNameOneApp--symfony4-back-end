-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 23, 2021 at 07:12 AM
-- Server version: 10.4.18-MariaDB
-- PHP Version: 7.4.16

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `elevation`
--

-- --------------------------------------------------------

--
-- Table structure for table `classe`
--

CREATE TABLE `classe` (
  `ID_Class` int(6) NOT NULL,
  `ClassName` varchar(6) NOT NULL,
  `Capacity` int(3) NOT NULL,
  `Level` int(2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `depense`
--

CREATE TABLE `depense` (
  `id` bigint(8) NOT NULL,
  `id_finance` bigint(8) NOT NULL,
  `idUtilisateur` bigint(8) NOT NULL,
  `valeur` double NOT NULL,
  `source` varchar(255) NOT NULL,
  `description` varchar(255) NOT NULL,
  `justificatif` varchar(255) NOT NULL,
  `date` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `depense`
--

INSERT INTO `depense` (`id`, `id_finance`, `idUtilisateur`, `valeur`, `source`, `description`, `justificatif`, `date`) VALUES
(1, 12345661, 12345661, 152, 'qsdqsdsqd', 'qsdqdqsdsqd', 'qsdqsdqsd', '2016-01-01');

-- --------------------------------------------------------

--
-- Table structure for table `examen`
--

CREATE TABLE `examen` (
  `id_examen` int(11) NOT NULL,
  `id_matiere` int(11) NOT NULL,
  `nom_matiere` varchar(200) NOT NULL,
  `date_ex` date NOT NULL,
  `duree_ex` varchar(11) NOT NULL,
  `PDF` varchar(200) NOT NULL,
  `inscription` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `fichefinance`
--

CREATE TABLE `fichefinance` (
  `id` bigint(8) NOT NULL,
  `idUtilisateur` bigint(8) NOT NULL,
  `date_creation` date NOT NULL,
  `description` varchar(255) NOT NULL,
  `total` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `fichefinance`
--

INSERT INTO `fichefinance` (`id`, `idUtilisateur`, `date_creation`, `description`, `total`) VALUES
(0, 12345661, '2021-04-01', 'sqdqdqsdqdqsdqds', 0),
(12345661, 12345661, '2021-04-01', 'sqdqdqsdqdqsdqds', 0);

-- --------------------------------------------------------

--
-- Table structure for table `inscription`
--

CREATE TABLE `inscription` (
  `id_inscri` int(11) NOT NULL,
  `id_etud` bigint(8) NOT NULL,
  `id_ex` int(11) NOT NULL,
  `date_in` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `matiere`
--

CREATE TABLE `matiere` (
  `id_matiere` int(11) NOT NULL,
  `nom_matiere` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `note`
--

CREATE TABLE `note` (
  `id_note` int(11) NOT NULL,
  `id_examen` int(11) NOT NULL,
  `nom_matiere` varchar(200) NOT NULL,
  `note_cc` int(11) NOT NULL,
  `note_ex` int(11) NOT NULL,
  `id_etud` bigint(8) NOT NULL,
  `nom` varchar(50) NOT NULL,
  `prenom` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `reclamation`
--

CREATE TABLE `reclamation` (
  `id` bigint(8) NOT NULL,
  `idUtilisateur` bigint(8) NOT NULL,
  `sujet` varchar(50) NOT NULL,
  `comment` varchar(500) NOT NULL,
  `date` date NOT NULL,
  `etat` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `reclamation`
--

INSERT INTO `reclamation` (`id`, `idUtilisateur`, `sujet`, `comment`, `date`, `etat`) VALUES
(1, 12345651, 'qsdqsdqds', 'qsdqsdqsd', '2016-01-01', 'qsdqds'),
(2, 12345663, 'qsdqds', 'qsdqsdqsd', '2016-01-01', 'qsdqds'),
(4, 12345677, 'qsdqdsqd', 'azeazeydf', '2016-01-01', 'qsdqdqdqs'),
(5, 12345678, 'qsdqdqdqd', 'qsdqdqsdqsd', '2016-05-05', 'qsdqdqsdd'),
(7, 12345678, 'qsdqdqsdqsd', 'qsdqdqdqdqsd', '2016-01-01', 'qsdqdqsd'),
(8, 12345682, 'qsdqsd', 'qsdqdqds', '2016-01-01', 'qsdqsdqds'),
(10, 12345676, 'A propos Les Examanes', 'Suite au Covid et Corona Comment on va passer les examens', '2021-01-09', 'En Cours'),
(11, 12345676, 'fqsdsqd', 'qsdqsdqsd', '2019-04-18', 'qsdqdqsdd'),
(12, 12345676, 'qsdqsd', 'qsdqsd', '2021-05-01', 'qsdqdqsd'),
(13, 12345676, 'qaza', 'z', '2022-05-01', 'traité');

-- --------------------------------------------------------

--
-- Table structure for table `recommendation`
--

CREATE TABLE `recommendation` (
  `id` bigint(8) NOT NULL,
  `idUtilisateur` bigint(8) NOT NULL,
  `sujet` varchar(50) NOT NULL,
  `comment` varchar(500) NOT NULL,
  `date` date NOT NULL,
  `etat` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `recommendation`
--

INSERT INTO `recommendation` (`id`, `idUtilisateur`, `sujet`, `comment`, `date`, `etat`) VALUES
(1, 12345662, 'qsdqsd', 'qsdqsd', '2021-04-23', 'qsdqsd'),
(2, 12345661, 'qsdqsd', 'qsdqsdqsd', '2021-04-23', 'qsdqsdqsd'),
(3, 12345661, 'qsdqsd', 'qsdqsd', '2021-04-23', 'qsdqsd'),
(4, 12345661, 'qsdsqd', 'sdsq', '2021-04-23', 'qsd'),
(5, 12345661, 'qsdsqd', 'qsdqd', '2021-04-23', 'qsdqsd');

-- --------------------------------------------------------

--
-- Table structure for table `revenu`
--

CREATE TABLE `revenu` (
  `id` bigint(8) NOT NULL,
  `id_finance` bigint(8) NOT NULL,
  `idUtilisateur` bigint(8) NOT NULL,
  `valeur` double NOT NULL,
  `source` varchar(255) NOT NULL,
  `description` varchar(255) NOT NULL,
  `justificatif` varchar(255) NOT NULL,
  `date` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `revenu`
--

INSERT INTO `revenu` (`id`, `id_finance`, `idUtilisateur`, `valeur`, `source`, `description`, `justificatif`, `date`) VALUES
(0, 12345661, 12345676, 5000, 'azeazeaze', 'qsdqsdqsd', 'qsdqsdqsdsqd', '2021-05-12');

-- --------------------------------------------------------

--
-- Table structure for table `seance`
--

CREATE TABLE `seance` (
  `ID_Seance` int(6) NOT NULL,
  `ID_User` bigint(8) NOT NULL,
  `ID_Class` int(6) NOT NULL,
  `Subject` varchar(30) NOT NULL,
  `Length` varchar(20) NOT NULL,
  `Date` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `ID_User` bigint(8) NOT NULL,
  `Name` varchar(30) NOT NULL,
  `FamilyName` varchar(30) NOT NULL,
  `Type` varchar(10) NOT NULL,
  `Email` varchar(40) NOT NULL,
  `Password` varchar(20) NOT NULL,
  `Subject` varchar(20) DEFAULT NULL,
  `Class` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`ID_User`, `Name`, `FamilyName`, `Type`, `Email`, `Password`, `Subject`, `Class`) VALUES
(12345651, 'Oussema', 'Benhmida', 'Professor', 'oussema.benhmida@esprit.tn', 'root51', 'C++', NULL),
(12345661, 'Selim', 'Zoghbar', 'Etudiant', 'selim.zoghbar@esprit.tn', 'root61', NULL, '3A19'),
(12345662, 'Jaafer', 'Zbida', 'Etudiant', 'Jaafer.zbida@esprit.tn', 'root62', NULL, '1A2'),
(12345663, 'Bilel', 'Bouaziza', 'Etudiant', 'Bilel.Bouaziza@esprit.tn', 'root63', NULL, '3A18'),
(12345676, 'Ahmed', 'Lagermi', 'Professor', 'ahmed.lagermi@esprit.tn', 'root76', 'Statistique', NULL),
(12345677, 'Aziz', 'Maki', 'Etudiant', 'Aziz.Maki@esprit.tn', 'root2', NULL, '3A19'),
(12345678, 'Chamseddine', 'Chennoufi', 'Admin', 'chamseddine.chennoufi@esprit.tn', 'root', '', ''),
(12345682, 'Khalil', 'Zakareia', 'Admin', 'khalil.zakareia@esprit.tn', 'root82', NULL, NULL),
(123456781, 'Ahmed', 'Bougerra', 'Admin', 'ahmed.bougerra@esprit.tn', 'root81', NULL, NULL);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `classe`
--
ALTER TABLE `classe`
  ADD PRIMARY KEY (`ID_Class`);

--
-- Indexes for table `depense`
--
ALTER TABLE `depense`
  ADD PRIMARY KEY (`id`),
  ADD KEY `idUtilisateur` (`idUtilisateur`),
  ADD KEY `id_finance` (`id_finance`);

--
-- Indexes for table `examen`
--
ALTER TABLE `examen`
  ADD PRIMARY KEY (`id_examen`),
  ADD KEY `id_matiere` (`id_matiere`);

--
-- Indexes for table `fichefinance`
--
ALTER TABLE `fichefinance`
  ADD PRIMARY KEY (`id`),
  ADD KEY `idUtilisateur` (`idUtilisateur`);

--
-- Indexes for table `inscription`
--
ALTER TABLE `inscription`
  ADD PRIMARY KEY (`id_inscri`),
  ADD KEY `id_etud` (`id_etud`),
  ADD KEY `id_ex` (`id_ex`);

--
-- Indexes for table `matiere`
--
ALTER TABLE `matiere`
  ADD PRIMARY KEY (`id_matiere`);

--
-- Indexes for table `note`
--
ALTER TABLE `note`
  ADD PRIMARY KEY (`id_note`),
  ADD KEY `id_examen` (`id_examen`),
  ADD KEY `id_etud` (`id_etud`);

--
-- Indexes for table `reclamation`
--
ALTER TABLE `reclamation`
  ADD PRIMARY KEY (`id`),
  ADD KEY `idUtilisateur` (`idUtilisateur`);

--
-- Indexes for table `recommendation`
--
ALTER TABLE `recommendation`
  ADD PRIMARY KEY (`id`),
  ADD KEY `idUtilisateur` (`idUtilisateur`);

--
-- Indexes for table `revenu`
--
ALTER TABLE `revenu`
  ADD PRIMARY KEY (`id`),
  ADD KEY `idUtilisateur` (`idUtilisateur`),
  ADD KEY `id_finance` (`id_finance`);

--
-- Indexes for table `seance`
--
ALTER TABLE `seance`
  ADD PRIMARY KEY (`ID_Seance`),
  ADD KEY `ID_User` (`ID_User`),
  ADD KEY `ID_Class` (`ID_Class`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`ID_User`),
  ADD UNIQUE KEY `Email` (`Email`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `depense`
--
ALTER TABLE `depense`
  MODIFY `id` bigint(8) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `examen`
--
ALTER TABLE `examen`
  MODIFY `id_examen` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=53;

--
-- AUTO_INCREMENT for table `inscription`
--
ALTER TABLE `inscription`
  MODIFY `id_inscri` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT for table `note`
--
ALTER TABLE `note`
  MODIFY `id_note` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=42;

--
-- AUTO_INCREMENT for table `reclamation`
--
ALTER TABLE `reclamation`
  MODIFY `id` bigint(8) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT for table `recommendation`
--
ALTER TABLE `recommendation`
  MODIFY `id` bigint(8) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `depense`
--
ALTER TABLE `depense`
  ADD CONSTRAINT `fk_ID_User_depense` FOREIGN KEY (`idUtilisateur`) REFERENCES `user` (`ID_User`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_id_finance_depense` FOREIGN KEY (`id_finance`) REFERENCES `fichefinance` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `examen`
--
ALTER TABLE `examen`
  ADD CONSTRAINT `fk_id_matiere_examen` FOREIGN KEY (`id_matiere`) REFERENCES `matiere` (`id_matiere`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `fichefinance`
--
ALTER TABLE `fichefinance`
  ADD CONSTRAINT `fk_ID_User_fichefinance` FOREIGN KEY (`idUtilisateur`) REFERENCES `user` (`ID_User`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `inscription`
--
ALTER TABLE `inscription`
  ADD CONSTRAINT `fk_ID_User_inscription` FOREIGN KEY (`id_etud`) REFERENCES `user` (`ID_User`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_id_ex_inscription` FOREIGN KEY (`id_ex`) REFERENCES `examen` (`id_examen`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `note`
--
ALTER TABLE `note`
  ADD CONSTRAINT `fk_ID_User_note` FOREIGN KEY (`id_etud`) REFERENCES `user` (`ID_User`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_id_ex_note` FOREIGN KEY (`id_examen`) REFERENCES `examen` (`id_examen`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `reclamation`
--
ALTER TABLE `reclamation`
  ADD CONSTRAINT `fk_ID_User_reclamation` FOREIGN KEY (`idUtilisateur`) REFERENCES `user` (`ID_User`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `recommendation`
--
ALTER TABLE `recommendation`
  ADD CONSTRAINT `fk_User_ID_recommendation` FOREIGN KEY (`idUtilisateur`) REFERENCES `user` (`ID_User`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `revenu`
--
ALTER TABLE `revenu`
  ADD CONSTRAINT `fk_ID_User_revenu` FOREIGN KEY (`idUtilisateur`) REFERENCES `user` (`ID_User`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_id_finance_revenu` FOREIGN KEY (`id_finance`) REFERENCES `fichefinance` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `seance`
--
ALTER TABLE `seance`
  ADD CONSTRAINT `fk_ID_Class_Seance` FOREIGN KEY (`ID_Class`) REFERENCES `classe` (`ID_Class`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_ID_User_Seance` FOREIGN KEY (`ID_User`) REFERENCES `user` (`ID_User`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
