CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(32) NOT NULL DEFAULT 'someuser',
  `email` varchar(32) NOT NULL,
  `password` varchar(64) NOT NULL,
  `role_user` enum('ROLE_USER','ROLE_ADMIN') NOT NULL DEFAULT 'ROLE_USER',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=125 DEFAULT CHARSET=latin1;
