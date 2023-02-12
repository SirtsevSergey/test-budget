package mobi.sevenwinds.app.author


import mobi.sevenwinds.app.author.AuthorTable.AuthorEntity.Companion.optionalReferrersOn
import mobi.sevenwinds.app.budget.BudgetEntity
import mobi.sevenwinds.app.budget.BudgetEntity.Companion.optionalReferrersOn
import mobi.sevenwinds.app.budget.BudgetTable
import mobi.sevenwinds.app.budget.BudgetTable.references
import mobi.sevenwinds.app.budget.BudgetTable.uniqueIndex
import org.jetbrains.exposed.dao.EntityID
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.IntIdTable
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Expression
import org.jetbrains.exposed.sql.Query
import org.jetbrains.exposed.sql.SqlExpressionBuilder
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

