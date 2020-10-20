INSERT INTO citizenship (citizenship_code,citizenship_name)
VALUES (643, 'Российская Федерация');
INSERT INTO citizenship (citizenship_code,citizenship_name)
VALUES (826, 'Соединенное Королевство Великобритании');
INSERT INTO citizenship (citizenship_code,citizenship_name)
VALUES (840, 'Соединенные Штаты Америки');
INSERT INTO citizenship (citizenship_code,citizenship_name)
VALUES (250, 'Французская Республика');


INSERT INTO doc_type (doc_code,doc_name)
VALUES (03, 'Свидетельство о рождении');
INSERT INTO doc_type (doc_code,doc_name)
VALUES (10, 'Паспорт иностранного гражданина ');
INSERT INTO doc_type (doc_code,doc_name)
VALUES (21, 'Паспорт гражданина Российской Федерации');
INSERT INTO doc_type (doc_code,doc_name)
VALUES (91, 'Иные документы');

INSERT INTO doc_concrete (doc_number,doc_date, doc_code)
VALUES (11-11-11, '2001-01-01', 10 );
INSERT INTO doc_concrete (doc_number,doc_date, doc_code)
VALUES (22-22-22, '2002-02-02', 21 );
INSERT INTO doc_concrete (doc_number,doc_date, doc_code)
VALUES (33-33-33, '2003-03-03', 10 );
INSERT INTO doc_concrete (doc_number,doc_date, doc_code)
VALUES (44-44-44, '2004-04-04', 91 );


INSERT INTO organization (id, version, adress, full_name, inn, is_active, kpp, name, phone)
VALUES (1, 0, 'Hawthorne, CA', 'SpaceX', '111-1', true, '111', 'SpaceX', '111-111');
INSERT INTO organization (id, version, adress, full_name, inn, is_active, kpp, name, phone)
VALUES (2, 0, 'San Francisco', 'GitHub Inc', '222-2', true, '222', 'GitHub', '222-222');
INSERT INTO organization (id, version, adress, full_name, inn, is_active, kpp, name, phone)
VALUES (3, 0, 'Prague', 'JetBrains s.r.o. ', '333-3', true, '333', 'JetBrains', '333-333');

INSERT INTO office (id,  adress, is_active, name, phone, version, organization_id)
VALUES (1, 'Hawthorne, CA', true, 'SpaceX Office', '111-111', 0, 1);
INSERT INTO office (id,  adress, is_active, name, phone, version, organization_id)
VALUES (2, 'San Francisco', true, 'Github Office', '222-222', 0, 2);
INSERT INTO office (id,  adress, is_active, name, phone, version, organization_id)
VALUES (3, 'Prague', true, 'JetBrains Office', '333-333', 0, 3);

INSERT INTO usr (id,  first_name, is_identified, middle_name, phone, position, second_name, version,
                 citizenship_code, doc_concrete_doc_number, office_id)
VALUES (1, 'Elon' , true, 'Reeve', '2-2-2', 'Chief designer', 'Musk', 0,  840, 22-22-22, 1);
INSERT INTO usr (id,  first_name, is_identified, middle_name, phone, position, second_name, version,
                 citizenship_code, doc_concrete_doc_number, office_id)
VALUES (2, 'John' , true, 'Henry', '1-1-1', 'Drummer', 'Bonham', 0,  826, 11-11-11, 2);
INSERT INTO usr (id,  first_name, is_identified, middle_name, phone, position, second_name, version,
                 citizenship_code, doc_concrete_doc_number, office_id)
VALUES (3, 'Mark' , true, 'Yakovlevich', '3-3-3', 'Artist', 'Rothko', 0,  643, 33-33-33, 3);
INSERT INTO usr (id,  first_name, is_identified, middle_name, phone, position, second_name, version,
                 citizenship_code, doc_concrete_doc_number, office_id)
VALUES (4, 'Honore ' , true, 'de', '4-4-4', 'DWriter', 'Balzac', 0,  250, 44-44-44, 3);