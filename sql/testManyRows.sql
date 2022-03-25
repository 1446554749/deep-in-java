select *
from t_many_rows
limit 100,10;
select *
from t_many_rows
limit 10000,10;
select *
from t_many_rows
limit 1000000,10;
select *
from t_many_rows
limit 5000000,10;
EXPLAIN
select *
from t_many_rows
limit 9000000,100;

EXPLAIN
select *
from t_many_rows
where id >= 9000000
limit 100;
EXPLAIN
select *
from t_many_rows
where id >= 9000000
  and id <= 9000000 + 100;