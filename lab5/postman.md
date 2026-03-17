## TC-S1 - Create valid student

- **Method:** `POST`
- **Endpoint:** `/api/students`
- **Data / Body:**
```json
{
  "name": "John",
  "major": "CS",
  "gpa": 3.2
}
```
- **Postman Tests:**
```javascript
pm.test("TC-S1 create valid student returns 200", function () {
    pm.response.to.have.status(200);
});

pm.test("TC-S1 response has success message", function () {
    const jsonData = pm.response.json();
    pm.expect(jsonData.message).to.eql("John added successfully");
});
```

## TC-S2 - Create student missing major

- **Method:** `POST`
- **Endpoint:** `/api/students`
- **Data / Body:**
```json
{
  "name": "Jane",
  "gpa": 3.0
}
```
- **Postman Tests:**
```javascript
pm.test("TC-S2 missing major should fail", function () {
    pm.expect(pm.response.code).to.be.oneOf([400, 500]);
});
```

## TC-S3 - Create student GPA below 0

- **Method:** `POST`
- **Endpoint:** `/api/students`
- **Data / Body:**
```json
{
  "name": "BadGpa1",
  "major": "Math",
  "gpa": -1
}
```
- **Postman Tests:**
```javascript
pm.test("TC-S3 GPA below 0 should fail", function () {
    pm.expect(pm.response.code).to.be.oneOf([400, 500]);
});
```

## TC-S4 - Create student GPA above 4

- **Method:** `POST`
- **Endpoint:** `/api/students`
- **Data / Body:**
```json
{
  "name": "BadGpa2",
  "major": "Math",
  "gpa": 4.5
}
```
- **Postman Tests:**
```javascript
pm.test("TC-S4 GPA above 4 should fail", function () {
    pm.expect(pm.response.code).to.be.oneOf([400, 500]);
});
```

## TC-S5 - Create student name too long

- **Method:** `POST`
- **Endpoint:** `/api/students`
- **Data / Body:**
```json
{
  "name": "<256-character string>",
  "major": "CS",
  "gpa": 3.0
}
```
- **Postman Tests:**
```javascript
pm.test("TC-S5 name too long should fail", function () {
    pm.expect(pm.response.code).to.be.oneOf([400, 500]);
});
```

## TC-S6 - Get all students

- **Method:** `GET`
- **Endpoint:** `/api/students`
- **Data / Body:**
```text
No body
```
- **Postman Tests:**
```javascript
pm.test("TC-S6 get all students returns 200", function () {
    pm.response.to.have.status(200);
});

pm.test("TC-S6 response is an array", function () {
    const jsonData = pm.response.json();
    pm.expect(jsonData).to.be.an("array");
});
```

## TC-S7 - Get student by ID

- **Method:** `GET`
- **Endpoint:** `/api/students/{id}`
- **Data / Body:**
```text
Path variable: valid student id
```
- **Postman Tests:**
```javascript
pm.test("TC-S7 get student by id returns 200", function () {
    pm.response.to.have.status(200);
});

pm.test("TC-S7 student has expected fields", function () {
    const jsonData = pm.response.json();
    pm.expect(jsonData).to.have.property("name");
    pm.expect(jsonData).to.have.property("major");
    pm.expect(jsonData).to.have.property("gpa");
});
```

## TC-S8 - Update valid student

- **Method:** `PUT`
- **Endpoint:** `/api/students`
- **Data / Body:**
```json
{
  "id": 1,
  "name": "John Updated",
  "major": "Cybersecurity",
  "gpa": 3.7
}
```
- **Postman Tests:**
```javascript
pm.test("TC-S8 update student returns 200", function () {
    pm.response.to.have.status(200);
});

pm.test("TC-S8 update student success message", function () {
    const jsonData = pm.response.json();
    pm.expect(jsonData.message).to.eql("John Updated updated successfully");
});
```

## TC-S9 - Delete valid student

- **Method:** `DELETE`
- **Endpoint:** `/api/students/{id}`
- **Data / Body:**
```text
Path variable: valid student id
```
- **Postman Tests:**
```javascript
pm.test("TC-S9 delete valid student returns 200", function () {
    pm.response.to.have.status(200);
});
```

## TC-S10 - Delete nonexistent student

- **Method:** `DELETE`
- **Endpoint:** `/api/students/{id}`
- **Data / Body:**
```text
Path variable: invalid student id
```
- **Postman Tests:**
```javascript
pm.test("TC-S10 delete nonexistent student returns 404", function () {
    pm.response.to.have.status(404);
});
```

## TC-C1 - Create valid course

- **Method:** `POST`
- **Endpoint:** `/api/courses`
- **Data / Body:**
```json
{
  "name": "Testing 101",
  "instructor": "Dr. Smith",
  "maxSize": 2,
  "room": "A101",
  "roster": []
}
```
- **Postman Tests:**
```javascript
pm.test("TC-C1 create valid course returns 200", function () {
    pm.response.to.have.status(200);
});

pm.test("TC-C1 success message is correct", function () {
    pm.expect(pm.response.text()).to.eql("Testing 101 added successfully");
});
```

## TC-C2 - Create course invalid instructor type

- **Method:** `POST`
- **Endpoint:** `/api/courses`
- **Data / Body:**
```json
{
  "name": "Bad Course",
  "instructor": 2,
  "maxSize": 10,
  "room": "A101",
  "roster": []
}
```
- **Postman Tests:**
```javascript
pm.test("TC-C2 invalid instructor type should fail", function () {
    pm.response.to.have.status(400);
});
```

## TC-C3 - Get all courses

- **Method:** `GET`
- **Endpoint:** `/api/courses`
- **Data / Body:**
```text
No body
```
- **Postman Tests:**
```javascript
pm.test("TC-C3 get all courses returns 200", function () {
    pm.response.to.have.status(200);
});

pm.test("TC-C3 response is an array", function () {
    const jsonData = pm.response.json();
    pm.expect(jsonData).to.be.an("array");
});
```

## TC-C4 - Update valid course

- **Method:** `PUT`
- **Endpoint:** `/api/courses/{id}`
- **Data / Body:**
```json
{
  "name": "Testing 101 Updated",
  "instructor": "Dr. Jones",
  "maxSize": 3,
  "room": "B202",
  "roster": []
}
```
- **Postman Tests:**
```javascript
pm.test("TC-C4 update course returns 200", function () {
    pm.response.to.have.status(200);
});

pm.test("TC-C4 success message is correct", function () {
    pm.expect(pm.response.text()).to.eql("Testing 101 Updated updated successfully");
});
```

## TC-C5 - Get enrollment valid course

- **Method:** `GET`
- **Endpoint:** `/api/courses/getEnrollment/{id}`
- **Data / Body:**
```text
Path variable: valid course id
```
- **Postman Tests:**
```javascript
pm.test("TC-C5 get enrollment returns 200", function () {
    pm.response.to.have.status(200);
});

pm.test("TC-C5 enrollment is numeric", function () {
    const jsonData = pm.response.json();
    pm.expect(jsonData).to.be.a("number");
});
```

## TC-C6 - Get enrollment invalid course

- **Method:** `GET`
- **Endpoint:** `/api/courses/getEnrollment/{id}`
- **Data / Body:**
```text
Path variable: invalid course id
```
- **Postman Tests:**
```javascript
pm.test("TC-C6 invalid course enrollment returns 400", function () {
    pm.response.to.have.status(400);
});

pm.test("TC-C6 returns -1", function () {
    const jsonData = pm.response.json();
    pm.expect(jsonData).to.eql(-1);
});
```

## TC-R1 - Add student when course has space

- **Method:** `PUT`
- **Endpoint:** `/api/courses/addStudent/{courseId}`
- **Data / Body:**
```text
Raw JSON number only: 1
```
- **Postman Tests:**
```javascript
pm.test("TC-R1 add student with space returns 200", function () {
    pm.response.to.have.status(200);
});

pm.test("TC-R1 add student success message", function () {
    pm.expect(pm.response.text()).to.eql("John added successfully");
});
```

## TC-R2 - Add student to fill course

- **Method:** `PUT`
- **Endpoint:** `/api/courses/addStudent/{courseId}`
- **Data / Body:**
```text
Raw JSON number only: 2
```
- **Postman Tests:**
```javascript
pm.test("TC-R2 add second student returns 200", function () {
    pm.response.to.have.status(200);
});
```

## TC-R3 - Add student when course is full

- **Method:** `PUT`
- **Endpoint:** `/api/courses/addStudent/{courseId}`
- **Data / Body:**
```text
Raw JSON number only: 3
```
- **Postman Tests:**
```javascript
pm.test("TC-R3 adding student to full course should fail", function () {
    pm.response.to.have.status(400);
});

pm.test("TC-R3 correct full-course message", function () {
    pm.expect(pm.response.text()).to.eql("Course is full");
});
```

## TC-R4 - Remove student in course

- **Method:** `PUT`
- **Endpoint:** `/api/courses/removeStudent/{courseId}`
- **Data / Body:**
```text
Raw JSON number only: 1
```
- **Postman Tests:**
```javascript
pm.test("TC-R4 remove student in course returns 200", function () {
    pm.response.to.have.status(200);
});

pm.test("TC-R4 remove student success message", function () {
    pm.expect(pm.response.text()).to.eql("John removed successfully");
});
```

## TC-R5 - Remove student not in course

- **Method:** `PUT`
- **Endpoint:** `/api/courses/removeStudent/{courseId}`
- **Data / Body:**
```text
Raw JSON number only: 1
```
- **Postman Tests:**
```javascript
pm.test("TC-R5 remove student not in course should fail", function () {
    pm.response.to.have.status(400);
});

pm.test("TC-R5 correct not-in-course message", function () {
    pm.expect(pm.response.text()).to.eql("Student not in that course");
});
```

## TC-R6 - Add nonexistent student

- **Method:** `PUT`
- **Endpoint:** `/api/courses/addStudent/{courseId}`
- **Data / Body:**
```text
Raw JSON number only: 9999
```
- **Postman Tests:**
```javascript
pm.test("TC-R6 add nonexistent student should fail", function () {
    pm.response.to.have.status(400);
});
```

## TC-R7 - Add to nonexistent course

- **Method:** `PUT`
- **Endpoint:** `/api/courses/addStudent/9999`
- **Data / Body:**
```text
Raw JSON number only: 1
```
- **Postman Tests:**
```javascript
pm.test("TC-R7 add to nonexistent course should fail", function () {
    pm.response.to.have.status(400);
});
```

## TC-R8 - Get enrollment after valid adds

- **Method:** `GET`
- **Endpoint:** `/api/courses/getEnrollment/{courseId}`
- **Data / Body:**
```text
No body
```
- **Postman Tests:**
```javascript
pm.test("TC-R8 get enrollment returns 200", function () {
    pm.response.to.have.status(200);
});

pm.test("TC-R8 enrollment is numeric", function () {
    const jsonData = pm.response.json();
    pm.expect(jsonData).to.be.a("number");
});
```

## TC-R9 - Enrollment does not exceed max size

- **Method:** `GET`
- **Endpoint:** `/api/courses/getEnrollment/{courseId}`
- **Data / Body:**
```text
No body; run after TC-R3 failed add
```
- **Postman Tests:**
```javascript
pm.test("TC-R9 enrollment should remain at max size", function () {
    pm.response.to.have.status(200);
    const enrollment = pm.response.json();
    pm.expect(enrollment).to.eql(2);
});
```
