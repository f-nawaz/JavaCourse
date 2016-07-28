
use filmstore;
select title from Film
join OrderTable_Film on Film.Id = OrderTable_Film.Films_Id
where OrderTable_Film.Order_Id = 1;




