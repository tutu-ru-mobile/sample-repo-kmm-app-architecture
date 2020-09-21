//package view
//
//import github.CommitInfo
//import github.GitHubRepo
//import kotlinx.css.*
//import kotlinx.css.properties.borderBottom
//import kotlinx.html.js.onClickFunction
//import react.RBuilder
//import react.RProps
//import react.dom.*
//import lib.stateLessRComponent
//import styled.css
//import styled.styledDiv
//import styled.styledSpan
//
//class GitHubRepoProps(
//    val gitHubRepo: GitHubRepo,
//    val onClickCommitLogBtn: () -> Unit
//) : RProps
//
//val gitHubRepoView = stateLessRComponent<GitHubRepoProps> { props ->
//    styledDiv {
//        css {
//            padding(all = 10.px)
//            backgroundColor = Color.lightGray
//        }
//        userView(props.gitHubRepo)
//        styledDiv {
//            css {
//                marginBottom = 8.px
//                if (props.gitHubRepo.commitLogs.isNotEmpty()) {
//                    paddingBottom = 8.px
//                    borderBottom(1.px, BorderStyle.solid, Color("#000").withAlpha(0.1))
//                }
//            }
//            +props.gitHubRepo.description
//        }
//
//        props.gitHubRepo.commitLogs.forEach {
//            commitView(it)
//        }
//
//        button {
//            attrs {
//                onClickFunction = {
//                    props.onClickCommitLogBtn()
//                }
//            }
//            +"Load commit history"
//        }
//    }
//}
//
//fun RBuilder.userView(repo: GitHubRepo) {
//    styledDiv {
//        css {
//            marginBottom = 16.px
//        }
//        table {
//            tr {
//                th {
//                    img(src = repo.imageUrl) {
//                        attrs.width = "100px"
//                    }
//                }
//                th {
//                    h4 {
//                        +repo.name
//                    }
//                    +repo.organization
//                }
//            }
//        }
//    }
//}
//
//fun RBuilder.commitView(commit: CommitInfo) {
//    styledDiv {
//        css {
//            lastOfType {
//                borderBottomStyle = BorderStyle.dashed
//            }
//        }
//        styledSpan {
//            +commit.time//package view
////
////import github.CommitInfo
////import github.GitHubRepo
////import kotlinx.css.*
////import kotlinx.css.properties.borderBottom
////import kotlinx.html.js.onClickFunction
////import react.RBuilder
////import react.RProps
////import react.dom.*
////import lib.stateLessRComponent
////import styled.css
////import styled.styledDiv
////import styled.styledSpan
////
////class GitHubRepoProps(
////    val gitHubRepo: GitHubRepo,
////    val onClickCommitLogBtn: () -> Unit
////) : RProps
////
////val gitHubRepoView = stateLessRComponent<GitHubRepoProps> { props ->
////    styledDiv {
////        css {
////            padding(all = 10.px)
////            backgroundColor = Color.lightGray
////        }
////        userView(props.gitHubRepo)
////        styledDiv {
////            css {
////                marginBottom = 8.px
////                if (props.gitHubRepo.commitLogs.isNotEmpty()) {
////                    paddingBottom = 8.px
////                    borderBottom(1.px, BorderStyle.solid, Color("#000").withAlpha(0.1))
////                }
////            }
////            +props.gitHubRepo.description
////        }
////
////        props.gitHubRepo.commitLogs.forEach {
////            commitView(it)
////        }
////
////        button {
////            attrs {
////                onClickFunction = {
////                    props.onClickCommitLogBtn()
////                }
////            }
////            +"Load commit history"
////        }
////    }
////}
////
////fun RBuilder.userView(repo: GitHubRepo) {
////    styledDiv {
////        css {
////            marginBottom = 16.px
////        }
////        table {
////            tr {
////                th {
////                    img(src = repo.imageUrl) {
////                        attrs.width = "100px"
////                    }
////                }
////                th {
////                    h4 {
////                        +repo.name
////                    }
////                    +repo.organization
////                }
////            }
////        }
////    }
////}
////
////fun RBuilder.commitView(commit: CommitInfo) {
////    styledDiv {
////        css {
////            lastOfType {
////                borderBottomStyle = BorderStyle.dashed
////            }
////        }
////        styledSpan {
////            +commit.time
////        }
////        styledSpan {
////            css {
////                marginLeft = 8.px
////                marginRight = 8.px
////                fontWeight = FontWeight.bold
////            }
////            +commit.author
////        }
////        styledSpan {
////            +commit.title
////        }
////    }
////}
//        }
//        styledSpan {
//            css {
//                marginLeft = 8.px
//                marginRight = 8.px
//                fontWeight = FontWeight.bold
//            }
//            +commit.author
//        }
//        styledSpan {
//            +commit.title
//        }
//    }
//}
