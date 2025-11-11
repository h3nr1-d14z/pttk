-- ==========================================
-- SIMPLIFIED SAMPLE DATA FOR CINEMAN
-- ==========================================

USE cineman;

-- Clear existing sample data (keep system accounts)
DELETE FROM tickets WHERE id > 0;
DELETE FROM invoices WHERE id > 0;
DELETE FROM showtimes WHERE id > 0;
DELETE FROM memberships WHERE id > 0;
DELETE FROM customers WHERE id > 1;
DELETE FROM users WHERE id > 3;
DELETE FROM movies WHERE id > 3;

-- ==========================================
-- INSERT 27 MORE MOVIES (Total 30)
-- ==========================================

INSERT INTO movies (title, director, genre, duration, release_date, rating, description, poster_url, status, country) VALUES
-- Action Movies (10)
('Avengers: Endgame', 'Anthony & Joe Russo', 'Action', 181, '2019-04-26', 8.4, 'The Avengers assemble once more to reverse Thanos actions.', 'avengers-endgame.jpg', 'NOW_SHOWING', 'USA'),
('John Wick: Chapter 4', 'Chad Stahelski', 'Action', 169, '2023-03-24', 7.8, 'John Wick uncovers a path to defeating The High Table.', 'john-wick-4.jpg', 'NOW_SHOWING', 'USA'),
('Mission: Impossible 7', 'Christopher McQuarrie', 'Action', 163, '2023-07-12', 7.9, 'Ethan Hunt must track down a terrifying new weapon.', 'mi7.jpg', 'NOW_SHOWING', 'USA'),
('Top Gun: Maverick', 'Joseph Kosinski', 'Action', 130, '2022-05-27', 8.3, 'Maverick is still pushing the envelope as a top naval aviator.', 'topgun.jpg', 'NOW_SHOWING', 'USA'),
('The Batman', 'Matt Reeves', 'Action', 176, '2022-03-04', 7.8, 'Batman ventures into Gothams underworld.', 'batman.jpg', 'NOW_SHOWING', 'USA'),
('Fast X', 'Louis Leterrier', 'Action', 141, '2023-05-19', 6.0, 'Dom Toretto faces the vengeful son of a drug kingpin.', 'fastx.jpg', 'NOW_SHOWING', 'USA'),
('Guardians Galaxy 3', 'James Gunn', 'Action', 150, '2023-05-05', 7.9, 'Peter Quill rallies his team to defend the universe.', 'gotg3.jpg', 'NOW_SHOWING', 'USA'),
('Extraction 2', 'Sam Hargrave', 'Action', 122, '2023-06-16', 7.0, 'Tyler Rake is back for another deadly mission.', 'extraction2.jpg', 'NOW_SHOWING', 'USA'),
('Bullet Train', 'David Leitch', 'Action', 127, '2022-08-05', 7.3, 'Five assassins aboard a fast moving bullet train.', 'bullettrain.jpg', 'NOW_SHOWING', 'USA'),
('The Gray Man', 'Anthony & Joe Russo', 'Action', 122, '2022-07-22', 6.5, 'A CIA operative on the run.', 'grayman.jpg', 'NOW_SHOWING', 'USA'),

-- Sci-Fi (5)
('Dune: Part Two', 'Denis Villeneuve', 'Sci-Fi', 166, '2024-03-01', 8.7, 'Paul Atreides unites with the Fremen.', 'dune2.jpg', 'COMING_SOON', 'USA'),
('Interstellar', 'Christopher Nolan', 'Sci-Fi', 169, '2014-11-07', 8.6, 'Explorers travel through a wormhole in space.', 'interstellar.jpg', 'NOW_SHOWING', 'USA'),
('Oppenheimer', 'Christopher Nolan', 'Biography', 180, '2023-07-21', 8.5, 'The story of J. Robert Oppenheimer.', 'oppenheimer.jpg', 'NOW_SHOWING', 'USA'),
('Avatar 2', 'James Cameron', 'Sci-Fi', 192, '2022-12-16', 7.7, 'Jake Sully and Neytiri form a family.', 'avatar2.jpg', 'NOW_SHOWING', 'USA'),
('Blade Runner 2049', 'Denis Villeneuve', 'Sci-Fi', 164, '2017-10-06', 8.0, 'A young blade runner discovers a secret.', 'bladerunner.jpg', 'NOW_SHOWING', 'USA'),

-- Horror (3)
('A Quiet Place', 'John Krasinski', 'Horror', 90, '2018-04-06', 7.5, 'A family forced to live in silence.', 'quietplace.jpg', 'NOW_SHOWING', 'USA'),
('The Conjuring', 'James Wan', 'Horror', 112, '2013-07-19', 7.5, 'Paranormal investigators help a terrorized family.', 'conjuring.jpg', 'NOW_SHOWING', 'USA'),
('It Chapter Two', 'Andy Muschietti', 'Horror', 169, '2019-09-06', 6.5, 'The Losers Club returns to face Pennywise.', 'it2.jpg', 'NOW_SHOWING', 'USA'),

-- Drama (4)
('The Shawshank Redemption', 'Frank Darabont', 'Drama', 142, '1994-10-14', 9.3, 'Two imprisoned men bond over years.', 'shawshank.jpg', 'NOW_SHOWING', 'USA'),
('Parasite', 'Bong Joon Ho', 'Drama', 132, '2019-05-30', 8.5, 'Greed and class discrimination threaten relationships.', 'parasite.jpg', 'NOW_SHOWING', 'South Korea'),
('Forrest Gump', 'Robert Zemeckis', 'Drama', 142, '1994-07-06', 8.8, 'Life through the perspective of an Alabama man.', 'forrestgump.jpg', 'NOW_SHOWING', 'USA'),
('The Godfather', 'Francis Ford Coppola', 'Drama', 175, '1972-03-24', 9.2, 'The aging patriarch transfers control to his son.', 'godfather.jpg', 'NOW_SHOWING', 'USA'),

-- Comedy (2)
('Barbie', 'Greta Gerwig', 'Comedy', 114, '2023-07-21', 7.0, 'Barbie and Ken in Barbie Land.', 'barbie.jpg', 'NOW_SHOWING', 'USA'),
('The Grand Budapest Hotel', 'Wes Anderson', 'Comedy', 99, '2014-03-28', 8.1, 'A writer encounters a hotel owner.', 'budapest.jpg', 'NOW_SHOWING', 'USA'),

-- Animation (3)
('Spider-Man: Across Spider-Verse', 'Joaquim Dos Santos', 'Animation', 140, '2023-06-02', 8.7, 'Miles Morales across the Multiverse.', 'spiderverse.jpg', 'NOW_SHOWING', 'USA'),
('Spirited Away', 'Hayao Miyazaki', 'Animation', 125, '2001-07-20', 8.6, 'A girl wanders into a world ruled by gods.', 'spiritedaway.jpg', 'NOW_SHOWING', 'Japan'),
('The Lion King', 'Jon Favreau', 'Animation', 118, '2019-07-19', 6.8, 'A young lion prince flees his kingdom.', 'lionking.jpg', 'NOW_SHOWING', 'USA');

-- ==========================================
-- INSERT SHOWTIMES (100+ showtimes)
-- ==========================================

-- Generate showtimes for multiple days and times
INSERT INTO showtimes (movie_id, room_id, show_date, start_time, end_time, ticket_price, available_seats, status)
SELECT
    m.id as movie_id,
    ((m.id + days.day_num) % 3) + 1 as room_id,  -- Rotate through rooms 1, 2, 3
    DATE_SUB(CURDATE(), INTERVAL (30 - days.day_num) DAY) as show_date,
    times.start_time,
    ADDTIME(times.start_time, SEC_TO_TIME(m.duration * 60)) as end_time,
    CASE
        WHEN HOUR(times.start_time) < 12 THEN 60000
        WHEN HOUR(times.start_time) < 18 THEN 80000
        ELSE 100000
    END as ticket_price,
    CASE ((m.id + days.day_num) % 3) + 1
        WHEN 1 THEN 100
        WHEN 2 THEN 80
        ELSE 50
    END as available_seats,
    CASE
        WHEN DATE_SUB(CURDATE(), INTERVAL (30 - days.day_num) DAY) < CURDATE() THEN 'FINISHED'
        WHEN DATE_SUB(CURDATE(), INTERVAL (30 - days.day_num) DAY) = CURDATE() THEN 'ONGOING'
        ELSE 'SCHEDULED'
    END as status
FROM movies m
CROSS JOIN (
    SELECT 0 as day_num UNION SELECT 1 UNION SELECT 2 UNION SELECT 3 UNION SELECT 4 UNION SELECT 5 UNION
    SELECT 6 UNION SELECT 7 UNION SELECT 8 UNION SELECT 9 UNION SELECT 10 UNION SELECT 11 UNION
    SELECT 12 UNION SELECT 13 UNION SELECT 14 UNION SELECT 15 UNION SELECT 16 UNION SELECT 17 UNION
    SELECT 18 UNION SELECT 19 UNION SELECT 20 UNION SELECT 21 UNION SELECT 22 UNION SELECT 23 UNION
    SELECT 24 UNION SELECT 25 UNION SELECT 26 UNION SELECT 27 UNION SELECT 28 UNION SELECT 29 UNION SELECT 30
) days
CROSS JOIN (
    SELECT '09:00:00' as start_time UNION
    SELECT '12:30:00' UNION
    SELECT '15:00:00' UNION
    SELECT '18:30:00' UNION
    SELECT '21:00:00'
) times
WHERE m.status = 'NOW_SHOWING'
AND (m.id % 3) = (days.day_num % 3)  -- Distribute movies across days
LIMIT 150;

-- ==========================================
-- INSERT MORE CUSTOMERS (17 new customers)
-- ==========================================

INSERT INTO users (username, password, full_name, email, phone, role, created_at) VALUES
('cust2', '123456', 'Nguyen Van B', 'nvb@email.com', '0987654322', 'CUSTOMER', NOW()),
('cust3', '123456', 'Tran Thi C', 'ttc@email.com', '0987654323', 'CUSTOMER', NOW()),
('cust4', '123456', 'Le Van D', 'lvd@email.com', '0987654324', 'CUSTOMER', NOW()),
('cust5', '123456', 'Pham Thi E', 'pte@email.com', '0987654325', 'CUSTOMER', NOW()),
('cust6', '123456', 'Hoang Van F', 'hvf@email.com', '0987654326', 'CUSTOMER', NOW()),
('cust7', '123456', 'Vu Thi G', 'vtg@email.com', '0987654327', 'CUSTOMER', NOW()),
('cust8', '123456', 'Dao Van H', 'dvh@email.com', '0987654328', 'CUSTOMER', NOW()),
('cust9', '123456', 'Bui Thi I', 'bti@email.com', '0987654329', 'CUSTOMER', NOW()),
('cust10', '123456', 'Ngo Van K', 'nvk@email.com', '0987654330', 'CUSTOMER', NOW()),
('cust11', '123456', 'Dang Thi L', 'dtl@email.com', '0987654331', 'CUSTOMER', NOW()),
('cust12', '123456', 'Duong Van M', 'dvm@email.com', '0987654332', 'CUSTOMER', NOW()),
('cust13', '123456', 'Ly Thi N', 'ltn@email.com', '0987654333', 'CUSTOMER', NOW()),
('cust14', '123456', 'Mai Van O', 'mvo@email.com', '0987654334', 'CUSTOMER', NOW()),
('cust15', '123456', 'Truong Thi P', 'ttp@email.com', '0987654335', 'CUSTOMER', NOW()),
('cust16', '123456', 'Do Van Q', 'dvq@email.com', '0987654336', 'CUSTOMER', NOW()),
('cust17', '123456', 'Ha Thi R', 'htr@email.com', '0987654337', 'CUSTOMER', NOW()),
('cust18', '123456', 'To Van S', 'tvs@email.com', '0987654338', 'CUSTOMER', NOW());

-- Insert customers from new users
INSERT INTO customers (user_id, full_name, email, phone, birth_date, address, is_member)
SELECT u.id, u.full_name, u.email, u.phone,
       DATE_SUB(CURDATE(), INTERVAL (20 + (u.id % 15)) YEAR) as birth_date,
       CONCAT(u.id * 100, ' Street, Hanoi') as address,
       (u.id % 3 != 0) as is_member
FROM users u
WHERE u.role = 'CUSTOMER' AND u.id > 3;

-- Create memberships for members
INSERT INTO memberships (customer_id, card_number, registration_date, expiry_date, points, card_type, status)
SELECT
    c.id,
    CONCAT('MEM', YEAR(NOW()), LPAD(c.id, 5, '0')) as card_number,
    DATE_SUB(CURDATE(), INTERVAL (c.id % 10) MONTH) as registration_date,
    DATE_ADD(CURDATE(), INTERVAL (12 - (c.id % 10)) MONTH) as expiry_date,
    (c.id * 37) % 1500 as points,
    CASE
        WHEN (c.id * 37) % 1500 < 400 THEN 'SILVER'
        WHEN (c.id * 37) % 1500 < 1000 THEN 'GOLD'
        ELSE 'PLATINUM'
    END as card_type,
    'ACTIVE' as status
FROM customers c
WHERE c.is_member = 1;

-- ==========================================
-- INSERT INVOICES & TICKETS (200+ invoices)
-- ==========================================

-- Generate invoices for past showtimes
INSERT INTO invoices (invoice_number, customer_id, staff_id, showtime_id, created_at, total_amount, discount, final_amount, payment_method, status, notes)
SELECT
    CONCAT('INV', DATE_FORMAT(s.show_date, '%Y%m%d'), LPAD(s.id, 4, '0')) as invoice_number,
    (SELECT id FROM customers ORDER BY RAND() LIMIT 1) as customer_id,  -- Random existing customer
    2 as staff_id,  -- staff1
    s.id as showtime_id,
    TIMESTAMP(s.show_date, SUBTIME(s.start_time, '01:00:00')) as created_at,  -- 1 hour before showtime
    s.ticket_price * (1 + (s.id % 4)) as total_amount,  -- 1-4 tickets
    CASE
        WHEN (s.id % 3 = 0) THEN s.ticket_price * (1 + (s.id % 4)) * 0.1
        ELSE 0
    END as discount,
    CASE
        WHEN (s.id % 3 = 0) THEN s.ticket_price * (1 + (s.id % 4)) * 0.9
        ELSE s.ticket_price * (1 + (s.id % 4))
    END as final_amount,
    CASE (s.id % 4)
        WHEN 0 THEN 'CASH'
        WHEN 1 THEN 'CARD'
        WHEN 2 THEN 'ONLINE'
        ELSE 'WALLET'
    END as payment_method,
    'PAID' as status,
    CASE
        WHEN (s.id % 3 = 0) THEN 'Member discount applied'
        ELSE NULL
    END as notes
FROM showtimes s
WHERE s.status = 'FINISHED'
LIMIT 200;

-- Generate tickets for each invoice
INSERT INTO tickets (invoice_id, showtime_id, seat_number, ticket_price, seat_type, status)
SELECT
    i.id as invoice_id,
    i.showtime_id,
    CONCAT(
        CHAR(65 + (i.id % 10)),  -- Row A-J
        LPAD((i.id * 3) % 20 + ticket_num, 2, '0')  -- Seat number
    ) as seat_number,
    i.total_amount / (1 + (i.id % 4)) as ticket_price,
    CASE (i.id + ticket_num) % 3
        WHEN 0 THEN 'NORMAL'
        WHEN 1 THEN 'VIP'
        ELSE 'COUPLE'
    END as seat_type,
    'SOLD' as status
FROM invoices i
CROSS JOIN (
    SELECT 1 as ticket_num UNION SELECT 2 UNION SELECT 3 UNION SELECT 4
) nums
WHERE nums.ticket_num <= 1 + (i.id % 4);

-- Update available seats
UPDATE showtimes s
SET available_seats = available_seats - (
    SELECT COUNT(*)
    FROM tickets t
    JOIN invoices i ON t.invoice_id = i.id
    WHERE i.showtime_id = s.id
)
WHERE s.id IN (SELECT DISTINCT showtime_id FROM invoices);

-- ==========================================
-- SUMMARY
-- ==========================================

SELECT 'Sample data seeded successfully!' as status;
SELECT COUNT(*) as total_movies FROM movies;
SELECT COUNT(*) as total_customers FROM customers;
SELECT COUNT(*) as total_memberships FROM memberships;
SELECT COUNT(*) as total_showtimes FROM showtimes;
SELECT COUNT(*) as total_invoices FROM invoices;
SELECT COUNT(*) as total_tickets FROM tickets;
SELECT CONCAT(FORMAT(SUM(final_amount), 0), ' VND') as total_revenue FROM invoices WHERE status = 'PAID';
