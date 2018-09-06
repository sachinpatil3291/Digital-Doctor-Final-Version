/*1. DDL for clinic table*/

CREATE TABLE `clinic` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Address` varchar(255) DEFAULT NULL,
  `Contact` varchar(255) DEFAULT NULL,
  `ImageURL` varchar(500) DEFAULT NULL,
  `Name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1

========================================================================

/*2. DDL for doctor table*/
CREATE TABLE `doctor` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `ClinicId` int(2) DEFAULT NULL,
  `Contact` varchar(255) DEFAULT NULL,
  `Degree` varchar(255) DEFAULT NULL,
  `Email` varchar(255) DEFAULT NULL,
  `Name` varchar(255) DEFAULT NULL,
  `ImageURL` varchar(500) DEFAULT NULL,
  `Specialist` varchar(255) DEFAULT NULL,
  `ClinicName` varchar(20) DEFAULT NULL,
   `Awards` varchar(500) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1
==========================================================================
/*3. DDL for patient table*/
CREATE TABLE `patient` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `FirstName` varchar(45) NOT NULL,
  `LastName` varchar(45) NOT NULL,
  `SFHC` varchar(6) NOT NULL,
  `Address` varchar(100) DEFAULT NULL,
  `Contact` varchar(13) DEFAULT NULL,
  `Password` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`Id`),
  UNIQUE KEY `SFHC_UNIQUE` (`SFHC`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1

==========================================================================
/*4. DDL for prescription table*/

CREATE TABLE `prescription` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `SFHC` varchar(6) NOT NULL,
  `PatientId` int(11) NOT NULL,
  `DoctorId` int(11) NOT NULL,
  `DoctorName` varchar(45) NOT NULL,
  `IllnessType` varchar(500) DEFAULT NULL,
  `MedProvided` varchar(500) DEFAULT NULL,
  `MedAdvised` varchar(500) DEFAULT NULL,
  `Date` datetime DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1

============================================================================
/*5. DDL for report table*/
CREATE TABLE `report` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `SFHC` varchar(6) NOT NULL,
  `DoctorId` int(11) NOT NULL,
  `PatientId` int(11) NOT NULL,
  `ImageURL` varchar(200) NOT NULL,
  `Date` date NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1

==========================================================================
/*6. DDL for post table*/
CREATE TABLE `post` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Title` varchar(500) NOT NULL,
  `Description` varchar(500) NOT NULL,
  `Link` varchar(200) NOT NULL,
  `DoctorId` int(10) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1




