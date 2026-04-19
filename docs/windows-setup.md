# Windows Local Setup

This project requires Java 21 and Maven.

## Check current versions

```cmd
java -version
mvn -version
```

Expected Java version:

```text
java version "21..."
```

If Java shows 1.8, 11, or 17, install JDK 21 and update `JAVA_HOME`.

## Recommended installation with winget

Open PowerShell as Administrator and run:

```powershell
winget install EclipseAdoptium.Temurin.21.JDK
winget install Apache.Maven
```

Close and reopen the terminal.

## If Java 8 still appears after installing JDK 21

Run:

```cmd
where java
echo %JAVA_HOME%
java -version
```

If Java 8 appears first in `where java`, Windows is resolving the old Java path before JDK 21.

Common old Java entries to move down or remove from `Path`:

```text
C:\Program Files (x86)\Common Files\Oracle\Java\javapath
C:\Program Files\Java\jre1.8...
C:\Program Files (x86)\Java\jre1.8...
```

Set `JAVA_HOME` to the JDK 21 directory, usually similar to:

```text
C:\Program Files\Eclipse Adoptium\jdk-21.0.10.7-hotspot
```

Then add this entry near the top of `Path`:

```text
%JAVA_HOME%\bin
```

Close all terminals and open a new one.

## Environment variables

Set `JAVA_HOME` to your JDK 21 folder, for example:

```text
C:\Program Files\Eclipse Adoptium\jdk-21.x.x.x-hotspot
```

Add Maven `bin` folder to PATH if needed, for example:

```text
C:\Program Files\Apache\Maven\apache-maven-x.x.x\bin
```

## Validate

```cmd
java -version
mvn -version
mvn test
```

## Run locally

```cmd
docker compose up -d postgres
mvn spring-boot:run
```

Swagger UI:

```text
http://localhost:8080/swagger-ui.html
```
