package mobi.sevenwinds.app.budget

import kotlinx.coroutines.selects.select
import mobi.sevenwinds.app.author.AuthorTable
import mobi.sevenwinds.app.budget.BudgetTable.nullable
import mobi.sevenwinds.app.budget.BudgetTable.varchar
import org.jetbrains.exposed.dao.EntityID
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.IntIdTable
import retrofit2.http.Query

object BudgetTable : IntIdTable("budget") {
    val year = integer("year")
    val month = integer("month")
    val amount = integer("amount")
    val type = enumerationByName("type", 100, BudgetType::class)
    val idAuthor = integer("idAuthor").references(AuthorTable.id).nullable()
    val fio = AuthorTable.fio.nullable()

}


class BudgetEntity(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<BudgetEntity>(BudgetTable)

    //    var idAuthorEntityID by AuthorTable.id
    var year by BudgetTable.year
    var month by BudgetTable.month
    var amount by BudgetTable.amount
    var type by BudgetTable.type
    var idAuthor by BudgetTable.idAuthor
    var fioAuthor by BudgetTable.fio

//    var authorId by AuthorTable optionalReferrersOn BudgetTable.authorId


//    var authorId by AuthorTable optionalReferrersOn BudgetTable.authorId

    fun toResponse(): BudgetRecord {
        return BudgetRecord(year, month, amount, type, idAuthor, fioAuthor)
    }

    suspend fun getIdAuthor(): Int? {
        select<Query> { BudgetTable.idAuthor }
        return idAuthor
    }

}



