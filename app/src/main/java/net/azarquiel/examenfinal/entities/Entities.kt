package net.azarquiel.examenfinal.entities

import java.io.Serializable

data class Usuario (
    val id: Long,
    val nick: String,
    val pass: String
) : Serializable

data class Categoria (
    val id: Long,
    val nombre: String
) : Serializable

data class Chiste (
    val id: Long,
    val idcategoria : Long,
    val contenido: String,
        ) : Serializable
data class Punto(
    val id: Long,
    val idchiste: Long,
    val puntos: Long
): Serializable



data class Respuesta(
    val categoria: Categoria,
    val categorias: List<Categoria>,
    val chiste: Chiste,
    val chistes: List<Chiste>,
    val punto: Punto,
    val puntos: List<Punto>,
    val usuario: Usuario,
    val usuarios: List<Usuario>,
    val avg: Int
)