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

    suspend fun getName(param: AuthorNameParam): AuthorResponse = withContext(Dispatchers.IO) {
        transaction {
            val query = AuthorTable
                .select { AuthorTable.fio eq param.fio }
                .limit(param.limit, param.offset)

            val fio = query.count()
            val data = AuthorTable.AuthorEntity.wrapRows(query).map { it.toResponse() }



            return@transaction AuthorResponse(
                total = fio,
                items = data,
            )
        }
    }
}