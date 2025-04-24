CREATE DATABASE "test_italika_db"

CREATE TABLE "producto" (
	"idProducto" INT NOT NULL,
	"nombre" VARCHAR(50) NOT NULL DEFAULT '''''',
	"descripcion" VARCHAR(150) NULL DEFAULT '''''' COLLATE 'Modern_Spanish_CI_AS',
	"precio" FLOAT NOT NULL DEFAULT '(0)',
	"cantidad" INT NOT NULL DEFAULT '(0)',
	"bloqueo" BIT NOT NULL DEFAULT '(0)'
)
;

CREATE PROCEDURE sp_get_all_products AS
BEGIN
    SELECT * FROM producto;
END;
GO

CREATE PROCEDURE sp_get_product_by_idProducto @idProducto INT AS
BEGIN
    SELECT * FROM producto WHERE idProducto = @idProducto;
END;
GO

CREATE PROCEDURE sp_create_product 
    @nombre NVARCHAR(100),
    @descripcion NVARCHAR(255),
    @precio DECIMAL(10,2),
    @cantidad INT,
    @bloqueado BIT
AS
BEGIN
    INSERT INTO producto (nombre, descripcion, precio, cantidad, bloqueado)
    VALUES (@nombre, @descripcion, @precio, @cantidad, @bloqueado);
END;
GO

CREATE PROCEDURE sp_update_product 
    @idProducto INT,
    @nombre NVARCHAR(100),
    @descripcion NVARCHAR(255),
    @precio DECIMAL(10,2),
    @cantidad INT
AS
BEGIN
    UPDATE producto 
    SET nombre = @nombre, descripcion = @descripcion, precio = @precio, cantidad = @cantidad
    WHERE idProducto = @idProducto;
END;
GO

CREATE PROCEDURE sp_delete_product @idProducto INT AS
BEGIN
    DELETE FROM producto WHERE idProducto = @idProducto;
END;
GO

CREATE PROCEDURE sp_is_product_blocked @idProducto INT AS
BEGIN
    SELECT bloqueo FROM producto WHERE idProducto = @idProducto;
END;
GO