import json
import random
names = ["Ensar", "Fernando", "Turan", "Said", "Muhammet", "Kadir", "Feyz", "Adullah", "Muhamammed", "Chris"]
surnames = ["Yozgat", "Alonso", "Kaya", "Canak", "Karagul", "Bat", "Asil", "Kan", "Nael", "Bumstead"]

faculty = "Engineering"
depart = "Computer Science Engineering"


for i in range(0, 10):

    id = 150120000 + i
    name = names[i]
    if(id % 2 == 0):
        advisorsID = 150101
    else:
        advisorsID = 150102


    students = {
        "ID" : id,

        "Name" : name,

        "Surname" : surnames[i],

        "Password" : (str)(id) + (str)(name),

        "EMail" : name + "@marun.edu.tr",

        "Faculty" : faculty,

        "Department" : depart,

        "AdvisorID" : advisorsID,

        "Semester": 5,

        "Transcript": {
            "Semester": [
                {
                    "Courses" : [   
                        {
                            "CourseID": "ATA121",
                            "CourseName": "Ataturks Principles and the History of Turkish Renovation I",
                            "CourseType": "M",
                            "Credit": 2,
                            "Semester": 1,
                            "Grade": 0
                        },
                        {
                            "CourseID": "MBG1201",
                            "CourseName": "Introduction to Modern Biology",
                            "CourseType": "M",
                            "Credit": 5,
                            "Semester": 1,
                            "Grade": 0
                        },
                        {
                            "CourseID": "CSE1200",
                            "CourseName": "Introduction to Computer Engineering",
                            "CourseType": "M",
                            "Credit": 4,
                            "Semester": 1,
                            "Grade": 0
                        },
                        {
                            "CourseID": "CSE1241",
                            "CourseName": "Computer Programming I",
                            "CourseType": "M",
                            "Credit": 6,
                            "Semester": 1,
                            "Grade": 0
                        },
                        {
                            "CourseID": "MATH1001",
                            "CourseName": "Calculus I",
                            "CourseType": "M",
                            "Credit": 6,
                            "Semester": 1,
                            "Grade": 0
                        },
                        {
                            "CourseID": "PHYS1101",
                            "CourseName": "Physics I",
                            "CourseType": "M",
                            "Credit": 4,
                            "Semester": 1,
                            "Grade": 0
                        },
                        {
                            "CourseID": "PHYS1103",
                            "CourseName": "Physics Lab I",
                            "CourseType": "M",
                            "Credit": 2,
                            "Semester": 1,
                            "Grade": 0
                        },
                        {
                            "CourseID": "TRD121",
                            "CourseName": "Turk Dili I",
                            "CourseType": "M",
                            "Credit": 2,
                            "Semester": 1,
                            "Grade": 0
                        }
                    ],
                    
                    "SemesterInf" : {
                        "TakenCredit" : "null",
                        "ComplatedCredit" : "null",
                        "Yano" : "null",
                        "Gano" : " "
                    }
                },

                {
                    "Courses" : [
                        {
                            "CourseID": "ATA122",
                            "CourseName": "Ataturk’s Principles and the History of Turkish Renovation II",
                            "CourseType": "M",
                            "Credit": 2,
                            "Semester": 2,
                            "Grade": 0
                        },
                        {
                            "CourseID": "CSE1242",
                            "CourseName": "Computer Programming II",
                            "CourseType": "M",
                            "Credit": 6,
                            "Semester": 2,
                            "Grade": 0
                        },
                        {
                            "CourseID": "MATH1002",
                            "CourseName": "Calculus II",
                            "CourseType": "M",
                            "Credit": 6,
                            "Semester": 2,
                            "Grade": 0
                        },
                        {
                            "CourseID": "MATH2256",
                            "CourseName": "Linear Algebra for Computer Engineering",
                            "CourseType": "M",
                            "Credit": 5,
                            "Semester": 2,
                            "Grade": 0
                        },
                        {
                            "CourseID": "PHYS1102",
                            "CourseName": "Physics II",
                            "CourseType": "M",
                            "Credit": 4,
                            "Semester": 2,
                            "Grade": 0
                        },
                        {
                            "CourseID": "PHYS1104",
                            "CourseName": "Physics Lab II",
                            "CourseType": "M",
                            "Credit": 2,
                            "Semester": 2,
                            "Grade": 0
                        },
                        {
                            "CourseID": "TRD122",
                            "CourseName": "Turk Dili II",
                            "CourseType": "M",
                            "Credit": 2,
                            "Semester": 2,
                            "Grade": 0
                        },
                        {
                            "CourseID": "NTE1",
                            "CourseName": "Non-Technical Elective - 1",
                            "CourseType": "E",
                            "Credit": 3,
                            "Semester": 2,
                            "Grade": 0
                        }
                    ],

                    "SemesterInf" : {
                        "TakenCredit" : "null",
                        "ComplatedCredit" : "null",
                        "Yano" : "null",
                        "Gano" : " "
                    }
                },

                {
                    "Courses" : [
                        {
                            "CourseID": "CSE2023",
                            "CourseName": "Discrete Computational Structures",
                            "CourseType": "M",
                            "Credit": 6,
                            "Semester": 3,
                            "Grade": 0

                        },
                        {
                            "CourseID": "CSE2225",
                            "CourseName": "Data Structures",
                            "CourseType": "M",
                            "Credit": 7,
                            "Semester": 3,
                            "Grade": 0
                        },
                        {
                            "CourseID": "ECON2004",
                            "CourseName": "Engineering Economy",
                            "CourseType": "M",
                            "Credit": 4,
                            "Semester": 3,
                            "Grade": 0
                        },
                        {
                            "CourseID": "EE2031",
                            "CourseName": "Electric Circuits",
                            "CourseType": "M",
                            "Credit": 5,
                            "Semester": 3,
                            "Grade": 0
                        },
                        {
                            "CourseID": "MATH2055",
                            "CourseName": "Differential Equations",
                            "CourseType": "M",
                            "Credit": 4,
                            "Semester": 3,
                            "Grade": 0
                        },
                        {
                            "CourseID": "MATH2059",
                            "CourseName": "Numerical Methods",
                            "CourseType": "M",
                            "Credit": 4,
                            "Semester": 3,
                            "Grade": 0
                        }
                    ],

                    "SemesterInf" : {
                        "TakenCredit" : "null",
                        "ComplatedCredit" : "null",
                        "Yano" : "null",
                        "Gano" : " "
                    }

                },

                {
                    "Courses" : [
                        {
                            "CourseID": "CSE2246",
                            "CourseName": "Analysis of Algorithms",
                            "CourseType": "M",
                            "Credit": 6,
                            "Semester": 4,
                            "Grade": 0
                        },
                        {
                            "CourseID": "CSE2138",
                            "CourseName": "Systems Programming",
                            "CourseType": "M",
                            "Credit": 7,
                            "Semester": 4,
                            "Grade": 0
                        },
                        {
                            "CourseID": "CSE2260",
                            "CourseName": "Principles of Programming Languages",
                            "CourseType": "M",
                            "Credit": 6,
                            "Semester": 4,
                            "Grade": 0
                        },
                        {
                            "CourseID": "EE2032",
                            "CourseName": "Electronics",
                            "CourseType": "M",
                            "Credit": 5,
                            "Semester": 4,
                            "Grade": 0
                        },
                        {
                            "CourseID": "STAT2253",
                            "CourseName": "Introduction to Probability and Statistics",
                            "CourseType": "M",
                            "Credit": 5,
                            "Semester": 4,
                            "Grade": 0
                        }
                    ],

                    "SemesterInf" : {
                        "TakenCredit" : "null",
                        "ComplatedCredit" : "null",
                        "Yano" : "null",
                        "Gano" : " "
                    }

                }

            ]

        }
    
    }

    # for i in students["Transcript"]["Semester"]:
    # semesterCredit = 0
    # for j in i["Courses"]:
    #     semesterCredit += j["Credit"]
    #     print(semesterCredit)

    for k in students["Transcript"]["Semester"]:
        for j in k["Courses"]:
            numbers = [0, 1, 1.5, 2, 2.5, 3, 3.5, 4]
            weights = [5, 10, 10, 25, 25, 25, 25, 10]

            selected_number = random.choices(numbers, weights=weights)[0]

            j["Grade"] = selected_number

            # print(j["Grade"])

    

    fileName = (str)(id) + ".json"
    with open(fileName, "w") as outfile:
        json.dump(students, outfile)


