SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `renamethis-2514-p4`
--
DROP DATABASE IF EXISTS `p4_Foley_Nicholas`;
CREATE DATABASE IF NOT EXISTS `p4_Foley_Nicholas` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `p4_Foley_Nicholas`;

-- --------------------------------------------------------

--
-- Table structure for table `person`
--

CREATE TABLE `person` (
  `employeeID` int(5) NOT NULL,
  `firstName` varchar(30) NOT NULL,
  `middleName` varchar(30) DEFAULT NULL,
  `lastName` varchar(30) NOT NULL,
  `birthDate` date NOT NULL,
  `hireDate` date DEFAULT NULL,
   `salary` decimal NULL,
    `rate` decimal NULL,
    `avgWeeklyHours` decimal NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `person`
--

INSERT INTO `person` (`employeeID`, `firstName`, `middleName`, `lastName`, `birthDate`, `hireDate`, `salary`, `rate`, `avgWeeklyHours`) VALUES
(65, 'Aaron', 'A', 'Aaronson', '1955-05-05', '1991-02-07', null, null, null),
(66, 'Erin', 'E', 'Erinson', '1963-06-03', '1991-04-15', null, null, null),
(734, 'Roy', NULL, 'Batty', '1962-06-02', '2016-01-09', 75000.00, null, null),
(1313, 'Beatrix', NULL, 'Kiddo', '1982-09-22', '2003-09-10', 85000.00, null, null),
(1985, 'Marty', NULL, 'McFly', '1950-06-01', '1885-01-01', 90000.00, null, null),
(2000, 'Paul', 'Muad\'Dib', 'Atreides', '1963-04-18', '1984-05-05',null, 25.50, 40.00),
(2001, 'Molly', NULL, 'Millions', '1956-03-19', '1995-02-28', null, 45.00, 40.00);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `person`
--
ALTER TABLE `person`
  ADD PRIMARY KEY (`employeeID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `person`
--
ALTER TABLE `person`
  MODIFY `employeeID` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=100003;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
