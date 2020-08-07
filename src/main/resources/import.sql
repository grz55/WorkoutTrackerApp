INSERT INTO user (username, password) VALUES ('Peter123','$2a$10$XnX0s5bCCBZDrGB8qSTZIOGONJWc1VDltFeLP6oQLzJdmWWzGHbKm'),('Armin123','$2a$10$XnX0s5bCCBZDrGB8qSTZIOGONJWc1VDltFeLP6oQLzJdmWWzGHbKm');
INSERT INTO workout (additional_comment, date, user_id) VALUES ('Legs workout','2020-08-07',2),('Arms workout','2020-08-08',2),('Full body workout','2020-08-09',1);
INSERT INTO exercise_performed (name, rest_period, workout_id) VALUES ('Legs extensions',120,1),('Leg curls',120,1),('Calf raises',120,1),('Biceps curls',60,2),('Triceps extensions',60,2),('Forearm',60,2),('Squat',120,3),('Deadlift',120,3),('Bench press',120,3);
INSERT INTO exercise_sets (reps, weight, exercise_performed_id) VALUES (12,60,1),(10,70,1),(8,80,1),(10,50,2),(8,55,2),(6,60,2),(12,100,3),(10,110,3),(8,120,3),(12,30,4),(10,35,4),(8,40,4),(10,12,5),(8,14,5),(6,16,5),(15,10,6),(12,12,6),(10,14,6),(10,100,7),(10,100,7),(10,100,7),(10,120,8),(10,120,8),(10,120,8),(10,80,9),(10,80,9),(10,80,9);

