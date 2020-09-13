package com.sample

class SolutionSearchFormTelegramImpl(
    val commonImpl: SolutionSearchFormImpl
//    val attentionAndroid: SolutionAttentionAndroidApi
) : SolutionSearchFormTelegramApi {

    override fun renderSearchForm(): Content {
        return Content.Button("Начать поиск") {
            commonImpl.store.send(SolutionSearchFormImpl.Action.Search)
        }
//        WrapColorBox(color = commonImpl.getColor()) {
//            Column {
//                Central {
//                    Box(
//                        modifier = Modifier.padding(16.dp),
//                        backgroundColor = commonImpl.attentionBackgroundColor.toComposeColor()
//                    ) {
//                        attentionAndroid.renderMainScreenAttention()
//                    }
//                }
//
//                TextField(
//                    value = commonImpl.store.state.searchFrom,
//                    onValueChange = {
//                        commonImpl.store.send(SolutionSearchFormImpl.Action.From(it))
//                    },
//                    label = { Text("Откуда") },
//                    modifier = Modifier.padding(16.dp) + Modifier.fillMaxWidth()
//                )
//                TextField(
//                    value = commonImpl.store.state.searchTo,
//                    onValueChange = {
//                        commonImpl.store.send(SolutionSearchFormImpl.Action.To(it))
//                    },
//                    label = { Text("Куда") },
//                    modifier = Modifier.padding(16.dp) + Modifier.fillMaxWidth()
//                )
//
//                Button(
//                    modifier = Modifier.padding(16.dp) + Modifier.fillMaxWidth(),
//                    onClick = {
//                        commonImpl.store.send(SolutionSearchFormImpl.Action.Search)
//                    }) {
//                    Text("Начать поиск")
//                }
//            }
    }

}

