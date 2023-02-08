package mobi.sevenwinds.app.budget

import io.ktor.utils.io.core.*
import mobi.sevenwinds.app.author.AuthorTable
import mobi.sevenwinds.app.author.AuthorTable.AuthorEntity.Companion.optionalReferrersOn
import org.jetbrains.exposed.dao.EntityID
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.IntIdTable
import kotlin.reflect.KProperty

object BudgetTable : IntIdTable("budget") {
    val year = integer("year")
    val month = integer("month")
    val amount = integer("amount")
    val type = enumerationByName("type", 100, BudgetType::class)
    val author = reference("author", AuthorTable).nullable()
}

class BudgetEntity(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<BudgetEntity>(BudgetTable)

    var year by BudgetTable.year
    var month by BudgetTable.month
    var amount by BudgetTable.amount
    var type by BudgetTable.type
//    var author by AuthorTable optionalReferrersOn BudgetTable.author

    fun toResponse(): BudgetRecord {
        return BudgetRecord(year, month, amount, type)
    }

//    object AuthorBudget: IntIdTable() {
//        val value = long("value")
//        val budgetId = reference("budget", BudgetTable)
//        val author = reference("author", AuthorTable)
//    }
//    class AuthorBudget(id: EntityID<Int>): IntEntity(id) {
//        companion object : IntEntityClass<AuthorBudget>(AuthorBudget)
//
//        var value by AuthorBudget.value
//        var budget by BudgetTable referencedOn AuthorTable.id
//        var author by AuthorTable referencedOn AuthorTable.id
//    }
}

