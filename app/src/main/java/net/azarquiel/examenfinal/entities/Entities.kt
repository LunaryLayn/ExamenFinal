package net.azarquiel.examenfinal.entities

data class Usuario (
    val id: Long,
    val nick: String,
    val pass: String
)

data class Categoria (
    val id: Long,
    val nombre: String
)

data class Chiste (
    val id: Long,
    val idcategoria : Long,
    val contenido: String,
        )
data class Punto(
    val id: Long,
    val idchiste: Long,
    val puntos: Long
)
data class Respuesta(
    val categoria: Categoria,
    val categorias: List<Categoria>,
    val chiste: Chiste,
    val chistes: List<Chiste>,
    val punto: Punto,
    val puntos: List<Punto>,
    val usuario: Usuario,
    val usuarios: List<Usuario>,
    val avgpuntos: Float
)