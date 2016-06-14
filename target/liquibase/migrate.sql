--  *********************************************************************
--  Rollback 1 Change(s) Script
--  *********************************************************************
--  Change Log: src/main/resources/migrations.xml
--  Ran at: 14/06/16 09:59
--  Against: liquibaseUser@localhost@jdbc:mysql://localhost:3306/DropBookmarks
--  Liquibase version: 3.4.1
--  *********************************************************************

--  Lock Database
UPDATE DATABASECHANGELOGLOCK SET LOCKED = 1, LOCKEDBY = 'fe80:0:0:0:4023:45ff:fe34:b3ef%8 (fe80:0:0:0:4023:45ff:fe34:b3ef%8)', LOCKGRANTED = '2016-06-14 09:59:57.040' WHERE ID = 1 AND LOCKED = 0;

--  Rolling Back ChangeSet: src/main/resources/migrations.xml::3::lucy
DELETE FROM users WHERE id=1;

DELETE FROM DATABASECHANGELOG WHERE ID = '3' AND AUTHOR = 'lucy' AND FILENAME = 'src/main/resources/migrations.xml';

--  Release Database Lock
UPDATE DATABASECHANGELOGLOCK SET LOCKED = 0, LOCKEDBY = NULL, LOCKGRANTED = NULL WHERE ID = 1;

