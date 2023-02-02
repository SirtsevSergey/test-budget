package mobi.sevenwinds.app.author

import com.papsign.ktor.openapigen.annotations.parameters.PathParam
import com.papsign.ktor.openapigen.annotations.parameters.QueryParam
import com.papsign.ktor.openapigen.annotations.type.number.integer.max.Max
import com.papsign.ktor.openapigen.annotations.type.number.integer.min.Min
import com.papsign.ktor.openapigen.route.info
import com.papsign.ktor.openapigen.route.path.normal.NormalOpenAPIRoute
import com.papsign.ktor.openapigen.route.path.normal.get
import com.papsign.ktor.openapigen.route.path.normal.post
import com.papsign.ktor.openapigen.route.response.respond
import com.papsign.ktor.openapigen.route.route

fun NormalOpenAPIRoute.author() {
    route("/author") {
        route("/add").post<Unit, AuthorRecord, AuthorRecord>(info("Добавить запись")) { param, body ->
            respond(AuthorService.addRecord(body))
        }

//        route("/year/{year}/stats") {
//            get<AurhorYearParam, BudgetYearStatsResponse>(info("Получить статистику за год")) { param ->
//                respond(AuthorService.getYearStats(param))
//            }
//        }
    }
}

data class AuthorRecord(
    val fio: String
)

//data class BudgetYearParam(
//    @PathParam("Год") val year: Int,
//    @QueryParam("Лимит пагинации") val limit: Int,
//    @QueryParam("Смещение пагинации") val offset: Int,
//)
//
//class BudgetYearStatsResponse(
//    val total: Int,
//    val totalByType: Map<String, Int>,
//    val items: List<BudgetRecord>
//)
//
//enum class BudgetType {
//    Приход, Расход, Комиссия