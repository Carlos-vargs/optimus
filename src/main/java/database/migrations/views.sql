CREATE VIEW VistaEquipos AS
SELECT
  Equipos.codigoInventario,
  TipoEquipos.nombre AS tipoEquipo,
  Equipos.ultimoMantenimiento,
  Estados.nombre AS estado,
  Equipos.proximoMantenimiento
FROM
  Equipos
  JOIN TipoEquipos ON Equipos.idTipoEquipo = TipoEquipos.id
  JOIN Estados ON Equipos.idEstado = Estados.id
ORDER BY
  Equipos.id;

CREATE VIEW VistaEstadoEquipos AS
SELECT
  Estados.nombre AS estado,
  COUNT(*) AS cantidadEquipos,
  ROUND(
    (
      COUNT(*) / (
        SELECT
          COUNT(*)
        FROM
          Equipos
      ) * 100
    ),
    0
  ) AS porcentaje
FROM
  Equipos
  JOIN Estados ON Equipos.idEstado = Estados.id
GROUP BY
  Estados.nombre;

CREATE VIEW VistaActividades AS
SELECT
  m.created_at,
  CONCAT(
    'Equipo ',
    e.codigoInventario,
    ' - ',
    tm.nombre
  ) AS titulo
FROM
  Mantenimientos m
  INNER JOIN TipoMantenimientos tm ON m.idTipoDeMantenimiento = tm.id
  INNER JOIN Equipos e ON m.idEquipo = e.id;

CREATE VIEW VistaSimpleReport AS
SELECT
  Equipos.codigoInventario AS "Codigo Inventario",
  TipoEquipos.nombre AS "Tipo de Equipo",
  COUNT(Mantenimientos.id) AS "Cantidad de Mantenimientos",
  Equipos.proximoMantenimiento AS "Proxima Fecha de Mantenimiento"
FROM
  Equipos
  INNER JOIN TipoEquipos ON Equipos.idTipoEquipo = TipoEquipos.id
  LEFT JOIN Mantenimientos ON Equipos.id = Mantenimientos.idEquipo
GROUP BY
  Equipos.id,
  Equipos.codigoInventario,
  TipoEquipos.nombre,
  Equipos.proximoMantenimiento
ORDER BY
  Equipos.id;