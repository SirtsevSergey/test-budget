package mobi.sevenwinds.app.author

import com.papsign.ktor.openapigen.annotations.parameters.PathParam
import com.papsign.ktor.openapigen.annotations.parameters.QueryParam
import com.papsign.ktor.openapigen.annotations.type.number.integer.min.Min
import com.papsign.ktor.openapigen.route.info
import com.papsign.ktor.openapigen.route.path.normal.NormalOpenAPIRoute
import com.papsign.ktor.openapigen.route.path.normal.get
import com.papsign.ktor.openapigen.route.path.normal.post
import com.papsign.ktor.openapigen.route.response.respond
import com.papsign.ktor.openapigen.route.route
import mobi.sevenwinds.app.budget.BudgetRecord

fun NormalOpenAPIRoute.author() {
    route("/author") {
        route("/add").post<Unit, AuthorRecord, AuthorRecord>(info("Добавить запись")) { param, body ->
            respond(AuthorService.addRecord(body))
        }
    }
}

data class AuthorRecord(
    val fio: String

)
data class AuthorNameParam(
    @PathParam("Имя Автора") val fio: String,
    @QueryParam("id") val idAuthorNameParam:  Int,
//    val sql = """
//        SELECT * FROM author
//    """.trimIndent()

) {

}

class AuthorResponse(
    val total: Int,
    val items: List<AuthorRecord>
)