-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Oct 08, 2021 at 07:49 AM
-- Server version: 10.4.20-MariaDB
-- PHP Version: 7.4.21

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `agendist`
--

-- --------------------------------------------------------

--
-- Table structure for table `penjualan_detail`
--

CREATE TABLE `penjualan_detail` (
  `id` varchar(16) DEFAULT NULL,
  `barcode` varchar(16) DEFAULT NULL,
  `nama` varchar(32) DEFAULT NULL,
  `harga` int(11) DEFAULT NULL,
  `qty` int(11) DEFAULT NULL,
  `netto` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `penjualan_detail`
--

INSERT INTO `penjualan_detail` (`id`, `barcode`, `nama`, `harga`, `qty`, `netto`) VALUES
('PJ00000001', '2021100601', 'SURYA', 55000, 5, 275000),
('PJ00000002', '2021100601', 'SURYA', 55000, 1, 55000),
('PJ00000002', '2021100602', 'GUDANG GARAM', 110000, 1, 110000),
('PJ00000003', '2021100602', 'GUDANG GARAM', 110000, 1, 110000),
('PJ00000003', '2021100601', 'SURYA', 55000, 2, 110000),
('PJ00000004', '2021100601', 'SURYA', 55000, 5, 275000),
('PJ00000004', '2021100602', 'GUDANG GARAM', 110000, 1, 110000),
('PJ00000007', '2021100703', 'OREO', 38000, 10, 380000),
('PJ00000008', '2021100602', 'GUDANG GARAM', 110000, 10, 1100000),
('PJ00000009', '2021100601', 'SURYA', 55000, 8, 440000),
('PJ00000009', '2021100703', 'OREO', 38000, 10, 380000),
('PJ00000010', '2021100601', 'TEH PUCUK', 55000, 20, 1100000),
('PJ00000012', '2021100703', 'OREO', 38000, 80, 3040000),
('PJ00000013', '2021100703', 'OREO', 38000, 12, 456000),
('PJ00000014', '2021100703', 'OREO', 38000, 10, 380000),
('PJ00000014', '2021100601', 'TEH PUCUK', 55000, 12, 660000),
('PJ00000015', 'MB00000704', 'GOOD DAY', 70000, 10, 700000),
('PJ00000015', '2021100601', 'TEH PUCUK', 55000, 10, 550000),
('PJ00000016', 'MB0007', 'OKKY BIG', 32000, 20, 640000);

--
-- Triggers `penjualan_detail`
--
DELIMITER $$
CREATE TRIGGER `stok_keluar` AFTER INSERT ON `penjualan_detail` FOR EACH ROW BEGIN
UPDATE product SET stok = stok - NEW.qty
WHERE id = NEW.barcode;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `penjualan_header`
--

CREATE TABLE `penjualan_header` (
  `id` varchar(16) NOT NULL,
  `kasir` varchar(16) DEFAULT NULL,
  `tanggal` varchar(16) DEFAULT NULL,
  `jam` varchar(8) DEFAULT NULL,
  `total_barang` int(11) DEFAULT NULL,
  `total_qty` int(11) DEFAULT NULL,
  `total_harga` int(11) DEFAULT NULL,
  `pembayaran` varchar(16) DEFAULT NULL,
  `bank` varchar(16) DEFAULT 'N',
  `nomor` varchar(16) DEFAULT 'N',
  `nominal` int(11) DEFAULT NULL,
  `kembali` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `penjualan_header`
--

INSERT INTO `penjualan_header` (`id`, `kasir`, `tanggal`, `jam`, `total_barang`, `total_qty`, `total_harga`, `pembayaran`, `bank`, `nomor`, `nominal`, `kembali`) VALUES
('PJ00000001', 'jLabel4', '2021-10-07', '02:10', 1, 5, 275000, 'Tunai', 'Item 1', '', 280000, 5000),
('PJ00000002', 'jLabel4', '2021-37-07', '02:37', 2, 2, 165000, 'Tunai', '-', 'N', 170000, 5000),
('PJ00000003', 'jLabel4', '2021-53-07', '02:53', 2, 3, 220000, 'Tunai', '-', 'N', 250000, 30000),
('PJ00000004', 'jLabel4', '2021-56-07', '02:56', 2, 6, 385000, 'Tunai', '-', 'N', 400000, 15000),
('PJ00000005', 'jLabel4', '2021-10-07', '11:25', 1, 20, 760000, 'Tunai', '-', 'N', 800000, 40000),
('PJ00000006', 'jLabel4', '2021-10-07', '11:27', 1, 20, 760000, 'Tunai', '-', 'N', 800000, 40000),
('PJ00000007', 'jLabel4', '2021-10-07', '11:29', 1, 10, 380000, 'Tunai', '-', 'N', 380000, 0),
('PJ00000008', 'jLabel4', '2021-10-07', '11:31', 1, 10, 1100000, 'Tunai', '-', 'N', 1100000, 0),
('PJ00000009', 'jLabel4', '2021-10-07', '06:50:27', 2, 18, 820000, 'Tunai', '-', 'N', 850000, 30000),
('PJ00000010', 'jLabel4', '2021-10-07', '06:54:12', 1, 20, 1100000, 'Tunai', '-', 'N', 1100000, 0),
('PJ00000011', 'jLabel4', '2021-10-07', '06:55:10', 1, 50, 3500000, 'Tunai', '-', 'N', 3500000, 0),
('PJ00000012', 'jLabel4', '2021-10-07', '06:57:08', 1, 80, 3040000, 'Tunai', '-', 'N', 3100000, 60000),
('PJ00000013', 'jLabel4', '2021-10-07', '06:57:43', 1, 12, 456000, 'Tunai', '-', 'N', 500000, 44000),
('PJ00000014', 'jLabel4', '2021-10-08', '02:47:17', 2, 22, 1040000, 'Kredit', 'BCA', '123456789', 1040000, 0),
('PJ00000015', 'jLabel4', '2021-10-08', '02:53:55', 2, 20, 1250000, 'Kredit', 'MANDIRI', '123456789', 1250000, 0),
('PJ00000016', 'jLabel4', '2021-10-08', '03:30:59', 1, 20, 640000, 'Kredit', 'MANDIRI', '123456789', 640000, 0);

-- --------------------------------------------------------

--
-- Table structure for table `product`
--

CREATE TABLE `product` (
  `id` varchar(11) NOT NULL,
  `nama` varchar(32) DEFAULT NULL,
  `jenis` varchar(16) DEFAULT NULL,
  `satuan` varchar(8) DEFAULT NULL,
  `harga_beli` decimal(11,0) DEFAULT NULL,
  `harga_jual` decimal(11,0) DEFAULT NULL,
  `stok` int(11) DEFAULT 0,
  `status` varchar(1) DEFAULT 'N',
  `tanggal` varchar(16) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `product`
--

INSERT INTO `product` (`id`, `nama`, `jenis`, `satuan`, `harga_beli`, `harga_jual`, `stok`, `status`, `tanggal`) VALUES
('MB0001', 'KOPI KAP', 'Minuman', 'Box', '18800', '20000', 100, 'Y', '2021-10-08'),
('MB0002', 'TEH GELAS', 'Minuman', 'Box', '17300', '18500', 100, 'Y', '2021-10-08'),
('MB0003', 'MING COCO', 'Minuman', 'Box', '16300', '17500', 100, 'Y', '2021-10-08'),
('MB0004', 'PANTHER', 'Minuman', 'Box', '16500', '17500', 100, 'Y', '2021-10-08'),
('MB0005', 'CINCAU MADU', 'Minuman', 'Box', '17000', '18000', 100, 'Y', '2021-10-08'),
('MB0006', 'TEH RIO', 'Minuman', 'Box', '17300', '18500', 100, 'Y', '2021-10-08'),
('MB0007', 'OKKY BIG', 'Minuman', 'Pcs', '31000', '32000', 80, 'Y', '2021-10-08'),
('MB0008', 'SANQUA 220 ML', 'Minuman', 'Box', '15000', '16500', 100, 'Y', '2021-10-08'),
('MB0009', 'AQUA BTL 600ML', 'Minuman', 'Box', '40000', '42000', 100, 'Y', '2021-10-08'),
('MB0010', 'WELFIT 220ML', 'Minuman', 'Box', '12000', '14000', 100, 'Y', '2021-10-08'),
('MB0011', 'MOUNTOP 220ML', 'Minuman', 'Box', '12200', '14000', 100, 'Y', '2021-10-08'),
('MB0012', 'CINCAU PANDA KALENG', 'Minuman', 'Box', '85000', '90000', 100, 'Y', '2021-10-08');

-- --------------------------------------------------------

--
-- Table structure for table `stock`
--

CREATE TABLE `stock` (
  `id_product` varchar(16) NOT NULL,
  `nama` varchar(32) DEFAULT NULL,
  `qty` int(11) DEFAULT 0,
  `edit_date` varchar(16) DEFAULT NULL,
  `user` varchar(16) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `username` varchar(16) NOT NULL,
  `nama` varchar(32) NOT NULL,
  `telp` varchar(13) NOT NULL,
  `alamat` varchar(64) NOT NULL,
  `jabatan` varchar(16) NOT NULL,
  `password` varchar(8) NOT NULL,
  `status` varchar(1) NOT NULL DEFAULT 'N'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`username`, `nama`, `telp`, `alamat`, `jabatan`, `password`, `status`) VALUES
('fany', 'Fany', '0102030405', 'kunciran', 'Administrator', '123456', 'Y'),
('farhan', 'Farhan', '0102030405', 'pinang', 'Admin', '123456', 'Y'),
('roby', 'Roby', '0102030405', 'kunciran', 'Administrator', '123456', 'Y'),
('septian', 'Septian', '0102030405', 'kunciran', 'Administrator', '123456', 'Y');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `penjualan_header`
--
ALTER TABLE `penjualan_header`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `stock`
--
ALTER TABLE `stock`
  ADD PRIMARY KEY (`id_product`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`username`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
