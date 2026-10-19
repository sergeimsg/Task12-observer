# Task Array — Java Core Project

## Structure

```
task-array/
├── pom.xml
├── src/main/java/com/task/
│   ├── comparator/      — 
│     └── FirstDigitComparator.java — Comparator by first dijit
│     └── IdComparator.java — Comparator by Id number
│     └── LengthComparator.java — Comparator by length
│   ├── entity/          — AbstractArray, IntegerArray
│   ├── exception/       — ArrayException
│   ├── factory/         — ArrayFactory, IntegerArrayFactory (Factory Method)
│   ├── observer/      — 
│     └── ArrayObservable.java — Observable
│     └── ArrayObserver.java —  
│   ├── parser/          — ArrayParser, ArrayParserImpl
│   ├── reader/          — DataReader, DataReaderImpl
│   ├── repository/      — Repository, RepositoryImpl
│   ├── service/         — ArrayService, ArrayServiceImpl
│   ├── util/            — IdGeneration
│   ├── validator/       — ArrayValidator, ArrayValidatorImpl
│   ├── Warehaouse/      — Warehouse
│   └── ApplicationRun.java  — Application entry point
├── src/main/resources/
│   ├── data/
│     └── array_data.txt — Input data (mix of valid and invalid lines)
│   └── log4j2.xml       — Log4J2 configuration (console + file)
│       
└── src/test/java/com/task/
    ├── service/         — ArrayServiceImplTest
    ├── validator/       — ArrayValidatorImplTest
    ├── parser/          — ArrayParserImplTest
    ├── reader/          — DataReaderImplTest
    ├── factory/         — IntegerArrayFactoryTest
        
```

## Patterns

- **Factory Method**: `ArrayFactory` interface + `IntegerArrayFactory` implementation.


## Run/Main

```bash
mvn clean package
java -cp target/Task12-observer-1.0-SNAPSHOT.jar com.task.Main``
