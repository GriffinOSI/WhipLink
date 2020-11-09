//import io.ktor.http.*
//import io.ktor.routing.*
//import io.ktor.server.testing.*
//import org.jetbrains.spek.api.Spek
//import org.jetbrains.spek.api.dsl.given
//
//object ApplicationSpec: Spek({
//    given("an application") {
//        val engine = TestApplicationEngine(createTestEnvironment())
//        engine.start(wait = false)
//        engine.application.routing {  }
//
//        with(engine) {
//            on("index") {
//                it("should redirect on base path") {
//                    handleRequest(HttpMethod.Get, "/").let { call ->
//                    }
//                }
//                it("should return 200 on create with valid param ") {
//                    handleRequest(HttpMethod.Post, "/create").let { call ->
//                    }
//                }
//                it("should return 400 on create with no params") {
//                    handleRequest(HttpMethod.Post, "/create").let { call ->
//                    }
//                }
//                it("should return error message if no short link exists") {
//                    handleRequest(HttpMethod.Post, "/invalidShortLink").let { call ->
//                    }
//                }
//                it("should return redirect if short link exists") {
//                    handleRequest(HttpMethod.Post, "/invalidShortLink").let { call ->
//                    }
//                }
//            }
//        }
//    }
//})