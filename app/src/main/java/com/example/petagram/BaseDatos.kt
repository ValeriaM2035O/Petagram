package com.example.petagram

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


class BaseDatos(context: Context) :
    SQLiteOpenHelper(context, "petagram.db", null, 1) {

    companion object {
        const val TABLA = "mascota"
        const val COL_ID = "id"
        const val COL_NOMBRE = "nombre"
        const val COL_FOTO = "foto"
        const val COL_RATING = "rating"
        const val COL_ULTIMO_LIKE = "ultimoLike"
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(
            """
            CREATE TABLE $TABLA (
                $COL_ID INTEGER PRIMARY KEY,
                $COL_NOMBRE TEXT NOT NULL,
                $COL_FOTO INTEGER NOT NULL,
                $COL_RATING INTEGER NOT NULL,
                $COL_ULTIMO_LIKE INTEGER NOT NULL
            )
            """.trimIndent()
        )
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLA")
        onCreate(db)
    }

    // Guarda (o actualiza) la mascota completa y deja solo las últimas 5
    fun guardarMascota(m: Mascota) {
        val db = writableDatabase
        val valores = ContentValues().apply {
            put(COL_ID, m.id)
            put(COL_NOMBRE, m.nombre)
            put(COL_FOTO, m.foto)
            put(COL_RATING, m.rating)
            put(COL_ULTIMO_LIKE, m.ultimoLike)
        }
        db.insertWithOnConflict(TABLA, null, valores, SQLiteDatabase.CONFLICT_REPLACE)

        db.execSQL(
            "DELETE FROM $TABLA WHERE $COL_ID NOT IN " +
                    "(SELECT $COL_ID FROM $TABLA ORDER BY $COL_ULTIMO_LIKE DESC LIMIT 5)"
        )
    }

    // Las últimas 5 mascotas a las que se les dio rating, la más reciente primero
    fun obtenerUltimas5(): MutableList<Mascota> {
        val lista = mutableListOf<Mascota>()
        val cursor = readableDatabase.rawQuery(
            "SELECT $COL_ID, $COL_NOMBRE, $COL_FOTO, $COL_RATING, $COL_ULTIMO_LIKE " +
                    "FROM $TABLA ORDER BY $COL_ULTIMO_LIKE DESC LIMIT 5",
            null
        )
        cursor.use {
            while (it.moveToNext()) {
                lista.add(
                    Mascota(
                        id = it.getInt(0),
                        nombre = it.getString(1),
                        foto = it.getInt(2),
                        rating = it.getInt(3),
                        ultimoLike = it.getLong(4)
                    )
                )
            }
        }
        return lista
    }
}