create table Person
(

    PersonId  int(11),
    FirstName varchar(20),
    LastName  varchar(20)
);


create table Address
(

    AddressId int(11),
    PersonId  int(11),
    City      varchar(20),
    State     varchar(20)
);


# 答案
select P.FirstName, P.LastName, A.City, A.State  from Person P left join  Address A on P.PersonId = A.PersonId  ;
