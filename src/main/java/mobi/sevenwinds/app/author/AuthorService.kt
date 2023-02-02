package mobi.sevenwinds.app.author

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction

object AuthorService {
    suspend fun addRecord(body: AuthorRecord): AuthorRecord = withContext(Dispatchers.IO) {
        transaction {
            val entity = AuthorTable.AuthorEntity.new {
                this.fio = body.fio
            }

            return@transaction entity.toResponse()
        }
    }

//    suspend fun getYearStats(param: BudgetYearParam): BudgetYearStatsResponse = withContext(Dispatchers.IO) {
//        transaction {
//            val query = BudgetTable
//                .select { BudgetTable.year eq param.year }
//                .limit(param.limit, param.offset)
//
//            val total = query.count()
//            val data = BudgetEntity.wrapRows(query).map { it.toResponse() }
//
//            val sumByType = data.groupBy { it.type.name }.mapValues { it.value.sumOf { v -> v.amount } }
//
//            return@transaction BudgetYearStatsResponse(
//                total = total,
//                totalByType = sumByType,
//                items = data
//            )
//        }
//    }
}