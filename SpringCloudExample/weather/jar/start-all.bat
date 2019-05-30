start cmd /k "java -jar weather-eureka-server-0.0.1-SNAPSHOT.jar
ping 127.0.0.1 -n 30 > nul
start cmd /k "java -jar city-collection-server-0.0.1-SNAPSHOT.jar --server.port=7001
start cmd /k "java -jar city-collection-server-0.0.1-SNAPSHOT.jar --server.port=7002
ping 127.0.0.1 -n 30 > nul
start cmd /k "java -jar weather-report-server-0.0.1-SNAPSHOT.jar --server.port=7003
start cmd /k "java -jar weather-report-server-0.0.1-SNAPSHOT.jar --server.port=7004
ping 127.0.0.1 -n 30 > nul
start cmd /k "java -jar weather-zuul-server-0.0.1-SNAPSHOT.jar --server.port=7005
ping 127.0.0.1 -n 30 > nul
start cmd /k "java -jar weather-collection-server-0.0.1-SNAPSHOT.jar --server.port=7006
start cmd /k "java -jar weather-collection-server-0.0.1-SNAPSHOT.jar --server.port=7007
ping 127.0.0.1 -n 30 > nul
start cmd /k "java -jar weather-ui-server-0.0.1-SNAPSHOT.jar

