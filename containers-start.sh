sudo mvn clean install
cp target/project-one.war docker/images/payara/project-one.war
cd docker/topology || exit
sudo docker-compose up --build -d