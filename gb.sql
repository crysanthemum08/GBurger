
SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `gb`
--

-- --------------------------------------------------------

--
-- Table structure for table `gblogin`
--

CREATE TABLE `gblogin` (
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `usertype` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `gblogin`
--

INSERT INTO `gblogin` (`username`, `password`, `usertype`) VALUES
('admin', 'admin123', 'admin123'),
('admin', 'admin123', 'Admin');

-- --------------------------------------------------------

--
-- Table structure for table `gb_items`
--

CREATE TABLE `gb_items` (
  `item_name` varchar(100) NOT NULL,
  `price` int(50) NOT NULL,
  `stocks` int(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `gb_items`
--

INSERT INTO `gb_items` (`item_name`, `price`, `stocks`) VALUES
('Chili Burger w/ Pepper Relish', 59, 8),
('Lamb and Tomato Stuff Burger', 69, 10),
('Crunchy Chicken and Fish Burger', 49, 0),
('Chicken Feta Cheese Burger ', 69, 0),
('Lentil and Mushroom Burger', 59, 0),
('Stuffed Bean Burger', 59, 0),
('Lamb Burger w/ Radish Slaw', 89, 0),
('Potato Corn Burger', 59, 0),
('Supreme Veggie Burger', 59, 0),
('Butter Chicken Twin Burgers', 79, 0),
('Pizza Burger', 99, 0),
('Gara\'s Whoppie Burger', 89, 0),
('Coke', 30, 0),
('Sprite', 30, 0),
('Fanta', 25, 0),
('Nestea', 25, 0);

-- --------------------------------------------------------

--
-- Table structure for table `gb_queue`
--

CREATE TABLE `gb_queue` (
  `id` int(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `transactions`
--

CREATE TABLE `transactions` (
  `Receipt_Number` int(255) NOT NULL,
  `Date` date NOT NULL DEFAULT current_timestamp(),
  `Time` time NOT NULL DEFAULT current_timestamp(),
  `Items` varchar(255) NOT NULL,
  `Total` int(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `transactions`
--

INSERT INTO `transactions` (`Receipt_Number`, `Date`, `Time`, `Items`, `Total`) VALUES
(71450, '2025-05-26', '22:52:32', 'Chili Burger w/ Pepper Relish', 118),
(69680, '2025-05-26', '22:53:59', 'Lamb and Tomato Stuff Burger', 1380),
(63010, '2025-05-26', '22:54:14', 'Lamb and Tomato Stuff Burger', 2560),
(63010, '2025-05-26', '22:54:14', 'Chili Burger w/ Pepper Relish', 2560);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `gb_queue`
--
ALTER TABLE `gb_queue`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `gb_queue`
--
ALTER TABLE `gb_queue`
  MODIFY `id` int(255) NOT NULL AUTO_INCREMENT;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
