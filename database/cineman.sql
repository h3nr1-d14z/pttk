-- =====================================================
-- CineMan - Cinema Management System Database
-- Tech Stack: JSP + Servlet + JDBC + MySQL + DAO
-- All names in English
-- =====================================================

DROP DATABASE IF EXISTS cineman;
CREATE DATABASE cineman CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE cineman;

-- =====================================================
-- TABLE: users
-- Roles: ADMIN (Manager), STAFF (Sales Staff), CUSTOMER
-- =====================================================
CREATE TABLE users (
    id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    role ENUM('ADMIN', 'STAFF', 'CUSTOMER') NOT NULL,
    full_name VARCHAR(100) NOT NULL,
    email VARCHAR(100),
    phone VARCHAR(15),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    is_active BOOLEAN DEFAULT TRUE,
    INDEX idx_username (username),
    INDEX idx_role (role)
) ENGINE=InnoDB;

-- =====================================================
-- TABLE: customers
-- =====================================================
CREATE TABLE customers (
    id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT UNIQUE,
    full_name VARCHAR(100) NOT NULL,
    birth_date DATE,
    address VARCHAR(255),
    email VARCHAR(100),
    phone VARCHAR(15) NOT NULL,
    is_member BOOLEAN DEFAULT FALSE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
    INDEX idx_phone (phone),
    INDEX idx_is_member (is_member)
) ENGINE=InnoDB;

-- =====================================================
-- TABLE: memberships
-- =====================================================
CREATE TABLE memberships (
    id INT PRIMARY KEY AUTO_INCREMENT,
    customer_id INT UNIQUE NOT NULL,
    card_number VARCHAR(20) UNIQUE NOT NULL,
    registration_date DATE NOT NULL,
    expiry_date DATE NOT NULL,
    points INT DEFAULT 0,
    card_type ENUM('SILVER', 'GOLD', 'PLATINUM') DEFAULT 'SILVER',
    status ENUM('ACTIVE', 'EXPIRED', 'SUSPENDED') DEFAULT 'ACTIVE',
    FOREIGN KEY (customer_id) REFERENCES customers(id) ON DELETE CASCADE,
    INDEX idx_card_number (card_number),
    INDEX idx_status (status)
) ENGINE=InnoDB;

-- =====================================================
-- TABLE: movies
-- =====================================================
CREATE TABLE movies (
    id INT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(200) NOT NULL,
    english_title VARCHAR(200),
    director VARCHAR(100),
    actors TEXT,
    genre VARCHAR(100),
    country VARCHAR(50),
    duration INT NOT NULL COMMENT 'Duration in minutes',
    description TEXT,
    poster_url VARCHAR(255),
    trailer_url VARCHAR(255),
    release_date DATE NOT NULL,
    end_date DATE,
    rating DECIMAL(2,1) DEFAULT 0.0,
    age_rating ENUM('G', 'PG', 'PG13', 'R', 'NC17') DEFAULT 'G',
    status ENUM('COMING_SOON', 'NOW_SHOWING', 'ENDED') DEFAULT 'COMING_SOON',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    INDEX idx_title (title),
    INDEX idx_release_date (release_date),
    INDEX idx_status (status)
) ENGINE=InnoDB;

-- =====================================================
-- TABLE: rooms
-- =====================================================
CREATE TABLE rooms (
    id INT PRIMARY KEY AUTO_INCREMENT,
    room_name VARCHAR(50) NOT NULL,
    total_seats INT NOT NULL,
    total_rows INT NOT NULL COMMENT 'Number of seat rows (A, B, C...)',
    seats_per_row INT NOT NULL,
    room_type ENUM('2D', '3D', 'IMAX', 'VIP') DEFAULT '2D',
    status ENUM('ACTIVE', 'MAINTENANCE', 'INACTIVE') DEFAULT 'ACTIVE',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    INDEX idx_room_name (room_name),
    INDEX idx_status (status)
) ENGINE=InnoDB;

-- =====================================================
-- TABLE: showtimes
-- =====================================================
CREATE TABLE showtimes (
    id INT PRIMARY KEY AUTO_INCREMENT,
    movie_id INT NOT NULL,
    room_id INT NOT NULL,
    show_date DATE NOT NULL,
    start_time TIME NOT NULL,
    end_time TIME NOT NULL,
    ticket_price DECIMAL(10,2) NOT NULL,
    available_seats INT NOT NULL,
    status ENUM('SCHEDULED', 'ONGOING', 'FINISHED', 'CANCELLED') DEFAULT 'SCHEDULED',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (movie_id) REFERENCES movies(id) ON DELETE CASCADE,
    FOREIGN KEY (room_id) REFERENCES rooms(id) ON DELETE CASCADE,
    INDEX idx_show_date (show_date),
    INDEX idx_movie (movie_id),
    INDEX idx_room (room_id),
    INDEX idx_status (status)
) ENGINE=InnoDB;

-- =====================================================
-- TABLE: invoices
-- =====================================================
CREATE TABLE invoices (
    id INT PRIMARY KEY AUTO_INCREMENT,
    invoice_number VARCHAR(20) UNIQUE NOT NULL,
    customer_id INT,
    staff_id INT COMMENT 'Staff who sold the tickets (if sold at counter)',
    showtime_id INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    total_amount DECIMAL(10,2) NOT NULL,
    discount DECIMAL(10,2) DEFAULT 0,
    final_amount DECIMAL(10,2) NOT NULL,
    payment_method ENUM('CASH', 'CARD', 'ONLINE', 'WALLET') DEFAULT 'CASH',
    status ENUM('PENDING', 'PAID', 'CANCELLED', 'REFUNDED') DEFAULT 'PAID',
    notes TEXT,
    FOREIGN KEY (customer_id) REFERENCES customers(id) ON DELETE SET NULL,
    FOREIGN KEY (staff_id) REFERENCES users(id) ON DELETE SET NULL,
    FOREIGN KEY (showtime_id) REFERENCES showtimes(id) ON DELETE CASCADE,
    INDEX idx_invoice_number (invoice_number),
    INDEX idx_created_at (created_at),
    INDEX idx_customer (customer_id),
    INDEX idx_showtime (showtime_id)
) ENGINE=InnoDB;

-- =====================================================
-- TABLE: tickets
-- =====================================================
CREATE TABLE tickets (
    id INT PRIMARY KEY AUTO_INCREMENT,
    invoice_id INT NOT NULL,
    showtime_id INT NOT NULL,
    seat_number VARCHAR(10) NOT NULL COMMENT 'Seat position: A1, B5, C10...',
    ticket_price DECIMAL(10,2) NOT NULL,
    seat_type ENUM('NORMAL', 'VIP', 'COUPLE') DEFAULT 'NORMAL',
    status ENUM('AVAILABLE', 'BOOKED', 'SOLD', 'CANCELLED') DEFAULT 'SOLD',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (invoice_id) REFERENCES invoices(id) ON DELETE CASCADE,
    FOREIGN KEY (showtime_id) REFERENCES showtimes(id) ON DELETE CASCADE,
    UNIQUE KEY unique_seat_showtime (showtime_id, seat_number),
    INDEX idx_invoice (invoice_id),
    INDEX idx_showtime (showtime_id),
    INDEX idx_status (status)
) ENGINE=InnoDB;

-- =====================================================
-- SAMPLE DATA
-- =====================================================

-- Insert Users
-- Password: "123456" (in production should use BCrypt)
INSERT INTO users (username, password, role, full_name, email, phone) VALUES
('admin', '123456', 'ADMIN', 'John Admin', 'admin@cineman.com', '0901234567'),
('staff1', '123456', 'STAFF', 'Jane Staff', 'staff1@cineman.com', '0901234568'),
('staff2', '123456', 'STAFF', 'Bob Staff', 'staff2@cineman.com', '0901234569'),
('customer1', '123456', 'CUSTOMER', 'Alice Customer', 'customer1@gmail.com', '0909876543'),
('customer2', '123456', 'CUSTOMER', 'Tom Customer', 'customer2@gmail.com', '0909876544');

-- Insert Customers
INSERT INTO customers (user_id, full_name, birth_date, address, email, phone, is_member) VALUES
(4, 'Alice Customer', '1995-05-15', '123 Main St, District 1, HCMC', 'customer1@gmail.com', '0909876543', TRUE),
(5, 'Tom Customer', '1998-08-20', '456 Second St, District 1, HCMC', 'customer2@gmail.com', '0909876544', FALSE),
(NULL, 'Mary Guest', '1990-03-10', '789 Third St, District 5, HCMC', 'mary@gmail.com', '0908765432', TRUE);

-- Insert Memberships
INSERT INTO memberships (customer_id, card_number, registration_date, expiry_date, points, card_type) VALUES
(1, 'MEM2024001', '2024-01-15', '2025-01-15', 150, 'GOLD'),
(3, 'MEM2024002', '2024-02-20', '2025-02-20', 50, 'SILVER');

-- Insert Movies
INSERT INTO movies (title, english_title, director, actors, genre, country, duration, description,
                    release_date, end_date, rating, age_rating, status) VALUES
('Mai', 'Mai', 'Tran Thanh', 'Tran Thanh, Tuan Tran, Phuong Anh Dao', 'Drama, Romance', 'Vietnam', 131,
 'Story about Mai - a woman with mysterious past', '2024-02-10', '2024-03-10', 8.5, 'PG13', 'NOW_SHOWING'),

('Dune: Part Two', 'Dune: Part Two', 'Denis Villeneuve', 'Timothée Chalamet, Zendaya, Austin Butler',
 'Sci-Fi, Adventure', 'USA', 166,
 'The sequel to the epic Dune saga', '2024-03-01', '2024-04-15', 9.0, 'PG13', 'NOW_SHOWING'),

('Kung Fu Panda 4', 'Kung Fu Panda 4', 'Mike Mitchell', 'Jack Black, Awkwafina, Viola Davis',
 'Animation, Action, Comedy', 'USA', 94,
 'Po continues his adventure as a kung fu teacher', '2024-03-08', '2024-04-20', 7.8, 'G', 'NOW_SHOWING'),

('Godzilla x Kong', 'Godzilla x Kong: The New Empire', 'Adam Wingard', 'Rebecca Hall, Dan Stevens',
 'Action, Adventure, Sci-Fi', 'USA', 115,
 'Two legendary titans face a new threat', '2024-03-29', NULL, 7.5, 'PG13', 'COMING_SOON'),

('Face Off 7', 'Face Off 7', 'Ly Hai', 'Ly Hai, Thanh Hien, Minh Luan', 'Action, Drama', 'Vietnam', 140,
 'Part 7 of the blockbuster Face Off series', '2024-04-26', NULL, 0.0, 'PG13', 'COMING_SOON');

-- Insert Rooms
INSERT INTO rooms (room_name, total_seats, total_rows, seats_per_row, room_type) VALUES
('Room 1', 120, 10, 12, '2D'),
('Room 2', 100, 10, 10, '2D'),
('Room 3', 150, 10, 15, '3D'),
('Room 4', 80, 8, 10, 'VIP'),
('Room 5', 200, 12, 17, 'IMAX');

-- Insert Showtimes
INSERT INTO showtimes (movie_id, room_id, show_date, start_time, end_time, ticket_price, available_seats) VALUES
-- Mai
(1, 1, '2024-03-15', '09:00:00', '11:15:00', 75000, 100),
(1, 2, '2024-03-15', '14:00:00', '16:15:00', 80000, 95),
(1, 3, '2024-03-15', '19:00:00', '21:15:00', 120000, 140),
-- Dune 2
(2, 3, '2024-03-15', '10:00:00', '12:50:00', 150000, 145),
(2, 5, '2024-03-15', '15:00:00', '17:50:00', 200000, 195),
(2, 5, '2024-03-15', '20:00:00', '22:50:00', 200000, 198),
-- Kung Fu Panda 4
(3, 1, '2024-03-15', '13:00:00', '14:35:00', 70000, 118),
(3, 2, '2024-03-15', '10:30:00', '12:05:00', 70000, 100),
(3, 2, '2024-03-15', '16:00:00', '17:35:00', 75000, 98);

-- Insert Invoices
INSERT INTO invoices (invoice_number, customer_id, staff_id, showtime_id, total_amount, discount, final_amount, payment_method) VALUES
('INV2024030001', 1, 2, 1, 150000, 15000, 135000, 'CARD'),
('INV2024030002', 2, 2, 2, 160000, 0, 160000, 'CASH'),
('INV2024030003', 1, NULL, 5, 400000, 20000, 380000, 'ONLINE'),
('INV2024030004', 3, 2, 3, 240000, 24000, 216000, 'CASH'),
('INV2024030005', NULL, 3, 7, 210000, 0, 210000, 'CASH');

-- Insert Tickets
INSERT INTO tickets (invoice_id, showtime_id, seat_number, ticket_price, seat_type) VALUES
-- Invoice 1: 2 tickets
(1, 1, 'A5', 75000, 'NORMAL'),
(1, 1, 'A6', 75000, 'NORMAL'),
-- Invoice 2: 2 tickets
(2, 2, 'B3', 80000, 'NORMAL'),
(2, 2, 'B4', 80000, 'NORMAL'),
-- Invoice 3: 2 IMAX tickets
(3, 5, 'C7', 200000, 'NORMAL'),
(3, 5, 'C8', 200000, 'NORMAL'),
-- Invoice 4: 2 3D tickets
(4, 3, 'D5', 120000, 'NORMAL'),
(4, 3, 'D6', 120000, 'NORMAL'),
-- Invoice 5: 3 tickets
(5, 7, 'E1', 70000, 'NORMAL'),
(5, 7, 'E2', 70000, 'NORMAL'),
(5, 7, 'E3', 70000, 'NORMAL');

-- =====================================================
-- VIEWS for reporting
-- =====================================================

-- View: Revenue by movie
CREATE VIEW v_revenue_by_movie AS
SELECT
    m.id as movie_id,
    m.title,
    COUNT(DISTINCT i.id) as invoice_count,
    COUNT(t.id) as ticket_count,
    SUM(i.final_amount) as total_revenue
FROM movies m
LEFT JOIN showtimes s ON m.id = s.movie_id
LEFT JOIN invoices i ON s.id = i.showtime_id AND i.status = 'PAID'
LEFT JOIN tickets t ON i.id = t.invoice_id AND t.status = 'SOLD'
GROUP BY m.id, m.title;

-- View: Showtime statistics
CREATE VIEW v_showtime_stats AS
SELECT
    s.id as showtime_id,
    m.title,
    r.room_name,
    s.show_date,
    s.start_time,
    s.ticket_price,
    r.total_seats - s.available_seats as seats_sold,
    s.available_seats,
    COUNT(t.id) as ticket_count,
    SUM(t.ticket_price) as revenue
FROM showtimes s
JOIN movies m ON s.movie_id = m.id
JOIN rooms r ON s.room_id = r.id
LEFT JOIN tickets t ON s.id = t.showtime_id AND t.status = 'SOLD'
GROUP BY s.id, m.title, r.room_name, s.show_date, s.start_time, s.ticket_price, r.total_seats, s.available_seats;

-- =====================================================
-- STORED PROCEDURES
-- =====================================================

-- Procedure: Generate invoice number
DELIMITER //
CREATE PROCEDURE sp_generate_invoice_number(OUT new_invoice_number VARCHAR(20))
BEGIN
    DECLARE next_number INT;
    DECLARE current_date VARCHAR(8);

    SET current_date = DATE_FORMAT(NOW(), '%Y%m%d');

    SELECT COALESCE(MAX(CAST(SUBSTRING(invoice_number, 11) AS UNSIGNED)), 0) + 1
    INTO next_number
    FROM invoices
    WHERE invoice_number LIKE CONCAT('INV', current_date, '%');

    SET new_invoice_number = CONCAT('INV', current_date, LPAD(next_number, 4, '0'));
END //
DELIMITER ;

-- Procedure: Generate membership card number
DELIMITER //
CREATE PROCEDURE sp_generate_card_number(OUT new_card_number VARCHAR(20))
BEGIN
    DECLARE next_number INT;
    DECLARE current_year VARCHAR(4);

    SET current_year = YEAR(NOW());

    SELECT COALESCE(MAX(CAST(SUBSTRING(card_number, 8) AS UNSIGNED)), 0) + 1
    INTO next_number
    FROM memberships
    WHERE card_number LIKE CONCAT('MEM', current_year, '%');

    SET new_card_number = CONCAT('MEM', current_year, LPAD(next_number, 3, '0'));
END //
DELIMITER ;

-- =====================================================
-- DATABASE INITIALIZATION COMPLETE
-- =====================================================
