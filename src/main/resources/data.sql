insert into company (id, name, uuid) values
    (1000, 'Company name 1', '76e1312a-dc58-11ed-afa1-0242ac120002'),
    (1001, 'Company name 2', '76e1312a-dc58-11ed-afa1-0242ac120003');

insert into employee (id, name, company_id) values
    (1000, 'First', 1000),
    (1001, 'Second', 1000),
    (1002, 'Third', 1001),
    (1003, 'Fourth', 1001);