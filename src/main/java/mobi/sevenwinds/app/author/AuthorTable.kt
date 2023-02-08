package mobi.sevenwinds.app.author


import mobi.sevenwinds.app.budget.BudgetTable
import mobi.sevenwinds.app.budget.BudgetTable.author
import mobi.sevenwinds.app.budget.BudgetTable.references
import mobi.sevenwinds.app.budget.BudgetTable.uniqueIndex
import org.jetbrains.exposed.dao.EntityID
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.IntIdTable
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Expression
import kotlin.reflect.KProperty

object AuthorTable : IntIdTable("author") {
    infix fun optionalReferrersOn(column: Column<EntityID<Int>?>): Any {
        return author
    }


    val sequelId = integer("sequel_id").uniqueIndex()
    val fio = varchar("fio", 50)
    val sequelid = integer("sequel_id")
        .uniqueIndex()
        .references(BudgetTable.id)

    class AuthorEntity(id: EntityID<Int>) : IntEntity(id) {
        companion object : IntEntityClass<AuthorEntity>(AuthorTable)

        var fio by AuthorTable.fio


        fun toResponse(): AuthorRecord {
            return AuthorRecord(fio as String)
        }
    }

}

