drop table if exists Teams;
create table Teams
(
    team_id   int,
    team_name varchar(32)
);
drop table if exists Matches;
create table Matches
(
    match_id    int,
    host_team   int,
    guest_team  int,
    host_goals  int,
    guest_goals int
);
INSERT INTO teams (team_id, team_name) VALUES (10, 'Leetcode FC');
INSERT INTO teams (team_id, team_name) VALUES (20, 'NewYork FC');
INSERT INTO teams (team_id, team_name) VALUES (30, 'Atlanta FC');
INSERT INTO teams (team_id, team_name) VALUES (40, 'Chicago FC');
INSERT INTO teams (team_id, team_name) VALUES (50, 'Toronto FC');
select * from Teams;

INSERT INTO matches (match_id, host_team, guest_team, host_goals, guest_goals) VALUES (1, 10, 20, 30, 0);
INSERT INTO matches (match_id, host_team, guest_team, host_goals, guest_goals) VALUES (2, 30, 10, 2, 2);
INSERT INTO matches (match_id, host_team, guest_team, host_goals, guest_goals) VALUES (3, 10, 50, 5, 1);
INSERT INTO matches (match_id, host_team, guest_team, host_goals, guest_goals) VALUES (4, 20, 30, 1, 0);
INSERT INTO matches (match_id, host_team, guest_team, host_goals, guest_goals) VALUES (5, 50, 30, 1, 0);
select * from Matches;


select t.team_id, t.team_name, ifnull(num_points, 0) from Teams  t left  join (
select team_id, sum(score) as num_points
from (
         (select host_team           as team_id,
                 sum(case
                         when (host_goals > guest_goals) then 3
                         when host_goals = guest_goals then 1
                         else 0 end) as score
          from Matches
          group by host_team)
         union all
         (select guest_team          as team_id,
                 sum(case
                         when (host_goals > guest_goals) then 0
                         when host_goals = guest_goals then 1
                         else 3 end) as score
          from Matches
          group by guest_team)
     ) m
group by team_id
) n on   t.team_id = n.team_id order by num_points desc , team_id
