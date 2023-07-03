create database recetas
go
use recetas
go

create table dbo.estados_autorizaciones
(
    id           int identity
        constraint pkEstados
            primary key,
    id_entidad   int not null,
    tipo_estado  varchar(15)
        constraint chk_tipo_estado
            check ([tipo_estado] = 'No autorizado' OR [tipo_estado] = 'Autorizado'),
    tipo_entidad varchar(20)
)
go

create table dbo.ingredientes
(
    idIngrediente int identity
        constraint pk_ingredientes
            primary key,
    nombre        varchar(200)
)
go

create table dbo.tipos
(
    idTipo      int identity
        constraint pk_tipos
            primary key,
    descripcion varchar(250)
)
go

create table dbo.unidades
(
    idUnidad    int identity
        constraint pk_unidades
            primary key,
    descripcion varchar(50) not null
)
go

create table dbo.conversiones
(
    idConversion       int identity
        constraint pk_conversiones
            primary key,
    idUnidadOrigen     int not null
        constraint fk_unidad_origen
            references dbo.unidades,
    idUnidadDestino    int not null
        constraint fk_unidad_destino
            references dbo.unidades,
    factorConversiones float
)
go

create table dbo.usuarios
(
    idUsuario    int identity
        constraint pk_usuarios
            primary key,
    mail         varchar(150)
        unique,
    nickname     varchar(100) not null,
    habilitado   varchar(2)
        constraint chk_habilitado
            check ([habilitado] = 'No' OR [habilitado] = 'Si'),
    nombre       varchar(150),
    avatar       varchar(300),
    tipo_usuario varchar(10)
        constraint chk_tipo_usuario
            check ([tipo_usuario] = 'Visitante' OR [tipo_usuario] = 'Alumno')
)
go

create table dbo.credenciales
(
    id                  int identity
        constraint pk_credenciales
            primary key,
    idUsuario           int
        constraint fk_credenciales_usuarios
            references dbo.usuarios,
    contrasenia         varchar(128),
    codigo_verificacion varchar(6)
)
go

create table dbo.recetas
(
    idReceta         int identity
        constraint pk_recetas
            primary key,
    idUsuario        int
        constraint fk_recetas_usuarios
            references dbo.usuarios,
    nombre           varchar(500),
    descripcion      varchar(1000),
    foto             varchar(300),
    porciones        int,
    cantidadPersonas int,
    idTipo           int
        constraint fk_recetas_tipos
            references dbo.tipos
)
go

create table dbo.calificaciones
(
    idCalificacion int identity
        constraint pk_calificaciones
            primary key,
    idusuario      int
        constraint fk_calificaciones_usuarios
            references dbo.usuarios,
    idReceta       int
        constraint fk_calificaciones_recetas
            references dbo.recetas,
    calificacion   int,
    comentarios    varchar(500)
)
go

create table dbo.favoritos
(
    id         int identity
        constraint pk_favoritos
            primary key,
    id_usuario int not null
        constraint fk_favoritos_usuarios
            references dbo.usuarios,
    id_receta  int not null
        constraint fk_favoritos_recetas
            references dbo.recetas
)
go

create table dbo.fechasReceta
(
    id            int identity
        constraint pk_fechasReceta
            primary key,
    idReceta      int
        constraint fk_fechasReceta_receta
            references dbo.recetas,
    fechaCreacion datetime
)
go

create table dbo.fotos
(
    idfoto    int identity
        constraint pk_fotos
            primary key,
    idReceta  int not null
        constraint fk_fotos_recetas
            references dbo.recetas,
    urlFoto   varchar(300),
    extension varchar(5)
)
go

create table dbo.pasos
(
    idPaso   int identity
        constraint pk_pasos
            primary key,
    idReceta int
        constraint fk_pasos_recetas
            references dbo.recetas,
    nroPaso  int,
    texto    varchar(2000)
)
go

create table dbo.multimedia
(
    idContenido    int identity
        constraint pk_multimedia
            primary key,
    idPaso         int not null
        constraint fk_multimedia_pasos
            references dbo.pasos,
    tipo_contenido varchar(10)
        constraint chk_tipo_contenido
            check ([tipo_contenido] = 'audio' OR [tipo_contenido] = 'video' OR [tipo_contenido] = 'foto'),
    extension      varchar(5),
    urlContenido   varchar(300)
)
go

create table dbo.utilizados
(
    idUtilizado   int identity
        constraint pk_utilizados
            primary key,
    idReceta      int
        constraint fk_utilizados_recetas
            references dbo.recetas,
    idIngrediente int
        constraint fk_utilizados_ingredientes
            references dbo.ingredientes,
    cantidad      int,
    idUnidad      int
        constraint fk_utilizados_unidades
            references dbo.unidades,
    observaciones varchar(500)
)
go

