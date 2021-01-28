--제일 연봉 낮은 사람 5명
select rownum, eno, name, department, position_name, salary 
from (select e.eno, e.name, e.department, p.position_name, s.salary 
from employee e, position_list p, employee_salary s 
where e.eno = s.eno and e.position = p.position_no order by s.salary asc)
where rownum <= 5;

select * from EMPLOYEE_salary;

select rownum, eno,name, department, position_name, salary from(select e.eno, e.name, e.department, p.position_name, s.salary from employee e, position_list p , employee_salary s where e.eno = s.eno and e.position = p.position_no order by s.salary asc) where rownum <= 5;

select * from position_list;

select rownum, eno,name, department, position_name, salary, position from(select e.eno, e.name, e.department, p.position_name, s.salary, e.position from employee e, position_list p , employee_salary s where e.eno = s.eno and e.position = p.position_no order by s.salary) where rownum <= 5
--사원 검색 전체
select * from employee e, employee_salary s, position_list p where e.eno = s.eno and e.position = p.position_no
--사원번호로 원하는 데이터 하나만 검색
select e.eno, e.name, e.department, e.position, s.salary, p.position_name from employee e, employee_salary s, position_list p where e.eno = s.eno and e.position = p.position_no

select rownum, eno,name, department, position_name, salary from(select e.eno, e.name, e.department, p.position_name, s.salary from employee e, position_list p , employee_salary s where e.eno = s.eno and e.position = p.position_no order by s.salary asc) where rownum <= 5;

select * from position_list;


delete from DISPATCH;
delete from EMPLOYEE_SALARY;
delete from EMPLOYEE; 

select rownum, eno,name, department, position_name, salary, position from(select e.eno, e.name, e.department, p.position_name, s.salary, e.position from employee e, position_list p , employee_salary s where e.eno = s.eno and e.position = p.position_no order by s.salary) where rownum <= 5
select e.eno, e.name, e.department, e.position, s.salary, p.position_name from 
EMPLOYEE e, EMPLOYEE_SALARY s , position_list p 
where e.eno = s.eno and e.position = p.position_no