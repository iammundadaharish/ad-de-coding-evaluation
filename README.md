1. Clone the repository.
2. I assume that mysql is already installed on the machine that is going to run this code. If it is a different machine then please update the IP address instead of localhost in src/main/resource/application.yml
3. To run the code do mvn spring-boot:run
3. This will start the service on 9002 port.
4. When the service comes up, it will create all the required tables in MYSQL and insert the seed data. I have used liquibase for the same.

Run the below commands to test the webservices:

POST APIs:
1. To submit a valid request"
curl -i -H "Accept: application/json" -H "Content-Type: application/json" --data @validrequest.json http://localhost:9002/api/v1/customer/2/collect
2. To submit a malformed json
curl -i -H "Accept: application/json" -H "Content-Type: application/json" --data @malformedrequest.json http://localhost:9002/api/v1/customer/2/collect
3. To submit for an inactive customer
curl -i -H "Accept: application/json" -H "Content-Type: application/json" --data @inactivecustomerrequest.json http://localhost:9002/api/v1/customer/3/collect
4. To submit from a blacklisted user agent -- Use any agent that is in the blacklist list.
curl -i -H "Accept: application/json" -H "Content-Type: application/json" --data @validrequest.json http://localhost:9002/api/v1/customer/2/collect
5. To submit with a blacklisted IP:
curl -i -H "Accept: application/json" -H "Content-Type: application/json" --data @blacklistediprequest.json http://localhost:9002/api/v1/customer/2/collect
6. To submit with a non existent customer id:
curl -i -H "Accept: application/json" -H "Content-Type: application/json" --data @invalidcustomeridrequest.json http://localhost:9002/api/v1/customer/100/collect


GET APIs -- PLEASE NOTE THAT THE GET STATS APIs ARE AGGREGATED BY DAY

1. To get stats for all days for a given customer id:
curl -i -H "Accept: application/json" -H "Content-Type: application/json" http://localhost:9002/api/v1/customer/2/requestDate/ALL/stats

2. TO get stats for a specific day and a speicific customer id
curl -i -H "Accept: application/json" -H "Content-Type: application/json" http://localhost:9002/api/v1/customer/2018-06-18/requestDate/ALL/stats
