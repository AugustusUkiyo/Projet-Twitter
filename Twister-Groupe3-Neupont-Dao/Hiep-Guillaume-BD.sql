-- phpMyAdmin SQL Dump
-- version 4.6.6deb4
-- https://www.phpmyadmin.net/
--
-- Client :  localhost:3306
-- Généré le :  Mar 16 Avril 2019 à 13:49
-- Version du serveur :  5.7.22
-- Version de PHP :  7.0.33-0+deb9u3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `Hiep-Guillaume-BD`
--

-- --------------------------------------------------------

--
-- Structure de la table `Friends`
--

CREATE TABLE `Friends` (
  `id_user` int(11) NOT NULL,
  `id_friend` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `Friends`
--

INSERT INTO `Friends` (`id_user`, `id_friend`) VALUES
(1, 2),
(3, 2),
(9, 2),
(1, 3),
(9, 3),
(1, 4),
(2, 4),
(1, 5),
(2, 5),
(1, 6),
(1, 7),
(2, 7),
(1, 8),
(1, 9),
(1, 10),
(1, 11),
(1, 12),
(3, 16),
(3, 18);

-- --------------------------------------------------------

--
-- Structure de la table `Sessions`
--

CREATE TABLE `Sessions` (
  `id_user` int(11) NOT NULL,
  `cle` int(11) NOT NULL,
  `date_deb` datetime DEFAULT NULL,
  `date_fin` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `Users`
--

CREATE TABLE `Users` (
  `id_user` int(11) NOT NULL,
  `login` varchar(32) NOT NULL,
  `password` varchar(255) NOT NULL,
  `nom` varchar(255) DEFAULT NULL,
  `prenom` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `Users`
--

INSERT INTO `Users` (`id_user`, `login`, `password`, `nom`, `prenom`) VALUES
(1, 'abc', 'abc123', 'nomABC', 'prenomABC'),
(2, 'def', 'def123', 'nomDEF', 'prenomDEF'),
(3, 'ghi', 'ghi123', 'nomGHI', 'prenomGHI'),
(4, 'jkl', 'jkl123', 'nomJKL', 'prenomJKL'),
(5, 'mno', 'mno123', 'nomMNO', 'prenomMNO'),
(6, 'pqr', 'pqr123', 'nomPQR', 'prenomPQR'),
(7, 'stu', 'stu123', 'nomSTU', 'prenomSTU'),
(8, 'vwx', 'vwx123', 'nomVWX', 'prenomVWX'),
(9, 'yz', 'yz123', 'nomYZ', 'prenomYZ'),
(10, 'cba', 'cba123', 'nomCBA', 'prenomCBA'),
(11, 'fed', 'fed123', 'nomFED', 'prenomFED'),
(12, 'ihg', 'ihg123', 'nomIHG', 'prenomIHG'),
(13, 'lkj', 'lkj123', 'nomLKJ', 'prenomLKJ'),
(14, 'onm', 'ONM123', 'nomONM', 'prenomONM'),
(15, 'rqp', 'rqp123', 'nomRQP', 'prenomRQP'),
(16, 'uts', 'uts123', 'nomUTS', 'prenomUTS'),
(17, 'xwv', 'xwv123', 'nomXWV', 'prenomXWV'),
(18, 'zy', 'zy123', 'nomZY', 'prenomZY');

--
-- Index pour les tables exportées
--

--
-- Index pour la table `Friends`
--
ALTER TABLE `Friends`
  ADD PRIMARY KEY (`id_user`,`id_friend`),
  ADD KEY `Friends` (`id_friend`);

--
-- Index pour la table `Sessions`
--
ALTER TABLE `Sessions`
  ADD PRIMARY KEY (`id_user`,`cle`),
  ADD UNIQUE KEY `cle` (`cle`);

--
-- Index pour la table `Users`
--
ALTER TABLE `Users`
  ADD PRIMARY KEY (`id_user`),
  ADD UNIQUE KEY `login` (`login`);

--
-- AUTO_INCREMENT pour les tables exportées
--

--
-- AUTO_INCREMENT pour la table `Users`
--
ALTER TABLE `Users`
  MODIFY `id_user` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;
--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `Friends`
--
ALTER TABLE `Friends`
  ADD CONSTRAINT `Friends_ibfk_1` FOREIGN KEY (`id_user`) REFERENCES `Users` (`id_user`),
  ADD CONSTRAINT `Friends_ibfk_2` FOREIGN KEY (`id_friend`) REFERENCES `Users` (`id_user`);

--
-- Contraintes pour la table `Sessions`
--
ALTER TABLE `Sessions`
  ADD CONSTRAINT `Sessions_ibfk_1` FOREIGN KEY (`id_user`) REFERENCES `Users` (`id_user`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
