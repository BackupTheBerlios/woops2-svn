create table actiontype(code varchar(5),label varchar(32),introduction_date varchar(16) ,introduction_price float , quantity integer , current_price float, primary key (code) );
create table alarmtype(alarm_type_id INTEGER GENERATED BY DEFAULT AS IDENTITY(START WITH 1, INCREMENT BY 1), label varchar(100));
create table administrator(login varchar(32), password varchar(32), primary key(login));
create table wallet(wallet_id INTEGER GENERATED BY DEFAULT AS IDENTITY(START WITH 1, INCREMENT BY 1),cash float );
create table event(event_id INTEGER GENERATED BY DEFAULT AS IDENTITY(START WITH 1, INCREMENT BY 1), price float, date varchar(16), action_type_code varchar(5),foreign key (action_type_code) references actiontype(code));
create table action(action_id INTEGER GENERATED BY DEFAULT AS IDENTITY(START WITH 1, INCREMENT BY 1), buy_price float, buy_date varchar(16), quantity integer, action_type_code varchar(5), wallet_id integer,  foreign key (action_type_code) references actiontype(code), foreign key (wallet_id) references wallet(wallet_id));
create table alarm(alarm_id INTEGER GENERATED BY DEFAULT AS IDENTITY(START WITH 1, INCREMENT BY 1), name varchar(32), alarm_type_id integer ,action_type_code varchar(5), wallet_id integer,  foreign key (wallet_id) references wallet(wallet_id), foreign key (alarm_type_id) references alarmtype(alarm_type_id), foreign key (action_type_code) references actiontype(code));
create table operator(login varchar(32), password varchar(32),wallet_id integer, foreign key (wallet_id) references wallet(wallet_id),  primary key(login));
