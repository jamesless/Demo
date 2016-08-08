drop database  IF EXISTS airscape;
-- 1.用root用户创建一个数据库kpidata.
CREATE DATABASE airscape  DEFAULT CHARACTER SET utf8 ;

-- 2.用root用户授权用户并刷新权限.如果工具遇到问题，用命令行mysql命令执行。
GRANT ALL ON airscape.* TO 'airscape'@'%' IDENTIFIED BY 'airscape';  

FLUSH PRIVILEGES;
