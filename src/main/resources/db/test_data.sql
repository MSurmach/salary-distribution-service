INSERT INTO salary_distribution_service.team (codename)
VALUES ('Alpha'),
       ('Beta');

INSERT INTO salary_distribution_service.employee (id, team_id)
VALUES (1, 1),
       (2, 2),
       (3, 2),
       (4, 2),
       (5, 1),
       (6, 1),
       (7, 1);

INSERT INTO salary_distribution_service.budget (period, total_amount)
VALUES ('2023-03', 10000),
       ('2023-04', 25456),
       ('2023-05', 18450),
       ('2023-06', 14908);

INSERT INTO salary_distribution_service.daily_work (employee_id, begin_date_and_time, end_date_and_time, work_duration)
VALUES (1, '2023-03-10 09:00', '2023-03-10 18:00', 300),
       (2, '2023-03-10 09:00', '2023-03-10 18:00', 480),
       (3, '2023-03-10 09:00', '2023-03-10 18:00', 320),
       (4, '2023-03-10 09:00', '2023-03-10 18:00', 450),
       (5, '2023-03-10 09:00', '2023-03-10 18:00', 120),
       (6, '2023-03-10 09:00', '2023-03-10 18:00', 430),
       (7, '2023-03-10 09:00', '2023-03-10 18:00', 180);