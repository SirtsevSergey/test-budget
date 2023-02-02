package mobi.sevenwinds.app.author

import org.jetbrains.exposed.dao.EntityID
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.IntIdTable
import kotlin.reflect.KProperty

object AuthorTable : IntIdTable("author") {
    val fio = varchar("fio", 50)

    class AuthorEntity(id: EntityID<Int>) : IntEntity(id) {
        companion object : IntEntityClass<AuthorEntity>(AuthorTable)

        var fio by AuthorTable.fio

        fun toResponse(): AuthorRecord {
            return AuthorRecord(fio as String)
        }
    }
}

private operator fun String.setValue(authorEntity: AuthorTable.AuthorEntity, property: KProperty<*>, any: Any) =
{
    TODO("Not yet implemented")
}

private operator fun String.getValue(authorEntity: AuthorTable.AuthorEntity, property: KProperty<*>): Any {
    TODO("Not yet implemented")
}
