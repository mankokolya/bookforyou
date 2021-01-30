delete from book_category;
delete from book_author;
delete from book;
delete from author;
delete from category;

delete from users_roles;
delete from users;
delete from role;
delete from publisher;

insert into role values (1, 'ADMIN');
-- insert into role values (2, 'USER');
-- insert into role values (3, 'LIBRARIAN');

INSERT INTO users VALUES (1, 'mankokolya@gmail.com', 'Mykola', 'Manko',
                          '$2y$12$3/SLz8PouovfM/CqC.nOt.wOXzSwSkq7WKZ8DVuTL0D/lTL0c6m5y');

INSERT INTO users_roles VALUES(1, 1);

--INSERT PUBLISHER

INSERT INTO publisher values (1, 'Pottermore Publishing'); --Harry Porter
INSERT INTO publisher values (2, 'McGraw-Hill Education'); --Java 7
INSERT INTO publisher values (3, 'Азбука'); --Гекльбери Фин, Кобзар, Доктор Айболит
INSERT INTO publisher values (4, 'Bantam'); --Thrones
INSERT INTO publisher values (5, 'РОСМЭН'); --Гарри Потер
INSERT INTO publisher values (6, 'Брайт Букс'); --Богатий тато


insert into book(id, description, published_date, quantity, title, publisher_id) values (1,
                         'Java 7: A Beginner''s Guide, Fifth Edition will have you programming in Java right away. ',
                         '13-10-2017',
                         10,
                         'Java. A Beginner''s Guid',
                         2);

insert into book(id, description, published_date, quantity, title, publisher_id) values (2,
                         'When the Knight Bus crashes through the darkness and screeches to a halt in front of him, ' ||
                            'it''s the start of another far from ordinary year at Hogwarts for Harry Potter. ',
                         '20-11-2015',
                         25,
                         'Harry Potter and the Prisoner of Azkaban',
                         1);
insert into book(id, description, published_date, quantity, title, publisher_id) values (3,
                            'В "Приключениях Гекльберри Финна" - прямом сюжетном продолжении романа "Приключения ' ||
                            'Тома Сойера" - пестрая жизнь американской глубинки сороковых годов XIX века увидена ' ||
                            'глазами двух беглецов, уносимых на деревянном плоту великой рекой Миссисипи: один из них - ' ||
                            'заглавный герой, удравший от невыносимо скучной благонамеренной жизни у вдовы Дуглас и ' ||
                            'выходок неожиданно воротившегося папаши-пропойцы, а другой - его новый приятель Джим, ' ||
                            'чернокожий раб мисс Уотсон, которого толкнула в бега перспектива продажи в южные штаты.',
                         '16-05-2016',
                         5,
                         'Приключения Гекльберри Финна',
                         3);
insert into book (id, description, published_date, quantity, title, publisher_id) values (4,
                            'Winter is coming. Such is the stern motto of House Stark, the northernmost of the fiefdoms ' ||
                            'that owe allegiance to King Robert Baratheon in far-off King''s Landing. There Eddard Stark ' ||
                            'of Winterfell rules in Robert''s name. There his family dwells in peace and comfort: his ' ||
                            'proud wife, Catelyn; his sons Robb, Brandon, and Rickon; his daughters Sansa and Arya; ' ||
                            'and his bastard son, Jon Snow. Far to the north, behind the towering Wall, ' ||
                            'lie savage Wildings and worse—unnatural things relegated to myth during the centuries-long ' ||
                            'summer, but proving all too real and all too deadly in the turning of the season. ' ||
                            'Yet a more immediate threat lurks to the south, where Jon Arryn, the Hand of the King, ' ||
                            'has died under mysterious circumstances. Now Robert is riding north to Winterfell, bringing ' ||
                            'his queen, the lovely but cold Cersei, his son, the cruel, vainglorious Prince Joffrey, ' ||
                            'and the queen''s brothers Jaime and Tyrion of the powerful and wealthy House Lannister—the ' ||
                            'first a swordsman without equal, the second a dwarf whose stunted stature belies a brilliant ' ||
                            'mind. All are heading for Winterfell and a fateful encounter that will change the course of ' ||
                            'kingdoms. Meanwhile, across the Narrow Sea, Prince Viserys, heir of the fallen House ' ||
                            'Targaryen, which once ruled all of Westeros, schemes to reclaim the throne with an army of ' ||
                            'barbarian Dothraki—whose loyalty he will purchase in the only coin left to him: his ' ||
                            'beautiful yet innocent sister, Daenerys.',
                         '14-08-2003',
                         1,
                         'A Game of Thrones',
                         4);
insert into book (id, description, published_date, quantity, title, publisher_id) values (5,
                            'Максимально повне зібрання поетового спадку, яке поєднало всі твори, що потрапляли під ' ||
                            'цензурування як за царату, так і за радянщини. До того ж чимало творів подано з авторовими ' ||
                            'варіянтами, практично невідомими широкому загалові.',
                         '10-07-2018',
                         5,
                         'Кобзар',
                         3);
insert into book (id, description, published_date, quantity, title, publisher_id) values (6,
                            'Повесть-сказка о добром докторе Айболите и его удивительных приключениях в Африке, ' ||
                            'пересказанная для детей Корнеем Ивановичем Чуковским по мотивам известной ' ||
                            'книги английского писателя Гью Лофтинга.',
                         '10-12-2019',
                         10,
                         'Доктор Айболит',
                         3);
insert into book (id, description, published_date, quantity, title, publisher_id) values (7,
                            'Гарри Поттера ждёт самое страшное испытание в жизни — смертельная схватка с Волан-де-Мортом.' ||
                            ' Ждать помощи не от кого — Гарри одинок как никогда. Друзья и враги Гарри предстают в ' ||
                            'совершенно неожиданном свете. Граница между Добром и Злом становится всё призрачнее… ' ||
                            'В седьмой, финальной книге Дж. К. Роулинг раскрывает все магические тайны.',
                         '21-02-2007',
                         1,
                         'Гарри Поттер и Дары Cмерти',
                        5);
insert into book (id, description, published_date, quantity, title, publisher_id)values (8,
                        'Whether you''re a beginner or an experienced pro, you''ll ' ||
                        'find recipes that are sure to satisfy your Java programming appetite!',
                         '05 November 2007',
                         10,
                         'Herb Schildt''s Java Programming Cookbook',
                         2);
insert into book (id, description, published_date, quantity, title, publisher_id) values (9,
                            'Роберт Кіосакі переконаний, що в школі наші діти не отримують потрібних фінансових знань і ' ||
                            'потім все життя працюють заради грошей, замість того щоб змусити гроші працювати на себе. ' ||
                            'Він порадував читачів новим виданням вже культової книги зі змінами та доповненнями для ' ||
                            'сьогоднішнього світу, сьогоднішніх ринкових умов і 9 новими розділами. На жаль, у сфері ' ||
                            'освіти мало що змінилося, школа до цих пір не дає підростаючому поколінню фінансових азів. ' ||
                            'Навчіть дітей поводитися з грошима раніше, ніж вони зіткнуться з матеріальними труднощами ' ||
                            'в нашому нестабільному світі!',
                         '24-10-2012',
                         14,
                         'Багатий тато, Бідний тато',
                         6);

--INSERT CATEGORIES
insert into category values (1, 'computer technology');
insert into category values (2, 'fantasy');
insert into category values (3, 'business');
insert into category values (4, 'poetry');
insert into category values (5, 'children');

--INSERT AUTHOR
insert into author values (1, 'J. K. Rowling');--harry porter children/fantasy
insert into author values (2, 'Марк Твен'); --фин children
insert into author values (3, 'Herbert Schildt');
insert into author values (4, 'Чуковский Корней Иванович');--children
insert into author values (5, 'George R. R. Martin');--thrones fantasy
insert into author values (6, 'Тарас Шевченко');
insert into author values (7, 'Роберт Т. Кійосакі');--children

--add category to the book
insert into book_category values(1, 1);
insert into book_category values(2, 2);
insert into book_category values(2, 5);
insert into book_category values(3, 2);
insert into book_category values(3, 5);
insert into book_category values(4, 2);
insert into book_category values(5, 4);
insert into book_category values(6, 5);
insert into book_category values(7, 2);
insert into book_category values(7, 5);
insert into book_category values(8, 1);
insert into book_category values(9, 3);

--add author to the book
insert into book_author values(1, 3);
insert into book_author values(2, 1);
insert into book_author values(3, 2);
insert into book_author values(4, 5);
insert into book_author values(5, 6);
insert into book_author values(6, 4);
insert into book_author values(7, 1);
insert into book_author values(8, 3);
insert into book_author values(9, 7);
