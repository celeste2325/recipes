package com.distribuidas.recetas.repositorios;

import com.distribuidas.recetas.modelo.entities.Receta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RecetaRepository extends JpaRepository<Receta, Integer> {
    Receta findByNombreAndIdUsuario(String nombre, Integer idUsuario);

    List<Receta> findByNombre(String nombreReceta);

    List<Receta> findByIdTipo(Integer idTipo);

    @Query(value = "SELECT r FROM Receta r inner join Utilizado u on r.idReceta = u.idReceta WHERE u.idIngrediente = ?1")
    List<Receta> recetasPorIngrediente(Integer idIngrediente);

    @Query(value = "SELECT r FROM Receta r  inner join Utilizado u on r.idReceta = u.idReceta where u.idIngrediente <> ?1")
    List<Receta> recetasSinIngredientes(Integer idIngrediente);

    List<Receta> findByIdUsuario(Integer idUsuario);

    List<Receta> findByNombreLikeIgnoreCase(String s);

    @Query(value = "SELECT r FROM Receta r  inner join Usuario u on r.idUsuario = u.idUsuario where r.nombre = ?1 order by u.nombre")
    List<Receta> findByNombreOrderByNombreUser(String nombreReceta);

    @Query(value = "SELECT top 3 r.* FROM recetas r inner join usuarios u on r.idUsuario = u.idUsuario inner join fechasReceta f on r.idReceta = f.idReceta where r.idUsuario = ?1 order by f.fechaCreacion desc ", nativeQuery = true)
    List<Receta> recetasPorUsuarioOrdenadasPorFecha(Integer idUsuario);

    @Query(value = "SELECT r FROM Receta r  inner join FechaReceta f on r.idReceta = f.idReceta where r.nombre = ?1 order by f.fechaCreacion")
    List<Receta> findByNombreOrderByAntiguedad(String nombreReceta);

    @Query(value = "SELECT DISTINCT r.*, fr.fechaCreacion, usu.nombre, r.nombre FROM recetas r inner join utilizados u on r.idReceta = u.idReceta inner join fechasReceta fr on r.idReceta = fr.idReceta inner join estados_autorizaciones es on es.id_entidad = r.idReceta inner join usuarios usu on r.idUsuario = usu.idUsuario WHERE (r.idReceta = ?1  OR r.nombre = ?2 OR r.idTipo = ?3 OR u.idIngrediente = ?4 OR usu.nombre = ?7 OR r.idUsuario = ?8) and ((es.tipo_estado='Autorizado' and es.tipo_entidad='Receta')  OR (es.tipo_estado='No autorizado' and es.tipo_entidad='Receta' and r.idUsuario = ?5)) order by ?6 ", nativeQuery = true)
    List<Object> recetasByParamQuery(Integer idReceta, String nombreReceta, Integer idTipo, Integer idIngrediente,  Integer IdUsuarioObligatorio, String tipoOrdenamiento, String nombreUsuario,Integer idUsuario);

    //@Query(value = "SELECT DISTINCT r, fr.fechaCreacion, usu.nombre, r.nombre FROM Receta r inner join Utilizado u on r.idReceta = u.idReceta inner join FechaReceta fr on r.idReceta = fr.idReceta inner join EstadoAutorizacion es on es.idEntidad = r.idReceta inner join Usuario usu on r.idUsuario = usu.idUsuario WHERE (r.idReceta = ?1  OR r.nombre = ?2 OR r.idTipo = ?3 OR u.idIngrediente = ?4 OR usu.nombre = ?7 OR r.idUsuario = ?8) and ((es.tipoEstado='Autorizado' and es.tipoEntidad='Receta')  OR (es.tipoEstado='No autorizado' and es.tipoEntidad='Receta' and r.idUsuario = ?5)) order by ?6 ")
    //List<Receta> recetasByParamQuery(Integer idReceta, String nombreReceta, Integer idTipo, Integer idIngrediente,  Integer IdUsuarioObligatorio, Object tipoOrdenamiento, String nombreUsuario,Integer idUsuario);

}
