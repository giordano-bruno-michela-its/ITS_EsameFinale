# Manuale tecnico

### Requisiti
- Docker Compose

L'avvio su Windows può essere effettuato tramite WSL o Docker Desktop.

Con Docker Desktop è tutto predisposto per la connessione db su localhost, mentre con WSL è necessario configurare l'ip nel file .env.

### Preparazione (se con WSL)

Da terminale (su Linux) o da WSL/Powershell (su Windows) assicurarsi di salvare l'ip locale da inserire nel file .env nella variabile d'ambiente **WSL_DB_HOST**.

Nel caso si usi WSL:

```sh
ip addr show eth0
```

L'indirizzo IP è nella riga inet.

Esempio:

    ip addr show eth0
        2: eth0: <BROADCAST,MULTICAST,UP,LOWER_UP> mtu 1500 qdisc mq state UP group default qlen 1000
        link/ether 00:15:5d:35:86:15 brd ff:ff:ff:ff:ff:ff
        inet 172.18.104.83/20 brd 172.18.111.255 scope global eth0
            valid_lft forever preferred_lft forever
        inet6 fe80::215:5dff:fe35:8615/64 scope link
            valid_lft forever preferred_lft forever

L'IP in questo esempio è 172.18.104.83

### Avvio

Dalla directory principale è sufficiente avviare il docker-compose.yml, che creerà automaticamente 3 container per il db MySQL, l'app Java Spring Boot per il backend.

```sh
docker compose up -d
```

Le REST API del backend sono accessibili da http://localhost:8080/api (fare rifermento a SwaggerUI o alla collection Postman)

### API

Swagger UI URL: http://localhost:8080/swagger-ui/index.html

Collection Postman presente al percorso **/Final Test - REST API.postman_collection.json**

Documentazione Javadoc Maven visionabile nel file **/javadoc_maven/apidocs.zip** (decomprimere)

### Informazioni tecniche

- Backend: Java 17, Spring Boot 3.4, Maven 3.9
- Database: MySQL (in Docker container)
- Approccio strutturale: MVC, REST API, code-first (le tabelle vengono generate automaticamente dall'app
tramite hibernate, se assenti o modificate)
- Strumenti di sviluppo backend: JetBrains IntelliJ IDEA, Visual Studio Code, Docker
- Strumenti API: Swagger UI, Postman