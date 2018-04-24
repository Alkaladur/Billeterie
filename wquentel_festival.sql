-- phpMyAdmin SQL Dump
-- version 4.0.10deb1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Apr 19, 2018 at 03:01 PM
-- Server version: 5.5.43-0ubuntu0.14.04.1
-- PHP Version: 5.5.9-1ubuntu4.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `wquentel_festival`
--

-- --------------------------------------------------------

--
-- Table structure for table `Attribution`
--

CREATE TABLE IF NOT EXISTS `Attribution` (
  `idEtab` char(8) NOT NULL,
  `idTypeChambre` char(2) NOT NULL,
  `idGroupe` char(4) NOT NULL,
  `nombreChambres` int(11) NOT NULL,
  PRIMARY KEY (`idEtab`,`idTypeChambre`,`idGroupe`),
  KEY `idTypeChambre` (`idTypeChambre`),
  KEY `idGroupe` (`idGroupe`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `Attribution`
--

INSERT INTO `Attribution` (`idEtab`, `idTypeChambre`, `idGroupe`, `nombreChambres`) VALUES
('0350773A', '6', 'g007', 15),
('0350773A', 'C2', 'g003', 9),
('0350773A', 'C2', 'g004', 2),
('0350773A', 'C2', 'g012', 3),
('0350773A', 'C2', 'g016', 1),
('0350773A', 'C3', 'g005', 1),
('0350785N', 'C1', 'g001', 1),
('0350785N', 'C1', 'g002', 2),
('0350785N', 'C1', 'g003', 2),
('0350785N', 'C2', 'g001', 2),
('0350785N', 'C2', 'g002', 1),
('0350785N', 'C3', 'g001', 2),
('0350785N', 'C3', 'g002', 1),
('0350785N', 'C3', 'g006', 2),
('0352072M', 'C1', 'g006', 1),
('0352072M', 'C2', 'g007', 3),
('0352072M', 'C3', 'g006', 3);

-- --------------------------------------------------------

--
-- Table structure for table `Etablissement`
--

CREATE TABLE IF NOT EXISTS `Etablissement` (
  `id` char(8) NOT NULL,
  `nom` varchar(45) NOT NULL,
  `adresseRue` varchar(45) NOT NULL,
  `codePostal` char(5) NOT NULL,
  `ville` varchar(35) NOT NULL,
  `tel` varchar(13) NOT NULL,
  `adresseElectronique` varchar(70) DEFAULT NULL,
  `type` tinyint(4) NOT NULL,
  `civiliteResponsable` varchar(12) NOT NULL,
  `nomResponsable` varchar(25) NOT NULL,
  `prenomResponsable` varchar(25) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `Etablissement`
--

INSERT INTO `Etablissement` (`id`, `nom`, `adresseRue`, `codePostal`, `ville`, `tel`, `adresseElectronique`, `type`, `civiliteResponsable`, `nomResponsable`, `prenomResponsable`) VALUES
('0350773A', 'Collège Ste Jeanne d''Arc-Choisy', '3, avenue de la Borderie BP 32', '35404', 'Paramé', '0299560159', NULL, 1, 'Madame', 'LEFORT', 'Anne'),
('0350785N', 'Collège de Moka', '2 avenue Aristide Briand BP 6', '35401', 'Saint-Malo', '0299206990', NULL, 1, 'Monsieur', 'DUPONT', 'Alain'),
('0352072M', 'Institution Saint-Malo Providence', '2 rue du collège BP 31863', '35418', 'Saint-Malo', '0299407474', NULL, 1, 'Monsieur', 'DURAND', 'Pierre');

-- --------------------------------------------------------

--
-- Table structure for table `Groupe`
--

CREATE TABLE IF NOT EXISTS `Groupe` (
  `id` char(4) NOT NULL,
  `nom` varchar(40) NOT NULL,
  `identiteResponsable` varchar(40) DEFAULT NULL,
  `adressePostale` varchar(120) DEFAULT NULL,
  `nombrePersonnes` int(11) NOT NULL,
  `nomPays` varchar(40) NOT NULL,
  `hebergement` char(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `Groupe`
--

INSERT INTO `Groupe` (`id`, `nom`, `identiteResponsable`, `adressePostale`, `nombrePersonnes`, `nomPays`, `hebergement`) VALUES
('g001', 'Groupe folklorique du Bachkortostan', NULL, NULL, 40, 'Bachkirie', 'O'),
('g002', 'Marina Prudencio Chavez', NULL, NULL, 25, 'Bolivie', 'O'),
('g003', 'Nangola Bahia de Salvador', NULL, NULL, 34, 'Brésil', 'O'),
('g004', 'Bizone de Kawarma', NULL, NULL, 38, 'Bulgarie', 'O'),
('g005', 'Groupe folklorique camerounais', NULL, NULL, 22, 'Cameroun', 'O'),
('g006', 'Syoung Yaru Mask Dance Group', NULL, NULL, 29, 'Corée du Sud', 'O'),
('g007', 'Pipe Band', NULL, NULL, 19, 'Ecosse', 'O'),
('g008', 'Aira da Pedra', NULL, NULL, 5, 'Espagne', 'O'),
('g009', 'The Jersey Caledonian Pipe Band', NULL, NULL, 21, 'Jersey', 'O'),
('g010', 'Groupe folklorique des Émirats', NULL, NULL, 30, 'Emirats arabes unis', 'O'),
('g011', 'Groupe folklorique mexicain', NULL, NULL, 38, 'Mexique', 'O'),
('g012', 'Groupe folklorique de Panama', NULL, NULL, 22, 'Panama', 'O'),
('g013', 'Groupe folklorique papou', NULL, NULL, 13, 'Papouasie', 'O'),
('g014', 'Paraguay Ete', NULL, NULL, 26, 'Paraguay', 'O'),
('g015', 'La Tuque Bleue', NULL, NULL, 8, 'Québec', 'O'),
('g016', 'Ensemble Leissen de Oufa', NULL, NULL, 40, 'République de Bachkirie', 'O'),
('g017', 'Groupe folklorique turc', NULL, NULL, 40, 'Turquie', 'O'),
('g018', 'Groupe folklorique russe', NULL, NULL, 43, 'Russie', 'O'),
('g019', 'Ruhunu Ballet du village de Kosgoda', NULL, NULL, 27, 'Sri Lanka', 'O'),
('g020', 'L''Alen', NULL, NULL, 34, 'France - Provence', 'O'),
('g021', 'L''escolo Di Tourre', NULL, NULL, 40, 'France - Provence', 'O'),
('g022', 'Deloubes Kévin', NULL, NULL, 1, 'France - Bretagne', 'O'),
('g023', 'Daonie See', NULL, NULL, 5, 'France - Bretagne', 'O'),
('g024', 'Boxty', NULL, NULL, 5, 'France - Bretagne', 'O'),
('g025', 'Soeurs Chauvel', NULL, NULL, 2, 'France - Bretagne', 'O'),
('g026', 'Cercle Gwik Alet', NULL, NULL, 0, 'France - Bretagne', 'N'),
('g027', 'Bagad Quic En Groigne', NULL, NULL, 0, 'France - Bretagne', 'N'),
('g028', 'Penn Treuz', NULL, NULL, 0, 'France - Bretagne', 'N'),
('g029', 'Savidan Launay', NULL, NULL, 0, 'France - Bretagne', 'N'),
('g030', 'Cercle Boked Er Lann', NULL, NULL, 0, 'France - Bretagne', 'N'),
('g031', 'Bagad Montfortais', NULL, NULL, 0, 'France - Bretagne', 'N'),
('g032', 'Vent de Noroise', NULL, NULL, 0, 'France - Bretagne', 'N'),
('g033', 'Cercle Strollad', NULL, NULL, 0, 'France - Bretagne', 'N'),
('g034', 'Bagad An Hanternoz', NULL, NULL, 0, 'France - Bretagne', 'N'),
('g035', 'Cercle Ar Vro Melenig', NULL, NULL, 0, 'France - Bretagne', 'N'),
('g036', 'Cercle An Abadenn Nevez', NULL, NULL, 0, 'France - Bretagne', 'N'),
('g037', 'Kerc''h Keltiek Roazhon', NULL, NULL, 0, 'France - Bretagne', 'N'),
('g038', 'Bagad Plougastel', NULL, NULL, 0, 'France - Bretagne', 'N'),
('g039', 'Bagad Nozeganed Bro Porh-Loeiz', NULL, NULL, 0, 'France - Bretagne', 'N'),
('g040', 'Bagad Nozeganed Bro Porh-Loeiz', NULL, NULL, 0, 'France - Bretagne', 'N'),
('g041', 'Jackie Molard Quartet', NULL, NULL, 0, 'France - Bretagne', 'N'),
('g042', 'Deomp', NULL, NULL, 0, 'France - Bretagne', 'N'),
('g043', 'Cercle Olivier de Clisson', NULL, NULL, 0, 'France - Bretagne', 'N'),
('g044', 'Kan Tri', NULL, NULL, 0, 'France - Bretagne', 'N');

-- --------------------------------------------------------

--
-- Table structure for table `Lieu`
--

CREATE TABLE IF NOT EXISTS `Lieu` (
  `id` int(11) NOT NULL,
  `nomLieu` varchar(100) DEFAULT NULL,
  `adr` varchar(100) DEFAULT NULL,
  `capacite` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `Lieu`
--

INSERT INTO `Lieu` (`id`, `nomLieu`, `adr`, `capacite`) VALUES
(1, 'SALLE DU PANIER FLEURI', 'Rue de Bonneville', 450),
(2, 'LE CABARET', 'MAIRIE ANNEXE DE PARAME, Place Georges COUDRAY', 250),
(3, 'LE PARC DES CHENES', '14 rue des chênes', 2000),
(4, 'LE VILLAGE', 'Ecole LEGATELOIS, 25 rue Général de Castelnau', 500);

-- --------------------------------------------------------

--
-- Table structure for table `Offre`
--

CREATE TABLE IF NOT EXISTS `Offre` (
  `idEtab` char(8) NOT NULL,
  `idTypeChambre` char(2) NOT NULL,
  `nombreChambres` int(11) NOT NULL,
  PRIMARY KEY (`idEtab`,`idTypeChambre`),
  KEY `idTypeChambre` (`idTypeChambre`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `Offre`
--

INSERT INTO `Offre` (`idEtab`, `idTypeChambre`, `nombreChambres`) VALUES
('0350773A', '6', 777),
('0350773A', 'C2', 15),
('0350773A', 'C3', 1),
('0350785N', 'C1', 5),
('0350785N', 'C2', 10),
('0350785N', 'C3', 5),
('0352072M', 'C1', 5),
('0352072M', 'C2', 10),
('0352072M', 'C3', 3);

-- --------------------------------------------------------

--
-- Table structure for table `Representation`
--

CREATE TABLE IF NOT EXISTS `Representation` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `Lieu` int(11) DEFAULT NULL,
  `Groupe` varchar(4) DEFAULT NULL,
  `date` date DEFAULT NULL,
  `heuredebut` time DEFAULT NULL,
  `heurefin` time DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=27 ;

--
-- Dumping data for table `Representation`
--

INSERT INTO `Representation` (`id`, `Lieu`, `Groupe`, `date`, `heuredebut`, `heurefin`) VALUES
(1, 1, 'g012', '2017-07-11', '20:30:00', '21:45:00'),
(2, 1, 'g014', '2017-07-11', '21:45:00', '23:00:00'),
(3, 2, 'g024', '2017-07-11', '19:00:00', '20:00:00'),
(4, 2, 'g003', '2017-07-11', '20:30:00', '21:30:00'),
(5, 2, 'g004', '2017-07-11', '21:45:00', '23:15:00'),
(6, 3, 'g031', '2017-07-11', '11:00:00', '12:00:00'),
(7, 3, 'g035', '2017-07-11', '12:00:00', '13:00:00'),
(8, 1, 'g008', '2017-07-12', '20:30:00', '22:00:00'),
(9, 1, 'g009', '2017-07-12', '22:15:00', '23:30:00'),
(10, 2, 'g005', '2017-07-12', '20:00:00', '23:00:00'),
(11, 1, 'g006', '2017-07-13', '20:30:00', '22:00:00'),
(12, 2, 'g041', '2017-07-13', '20:30:00', '22:00:00'),
(13, 1, 'g020', '2017-07-14', '19:30:00', '21:00:00'),
(14, 1, 'g022', '2017-07-14', '21:15:00', '23:00:00'),
(15, 3, 'g010', '2017-07-14', '14:00:00', '14:30:00'),
(16, 3, 'g011', '2017-07-14', '14:30:00', '15:00:00'),
(17, 3, 'g012', '2017-07-14', '15:00:00', '15:30:00'),
(18, 3, 'g013', '2017-07-14', '15:30:00', '16:00:00'),
(19, 3, 'g017', '2017-07-14', '16:00:00', '16:30:00'),
(20, 3, 'g018', '2017-07-14', '16:30:00', '17:00:00'),
(21, 4, 'g032', '2017-07-14', '11:00:00', '12:00:00'),
(22, 4, 'g044', '2017-07-14', '15:00:00', '17:00:00'),
(23, 4, 'g042', '2017-07-14', '17:30:00', '19:30:00'),
(24, 4, 'g037', '2017-07-15', '11:00:00', '12:30:00'),
(25, 4, 'g025', '2017-07-15', '15:00:00', '16:00:00'),
(26, 4, 'g029', '2017-07-15', '16:30:00', '19:00:00');

-- --------------------------------------------------------

--
-- Table structure for table `TypeChambre`
--

CREATE TABLE IF NOT EXISTS `TypeChambre` (
  `id` char(2) NOT NULL,
  `libelle` varchar(15) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `TypeChambre`
--

INSERT INTO `TypeChambre` (`id`, `libelle`) VALUES
('6', '7 lits'),
('C1', '1 lit'),
('C2', '2 à 3 lits'),
('C3', '4 à 5 lits'),
('C4', '6 à 8 lits'),
('C5', '8 à 12 lits');

--
-- Constraints for dumped tables
--

--
-- Constraints for table `Attribution`
--
ALTER TABLE `Attribution`
  ADD CONSTRAINT `fk1_Attribution` FOREIGN KEY (`idGroupe`) REFERENCES `Groupe` (`id`),
  ADD CONSTRAINT `fk2_Attribution` FOREIGN KEY (`idEtab`, `idTypeChambre`) REFERENCES `Offre` (`idEtab`, `idTypeChambre`);

--
-- Constraints for table `Offre`
--
ALTER TABLE `Offre`
  ADD CONSTRAINT `fk1_Offre` FOREIGN KEY (`idEtab`) REFERENCES `Etablissement` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk2_Offre` FOREIGN KEY (`idTypeChambre`) REFERENCES `TypeChambre` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
