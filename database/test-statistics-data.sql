-- ==========================================
-- TEST DATA FOR MOVIE STATISTICS MODULE
-- Specific data for testing Level 2 & Level 3 drill-down
-- ==========================================

USE cineman;

-- ==========================================
-- Ensure we have the first 3 movies (Mai, Godzilla, Dune)
-- ==========================================

-- Movie 1: Mai (if not exists)
INSERT IGNORE INTO movies (id, title, director, genre, duration, release_date, rating, description, status, country)
VALUES (1, 'Mai', 'Tran Thanh', 'Drama', 131, '2024-02-10', 7.2, 'A Vietnamese drama film', 'NOW_SHOWING', 'Vietnam');

-- Movie 2: Godzilla x Kong: The New Empire (if not exists)
INSERT IGNORE INTO movies (id, title, director, genre, duration, release_date, rating, description, status, country)
VALUES (2, 'Godzilla x Kong: The New Empire', 'Adam Wingard', 'Action', 115, '2024-03-29', 6.5, 'An epic battle awaits', 'NOW_SHOWING', 'USA');

-- Movie 3: Dune: Part Two (if not exists)
INSERT IGNORE INTO movies (id, title, director, genre, duration, release_date, rating, description, status, country)
VALUES (3, 'Dune: Part Two', 'Denis Villeneuve', 'Sci-Fi', 166, '2024-03-01', 8.7, 'Paul Atreides unites with Chani', 'NOW_SHOWING', 'USA');

-- ==========================================
-- Ensure we have rooms
-- ==========================================

INSERT IGNORE INTO rooms (id, name, capacity, room_type, status)
VALUES
(1, 'Phòng 2D-01', 100, '2D', 'ACTIVE'),
(2, 'Phòng 3D-01', 80, '3D', 'ACTIVE'),
(3, 'Phòng IMAX-01', 50, 'IMAX', 'ACTIVE');

-- ==========================================
-- INSERT SHOWTIMES for Movie 1 (Mai)
-- Create multiple showtimes in past 30 days
-- ==========================================

-- Delete existing showtimes for movie 1 to avoid duplicates
DELETE FROM tickets WHERE invoice_id IN (SELECT id FROM invoices WHERE showtime_id IN (SELECT id FROM showtimes WHERE movie_id = 1));
DELETE FROM invoices WHERE showtime_id IN (SELECT id FROM showtimes WHERE movie_id = 1);
DELETE FROM showtimes WHERE movie_id = 1;

-- Showtimes for past 30 days
INSERT INTO showtimes (movie_id, room_id, showtime, ticket_price, available_seats, status)
VALUES
-- Week 1 (30 days ago)
(1, 1, DATE_SUB(NOW(), INTERVAL 30 DAY) + INTERVAL 20 HOUR, 100000, 95, 'COMPLETED'),
(1, 2, DATE_SUB(NOW(), INTERVAL 29 DAY) + INTERVAL 18 HOUR, 120000, 75, 'COMPLETED'),
(1, 3, DATE_SUB(NOW(), INTERVAL 28 DAY) + INTERVAL 15 HOUR, 150000, 45, 'COMPLETED'),

-- Week 2 (25 days ago)
(1, 1, DATE_SUB(NOW(), INTERVAL 27 DAY) + INTERVAL 20 HOUR, 100000, 90, 'COMPLETED'),
(1, 2, DATE_SUB(NOW(), INTERVAL 26 DAY) + INTERVAL 18 HOUR, 120000, 70, 'COMPLETED'),
(1, 3, DATE_SUB(NOW(), INTERVAL 25 DAY) + INTERVAL 15 HOUR, 150000, 42, 'COMPLETED'),

-- Week 3 (20 days ago)
(1, 1, DATE_SUB(NOW(), INTERVAL 24 DAY) + INTERVAL 20 HOUR, 100000, 92, 'COMPLETED'),
(1, 2, DATE_SUB(NOW(), INTERVAL 23 DAY) + INTERVAL 18 HOUR, 120000, 72, 'COMPLETED'),
(1, 3, DATE_SUB(NOW(), INTERVAL 22 DAY) + INTERVAL 15 HOUR, 150000, 43, 'COMPLETED'),

-- Week 4 (15 days ago) - High demand weekend
(1, 1, DATE_SUB(NOW(), INTERVAL 21 DAY) + INTERVAL 20 HOUR, 100000, 85, 'COMPLETED'),
(1, 2, DATE_SUB(NOW(), INTERVAL 20 DAY) + INTERVAL 18 HOUR, 120000, 60, 'COMPLETED'),
(1, 3, DATE_SUB(NOW(), INTERVAL 19 DAY) + INTERVAL 15 HOUR, 150000, 35, 'COMPLETED'),

-- Recent week (10 days ago)
(1, 1, DATE_SUB(NOW(), INTERVAL 18 DAY) + INTERVAL 20 HOUR, 100000, 88, 'COMPLETED'),
(1, 2, DATE_SUB(NOW(), INTERVAL 17 DAY) + INTERVAL 18 HOUR, 120000, 65, 'COMPLETED'),
(1, 3, DATE_SUB(NOW(), INTERVAL 16 DAY) + INTERVAL 15 HOUR, 150000, 38, 'COMPLETED'),

-- Last week (7 days ago)
(1, 1, DATE_SUB(NOW(), INTERVAL 15 DAY) + INTERVAL 20 HOUR, 100000, 93, 'COMPLETED'),
(1, 2, DATE_SUB(NOW(), INTERVAL 14 DAY) + INTERVAL 18 HOUR, 120000, 73, 'COMPLETED'),
(1, 3, DATE_SUB(NOW(), INTERVAL 13 DAY) + INTERVAL 15 HOUR, 150000, 44, 'COMPLETED'),

-- This week (recent)
(1, 1, DATE_SUB(NOW(), INTERVAL 6 DAY) + INTERVAL 20 HOUR, 100000, 87, 'COMPLETED'),
(1, 2, DATE_SUB(NOW(), INTERVAL 5 DAY) + INTERVAL 18 HOUR, 120000, 62, 'COMPLETED'),
(1, 3, DATE_SUB(NOW(), INTERVAL 4 DAY) + INTERVAL 15 HOUR, 150000, 36, 'COMPLETED');

-- ==========================================
-- INSERT INVOICES for Mai showtimes
-- ==========================================

-- Get showtime IDs for movie 1 (Mai)
SET @showtime1 = (SELECT id FROM showtimes WHERE movie_id = 1 AND room_id = 1 ORDER BY showtime DESC LIMIT 1);
SET @showtime2 = (SELECT id FROM showtimes WHERE movie_id = 1 AND room_id = 2 ORDER BY showtime DESC LIMIT 1);
SET @showtime3 = (SELECT id FROM showtimes WHERE movie_id = 1 AND room_id = 3 ORDER BY showtime DESC LIMIT 1);

-- Invoices for Showtime 1 (Room 2D-01) - 5 tickets sold
INSERT INTO invoices (invoice_number, customer_id, staff_id, showtime_id, created_at, total_amount, discount, final_amount, payment_method, status, notes)
VALUES
(CONCAT('INV', DATE_FORMAT(NOW(), '%Y%m%d'), '0001'), 1, 2, @showtime1, DATE_SUB(NOW(), INTERVAL 6 DAY) + INTERVAL 19 HOUR, 500000, 50000, 450000, 'CREDIT_CARD', 'PAID', 'Member discount: 10%'),
(CONCAT('INV', DATE_FORMAT(NOW(), '%Y%m%d'), '0002'), 3, 2, @showtime1, DATE_SUB(NOW(), INTERVAL 6 DAY) + INTERVAL 19 HOUR + INTERVAL 10 MINUTE, 300000, 0, 300000, 'CASH', 'PAID', NULL),
(CONCAT('INV', DATE_FORMAT(NOW(), '%Y%m%d'), '0003'), 5, 2, @showtime1, DATE_SUB(NOW(), INTERVAL 6 DAY) + INTERVAL 19 HOUR + INTERVAL 20 MINUTE, 200000, 20000, 180000, 'MOMO', 'PAID', 'Member discount: 10%'),
(CONCAT('INV', DATE_FORMAT(NOW(), '%Y%m%d'), '0004'), 2, 2, @showtime1, DATE_SUB(NOW(), INTERVAL 6 DAY) + INTERVAL 19 HOUR + INTERVAL 30 MINUTE, 400000, 40000, 360000, 'CREDIT_CARD', 'PAID', 'Member discount: 10%'),
(CONCAT('INV', DATE_FORMAT(NOW(), '%Y%m%d'), '0005'), 4, 2, @showtime1, DATE_SUB(NOW(), INTERVAL 6 DAY) + INTERVAL 19 HOUR + INTERVAL 40 MINUTE, 100000, 0, 100000, 'CASH', 'PAID', NULL);

-- Invoices for Showtime 2 (Room 3D-01) - 8 tickets sold
INSERT INTO invoices (invoice_number, customer_id, staff_id, showtime_id, created_at, total_amount, discount, final_amount, payment_method, status, notes)
VALUES
(CONCAT('INV', DATE_FORMAT(NOW(), '%Y%m%d'), '0006'), 6, 2, @showtime2, DATE_SUB(NOW(), INTERVAL 5 DAY) + INTERVAL 17 HOUR, 600000, 60000, 540000, 'MOMO', 'PAID', 'Member discount: 10%'),
(CONCAT('INV', DATE_FORMAT(NOW(), '%Y%m%d'), '0007'), 7, 2, @showtime2, DATE_SUB(NOW(), INTERVAL 5 DAY) + INTERVAL 17 HOUR + INTERVAL 5 MINUTE, 360000, 0, 360000, 'CASH', 'PAID', NULL),
(CONCAT('INV', DATE_FORMAT(NOW(), '%Y%m%d'), '0008'), 8, 2, @showtime2, DATE_SUB(NOW(), INTERVAL 5 DAY) + INTERVAL 17 HOUR + INTERVAL 15 MINUTE, 240000, 24000, 216000, 'CREDIT_CARD', 'PAID', 'Member discount: 10%'),
(CONCAT('INV', DATE_FORMAT(NOW(), '%Y%m%d'), '0009'), 9, 2, @showtime2, DATE_SUB(NOW(), INTERVAL 5 DAY) + INTERVAL 17 HOUR + INTERVAL 25 MINUTE, 480000, 48000, 432000, 'MOMO', 'PAID', 'Member discount: 10%'),
(CONCAT('INV', DATE_FORMAT(NOW(), '%Y%m%d'), '0010'), 10, 2, @showtime2, DATE_SUB(NOW(), INTERVAL 5 DAY) + INTERVAL 17 HOUR + INTERVAL 35 MINUTE, 120000, 0, 120000, 'CASH', 'PAID', NULL);

-- Invoices for Showtime 3 (Room IMAX-01) - 7 tickets sold
INSERT INTO invoices (invoice_number, customer_id, staff_id, showtime_id, created_at, total_amount, discount, final_amount, payment_method, status, notes)
VALUES
(CONCAT('INV', DATE_FORMAT(NOW(), '%Y%m%d'), '0011'), 11, 2, @showtime3, DATE_SUB(NOW(), INTERVAL 4 DAY) + INTERVAL 14 HOUR, 750000, 75000, 675000, 'CREDIT_CARD', 'PAID', 'Member discount: 10%'),
(CONCAT('INV', DATE_FORMAT(NOW(), '%Y%m%d'), '0012'), 12, 2, @showtime3, DATE_SUB(NOW(), INTERVAL 4 DAY) + INTERVAL 14 HOUR + INTERVAL 10 MINUTE, 450000, 0, 450000, 'CASH', 'PAID', NULL),
(CONCAT('INV', DATE_FORMAT(NOW(), '%Y%m%d'), '0013'), 13, 2, @showtime3, DATE_SUB(NOW(), INTERVAL 4 DAY) + INTERVAL 14 HOUR + INTERVAL 20 MINUTE, 300000, 30000, 270000, 'MOMO', 'PAID', 'Member discount: 10%'),
(CONCAT('INV', DATE_FORMAT(NOW(), '%Y%m%d'), '0014'), 14, 2, @showtime3, DATE_SUB(NOW(), INTERVAL 4 DAY) + INTERVAL 14 HOUR + INTERVAL 30 MINUTE, 600000, 60000, 540000, 'CREDIT_CARD', 'PAID', 'Member discount: 10%');

-- ==========================================
-- INSERT TICKETS for invoices
-- ==========================================

-- Get invoice IDs
SET @inv1 = (SELECT id FROM invoices WHERE invoice_number LIKE CONCAT('INV', DATE_FORMAT(NOW(), '%Y%m%d'), '0001'));
SET @inv2 = (SELECT id FROM invoices WHERE invoice_number LIKE CONCAT('INV', DATE_FORMAT(NOW(), '%Y%m%d'), '0002'));
SET @inv3 = (SELECT id FROM invoices WHERE invoice_number LIKE CONCAT('INV', DATE_FORMAT(NOW(), '%Y%m%d'), '0003'));
SET @inv4 = (SELECT id FROM invoices WHERE invoice_number LIKE CONCAT('INV', DATE_FORMAT(NOW(), '%Y%m%d'), '0004'));
SET @inv5 = (SELECT id FROM invoices WHERE invoice_number LIKE CONCAT('INV', DATE_FORMAT(NOW(), '%Y%m%d'), '0005'));
SET @inv6 = (SELECT id FROM invoices WHERE invoice_number LIKE CONCAT('INV', DATE_FORMAT(NOW(), '%Y%m%d'), '0006'));
SET @inv7 = (SELECT id FROM invoices WHERE invoice_number LIKE CONCAT('INV', DATE_FORMAT(NOW(), '%Y%m%d'), '0007'));
SET @inv8 = (SELECT id FROM invoices WHERE invoice_number LIKE CONCAT('INV', DATE_FORMAT(NOW(), '%Y%m%d'), '0008'));
SET @inv9 = (SELECT id FROM invoices WHERE invoice_number LIKE CONCAT('INV', DATE_FORMAT(NOW(), '%Y%m%d'), '0009'));
SET @inv10 = (SELECT id FROM invoices WHERE invoice_number LIKE CONCAT('INV', DATE_FORMAT(NOW(), '%Y%m%d'), '0010'));
SET @inv11 = (SELECT id FROM invoices WHERE invoice_number LIKE CONCAT('INV', DATE_FORMAT(NOW(), '%Y%m%d'), '0011'));
SET @inv12 = (SELECT id FROM invoices WHERE invoice_number LIKE CONCAT('INV', DATE_FORMAT(NOW(), '%Y%m%d'), '0012'));
SET @inv13 = (SELECT id FROM invoices WHERE invoice_number LIKE CONCAT('INV', DATE_FORMAT(NOW(), '%Y%m%d'), '0013'));
SET @inv14 = (SELECT id FROM invoices WHERE invoice_number LIKE CONCAT('INV', DATE_FORMAT(NOW(), '%Y%m%d'), '0014'));

-- Tickets for Invoice 1 (5 tickets × 100,000 = 500,000)
INSERT INTO tickets (invoice_id, showtime_id, seat_number, ticket_price, seat_type, status)
VALUES
(@inv1, @showtime1, 'A01', 100000, 'NORMAL', 'USED'),
(@inv1, @showtime1, 'A02', 100000, 'NORMAL', 'USED'),
(@inv1, @showtime1, 'A03', 100000, 'NORMAL', 'USED'),
(@inv1, @showtime1, 'A04', 100000, 'NORMAL', 'USED'),
(@inv1, @showtime1, 'A05', 100000, 'NORMAL', 'USED');

-- Tickets for Invoice 2 (3 tickets)
INSERT INTO tickets (invoice_id, showtime_id, seat_number, ticket_price, seat_type, status)
VALUES
(@inv2, @showtime1, 'B01', 100000, 'NORMAL', 'USED'),
(@inv2, @showtime1, 'B02', 100000, 'NORMAL', 'USED'),
(@inv2, @showtime1, 'B03', 100000, 'NORMAL', 'USED');

-- Tickets for Invoice 3 (2 tickets)
INSERT INTO tickets (invoice_id, showtime_id, seat_number, ticket_price, seat_type, status)
VALUES
(@inv3, @showtime1, 'C01', 100000, 'NORMAL', 'USED'),
(@inv3, @showtime1, 'C02', 100000, 'NORMAL', 'USED');

-- Tickets for Invoice 4 (4 tickets)
INSERT INTO tickets (invoice_id, showtime_id, seat_number, ticket_price, seat_type, status)
VALUES
(@inv4, @showtime1, 'D01', 100000, 'VIP', 'USED'),
(@inv4, @showtime1, 'D02', 100000, 'VIP', 'USED'),
(@inv4, @showtime1, 'D03', 100000, 'VIP', 'USED'),
(@inv4, @showtime1, 'D04', 100000, 'VIP', 'USED');

-- Tickets for Invoice 5 (1 ticket)
INSERT INTO tickets (invoice_id, showtime_id, seat_number, ticket_price, seat_type, status)
VALUES
(@inv5, @showtime1, 'E01', 100000, 'NORMAL', 'USED');

-- Tickets for Showtime 2 (Room 3D) - 120,000 per ticket
INSERT INTO tickets (invoice_id, showtime_id, seat_number, ticket_price, seat_type, status)
VALUES
-- Invoice 6 (5 tickets)
(@inv6, @showtime2, 'A01', 120000, 'NORMAL', 'USED'),
(@inv6, @showtime2, 'A02', 120000, 'NORMAL', 'USED'),
(@inv6, @showtime2, 'A03', 120000, 'NORMAL', 'USED'),
(@inv6, @showtime2, 'A04', 120000, 'NORMAL', 'USED'),
(@inv6, @showtime2, 'A05', 120000, 'NORMAL', 'USED'),
-- Invoice 7 (3 tickets)
(@inv7, @showtime2, 'B01', 120000, 'NORMAL', 'USED'),
(@inv7, @showtime2, 'B02', 120000, 'NORMAL', 'USED'),
(@inv7, @showtime2, 'B03', 120000, 'NORMAL', 'USED'),
-- Invoice 8 (2 tickets)
(@inv8, @showtime2, 'C01', 120000, 'VIP', 'USED'),
(@inv8, @showtime2, 'C02', 120000, 'VIP', 'USED'),
-- Invoice 9 (4 tickets)
(@inv9, @showtime2, 'D01', 120000, 'NORMAL', 'USED'),
(@inv9, @showtime2, 'D02', 120000, 'NORMAL', 'USED'),
(@inv9, @showtime2, 'D03', 120000, 'NORMAL', 'USED'),
(@inv9, @showtime2, 'D04', 120000, 'NORMAL', 'USED'),
-- Invoice 10 (1 ticket)
(@inv10, @showtime2, 'E01', 120000, 'NORMAL', 'USED');

-- Tickets for Showtime 3 (Room IMAX) - 150,000 per ticket
INSERT INTO tickets (invoice_id, showtime_id, seat_number, ticket_price, seat_type, status)
VALUES
-- Invoice 11 (5 tickets)
(@inv11, @showtime3, 'A01', 150000, 'NORMAL', 'USED'),
(@inv11, @showtime3, 'A02', 150000, 'NORMAL', 'USED'),
(@inv11, @showtime3, 'A03', 150000, 'NORMAL', 'USED'),
(@inv11, @showtime3, 'A04', 150000, 'NORMAL', 'USED'),
(@inv11, @showtime3, 'A05', 150000, 'NORMAL', 'USED'),
-- Invoice 12 (3 tickets)
(@inv12, @showtime3, 'B01', 150000, 'VIP', 'USED'),
(@inv12, @showtime3, 'B02', 150000, 'VIP', 'USED'),
(@inv12, @showtime3, 'B03', 150000, 'VIP', 'USED'),
-- Invoice 13 (2 tickets)
(@inv13, @showtime3, 'C01', 150000, 'NORMAL', 'USED'),
(@inv13, @showtime3, 'C02', 150000, 'NORMAL', 'USED'),
-- Invoice 14 (4 tickets)
(@inv14, @showtime3, 'D01', 150000, 'COUPLE', 'USED'),
(@inv14, @showtime3, 'D02', 150000, 'COUPLE', 'USED'),
(@inv14, @showtime3, 'D03', 150000, 'COUPLE', 'USED'),
(@inv14, @showtime3, 'D04', 150000, 'COUPLE', 'USED');

-- ==========================================
-- UPDATE available seats
-- ==========================================

UPDATE showtimes s
SET available_seats = available_seats - (
    SELECT COUNT(*)
    FROM tickets t
    JOIN invoices i ON t.invoice_id = i.id
    WHERE i.showtime_id = s.id AND i.status = 'PAID'
)
WHERE s.movie_id = 1;

-- ==========================================
-- VERIFICATION QUERIES
-- ==========================================

SELECT '=== TEST DATA SUMMARY ===' as message;

SELECT
    m.id as movie_id,
    m.title as movie_title,
    COUNT(DISTINCT s.id) as total_showtimes,
    COUNT(DISTINCT i.id) as total_invoices,
    COUNT(t.id) as total_tickets_sold,
    SUM(i.final_amount) as total_revenue
FROM movies m
LEFT JOIN showtimes s ON m.id = s.movie_id
LEFT JOIN invoices i ON s.id = i.showtime_id AND i.status = 'PAID'
LEFT JOIN tickets t ON i.id = t.invoice_id
WHERE m.id = 1
GROUP BY m.id, m.title;

SELECT
    s.id as showtime_id,
    s.showtime as showtime_date,
    r.name as room_name,
    COUNT(t.id) as tickets_sold,
    SUM(i.final_amount) as revenue
FROM showtimes s
JOIN rooms r ON s.room_id = r.id
LEFT JOIN invoices i ON s.id = i.showtime_id AND i.status = 'PAID'
LEFT JOIN tickets t ON i.id = t.invoice_id
WHERE s.movie_id = 1
AND s.id IN (@showtime1, @showtime2, @showtime3)
GROUP BY s.id, s.showtime, r.name
ORDER BY s.showtime DESC;

SELECT
    i.id as invoice_id,
    i.invoice_number,
    i.created_at,
    i.total_amount,
    i.discount,
    i.final_amount,
    i.payment_method,
    i.status,
    COUNT(t.id) as num_tickets
FROM invoices i
LEFT JOIN tickets t ON i.id = t.invoice_id
WHERE i.showtime_id = @showtime1
GROUP BY i.id
ORDER BY i.created_at DESC;

SELECT 'Test data loaded successfully! You can now test Level 2 and Level 3 statistics.' as message;
