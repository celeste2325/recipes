package com.distribuidas.recetas.repository;

import com.distribuidas.recetas.model.entities.Receta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface RecetaRepository extends JpaRepository<Receta, Integer> {
    Receta findByNombreAndIdUsuario(String nombre, Integer idUsuario);
    @Query(value = "SELECT r FROM Receta r inner join Usuario usu on r.idUsuario = usu.idUsuario inner join FechaReceta fr on r.idReceta = fr.idReceta inner join Utilizado u on r.idReceta = u.idReceta inner join EstadoAutorizacion es on es.idEntidad = r.idReceta WHERE u.idIngrediente <> ?1 and ((es.tipoEstado='Autorizado' and es.tipoEntidad='Receta') OR (es.tipoEstado='No autorizado' and es.tipoEntidad='Receta' and r.idUsuario = ?2 )) order by fr.fechaCreacion desc")
    List<Receta> recetasSinIngredientesOrdenadaPorAntiguedad(Integer idIngrediente, Integer idUsuario);
    @Query(value = "SELECT r FROM Receta r inner join Usuario usu on r.idUsuario = usu.idUsuario inner join Utilizado u on r.idReceta = u.idReceta inner join EstadoAutorizacion es on es.idEntidad = r.idReceta WHERE u.idIngrediente <> ?1 and ((es.tipoEstado='Autorizado' and es.tipoEntidad='Receta') OR (es.tipoEstado='No autorizado' and es.tipoEntidad='Receta' and r.idUsuario = ?2 )) order by usu.nombre asc")
    List<Receta> recetasSinIngredientesOrdenadaPorNombreUser(Integer idIngrediente, Integer idUsuario);
    @Query(value = "SELECT r FROM Receta r inner join Usuario usu on r.idUsuario = usu.idUsuario inner join Utilizado u on r.idReceta = u.idReceta inner join EstadoAutorizacion es on es.idEntidad = r.idReceta WHERE u.idIngrediente <> ?1 and ((es.tipoEstado='Autorizado' and es.tipoEntidad='Receta') OR (es.tipoEstado='No autorizado' and es.tipoEntidad='Receta' and r.idUsuario = ?2 )) order by r.nombre asc")
    List<Receta> recetasSinIngredientesOrdenadaPorNombre(Integer idIngrediente, Integer idUsuario);
    @Query(value = "SELECT r FROM Receta r inner join Usuario u on r.idUsuario = u.idUsuario inner join EstadoAutorizacion es on es.idEntidad = r.idReceta WHERE r.nombre like ?1% and ((es.tipoEstado='Autorizado' and es.tipoEntidad='Receta') OR (es.tipoEstado='No autorizado' and es.tipoEntidad='Receta' and r.idUsuario = ?2)) order by r.nombre")
    List<Receta> findByNombreLikeIgnoreCase(String s, Integer idUsuario);

    @Query(value = "SELECT r FROM Receta r inner join Usuario u on r.idUsuario = u.idUsuario inner join EstadoAutorizacion es on es.idEntidad = r.idReceta WHERE r.nombre = ?1 and ((es.tipoEstado='Autorizado' and es.tipoEntidad='Receta') OR (es.tipoEstado='No autorizado' and es.tipoEntidad='Receta' and r.idUsuario = ?2)) order by u.nombre")
    List<Receta> findByNombreOrderByNombreUser(String nombreReceta, Integer idUsuario);

    @Query(value = "SELECT top 3 r.* FROM recetas r inner join fechasReceta f on r.idReceta = f.idReceta inner join estados_autorizaciones es on es.id_entidad = r.idReceta where ((es.tipo_estado='Autorizado' and es.tipo_entidad='Receta') OR (es.tipo_estado='No autorizado' and es.tipo_entidad='Receta' and r.idUsuario = ?1)) order by f.fechaCreacion desc ", nativeQuery = true)
    List<Receta> recetasPorUsuarioOrdenadasPorFecha(Integer idUsuario);

    @Query(value = "SELECT r FROM Receta r inner join FechaReceta f on r.idReceta = f.idReceta inner join EstadoAutorizacion es on es.idEntidad = r.idReceta WHERE r.nombre = ?1 and ((es.tipoEstado='Autorizado' and es.tipoEntidad='Receta') OR (es.tipoEstado='No autorizado' and es.tipoEntidad='Receta' and r.idUsuario = ?2)) order by f.fechaCreacion desc")
    List<Receta> findByNombreOrderByAntiguedad(String nombreReceta, Integer idUsuario);

    @Query("SELECT DISTINCT r FROM Receta r inner join Usuario usu on r.idUsuario = usu.idUsuario inner join Utilizado u on r.idReceta = u.idReceta inner join EstadoAutorizacion es on es.idEntidad = r.idReceta WHERE (r.nombre = ?1 OR r.idTipo = ?2 OR u.idIngrediente = ?3 OR usu.idUsuario = ?4) and ((es.tipoEstado='Autorizado' and es.tipoEntidad='Receta') OR (es.tipoEstado='No autorizado' and es.tipoEntidad='Receta' and r.idUsuario = ?5)) order by r.nombre")
    List<Receta> recetasByParamQuery(String nombreReceta, Integer idTipo, Integer idIngrediente, Integer idUsuario, Integer idUsuarioObligatorio);

    @Query(value = "SELECT DISTINCT r FROM Receta r inner join EstadoAutorizacion es on es.idEntidad = r.idReceta WHERE (es.tipoEstado='Autorizado' and es.tipoEntidad='Receta') OR (es.tipoEstado='No autorizado' and es.tipoEntidad='Receta' and r.idUsuario = ?1)")
    List<Receta> devolverTodasLasrecetasHabilitadas(Integer idUsuario);
    @Query(value = "SELECT r FROM Receta r inner join Usuario u on r.idUsuario = u.idUsuario inner join EstadoAutorizacion es on es.idEntidad = r.idReceta WHERE r.idReceta = ?1 and ((es.tipoEstado='Autorizado' and es.tipoEntidad='Receta') OR (es.tipoEstado='No autorizado' and es.tipoEntidad='Receta' and r.idUsuario = ?2)) order by r.nombre")
    Optional<Receta> devuelveRecetasPorId(Integer id,Integer idUsuario);

}
