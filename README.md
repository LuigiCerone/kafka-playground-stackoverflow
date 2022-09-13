# kafka-playground-stackoverflow

Simple docker-based development environment with a set of tools used to quickly answer questions on Stack Overflow with apache-kafka tags.

During my spare time, I often visit Stack Overflow under the [apache-kafka tag](https://stackoverflow.com/questions/tagged/apache-kafka) to read interesting questions and have a chance to learn more about this tool. A few times, I have also tried to answer some questions and I have always spent too much time in the process of reproducing the highlighted issues. 

This small project was created precisely to solve this problem and to always have an isolated environment equipped with all the tools that should allow to be operational immediately.

In addition to the dockerised environment, there are two small projects in Java and Python realised with the main reference libraries in the two languages. The idea is to have a starting point to eventually build on further case-specific logic.


## Prerequisites


Tested with:
* Docker: 20.10.13
* docker-compose: 1.29.2
* Java: 17.0.2
* maven: 3.8.5
* Python: 3.9.4


## How to run

### Docker environment

#### Basic environment

```bash
# Create
docker-compose up -d

# Tear down
docker-compose down
```

#### Environment with UI

```bash
# Create
docker-compose -f docker-compose.yml -f docker-compose-ui.yml up -d

# Tear down
docker-compose -f docker-compose.yml -f docker-compose-ui.yml down
```

Then go to [http://localhost:8081](http://localhost:8081) to visit the Kafka UI.

### Java


#### Run

```bash
cd java/
mvn spring-boot:run
```

### Python

#### Setup venv


```bash
cd python/
python3 -m venv venv/
source venv/bin/activate
pip install --upgrade pip
pip install -r requirements.txt
```

#### Run


```bash
cd python/
source venv/bin/activate
python3 producer.py
python3 consumer.py
```

## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.


## License
[Apache License 2.0](https://choosealicense.com/licenses/apache-2.0/)
