# Project One - Movie history

## Description

Movie history is a simple multi-tiered application in Java EE that applies the patterns and
techniques presented during the AMT 2019 lectures.
The idea of this app is that for each user it is possible to keep a history of the movies seen
so far. If the film exists in the database, the user can mark it as seen.

## Using Movie history

To use Movie history, you can build the app with `docker-compose`.
* First, execute the script `containers-start.sh`. You will find it at the root of the project.
* Then you can go to the [http://localhost:8080/project-one/](http://localhost:8080/project-one/).
* Here you can create a new account or use a example account like Ulysses Tourry (utourry3@xing.com, 9C6EedD4) or Riane Shord (rshord5@feedburner.com, ydu9lf3cc).

The two others script are used to stop(`containers-stop.sh`) or remove(`containers-down.sh`) the used containers.

## Documentation

All documentation files can be found in the [doc folder](doc/). You have access to the doc
files directly from the following links:

* [1 - Guidelines](doc/1_guidelines.md)
* [2 - Business Domain](doc/2_business_domain.md)
* [3 - Implementation](doc/3_implementation.md)
* [4 - Testing](doc/4_testing.md)
* [5 - Bugs](doc/5_known_bugs.md)

## Authors

| Name                                 | Email                                | Github      |
|--------------------------------------|--------------------------------------|-------------|
| Loris Gilliand                       | loris.gilliand@heig-vd.ch            | texx94      |
| Mateo Tutic                          | mateo.tutic@heig-vd.ch               | mtutic      |
