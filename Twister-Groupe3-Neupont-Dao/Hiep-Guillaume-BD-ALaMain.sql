create table Users (
	id_user integer primary key AUTO_INCREMENT,
	login varchar(32) unique not null,
	password varchar(255) not null,
	nom varchar(255),
	prenom varchar(255)
);


INSERT INTO `Users` (`id_user`, `login`, `password`, `prenom`, `nom`) VALUES ('1', 'abc', 'abc123', 'prenomABC', 'nomABC');
INSERT INTO `Users` (`id_user`, `login`, `password`, `prenom`, `nom`) VALUES ('2', 'def', 'def123', 'prenomDEF', 'nomDEF');
INSERT INTO `Users` (`id_user`, `login`, `password`, `prenom`, `nom`) VALUES ('3', 'ghi', 'ghi123', 'prenomGHI', 'nomGHI');
INSERT INTO `Users` (`id_user`, `login`, `password`, `prenom`, `nom`) VALUES ('4', 'jkl', 'jkl123', 'prenomJKL', 'nomJKL');
INSERT INTO `Users` (`id_user`, `login`, `password`, `prenom`, `nom`) VALUES ('5', 'mno', 'mno123', 'prenomMNO', 'nomMNO');
INSERT INTO `Users` (`id_user`, `login`, `password`, `prenom`, `nom`) VALUES ('6', 'pqr', 'pqr123', 'prenomPQR', 'nomPQR');
INSERT INTO `Users` (`id_user`, `login`, `password`, `prenom`, `nom`) VALUES ('7', 'stu', 'stu123', 'prenomSTU', 'nomSTU');
INSERT INTO `Users` (`id_user`, `login`, `password`, `prenom`, `nom`) VALUES ('8', 'vwx', 'vwx123', 'prenomVWX', 'nomVWX');
INSERT INTO `Users` (`id_user`, `login`, `password`, `prenom`, `nom`) VALUES ('9', 'yz', 'yz123', 'prenomYZ', 'nomYZ');
INSERT INTO `Users` (`id_user`, `login`, `password`, `prenom`, `nom`) VALUES ('10', 'cba', 'cba123', 'prenomCBA', 'nomCBA');
INSERT INTO `Users` (`id_user`, `login`, `password`, `prenom`, `nom`) VALUES ('11', 'fed', 'fed123', 'prenomFED', 'nomFED');
INSERT INTO `Users` (`id_user`, `login`, `password`, `prenom`, `nom`) VALUES ('12', 'ihg', 'ihg123', 'prenomIHG', 'nomIHG');
INSERT INTO `Users` (`id_user`, `login`, `password`, `prenom`, `nom`) VALUES ('13', 'lkj', 'lkj123', 'prenomLKJ', 'nomLKJ');
INSERT INTO `Users` (`id_user`, `login`, `password`, `prenom`, `nom`) VALUES ('14', 'onm', 'ONM123', 'prenomONM', 'nomONM');
INSERT INTO `Users` (`id_user`, `login`, `password`, `prenom`, `nom`) VALUES ('15', 'rqp', 'rqp123', 'prenomRQP', 'nomRQP');
INSERT INTO `Users` (`id_user`, `login`, `password`, `prenom`, `nom`) VALUES ('16', 'uts', 'uts123', 'prenomUTS', 'nomUTS');
INSERT INTO `Users` (`id_user`, `login`, `password`, `prenom`, `nom`) VALUES ('17', 'xwv', 'xwv123', 'prenomXWV', 'nomXWV');
INSERT INTO `Users` (`id_user`, `login`, `password`, `prenom`, `nom`) VALUES ('18', 'zy', 'zy123', 'prenomZY', 'nomZY');

create TABLE Friends (
    id_user integer,
    FOREIGN KEY Friends(id_user) REFERENCES Users(id_user),
    id_friend integer, 
    FOREIGN KEY Friends(id_friend) REFERENCES Users(id_user),
	PRIMARY KEY(id_user, id_friend)
);

INSERT INTO `Friends` (`id_user`, `id_friend`) VALUES ('1', '2');
INSERT INTO `Friends` (`id_user`, `id_friend`) VALUES ('1', '3');
INSERT INTO `Friends` (`id_user`, `id_friend`) VALUES ('1', '4');
INSERT INTO `Friends` (`id_user`, `id_friend`) VALUES ('1', '5');
INSERT INTO `Friends` (`id_user`, `id_friend`) VALUES ('1', '6');
INSERT INTO `Friends` (`id_user`, `id_friend`) VALUES ('1', '7');
INSERT INTO `Friends` (`id_user`, `id_friend`) VALUES ('1', '8');
INSERT INTO `Friends` (`id_user`, `id_friend`) VALUES ('1', '9');
INSERT INTO `Friends` (`id_user`, `id_friend`) VALUES ('1', '10');
INSERT INTO `Friends` (`id_user`, `id_friend`) VALUES ('1', '11');
INSERT INTO `Friends` (`id_user`, `id_friend`) VALUES ('1', '12');
INSERT INTO `Friends` (`id_user`, `id_friend`) VALUES ('2', '4');
INSERT INTO `Friends` (`id_user`, `id_friend`) VALUES ('2', '5');
INSERT INTO `Friends` (`id_user`, `id_friend`) VALUES ('2', '7');
INSERT INTO `Friends` (`id_user`, `id_friend`) VALUES ('3', '2');
INSERT INTO `Friends` (`id_user`, `id_friend`) VALUES ('3', '16');
INSERT INTO `Friends` (`id_user`, `id_friend`) VALUES ('3', '18');
INSERT INTO `Friends` (`id_user`, `id_friend`) VALUES ('9', '2');
INSERT INTO `Friends` (`id_user`, `id_friend`) VALUES ('9', '3');

create TABLE Sessions (
	id_user int ,
    FOREIGN KEY Sessions(id_user) REFERENCES Users(id_user),
	cle int UNIQUE,
	date_deb datetime,
	date_fin datetime,
	PRIMARY KEY(id_user,cle)
);	

--------------------------------------------

MongoDB

1) Créer la database

use Hiep-Guillaume-MDB

2) Ajouter dans la table comments

db.comments.insert({author_id:"1",author_name:"nomABC",date:"2019-02-20",texte:"toto"})
db.comments.insert({author_id:"1",author_name:"nomABC",date:"2019-02-21",texte:"titi"})
db.comments.insert({author_id:"1",author_name:"nomABC",date:"2019-02-22",texte:"tata"})
db.comments.insert({author_id:"2",author_name:"nomDEF",date:"2019-02-20",texte:"salut"})
db.comments.insert({author_id:"2",author_name:"nomDEF",date:"2019-02-21",texte:"ça va?"})
db.comments.insert({author_id:"3",author_name:"nomGHI",date:"2019-02-22",texte:"hello"})
db.comments.insert({author_id:"4",author_name:"nomJKL",date:"2019-02-20",texte:"incroyable !"})
db.comments.insert({author_id:"5",author_name:"nomMNO",date:"2019-02-21",texte:"Bravo"})
db.comments.insert({author_id:"5",author_name:"nomMNO",date:"2019-02-22",texte:"A la prochaine"})

3) Visualiser la table comments

db.comments.find()






























































