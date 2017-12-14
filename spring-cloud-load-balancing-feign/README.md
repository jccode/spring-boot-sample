# Launch services

Launch 3 `say-hello` instance to verify load balancing. 

1. Launch default `say-hello` instance, which binding to 8080. 

       cd say-hello
       mvn spring-boot:run
       
2. Launch another two in port 9092, 9999.

       cd say-hello
       SERVER_PORT=9092 mvn spring-boot:run
       SERVER_PORT=9999 mvn spring-boot:run

3. Launch `user` service.

       cd user
       mvn spring-boot:run
       
# Verify

      curl -s http://127.0.0.1:8888/hi?name=tom
      
And your requests to the User service should result in calls to Say Hello being spread across the running instances in 
round-robin form.

Also you can down one of an instance, you should see requests begin to balanced across the remaining instances.
