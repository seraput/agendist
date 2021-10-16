-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Oct 16, 2021 at 11:29 AM
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
-- Table structure for table `bad_stok`
--

CREATE TABLE `bad_stok` (
  `nodok` varchar(16) NOT NULL,
  `jenis` varchar(16) DEFAULT NULL,
  `keterangan` varchar(64) DEFAULT NULL,
  `item` int(11) DEFAULT NULL,
  `qty` int(11) DEFAULT NULL,
  `created` varchar(16) DEFAULT NULL,
  `tanggal` varchar(16) DEFAULT NULL,
  `jam` varchar(16) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `bad_stok`
--

INSERT INTO `bad_stok` (`nodok`, `jenis`, `keterangan`, `item`, `qty`, `created`, `tanggal`, `jam`) VALUES
('20211010', '-', '-', 8, 8, 'sadas', 'dsada', NULL),
('PN11011', 'Expired', '2021-10-10', 1, 5, 'fany', '15-10-2021', '06:18:57'),
('PN11012', 'Rusak', '2021-10-15', 2, 7, 'fany', '15-10-2021', '06:22:59'),
('SL11013', 'Rusak', 'basah', 2, 7, 'fany', '16-10-2021', '03:17:16');

-- --------------------------------------------------------

--
-- Table structure for table `bad_stok_detail`
--

CREATE TABLE `bad_stok_detail` (
  `nodok` varchar(16) DEFAULT NULL,
  `id` varchar(16) DEFAULT NULL,
  `nama` varchar(32) DEFAULT NULL,
  `qty` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `bad_stok_detail`
--

INSERT INTO `bad_stok_detail` (`nodok`, `id`, `nama`, `qty`) VALUES
('PN11011', 'MB0009', 'AQUA BTL 600ML', 5),
('PN11012', 'MB0005', 'CINCAU MADU', 5),
('PN11012', 'MB0009', 'AQUA BTL 600ML', 2),
('SL11013', 'MB0017', 'KOPIKO', 5),
('SL11013', 'MB0021', 'GOOD DAY', 2);

--
-- Triggers `bad_stok_detail`
--
DELIMITER $$
CREATE TRIGGER `bad_kurang` AFTER INSERT ON `bad_stok_detail` FOR EACH ROW BEGIN
UPDATE produk_baik SET qty = qty - NEW.qty
WHERE id = NEW.id;
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `bad_tambah` AFTER INSERT ON `bad_stok_detail` FOR EACH ROW BEGIN
UPDATE produk_bad SET qty = qty + NEW.qty
WHERE id = NEW.id;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `bank`
--

CREATE TABLE `bank` (
  `kdbank` varchar(8) NOT NULL,
  `namabank` varchar(32) DEFAULT NULL,
  `pembayaran` varchar(16) DEFAULT NULL,
  `status` varchar(1) DEFAULT NULL,
  `created_by` varchar(16) DEFAULT NULL,
  `created_date` varchar(32) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `bank`
--

INSERT INTO `bank` (`kdbank`, `namabank`, `pembayaran`, `status`, `created_by`, `created_date`) VALUES
('B10001', 'BCA', 'Kredit', 'Y', 'fany', '12-10-2021'),
('B10002', 'MANDIRI', 'Transfer', 'Y', 'fany', '12-10-2021'),
('B10003', 'BCA', 'Transfer', 'Y', 'fany', '12-10-2021'),
('B10004', 'MANDIRI', 'Kredit', 'Y', 'fany', '12-10-2021'),
('B10005', 'CIMB', 'Transfer', 'Y', 'fany', '13-10-2021'),
('B10006', 'GOPAY', 'Digital', 'Y', 'fany', '13-10-2021'),
('B10007', 'OVO', 'Digital', 'Y', 'fany', '13-10-2021');

-- --------------------------------------------------------

--
-- Table structure for table `distributor`
--

CREATE TABLE `distributor` (
  `id` varchar(12) NOT NULL,
  `nama` varchar(32) DEFAULT NULL,
  `telp` varchar(16) DEFAULT NULL,
  `alamat` varchar(64) DEFAULT NULL,
  `created` varchar(16) DEFAULT NULL,
  `date` varchar(16) DEFAULT NULL,
  `status` varchar(2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `distributor`
--

INSERT INTO `distributor` (`id`, `nama`, `telp`, `alamat`, `created`, `date`, `status`) VALUES
('D21003', 'MAKMUR JAYA', '-', 'Kota Tangerang', 'fany', '15-10-2021', 'Y'),
('D21004', 'MAYORA', '-', 'Daan Mogot', 'fany', '15-10-2021', 'Y'),
('D21005', 'KINO INDONESIA', '-', 'Alam Sutera', 'fany', '15-10-2021', 'Y'),
('D21006', 'WINGS FOOD', '-', 'Jakarta', 'fany', '15-10-2021', 'Y');

-- --------------------------------------------------------

--
-- Table structure for table `log_aktivitas`
--

CREATE TABLE `log_aktivitas` (
  `id` int(11) NOT NULL,
  `username` varchar(16) DEFAULT NULL,
  `tanggal` varchar(16) DEFAULT NULL,
  `jam` varchar(16) DEFAULT NULL,
  `aktivitas` varchar(32) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `opname`
--

CREATE TABLE `opname` (
  `nodok` varchar(16) NOT NULL,
  `bulan` varchar(16) DEFAULT NULL,
  `tahun` varchar(16) DEFAULT NULL,
  `item` int(11) DEFAULT NULL,
  `created` varchar(16) DEFAULT NULL,
  `tanggal` varchar(16) DEFAULT NULL,
  `jam` varchar(16) DEFAULT NULL,
  `status` varchar(2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `opname`
--

INSERT INTO `opname` (`nodok`, `bulan`, `tahun`, `item`, `created`, `tanggal`, `jam`, `status`) VALUES
('SO00002', 'Bulan', '2021', 4, 'fany', '15-10-2021', '08:52:48', 'R'),
('SO00003', 'Oktober', '2021', 8, 'fany', '16-10-2021', '12:08:06', 'R'),
('SO00004', 'Oktober', '2021', 6, 'fany', '16-10-2021', '03:35:08', 'A');

-- --------------------------------------------------------

--
-- Table structure for table `opname_detail`
--

CREATE TABLE `opname_detail` (
  `nodok` varchar(16) DEFAULT NULL,
  `id` varchar(16) DEFAULT NULL,
  `nama` varchar(64) DEFAULT NULL,
  `qty_sistem` int(11) DEFAULT NULL,
  `qty_baik` int(11) DEFAULT NULL,
  `qty_bad` int(11) DEFAULT NULL,
  `status` varchar(2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `opname_detail`
--

INSERT INTO `opname_detail` (`nodok`, `id`, `nama`, `qty_sistem`, `qty_baik`, `qty_bad`, `status`) VALUES
('SO00002', 'MB0004', 'PANTHER', 90, 90, 0, 'A'),
('SO00002', 'MB0002', 'TEH GELAS', 76, 76, 0, 'A'),
('SO00002', 'MB0007', 'OKKY BIG', 50, 50, 0, 'A'),
('SO00002', 'MB0011', 'MOUNTOP 220ML', 50, 50, 0, 'A'),
('SO00003', 'MB0004', 'PANTHER', 90, 90, 0, 'R'),
('SO00003', 'MB0007', 'OKKY BIG', 50, 50, 0, 'R'),
('SO00003', 'MB0010', 'WELFIT 220ML', 65, 65, 0, 'R'),
('SO00003', 'MB0004', 'PANTHER', 90, 90, 0, 'R'),
('SO00003', 'MB0007', 'OKKY BIG', 50, 50, 0, 'R'),
('SO00003', 'MB0010', 'WELFIT 220ML', 65, 65, 0, 'R'),
('SO00003', 'MB0012', 'CINCAU PANDA KALENG', 98, 98, 0, 'R'),
('SO00003', 'MB0009', 'AQUA BTL 600ML', 80, 80, 0, 'R'),
('SO00003', 'MB0002', 'TEH GELAS', 76, 76, 0, 'R'),
('SO00003', 'MB0006', 'TEH RIO', 80, 80, 0, 'R'),
('SO00003', 'MB0008', 'SANQUA 220 ML', 46, 46, 0, 'R'),
('SO00004', 'MB0016', 'MILKU', 40, 35, 5, 'A'),
('SO00004', 'MB0017', 'KOPIKO', 75, 75, 0, 'A'),
('SO00004', 'MB0018', 'KAPAL API', 35, 36, 0, 'A'),
('SO00004', 'MB0019', 'TEH PUCUK', 100, 98, 0, 'A'),
('SO00004', 'MB0020', 'TEH KOTAK', 50, 49, 0, 'A'),
('SO00004', 'MB0021', 'GOOD DAY', 50, 50, 0, 'A');

-- --------------------------------------------------------

--
-- Table structure for table `opname_ok`
--

CREATE TABLE `opname_ok` (
  `nodok` varchar(16) DEFAULT NULL,
  `id` varchar(16) DEFAULT NULL,
  `qty_sistem` int(11) DEFAULT 0,
  `qty_baik` int(11) DEFAULT 0,
  `qty_bad` int(11) DEFAULT 0,
  `selisih` int(11) DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `opname_ok`
--

INSERT INTO `opname_ok` (`nodok`, `id`, `qty_sistem`, `qty_baik`, `qty_bad`, `selisih`) VALUES
('SO00004', 'MB0016', 40, 35, 5, 0),
('SO00004', 'MB0017', 75, 75, 0, 0),
('SO00004', 'MB0018', 35, 36, 0, 1),
('SO00004', 'MB0019', 100, 98, 0, -2),
('SO00004', 'MB0020', 50, 49, 0, -1),
('SO00004', 'MB0021', 50, 50, 0, 0);

--
-- Triggers `opname_ok`
--
DELIMITER $$
CREATE TRIGGER `opname_bad` AFTER INSERT ON `opname_ok` FOR EACH ROW BEGIN
UPDATE produk_bad SET qty = NEW.qty_bad
WHERE id = NEW.id;
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `opname_baik` AFTER INSERT ON `opname_ok` FOR EACH ROW BEGIN
UPDATE produk_baik SET qty = NEW.qty_baik
WHERE id = NEW.id;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `penerimaan`
--

CREATE TABLE `penerimaan` (
  `nodok` varchar(8) NOT NULL,
  `tanggal` varchar(16) DEFAULT NULL,
  `jam` varchar(16) DEFAULT NULL,
  `dist` varchar(32) DEFAULT NULL,
  `keterangan` varchar(120) DEFAULT NULL,
  `total_item` int(11) DEFAULT 0,
  `total_qty` int(11) DEFAULT 0,
  `created` varchar(16) DEFAULT NULL,
  `status` varchar(2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `penerimaan`
--

INSERT INTO `penerimaan` (`nodok`, `tanggal`, `jam`, `dist`, `keterangan`, `total_item`, `total_qty`, `created`, `status`) VALUES
('PN11011', '14-10-2021', '07:08:45', 'PT. Kino Indonesia.Tbk', 'Surat jalan', 1, 20, 'fany', 'Y'),
('PN11012', '14-10-2021', '07:16:00', 'PT. Kino Indonesia.Tbk', 'asdasdas', 1, 2, 'fany', 'Y'),
('PN11013', '14-10-2021', '07:28:52', 'PT. Kino Indonesia.Tbk', 'test', 1, 53, 'fany', 'Y'),
('PN11014', '16-10-2021', '03:06:50', 'MAKMUR JAYA', 'penambahan barang', 6, 375, 'fany', 'Y'),
('PN11015', '16-10-2021', '02:42:56', 'MAKMUR JAYA', 'test', 2, 70, 'fany', 'Y');

-- --------------------------------------------------------

--
-- Table structure for table `penerimaan_detail`
--

CREATE TABLE `penerimaan_detail` (
  `nodok` varchar(16) DEFAULT NULL,
  `id` varchar(16) DEFAULT NULL,
  `nama` varchar(32) DEFAULT NULL,
  `qty` int(11) DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `penerimaan_detail`
--

INSERT INTO `penerimaan_detail` (`nodok`, `id`, `nama`, `qty`) VALUES
('PN11011', 'MB0007', 'OKKY BIG', 20),
('PN11012', 'MB0009', 'AQUA BTL 600ML', 2),
('PN11013', 'MB0007', 'OKKY BIG', 53),
('PN11014', 'MB0016', 'MILKU', 50),
('PN11014', 'MB0017', 'KOPIKO', 75),
('PN11014', 'MB0018', 'KAPAL API', 50),
('PN11014', 'MB0019', 'TEH PUCUK', 100),
('PN11014', 'MB0020', 'TEH KOTAK', 50),
('PN11014', 'MB0021', 'GOOD DAY', 50),
('PN11015', 'MB0016', 'MILKU', 20),
('PN11015', 'MB0021', 'GOOD DAY', 50);

--
-- Triggers `penerimaan_detail`
--
DELIMITER $$
CREATE TRIGGER `masuk_barang` AFTER INSERT ON `penerimaan_detail` FOR EACH ROW BEGIN
UPDATE produk_baik SET qty = qty + NEW.qty
WHERE id = NEW.id;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `pengeluaran`
--

CREATE TABLE `pengeluaran` (
  `nodok` varchar(8) NOT NULL,
  `tanggal` varchar(16) DEFAULT NULL,
  `jam` varchar(16) DEFAULT NULL,
  `jenis` varchar(16) DEFAULT NULL,
  `tujuan` varchar(32) DEFAULT NULL,
  `keterangan` varchar(120) DEFAULT NULL,
  `total_item` int(11) DEFAULT 0,
  `total_qty` int(11) DEFAULT 0,
  `created` varchar(16) DEFAULT NULL,
  `status` varchar(2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `pengeluaran`
--

INSERT INTO `pengeluaran` (`nodok`, `tanggal`, `jam`, `jenis`, `tujuan`, `keterangan`, `total_item`, `total_qty`, `created`, `status`) VALUES
('PL00001', 'Oct 14, 2021', '07:11:26', 'Expired', 'PT. Kino Indonesia.Tbk', 'asdsadasdas', 1, 10, 'fany', 'Y'),
('PL00002', '14-10-2021', '07:24:16', 'Expired', 'PT. Kino Indonesia.Tbk', 'Testing', 1, 20, 'fany', 'Y'),
('PL00003', '14-10-2021', '07:28:20', 'Rusak', 'PT. Kino Indonesia.Tbk', 'test', 1, 20, 'fany', 'Y');

-- --------------------------------------------------------

--
-- Table structure for table `pengeluaran_bad`
--

CREATE TABLE `pengeluaran_bad` (
  `nodok` varchar(16) DEFAULT NULL,
  `id` varchar(16) DEFAULT NULL,
  `nama` varchar(32) DEFAULT NULL,
  `qty` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `pengeluaran_detail`
--

CREATE TABLE `pengeluaran_detail` (
  `nodok` varchar(16) DEFAULT NULL,
  `id` varchar(16) DEFAULT NULL,
  `nama` varchar(16) DEFAULT NULL,
  `qty` int(11) DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `pengeluaran_detail`
--

INSERT INTO `pengeluaran_detail` (`nodok`, `id`, `nama`, `qty`) VALUES
('PL00001', 'MB0006', 'TEH RIO', 10),
('PL00002', 'MB0006', 'TEH RIO', 20),
('PL00003', 'MB0006', 'TEH RIO', 20);

--
-- Triggers `pengeluaran_detail`
--
DELIMITER $$
CREATE TRIGGER `keluar_barang_bad` AFTER INSERT ON `pengeluaran_detail` FOR EACH ROW BEGIN
UPDATE produk_bad SET qty = qty - NEW.qty
WHERE id = NEW.id;
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `keluar_barang_baik` AFTER INSERT ON `pengeluaran_detail` FOR EACH ROW BEGIN
UPDATE produk_baik SET qty = qty - NEW.qty
WHERE id = NEW.id;
END
$$
DELIMITER ;

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
('PJ00000017', 'MB0007', 'OKKY BIG', 32000, 76, 2432000),
('PJ00000018', 'MB0007', 'OKKY BIG', 32000, 2, 64000),
('PJ00000019', 'MB0010', 'WELFIT 220ML', 25000, 5, 125000),
('PJ00000019', 'MB0012', 'CINCAU PANDA KALENG', 100000, 2, 200000),
('PJ00000020', 'MB0005', 'CINCAU MADU', 18000, 2, 36000),
('PJ00000021', 'MB0009', 'AQUA BTL 600ML', 45000, 5, 225000),
('PJ00000022', 'MB0011', 'MOUNTOP 220ML', 15000, 5, 75000),
('PJ00000022', 'MB0009', 'AQUA BTL 600ML', 45000, 5, 225000),
('PJ00000022', 'MB0002', 'TEH GELAS', 19000, 9, 171000),
('PJ00000023', 'MB0008', 'SANQUA 220 ML', 17000, 9, 153000),
('PJ00000023', 'MB0005', 'CINCAU MADU', 19000, 5, 95000),
('PJ00000024', 'MB0003', 'MING COCO', 20000, 2, 40000),
('PJ00000025', 'MB0001', 'KOPI KAP', 20000, 4, 80000),
('PJ00000025', 'MB0005', 'CINCAU MADU', 80000, 1, 80000),
('PJ00000026', 'MB0011', 'MOUNTOP 220ML', 14000, 25, 350000),
('PJ00000027', 'MB0008', 'SANQUA 220 ML', 16500, 25, 412500),
('PJ00000027', 'MB0010', 'WELFIT 220ML', 14000, 10, 140000),
('PJ00000028', 'MB0004', 'PANTHER', 17500, 10, 175000),
('PJ00000028', 'MB0010', 'WELFIT 220ML', 14000, 20, 280000),
('PJ00000028', 'MB0002', 'TEH GELAS', 18500, 15, 277500),
('PJ00000028', 'MB0011', 'MOUNTOP 220ML', 14000, 20, 280000),
('PJ00000029', 'MB0003', 'MING COCO', 17500, 20, 350000),
('PJ00000029', 'MB0008', 'SANQUA 220 ML', 16500, 20, 330000),
('PJ00000030', 'MB0007', 'OKKY BIG', 32000, 5, 160000),
('PJ00000031', 'MB0018', 'KAPAL API', 32000, 15, 480000),
('PJ00000031', 'MB0016', 'MILKU', 30000, 10, 300000),
('PJ00000032', 'MB0019', 'TEH PUCUK', 30000, 10, 300000),
('PJ00000032', 'MB0018', 'KAPAL API', 30000, 10, 300000);

--
-- Triggers `penjualan_detail`
--
DELIMITER $$
CREATE TRIGGER `stok_keluar` AFTER INSERT ON `penjualan_detail` FOR EACH ROW BEGIN
UPDATE produk_baik SET qty = qty - NEW.qty
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
  `sales` varchar(16) DEFAULT NULL,
  `tanggal` varchar(16) DEFAULT NULL,
  `jam` varchar(16) DEFAULT NULL,
  `total_barang` int(11) DEFAULT NULL,
  `total_qty` int(11) DEFAULT NULL,
  `total_harga` int(11) DEFAULT NULL,
  `pembayaran` varchar(16) DEFAULT NULL,
  `bank` varchar(16) DEFAULT 'N',
  `nomor` varchar(16) DEFAULT 'N',
  `nominal` int(11) DEFAULT NULL,
  `kembali` int(11) DEFAULT NULL,
  `created` varchar(16) DEFAULT 'N'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `penjualan_header`
--

INSERT INTO `penjualan_header` (`id`, `sales`, `tanggal`, `jam`, `total_barang`, `total_qty`, `total_harga`, `pembayaran`, `bank`, `nomor`, `nominal`, `kembali`, `created`) VALUES
('PJ00000017', 'revin', '09-10-2021', '03:35:22', 1, 76, 2432000, 'Tunai', 'Pilih', '', 2450000, 18000, 'fany'),
('PJ00000018', 'razka', '09-10-2021', '04:00:34', 1, 2, 64000, 'Tunai', '-', '-', 64000, 0, 'fany'),
('PJ00000019', 'revin', '11-10-2021', '03:34:25', 2, 7, 325000, 'Tunai', '-', '-', 350000, 25000, 'fany'),
('PJ00000020', 'ahmad', '11-10-2021', '01:38:57', 1, 2, 36000, 'Tunai', '-', '-', 50000, 14000, 'fany'),
('PJ00000021', 'razka', '12-10-2021', '08:07:33', 1, 5, 225000, 'Transfer', 'MANDIRI', '123456789', 225000, 0, 'fany'),
('PJ00000022', 'riski', '12-10-2021', '08:10:13', 3, 19, 471000, 'Tunai', '-', '-', 500000, 29000, 'fany'),
('PJ00000023', 'zaky', '12-10-2021', '10:02:20', 2, 14, 248000, 'Tunai', '-', '-', 250000, 2000, 'fany'),
('PJ00000024', 'ahmad', '12-10-2021', '10:27:31', 1, 2, 40000, 'Tunai', '-', '-', 50000, 0, 'fany'),
('PJ00000025', 'razka', '12-10-2021', '10:29:52', 2, 5, 160000, 'Tunai', '-', '-', 200000, 0, 'fany'),
('PJ00000026', 'revin', '13-10-2021', '05:36:26', 1, 25, 350000, 'Tunai', '-', '-', 350000, 0, 'fany'),
('PJ00000027', 'razka', '13-10-2021', '05:43:18', 2, 35, 552500, 'Tunai', '-', '-', 600000, 47500, 'fany'),
('PJ00000028', 'riski', '13-10-2021', '09:14:44', 4, 65, 1012500, 'Tunai', 'Pilih', '-', 1100000, 87500, 'fany'),
('PJ00000029', 'riski', '14-10-2021', '03:06:43', 2, 40, 680000, 'Transfer', 'BCA', '123456789', 680000, 0, 'fany'),
('PJ00000030', 'ahmad', '14-10-2021', '07:29:21', 1, 5, 160000, 'Tunai', '-', '-', 170000, 10000, 'fany'),
('PJ00000031', 'razka', '16-10-2021', '03:08:07', 2, 25, 780000, 'Transfer', 'BCA', '123456', 800000, 20000, 'fany'),
('PJ00000032', 'zaky', '16-10-2021', '01:44:55', 2, 20, 600000, 'Tunai', '-', '-', 600000, 0, 'fany');

-- --------------------------------------------------------

--
-- Table structure for table `persentase`
--

CREATE TABLE `persentase` (
  `id_trans` varchar(16) NOT NULL,
  `sales` varchar(16) DEFAULT NULL,
  `perdua` int(11) DEFAULT NULL,
  `perlapan` int(11) DEFAULT NULL,
  `pertiga` int(11) DEFAULT NULL,
  `pertujuh` int(11) DEFAULT NULL,
  `perempat` int(11) DEFAULT NULL,
  `perenam` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

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
  `status` varchar(1) DEFAULT 'N',
  `tanggal` varchar(16) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `product`
--

INSERT INTO `product` (`id`, `nama`, `jenis`, `satuan`, `harga_beli`, `harga_jual`, `status`, `tanggal`) VALUES
('MB0016', 'MILKU', 'Minuman', 'Box', '25000', '30000', 'Y', '16-10-2021'),
('MB0017', 'KOPIKO', 'Minuman', 'Box', '50000', '52000', 'Y', '16-10-2021'),
('MB0018', 'KAPAL API', 'Minuman', 'Box', '25000', '30000', 'Y', '16-10-2021'),
('MB0019', 'TEH PUCUK', 'Makanan', 'Box', '25000', '30000', 'Y', '16-10-2021'),
('MB0020', 'TEH KOTAK', 'Minuman', 'Box', '25000', '30000', 'Y', '16-10-2021'),
('MB0021', 'GOOD DAY', 'Minuman', 'Box', '50000', '55000', 'Y', '16-10-2021');

-- --------------------------------------------------------

--
-- Table structure for table `produk_bad`
--

CREATE TABLE `produk_bad` (
  `id` varchar(16) NOT NULL,
  `qty` int(11) DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `produk_bad`
--

INSERT INTO `produk_bad` (`id`, `qty`) VALUES
('MB0016', 5),
('MB0017', 0),
('MB0018', 0),
('MB0019', 0),
('MB0020', 0),
('MB0021', 0);

-- --------------------------------------------------------

--
-- Table structure for table `produk_baik`
--

CREATE TABLE `produk_baik` (
  `id` varchar(16) NOT NULL,
  `qty` int(11) DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `produk_baik`
--

INSERT INTO `produk_baik` (`id`, `qty`) VALUES
('MB0016', 55),
('MB0017', 75),
('MB0018', 26),
('MB0019', 88),
('MB0020', 49),
('MB0021', 100);

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
('afi', 'afi', '0123456789', 'testing', 'Sales', '123456', 'Y'),
('ahmad', 'ahmad', '0123456789', 'dirumah', 'Sales', '123456', 'Y'),
('fany', 'Fany', '0102030405', 'kunciran', 'Administrator', '123456', 'Y'),
('farhan', 'Farhan', '0102030405', 'pinang', 'Owner', '123456', 'Y'),
('razka', 'razka', '0123456789', 'testing', 'Sales', '123456', 'Y'),
('revin', 'revin', '123456789', 'testing', 'Sales', '123456', 'Y'),
('riski', 'acong', '0123456789', 'kunciran', 'Sales', '123456', 'N'),
('roby', 'Roby', '0102030405', 'kunciran', 'Administrator', '123456', 'Y'),
('septian', 'Septian', '0102030405', 'kunciran', 'Kasir', '123456', 'Y'),
('zaky', 'zaky', '123456789', 'testing', 'Sales', '123456', 'Y');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `bad_stok`
--
ALTER TABLE `bad_stok`
  ADD PRIMARY KEY (`nodok`);

--
-- Indexes for table `bank`
--
ALTER TABLE `bank`
  ADD PRIMARY KEY (`kdbank`);

--
-- Indexes for table `distributor`
--
ALTER TABLE `distributor`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `nama` (`nama`);

--
-- Indexes for table `log_aktivitas`
--
ALTER TABLE `log_aktivitas`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `opname`
--
ALTER TABLE `opname`
  ADD PRIMARY KEY (`nodok`);

--
-- Indexes for table `penerimaan`
--
ALTER TABLE `penerimaan`
  ADD PRIMARY KEY (`nodok`);

--
-- Indexes for table `pengeluaran`
--
ALTER TABLE `pengeluaran`
  ADD PRIMARY KEY (`nodok`);

--
-- Indexes for table `penjualan_header`
--
ALTER TABLE `penjualan_header`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `persentase`
--
ALTER TABLE `persentase`
  ADD PRIMARY KEY (`id_trans`);

--
-- Indexes for table `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `produk_bad`
--
ALTER TABLE `produk_bad`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `produk_baik`
--
ALTER TABLE `produk_baik`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`username`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `log_aktivitas`
--
ALTER TABLE `log_aktivitas`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
