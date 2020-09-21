@OptIn(ExperimentalStdlibApi::class)
val dictionary2
    get() = Dictionary(
        name = "Англо-русский",
        useByDefault = false,
        words = buildList {
            word("Dog", "Собака")
            word("Cat", "Кошка")
        }
    )

