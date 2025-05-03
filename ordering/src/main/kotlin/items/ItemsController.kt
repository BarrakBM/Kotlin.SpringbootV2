//package items
//
//import io.swagger.v3.oas.annotations.tags.Tag
//import org.springframework.web.bind.annotation.GetMapping
//import org.springframework.web.bind.annotation.PostMapping
//import org.springframework.web.bind.annotation.RequestBody
//import org.springframework.web.bind.annotation.RestController
//
//@Tag(name = "ItemAPI")
//@RestController
//class ItemsController(
//    private val itemsService: ItemsService
//) {
//
//    @GetMapping("/items/list")
//    fun items() = itemsService.listItems()
//
//    @PostMapping("/items/create")
//    fun addItems(@RequestBody request: CreateItemRequestDTO): ItemDTO {
//        return itemsService.createItem(request)
//    }
//}