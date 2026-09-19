-- =====================================================
-- PROYECTO: AGENDA DE CITAS
-- BASE DE DATOS: agenda_citas_db
-- =====================================================

CREATE DATABASE IF NOT EXISTS agenda_citas_db;

USE agenda_citas_db;


-- =====================================================
-- TABLA CITAS
-- =====================================================
-- fecha_hora se almacena como DATETIME porque una cita
-- necesita guardar la fecha y la hora en un mismo campo.
--
-- cliente utiliza VARCHAR(100), suficiente para almacenar
-- el nombre completo del cliente.
--
-- servicio utiliza VARCHAR(255) porque la descripción del
-- servicio puede ser más extensa.
--
-- duracion_minutos utiliza INT porque la duración se
-- representa mediante una cantidad entera de minutos.
--
-- estado utiliza ENUM para permitir únicamente:
-- pendiente, confirmada o cancelada.
--
-- El ID es AUTO_INCREMENT para generar automáticamente
-- un identificador único para cada cita.
-- =====================================================

CREATE TABLE IF NOT EXISTS citas (

    id INT AUTO_INCREMENT PRIMARY KEY,

    cliente VARCHAR(100) NOT NULL,

    fecha_hora DATETIME NOT NULL,

    servicio VARCHAR(255) NOT NULL,

    duracion_minutos INT NOT NULL,

        estado ENUM(
        'pendiente',
        'confirmada',
        'cancelada'
    ) NOT NULL DEFAULT 'pendiente',

    requiere_confirmacion_llamada BOOLEAN NOT NULL DEFAULT FALSE,

    CHECK (duracion_minutos > 0)
);


-- =====================================================
-- DATOS DE EJEMPLO
-- =====================================================

INSERT INTO citas (
    cliente,
    fecha_hora,
    servicio,
    duracion_minutos,
    estado
)
VALUES
(
    'Maria Fernanda Lopez',
    '2026-09-20 14:30:00',
    'Corte de cabello',
    45,
    'pendiente'
),
(
    'Jose Ramirez',
    '2026-09-21 09:00:00',
    'Cambio de aceite',
    60,
    'confirmada'
),
(
    'Ana Castillo',
    '2026-09-18 16:00:00',
    'Consulta general',
    30,
    'cancelada'
);


-- =====================================================
-- CONSULTA DE VERIFICACION
-- =====================================================

SELECT * FROM citas;







