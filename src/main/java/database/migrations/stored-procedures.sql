DROP PROCEDURE IF EXISTS save_equipment;

CREATE PROCEDURE save_equipment (
  IN p_typeId INT,
  IN p_brandId INT,
  IN p_stateId INT,
  IN p_acquisitionDate DATE,
  IN p_lastMaintenance DATE,
  IN p_nextMaintenance DATE,
  IN p_inventoryCode VARCHAR(50),
  OUT p_generatedId INT
) BEGIN
INSERT INTO
  Equipos (
    idTipoEquipo,
    idMarca,
    idEstado,
    fechaDeAdquisicion,
    ultimoMantenimiento,
    proximoMantenimiento,
    codigoInventario
  )
VALUES
  (
    p_typeId,
    p_brandId,
    p_stateId,
    p_acquisitionDate,
    p_lastMaintenance,
    p_nextMaintenance,
    p_inventoryCode
  );

SET
  p_generatedId = LAST_INSERT_ID();

END;

DROP PROCEDURE IF EXISTS save_component;

CREATE PROCEDURE save_component(
  IN p_idTipoComponente INT,
  IN p_idMarca INT,
  IN p_codigoInventario VARCHAR(50),
  IN p_fechaDeAdquisicion DATE,
  OUT p_generatedId INT
) BEGIN
INSERT INTO
  Componentes (
    idTipoComponente,
    idMarca,
    codigoInventario,
    fechaDeAdquisicion
  )
VALUES
  (
    p_idTipoComponente,
    p_idMarca,
    p_codigoInventario,
    p_fechaDeAdquisicion
  );

SET
  p_generatedId = LAST_INSERT_ID();

END;

DROP PROCEDURE IF EXISTS attach_component;

CREATE PROCEDURE attach_component(
  IN p_equipoId INT,
  IN p_componenteId INT,
  IN p_observaciones VARCHAR(255)
) BEGIN
INSERT INTO
  Equipo_Componente (idEquipo, idComponente, observaciones)
VALUES
  (p_equipoId, p_componenteId, p_observaciones);

END;

DROP PROCEDURE IF EXISTS search;

CREATE PROCEDURE search(
  IN columnNames VARCHAR(255),
  IN tableName VARCHAR(255),
  IN columnName VARCHAR(255),
  IN searchValue VARCHAR(255)
) BEGIN
SET
  @query = CONCAT(
    'SELECT ',
    columnNames,
    ' FROM ',
    tableName,
    ' WHERE ',
    columnName,
    ' = ?'
  );

PREPARE stmt
FROM
  @query;

SET
  @searchValue = searchValue;

EXECUTE stmt USING @searchValue;

DEALLOCATE PREPARE stmt;

END;