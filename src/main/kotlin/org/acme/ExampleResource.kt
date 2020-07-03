package org.acme

import io.vertx.core.json.JsonArray
import io.vertx.core.json.JsonObject
import io.vertx.mutiny.mysqlclient.MySQLPool
import io.vertx.mutiny.sqlclient.Row
import io.vertx.mutiny.sqlclient.RowSet
import java.util.concurrent.CompletionStage
import javax.inject.Inject
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType

@Path("/fruits")
class FruitResource {
    @field:Inject
    lateinit var client: MySQLPool


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    fun listFruits(): CompletionStage<JsonArray> {
        return client.query("SELECT * FROM fruits").execute()
                .map { rows: RowSet<Row> ->
                    rows.fold(JsonArray()) { array, row ->
                        array.add(JsonObject()
                                .put("id", row.getLong("id"))
                                .put("name", row.getString("name")))
                    }
                }.subscribeAsCompletionStage()
    }
}