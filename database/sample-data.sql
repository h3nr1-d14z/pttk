-- ==========================================
-- SAMPLE DATA FOR CINEMAN SYSTEM
-- More realistic data for testing
-- ==========================================

USE cineman;

-- Clear existing data (except system users)
DELETE FROM tickets WHERE id > 0;
DELETE FROM invoices WHERE id > 0;
DELETE FROM showtimes WHERE id > 0;
DELETE FROM movies WHERE id > 3; -- Keep first 3 movies
DELETE FROM memberships WHERE id > 0;
DELETE FROM customers WHERE id > 1; -- Keep first customer
DELETE FROM users WHERE id > 3; -- Keep admin, staff, customer1

-- ==========================================
-- INSERT MORE MOVIES (30 movies total)
-- ==========================================

INSERT INTO movies (title, director, genre, duration, release_date, rating, description, poster_url, status, country) VALUES
-- Action Movies
('Avengers: Endgame', 'Anthony Russo, Joe Russo', 'Action', 181, '2019-04-26', 8.4, 'After the devastating events of Infinity War, the Avengers assemble once more.', 'avengers-endgame.jpg', 'NOW_SHOWING', 'USA'),
('John Wick: Chapter 4', 'Chad Stahelski', 'Action', 169, '2023-03-24', 7.8, 'John Wick uncovers a path to defeating The High Table.', 'john-wick-4.jpg', 'NOW_SHOWING', 'USA'),
('Mission: Impossible - Dead Reckoning', 'Christopher McQuarrie', 'Action', 163, '2023-07-12', 7.9, 'Ethan Hunt and his IMF team must track down a terrifying new weapon.', 'mi-dead-reckoning.jpg', 'NOW_SHOWING', 'USA'),
('Top Gun: Maverick', 'Joseph Kosinski', 'Action', 130, '2022-05-27', 8.3, 'After thirty years, Maverick is still pushing the envelope as a top naval aviator.', 'top-gun-maverick.jpg', 'NOW_SHOWING', 'USA'),
('The Batman', 'Matt Reeves', 'Action', 176, '2022-03-04', 7.8, 'Batman ventures into Gotham City\'s underworld when a sadistic killer leaves behind a trail of cryptic clues.', 'the-batman.jpg', 'NOW_SHOWING', 'USA'),
('Fast X', 'Louis Leterrier', 'Action', 141, '2023-05-19', 6.0, 'Dom Toretto and his family are targeted by the vengeful son of drug kingpin Hernan Reyes.', 'fast-x.jpg', 'NOW_SHOWING', 'USA'),
('Guardians of the Galaxy Vol. 3', 'James Gunn', 'Action', 150, '2023-05-05', 7.9, 'Still reeling from the loss of Gamora, Peter Quill rallies his team to defend the universe.', 'gotg-3.jpg', 'NOW_SHOWING', 'USA'),

-- Sci-Fi Movies
('Dune: Part Two', 'Denis Villeneuve', 'Sci-Fi', 166, '2024-03-01', 8.7, 'Paul Atreides unites with Chani and the Fremen while seeking revenge.', 'dune-2.jpg', 'COMING_SOON', 'USA'),
('Interstellar', 'Christopher Nolan', 'Sci-Fi', 169, '2014-11-07', 8.6, 'A team of explorers travel through a wormhole in space.', 'interstellar.jpg', 'NOW_SHOWING', 'USA'),
('Oppenheimer', 'Christopher Nolan', 'Biography', 180, '2023-07-21', 8.5, 'The story of American scientist J. Robert Oppenheimer and his role in the development of the atomic bomb.', 'oppenheimer.jpg', 'NOW_SHOWING', 'USA'),
('Avatar: The Way of Water', 'James Cameron', 'Sci-Fi', 192, '2022-12-16', 7.7, 'Jake Sully and Ney\'tiri have formed a family and are doing everything to stay together.', 'avatar-2.jpg', 'NOW_SHOWING', 'USA'),
('Blade Runner 2049', 'Denis Villeneuve', 'Sci-Fi', 164, '2017-10-06', 8.0, 'A young blade runner discovers a long-buried secret.', 'blade-runner-2049.jpg', 'NOW_SHOWING', 'USA'),

-- Horror Movies
('A Quiet Place', 'John Krasinski', 'Horror', 90, '2018-04-06', 7.5, 'In a post-apocalyptic world, a family is forced to live in silence.', 'quiet-place.jpg', 'NOW_SHOWING', 'USA'),
('The Conjuring', 'James Wan', 'Horror', 112, '2013-07-19', 7.5, 'Paranormal investigators work to help a family terrorized by a dark presence.', 'conjuring.jpg', 'NOW_SHOWING', 'USA'),
('It Chapter Two', 'Andy Muschietti', 'Horror', 169, '2019-09-06', 6.5, 'Twenty-seven years after their first encounter with Pennywise, the Losers Club returns.', 'it-2.jpg', 'NOW_SHOWING', 'USA'),

-- Drama Movies
('The Shawshank Redemption', 'Frank Darabont', 'Drama', 142, '1994-10-14', 9.3, 'Two imprisoned men bond over a number of years.', 'shawshank.jpg', 'NOW_SHOWING', 'USA'),
('The Godfather', 'Francis Ford Coppola', 'Drama', 175, '1972-03-24', 9.2, 'The aging patriarch of an organized crime dynasty transfers control to his reluctant son.', 'godfather.jpg', 'NOW_SHOWING', 'USA'),
('Forrest Gump', 'Robert Zemeckis', 'Drama', 142, '1994-07-06', 8.8, 'The presidencies of Kennedy and Johnson unfold through the perspective of an Alabama man.', 'forrest-gump.jpg', 'NOW_SHOWING', 'USA'),
('Parasite', 'Bong Joon Ho', 'Drama', 132, '2019-05-30', 8.5, 'Greed and class discrimination threaten the newly formed symbiotic relationship.', 'parasite.jpg', 'NOW_SHOWING', 'South Korea'),

-- Comedy Movies
('The Grand Budapest Hotel', 'Wes Anderson', 'Comedy', 99, '2014-03-28', 8.1, 'A writer encounters the owner of an aging high-class hotel.', 'budapest-hotel.jpg', 'NOW_SHOWING', 'USA'),
('Barbie', 'Greta Gerwig', 'Comedy', 114, '2023-07-21', 7.0, 'Barbie and Ken are having the time of their lives in Barbie Land.', 'barbie.jpg', 'NOW_SHOWING', 'USA'),

-- Thriller Movies
('Gone Girl', 'David Fincher', 'Thriller', 149, '2014-10-03', 8.1, 'With his wife\'s disappearance having become the focus of an intense media circus.', 'gone-girl.jpg', 'NOW_SHOWING', 'USA'),
('Shutter Island', 'Martin Scorsese', 'Thriller', 138, '2010-02-19', 8.2, 'A U.S. Marshal investigates the disappearance of a murderer.', 'shutter-island.jpg', 'NOW_SHOWING', 'USA'),
('No Country for Old Men', 'Coen Brothers', 'Thriller', 122, '2007-11-21', 8.1, 'Violence and mayhem ensue after a hunter stumbles upon a drug deal gone wrong.', 'no-country.jpg', 'NOW_SHOWING', 'USA'),

-- Animation Movies
('Spirited Away', 'Hayao Miyazaki', 'Animation', 125, '2001-07-20', 8.6, 'During her family\'s move to the suburbs, a sullen 10-year-old girl wanders into a world ruled by gods.', 'spirited-away.jpg', 'NOW_SHOWING', 'Japan'),
('The Lion King', 'Jon Favreau', 'Animation', 118, '2019-07-19', 6.8, 'After the murder of his father, a young lion prince flees his kingdom.', 'lion-king.jpg', 'NOW_SHOWING', 'USA'),
('Toy Story 4', 'Josh Cooley', 'Animation', 100, '2019-06-21', 7.7, 'When a new toy called "Forky" joins Woody and the gang.', 'toy-story-4.jpg', 'NOW_SHOWING', 'USA'),
('Spider-Man: Across the Spider-Verse', 'Joaquim Dos Santos', 'Animation', 140, '2023-06-02', 8.7, 'Miles Morales catapults across the Multiverse.', 'spiderverse.jpg', 'NOW_SHOWING', 'USA');

-- ==========================================
-- INSERT MORE CUSTOMERS (20 customers)
-- ==========================================

INSERT INTO users (username, password, full_name, email, phone, role, created_at) VALUES
('customer2', '123456', 'Nguyen Van B', 'nguyenvanb@email.com', '0987654322', 'CUSTOMER', NOW()),
('customer3', '123456', 'Tran Thi C', 'tranthic@email.com', '0987654323', 'CUSTOMER', NOW()),
('customer4', '123456', 'Le Van D', 'levand@email.com', '0987654324', 'CUSTOMER', NOW()),
('customer5', '123456', 'Pham Thi E', 'phamthie@email.com', '0987654325', 'CUSTOMER', NOW()),
('customer6', '123456', 'Hoang Van F', 'hoangvanf@email.com', '0987654326', 'CUSTOMER', NOW()),
('customer7', '123456', 'Vu Thi G', 'vuthig@email.com', '0987654327', 'CUSTOMER', NOW()),
('customer8', '123456', 'Dao Van H', 'daovanh@email.com', '0987654328', 'CUSTOMER', NOW()),
('customer9', '123456', 'Bui Thi I', 'buithii@email.com', '0987654329', 'CUSTOMER', NOW()),
('customer10', '123456', 'Ngo Van K', 'ngovank@email.com', '0987654330', 'CUSTOMER', NOW()),
('customer11', '123456', 'Dang Thi L', 'dangthil@email.com', '0987654331', 'CUSTOMER', NOW()),
('customer12', '123456', 'Duong Van M', 'duongvanm@email.com', '0987654332', 'CUSTOMER', NOW()),
('customer13', '123456', 'Ly Thi N', 'lythin@email.com', '0987654333', 'CUSTOMER', NOW()),
('customer14', '123456', 'Mai Van O', 'maivano@email.com', '0987654334', 'CUSTOMER', NOW()),
('customer15', '123456', 'Truong Thi P', 'truongthip@email.com', '0987654335', 'CUSTOMER', NOW()),
('customer16', '123456', 'Do Van Q', 'dovanq@email.com', '0987654336', 'CUSTOMER', NOW()),
('customer17', '123456', 'Ha Thi R', 'hathir@email.com', '0987654337', 'CUSTOMER', NOW()),
('customer18', '123456', 'To Van S', 'tovans@email.com', '0987654338', 'CUSTOMER', NOW()),
('customer19', '123456', 'Cao Thi T', 'caothit@email.com', '0987654339', 'CUSTOMER', NOW()),
('customer20', '123456', 'Trinh Van U', 'trinhvanu@email.com', '0987654340', 'CUSTOMER', NOW());

-- Insert corresponding customer records (dynamically get user_ids)
INSERT INTO customers (user_id, full_name, email, phone, birth_date, address, is_member)
SELECT u.id, u.full_name, u.email, u.phone,
       CASE u.username
           WHEN 'customer2' THEN '1995-03-15'
           WHEN 'customer3' THEN '1998-07-22'
           WHEN 'customer4' THEN '1992-11-08'
           WHEN 'customer5' THEN '1990-05-30'
           WHEN 'customer6' THEN '1996-09-14'
           WHEN 'customer7' THEN '1994-02-28'
           WHEN 'customer8' THEN '1991-12-05'
           WHEN 'customer9' THEN '1997-06-18'
           WHEN 'customer10' THEN '1993-08-25'
           WHEN 'customer11' THEN '1999-04-12'
           WHEN 'customer12' THEN '1995-10-20'
           WHEN 'customer13' THEN '1992-01-07'
           WHEN 'customer14' THEN '1998-03-29'
           WHEN 'customer15' THEN '1996-11-16'
           WHEN 'customer16' THEN '1994-07-03'
           WHEN 'customer17' THEN '1991-09-11'
           WHEN 'customer18' THEN '1997-12-24'
           WHEN 'customer19' THEN '1993-05-08'
           WHEN 'customer20' THEN '1990-02-19'
           ELSE '1995-01-01'
       END as birth_date,
       CASE u.username
           WHEN 'customer2' THEN '456 Le Loi, Hanoi'
           WHEN 'customer3' THEN '789 Tran Phu, Hanoi'
           WHEN 'customer4' THEN '321 Nguyen Hue, Hanoi'
           WHEN 'customer5' THEN '654 Hai Ba Trung, Hanoi'
           WHEN 'customer6' THEN '987 Ba Trieu, Hanoi'
           WHEN 'customer7' THEN '147 Ly Thuong Kiet, Hanoi'
           WHEN 'customer8' THEN '258 Quang Trung, Hanoi'
           WHEN 'customer9' THEN '369 Tran Hung Dao, Hanoi'
           WHEN 'customer10' THEN '741 Giai Phong, Hanoi'
           WHEN 'customer11' THEN '852 Cau Giay, Hanoi'
           WHEN 'customer12' THEN '963 Lang Ha, Hanoi'
           WHEN 'customer13' THEN '159 Nguyen Chi Thanh, Hanoi'
           WHEN 'customer14' THEN '357 Xuan Thuy, Hanoi'
           WHEN 'customer15' THEN '486 Pham Van Dong, Hanoi'
           WHEN 'customer16' THEN '572 Giang Vo, Hanoi'
           WHEN 'customer17' THEN '683 Kim Ma, Hanoi'
           WHEN 'customer18' THEN '794 Lieu Giai, Hanoi'
           WHEN 'customer19' THEN '815 Doi Can, Hanoi'
           WHEN 'customer20' THEN '926 Hoe Nhai, Hanoi'
           ELSE '123 Main St, Hanoi'
       END as address,
       CASE u.username
           WHEN 'customer2' THEN true
           WHEN 'customer3' THEN true
           WHEN 'customer4' THEN false
           WHEN 'customer5' THEN true
           WHEN 'customer6' THEN true
           WHEN 'customer7' THEN false
           WHEN 'customer8' THEN true
           WHEN 'customer9' THEN true
           WHEN 'customer10' THEN false
           WHEN 'customer11' THEN true
           WHEN 'customer12' THEN false
           WHEN 'customer13' THEN true
           WHEN 'customer14' THEN true
           WHEN 'customer15' THEN false
           WHEN 'customer16' THEN true
           WHEN 'customer17' THEN true
           WHEN 'customer18' THEN false
           WHEN 'customer19' THEN true
           WHEN 'customer20' THEN true
           ELSE false
       END as is_member
FROM users u
WHERE u.role = 'CUSTOMER' AND u.username LIKE 'customer%'
ORDER BY u.id;

-- ==========================================
-- INSERT MEMBERSHIPS (for members)
-- ==========================================

-- Create memberships for members (dynamically)
INSERT INTO memberships (customer_id, card_number, registration_date, expiry_date, points, card_type, status)
SELECT
    c.id as customer_id,
    CONCAT('MEM', DATE_FORMAT(NOW(), '%Y'), LPAD(c.id, 4, '0')) as card_number,
    DATE_SUB(CURDATE(), INTERVAL (c.id % 12) MONTH) as registration_date,
    DATE_ADD(CURDATE(), INTERVAL (12 - (c.id % 12)) MONTH) as expiry_date,
    (c.id * 50) % 2000 as points,
    CASE
        WHEN (c.id * 50) % 2000 < 500 THEN 'SILVER'
        WHEN (c.id * 50) % 2000 < 1200 THEN 'GOLD'
        ELSE 'PLATINUM'
    END as card_type,
    CASE
        WHEN (12 - (c.id % 12)) <= 0 THEN 'EXPIRED'
        ELSE 'ACTIVE'
    END as status
FROM customers c
WHERE c.is_member = true;

-- ==========================================
-- INSERT SHOWTIMES (150+ showtimes)
-- ==========================================

-- Generate showtimes for the past 30 days and next 7 days
-- Multiple showtimes per day (morning, afternoon, evening, night)
-- Various rooms and movies

SET @start_date = DATE_SUB(CURDATE(), INTERVAL 30 DAY);
SET @end_date = DATE_ADD(CURDATE(), INTERVAL 7 DAY);

-- Room 1 showtimes (mix of movies)
INSERT INTO showtimes (movie_id, room_id, showtime, ticket_price, available_seats)
SELECT
    m.id as movie_id,
    1 as room_id,
    TIMESTAMP(DATE_ADD(@start_date, INTERVAL day_offset DAY), time_slot) as showtime,
    CASE
        WHEN HOUR(time_slot) < 12 THEN 60000
        WHEN HOUR(time_slot) < 18 THEN 80000
        ELSE 100000
    END as ticket_price,
    100 as available_seats
FROM (
    SELECT id FROM movies WHERE status = 'NOW_SHOWING' ORDER BY RAND() LIMIT 15
) m
CROSS JOIN (
    SELECT 0 as day_offset UNION ALL SELECT 1 UNION ALL SELECT 2 UNION ALL SELECT 3 UNION ALL SELECT 4 UNION ALL
    SELECT 5 UNION ALL SELECT 6 UNION ALL SELECT 7 UNION ALL SELECT 8 UNION ALL SELECT 9 UNION ALL
    SELECT 10 UNION ALL SELECT 11 UNION ALL SELECT 12 UNION ALL SELECT 13 UNION ALL SELECT 14 UNION ALL
    SELECT 15 UNION ALL SELECT 16 UNION ALL SELECT 17 UNION ALL SELECT 18 UNION ALL SELECT 19 UNION ALL
    SELECT 20 UNION ALL SELECT 21 UNION ALL SELECT 22 UNION ALL SELECT 23 UNION ALL SELECT 24 UNION ALL
    SELECT 25 UNION ALL SELECT 26 UNION ALL SELECT 27 UNION ALL SELECT 28 UNION ALL SELECT 29 UNION ALL
    SELECT 30 UNION ALL SELECT 31 UNION ALL SELECT 32 UNION ALL SELECT 33 UNION ALL SELECT 34 UNION ALL
    SELECT 35 UNION ALL SELECT 36 UNION ALL SELECT 37
) days
CROSS JOIN (
    SELECT '09:00:00' as time_slot UNION ALL
    SELECT '12:00:00' UNION ALL
    SELECT '15:00:00' UNION ALL
    SELECT '18:00:00' UNION ALL
    SELECT '21:00:00'
) times
WHERE day_offset % 3 = MOD(m.id, 3)  -- Distribute movies across days
LIMIT 100;

-- Room 2 showtimes
INSERT INTO showtimes (movie_id, room_id, showtime, ticket_price, available_seats)
SELECT
    m.id as movie_id,
    2 as room_id,
    TIMESTAMP(DATE_ADD(@start_date, INTERVAL day_offset DAY), time_slot) as showtime,
    CASE
        WHEN HOUR(time_slot) < 12 THEN 60000
        WHEN HOUR(time_slot) < 18 THEN 80000
        ELSE 100000
    END as ticket_price,
    80 as available_seats
FROM (
    SELECT id FROM movies WHERE status = 'NOW_SHOWING' ORDER BY RAND() LIMIT 15
) m
CROSS JOIN (
    SELECT 0 as day_offset UNION ALL SELECT 1 UNION ALL SELECT 2 UNION ALL SELECT 3 UNION ALL SELECT 4 UNION ALL
    SELECT 5 UNION ALL SELECT 6 UNION ALL SELECT 7 UNION ALL SELECT 8 UNION ALL SELECT 9 UNION ALL
    SELECT 10 UNION ALL SELECT 11 UNION ALL SELECT 12 UNION ALL SELECT 13 UNION ALL SELECT 14 UNION ALL
    SELECT 15 UNION ALL SELECT 16 UNION ALL SELECT 17 UNION ALL SELECT 18 UNION ALL SELECT 19 UNION ALL
    SELECT 20 UNION ALL SELECT 21 UNION ALL SELECT 22 UNION ALL SELECT 23 UNION ALL SELECT 24 UNION ALL
    SELECT 25 UNION ALL SELECT 26 UNION ALL SELECT 27 UNION ALL SELECT 28 UNION ALL SELECT 29 UNION ALL
    SELECT 30 UNION ALL SELECT 31 UNION ALL SELECT 32 UNION ALL SELECT 33 UNION ALL SELECT 34 UNION ALL
    SELECT 35 UNION ALL SELECT 36 UNION ALL SELECT 37
) days
CROSS JOIN (
    SELECT '10:30:00' as time_slot UNION ALL
    SELECT '13:30:00' UNION ALL
    SELECT '16:30:00' UNION ALL
    SELECT '19:30:00' UNION ALL
    SELECT '22:00:00'
) times
WHERE day_offset % 3 = MOD(m.id + 1, 3)
LIMIT 100;

-- Room 3 showtimes
INSERT INTO showtimes (movie_id, room_id, showtime, ticket_price, available_seats)
SELECT
    m.id as movie_id,
    3 as room_id,
    TIMESTAMP(DATE_ADD(@start_date, INTERVAL day_offset DAY), time_slot) as showtime,
    CASE
        WHEN HOUR(time_slot) < 12 THEN 80000
        WHEN HOUR(time_slot) < 18 THEN 100000
        ELSE 120000
    END as ticket_price,
    50 as available_seats
FROM (
    SELECT id FROM movies WHERE status = 'NOW_SHOWING' ORDER BY RAND() LIMIT 15
) m
CROSS JOIN (
    SELECT 0 as day_offset UNION ALL SELECT 1 UNION ALL SELECT 2 UNION ALL SELECT 3 UNION ALL SELECT 4 UNION ALL
    SELECT 5 UNION ALL SELECT 6 UNION ALL SELECT 7 UNION ALL SELECT 8 UNION ALL SELECT 9 UNION ALL
    SELECT 10 UNION ALL SELECT 11 UNION ALL SELECT 12 UNION ALL SELECT 13 UNION ALL SELECT 14 UNION ALL
    SELECT 15 UNION ALL SELECT 16 UNION ALL SELECT 17 UNION ALL SELECT 18 UNION ALL SELECT 19 UNION ALL
    SELECT 20 UNION ALL SELECT 21 UNION ALL SELECT 22 UNION ALL SELECT 23 UNION ALL SELECT 24 UNION ALL
    SELECT 25 UNION ALL SELECT 26 UNION ALL SELECT 27 UNION ALL SELECT 28 UNION ALL SELECT 29 UNION ALL
    SELECT 30 UNION ALL SELECT 31 UNION ALL SELECT 32 UNION ALL SELECT 33 UNION ALL SELECT 34 UNION ALL
    SELECT 35 UNION ALL SELECT 36 UNION ALL SELECT 37
) days
CROSS JOIN (
    SELECT '11:00:00' as time_slot UNION ALL
    SELECT '14:00:00' UNION ALL
    SELECT '17:00:00' UNION ALL
    SELECT '20:00:00' UNION ALL
    SELECT '22:30:00'
) times
WHERE day_offset % 3 = MOD(m.id + 2, 3)
LIMIT 100;

-- ==========================================
-- INSERT INVOICES & TICKETS (200+ invoices)
-- Only for past showtimes
-- ==========================================

-- Generate invoices for random past showtimes
INSERT INTO invoices (invoice_number, customer_id, staff_id, showtime_id, created_at, total_amount, discount, final_amount, payment_method, status, notes)
SELECT
    CONCAT('INV', DATE_FORMAT(s.showtime, '%Y%m%d'), LPAD((@row_number := @row_number + 1), 4, '0')) as invoice_number,
    FLOOR(1 + RAND() * 20) as customer_id,  -- Random customer 1-20
    2 as staff_id,  -- staff1
    s.id as showtime_id,
    DATE_ADD(s.showtime, INTERVAL -FLOOR(RAND() * 120) MINUTE) as created_at,  -- Sold within 2 hours before showtime
    s.ticket_price * num_tickets as total_amount,
    CASE
        WHEN c.is_member = 1 THEN s.ticket_price * num_tickets * 0.1  -- 10% discount for members
        ELSE 0
    END as discount,
    CASE
        WHEN c.is_member = 1 THEN s.ticket_price * num_tickets * 0.9
        ELSE s.ticket_price * num_tickets
    END as final_amount,
    CASE FLOOR(RAND() * 3)
        WHEN 0 THEN 'CASH'
        WHEN 1 THEN 'CREDIT_CARD'
        ELSE 'MOMO'
    END as payment_method,
    'PAID' as status,
    CASE
        WHEN c.is_member = 1 THEN 'Member discount applied'
        ELSE NULL
    END as notes
FROM (
    SELECT id, showtime, ticket_price
    FROM showtimes
    WHERE showtime < NOW()
    AND showtime > DATE_SUB(NOW(), INTERVAL 30 DAY)
    ORDER BY RAND()
    LIMIT 200
) s
CROSS JOIN (SELECT @row_number := 0) r
CROSS JOIN (
    SELECT 1 as num_tickets UNION ALL SELECT 2 UNION ALL SELECT 3 UNION ALL SELECT 4
) tickets_count
CROSS JOIN customers c
WHERE c.id = FLOOR(1 + RAND() * 20)
LIMIT 200;

-- Generate tickets for each invoice
INSERT INTO tickets (invoice_id, seat_number, ticket_type, price)
SELECT
    i.id as invoice_id,
    CONCAT(
        CHAR(65 + FLOOR(RAND() * 10)),  -- Row A-J
        LPAD(FLOOR(1 + RAND() * 20), 2, '0')  -- Seat 01-20
    ) as seat_number,
    CASE FLOOR(RAND() * 3)
        WHEN 0 THEN 'REGULAR'
        WHEN 1 THEN 'VIP'
        ELSE 'COUPLE'
    END as ticket_type,
    i.total_amount / FLOOR(1 + (i.total_amount / s.ticket_price)) as price
FROM invoices i
JOIN showtimes s ON i.showtime_id = s.id
CROSS JOIN (
    SELECT 1 as ticket_num UNION ALL SELECT 2 UNION ALL SELECT 3 UNION ALL SELECT 4
) ticket_numbers
WHERE ticket_numbers.ticket_num <= FLOOR(1 + (i.total_amount / s.ticket_price))
LIMIT 600;

-- Update available seats based on sold tickets
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

SELECT 'Data seeding completed!' as message;
SELECT COUNT(*) as total_movies FROM movies;
SELECT COUNT(*) as total_customers FROM customers;
SELECT COUNT(*) as total_memberships FROM memberships;
SELECT COUNT(*) as total_showtimes FROM showtimes;
SELECT COUNT(*) as total_invoices FROM invoices;
SELECT COUNT(*) as total_tickets FROM tickets;
SELECT SUM(final_amount) as total_revenue FROM invoices WHERE status = 'PAID';
