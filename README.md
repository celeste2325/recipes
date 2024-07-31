# Adicook Project (Back-end)

This project is built using [Java 17 LTS](https://community.chocolatey.org/packages/corretto17jdk), AWS [SES](https://aws.amazon.com/en/ses/) for email, [Docker](https://www.docker.com/) for containers, and Chocolatey on Windows for easy package installation.

## Installation

Install [Chocolatey](https://chocolatey.org/install), a package manager for Windows similar to brew (macOS) / apt (Linux).

Open a terminal or command prompt in administrator mode and run:
```bash
choco install corretto17jdk
choco install docker-desktop
choco install awscli
```
# Configuring the Environment
Start the database with Docker:
```
docker run --name recetas -e "ACCEPT_EULA=Y" -e "SA_PASSWORD=ROOTfancyPass252!" -p 1433:1433 -d mcr.microsoft.com/mssql/server:2022-latest
```
# Configuring AWS
```
aws configure 

```
This will prompt you to enter the access key, secret key, and region (us-east-1). Set the output format to (json). Obtain the credentials from the group administrator.

## License

[MIT](https://choosealicense.com/licenses/mit/)




