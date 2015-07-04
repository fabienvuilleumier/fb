
-- -----------------------------------------------------
-- Data for table `fablab`.`t_membership_type`
-- -----------------------------------------------------
START TRANSACTION;
USE `fablab`;
INSERT INTO `fablab`.`t_membership_type` (`membership_type_id`, `name`, `duration`, `price`, `active`) VALUES (1, 'Externe', 1, 10, 1);
INSERT INTO `fablab`.`t_membership_type` (`membership_type_id`, `name`, `duration`, `price`, `active`) VALUES (2, 'Cotisant', 1, 8, 1);
INSERT INTO `fablab`.`t_membership_type` (`membership_type_id`, `name`, `duration`, `price`, `active`) VALUES (3, 'Etudiant', 1, 5, 1);
INSERT INTO `fablab`.`t_membership_type` (`membership_type_id`, `name`, `duration`, `price`, `active`) VALUES (4, 'Soutien', 1, 15, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `fablab`.`t_user`
-- -----------------------------------------------------
START TRANSACTION;
USE `fablab`;
-- pwd = admin
INSERT INTO `fablab`.`t_user` (`user_id`, `membership_type_id`, `auth_by_sql`, `login`, `password_manager`, `password`, `password_salt`, `firstname`, `lastname`, `email`, `date_inscr`, `balance`, `rfid`, `enabled`, `phone`, `address`, `birthdate`, `gender`, `comment`, `active`) VALUES (1, 2, 1, 'admin', '7c0deadab3077e14480bc4cfc80714b7cd8f12bd4c8968f69e4a0053c38867f1', '7c0deadab3077e14480bc4cfc80714b7cd8f12bd4c8968f69e4a0053c38867f1', 'tW7RjN0OvD3ltNFb27fi4nZ39qIhLM1x99ls1qFZ', 'admin', 'admin', 'admin@fablabmanager.ch', '2015-05-26 14:57:12', 0, '120050C9ED65', 1, 'NULL', 'NULL', '2015-01-01', 1, 'NULL', 1);

COMMIT;



-- -----------------------------------------------------
-- Data for table `fablab`.`t_machine_status`
-- -----------------------------------------------------
START TRANSACTION;
USE `fablab`;
INSERT INTO `fablab`.`t_machine_status` (`machine_status_id`, `label`, `color`, `active`) VALUES (1, 'Disponible', '#DFF0D8', 1);
INSERT INTO `fablab`.`t_machine_status` (`machine_status_id`, `label`, `color`, `active`) VALUES (3, 'Indisponible', '#F2DEDE', 1);

COMMIT;

-- -----------------------------------------------------
-- Data for table `fablab`.`t_group`
-- -----------------------------------------------------
START TRANSACTION;
USE `fablab`;
INSERT INTO `fablab`.`t_group` (`group_id`, `technicalname`, `name`, `active`) VALUES (1, 'ADMIN', 'Comité', 1);
INSERT INTO `fablab`.`t_group` (`group_id`, `technicalname`, `name`, `active`) VALUES (2, 'CONFIRME', 'Confirmé', 1);
INSERT INTO `fablab`.`t_group` (`group_id`, `technicalname`, `name`, `active`) VALUES (3, 'MEMBER', 'Membre', 1);

COMMIT;

-- -----------------------------------------------------
-- Data for table `fablab`.`t_role`
-- -----------------------------------------------------
START TRANSACTION;
USE `fablab`;
INSERT INTO `fablab`.`t_role` (`role_id`, `name`, `technicalname`, `active`) VALUES (1, 'Administration', 'ROLE_ADMIN', 1);
INSERT INTO `fablab`.`t_role` (`role_id`, `name`, `technicalname`, `active`) VALUES (2, 'View user', 'ROLE_USER_VIEW', 1);
INSERT INTO `fablab`.`t_role` (`role_id`, `name`, `technicalname`, `active`) VALUES (3, 'Manage user', 'ROLE_USER_MANAGE', 1);
INSERT INTO `fablab`.`t_role` (`role_id`, `name`, `technicalname`, `active`) VALUES (4, 'View machine', 'ROLE_MACHINE_VIEW', 1);
INSERT INTO `fablab`.`t_role` (`role_id`, `name`, `technicalname`, `active`) VALUES (5, 'Manage machine', 'ROLE_MACHINE_MANAGE', 1);
INSERT INTO `fablab`.`t_role` (`role_id`, `name`, `technicalname`, `active`) VALUES (6, 'View payment', 'ROLE_PAYMENT_VIEW', 1);
INSERT INTO `fablab`.`t_role` (`role_id`, `name`, `technicalname`, `active`) VALUES (7, 'Manage payment', 'ROLE_PAYMENT_MANAGE', 1);
INSERT INTO `fablab`.`t_role` (`role_id`, `name`, `technicalname`, `active`) VALUES (8, 'View accounting', 'ROLE_ACCOUNTING_VIEW', 1);
INSERT INTO `fablab`.`t_role` (`role_id`, `name`, `technicalname`, `active`) VALUES (9, 'Manage accounting', 'ROLE_ACCOUNTING_MANAGE', 1);
INSERT INTO `fablab`.`t_role` (`role_id`, `name`, `technicalname`, `active`) VALUES (10, 'View audit', 'ROLE_AUDIT_VIEW', 1);
INSERT INTO `fablab`.`t_role` (`role_id`, `name`, `technicalname`, `active`) VALUES (11, 'View reservation', 'ROLE_RESERVATION_VIEW', 1);
INSERT INTO `fablab`.`t_role` (`role_id`, `name`, `technicalname`, `active`) VALUES (12, 'Use reservation', 'ROLE_RESERVATION_USE', 1);
INSERT INTO `fablab`.`t_role` (`role_id`, `name`, `technicalname`, `active`) VALUES (13, 'Manage reservation', 'ROLE_RESERVATION_MANAGE', 1);
INSERT INTO `fablab`.`t_role` (`role_id`, `name`, `technicalname`, `active`) VALUES (14, 'Manage Mailing list', 'ROLE_MAILINGLIST_MANAGE', 1);
INSERT INTO `fablab`.`t_role` (`role_id`, `name`, `technicalname`, `active`) VALUES (15, 'Ticket View', 'ROLE_TICKET_VIEW', 1);
INSERT INTO `fablab`.`t_role` (`role_id`, `name`, `technicalname`, `active`) VALUES (16, 'Ticket manage', 'ROLE_TICKET_MANAGE', 1);
INSERT INTO `fablab`.`t_role` (`role_id`, `name`, `technicalname`, `active`) VALUES (17, 'Supply view', 'ROLE_SUPPLY_VIEW', 1);
INSERT INTO `fablab`.`t_role` (`role_id`, `name`, `technicalname`, `active`) VALUES (18, 'Supply manage', 'ROLE_SUPPLY_MANAGE', 1);
INSERT INTO `fablab`.`t_role` (`role_id`, `name`, `technicalname`, `active`) VALUES (19, 'Training manage', 'ROLE_TRAINING_MANAGE', 1);
INSERT INTO `fablab`.`t_role` (`role_id`, `name`, `technicalname`, `active`) VALUES (20, 'Training view', 'ROLE_TRAINING_VIEW', 1);
INSERT INTO `fablab`.`t_role` (`role_id`, `name`, `technicalname`, `active`) VALUES (21, 'Group manage', 'ROLE_GROUP_MANAGE', 1);
INSERT INTO `fablab`.`t_role` (`role_id`, `name`, `technicalname`, `active`) VALUES (22, 'Event view', 'ROLE_EVENT_VIEW', 1);
INSERT INTO `fablab`.`t_role` (`role_id`, `name`, `technicalname`, `active`) VALUES (23, 'Event manage', 'ROLE_EVENT_MANAGE', 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `fablab`.`t_configuration`
-- -----------------------------------------------------
START TRANSACTION;
USE `fablab`;
INSERT INTO `fablab`.`t_configuration` (`configuration_id`, `common_name`, `conf_key`, `conf_value`) VALUES (1, 'Nom du FabLab', 'FABLAB_NAME', 'Fablab-Manager');
INSERT INTO `fablab`.`t_configuration` (`configuration_id`, `common_name`, `conf_key`, `conf_value`) VALUES (2, 'Url du site du FabLab', 'FABLAB_URL', 'fablab.ch');
INSERT INTO `fablab`.`t_configuration` (`configuration_id`, `common_name`, `conf_key`, `conf_value`) VALUES (3, 'Temps après lequel l\'entrée d\'une utilisation n\'est plus possible [ENTIER], en jours', 'ACCOUNTING_EDIT_HISTORY_LIMIT', '7');
INSERT INTO `fablab`.`t_configuration` (`configuration_id`, `common_name`, `conf_key`, `conf_value`) VALUES (4, 'Clé d\'api pour Google calendar', 'GOOGLE_CALENDAR_API_KEY', 'AIzaSyAQTP0T3Bf8x5Q0UDx_zMrAdx71VChg2vo');
INSERT INTO `fablab`.`t_configuration` (`configuration_id`, `common_name`, `conf_key`, `conf_value`) VALUES (6, 'Monaie courante', 'CURRENCY', 'CHF');
INSERT INTO `fablab`.`t_configuration` (`configuration_id`, `common_name`, `conf_key`, `conf_value`) VALUES (9, 'Temps limite jusqu\'auquel une réservation est possible [ENTIER], en jours', 'OFFSET_RESERVATION', '10');
INSERT INTO `fablab`.`t_configuration` (`configuration_id`, `common_name`, `conf_key`, `conf_value`) VALUES (12, 'Url du Google calendar', 'GOOGLE_CALENDAR_URL', 'https://www.google.com/calendar/feeds/fablabmanagerhegarc%40gmail.com/public/basic');
INSERT INTO `fablab`.`t_configuration` (`configuration_id`, `common_name`, `conf_key`, `conf_value`) VALUES (13, 'Couleur des events du Google calendar [#COULEUR]', 'CALENDAR_AGENDA_COLOR', '#B2E0FF');
INSERT INTO `fablab`.`t_configuration` (`configuration_id`, `common_name`, `conf_key`, `conf_value`) VALUES (14, 'Couleur des réservations du Google calendar [#COULEUR]', 'CALENDAR_RESERVATION_COLOR', '#FF0000');
INSERT INTO `fablab`.`t_configuration` (`configuration_id`, `common_name`, `conf_key`, `conf_value`) VALUES (15, 'Lors d\'un rabais, l\'affichage propose d\'abord un pourcentage (PERCENT) ou en absolu (MONEY) ? [PERCENT, MONEY]', 'FIRST_PERCENT', 'PERCENT');
INSERT INTO `fablab`.`t_configuration` (`configuration_id`, `common_name`, `conf_key`, `conf_value`) VALUES (16, 'Fuseau horaire du Google calendar [REGION/VILLE DE REFERENCE]', 'CALENDAR_TIME_ZONE', 'Europe/Zurich');

COMMIT;

-- -----------------------------------------------------
-- Data for table `fablab`.`r_group_role`
-- -----------------------------------------------------
START TRANSACTION;
USE `fablab`;
INSERT INTO `fablab`.`r_group_role` (`role_id`, `group_id`) VALUES (1, 1);
INSERT INTO `fablab`.`r_group_role` (`role_id`, `group_id`) VALUES (2, 1);
INSERT INTO `fablab`.`r_group_role` (`role_id`, `group_id`) VALUES (3, 1);
INSERT INTO `fablab`.`r_group_role` (`role_id`, `group_id`) VALUES (4, 1);
INSERT INTO `fablab`.`r_group_role` (`role_id`, `group_id`) VALUES (5, 1);
INSERT INTO `fablab`.`r_group_role` (`role_id`, `group_id`) VALUES (6, 1);
INSERT INTO `fablab`.`r_group_role` (`role_id`, `group_id`) VALUES (7, 1);
INSERT INTO `fablab`.`r_group_role` (`role_id`, `group_id`) VALUES (8, 1);
INSERT INTO `fablab`.`r_group_role` (`role_id`, `group_id`) VALUES (9, 1);
INSERT INTO `fablab`.`r_group_role` (`role_id`, `group_id`) VALUES (10, 1);
INSERT INTO `fablab`.`r_group_role` (`role_id`, `group_id`) VALUES (11, 1);
INSERT INTO `fablab`.`r_group_role` (`role_id`, `group_id`) VALUES (12, 1);
INSERT INTO `fablab`.`r_group_role` (`role_id`, `group_id`) VALUES (13, 1);
INSERT INTO `fablab`.`r_group_role` (`role_id`, `group_id`) VALUES (14, 1);
INSERT INTO `fablab`.`r_group_role` (`role_id`, `group_id`) VALUES (15, 1);
INSERT INTO `fablab`.`r_group_role` (`role_id`, `group_id`) VALUES (16, 1);
INSERT INTO `fablab`.`r_group_role` (`role_id`, `group_id`) VALUES (17, 1);
INSERT INTO `fablab`.`r_group_role` (`role_id`, `group_id`) VALUES (18, 1);
INSERT INTO `fablab`.`r_group_role` (`role_id`, `group_id`) VALUES (19, 1);
INSERT INTO `fablab`.`r_group_role` (`role_id`, `group_id`) VALUES (20, 1);
INSERT INTO `fablab`.`r_group_role` (`role_id`, `group_id`) VALUES (21, 1);
INSERT INTO `fablab`.`r_group_role` (`role_id`, `group_id`) VALUES (22, 1);
INSERT INTO `fablab`.`r_group_role` (`role_id`, `group_id`) VALUES (23, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `fablab`.`r_group_user`
-- -----------------------------------------------------
START TRANSACTION;
USE `fablab`;
INSERT INTO `fablab`.`r_group_user` (`group_id`, `user_id`) VALUES (1, 1);

COMMIT;

-- -----------------------------------------------------
-- Data for table `fablab`.`t_ticket_status`
-- -----------------------------------------------------
START TRANSACTION;
USE `fablab`;
INSERT INTO `fablab`.`t_ticket_status` (`ticket_status_id`, `label`, `active`) VALUES (1, 'OUVERT', 1);
INSERT INTO `fablab`.`t_ticket_status` (`ticket_status_id`, `label`, `active`) VALUES (2, 'CLOS', 1);

COMMIT;