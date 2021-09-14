# Beşinci hafta ödevi teslim tarihi : 14 Eylül Salı - Saat 23:00

![hm05](https://user-images.githubusercontent.com/45206582/132606840-bcc89ab7-37f4-4bbd-a950-227b838b0b3c.PNG)

---
### How to use ? 
* Run the `FifthHomeworkYetanrikuluApplication`
* Go to `localhost:8080/swagger-ui.html` for endpoints.
* Go to `localhost:8080/h2-console` for database.
---
### Features
* Instructors, Courses and Students can be created using object information.
* Whole Instructors, Courses and Students can be listed.
* Instructors, Courses and Students can be deleted by using id or object information.
* Instructors, Courses and Students can be updated using object information.
* Exceptions can be listed.
* Date Format should be YYYY-MM-DD.
---

### Exceptions
* Student age should be between 18 and 40, otherwise `StudentAgeNotValidException` is thrown.
* 2 instructors can't have same phone number, otherwise `InstructorIsAlreadyExistException` is thrown.
* 2 courses can't have same course code, otherwise `CourseIsAlreadyExistException` is thrown.
* Each course can be taken by at most 20 students, otherwise `StudentNumberForOneCourseExceededException` is thrown.

---
### Endpoints 
![instructor](https://i.hizliresim.com/4a44bdf.jpg)
![student](https://i.hizliresim.com/jop2eg3.jpg)
![course](https://i.hizliresim.com/iyfeym1.jpg)
![exception](https://i.hizliresim.com/bhytt6m.jpg)
---


