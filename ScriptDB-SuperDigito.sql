CREATE TABLE Historial(
    IdHistorial NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    Numero VARCHAR(20),
    Resultado CHAR(1),
    Fecha DATE
);

/* SP-HistorialGetAll */
CREATE OR REPLACE PROCEDURE HistorialGetAll(
    pCursor OUT SYS_REFCURSOR
)IS
BEGIN
    OPEN pCursor FOR
        SELECT  IdHistorial,
                Numero, 
                Resultado, 
                Fecha
        FROM Historial
        ORDER BY IdHistorial;

END HistorialGetAll;

VARIABLE pCursor REFCURSOR;
CALL HistorialGetAll(:pCursor);
PRINT pCursor;

/* SP-HistorialGetByNumero */
CREATE OR REPLACE PROCEDURE HistorialGetByNumero(
    pCursor OUT SYS_REFCURSOR,
    pNumero IN VARCHAR
)IS
BEGIN
    OPEN pCursor FOR
        SELECT  IdHistorial,
                Numero, 
                Resultado, 
                Fecha
        FROM Historial
        WHERE Numero = pNumero;

END HistorialGetByNumero;

VARIABLE pCursor REFCURSOR;
CALL HistorialGetByNumero(:pCursor, '13');
PRINT pCursor;


/* SP-HistorialAdd */
CREATE OR REPLACE PROCEDURE HistorialAdd(
    pNumero IN VARCHAR,
    pResultado IN VARCHAR
)IS
BEGIN
    INSERT INTO Historial (Numero, Resultado, Fecha)
    VALUES(pNumero, pResultado, SYSDATE);
    
END HistorialAdd;

CALL HistorialAdd('23452345', '1');


/* SP-HistorialUpdate */
CREATE OR REPLACE PROCEDURE HistorialUpdate (
    pIdHistorial IN NUMBER
)IS
BEGIN
    UPDATE Historial
    SET Fecha = TO_DATE(CURRENT_DATE, 'yyyy-mm-dd')
    WHERE IdHistorial = pIdHistorial;

END HistorialUpdate;

CALL HistorialUpdate(1);

/* SP-HistorialDeleteById */
CREATE OR REPLACE PROCEDURE HistorialDeleteById(
    pIdHistorial IN NUMBER
)IS
BEGIN
    DELETE FROM Historial WHERE IdHistorial = pIdHistorial;
    
END HistorialDeleteById;

CALL HistorialDeleteById(3);

/* SP-HistorialDeleteAll */
CREATE OR REPLACE PROCEDURE HistorialDeleteAll IS
BEGIN
    DELETE FROM Historial;
    
END HistorialDeleteAll;

CALL HistorialDelete();
