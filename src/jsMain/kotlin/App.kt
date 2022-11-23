import emotion.react.css
import csstype.*
import react.*
import kotlinx.coroutines.*
import react.dom.html.ReactHTML.div
import react.dom.html.ReactHTML.h1
import react.dom.html.ReactHTML.li
import react.dom.html.ReactHTML.p
import react.dom.html.ReactHTML.ul

private val scope = MainScope()

val App = FC<Props> {
    var shoppingList by useState(emptyList<ShoppingListItem>())

    useEffectOnce {
        scope.launch {
            shoppingList = getShoppingList()
        }
    }

    div {
        h1 {
            css {
                display = Display.flex
                justifyContent = JustifyContent.spaceAround
            }
            +"Christmas wish list"
        }

        ul {
            shoppingList.sortedByDescending(ShoppingListItem::priority).forEach { item ->
                li {
                    key = item.toString()
                    onClick = {
                        scope.launch {
                            deleteShoppingListItem(item)
                            shoppingList = getShoppingList()
                        }
                    }
                    +"[${item.priority}] ${item.desc} "
                }
            }
        }
        p {
            +"Add item to wish list (insert ! to set priority). Press item to delete from list"
        }
        inputComponent {
            onSubmit = { input ->
                val cartItem = ShoppingListItem(input.replace("!", ""), input.count { it == '!' })
                scope.launch {
                    addShoppingListItem(cartItem)
                    shoppingList = getShoppingList()
                }
            }
        }
    }
}
