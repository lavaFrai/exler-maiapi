import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import ru.lavafrai.exler.mai.Exler
import ru.lavafrai.exler.mai.types.Teacher

class TeachersList {
    private val exler = Exler

    @Test
    fun `Parse faculties`() {
        print(exler.parseFaculties().size)
        print(": ")
        println(exler.parseFaculties())
    }

    @Test
    fun `Parse all teachers`() {
        print(exler.parseTeachers().size)
        print(": ")
        println(exler.parseTeachers())
    }

    @Test
    fun `Parse single teacher info`() {
        var teacher = Teacher(name="Сычёв Михаил Иванович", path="/education/prepods/04/sychyov/")
        //teacher = exler.findTeacher("Ирина Александровна Данилина ")!!
        println(Json.encodeToString(exler.parseTeacherInfo(teacher)))
    }

    /*
    @Test
    fun `Parse all teachers info`() {
        val teachers = exler.parseTeachers()
        val infos = teachers.map { exler.parseTeacherInfo(it) }
        print(infos.size)
        print(": ")
        println(infos)
    }
    */

    @Test
    fun `Find teacher`() {
        var teacher = exler.findTeacher("СЫЧЕВ МИХАИЛ ИВАНОВИЧ ")
        //Assertions.assertNotNull(teacher)

        println(teacher)
    }
}
