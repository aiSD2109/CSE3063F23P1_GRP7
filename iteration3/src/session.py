from typing import Union
from person import Person
from datetime import datetime
from student import Student
from typing import Optional


class Session:
    def __init__(self, user: Person):
        self.__user = user
        self.__starting_time = datetime.now()

    def get_starting_time(self) -> datetime:
        return self.__starting_time

    def get_user(self) -> Optional[Person]:
        return self.__user

    def set_user(self, user: Person) -> None:
        self.__user = user
