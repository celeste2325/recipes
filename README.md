# Adicook Project

este projecto esta echo en [java 17 LTS](https://community.chocolatey.org/packages/corretto17jdk)
AWS [SES](https://aws.amazon.com/es/ses/) para email
[Docker](https://www.docker.com/) para container
Choco en windows para facil instalacion de los paquetes

## Installation


install [choco](https://chocolatey.org/install) es un sistema para installar paquetes de forma facil en windows parecido a brew(mac)/apt(linux)

abre una terminal/cmd en modo adiministrador y corre:
```bash
choco install corretto17jdk
choco install docker-desktop
choco install awscli
```




## Configurar el ambiente
levanatar la BD con docker
```bash
docker run --name recetas -e "ACCEPT_EULA=Y" -e "SA_PASSWORD=ROOTfancyPass252!" -p 1433:1433 -d mcr.microsoft.com/mssql/server:2022-latest 
```

ahora configurar aws
```bash
aws configure 
```
esto te generar un prompt tendras que meter el access key y el secret key y la region (us-east-1) output (json)
pedir los credenciales al administrador del grupo



## License

[MIT](https://choosealicense.com/licenses/mit/)
