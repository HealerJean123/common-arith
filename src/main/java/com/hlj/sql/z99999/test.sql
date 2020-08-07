




select  datediff('2020-01-02','2020-01-01') as diff ;   -- 1

select unix_timestamp();                        -- 1218290027
select unix_timestamp('2008-08-08');            -- 1218124800
select unix_timestamp('2008-08-08 12:30:00');   -- 1218169800



select to_days('0000-00-00'); -- 0
select to_days('2020-08-08'); -- 738010

select from_days(738010);  -- 2020-08-08


select 10 sub 3 ;


select sqrt(4); -- 2

