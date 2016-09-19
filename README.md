drill-module.conf：配置自定义函数的路径

1.	mvn clean package
2.	复制drill-ext-0.0.1.jar和drill-ext-0.0.1-sources.jar 到$DRILL_HOME/jars/3rdparty目录
3.  二进制转换函数使用 select bin(10) from (VALUES (1)) ,参数必须是int类型
4.	重启drillbit服务