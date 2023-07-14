-- Insertar valores en la tabla TipoComponente
INSERT INTO
  TipoComponentes (nombre)
VALUES
  ('Mouse'),
  ('Monitor'),
  ('CPU'),
  ('Batería');

-- Insertar valores en la tabla Estado
INSERT INTO
  Estados (nombre)
VALUES
  ('Bueno'),
  ('Dañado'),
  ('En Mantenimiento');

-- Insertar valores en la tabla TipoEquipo
INSERT INTO
  TipoEquipos (nombre)
VALUES
  ('Laptop'),
  ('Proyector'),
  ('Impresora'),
  ('Computadora de escritorio');

-- Insertar valores en la tabla Marca
INSERT INTO
  Marcas (nombre)
VALUES
  ('HP'),
  ('Dell'),
  ('Epson'),
  ('Canon'),
  ('Lenovo');

-- Insertar valores en la tabla TipoMantenimiento
INSERT INTO
  TipoMantenimientos (nombre)
VALUES
  ('Preventivo'),
  ('Correctivo');

-- Insertar valores en la tabla Fallas (puedes inventar las fallas)
INSERT INTO
  Fallas (descripcion)
VALUES
  ('Pantalla rota'),
  ('No enciende'),
  ('Impresiones borrosas'),
  ('Teclado no funciona'),
  ('Error de conexión');

-- Generar registros aleatorios en las tablas restantes
SET
  @rowCount := 0;

-- Generar registros en la tabla Componente
INSERT INTO
  Componentes (
    idTipoComponente,
    idMarca,
    codigoInventario,
    fechaDeAdquisicion
  )
SELECT
  tc.id AS idTipoComponente,
  m.id AS idMarca,
  CONCAT(
    'LAB08-',
    LPAD(@rowCount := @rowCount + 1, 4, '0')
  ) AS codigoInventario,
  DATE_SUB(NOW(), INTERVAL FLOOR(RAND() * 365) DAY) AS fechaDeAdquisicion
FROM
  TipoComponentes tc
  CROSS JOIN Marcas m
LIMIT
  50;

-- Generar registros en la tabla Equipo
INSERT INTO
  Equipos (
    idTipoEquipo,
    idMarca,
    idEstado,
    codigoInventario,
    fechaDeAdquisicion,
    ultimoMantenimiento,
    proximoMantenimiento
  )
SELECT
  te.id AS idTipoEquipo,
  m.id AS idMarca,
  e.id AS idEstado,
  CONCAT(
    'LAB08-',
    LPAD(@rowCount := @rowCount + 1, 4, '0')
  ) AS codigoInventario,
  DATE_SUB(NOW(), INTERVAL FLOOR(RAND() * 365) DAY) AS fechaDeAdquisicion,
  DATE_SUB(NOW(), INTERVAL FLOOR(RAND() * 365) DAY) AS ultimoMantenimiento,
  DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * 365) DAY) AS proximoMantenimiento
FROM
  TipoEquipos te
  CROSS JOIN Marcas m
  CROSS JOIN Estados e
LIMIT
  50;

-- Generar registros en la tabla Mantenimiento
INSERT INTO
  Mantenimientos (
    idEquipo,
    idTipoDeMantenimiento,
    descripcion,
    solucionesImplementadas
  )
SELECT
  e.id AS idEquipo,
  tm.id AS idTipoDeMantenimiento,
  CONCAT('Descripción del mantenimiento ', m.id) AS descripcion,
  CONCAT(
    'Soluciones implementadas para el mantenimiento ',
    m.id
  ) AS solucionesImplementadas
FROM
  Equipos e
  CROSS JOIN TipoMantenimientos tm
  CROSS JOIN Marcas m
  CROSS JOIN (
    SELECT
      @rowCount := 0
  ) AS r
LIMIT
  50;

-- Generar registros en la tabla Equipo_Componente
INSERT INTO
  Equipo_Componente (idEquipo, idComponente, observaciones)
SELECT
  e.id AS idEquipo,
  c.id AS idComponente,
  CONCAT(
    'Observaciones del componente ',
    c.id,
    ' en el equipo ',
    e.id
  ) AS observaciones
FROM
  Equipos e
  CROSS JOIN Componentes c
LIMIT
  50;

-- Generar registros en la tabla Mantenimiento_Falla
INSERT INTO
  Mantenimiento_Falla (idMantenimiento, idFalla, observaciones)
SELECT
  m.id AS idMantenimiento,
  f.id AS idFalla,
  CONCAT(
    'Observaciones de la falla ',
    f.id,
    ' en el mantenimiento ',
    m.id
  ) AS observaciones
FROM
  Mantenimientos m
  CROSS JOIN Fallas f
LIMIT
  50;