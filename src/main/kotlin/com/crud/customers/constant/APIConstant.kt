package com.crud.customers.constant

class APIConstant {

    companion object {
        const val BASE_API = "/v1"
        const val SERVICE_CREATE_CUSTOMER = "/customer"
        const val SERVICE_GET_CUSTOMER = "/customer"
        const val SERVICE_DELETE_CUSTOMER = "/customer/{id}"
        const val SERVICE_UPDATE_CUSTOMER = "/customer/{id}"

        const val ERROR_400 = "Entity already exists in the system."
        const val ERROR_404 = "Entity does not exist in the system."
        const val ERROR_412 = "There are fields with inconsistencies."
        const val ERROR_500 = "Internal server error."

        const val DETAILS_ERROR_404 = "No customer was found with the document presented. Check the document and try again."
        const val DETAILS_ERROR_500 = "An internal server error occurred, contact your system administrator."
    }
}