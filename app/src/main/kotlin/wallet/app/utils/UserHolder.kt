package wallet.app.utils

import jakarta.servlet.http.HttpServletRequest
import org.springframework.web.context.request.RequestContextHolder
import org.springframework.web.context.request.ServletRequestAttributes

class UserHolder {

    private fun getRequest(): HttpServletRequest? {
        return (RequestContextHolder.getRequestAttributes() as? ServletRequestAttributes)?.request
    }

    fun getUserName() = getRequest()?.getHeader("iv-user")
}