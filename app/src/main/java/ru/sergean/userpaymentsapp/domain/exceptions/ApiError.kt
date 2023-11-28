package ru.sergean.userpaymentsapp.domain.exceptions

open class ApiError : Exception()

class IncorrectUserDataError : ApiError()

class TokenNotFoundError : ApiError()

class ValidateError : Exception()
