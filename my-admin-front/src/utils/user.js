const USER_KEY = 'rui:user'

export function setUserInfo(userInfo) {
  return sessionStorage.setItem(USER_KEY, userInfo)
}

export function getUserInfo() {
  return sessionStorage.getItem(USER_KEY)
}

export function removeUserInfo() {
  return sessionStorage.removeItem(USER_KEY)
}
