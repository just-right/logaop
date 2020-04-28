-- auto-generated definition
--数据库mybatis
--set global time_zone = '+8:00';
create table logaopinfo
(
    id              int auto_increment
        primary key,
    sessionID       varchar(50)  null,
    requestURI      varchar(100) null,
    method          varchar(20)  null,
    parameterMapStr varchar(500) null,
    ipAddress       varchar(50)  null,
    beginDateTime   datetime     null,
    endDateTime     datetime     null,
    spendTimes      int          null,
    statusCode      int          null,
    responseInfo    varchar(500) null,
    error           varchar(5)   null,
    exception       varchar(50)  null,
    errorMsg        varchar(500) null
);



