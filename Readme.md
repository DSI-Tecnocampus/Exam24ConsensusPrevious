# Examen DSI curs 2023-2024: Consensus

L'examen del dia 15 de Març de 2024 es basarà en aquest codi. Us recomanem que us hi familiaritzeu per tal d'afrontar l'examen
amb tranquil·litat. El tipus de preguntes serà similar al curs passat: circuit-breaker, comunicació asíncrona, arquitectura exhagonal,
algun patró d'infraestructura.

Implementarem un sistema per arribar a consens sobre temes concrets. El sistema permet a qualsevol usuari crear un tema de debat 
i que altres usuaris puguin votar-hi. Quan es crea el tema de debat (que li direm *consensus*), s'hi inclou una llista d'usuaris
participants i una data de finalització.
Els *consensus* es tanquen, o be quan s'arriba a la data de finalització, o be quan tots els participants han votat.

El sistema ja està implementat amb dos microserveis que funcionen i tenen implementada quasi tota la lògica.

### Microservei Consensus
Segueix l'arquitectura hexagonal. Conté les següents classes de domini:

**Theme**: que representa el tema sobre el qual es farà una votació (consensus). 
* id
* title
* description

**Consensus**: que representa una votació sobre un tema. 
* id
* theme
* participants (una llista d'emails d'usuaris i el que han votat)
* dueDate (data de tancament)
* state (obert/tancat)

Ofereix un API REST amb les següents crides, que teniu implementades i amb exemples al fitxer *calls.http*
* **POST /themes**: per crear un nou tema
* **GET /themes**: per llistar tots els temes
* **GET /themes/{id}**: per obtenir un tema
* **POST /consensus**: per crear un nou consensus
* **GET /consensus**: per llistar els resultats de tots els consensos
* **GET /consensus/{id}**: per obtenir el resultat d'un consens
* **PUT /consensus/{id}/vote**: per votar un consens

### Microservei People
Aquest microservei és pràcticament un CRUD i es salta moltes de les regles de l'arquitectura hexagonal. Per exemple, el @RestController
crida directament al repository. Tampoc separa el domini de la persistència, és a dir, la classe de domini té les anotacions JPA.

**Person**: 
* email (que fa de identificador)
* name
* surname
* title (Sr, Sra, Srta, Dr, Dra, etc.)

Ofereix un API REST amb les següents crides, que teniu implementades i amb exemples al fitxer *calls.http*
* **POST /people**: per crear un nou usuari
* **GET /people**: per llistar tots els usuaris
* **GET /people/{email}**: per obtenir un usuari
* **GET /people/{email}/exists**: per saber si un usuari existeix


### Lògica dels microserveis
Ja teniu implementada tota (quasi) la lògica. 
* Consensus
  * **Creació d'un consensus nou** (POST /consensus): Per crear un consensus nou, es necessita un *theme id*, una data de tancament (*dueDate*)
    i una llista d'usuaris participants. Abans de crear el *consensus* es comprova que tots els usuaris existeixen fent tantes crides
  com participants hi ha al microservei de *people*, usant l'API REST. Un cop comprovat que tots els usuaris existeixen, es crea el *consensus* amb estat obert 
  i s'emmagatzema a la BBDD. 
  * **Votació d'un consensus** (PUT /consensus/{id}/vote): Per votar un *consensus* es necessita l'identificador i l'email de l'usuari que vota, així
  com el sentit del seu vot (si / no / abstenció).
      Es comprova que l'usuari està a la llista de participants i que el *consensus* està obert. Si tot és correcte, es vota i es comprova si tots els participants han votat.
    En cas afirmatiu es tanca el *consensus*. 
  * **Tancament dels consensus caducats** El microservei de *consensus* té un *scheduler* que cada cinc segons comprova si hi ha *consensus* caducats. 
  És a dir, que encara estàn oberts (perquè no han votat tots els participants) i la data de tancament ja ha passat. En aquest cas es tanca el *consensus*.
* People
  * Implementa un CRUD de persones. També exposa una funcionalitat GET per saber si un usuari existeix. Aquesta funcionalitat és la que s'usa al microservei de *consensus*.

### Més coses
* Als fitxers *data.sql* pertinents, teniu 3 usuaris donats d'alta, 3 temes i 3 consensus, dels quals un està caducat.
* Teniu totes les dependències necessàries per fer l'examen. Si el intellij o el copilot insisteigen en afegir-ne més alguan cosa extranya passa. 
* També teniu un fitxer docker-compose per aixecar el RabbitMQ, que és el que s'usa per a la comunicació asíncrona. Podeu portar el Rabbit 
instal·lat des de casa, sigui de forma local o en un docker. Si el porteu de casa, no cal que executeu el docker-compose d'aquest projecte. 