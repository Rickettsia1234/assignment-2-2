package com.example.assignment2_1

fun main()
{
    //#1 리스트 중복 제거 : LinkedHashSet으로 데이터의 순서를 기억하는 set 자료구조를 이용해 중복을 제거함
    val myList: List<String> = listOf("1","1","2","2","3","3","4","4","5","5","6","6","7")
    println(myList)
    println(myList.distinct())
    //#2 리스트의 인덱스를 띄어넘어서 반환하기
    println(myList.filterByIndexStep())
    println(myList.filterByIndexStep(3))
    println(myList.filterByIndexStep(4))
    //#3 BCSDLab 비기너, 레귤러, 멘토 클래스 모의구현
    val beginner: Beginner = Beginner("김")
    val regular: Regular = Regular("민")
    val mentor: Mentor = Mentor("지")
    //#4 모의구현 클래스 심화
    var bcsdList: List<BcsdLabUser> = listOf(
        Beginner("김1", 10),
        Beginner("김2", 9),
        Beginner("양3", 3),
        Beginner("김4", 2),
        Beginner("김5", 1),
        Regular("민1", true),
        Regular("양2", true),
        Regular("민3", false),
        Mentor("지1", "지1@지1.지1.지1"),
        Mentor("양2", "지2@지2.지2.지2")
    )
    bcsdList.filterIsInstance<Beginner>().filter{ it.successfulAssignmentCount >= 3 }.forEach{println(it.printTheosStatus())}
    bcsdList.filterIsInstance<Regular>().filter { !it.isFeePaid }.forEach{println(it.printTheosStatus())}
    bcsdList.filter{ it.name[0] == '양' }.forEach{println(it.printTheosStatus())}
}

fun <T> List<T>.filterByIndexStep(n: Int = 2) : List<T>//함수의 매개변수는 자동으로 val로 취급되서 붙이면 안된다
{
    var result = mutableListOf<T>()
    for(i in 0 until this.size step n)
    {
        result.add(this[i])
    }
    return result
}

sealed class BcsdLabUser()
{
    abstract val name: String
    abstract val emoji: String
}

class Beginner(override val name: String, var successfulAssignmentCount: Int = 0) :BcsdLabUser()
{
    override val emoji: String = "🌱"
}

class Regular(override val name: String, var isFeePaid: Boolean = false) :BcsdLabUser()
{
    override val emoji: String = "🍏"
}

class Mentor(override val name: String, var email: String = "has no email") :BcsdLabUser()
{
    override val emoji: String = "✨"
}

fun BcsdLabUser.printTheosStatus(): String
{
    return when(this)
    {
        is Beginner -> "${this.name} 비기너${this.emoji}, 성공횟수 : ${this.successfulAssignmentCount}"
        is Regular -> "${this.name} 레귤러${this.emoji}, 회비 제출 : ${this.isFeePaid}"
        is Mentor -> "${this.name} 멘토${this.emoji}, 이메일 : ${this.email}"
    }
}