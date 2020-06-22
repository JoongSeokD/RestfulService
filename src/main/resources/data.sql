insert into account values(90001, sysdate(), 'account1', 'test1', '701010-1111111');
insert into account values(90002, sysdate(), 'account2', 'test2', '801010-2222222');
insert into account values(90003, sysdate(), 'account3', 'test3', '901010-3333333');

insert into post values(10001, 'My first post', 90001);
insert into post values(10002, 'My second post', 90001);