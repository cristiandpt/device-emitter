PROJECT_NAME = device-emitter
OUTPUT_FILE = $(PROJECT_NAME).zip
DEPENDENCIES = jpa,devtools
GROUP_ID = com.cristiandpt
ARTIFACT_ID = $(PROJECT_NAME)
PACKAGE_NAME = com.cristiandpt.$(PROJECT_NAME)
VERSION = 0.0.1
DESCRIPTION = "Spring Boot Application for measurement device generation"
LANGUAGE = kotlin
JAVA_VERSION = 17
TYPE = gradle-project

# Default target
all: download unzip

# Target to download the Spring Boot project
download:
	curl https://start.spring.io/starter.zip \
		-d dependencies=$(DEPENDENCIES) \
		-d name=$(PROJECT_NAME) \
		-d artifactId=$(ARTIFACT_ID) \
		-d groupId=$(GROUP_ID) \
		-d packageName=$(PACKAGE_NAME) \
		-d version=$(VERSION) \
		-d description=$(DESCRIPTION) \
		-d type=$(TYPE) \
		-d language=$(LANGUAGE) \
		-d javaVersion=$(JAVA_VERSION) \
		-o $(OUTPUT_FILE)

# Target to unzip the downloaded project
unzip:
	unzip $(OUTPUT_FILE)

# Clean up the downloaded zip file
clean:
	rm -f $(OUTPUT_FILE)

deep_clean:
	find -mindepth 1 ! -name "Makefile" -exec rm -rf {} +

kafka-desribe:
	kafka-topics --bootstrap-server kafka:9092 --describe --topic measurement-topic

kafka-list:
	kafka-topics --bootstrap-server kafka:9092 --list

kafka-published:
	docker exec -it kafka kafka-console-consumer --bootstrap-server kafka:9092 --topic measurement-topic --from-beginning --property print.key=true --property key.deserializer=org.apache.kafka.common.serialization.StringDeserializer --property value.deserializer=org.apache.kafka.common.serialization.StringDeserializer
